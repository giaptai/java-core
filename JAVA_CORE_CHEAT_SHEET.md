# 📚 JAVA CORE CHEAT SHEET - INTERN/FRESHER
## Ôn tập nhanh trước phỏng vấn

**Thời gian:** 17/12/2025 - 14:12
**Phỏng vấn:** 18/12/2025 - Sáng
**Điểm trung bình:** 8.6/10 ⭐

---

## 📌 TOPIC 1: hashCode() & equals()

### Kiến thức cốt lõi

**Contract (quy tắc BẮT BUỘC):**
```java
if (a.equals(b) == true)
→ a.hashCode() PHẢI == b.hashCode()
```

**Khi nào phải override?**
- Dùng object làm **key** trong HashMap/HashSet
- Muốn so sánh theo **NỘI DUNG** thay vì **IDENTITY**

### Template code

```java
class Person {
    String name;
    int age;

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person p = (Person) obj;
        return age == p.age && Objects.equals(name, p.name);
    }
}
```

### Câu hỏi phỏng vấn mẫu

**Q: Tại sao phải override CẢ 2?**

**A:** "Vì HashMap dùng hashCode() để tìm bucket nhanh, và equals() để so sánh chính xác trong bucket. Nếu chỉ override 1 trong 2, HashMap sẽ hoạt động sai."

**Q: Nếu override equals() nhưng quên hashCode()?**

**A:** "Vi phạm contract. 2 objects bằng nhau (equals = true) nhưng có hashCode khác nhau → HashMap không tìm được object."

### Ghi nhớ nhanh
```
Default:
- hashCode() dựa vào IDENTITY (object identity)
- equals() dựa vào IDENTITY (== operator)

Override:
- hashCode() dựa vào STATE (fields)
- equals() dựa vào STATE (fields)

→ Luôn override CẢ 2 cùng lúc!
```

---

## 📌 TOPIC 2: String Pool & Immutability

### Kiến thức cốt lõi

**String Pool:**
- Vùng nhớ trong **Heap** (từ Java 7+)
- Lưu String literals **không trùng lặp**
- Mục đích: **Tiết kiệm bộ nhớ**

### So sánh

```java
String s1 = "hello";           // → String Pool
String s2 = "hello";           // → Tái sử dụng từ Pool
String s3 = new String("hello"); // → Heap (ngoài Pool)
String s4 = new String("hello").intern(); // → Pool

s1 == s2  // true  (cùng reference)
s1 == s3  // false (khác reference)
s1 == s4  // true  (intern() trả về reference từ Pool)
```

### intern() method

```java
String s = new String("hello").intern();
```

**Làm gì?**
- Nếu "hello" **ĐÃ CÓ** trong Pool → trả về reference của String đó
- Nếu "hello" **CHƯA CÓ** → thêm vào Pool và trả về reference

### Câu hỏi phỏng vấn mẫu

**Q: Tại sao String immutable?**

**A:**
1. Security (String dùng trong password, file path)
2. Thread-safe (nhiều threads dùng chung 1 String an toàn)
3. Hashcode caching (hashCode tính 1 lần, dùng mãi)
4. String Pool hoạt động được

**Q: `"hello"` vs `new String("hello")`?**

**A:**
- `"hello"` → vào String Pool, tái sử dụng
- `new String("hello")` → tạo object mới trong Heap, không tái sử dụng

### Ghi nhớ nhanh
```
String literal → Pool → Tái sử dụng
new String()   → Heap → Không tái sử dụng
intern()       → Đưa vào Pool (hoặc lấy từ Pool)

== so sánh reference
equals() so sánh nội dung
```

---

## 📌 TOPIC 3: ArrayList vs LinkedList

### Performance Table

| Operation | ArrayList | LinkedList | Winner |
|-----------|-----------|------------|--------|
| `add()` cuối | O(1) amortized | O(1) | LinkedList (ổn định) |
| `add(0)` đầu | O(n) | O(1) | **LinkedList** |
| `get(index)` | O(1) | O(n) | **ArrayList** |
| `remove(0)` | O(n) | O(1) | **LinkedList** |

### Memory

**ArrayList:** ~8 bytes/element
**LinkedList:** ~40 bytes/element (gấp 3-5 lần!)

