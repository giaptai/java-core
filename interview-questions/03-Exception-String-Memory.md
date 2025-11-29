# Câu hỏi phỏng vấn về Exception, String & Memory Management

## PHẦN 1: EXCEPTION HANDLING

### Câu 1: Exception trong Java là gì? Phân loại Exception?

**Đáp án:**

Exception là sự kiện bất thường xảy ra trong quá trình thực thi chương trình, làm gián đoạn luồng bình thường.

### Hierarchy:

```
Throwable
├── Error (Không nên catch)
│   ├── OutOfMemoryError
│   ├── StackOverflowError
│   └── VirtualMachineError
└── Exception
    ├── RuntimeException (Unchecked)
    │   ├── NullPointerException
    │   ├── ArrayIndexOutOfBoundsException
    │   ├── ArithmeticException
    │   └── ClassCastException
    └── IOException (Checked)
        ├── FileNotFoundException
        └── SQLException
```

### Phân loại:

**1. Checked Exception (Compile-time Exception)**
- Compiler bắt buộc phải xử lý
- Phải dùng try-catch hoặc throws
- Ví dụ: IOException, SQLException, ClassNotFoundException

**2. Unchecked Exception (Runtime Exception)**
- Không bắt buộc xử lý
- Xảy ra do lỗi lập trình
- Ví dụ: NullPointerException, ArrayIndexOutOfBoundsException

**3. Error**
- Lỗi nghiêm trọng, không nên catch
- JVM không thể recover
- Ví dụ: OutOfMemoryError, StackOverflowError

---

### Câu 2: Try-catch-finally hoạt động như thế nào?

**Đáp án:**

```java
try {
    // Code có thể gây exception
    int result = 10 / 0;
} catch (ArithmeticException e) {
    // Xử lý exception cụ thể
    System.out.println("Không thể chia cho 0");
} catch (Exception e) {
    // Xử lý exception chung (phải để sau)
    System.out.println("Lỗi khác: " + e.getMessage());
} finally {
    // Luôn chạy, dù có exception hay không
    // Thường dùng để đóng resources
    System.out.println("Finally block");
}
```

### Đặc điểm:

**Try block:**
- Chứa code có thể gây exception
- Bắt buộc phải có catch hoặc finally

**Catch block:**
- Xử lý exception cụ thể
- Có thể có nhiều catch blocks
- Catch cụ thể phải đặt trước catch chung
- Multi-catch (Java 7+): `catch (IOException | SQLException e)`

**Finally block:**
- Luôn được thực thi (trừ System.exit())
- Dùng để cleanup resources
- Chạy ngay cả khi có return trong try/catch

```java
public int test() {
    try {
        return 1;
    } catch (Exception e) {
        return 2;
    } finally {
        // Finally vẫn chạy trước khi return
        System.out.println("Finally");
        // Nếu finally có return → override return của try/catch
    }
}
```

**Try-with-resources (Java 7+):**
```java
// Auto-close resources
try (FileReader fr = new FileReader("file.txt");
     BufferedReader br = new BufferedReader(fr)) {
    // Code
} catch (IOException e) {
    // Handle
}
// Không cần finally, tự động close
```

---

### Câu 3: Throw và Throws khác nhau như thế nào?

**Đáp án:**

| Tiêu chí | throw | throws |
|----------|-------|--------|
| Vị trí | Trong method body | Method signature |
| Mục đích | Ném exception | Khai báo exception có thể xảy ra |
| Số lượng | 1 exception | Nhiều exceptions |
| Kiểu | throw new Exception() | throws Exception1, Exception2 |

### throw
```java
public void validateAge(int age) {
    if (age < 18) {
        // Ném exception
        throw new IllegalArgumentException("Tuổi phải >= 18");
    }
}
```

### throws
```java
// Khai báo method có thể ném exception
public void readFile(String path) throws IOException, FileNotFoundException {
    FileReader file = new FileReader(path);
    // Code có thể gây IOException
}

// Caller phải xử lý
public void caller() {
    try {
        readFile("file.txt");
    } catch (IOException e) {
        // Handle
    }
}
```

