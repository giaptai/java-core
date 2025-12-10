# Câu hỏi phỏng vấn về Stream API & Lambda - NÂNG CAO

> **Lưu ý:** File này chứa kiến thức nâng cao từ Stream & Lambda Exercises. Đọc file `04-Java8-Features.md` trước để nắm kiến thức cơ bản.

---

## Câu 1: Lambda Expression - Functional Interfaces chi tiết?

**Đáp án:**

### Built-in Functional Interfaces

Java cung cấp sẵn nhiều functional interfaces trong package `java.util.function`:

**1. Predicate<T> - Test condition (return boolean)**
```java
@FunctionalInterface
public interface Predicate<T> {
    boolean test(T t);
}

// Sử dụng
Predicate<Integer> isEven = x -> x % 2 == 0;
Predicate<String> startsWithA = s -> s.startsWith("A");

// Trong Stream
numbers.stream()
    .filter(x -> x % 2 == 0)  // Predicate
    .collect(Collectors.toList());
```

**2. Function<T, R> - Transform T to R**
```java
@FunctionalInterface
public interface Function<T, R> {
    R apply(T t);
}

// Sử dụng
Function<String, Integer> length = s -> s.length();
Function<Integer, String> toString = n -> String.valueOf(n);

// Trong Stream
List<Integer> lengths = strings.stream()
    .map(s -> s.length())  // Function<String, Integer>
    .toList();
```

**3. Consumer<T> - Accept input, no return**
```java
@FunctionalInterface
public interface Consumer<T> {
    void accept(T t);
}

// Sử dụng
Consumer<String> print = s -> System.out.println(s);
Consumer<String> printUpper = s -> System.out.println(s.toUpperCase());

// Trong Stream
names.forEach(s -> System.out.println(s));  // Consumer
```

**4. Supplier<T> - No input, return T**
```java
@FunctionalInterface
public interface Supplier<T> {
    T get();
}

// Sử dụng
Supplier<Double> random = () -> Math.random();
Supplier<String> uuid = () -> UUID.randomUUID().toString();

// Trong Stream
Stream.generate(() -> Math.random())  // Supplier
    .limit(5)
    .forEach(System.out::println);
```

**5. BiFunction<T, U, R> - Two inputs, return R**
```java
@FunctionalInterface
public interface BiFunction<T, U, R> {
    R apply(T t, U u);
}

// Sử dụng
BiFunction<String, String, String> concat = (a, b) -> a + b;
BiFunction<Integer, Integer, Integer> sum = (a, b) -> a + b;

// Example usage
String result = concat.apply("Hello", "World");  // "HelloWorld"
```

### Lambda Syntax Variations

```java
// No parameters
Runnable r = () -> System.out.println("Hello");

// One parameter (no parentheses needed)
Consumer<String> c1 = s -> System.out.println(s);
Consumer<String> c2 = (s) -> System.out.println(s);  // Also valid

// Multiple parameters
BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;

// With type declaration
BiFunction<Integer, Integer, Integer> add = (Integer a, Integer b) -> a + b;

// Multiple statements (need braces and return)
Function<Integer, Integer> square = x -> {
    int result = x * x;
    return result;
};

// Single expression (implicit return)
Function<Integer, Integer> square = x -> x * x;
```

---

## Câu 2: Method Reference - 4 loại chi tiết?

**Đáp án:**

### 4 Loại Method Reference

**1. Static Method Reference - ClassName::staticMethod**
```java
// Lambda
Function<String, Integer> parse1 = s -> Integer.parseInt(s);

// Method reference
Function<String, Integer> parse2 = Integer::parseInt;

// Example
List<String> strings = Arrays.asList("1", "2", "3");
List<Integer> numbers = strings.stream()
    .map(Integer::parseInt)
    .toList();
```

**2. Instance Method Reference - object::instanceMethod**
```java
String prefix = "Hello ";
Function<String, String> greet = prefix::concat;
System.out.println(greet.apply("World"));  // "Hello World"

// Example
List<String> words = Arrays.asList("java", "python", "javascript");
words.forEach(System.out::println);  // System.out là object
```

**3. Instance Method of Arbitrary Object - ClassName::instanceMethod**
```java
// Lambda
Function<String, Integer> length1 = s -> s.length();

// Method reference
Function<String, Integer> length2 = String::length;

// Example
List<String> words = Arrays.asList("java", "python", "javascript");
List<Integer> lengths = words.stream()
    .map(String::length)  // Gọi length() trên mỗi String object
    .toList();

// Sort example
List<String> names = Arrays.asList("Charlie", "Alice", "Bob");
names.sort(String::compareToIgnoreCase);
```

**4. Constructor Reference - ClassName::new**
```java
// Lambda
Supplier<List<String>> listSupplier1 = () -> new ArrayList<>();

// Constructor reference
Supplier<List<String>> listSupplier2 = ArrayList::new;

// Example with Function
Function<String, Person> creator = Person::new;
Person p = creator.apply("Alice");

// Example in Stream
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
List<Person> people = names.stream()
    .map(Person::new)  // new Person(name)
    .toList();
```

### Khi nào dùng Method Reference?