### Khi nào dùng gì?

**ArrayList (90% trường hợp):**
- Random access (get/set)
- Iterate tuần tự
- Kích thước nhỏ-trung bình
- Tiết kiệm memory

**LinkedList (hiếm khi):**
- Thường xuyên thêm/xóa **ĐẦU**
- Implement Queue/Deque

### Câu hỏi phỏng vấn mẫu

**Q: Tại sao ArrayList.get() nhanh hơn LinkedList?**

**A:** "ArrayList dùng array → truy cập trực tiếp theo index O(1). LinkedList phải duyệt từ đầu/cuối → O(n)."

**Q: Use case thực tế?**

**A:**
- Giỏ hàng: ArrayList (kích thước nhỏ, chủ yếu get)
- Log hệ thống: ArrayList (append-only, cache-friendly)

### Ghi nhớ nhanh
```
ArrayList = Array động
  + Get O(1)
  + Memory hiệu quả
  - Add/remove đầu O(n)

LinkedList = Chuỗi nodes
  + Add/remove đầu O(1)
  - Get O(n)
  - Tốn memory

→ Nghi ngờ thì dùng ArrayList!
```

---

## 📌 TOPIC 4: Exception Handling

### try-catch-finally Execution Order

```
try → (exception?) → catch → finally → return
```

**finally LUÔN LUÔN chạy** (trừ System.exit(), JVM crash)

### Quy tắc quan trọng

**1. finally chạy TRƯỚC return:**
```java
try {
    System.out.println("A");
    return 1;  // Ghi nhớ return = 1
} finally {
    System.out.println("C");  // Chạy trước khi return
}
// Output: A → C → 1
```

**2. return trong finally GHI ĐÈ:**
```java
try {
    return 1;
} finally {
    return 2;  // GHI ĐÈ → trả về 2
}
```

**3. Exception trong finally "nuốt" exception trong try:**
```java
try {
    throw new IOException();
} finally {
    throw new RuntimeException();  // IOException bị mất!
}
```

### Best Practices

❌ **TRÁNH:**
- return trong finally
- throw exception trong finally

✅ **NÊN:**
- Dùng finally cho cleanup (đóng file, connection, lock)
- Dùng try-with-resources (Java 7+) thay vì finally

### try-with-resources

```java
// Cũ:
FileInputStream fis = null;
try {
    fis = new FileInputStream("file.txt");
} finally {
    if (fis != null) fis.close();
}

// Mới (tốt hơn):
try (FileInputStream fis = new FileInputStream("file.txt")) {
    // Use fis
} // Tự động close!
```

### Ghi nhớ nhanh
```
finally:
  ✓ LUÔN chạy
  ✓ Chạy TRƯỚC return
  ✗ KHÔNG return trong finally
  ✗ KHÔNG throw trong finally

try-with-resources > finally (cho resources)
```

---

## 📌 TOPIC 5: Multithreading - synchronized & volatile

### Kiến thức cốt lõi

**synchronized:**
- Đảm bảo chỉ **1 thread** vào method/block tại 1 thời điểm
- Dùng cho: **Compound operations** (count++, check-then-act)

**volatile:**
- Đảm bảo thay đổi của 1 thread được **threads khác thấy ngay**
- Dùng cho: **Simple flags** (boolean running)
- **KHÔNG đủ** cho compound operations

### Tại sao `count++` cần synchronized?

**count++ = 3 bước (KHÔNG atomic):**
```
1. READ  count từ memory
2. ADD   1
3. WRITE count về memory
```

**Race condition:**
```
Thread A: READ(100) → ADD(1)
Thread B: READ(100) → ADD(1)  ← Đọc cùng giá trị!
Thread A: WRITE(101)
Thread B: WRITE(101)  ← Ghi đè! Mất 1 lần increment!
```

### Code mẫu

```java
// ❌ KHÔNG an toàn:
private int count = 0;
public void increment() {
    count++;  // Race condition!
}

// ✓ synchronized:
public synchronized void increment() {
    count++;  // An toàn
}

// ✓ volatile (CHỈ cho simple flag):
private volatile boolean running = true;
public void stop() {
    running = false;  // Threads khác thấy ngay
}
```