---

### Câu 4: Custom Exception tạo như thế nào?

**Đáp án:**

```java
// Checked Exception - Kế thừa Exception
public class InsufficientBalanceException extends Exception {
    private double amount;

    public InsufficientBalanceException(String message) {
        super(message);
    }

    public InsufficientBalanceException(String message, double amount) {
        super(message);
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }
}

// Unchecked Exception - Kế thừa RuntimeException
public class InvalidAccountException extends RuntimeException {
    public InvalidAccountException(String message) {
        super(message);
    }
}

// Sử dụng
public class BankAccount {
    private double balance;

    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount > balance) {
            throw new InsufficientBalanceException(
                "Số dư không đủ", amount - balance
            );
        }
        balance -= amount;
    }
}

// Caller
try {
    account.withdraw(1000);
} catch (InsufficientBalanceException e) {
    System.out.println(e.getMessage());
    System.out.println("Thiếu: " + e.getAmount());
}
```

**Best Practices:**
- Checked exception: Cho lỗi có thể recover
- Unchecked exception: Cho lỗi lập trình
- Tên kết thúc bằng "Exception"
- Cung cấp constructors với message và cause

---

## PHẦN 2: STRING

### Câu 5: String, StringBuilder, StringBuffer khác nhau như thế nào?

**Đáp án:**

| Tiêu chí | String | StringBuilder | StringBuffer |
|----------|--------|---------------|--------------|
| Mutability | Immutable | Mutable | Mutable |
| Thread-safe | Có | Không | Có (synchronized) |
| Performance | Chậm (tạo object mới) | Nhanh nhất | Chậm hơn StringBuilder |
| Memory | Tốn nhiều | Tiết kiệm | Tiết kiệm |
| Khi nào dùng | Ít thay đổi | Single-thread, thay đổi nhiều | Multi-thread |

### String - Immutable
```java
String s1 = "Java";
s1 = s1 + " Programming"; // Tạo object mới, không sửa object cũ
// s1 cũ vẫn tồn tại trong String Pool

// Tại sao chậm?
String result = "";
for (int i = 0; i < 10000; i++) {
    result += i; // Tạo 10000 objects mới → Rất chậm
}
```

### StringBuilder - Mutable, Not Thread-safe
```java
StringBuilder sb = new StringBuilder("Java");
sb.append(" Programming"); // Sửa trực tiếp, không tạo object mới
sb.insert(0, "Learn ");
sb.delete(0, 6);
sb.reverse();
String result = sb.toString();

// Hiệu quả hơn
StringBuilder result = new StringBuilder();
for (int i = 0; i < 10000; i++) {
    result.append(i); // Nhanh hơn String rất nhiều
}
```

### StringBuffer - Mutable, Thread-safe
```java
StringBuffer sb = new StringBuffer("Java");
sb.append(" Programming"); // Synchronized methods → Chậm hơn StringBuilder

// Dùng khi multi-thread
public synchronized String buildString() {
    StringBuffer buffer = new StringBuffer();
    // Multiple threads có thể gọi method này
    return buffer.toString();
}
```

**Kết luận:**
- String: Cho giá trị không đổi
- StringBuilder: Single-thread, concatenation nhiều
- StringBuffer: Multi-thread (hiếm dùng, dùng StringBuilder + synchronization thường tốt hơn)

---

### Câu 6: String Pool là gì?

**Đáp án:**

String Pool (String Constant Pool) là vùng nhớ đặc biệt trong Heap để lưu trữ các String literals.

### Cách hoạt động:

