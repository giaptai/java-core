# Câu hỏi phỏng vấn về Java 8+ Features

## Câu 1: Lambda Expression là gì? Syntax như thế nào?

**Đáp án:**

Lambda Expression là cách viết ngắn gọn cho anonymous function (functional interface).

### Syntax:

```java
(parameters) -> expression
(parameters) -> { statements; }
```

### Ví dụ:

```java
// Trước Java 8: Anonymous class
Runnable r1 = new Runnable() {
    @Override
    public void run() {
        System.out.println("Hello");
    }
};

// Java 8: Lambda
Runnable r2 = () -> System.out.println("Hello");

// Với parameters
Comparator<Integer> comp1 = (a, b) -> a - b;
Comparator<Integer> comp2 = (a, b) -> {
    return a - b;
};

// Type inference
List<String> list = Arrays.asList("a", "b", "c");
list.forEach(s -> System.out.println(s)); // Compiler tự hiểu s là String
list.forEach(System.out::println);        // Method reference
```

### Đặc điểm:
- Chỉ dùng với Functional Interface (interface có 1 abstract method)
- Ngắn gọn, dễ đọc hơn anonymous class
- Có thể capture variables (effectively final)

```java
int factor = 2;
Function<Integer, Integer> multiply = x -> x * factor;
// factor = 3; // Error: phải effectively final
```

---

## Câu 2: Functional Interface là gì? Các loại thường dùng?

**Đáp án:**

Functional Interface là interface có **đúng 1 abstract method** (SAM - Single Abstract Method).

### Annotation:
```java
@FunctionalInterface
public interface MyFunction {
    int apply(int x); // 1 abstract method

    // Có thể có default methods
    default void print() {
        System.out.println("Print");
    }

    // Có thể có static methods
    static void staticMethod() {
        System.out.println("Static");
    }
}
```

### Built-in Functional Interfaces (java.util.function):

**1. Predicate\<T\> - Test condition**
```java
@FunctionalInterface
public interface Predicate<T> {
    boolean test(T t);
}

// Sử dụng
Predicate<Integer> isEven = x -> x % 2 == 0;
System.out.println(isEven.test(4)); // true

List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
numbers.stream()
       .filter(x -> x % 2 == 0) // Predicate
       .forEach(System.out::println); // 2, 4
```

**2. Function\<T, R\> - Transform T to R**
```java
@FunctionalInterface
public interface Function<T, R> {
    R apply(T t);
}

// Sử dụng
Function<String, Integer> length = s -> s.length();
System.out.println(length.apply("Java")); // 4

List<String> names = Arrays.asList("Java", "Python", "C++");
names.stream()
     .map(s -> s.length()) // Function<String, Integer>
     .forEach(System.out::println); // 4, 6, 3
```

**3. Consumer\<T\> - Consume T, no return**
```java
@FunctionalInterface
public interface Consumer<T> {
    void accept(T t);
}

// Sử dụng
Consumer<String> print = s -> System.out.println(s);
print.accept("Hello");

List<String> list = Arrays.asList("A", "B", "C");
list.forEach(s -> System.out.println(s)); // Consumer
```

**4. Supplier\<T\> - Provide T, no input**
```java
@FunctionalInterface
public interface Supplier<T> {
    T get();
}

// Sử dụng
Supplier<Double> randomValue = () -> Math.random();
System.out.println(randomValue.get());

Supplier<String> greeting = () -> "Hello World";
System.out.println(greeting.get());
```

**5. BiFunction\<T, U, R\> - 2 inputs, 1 output**
```java
BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
System.out.println(add.apply(5, 3)); // 8
```

**6. UnaryOperator\<T\> - T → T**
```java
UnaryOperator<Integer> square = x -> x * x;
System.out.println(square.apply(5)); // 25
```

**7. BinaryOperator\<T\> - (T, T) → T**
```java
BinaryOperator<Integer> max = (a, b) -> a > b ? a : b;
System.out.println(max.apply(5, 3)); // 5
```

---

## Câu 3: Stream API là gì? Cách sử dụng?

**Đáp án:**