### Câu hỏi phỏng vấn mẫu

**Q: Tại sao volatile không đủ cho count++?**

**A:** "Vì count++ gồm 3 bước (read-add-write). volatile chỉ đảm bảo visibility, không đảm bảo atomicity. 2 threads vẫn có thể đọc cùng giá trị và ghi đè lẫn nhau."

### Ghi nhớ nhanh
```
synchronized:
  ✓ Mutual exclusion (1 thread)
  ✓ Atomicity
  → Dùng cho: count++, check-then-act

volatile:
  ✓ Visibility
  ✗ KHÔNG atomicity
  → Dùng cho: boolean flags

count++ cần synchronized (hoặc AtomicInteger)
```

---

## 📌 TOPIC 6: Stream API & Optional

### Intermediate vs Terminal

**Intermediate (lazy, return Stream):**
- `filter()`, `map()`, `sorted()`, `distinct()`, `limit()`

**Terminal (eager, return kết quả):**
- `collect()`, `forEach()`, `count()`, `sum()`
- `findFirst()`, `anyMatch()`, `allMatch()`

### Code mẫu Stream

```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

// Filter + Map + Collect
List<Integer> result = numbers.stream()
    .filter(n -> n % 2 == 0)  // Lọc số chẵn
    .map(n -> n * 2)           // Nhân 2
    .collect(Collectors.toList());
// result = [4, 8]

// Sum
int sum = numbers.stream()
    .filter(n -> n > 2)
    .mapToInt(n -> n)
    .sum();
// sum = 3+4+5 = 12
```

### Optional - 3 cách dùng an toàn

```java
Optional<String> opt = Optional.ofNullable(name);

// 1. Check rồi get
if (opt.isPresent()) {
    String value = opt.get();
}

// 2. Hoặc default value
String value = opt.orElse("default");

// 3. Hoặc action
opt.ifPresent(v -> System.out.println(v));
```

**❌ TRÁNH:**
```java
opt.get();  // Không check → NoSuchElementException!
```

### Ghi nhớ nhanh
```
Stream pipeline:
source.stream()
  .intermediate()  // Lazy
  .terminal()      // Triggers execution

Optional methods:
  isPresent() → boolean
  orElse(T)   → T
  ifPresent(Consumer) → void

Luôn check hoặc dùng orElse/ifPresent
```

---

## 📌 TOPIC 7: Lambda & Functional Interface

### Functional Interface

```java
@FunctionalInterface  // Optional
interface Calculator {
    int calculate(int a, int b);  // 1 abstract method
}
```

**Quy tắc:** Chỉ có **1 abstract method**

### Lambda Syntax

```java
// Ngắn gọn:
(a, b) -> a + b

// Nhiều statements:
(a, b) -> {
    int sum = a + b;
    return sum * 2;
}

// 1 tham số:
n -> n * 2

// Không tham số:
() -> System.out.println("Hello")
```

### Built-in Functional Interfaces

```java
Predicate<T>   → boolean test(T)     → n -> n > 0
Function<T,R>  → R apply(T)          → n -> n * 2
Consumer<T>    → void accept(T)      → s -> System.out.println(s)
Supplier<T>    → T get()             → () -> new Random().nextInt()
```

### Method Reference

```java
// Lambda:
names.forEach(name -> System.out.println(name));

// Method reference:
names.forEach(System.out::println);
```

### Ghi nhớ nhanh
```
Functional Interface = 1 abstract method

Lambda = Cách ngắn gọn implement Functional Interface

Method reference = Ngắn hơn lambda (khi có method sẵn)

Predicate → test điều kiện
Function  → biến đổi
Consumer  → tiêu thụ (không return)
Supplier  → cung cấp (không nhận tham số)
```

---

## 📌 TOPIC 8: Garbage Collection

### Kiến thức cốt lõi

**Object eligible for GC khi:** Không còn reference nào (unreachable)

**3 trường hợp unreachable:**
1. `obj = null`
2. `obj = new Object()` (re-assignment)
3. Local variable ra khỏi scope

### Lưu ý quan trọng