**Dùng Method Reference khi:**
- ✅ Lambda chỉ gọi một method duy nhất
- ✅ Parameters được pass trực tiếp vào method
- ✅ Code ngắn gọn và dễ đọc hơn

**Dùng Lambda khi:**
- ❌ Cần thêm logic xử lý
- ❌ Cần transform parameters trước khi pass
- ❌ Multiple statements

```java
// ✅ Good - Dùng method reference
list.forEach(System.out::println);

// ❌ Bad - Phải dùng lambda
list.forEach(s -> System.out.println("Name: " + s));

// ✅ Good - Dùng method reference
list.stream().map(String::length).toList();

// ❌ Bad - Phải dùng lambda
list.stream().map(s -> s.length() * 2).toList();
```

---

## Câu 3: Stream Pipeline - Intermediate vs Terminal Operations?

**Đáp án:**

### Stream Pipeline Structure

```
Stream Source → Intermediate Operations → Terminal Operation
    |                    |                        |
  List.stream()      .filter()               .collect()
                     .map()                  .forEach()
                     .sorted()               .count()
```

### Intermediate Operations (Lazy Evaluation)

**Không thực thi cho đến khi có terminal operation!**

```java
List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");

// Không in ra gì! (no terminal operation)
names.stream()
    .filter(s -> {
        System.out.println("Filtering: " + s);  // Không chạy!
        return s.length() > 3;
    })
    .map(s -> {
        System.out.println("Mapping: " + s);    // Không chạy!
        return s.toUpperCase();
    });
```

**Các Intermediate Operations:**

**1. filter(Predicate) - Lọc elements**
```java
List<Integer> even = numbers.stream()
    .filter(x -> x % 2 == 0)
    .toList();
```

**2. map(Function) - Transform elements**
```java
List<Integer> lengths = strings.stream()
    .map(String::length)
    .toList();
```

**3. flatMap(Function) - Flatten nested structures**
```java
List<List<Integer>> nested = Arrays.asList(
    Arrays.asList(1, 2, 3),
    Arrays.asList(4, 5),
    Arrays.asList(6, 7, 8)
);

List<Integer> flat = nested.stream()
    .flatMap(List::stream)
    .toList();
// Result: [1, 2, 3, 4, 5, 6, 7, 8]
```

**4. sorted() / sorted(Comparator) - Sort elements**
```java
// Natural order
List<Integer> sorted = numbers.stream()
    .sorted()
    .toList();

// Custom comparator
List<String> sorted = names.stream()
    .sorted(Comparator.comparing(String::length))
    .toList();

// Descending
List<String> sorted = names.stream()
    .sorted(Comparator.comparing(String::length).reversed())
    .toList();
```

**5. distinct() - Remove duplicates**
```java
List<Integer> unique = numbers.stream()
    .distinct()
    .toList();
```

**6. limit(n) - Take first n elements**
```java
List<Integer> first5 = numbers.stream()
    .limit(5)
    .toList();
```

**7. skip(n) - Skip first n elements**
```java
List<Integer> afterFirst5 = numbers.stream()
    .skip(5)
    .toList();
```

**8. peek(Consumer) - Debug/side effects (không thay đổi stream)**
```java
List<String> result = names.stream()
    .filter(s -> s.length() > 3)
    .peek(s -> System.out.println("Filtered: " + s))
    .map(String::toUpperCase)
    .peek(s -> System.out.println("Mapped: " + s))
    .toList();
```

---

### Terminal Operations (Eager Evaluation)

**Trigger stream execution và return result!**

**1. collect(Collector) - Collect to collection**
```java
// To List
List<String> list = stream.collect(Collectors.toList());
List<String> list = stream.toList();  // Java 16+

// To Set
Set<String> set = stream.collect(Collectors.toSet());

// To Map
Map<String, Integer> map = people.stream()
    .collect(Collectors.toMap(Person::getName, Person::getAge));

// Joining strings
String joined = stream.collect(Collectors.joining(", "));
```

**2. forEach(Consumer) - Perform action**
```java
stream.forEach(System.out::println);
stream.forEach(s -> System.out.println(s));
```

**3. count() - Count elements**
```java
long count = stream.count();
```

**4. reduce() - Aggregate elements**
```java
// With identity
int sum = numbers.stream()
    .reduce(0, (a, b) -> a + b);

// Without identity (return Optional)
Optional<Integer> max = numbers.stream()
    .reduce((a, b) -> a > b ? a : b);

// With accumulator and combiner
int sum = numbers.stream()
    .reduce(0, Integer::sum);
```

**5. Matching operations:**
```java
// anyMatch - Có ít nhất 1 element thỏa mãn?
boolean hasEven = numbers.stream()
    .anyMatch(x -> x % 2 == 0);

// allMatch - Tất cả elements thỏa mãn?
boolean allPositive = numbers.stream()
    .allMatch(x -> x > 0);

// noneMatch - Không có element nào thỏa mãn?
boolean noneNegative = numbers.stream()
    .noneMatch(x -> x < 0);
```

**6. Finding operations:**
```java
// findFirst - Tìm element đầu tiên
Optional<String> first = stream.findFirst();

// findAny - Tìm bất kỳ element nào (useful for parallel streams)
Optional<String> any = stream.findAny();
```