Stream API cung cấp cách xử lý collections theo functional programming style.

### Đặc điểm:
- **Không lưu trữ dữ liệu** (chỉ xử lý)
- **Lazy evaluation** (chỉ chạy khi gặp terminal operation)
- **Có thể parallel**
- **Immutable** (không thay đổi source)

### Tạo Stream:

```java
// 1. Từ Collection
List<String> list = Arrays.asList("a", "b", "c");
Stream<String> stream1 = list.stream();

// 2. Từ Array
String[] array = {"a", "b", "c"};
Stream<String> stream2 = Arrays.stream(array);

// 3. Stream.of()
Stream<String> stream3 = Stream.of("a", "b", "c");

// 4. Stream.generate()
Stream<Double> randoms = Stream.generate(Math::random).limit(5);

// 5. Stream.iterate()
Stream<Integer> numbers = Stream.iterate(0, n -> n + 2).limit(5); // 0,2,4,6,8

// 6. Parallel Stream
Stream<String> parallelStream = list.parallelStream();
```

### Intermediate Operations (Lazy):

```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

// filter() - Lọc elements
numbers.stream()
       .filter(x -> x % 2 == 0)
       .forEach(System.out::println); // 2,4,6,8,10

// map() - Transform elements
List<String> names = Arrays.asList("java", "python", "c++");
names.stream()
     .map(String::toUpperCase)
     .forEach(System.out::println); // JAVA, PYTHON, C++

// flatMap() - Flatten nested structures
List<List<Integer>> nested = Arrays.asList(
    Arrays.asList(1, 2),
    Arrays.asList(3, 4),
    Arrays.asList(5, 6)
);
nested.stream()
      .flatMap(List::stream)
      .forEach(System.out::println); // 1,2,3,4,5,6

// distinct() - Loại bỏ duplicate
Stream.of(1, 2, 2, 3, 3, 4)
      .distinct()
      .forEach(System.out::println); // 1,2,3,4

// sorted() - Sắp xếp
Stream.of(3, 1, 4, 1, 5)
      .sorted()
      .forEach(System.out::println); // 1,1,3,4,5

Stream.of(3, 1, 4, 1, 5)
      .sorted(Comparator.reverseOrder())
      .forEach(System.out::println); // 5,4,3,1,1

// limit() - Giới hạn số lượng
Stream.of(1, 2, 3, 4, 5)
      .limit(3)
      .forEach(System.out::println); // 1,2,3

// skip() - Bỏ qua n elements đầu
Stream.of(1, 2, 3, 4, 5)
      .skip(2)
      .forEach(System.out::println); // 3,4,5

// peek() - Debug
Stream.of(1, 2, 3)
      .peek(x -> System.out.println("Before: " + x))
      .map(x -> x * 2)
      .peek(x -> System.out.println("After: " + x))
      .collect(Collectors.toList());
```

### Terminal Operations (Eager):

```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

// forEach() - Iterate
numbers.stream().forEach(System.out::println);

// collect() - Thu thập kết quả
List<Integer> evenNumbers = numbers.stream()
    .filter(x -> x % 2 == 0)
    .collect(Collectors.toList());

Set<Integer> set = numbers.stream().collect(Collectors.toSet());

String joined = Stream.of("a", "b", "c")
    .collect(Collectors.joining(", ")); // "a, b, c"

// count() - Đếm
long count = numbers.stream().filter(x -> x > 2).count(); // 3

// min() / max()
Optional<Integer> min = numbers.stream().min(Integer::compareTo);
Optional<Integer> max = numbers.stream().max(Integer::compareTo);

// reduce() - Tổng hợp
int sum = numbers.stream().reduce(0, (a, b) -> a + b);
int sum2 = numbers.stream().reduce(0, Integer::sum);

// anyMatch(), allMatch(), noneMatch()
boolean hasEven = numbers.stream().anyMatch(x -> x % 2 == 0); // true
boolean allPositive = numbers.stream().allMatch(x -> x > 0);  // true
boolean noneNegative = numbers.stream().noneMatch(x -> x < 0); // true

// findFirst(), findAny()
Optional<Integer> first = numbers.stream().findFirst();
Optional<Integer> any = numbers.parallelStream().findAny();

// toArray()
Integer[] array = numbers.stream().toArray(Integer[]::new);
```