```java
obj = null;
// Object ĐỦ ĐIỀU KIỆN bị GC
// NHƯNG CHƯA bị thu hồi ngay!
// GC sẽ chạy khi JVM quyết định
```

### System.gc()

```java
System.gc();
// Chỉ là REQUEST, không bắt buộc
// JVM có thể bỏ qua
// KHÔNG NÊN dùng trong production
```

### Câu hỏi phỏng vấn mẫu

**Q: Khi nào object bị GC?**

**A:** "Khi object không còn reference nào trỏ đến (unreachable). Ví dụ: gán null, reassignment, hoặc local variable ra khỏi scope. Nhưng object chỉ đủ điều kiện bị GC, chưa bị thu hồi ngay. GC sẽ chạy khi JVM cần memory."

### Ghi nhớ nhanh
```
eligible for GC ≠ thu hồi ngay

Unreachable khi:
  - obj = null
  - obj = new Object()
  - Local variable ra khỏi scope

System.gc():
  ✗ KHÔNG bắt buộc JVM chạy
  ✗ KHÔNG dùng trong production
  ✓ Chỉ dùng testing/debugging
```

---

## 📌 TOPIC 9: File I/O (Đọc/Ghi File)

### 3 cách đọc file phổ biến

#### 1. FileReader + BufferedReader (Đọc text file từng dòng)
```java
// Cách cũ (phải close thủ công)
FileReader fr = null;
BufferedReader br = null;
try {
    fr = new FileReader("input.txt");
    br = new BufferedReader(fr);

    String line;
    while ((line = br.readLine()) != null) {
        System.out.println(line);
    }
} catch (IOException e) {
    e.printStackTrace();
} finally {
    if (br != null) br.close();
    if (fr != null) fr.close();
}

// Cách mới (try-with-resources - TỐT HƠN)
try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
    String line;
    while ((line = br.readLine()) != null) {
        System.out.println(line);
    }
} catch (IOException e) {
    e.printStackTrace();
}
```

#### 2. Files.readAllLines() (Java 7+ - Đơn giản nhất)
```java
try {
    List<String> lines = Files.readAllLines(Paths.get("input.txt"));
    for (String line : lines) {
        System.out.println(line);
    }
} catch (IOException e) {
    e.printStackTrace();
}

// Hoặc ngắn hơn với Stream API
try {
    Files.lines(Paths.get("input.txt"))
         .forEach(System.out::println);
} catch (IOException e) {
    e.printStackTrace();
}
```

#### 3. Scanner (Đọc từng token)
```java
try (Scanner sc = new Scanner(new File("input.txt"))) {
    while (sc.hasNextLine()) {
        String line = sc.nextLine();
        System.out.println(line);
    }
} catch (FileNotFoundException e) {
    e.printStackTrace();
}
```

### 3 cách ghi file phổ biến

#### 1. FileWriter + BufferedWriter
```java
try (BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
    bw.write("Hello World");
    bw.newLine();  // Xuống dòng
    bw.write("Line 2");
} catch (IOException e) {
    e.printStackTrace();
}

// Append mode (ghi tiếp, không ghi đè)
try (BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt", true))) {
    bw.write("Append this line");
} catch (IOException e) {
    e.printStackTrace();
}
```

#### 2. Files.write() (Java 7+ - Đơn giản nhất)
```java
try {
    List<String> lines = Arrays.asList("Line 1", "Line 2", "Line 3");
    Files.write(Paths.get("output.txt"), lines);
} catch (IOException e) {
    e.printStackTrace();
}

// Append mode
try {
    Files.write(
        Paths.get("output.txt"),
        "New line\n".getBytes(),
        StandardOpenOption.APPEND
    );
} catch (IOException e) {
    e.printStackTrace();
}
```

#### 3. PrintWriter (Có print, println methods)
```java
try (PrintWriter pw = new PrintWriter(new FileWriter("output.txt"))) {
    pw.println("Line 1");
    pw.println("Line 2");
    pw.printf("Number: %d\n", 123);
} catch (IOException e) {
    e.printStackTrace();
}
```

### Binary File (FileInputStream/FileOutputStream)