**7. min() / max() - Find min/max**
```java
Optional<Integer> min = numbers.stream()
    .min(Integer::compareTo);

Optional<Integer> max = numbers.stream()
    .max(Integer::compareTo);
```

---

## Câu 4: Stream reduce() - Identity, Accumulator, Combiner?

**Đáp án:**

### reduce() Signatures

**1. reduce(BinaryOperator accumulator) - No identity**
```java
Optional<Integer> max = numbers.stream()
    .reduce((a, b) -> a > b ? a : b);

// Return Optional vì stream có thể empty
if (max.isPresent()) {
    System.out.println("Max: " + max.get());
}
```

**2. reduce(T identity, BinaryOperator accumulator) - With identity**
```java
int sum = numbers.stream()
    .reduce(0, (a, b) -> a + b);

// identity = 0: Giá trị khởi đầu
// Nếu stream empty → return identity
// Không return Optional vì luôn có giá trị
```

**3. reduce(U identity, BiFunction accumulator, BinaryOperator combiner)**
```java
// Dùng cho parallel streams
int sum = numbers.parallelStream()
    .reduce(
        0,                          // identity
        (subtotal, element) -> subtotal + element,  // accumulator
        (sum1, sum2) -> sum1 + sum2                // combiner
    );
```

---

### Chi tiết từng parameter:

**1. Identity:**
- Giá trị khởi đầu
- Phải là identity value cho operation:
  - Addition: `0` (0 + x = x)
  - Multiplication: `1` (1 × x = x)
  - String concatenation: `""` ("" + x = x)

```java
// Addition identity = 0
int sum = numbers.stream().reduce(0, (a, b) -> a + b);

// Multiplication identity = 1
int product = numbers.stream().reduce(1, (a, b) -> a * b);

// String concatenation identity = ""
String concat = strings.stream().reduce("", (a, b) -> a + b);
```

**2. Accumulator:**
- Combine 2 values thành 1
- `(partial_result, next_element) -> combined_result`

```java
// Sum
(subtotal, element) -> subtotal + element

// Product
(product, element) -> product * element

// Max
(max, element) -> max > element ? max : element

// String concat
(result, string) -> result + string
```

**3. Combiner (chỉ cho parallel streams):**
- Combine 2 partial results
- `(result1, result2) -> combined_result`

```java
// Parallel stream chia thành nhiều chunks
// Mỗi chunk tính partial result
// Combiner gộp các partial results lại

List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);

int sum = numbers.parallelStream()
    .reduce(
        0,                              // identity
        (subtotal, n) -> subtotal + n,  // accumulator
        (sum1, sum2) -> sum1 + sum2     // combiner
    );

// Execution:
// Thread 1: [1,2,3,4] → 0+1+2+3+4 = 10
// Thread 2: [5,6,7,8] → 0+5+6+7+8 = 26
// Combiner: 10 + 26 = 36
```

---

### Ví dụ thực tế:

**Example 1: Sum**
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

// Manual reduction
int sum = 0;  // identity
for (int n : numbers) {
    sum = sum + n;  // accumulator
}

// Stream reduce
int sum = numbers.stream()
    .reduce(0, (a, b) -> a + b);

// Using method reference
int sum = numbers.stream()
    .reduce(0, Integer::sum);
```

**Example 2: Product**
```java
int product = numbers.stream()
    .reduce(1, (a, b) -> a * b);
// 1 × 1 × 2 × 3 × 4 × 5 = 120
```

**Example 3: Max**
```java
Optional<Integer> max = numbers.stream()
    .reduce((a, b) -> a > b ? a : b);

// Or using Integer::max
Optional<Integer> max = numbers.stream()
    .reduce(Integer::max);
```

**Example 4: String concatenation**
```java
List<String> words = Arrays.asList("Java", "is", "awesome");

String sentence = words.stream()
    .reduce("", (a, b) -> a + " " + b);
// Result: " Java is awesome" (có space đầu)

// Better: use Collectors.joining()
String sentence = words.stream()
    .collect(Collectors.joining(" "));
// Result: "Java is awesome"
```

---

## Câu 5: Collectors - groupingBy, partitioningBy, downstream collectors?

**Đáp án:**

### Collectors Hierarchy

```
Collectors
├── toList(), toSet(), toMap()
├── joining()
├── counting()
├── summingInt/Long/Double()
├── averagingInt/Long/Double()
├── summarizingInt/Long/Double()
├── groupingBy()              ← Nhóm theo key
├── partitioningBy()          ← Chia 2 nhóm (true/false)
├── maxBy(), minBy()
└── reducing()
```

---

### 1. groupingBy() - Group elements by key

**Basic groupingBy:**
```java
class Person {
    String name;
    String city;
    int age;
}

List<Person> people = ...;

// Group by city
Map<String, List<Person>> byCity = people.stream()
    .collect(Collectors.groupingBy(Person::getCity));

// Result: {"NYC": [person1, person2], "LA": [person3]}
```

**groupingBy with downstream collector:**
```java
// Count people by city
Map<String, Long> countByCity = people.stream()
    .collect(Collectors.groupingBy(
        Person::getCity,           // classifier
        Collectors.counting()      // downstream collector
    ));
// Result: {"NYC": 2, "LA": 1}

