# Java Core - Tài liệu Ôn Tập Phỏng Vấn 📚

Bộ tài liệu ôn tập Java Core cho vị trí **Java Intern**, bao gồm câu hỏi phỏng vấn thường gặp và bài tập thực hành.

---

## 📋 Nội Dung

### 1️⃣ Câu Hỏi Phỏng Vấn (Interview Questions)

Nằm trong thư mục [interview-questions/](interview-questions/)

| File | Chủ đề | Số câu hỏi |
|------|--------|------------|
| [01-OOP-Questions.md](interview-questions/01-OOP-Questions.md) | Lập trình Hướng Đối Tượng | 10 câu |
| [02-Collections-Questions.md](interview-questions/02-Collections-Questions.md) | Collections Framework | 10 câu |
| [03-Exception-String-Memory.md](interview-questions/03-Exception-String-Memory.md) | Exception, String & Memory | 10 câu |
| [04-Java8-Features.md](interview-questions/04-Java8-Features.md) | Java 8+ Features | 8 câu |

**Tổng: 38+ câu hỏi với đáp án chi tiết**

---

### 2️⃣ Bài Tập Thực Hành (Exercises)

Nằm trong thư mục [exercises/](exercises/)

| File | Chủ đề | Số bài |
|------|--------|--------|
| [01-OOP-Exercises.java](exercises/01-OOP-Exercises.java) | OOP Practice | 10 bài |
| [02-Collections-Exercises.java](exercises/02-Collections-Exercises.java) | Collections Practice | 15 bài |
| [03-Exception-Exercises.java](exercises/03-Exception-Exercises.java) | Exception Handling | 15 bài |
| [04-Stream-Lambda-Exercises.java](exercises/04-Stream-Lambda-Exercises.java) | Stream API & Lambda | 20 bài |

**Tổng: 60 bài tập thực hành**

---

## 🎯 Cách Sử Dụng

### Bước 1: Đọc Câu Hỏi Phỏng Vấn

1. Bắt đầu với [01-OOP-Questions.md](interview-questions/01-OOP-Questions.md)
2. Đọc từng câu hỏi và cố gắng trả lời trước khi xem đáp án
3. Ghi chú những câu chưa nắm vững để ôn lại

### Bước 2: Làm Bài Tập Thực Hành

1. Mở file Java trong thư mục `exercises/`
2. Đọc yêu cầu từng bài (đánh dấu `// TODO`)
3. Implement code theo yêu cầu
4. Uncomment test code trong `main()` để kiểm tra
5. Chạy và debug cho đến khi đúng

**Ví dụ:**

```java
// 1. Đọc yêu cầu
public static int findMax(List<Integer> numbers) {
    // TODO: Implement
    return 0;
}

// 2. Implement
public static int findMax(List<Integer> numbers) {
    return numbers.stream()
                  .max(Integer::compareTo)
                  .orElse(0);
}

// 3. Uncomment và test
public static void main(String[] args) {
    List<Integer> numbers = Arrays.asList(5, 2, 8, 1, 9, 3);
    System.out.println("Max: " + findMax(numbers)); // 9
}
```

### Bước 3: Tự Kiểm Tra

- Giải thích code của bạn như đang phỏng vấn
- So sánh với best practices trong câu hỏi phỏng vấn
- Tìm cách optimize hoặc viết ngắn gọn hơn

---

## 📖 Chi Tiết Từng Chủ Đề

### 🎨 OOP (Object-Oriented Programming)

**Câu hỏi quan trọng:**
- 4 tính chất OOP
- Abstract Class vs Interface
- Method Overloading vs Overriding
- equals() và hashCode()
- Comparable vs Comparator

**Bài tập:**
- Encapsulation: BankAccount
- Inheritance: Vehicle hierarchy
- Polymorphism: Shape calculations
- Composition: Computer components
- Static members & Custom exceptions

---

### 📦 Collections Framework