### Collectors nâng cao:

```java
List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "Alice");

// groupingBy()
Map<Integer, List<String>> byLength = names.stream()
    .collect(Collectors.groupingBy(String::length));
// {3=[Bob], 5=[Alice, Alice], 7=[Charlie]}

// partitioningBy()
Map<Boolean, List<Integer>> partitioned = numbers.stream()
    .collect(Collectors.partitioningBy(x -> x % 2 == 0));
// {false=[1,3,5], true=[2,4]}

// counting()
Map<String, Long> nameCount = names.stream()
    .collect(Collectors.groupingBy(s -> s, Collectors.counting()));
// {Alice=2, Bob=1, Charlie=1}

// summingInt(), averagingInt()
int total = names.stream()
    .collect(Collectors.summingInt(String::length));

Double avgLength = names.stream()
    .collect(Collectors.averagingInt(String::length));
```

---

## Câu 4: Optional là gì? Cách sử dụng?

**Đáp án:**

Optional là container object để tránh NullPointerException.

### Tạo Optional:

```java
// 1. Optional.of() - Value không null
Optional<String> opt1 = Optional.of("Java");
// Optional.of(null); // NullPointerException

// 2. Optional.ofNullable() - Value có thể null
Optional<String> opt2 = Optional.ofNullable("Java");
Optional<String> opt3 = Optional.ofNullable(null); // OK

// 3. Optional.empty()
Optional<String> opt4 = Optional.empty();
```

### Sử dụng:

```java
String value = "Java";
Optional<String> opt = Optional.ofNullable(value);

// 1. isPresent() / isEmpty()
if (opt.isPresent()) {
    System.out.println(opt.get());
}

if (opt.isEmpty()) { // Java 11+
    System.out.println("Empty");
}

// 2. ifPresent() - Consumer
opt.ifPresent(v -> System.out.println(v));
opt.ifPresent(System.out::println);

// 3. orElse() - Default value
String result1 = opt.orElse("Default");

// 4. orElseGet() - Supplier (lazy)
String result2 = opt.orElseGet(() -> "Default");

// 5. orElseThrow() - Throw exception
String result3 = opt.orElseThrow(() -> new RuntimeException("Not found"));

// 6. map() - Transform
Optional<Integer> length = opt.map(String::length);

// 7. flatMap() - Avoid nested Optional
Optional<Optional<String>> nested = opt.map(s -> Optional.of(s.toUpperCase()));
Optional<String> flat = opt.flatMap(s -> Optional.of(s.toUpperCase()));

// 8. filter() - Predicate
Optional<String> filtered = opt.filter(s -> s.startsWith("J"));
```

### Ví dụ thực tế:

```java
// Bad: Cách cũ
public String getUserName(User user) {
    if (user != null) {
        if (user.getAddress() != null) {
            if (user.getAddress().getCity() != null) {
                return user.getAddress().getCity();
            }
        }
    }
    return "Unknown";
}

// Good: Dùng Optional
public Optional<String> getUserCity(User user) {
    return Optional.ofNullable(user)
                   .map(User::getAddress)
                   .map(Address::getCity);
}

// Usage
String city = getUserCity(user).orElse("Unknown");
```

**Anti-patterns:**
```java
// DON'T
Optional<String> opt = Optional.ofNullable(value);
if (opt.isPresent()) {
    String val = opt.get(); // Giống null check, không có lợi
}

// DO
String val = Optional.ofNullable(value).orElse("Default");
```

---

## Câu 5: Method Reference là gì?

**Đáp án:**

Method Reference là cách viết ngắn gọn hơn lambda khi lambda chỉ gọi 1 method.

### Syntax: `ClassName::methodName`

### Các loại:

**1. Static Method Reference**
```java
// Lambda
Function<String, Integer> f1 = s -> Integer.parseInt(s);

// Method Reference
Function<String, Integer> f2 = Integer::parseInt;
```