// Sum ages by city
Map<String, Integer> totalAgeByCity = people.stream()
    .collect(Collectors.groupingBy(
        Person::getCity,
        Collectors.summingInt(Person::getAge)
    ));

// Average age by city
Map<String, Double> avgAgeByCity = people.stream()
    .collect(Collectors.groupingBy(
        Person::getCity,
        Collectors.averagingInt(Person::getAge)
    ));
```

**groupingBy with multiple levels:**
```java
// Group by city, then by age range
Map<String, Map<String, List<Person>>> cityThenAge = people.stream()
    .collect(Collectors.groupingBy(
        Person::getCity,
        Collectors.groupingBy(p ->
            p.getAge() < 18 ? "Minor" : "Adult"
        )
    ));
// Result: {"NYC": {"Adult": [...], "Minor": [...]}, "LA": {...}}
```

**groupingBy with maxBy/minBy:**
```java
// Find oldest person in each city
Map<String, Optional<Person>> oldestByCity = people.stream()
    .collect(Collectors.groupingBy(
        Person::getCity,
        Collectors.maxBy(Comparator.comparingInt(Person::getAge))
    ));
```

---

### 2. partitioningBy() - Split into 2 groups (true/false)

```java
// Partition by age >= 18
Map<Boolean, List<Person>> byAdult = people.stream()
    .collect(Collectors.partitioningBy(p -> p.getAge() >= 18));

// Result: {true: [adults], false: [minors]}

// Access:
List<Person> adults = byAdult.get(true);
List<Person> minors = byAdult.get(false);
```

**partitioningBy with downstream:**
```java
// Count adults vs minors
Map<Boolean, Long> countByAdult = people.stream()
    .collect(Collectors.partitioningBy(
        p -> p.getAge() >= 18,
        Collectors.counting()
    ));
// Result: {true: 15, false: 5}

// Average age of adults vs minors
Map<Boolean, Double> avgAgeByAdult = people.stream()
    .collect(Collectors.partitioningBy(
        p -> p.getAge() >= 18,
        Collectors.averagingInt(Person::getAge)
    ));
```

---

### 3. Downstream Collectors

**Các collectors có thể dùng làm downstream:**

**counting() - Count elements**
```java
Map<String, Long> countByCity = people.stream()
    .collect(Collectors.groupingBy(
        Person::getCity,
        Collectors.counting()
    ));
```

**summingInt/Long/Double() - Sum values**
```java
Map<String, Integer> totalAgeByCity = people.stream()
    .collect(Collectors.groupingBy(
        Person::getCity,
        Collectors.summingInt(Person::getAge)
    ));
```

**averagingInt/Long/Double() - Average values**
```java
Map<String, Double> avgAgeByCity = people.stream()
    .collect(Collectors.groupingBy(
        Person::getCity,
        Collectors.averagingInt(Person::getAge)
    ));
```

**maxBy() / minBy() - Find max/min**
```java
Map<String, Optional<Person>> oldestByCity = people.stream()
    .collect(Collectors.groupingBy(
        Person::getCity,
        Collectors.maxBy(Comparator.comparingInt(Person::getAge))
    ));
```

**mapping() - Transform before collecting**
```java
// Get all names by city
Map<String, List<String>> namesByCity = people.stream()
    .collect(Collectors.groupingBy(
        Person::getCity,
        Collectors.mapping(Person::getName, Collectors.toList())
    ));
```

**filtering() - Filter within group (Java 9+)**
```java
// Get adults only by city
Map<String, List<Person>> adultsByCity = people.stream()
    .collect(Collectors.groupingBy(
        Person::getCity,
        Collectors.filtering(p -> p.getAge() >= 18, Collectors.toList())
    ));
```

---

### Real World Example: Complex Aggregation

```java
class Transaction {
    String customerId;
    double amount;
    String date;
}

List<Transaction> transactions = ...;

// Find top 3 customers by total transaction amount
List<String> top3Customers = transactions.stream()
    .collect(Collectors.groupingBy(
        Transaction::getCustomerId,
        Collectors.summingDouble(Transaction::getAmount)
    ))
    .entrySet().stream()
    .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
    .limit(3)
    .map(Map.Entry::getKey)
    .toList();

// Step by step:
// 1. groupingBy(customerId) + summingDouble(amount)
//    → Map<String, Double>: {C1: 5000.0, C2: 3000.0, C3: 7000.0}
//
// 2. .entrySet().stream()
//    → Stream<Entry<String, Double>>
//
// 3. .sorted(...reversed())
//    → Sort by value descending: C3:7000, C1:5000, C2:3000
//
// 4. .limit(3)
//    → Top 3
//
// 5. .map(Entry::getKey)
//    → Extract customer IDs: [C3, C1, C2]
```

---

## Câu 6: Optional - Cách dùng đúng?

**Đáp án:**

### Optional là gì?

Container object có thể chứa hoặc không chứa non-null value.

**Mục đích:** Tránh NullPointerException và explicit null checks.

---

### Tạo Optional

```java
// Empty Optional
Optional<String> empty = Optional.empty();

// Optional with non-null value
Optional<String> opt = Optional.of("Hello");
// ⚠️ Throw NullPointerException nếu value = null!