**Câu hỏi quan trọng:**
- ArrayList vs LinkedList
- HashSet vs TreeSet vs LinkedHashSet
- HashMap internal working
- Fail-fast vs Fail-safe Iterator
- ConcurrentHashMap

**Bài tập:**
- List operations (find min/max/sum)
- Remove duplicates với Set
- Word frequency với HashMap
- Group by operations
- Custom sorting với Comparator

---

### ⚠️ Exception Handling

**Câu hỏi quan trọng:**
- Checked vs Unchecked Exception
- try-catch-finally
- throw vs throws
- Custom exceptions
- Try-with-resources

**Bài tập:**
- Basic exception handling
- Custom checked exceptions (InsufficientBalanceException)
- Custom unchecked exceptions (InvalidEmailException)
- Exception chaining
- AutoCloseable implementation

---

### 💾 String & Memory

**Câu hỏi quan trọng:**
- String vs StringBuilder vs StringBuffer
- String Pool
- Immutability của String
- JVM Memory Structure
- Garbage Collection

---

### 🚀 Java 8+ Features

**Câu hỏi quan trọng:**
- Lambda Expressions
- Functional Interfaces
- Stream API
- Optional
- Method Reference
- Default Methods

**Bài tập:**
- Lambda basics với tất cả Functional Interfaces
- Stream operations: filter, map, flatMap
- Collectors: groupingBy, partitioningBy
- Complex queries với Stream
- Optional usage patterns

---

## 📅 Lộ Trình Ôn Tập 2 Tuần

### Tuần 1: Fundamentals

| Ngày | Sáng | Chiều |
|------|------|-------|
| **Day 1-2** | OOP Questions (10 câu) | OOP Exercises (10 bài) |
| **Day 3-4** | Collections Questions (10 câu) | Collections Exercises (15 bài) |
| **Day 5** | Exception Questions (phần Exception) | Exception Exercises (8 bài đầu) |
| **Day 6-7** | Review & Redo khó | Mock interview OOP + Collections |

### Tuần 2: Advanced

| Ngày | Sáng | Chiều |
|------|------|-------|
| **Day 1** | String & Memory Questions | Exception Exercises (7 bài còn lại) |
| **Day 2-3** | Java 8 Questions | Stream Exercises (10 bài đầu) |
| **Day 4** | Java 8 Questions (tiếp) | Stream Exercises (10 bài còn lại) |
| **Day 5-6** | Review tất cả Questions | Redo bài tập khó |
| **Day 7** | Mock Interview | Final Review |

---

## 💡 Tips Ôn Tập Hiệu Quả

### 1. Học Theo Thứ Tự
- Không skip chủ đề
- OOP là nền tảng, phải nắm vững
- Collections và Exception dùng hằng ngày
- Java 8 features là must-have hiện nay

### 2. Practice Makes Perfect
- Viết code thay vì chỉ đọc
- Tự implement từ đầu, không copy-paste
- Giải thích code như đang phỏng vấn
- Làm lại bài tập nhiều lần

### 3. Hiểu Sâu, Không Học Vẹt
- Tại sao ArrayList nhanh hơn LinkedList khi truy cập?
- Tại sao String là immutable?
- HashMap hoạt động như thế nào?
- Khi nào dùng Stream, khi nào dùng for loop?

### 4. So Sánh & Phân Biệt
- ArrayList vs LinkedList
- HashMap vs TreeMap vs LinkedHashMap
- throw vs throws
- map() vs flatMap()
- filter() vs map()

### 5. Code Examples
- Chuẩn bị sẵn code examples cho từng concept
- Ví dụ: HashMap collision, Stream pipeline, Custom exception
- Nói được flow từng dòng code

---

## 🎤 Câu Hỏi Phỏng Vấn Hay Gặp

### Câu Mở Đầu
> "Giới thiệu về bản thân và kinh nghiệm Java của bạn?"

**Chuẩn bị:**
- Các project đã làm
- Technologies đã dùng (Spring, Hibernate, etc.)
- Điểm mạnh về Java

---

### Câu Coding On-the-Spot