**2. Instance Method Reference (of particular object)**
```java
String str = "Hello";

// Lambda
Supplier<Integer> s1 = () -> str.length();

// Method Reference
Supplier<Integer> s2 = str::length;
```

**3. Instance Method Reference (of arbitrary object)**
```java
List<String> list = Arrays.asList("c", "a", "b");

// Lambda
list.sort((s1, s2) -> s1.compareTo(s2));

// Method Reference
list.sort(String::compareTo);
```

**4. Constructor Reference**
```java
// Lambda
Supplier<List<String>> s1 = () -> new ArrayList<>();

// Constructor Reference
Supplier<List<String>> s2 = ArrayList::new;

// Với parameter
Function<Integer, List<String>> f1 = size -> new ArrayList<>(size);
Function<Integer, List<String>> f2 = ArrayList::new;
```

### Ví dụ:

```java
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

// forEach với method reference
names.forEach(System.out::println);

// map với method reference
names.stream()
     .map(String::toUpperCase)
     .forEach(System.out::println);

// sorted với method reference
names.stream()
     .sorted(String::compareToIgnoreCase)
     .forEach(System.out::println);

// Custom class
class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

// Constructor reference
List<String> nameList = Arrays.asList("Alice", "Bob");
List<Person> persons = nameList.stream()
    .map(Person::new)
    .collect(Collectors.toList());

// Method reference
persons.stream()
       .map(Person::getName)
       .forEach(System.out::println);
```

---

## Câu 6: Default Methods và Static Methods trong Interface?

**Đáp án:**

Java 8 cho phép interface có default methods và static methods.

### Default Methods:

```java
public interface Vehicle {
    // Abstract method
    void start();

    // Default method
    default void stop() {
        System.out.println("Vehicle stopped");
    }

    default void honk() {
        System.out.println("Beep beep!");
    }
}

// Implementation
class Car implements Vehicle {
    @Override
    public void start() {
        System.out.println("Car started");
    }

    // Có thể override default method
    @Override
    public void stop() {
        System.out.println("Car stopped");
    }

    // Hoặc không override, dùng default implementation
}
```

**Tại sao cần Default Methods?**
- Thêm method mới vào interface mà không break existing implementations
- Ví dụ: `List.sort()`, `Collection.stream()`

**Diamond Problem:**
```java
interface A {
    default void print() {
        System.out.println("A");
    }
}

interface B {
    default void print() {
        System.out.println("B");
    }
}

// Conflict!
class C implements A, B {
    // Phải override để resolve conflict
    @Override
    public void print() {
        A.super.print(); // Gọi default method của A
        // hoặc
        B.super.print(); // Gọi default method của B
    }
}
```

### Static Methods:

```java
public interface MathUtils {
    // Static method
    static int add(int a, int b) {
        return a + b;
    }

    static int multiply(int a, int b) {
        return a * b;
    }
}

// Gọi: MathUtils.add(5, 3)
// KHÔNG thể override
```

---

## Câu 7: Date/Time API (java.time) mới trong Java 8?

**Đáp án:**

Java 8 giới thiệu `java.time` package thay thế `java.util.Date` và `Calendar`.

### Lý do:
- Date/Calendar: Mutable, not thread-safe, API khó dùng
- java.time: Immutable, thread-safe, API rõ ràng

### Các class chính:

**1. LocalDate - Ngày (không có time)**
```java
LocalDate today = LocalDate.now();
LocalDate birthday = LocalDate.of(1990, Month.JANUARY, 15);
LocalDate parsed = LocalDate.parse("2024-01-15");

// Operations
LocalDate tomorrow = today.plusDays(1);
LocalDate nextWeek = today.plusWeeks(1);
LocalDate nextMonth = today.plusMonths(1);
LocalDate nextYear = today.plusYears(1);

// Get
int year = today.getYear();
Month month = today.getMonth();
int day = today.getDayOfMonth();
DayOfWeek dayOfWeek = today.getDayOfWeek();
```