// Optional with nullable value
Optional<String> opt = Optional.ofNullable(value);
// ✅ Safe - return empty Optional nếu value = null
```

---

### Check value

```java
Optional<String> opt = Optional.ofNullable(name);

// ❌ BAD - Giống null check
if (opt.isPresent()) {
    String value = opt.get();
    System.out.println(value);
}

// ✅ GOOD - Dùng Optional API
opt.ifPresent(value -> System.out.println(value));

// ✅ BETTER - Method reference
opt.ifPresent(System.out::println);
```

---

### Get value

**orElse() - Return default value**
```java
String name = Optional.ofNullable(person)
    .map(Person::getName)
    .orElse("Unknown");
```

**orElseGet() - Return value from Supplier (lazy)**
```java
String name = Optional.ofNullable(person)
    .map(Person::getName)
    .orElseGet(() -> getDefaultName());  // Chỉ call nếu empty

// Khác với orElse():
.orElse(getDefaultName())  // ❌ Luôn call getDefaultName()!
.orElseGet(() -> getDefaultName())  // ✅ Chỉ call khi empty
```

**orElseThrow() - Throw exception if empty**
```java
Person person = Optional.ofNullable(findPerson(id))
    .orElseThrow(() -> new PersonNotFoundException("Not found: " + id));

// Java 10+: Default exception
Person person = Optional.ofNullable(findPerson(id))
    .orElseThrow();  // Throw NoSuchElementException
```

---

### Transform value

**map() - Transform value if present**
```java
Optional<String> name = Optional.ofNullable(person)
    .map(Person::getName);

Optional<Integer> length = Optional.ofNullable(name)
    .map(String::length);

// Chain transformations
String upperName = Optional.ofNullable(person)
    .map(Person::getName)
    .map(String::toUpperCase)
    .orElse("UNKNOWN");
```

**flatMap() - Transform to another Optional (avoid nested Optional)**
```java
class Person {
    Optional<Address> getAddress() { ... }
}

class Address {
    Optional<String> getStreet() { ... }
}

// ❌ BAD - Nested Optional<Optional<String>>
Optional<Optional<String>> street = Optional.ofNullable(person)
    .map(Person::getAddress)
    .map(addr -> addr.getStreet());

// ✅ GOOD - FlatMap để flatten
Optional<String> street = Optional.ofNullable(person)
    .flatMap(Person::getAddress)
    .flatMap(Address::getStreet);
```

**filter() - Filter value**
```java
Optional<Person> adult = Optional.ofNullable(person)
    .filter(p -> p.getAge() >= 18);

// Example: Get name if age >= 18
String name = Optional.ofNullable(person)
    .filter(p -> p.getAge() >= 18)
    .map(Person::getName)
    .orElse("Not an adult");
```

---

### Common Patterns

**Pattern 1: Null-safe chain**
```java
// ❌ BAD - Multiple null checks
String street = null;
if (person != null) {
    Address addr = person.getAddress();
    if (addr != null) {
        street = addr.getStreet();
    }
}

// ✅ GOOD - Optional chain
String street = Optional.ofNullable(person)
    .map(Person::getAddress)
    .map(Address::getStreet)
    .orElse("Unknown");
```

**Pattern 2: Conditional execution**
```java
// ❌ BAD
if (person != null) {
    sendEmail(person.getEmail());
}

// ✅ GOOD
Optional.ofNullable(person)
    .map(Person::getEmail)
    .ifPresent(email -> sendEmail(email));
```

**Pattern 3: Fallback chain**
```java
// Try multiple sources
String value = Optional.ofNullable(getValue1())
    .or(() -> Optional.ofNullable(getValue2()))
    .or(() -> Optional.ofNullable(getValue3()))
    .orElse("default");
```

---

### Anti-patterns (Tránh!)

**❌ Don't use Optional as field**
```java
// ❌ BAD
class Person {
    private Optional<String> name;  // NO!
}

// ✅ GOOD
class Person {
    private String name;  // Can be null

    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }
}
```

**❌ Don't use Optional.get() without checking**
```java
// ❌ BAD - Can throw NoSuchElementException
Optional<String> opt = Optional.ofNullable(value);
String result = opt.get();  // Dangerous!

// ✅ GOOD
String result = opt.orElse("default");
```

**❌ Don't use Optional for collections**
```java
// ❌ BAD
Optional<List<String>> names = Optional.ofNullable(getNames());

// ✅ GOOD - Return empty list instead
List<String> names = getNames() != null ? getNames() : Collections.emptyList();
```

---

## Câu 7: Parallel Streams - Khi nào dùng? Performance?

**Đáp án:**

### Sequential vs Parallel

**Sequential Stream:**
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

int sum = numbers.stream()  // Sequential
    .mapToInt(Integer::intValue)
    .sum();
```

**Parallel Stream:**
```java
int sum = numbers.parallelStream()  // Parallel
    .mapToInt(Integer::intValue)
    .sum();

// Or
int sum = numbers.stream()
    .parallel()  // Convert to parallel
    .mapToInt(Integer::intValue)
    .sum();
```

---

### Parallel Stream hoạt động thế nào?

