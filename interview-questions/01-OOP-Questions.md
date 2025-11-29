# Câu hỏi phỏng vấn về OOP (Lập trình Hướng Đối Tượng)

## Câu 1: 4 tính chất của OOP là gì? Giải thích từng tính chất.

**Đáp án:**

1. **Encapsulation (Tính đóng gói)**
   - Ẩn giấu dữ liệu bên trong class, chỉ cho phép truy cập qua các phương thức public
   - Sử dụng access modifier (private, protected, public)
   - Ví dụ: Các thuộc tính private + getter/setter

2. **Inheritance (Tính kế thừa)**
   - Class con kế thừa thuộc tính và phương thức từ class cha
   - Sử dụng từ khóa `extends`
   - Tái sử dụng code, tạo mối quan hệ IS-A

3. **Polymorphism (Tính đa hình)**
   - Một đối tượng có thể có nhiều hình thái khác nhau
   - **Compile-time**: Method Overloading (cùng tên khác tham số)
   - **Runtime**: Method Overriding (ghi đè phương thức class cha)

4. **Abstraction (Tính trừu tượng)**
   - Ẩn giấu chi tiết cài đặt, chỉ hiển thị chức năng cần thiết
   - Sử dụng abstract class hoặc interface
   - Tập trung vào "cái gì" thay vì "như thế nào"

---

## Câu 2: Phân biệt Abstract Class và Interface?

**Đáp án:**

| Tiêu chí | Abstract Class | Interface |
|----------|---------------|-----------|
| Kế thừa | Chỉ kế thừa 1 class | Implement nhiều interface |
| Constructor | Có constructor | Không có constructor |
| Phương thức | Có thể có cả abstract và concrete methods | Tất cả methods là abstract (trước Java 8) |
| Biến | Có thể có biến instance | Chỉ có constants (public static final) |
| Access Modifier | Có thể dùng mọi loại | Methods mặc định là public |
| Từ khóa | `extends` | `implements` |
| Mục đích | Dùng cho các class có mối quan hệ chặt chẽ | Dùng để định nghĩa hành vi chung |

**Kể từ Java 8:**
- Interface có thể có default methods và static methods
- Java 9: Interface có thể có private methods

---

## Câu 3: Method Overloading và Method Overriding khác nhau như thế nào?

**Đáp án:**

### Method Overloading (Nạp chồng phương thức)
- Cùng tên phương thức, khác tham số (số lượng hoặc kiểu dữ liệu)
- Xảy ra trong cùng 1 class
- Compile-time polymorphism
- Return type có thể khác nhau
```java
public int add(int a, int b) { return a + b; }
public double add(double a, double b) { return a + b; }
public int add(int a, int b, int c) { return a + b + c; }
```

### Method Overriding (Ghi đè phương thức)
- Cùng tên, cùng tham số với phương thức ở class cha
- Xảy ra giữa class cha và class con
- Runtime polymorphism
- Return type phải giống hoặc là subtype (covariant return type)
- Access modifier không được hạn chế hơn class cha
```java
// Class cha
public void display() { System.out.println("Parent"); }

// Class con
@Override
public void display() { System.out.println("Child"); }
```

---

## Câu 4: Từ khóa `this` và `super` dùng để làm gì?

**Đáp án:**

### `this`
- Tham chiếu đến đối tượng hiện tại
- Phân biệt biến instance và tham số có cùng tên
- Gọi constructor khác trong cùng class
```java
public class Student {
    private String name;

    public Student(String name) {
        this.name = name; // Phân biệt biến instance và parameter
    }

    public Student() {
        this("Unknown"); // Gọi constructor khác
    }
}
```

### `super`
- Tham chiếu đến class cha
- Gọi constructor của class cha
- Truy cập phương thức/thuộc tính của class cha bị override
```java
public class Child extends Parent {
    public Child() {
        super(); // Gọi constructor class cha
    }

    @Override
    public void display() {
        super.display(); // Gọi method của class cha
        System.out.println("Child");
    }
}
```

---

## Câu 5: Constructor là gì? Các loại constructor trong Java?

**Đáp án:**

**Constructor** là phương thức đặc biệt được gọi khi tạo object, dùng để khởi tạo giá trị cho object.

**Đặc điểm:**
- Cùng tên với class
- Không có return type (kể cả void)
- Tự động được gọi khi tạo object bằng `new`

**Các loại:**

1. **Default Constructor (No-arg constructor)**
```java
public class Student {
    public Student() {
        // Constructor không tham số
    }
}
```

2. **Parameterized Constructor**
```java
public class Student {
    private String name;

    public Student(String name) {
        this.name = name;
    }
}
```

3. **Copy Constructor**
```java
public class Student {
    private String name;

    public Student(Student other) {
        this.name = other.name;
    }
}
```