```java
// String literal → Lưu trong String Pool
String s1 = "Java";
String s2 = "Java";
System.out.println(s1 == s2); // true - Cùng reference trong pool

// new String() → Tạo object mới trong Heap
String s3 = new String("Java");
String s4 = new String("Java");
System.out.println(s3 == s4); // false - Khác object

// So sánh với s1
System.out.println(s1 == s3); // false - s1 trong pool, s3 trong heap
System.out.println(s1.equals(s3)); // true - Cùng content

// intern() → Đưa vào pool
String s5 = s3.intern();
System.out.println(s1 == s5); // true - s5 trỏ đến s1 trong pool
```

### Ưu điểm:
- Tiết kiệm memory (reuse strings)
- Performance: So sánh == nhanh hơn equals()

### Vị trí:
- Java 6: PermGen (cố định, có thể OutOfMemory)
- Java 7+: Heap (dynamic, được GC quản lý)

```java
// Ví dụ String Pool
String s1 = "Hello";           // Pool
String s2 = "Hello";           // Reuse từ pool
String s3 = new String("Hello"); // Heap
String s4 = s3.intern();       // Trả về reference từ pool

System.out.println(s1 == s2);  // true
System.out.println(s1 == s3);  // false
System.out.println(s1 == s4);  // true
```

---

### Câu 7: Tại sao String là Immutable? Lợi ích?

**Đáp án:**

### Tại sao Immutable?

String được thiết kế là immutable (không thể thay đổi sau khi tạo) vì:

**1. String Pool Optimization**
- Nếu mutable → thay đổi 1 String sẽ ảnh hưởng tất cả references
```java
String s1 = "Java";
String s2 = "Java"; // Cùng object trong pool
// Nếu String mutable:
s1.setValue("Python"); // s2 cũng thành "Python" → Bug
```

**2. Security**
- String dùng cho username, password, connection URLs
- Immutable → Không bị thay đổi ngầm
```java
public void login(String username, String password) {
    // Nếu String mutable, code khác có thể thay đổi username/password
}
```

**3. Thread-safe**
- Immutable objects tự động thread-safe
- Không cần synchronization

**4. Hashcode Caching**
- hashCode() tính 1 lần, cache lại
- HashMap, HashSet performance tốt hơn

### Lợi ích:

```java
public final class String {
    private final char[] value; // Final → Không thể thay đổi
    private int hash; // Cached hashcode

    public int hashCode() {
        int h = hash;
        if (h == 0 && value.length > 0) {
            // Tính hashcode 1 lần duy nhất
            hash = h = calculateHash();
        }
        return h;
    }
}
```

**Nhược điểm:**
- Tạo nhiều objects khi concatenation
- Giải pháp: Dùng StringBuilder

---

## PHẦN 3: MEMORY MANAGEMENT

### Câu 8: JVM Memory Structure gồm những phần nào?

**Đáp án:**

```
JVM Memory
├── Heap (Shared)
│   ├── Young Generation
│   │   ├── Eden Space
│   │   ├── Survivor 0 (S0)
│   │   └── Survivor 1 (S1)
│   └── Old Generation (Tenured)
├── Stack (Per thread)
│   ├── Local variables
│   ├── Method calls
│   └── References
├── Method Area / Metaspace (Shared)
│   ├── Class metadata
│   ├── Static variables
│   └── Constant Pool
└── PC Register & Native Method Stack
```

### Chi tiết:

**1. Heap**
- Lưu objects và arrays
- Được GC quản lý
- Chia sẻ giữa các threads
- OutOfMemoryError nếu hết

**2. Stack**
- Mỗi thread có 1 stack riêng
- Lưu local variables, method calls
- LIFO (Last In First Out)
- StackOverflowError nếu quá sâu (vd: vô hạn recursion)

```java
public void method1() {
    int x = 10;        // x trong stack
    String s = "Java"; // Reference trong stack, object "Java" trong heap
    method2();         // method2 frame push lên stack
}
```

**3. Method Area / Metaspace**
- Java 7: PermGen (fixed size)
- Java 8+: Metaspace (native memory)
- Lưu class metadata, static variables

---

### Câu 9: Garbage Collection hoạt động như thế nào?

**Đáp án:**

GC tự động thu hồi memory của objects không còn được reference.