**Fork-Join Framework:**
```
Original List: [1, 2, 3, 4, 5, 6, 7, 8]

Fork (Split):
Thread 1: [1, 2, 3, 4]
Thread 2: [5, 6, 7, 8]

Process:
Thread 1: sum = 10
Thread 2: sum = 26

Join (Merge):
Total: 10 + 26 = 36
```

**Common Fork-Join Pool:**
- Shared by all parallel streams in application
- Number of threads = số CPU cores
- `Runtime.getRuntime().availableProcessors()`

---

### Khi nào dùng Parallel Streams?

**✅ NÊN DÙNG KHI:**

1. **Large dataset** (>10,000 elements)
```java
List<Integer> largeList = IntStream.range(0, 1_000_000)
    .boxed()
    .toList();

// Parallel tốt hơn
long sum = largeList.parallelStream()
    .mapToInt(Integer::intValue)
    .sum();
```

2. **CPU-intensive operations**
```java
// Complex computation cho mỗi element
List<Double> results = data.parallelStream()
    .map(item -> expensiveComputation(item))
    .toList();
```

3. **Independent operations** (no shared state)
```java
// ✅ GOOD - Mỗi operation độc lập
List<String> upper = words.parallelStream()
    .map(String::toUpperCase)
    .toList();
```

**❌ KHÔNG NÊN DÙNG KHI:**

1. **Small dataset** (<10,000 elements)
```java
// Sequential nhanh hơn vì overhead của parallel
List<Integer> small = Arrays.asList(1, 2, 3, 4, 5);
int sum = small.stream().mapToInt(Integer::intValue).sum();
```

2. **I/O operations**
```java
// ❌ BAD - I/O blocking
List<String> contents = files.parallelStream()
    .map(file -> readFile(file))  // I/O operation
    .toList();
```

3. **Stateful operations** (có shared state)
```java
// ❌ BAD - Race condition!
List<Integer> result = new ArrayList<>();
numbers.parallelStream()
    .forEach(n -> result.add(n * 2));  // Not thread-safe!

// ✅ GOOD - Use collect
List<Integer> result = numbers.parallelStream()
    .map(n -> n * 2)
    .toList();
```

4. **Order-dependent operations**
```java
// ❌ Thứ tự không đảm bảo
numbers.parallelStream()
    .forEach(System.out::println);

// ✅ Dùng forEachOrdered nếu cần order
numbers.parallelStream()
    .forEachOrdered(System.out::println);
```

---

### Performance Considerations

**Overhead:**
- Splitting data
- Thread management
- Merging results

**Break-even point:** Thường ~10,000 elements

**Benchmark example:**
```java
// Sequential: 100ms
List<Integer> result = numbers.stream()
    .map(n -> expensiveOperation(n))
    .toList();

// Parallel: 30ms (with 4 cores)
List<Integer> result = numbers.parallelStream()
    .map(n -> expensiveOperation(n))
    .toList();
```

---

### Common Pitfalls

**1. Thread Safety Issues**
```java
// ❌ BAD - Not thread-safe
List<Integer> result = new ArrayList<>();
numbers.parallelStream().forEach(result::add);

// ✅ GOOD - Use concurrent collection
List<Integer> result = new CopyOnWriteArrayList<>();
numbers.parallelStream().forEach(result::add);

// ✅ BEST - Use collect
List<Integer> result = numbers.parallelStream().toList();
```

**2. Blocking Operations**
```java
// ❌ BAD - Blocking reduces parallelism
results = items.parallelStream()
    .map(item -> callExternalAPI(item))  // Blocking I/O
    .toList();
```

**3. Boxing Overhead**
```java
// ❌ BAD - Boxing/unboxing overhead
int sum = numbers.parallelStream()
    .reduce(0, (a, b) -> a + b);

// ✅ GOOD - Use primitive stream
int sum = numbers.parallelStream()
    .mapToInt(Integer::intValue)
    .sum();
```

---

## Câu 8: Infinite Streams - iterate() vs generate()?

**Đáp án:**

### Infinite Streams

Streams không có kích thước cố định, cần `.limit()` để giới hạn.

---

### 1. Stream.iterate() - Stateful generation

**Syntax:**
```java
Stream.iterate(seed, unaryOperator)
```

**Ví dụ:**
```java
// Generate even numbers: 0, 2, 4, 6, 8...
Stream.iterate(0, n -> n + 2)
    .limit(5)
    .forEach(System.out::println);
// Output: 0, 2, 4, 6, 8

// Fibonacci sequence
Stream.iterate(new int[]{0, 1}, arr -> new int[]{arr[1], arr[0] + arr[1]})
    .limit(10)
    .map(arr -> arr[0])
    .forEach(System.out::println);
// Output: 0, 1, 1, 2, 3, 5, 8, 13, 21, 34

// Powers of 2
Stream.iterate(1, n -> n * 2)
    .limit(10)
    .forEach(System.out::println);
// Output: 1, 2, 4, 8, 16, 32, 64, 128, 256, 512
```

**Đặc điểm:**
- ✅ Mỗi element phụ thuộc vào element trước
- ✅ Stateful - có state giữa các iterations
- ✅ Deterministic - luôn cho kết quả giống nhau

---

### 2. Stream.generate() - Stateless generation

**Syntax:**
```java
Stream.generate(supplier)
```