```java
// Copy file (binary)
try (FileInputStream fis = new FileInputStream("source.jpg");
     FileOutputStream fos = new FileOutputStream("dest.jpg")) {

    byte[] buffer = new byte[1024];
    int bytesRead;
    while ((bytesRead = fis.read(buffer)) != -1) {
        fos.write(buffer, 0, bytesRead);
    }
} catch (IOException e) {
    e.printStackTrace();
}
```

### Câu hỏi phỏng vấn mẫu

**Q: Tại sao nên dùng BufferedReader thay vì FileReader?**

**A:** "Vì BufferedReader đọc nhiều ký tự 1 lúc (buffer 8KB) rồi trả về từng dòng, nên nhanh hơn FileReader (đọc từng ký tự). BufferedReader cũng có method `readLine()` tiện lợi."

**Q: FileReader vs Scanner?**

**A:**
- FileReader: Đọc character stream, nhanh hơn
- Scanner: Parse input (nextInt, nextDouble...), chậm hơn nhưng tiện hơn

**Q: Tại sao nên dùng try-with-resources?**

**A:** "Vì tự động gọi close() khi ra khỏi try block, tránh resource leak. Ngắn gọn hơn finally block. Resource phải implement AutoCloseable."

### Ghi nhớ nhanh
```
Đọc file:
  BufferedReader.readLine()    → Đọc từng dòng (nhanh)
  Files.readAllLines()         → Đọc toàn bộ vào List (đơn giản)
  Scanner.nextLine()           → Đọc + parse (tiện)

Ghi file:
  BufferedWriter.write()       → Ghi text (nhanh)
  Files.write()                → Ghi List<String> (đơn giản)
  PrintWriter.println()        → Ghi với print methods (tiện)

Binary file:
  FileInputStream/FileOutputStream → Đọc/ghi byte array

Luôn dùng try-with-resources!
```

---

## 📌 TOPIC 10: JDBC (Java Database Connectivity)

### 7 bước kết nối Database

```java
// 1. Load Driver (Java 6+ tự động, nhưng vẫn nên có)
Class.forName("com.mysql.cj.jdbc.Driver");

// 2. Tạo Connection
String url = "jdbc:mysql://localhost:3306/hotel_db";
String user = "root";
String password = "12345";
Connection conn = DriverManager.getConnection(url, user, password);

// 3. Tạo Statement/PreparedStatement
PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM rooms WHERE id = ?");

// 4. Set Parameters (nếu dùng PreparedStatement)
pstmt.setInt(1, 101);

// 5. Execute query
ResultSet rs = pstmt.executeQuery();

// 6. Process ResultSet
while (rs.next()) {
    int id = rs.getInt("id");
    String name = rs.getString("name");
    System.out.println(id + " - " + name);
}

// 7. Close resources
rs.close();
pstmt.close();
conn.close();
```

### Connection String Examples

```java
// MySQL
"jdbc:mysql://localhost:3306/database_name?useSSL=false"

// PostgreSQL
"jdbc:postgresql://localhost:5432/database_name"

// SQL Server
"jdbc:sqlserver://localhost:1433;databaseName=database_name"

// Oracle
"jdbc:oracle:thin:@localhost:1521:xe"
```

### Statement vs PreparedStatement

| | **Statement** | **PreparedStatement** |
|---|---|---|
| **SQL** | String concatenation | Parameterized (?) |
| **SQL Injection** | ❌ Dễ bị | ✅ An toàn |
| **Performance** | Chậm (compile lại) | Nhanh (pre-compiled) |
| **Khi nào dùng?** | SQL tĩnh, 1 lần | SQL động, nhiều lần |

```java
// ❌ Statement (SQL Injection!)
String username = "admin' OR '1'='1";  // Malicious input
Statement stmt = conn.createStatement();
String sql = "SELECT * FROM users WHERE username = '" + username + "'";
ResultSet rs = stmt.executeQuery(sql);
// SQL thực tế: SELECT * FROM users WHERE username = 'admin' OR '1'='1'
// → Trả về TẤT CẢ users!

// ✅ PreparedStatement (An toàn)
String username = "admin' OR '1'='1";
PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM users WHERE username = ?");
pstmt.setString(1, username);  // Tự động escape
ResultSet rs = pstmt.executeQuery();
// → Tìm username chính xác là "admin' OR '1'='1" (không có SQL Injection)
```