### Khi nào object được GC?

```java
// 1. Null reference
String s1 = new String("Java");
s1 = null; // Object "Java" có thể bị GC

// 2. Reassign reference
String s2 = new String("Hello");
s2 = new String("World"); // "Hello" có thể bị GC

// 3. Object ra khỏi scope
public void method() {
    String s3 = new String("Temp");
} // s3 ra khỏi scope → có thể bị GC

// 4. Island of isolation
class Node {
    Node next;
}
Node n1 = new Node();
Node n2 = new Node();
n1.next = n2;
n2.next = n1;
n1 = null;
n2 = null;
// n1, n2 reference nhau nhưng không ai reference từ ngoài → GC
```

### GC Process:

**1. Young Generation GC (Minor GC)**
- Objects mới tạo vào Eden
- Eden đầy → Minor GC
- Objects còn sống → S0 hoặc S1
- Nhanh, thường xuyên

**2. Old Generation GC (Major GC)**
- Objects sống lâu → Old Gen
- Old Gen đầy → Major GC
- Chậm hơn Minor GC

**3. Full GC**
- GC toàn bộ Heap
- Stop-the-world event → Pause application

### GC Algorithms:

```java
// 1. Serial GC (single-thread)
-XX:+UseSerialGC

// 2. Parallel GC (multi-thread)
-XX:+UseParallelGC

// 3. CMS (Concurrent Mark Sweep) - Low pause
-XX:+UseConcMarkSweepGC

// 4. G1 GC (Garbage First) - Default Java 9+
-XX:+UseG1GC

// 5. ZGC (Java 11+) - Ultra low latency
-XX:+UseZGC
```

### Gọi GC:
```java
System.gc();       // Gợi ý JVM chạy GC (không đảm bảo)
Runtime.getRuntime().gc();

// finalize() method (deprecated)
@Override
protected void finalize() throws Throwable {
    // Cleanup trước khi GC (không nên dùng)
}
```

---

### Câu 10: Memory Leak trong Java là gì? Cách tránh?

**Đáp án:**

Memory Leak xảy ra khi objects không còn dùng nhưng vẫn bị reference, không thể GC.

### Nguyên nhân thường gặp:

**1. Static Fields**
```java
public class MemoryLeak {
    private static List<Object> list = new ArrayList<>();

    public void add(Object obj) {
        list.add(obj); // list tồn tại suốt đời app → leak
    }
}
```

**2. Unclosed Resources**
```java
public void readFile() {
    FileInputStream fis = new FileInputStream("file.txt");
    // Không close → leak
}

// Fix: Try-with-resources
try (FileInputStream fis = new FileInputStream("file.txt")) {
    // Auto close
}
```

**3. Inner Classes**
```java
public class Outer {
    private int[] data = new int[1000000];

    public Inner createInner() {
        return new Inner(); // Inner giữ reference đến Outer
    }

    class Inner {
        // Implicit reference to Outer
    }
}

// Fix: Dùng static inner class
static class Inner {
    // Không reference Outer
}
```

**4. HashMap với key không override equals/hashCode**
```java
class Key {
    private int id;
    // Không override equals/hashCode
}

Map<Key, String> map = new HashMap<>();
Key key = new Key(1);
map.put(key, "Value");
key = null; // map vẫn giữ reference → leak
```

**5. ThreadLocal không cleanup**
```java
ThreadLocal<List<Object>> threadLocal = new ThreadLocal<>();
threadLocal.set(new ArrayList<>());
// Không remove() → leak khi thread pool reuse thread

// Fix:
try {
    threadLocal.set(new ArrayList<>());
    // Use
} finally {
    threadLocal.remove(); // Cleanup
}
```

### Cách phát hiện:
- Heap dump analysis
- Memory profilers (VisualVM, JProfiler)
- Monitor heap usage

### Cách tránh:
- Luôn close resources
- Dùng try-with-resources
- Tránh static collections
- Remove listeners khi không dùng
- Dùng WeakHashMap cho caches