**Lưu ý:**
- Nếu không định nghĩa constructor nào, Java tự động tạo default constructor
- Nếu đã có parameterized constructor, Java không tạo default constructor

---

## Câu 6: Static trong Java là gì? Khi nào dùng?

**Đáp án:**

`static` là từ khóa để khai báo thành phần thuộc về class thay vì object.

### Static Variable (Class Variable)
- Được chia sẻ bởi tất cả các object của class
- Chỉ có 1 bản copy trong memory
```java
public class Counter {
    static int count = 0; // Dùng chung cho tất cả objects

    public Counter() {
        count++;
    }
}
```

### Static Method
- Có thể gọi mà không cần tạo object
- Chỉ truy cập được static members
- Không dùng được `this` và `super`
```java
public class MathUtil {
    public static int add(int a, int b) {
        return a + b;
    }
}
// Gọi: MathUtil.add(5, 3);
```

### Static Block
- Khởi tạo static variables
- Chạy 1 lần khi class được load vào memory
```java
public class Database {
    static Connection conn;

    static {
        // Khởi tạo connection
        conn = DriverManager.getConnection("...");
    }
}
```

**Khi nào dùng:**
- Utility methods (Math.max(), Arrays.sort())
- Constants (Math.PI)
- Counter, shared data
- Factory methods

---

## Câu 7: Final keyword có ý nghĩa gì?

**Đáp án:**

### Final Variable
- Biến không thể thay đổi giá trị (constant)
- Phải khởi tạo khi khai báo hoặc trong constructor
```java
final int MAX_SIZE = 100;
final String NAME;

public MyClass() {
    NAME = "Java"; // OK trong constructor
}
```

### Final Method
- Phương thức không thể bị override bởi class con
```java
public final void display() {
    // Không thể override
}
```

### Final Class
- Class không thể bị kế thừa
- Ví dụ: String, Integer, Math
```java
public final class ImmutableClass {
    // Không class nào extend được
}
```

**Tại sao String là final class?**
- Bảo mật: tránh bị thay đổi giá trị
- String Pool optimization
- Thread-safe

---

## Câu 8: Phân biệt Composition và Inheritance?

**Đáp án:**

### Inheritance (IS-A relationship)
- Class con kế thừa class cha
- Tái sử dụng code nhưng tạo tight coupling
```java
public class Dog extends Animal {
    // Dog IS-A Animal
}
```

### Composition (HAS-A relationship)
- Class chứa object của class khác
- Linh hoạt hơn, loose coupling
```java
public class Car {
    private Engine engine; // Car HAS-A Engine

    public Car() {
        this.engine = new Engine();
    }
}
```

**Nguyên tắc:** "Favor Composition over Inheritance"
- Composition linh hoạt hơn, dễ test hơn
- Tránh hierarchy phức tạp
- Dễ thay đổi behavior tại runtime

---

## Câu 9: Access Modifier trong Java gồm những loại nào?

**Đáp án:**

| Modifier | Cùng Class | Cùng Package | Subclass | Khác Package |
|----------|-----------|--------------|----------|--------------|
| **private** | ✓ | ✗ | ✗ | ✗ |
| **default** | ✓ | ✓ | ✗ | ✗ |
| **protected** | ✓ | ✓ | ✓ | ✗ |
| **public** | ✓ | ✓ | ✓ | ✓ |

**Chi tiết:**
- **private**: Chỉ trong class đó
- **default** (không khai báo gì): Trong cùng package
- **protected**: Trong package và các subclass
- **public**: Truy cập từ mọi nơi

**Best Practice:**
- Luôn dùng private cho variables
- Cung cấp public getter/setter nếu cần

---

## Câu 10: Equals() và == khác nhau như thế nào?

**Đáp án:**

### == (Reference Comparison)
- So sánh địa chỉ bộ nhớ (reference)
- Với primitive types: so sánh giá trị
- Với objects: kiểm tra 2 biến có trỏ đến cùng object không

### equals() (Content Comparison)
- So sánh nội dung của object
- Có thể override để định nghĩa cách so sánh
- String đã override equals() để so sánh nội dung

```java
String s1 = new String("Java");
String s2 = new String("Java");
String s3 = s1;

s1 == s2       // false (khác địa chỉ)
s1.equals(s2)  // true (cùng nội dung)
s1 == s3       // true (cùng địa chỉ)

Integer a = 127;
Integer b = 127;
a == b         // true (Integer cache từ -128 đến 127)

Integer x = 128;
Integer y = 128;
x == y         // false (ngoài cache range)
```

**Override equals():**
```java
@Override
public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Student student = (Student) obj;
    return id == student.id;
}

@Override
public int hashCode() {
    return Objects.hash(id); // Phải override cả hashCode
}
```

**Contract:**
- Nếu override equals() thì phải override hashCode()
- Nếu a.equals(b) = true thì a.hashCode() == b.hashCode()