### CRUD Operations

#### CREATE (INSERT)
```java
String sql = "INSERT INTO rooms (room_id, available, booked_by) VALUES (?, ?, ?)";
try (Connection conn = DriverManager.getConnection(url, user, password);
     PreparedStatement pstmt = conn.prepareStatement(sql)) {

    pstmt.setInt(1, 101);
    pstmt.setBoolean(2, true);
    pstmt.setString(3, null);

    int rowsAffected = pstmt.executeUpdate();
    System.out.println("Inserted " + rowsAffected + " row(s)");
} catch (SQLException e) {
    e.printStackTrace();
}
```

#### READ (SELECT)
```java
String sql = "SELECT * FROM rooms WHERE available = ?";
try (Connection conn = DriverManager.getConnection(url, user, password);
     PreparedStatement pstmt = conn.prepareStatement(sql)) {

    pstmt.setBoolean(1, true);
    ResultSet rs = pstmt.executeQuery();

    while (rs.next()) {
        int roomId = rs.getInt("room_id");
        String bookedBy = rs.getString("booked_by");
        System.out.println("Room " + roomId + " - " + bookedBy);
    }
    rs.close();
} catch (SQLException e) {
    e.printStackTrace();
}
```

#### UPDATE
```java
String sql = "UPDATE rooms SET available = ?, booked_by = ? WHERE room_id = ?";
try (Connection conn = DriverManager.getConnection(url, user, password);
     PreparedStatement pstmt = conn.prepareStatement(sql)) {

    pstmt.setBoolean(1, false);
    pstmt.setString(2, "User1");
    pstmt.setInt(3, 101);

    int rowsAffected = pstmt.executeUpdate();
    System.out.println("Updated " + rowsAffected + " row(s)");
} catch (SQLException e) {
    e.printStackTrace();
}
```

#### DELETE
```java
String sql = "DELETE FROM rooms WHERE room_id = ?";
try (Connection conn = DriverManager.getConnection(url, user, password);
     PreparedStatement pstmt = conn.prepareStatement(sql)) {

    pstmt.setInt(1, 101);

    int rowsAffected = pstmt.executeUpdate();
    System.out.println("Deleted " + rowsAffected + " row(s)");
} catch (SQLException e) {
    e.printStackTrace();
}
```

### Transaction Management

```java
Connection conn = null;
try {
    conn = DriverManager.getConnection(url, user, password);

    // 1. Tắt auto-commit
    conn.setAutoCommit(false);

    // 2. Thực hiện nhiều operations
    PreparedStatement pstmt1 = conn.prepareStatement("UPDATE rooms SET available = 0 WHERE room_id = ?");
    pstmt1.setInt(1, 101);
    pstmt1.executeUpdate();

    PreparedStatement pstmt2 = conn.prepareStatement("INSERT INTO bookings (room_id, user) VALUES (?, ?)");
    pstmt2.setInt(1, 101);
    pstmt2.setString(2, "User1");
    pstmt2.executeUpdate();

    // 3. Nếu OK → commit
    conn.commit();
    System.out.println("Transaction committed");

} catch (SQLException e) {
    // 4. Nếu lỗi → rollback
    if (conn != null) {
        try {
            conn.rollback();
            System.out.println("Transaction rolled back");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    e.printStackTrace();
} finally {
    if (conn != null) {
        try {
            conn.setAutoCommit(true);  // Bật lại auto-commit
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```

### executeQuery() vs executeUpdate()

| Method | Return Type | Dùng cho |
|--------|-------------|----------|
| `executeQuery()` | `ResultSet` | SELECT |
| `executeUpdate()` | `int` (rows affected) | INSERT, UPDATE, DELETE |
| `execute()` | `boolean` | Bất kỳ SQL nào (hiếm dùng) |

### ResultSet Methods

```java
ResultSet rs = pstmt.executeQuery();

// Lấy dữ liệu theo type
int id = rs.getInt("column_name");        // hoặc rs.getInt(1)
String name = rs.getString("column_name");
boolean flag = rs.getBoolean("column_name");
Date date = rs.getDate("column_name");

// Check null
String value = rs.getString("column_name");
if (rs.wasNull()) {
    // Value was NULL in database
}
```