**2. LocalTime - Giờ (không có date)**
```java
LocalTime now = LocalTime.now();
LocalTime time = LocalTime.of(14, 30, 0);
LocalTime parsed = LocalTime.parse("14:30:00");

// Operations
LocalTime later = now.plusHours(2);
LocalTime earlier = now.minusMinutes(30);

// Get
int hour = now.getHour();
int minute = now.getMinute();
int second = now.getSecond();
```

**3. LocalDateTime - Ngày + Giờ**
```java
LocalDateTime now = LocalDateTime.now();
LocalDateTime dt = LocalDateTime.of(2024, 1, 15, 14, 30);

LocalDateTime fromDate = LocalDate.now().atTime(14, 30);
LocalDateTime fromTime = LocalTime.now().atDate(LocalDate.now());
```

**4. ZonedDateTime - Datetime với timezone**
```java
ZonedDateTime nowInTokyo = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));
ZonedDateTime nowInNY = ZonedDateTime.now(ZoneId.of("America/New_York"));
```

**5. Period - Khoảng thời gian (ngày)**
```java
Period period = Period.between(
    LocalDate.of(2020, 1, 1),
    LocalDate.of(2024, 1, 1)
);
System.out.println(period.getYears()); // 4
```

**6. Duration - Khoảng thời gian (giờ/phút/giây)**
```java
Duration duration = Duration.between(
    LocalTime.of(10, 0),
    LocalTime.of(14, 30)
);
System.out.println(duration.toHours()); // 4
```

**7. DateTimeFormatter - Format/Parse**
```java
LocalDate date = LocalDate.now();

// Format
String formatted = date.format(DateTimeFormatter.ISO_DATE);
String custom = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

// Parse
LocalDate parsed = LocalDate.parse("15/01/2024",
    DateTimeFormatter.ofPattern("dd/MM/yyyy"));
```

### So sánh với Date cũ:

```java
// Old way (Bad)
Date date = new Date();
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
String formatted = sdf.format(date);
date.setYear(2024); // Mutable, deprecated

// New way (Good)
LocalDate now = LocalDate.now();
String formatted = now.format(DateTimeFormatter.ISO_DATE);
LocalDate newDate = now.withYear(2024); // Immutable, returns new object
```

---

## Câu 8: Các tính năng khác của Java 8+?

**Đáp án:**

### Java 8:

**1. Nashorn JavaScript Engine**
```java
ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
engine.eval("print('Hello from JavaScript')");
```

**2. Base64 Encoding/Decoding**
```java
String encoded = Base64.getEncoder().encodeToString("Hello".getBytes());
String decoded = new String(Base64.getDecoder().decode(encoded));
```

### Java 9:

**1. Module System (Jigsaw)**
```java
module com.example.myapp {
    requires java.sql;
    exports com.example.myapp.api;
}
```

**2. Collection Factory Methods**
```java
List<String> list = List.of("a", "b", "c");
Set<String> set = Set.of("a", "b", "c");
Map<String, Integer> map = Map.of("a", 1, "b", 2);
```

**3. Stream.takeWhile() / dropWhile()**
```java
Stream.of(1, 2, 3, 4, 5)
      .takeWhile(x -> x < 4)
      .forEach(System.out::println); // 1, 2, 3
```

### Java 10:

**1. var (Local Variable Type Inference)**
```java
var list = new ArrayList<String>();
var map = new HashMap<String, Integer>();
```

### Java 11:

**1. String methods**
```java
" ".isBlank();              // true
"Java".repeat(3);           // JavaJavaJava
"A\nB\nC".lines().count();  // 3
```

**2. Files methods**
```java
String content = Files.readString(Path.of("file.txt"));
Files.writeString(Path.of("file.txt"), "content");
```

### Java 14+:

**1. Switch Expressions**
```java
int numLetters = switch (day) {
    case MONDAY, FRIDAY, SUNDAY -> 6;
    case TUESDAY -> 7;
    default -> 0;
};
```

**2. Records (Java 14 preview, 16 stable)**
```java
record Person(String name, int age) {}

Person p = new Person("Alice", 25);
System.out.println(p.name()); // Auto-generated getter
```

**3. Text Blocks (Java 15)**
```java
String json = """
    {
        "name": "Alice",
        "age": 25
    }
    """;
```