**Ví dụ:**
```java
// Random numbers
Stream.generate(Math::random)
    .limit(5)
    .forEach(System.out::println);

// UUID strings
Stream.generate(() -> UUID.randomUUID().toString())
    .limit(3)
    .forEach(System.out::println);

// Constant value
Stream.generate(() -> "Hello")
    .limit(5)
    .forEach(System.out::println);
// Output: Hello, Hello, Hello, Hello, Hello
```

**Đặc điểm:**
- ✅ Mỗi element độc lập
- ✅ Stateless - không có state
- ✅ Non-deterministic (với random)

---

### So sánh iterate() vs generate()

| Aspect | iterate() | generate() |
|--------|-----------|------------|
| **State** | Stateful | Stateless |
| **Dependency** | Current depends on previous | Independent |
| **Deterministic** | Yes | Not necessarily |
| **Use case** | Sequences (even numbers, Fibonacci) | Random values, constants |

---

### Use Cases

**iterate() - Sequences:**
```java
// Countdown
Stream.iterate(10, n -> n - 1)
    .limit(11)
    .forEach(System.out::println);
// 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0

// Date sequence
Stream.iterate(LocalDate.now(), date -> date.plusDays(1))
    .limit(7)
    .forEach(System.out::println);
// Next 7 days
```

**generate() - Random/Constant values:**
```java
// Random integers
Stream.generate(() -> ThreadLocalRandom.current().nextInt(100))
    .limit(10)
    .forEach(System.out::println);

// Test data
List<Person> testData = Stream.generate(() -> new Person("Test", 25))
    .limit(100)
    .toList();
```

---

### Java 9+: iterate() with predicate

```java
// Java 8: Manual limit
Stream.iterate(1, n -> n + 1)
    .limit(10)  // Có thể quên limit!
    .forEach(System.out::println);

// Java 9+: Predicate để stop
Stream.iterate(1, n -> n <= 10, n -> n + 1)
    .forEach(System.out::println);
// Safer - không cần limit()
```

---

### Performance Note

**⚠️ Cẩn thận với infinite streams:**
```java
// ❌ DANGER - Infinite loop!
Stream.iterate(0, n -> n + 1)
    .forEach(System.out::println);  // Never ends!

// ✅ SAFE - Always use limit() or takeWhile()
Stream.iterate(0, n -> n + 1)
    .limit(100)
    .forEach(System.out::println);
```

---

## Câu 9: IntSummaryStatistics - Tính statistics một lúc?

**Đáp án:**

### IntSummaryStatistics

Class dùng để collect statistics (count, sum, min, max, average) trong 1 pass.

---

### Cách sử dụng

**Method 1: summaryStatistics() trên IntStream**
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

IntSummaryStatistics stats = numbers.stream()
    .mapToInt(Integer::intValue)
    .summaryStatistics();

System.out.println("Count: " + stats.getCount());      // 5
System.out.println("Sum: " + stats.getSum());          // 15
System.out.println("Min: " + stats.getMin());          // 1
System.out.println("Max: " + stats.getMax());          // 5
System.out.println("Average: " + stats.getAverage());  // 3.0
```

**Method 2: Collectors.summarizingInt()**
```java
IntSummaryStatistics stats = numbers.stream()
    .collect(Collectors.summarizingInt(Integer::intValue));
```

---

### Tại sao dùng SummaryStatistics?

**❌ BAD - Multiple passes:**
```java
long count = numbers.stream().count();
int sum = numbers.stream().mapToInt(Integer::intValue).sum();
int min = numbers.stream().mapToInt(Integer::intValue).min().orElse(0);
int max = numbers.stream().mapToInt(Integer::intValue).max().orElse(0);
double avg = numbers.stream().mapToInt(Integer::intValue).average().orElse(0);

// 5 passes through the stream!
```

**✅ GOOD - Single pass:**
```java
IntSummaryStatistics stats = numbers.stream()
    .mapToInt(Integer::intValue)
    .summaryStatistics();

// 1 pass, get all stats!
```

---

### Variants

**LongSummaryStatistics:**
```java
LongSummaryStatistics stats = numbers.stream()
    .mapToLong(Integer::longValue)
    .summaryStatistics();
```

**DoubleSummaryStatistics:**
```java
DoubleSummaryStatistics stats = prices.stream()
    .mapToDouble(Product::getPrice)
    .summaryStatistics();
```

---

### Real World Example

```java
class Product {
    String name;
    double price;
    int quantity;
}

List<Product> products = ...;

// Get price statistics
DoubleSummaryStatistics priceStats = products.stream()
    .collect(Collectors.summarizingDouble(Product::getPrice));

System.out.println("Products count: " + priceStats.getCount());
System.out.println("Total value: $" + priceStats.getSum());
System.out.println("Cheapest: $" + priceStats.getMin());
System.out.println("Most expensive: $" + priceStats.getMax());
System.out.println("Average price: $" + priceStats.getAverage());
```

---

### Group Statistics

```java
// Statistics by category
Map<String, IntSummaryStatistics> statsByCategory = products.stream()
    .collect(Collectors.groupingBy(
        Product::getCategory,
        Collectors.summarizingInt(Product::getQuantity)
    ));