### Câu hỏi phỏng vấn mẫu

**Q: Tại sao nên dùng PreparedStatement thay vì Statement?**

**A:**
1. **An toàn hơn**: Tránh SQL Injection
2. **Nhanh hơn**: SQL được pre-compiled, dùng lại nhiều lần
3. **Dễ đọc hơn**: Parameterized query rõ ràng hơn string concatenation

**Q: Transaction là gì? Khi nào dùng?**

**A:** "Transaction là nhóm nhiều operations thành 1 đơn vị. Hoặc TẤT CẢ thành công (commit), hoặc TẤT CẢ rollback. Dùng khi: chuyển tiền (trừ A + cộng B), booking (reserve room + insert booking)."

**Q: executeQuery() và executeUpdate() khác nhau gì?**

**A:**
- `executeQuery()`: Dùng cho SELECT, trả về ResultSet
- `executeUpdate()`: Dùng cho INSERT/UPDATE/DELETE, trả về số dòng bị ảnh hưởng

**Q: Tại sao phải close() Connection?**

**A:** "Vì Connection tốn tài nguyên (socket, memory). Nếu không close, sẽ dẫn đến resource leak, database hết connection pool."

### Best Practices

1. **Luôn dùng try-with-resources** (tự động close)
   ```java
   try (Connection conn = DriverManager.getConnection(url);
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery()) {
       // Use resources
   } // Tự động close theo thứ tự ngược: rs → pstmt → conn
   ```

2. **Không bao giờ concatenate user input vào SQL**
   ```java
   // ❌ NGUY HIỂM
   String sql = "SELECT * FROM users WHERE id = " + userId;

   // ✅ AN TOÀN
   String sql = "SELECT * FROM users WHERE id = ?";
   pstmt.setInt(1, userId);
   ```

3. **Dùng connection pool trong production** (HikariCP, Apache DBCP)
   ```java
   // Thay vì tạo Connection mới mỗi lần
   Connection conn = DriverManager.getConnection(url);

   // Nên dùng pool (lấy từ pool, dùng xong trả lại)
   Connection conn = dataSource.getConnection();
   ```

4. **Handle SQLException cụ thể**
   ```java
   try {
       ...
   } catch (SQLIntegrityConstraintViolationException e) {
       // Duplicate key, foreign key violation
   } catch (SQLTimeoutException e) {
       // Query timeout
   } catch (SQLException e) {
       // Other SQL errors
   }
   ```

### Ghi nhớ nhanh
```
JDBC Workflow:
1. Load Driver (Class.forName)
2. Get Connection (DriverManager.getConnection)
3. Create PreparedStatement
4. Set Parameters (setInt, setString...)
5. Execute (executeQuery/executeUpdate)
6. Process ResultSet (next, getInt, getString...)
7. Close (rs, pstmt, conn)

PreparedStatement > Statement:
  ✓ SQL Injection safe
  ✓ Performance (pre-compiled)
  ✓ Readable

Transaction:
  setAutoCommit(false) → operations → commit/rollback

Luôn dùng try-with-resources!
```

---

## 🎯 CHECKLIST TRƯỚC PHỎNG VẤN

- [ ] Đọc lại CHEAT SHEET 1-2 lần
- [ ] Viết lại code mẫu (hashCode, equals, synchronized)
- [ ] Ôn lại 3 câu hỏi mẫu mỗi topic
- [ ] Ngủ đủ giấc (quan trọng nhất!)

---

## 💪 LỜI KHUYÊN CUỐI

1. **Tự tin:** Bạn đã trả lời đúng trung bình 8.6/10 → Kiến thức tốt!
2. **Nói chậm, rõ ràng:** Đừng vội vàng
3. **Nếu không biết:** Thành thật nói "Tôi chưa rõ phần này"
4. **Cho ví dụ:** Interviewer thích candidate cho ví dụ cụ thể

---

**CHÚC BẠN PHỎNG VẤN THÀNH CÔNG! 🚀**

*Tạo ngày: 17/12/2025 - 14:12*
*Phỏng vấn: 18/12/2025 - Sáng*
