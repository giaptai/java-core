package oop.encapsulation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface Component {
}

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface Autowired {
}

class BeanDefinition {
    private final Class<?> beanClass;

    public BeanDefinition(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    public Class<?> getBeanClass() {
        return this.beanClass;
    }
}

interface BeanPostProcessor {
    void process(Object bean) throws Exception;
}

class AutowiredAnnotationBeanPostProcessor implements BeanPostProcessor {
    private final ApplicationContext context;

    public AutowiredAnnotationBeanPostProcessor(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public void process(Object bean) throws Exception {
        Class<?> clazz = bean.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Autowired.class)) {
                Object dependency = context.getBean(field.getType());
                field.setAccessible(true);
                field.set(bean, dependency);
            }
        }
    }
}

class ApplicationContext {
    private Map<String, BeanDefinition> beanDefinitions = new HashMap<>();
    private Map<String, Object> singletonObjects = new HashMap<>();
    private final List<BeanPostProcessor> postProccessors = new ArrayList<>();

    public ApplicationContext() {
        postProccessors.add(new AutowiredAnnotationBeanPostProcessor(this));
    }

    public void scan(Class<?>... classes) {
        for (Class<?> clazz : classes) {
            if (clazz.isAnnotationPresent(Component.class)) {
                String beanName = clazz.getSimpleName();
                BeanDefinition bd = new BeanDefinition(clazz);
                beanDefinitions.put(beanName, bd);
            }
        }
    }

    public void refresh() throws Exception {
        for (String beanName : beanDefinitions.keySet()) {
            getBean(beanName);
        }
    }

    private Object createBean(String beanName, BeanDefinition beanDefinition) throws Exception {
        Class<?> beanClass = beanDefinition.getBeanClass();
        Object bean = beanClass.getDeclaredConstructor().newInstance();
        singletonObjects.put(beanName, bean);
        for (BeanPostProcessor processor : postProccessors) {
            processor.process(bean);
        }
        return bean;
    }

    private Object getBean(String beanName) throws Exception {
        if (singletonObjects.containsKey(beanName)) {
            return singletonObjects.get(beanName);
        }
        BeanDefinition beanDefinition = beanDefinitions.get(beanName);
        if (beanDefinition == null) {
            throw new RuntimeException("Bean not found: " + beanName);
        }
        return createBean(beanName, beanDefinition);
    }

    public <T> T getBean(Class<T> type) throws Exception {
        for (Map.Entry<String, BeanDefinition> entry : beanDefinitions.entrySet()) {
            Class<?> beanClass = entry.getValue().getBeanClass();
            if (type.isAssignableFrom(beanClass)) {
                Object bean = getBean(entry.getKey());
                return type.cast(bean);
            }
        }
        throw new RuntimeException("Bean not found: " + type.getName());
    }
}

@Component
class CarService {
    @Autowired
    private UserService userService; // A cần B
}

@Component
class UserService {
    @Autowired
    private CarService carService;   // B cần A
}

public class main {
    public static void main(String[] args) {
        ApplicationContext context = new ApplicationContext();
        context.scan(CarService.class, UserService.class);
        try {
            context.refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