// Access
IntSummaryStatistics electronicsStats = statsByCategory.get("Electronics");
System.out.println("Electronics total quantity: " + electronicsStats.getSum());
```

---

## Câu 10: Best Practices & Common Pitfalls?

**Đáp án:**

### Best Practices

**1. Use method references when possible**
```java
// ❌ BAD
list.forEach(s -> System.out.println(s));

// ✅ GOOD
list.forEach(System.out::println);
```

**2. Prefer primitive streams for numbers**
```java
// ❌ BAD - Boxing overhead
int sum = numbers.stream()
    .reduce(0, Integer::sum);

// ✅ GOOD - No boxing
int sum = numbers.stream()
    .mapToInt(Integer::intValue)
    .sum();
```

**3. Use collect() instead of forEach() for accumulation**
```java
// ❌ BAD - Not thread-safe, side effects
List<String> result = new ArrayList<>();
stream.forEach(result::add);

// ✅ GOOD - Safe and idiomatic
List<String> result = stream.toList();
```

**4. Avoid stateful lambdas**
```java
// ❌ BAD - External state
int[] sum = {0};
numbers.forEach(n -> sum[0] += n);

// ✅ GOOD - Use reduce
int sum = numbers.stream()
    .mapToInt(Integer::intValue)
    .sum();
```

**5. Use Optional properly**
```java
// ❌ BAD
if (optional.isPresent()) {
    return optional.get();
} else {
    return "default";
}

// ✅ GOOD
return optional.orElse("default");
```

---

### Common Pitfalls

**1. Stream reuse**
```java
// ❌ ERROR - Stream already operated upon
Stream<String> stream = list.stream();
stream.forEach(System.out::println);
stream.count();  // IllegalStateException!

// ✅ CORRECT - Create new stream
list.stream().forEach(System.out::println);
list.stream().count();
```

**2. Modifying source during stream operation**
```java
// ❌ BAD - ConcurrentModificationException
List<String> list = new ArrayList<>(Arrays.asList("A", "B", "C"));
list.stream()
    .forEach(s -> {
        if (s.equals("B")) {
            list.remove(s);  // Modifying source!
        }
    });

// ✅ GOOD - Use filter and collect
List<String> filtered = list.stream()
    .filter(s -> !s.equals("B"))
    .toList();
```

**3. Misusing parallel streams**
```java
// ❌ BAD - Small dataset
List<Integer> small = Arrays.asList(1, 2, 3);
small.parallelStream().forEach(System.out::println);  // Overhead > benefit

// ❌ BAD - I/O operations
files.parallelStream()
    .map(this::readFile)  // Blocking I/O
    .toList();

// ✅ GOOD - Use when appropriate
largeList.parallelStream()
    .map(this::cpuIntensiveOperation)
    .toList();
```

**4. Forgetting terminal operation**
```java
// ❌ NO OUTPUT - No terminal operation!
numbers.stream()
    .filter(n -> n % 2 == 0)
    .map(n -> n * 2);  // Nothing happens!

// ✅ CORRECT - Add terminal operation
numbers.stream()
    .filter(n -> n % 2 == 0)
    .map(n -> n * 2)
    .toList();  // Terminal operation
```

**5. Inefficient chaining**
```java
// ❌ BAD - Multiple iterations
list.stream()
    .filter(s -> s.length() > 3)
    .collect(Collectors.toList())
    .stream()  // New stream!
    .map(String::toUpperCase)
    .collect(Collectors.toList());

// ✅ GOOD - Single pipeline
list.stream()
    .filter(s -> s.length() > 3)
    .map(String::toUpperCase)
    .toList();
```

---

### Performance Tips

**1. Use appropriate collector**
```java
// Slow
String result = list.stream()
    .reduce("", (a, b) -> a + b);  // Creates many intermediate strings

// Fast
String result = list.stream()
    .collect(Collectors.joining());  // Uses StringBuilder internally
```

**2. Short-circuit operations**
```java
// ❌ Processes all elements
boolean hasEven = numbers.stream()
    .filter(n -> n % 2 == 0)
    .count() > 0;

// ✅ Stops at first match
boolean hasEven = numbers.stream()
    .anyMatch(n -> n % 2 == 0);
```

**3. Filter early**
```java
// ❌ BAD - Process many elements
list.stream()
    .map(expensiveOperation)
    .filter(predicate)
    .toList();

// ✅ GOOD - Filter first
list.stream()
    .filter(predicate)
    .map(expensiveOperation)
    .toList();
```

---

## Tổng kết

File này cover các chủ đề nâng cao:
- ✅ Lambda expressions & Functional Interfaces chi tiết
- ✅ Method References (4 loại)
- ✅ Stream Pipeline (Intermediate vs Terminal)
- ✅ reduce() với identity, accumulator, combiner
- ✅ Collectors (groupingBy, partitioningBy, downstream)
- ✅ Optional patterns và best practices
- ✅ Parallel Streams và performance considerations
- ✅ Infinite Streams (iterate vs generate)
- ✅ IntSummaryStatistics
- ✅ Best Practices & Common Pitfalls

**Khi nào đọc file này:**
- Đã làm xong 20 bài Stream & Lambda exercises
- Chuẩn bị phỏng vấn mid/senior level
- Cần hiểu sâu về Stream API internals
- Muốn optimize stream operations