**Dễ:**
- Reverse một String
- Tìm số lớn nhất trong array
- Remove duplicates từ List

**Trung bình:**
- Implement equals() và hashCode()
- Group list of objects by property
- Custom sorting với Comparator

**Khó:**
- Find duplicate characters in String
- Flatten nested List
- Top K elements using Stream

---

### Câu Lý Thuyết Sâu

1. "Giải thích HashMap hoạt động như thế nào?"
2. "Tại sao String là immutable?"
3. "Phân biệt Checked vs Unchecked Exception?"
4. "Stream API hoạt động như thế nào? Lazy evaluation là gì?"
5. "Garbage Collection trong Java?"

→ Tất cả đều có trong câu hỏi phỏng vấn!

---

## 🔧 Setup & Run

### Requirements
- Java 8 trở lên (recommend Java 11+)
- IDE: IntelliJ IDEA / Eclipse / VS Code

### Compile & Run

```bash
# Compile
javac exercises/01-OOP-Exercises.java

# Run
java -cp exercises OOPExercises

# Hoặc dùng IDE: Right click → Run
```

---

## 📝 Ghi Chú Quan Trọng

### ⭐ Must Know Cho Intern
- [x] 4 tính chất OOP
- [x] ArrayList vs LinkedList
- [x] HashMap basics
- [x] Exception handling
- [x] Lambda & Stream basics

### 🌟 Nice to Have
- [ ] ConcurrentHashMap
- [ ] Memory management chi tiết
- [ ] Advanced Stream operations
- [ ] Design patterns basics

---

## 🎯 Sau Khi Hoàn Thành

### Checklist Kiểm Tra
- [ ] Trả lời được tất cả 38 câu hỏi không cần xem đáp án
- [ ] Hoàn thành 60 bài tập
- [ ] Giải thích được code của mình
- [ ] So sánh được các concepts tương tự
- [ ] Tự tin code trước mặt interviewer

### Next Steps
1. **Practice trên LeetCode/HackerRank**
   - Easy problems với Java Collections
   - String manipulation
   - Stream API challenges

2. **Học thêm**
   - Design Patterns (Singleton, Factory, Observer)
   - Spring Framework basics
   - JUnit testing
   - Git basics

3. **Mock Interviews**
   - Tập phỏng vấn với bạn bè
   - Record và review
   - Cải thiện cách trình bày

---

## 📚 Tài Liệu Tham Khảo

### Official Docs
- [Java SE Documentation](https://docs.oracle.com/javase/)
- [Java Tutorials](https://docs.oracle.com/javase/tutorial/)

### Books
- "Effective Java" - Joshua Bloch
- "Java Concurrency in Practice"
- "Head First Design Patterns"

### Online Resources
- [Baeldung](https://www.baeldung.com/)
- [GeeksforGeeks Java](https://www.geeksforgeeks.org/java/)
- [JavaPoint](https://www.javatpoint.com/java-tutorial)

---

## 🤝 Đóng Góp

Nếu bạn tìm thấy lỗi hoặc muốn thêm câu hỏi/bài tập:
1. Tạo Issue mô tả vấn đề
2. Hoặc tạo Pull Request với improvements

---

## 📞 Hỗ Trợ

Nếu có thắc mắc trong quá trình học:
- Review lại phần lý thuyết trong câu hỏi phỏng vấn
- Google với keywords cụ thể
- Tham khảo Official Java Documentation
- Thảo luận với cộng đồng Java

---

## ✅ Lời Kết

**Chúc bạn ôn tập tốt và thành công trong buổi phỏng vấn! 🎉**

Remember:
> "Practice doesn't make perfect. Perfect practice makes perfect."

Hãy:
- ✅ Hiểu sâu, không học vẹt
- ✅ Code nhiều, đọc ít
- ✅ Giải thích được tại sao
- ✅ Tự tin và chuẩn bị kỹ

**Good luck! 🍀**

---

*Tài liệu được tạo để hỗ trợ ôn tập Java Core cho vị trí Java Intern.*
*Cập nhật lần cuối: 2025*
