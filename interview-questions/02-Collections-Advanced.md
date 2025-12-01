# Câu hỏi phỏng vấn về Collections Framework - NÂNG CAO

> **Lưu ý:** File này chứa kiến thức nâng cao từ Collections Exercises. Đọc file `02-Collections-Questions.md` trước để nắm kiến thức cơ bản.

---

## Câu 1: PriorityQueue hoạt động như thế nào? Làm sao tìm K phần tử lớn nhất hiệu quả?

**Đáp án:**

### PriorityQueue = Min Heap (Default)

**Đặc điểm:**
- Phần tử nhỏ nhất luôn ở root (đầu queue)
- `offer()` / `poll()`: O(log n)
- `peek()`: O(1)
- Không cho phép null

```java
// Min Heap - Default
Queue<Integer> minHeap = new PriorityQueue<>();
minHeap.offer(3);
minHeap.offer(1);
minHeap.offer(5);
System.out.println(minHeap.peek()); // 1 (smallest)

// Max Heap - Custom comparator
Queue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
maxHeap.offer(3);
maxHeap.offer(1);
maxHeap.offer(5);
System.out.println(maxHeap.peek()); // 5 (largest)
```

### Bài toán: Tìm K phần tử lớn nhất

**Cách 1: Sort toàn bộ array (Naive)**
```java
Arrays.sort(nums); // O(n log n)
return Arrays.copyOfRange(nums, nums.length - k, nums.length);
```
- Time: O(n log n)
- Space: O(n)

**Cách 2: Min Heap (Optimal) ⭐⭐⭐**
```java
public static List<Integer> findKLargest(int[] nums, int k) {
    Queue<Integer> minHeap = new PriorityQueue<>();

    for (int num : nums) {
        minHeap.offer(num);
        if (minHeap.size() > k) {
            minHeap.poll(); // Remove smallest
        }
    }

    List<Integer> result = new ArrayList<>(minHeap);
    Collections.sort(result, Collections.reverseOrder());
    return result;
}
```
- Time: **O(n log k)** - Tốt hơn nhiều khi k << n
- Space: **O(k)** - Chỉ lưu k elements

**Giải thích tại sao dùng Min Heap:**
1. Maintain heap size = k
2. Luôn remove phần tử nhỏ nhất → giữ lại k phần tử lớn nhất
3. Ví dụ: `nums = [3,1,4,1,5,9,2,6]`, k = 3
   - Add 3: heap = [3]
   - Add 1: heap = [1,3]
   - Add 4: heap = [1,3,4]
   - Add 1: heap = [1,1,3,4] → poll() → heap = [1,3,4]
   - Add 5: heap = [1,3,4,5] → poll() → heap = [3,4,5]
   - ...
   - Result: [5, 6, 9]

**Khi nào dùng:**
- Tìm top K elements trong large dataset
- Streaming data (không thể load hết vào memory)
- k << n (k nhỏ hơn n rất nhiều)

---

## Câu 2: Fail-fast Iterator hoạt động như thế nào? Cách xử lý ConcurrentModificationException?

**Đáp án:**

### Fail-fast Mechanism

**Cách hoạt động:**
- Collection có biến `modCount` (modification count)
- Iterator có biến `expectedModCount`
- Mỗi khi modify collection → `modCount++`
- Iterator check: nếu `modCount != expectedModCount` → throw exception

```java
List<String> list = new ArrayList<>(Arrays.asList("A", "B", "C", "D"));

// ❌ SAI - ConcurrentModificationException
for (String item : list) {
    if (item.equals("B")) {
        list.remove(item); // Modify collection trong khi iterate
    }
}
```

### 3 Cách xử lý:

**Cách 1: Dùng Iterator.remove() ✅**
```java
Iterator<String> it = list.iterator();
while (it.hasNext()) {
    String item = it.next();
    if (item.equals("B")) {
        it.remove(); // OK - Dùng iterator.remove()
    }
}
```

**Cách 2: Dùng removeIf() (Java 8+) ✅**
```java
list.removeIf(item -> item.equals("B"));
```

**Cách 3: Collect indices rồi remove (Backward) ✅**
```java
for (int i = list.size() - 1; i >= 0; i--) {
    if (list.get(i).equals("B")) {
        list.remove(i);
    }
}
```

**Tại sao Iterator.remove() không throw exception?**
- Iterator.remove() update cả `modCount` VÀ `expectedModCount`
- Giữ sync giữa collection và iterator

---

## Câu 3: Comparator với Double - Tại sao không nên dùng subtraction?

**Đáp án:**

### Vấn đề với Subtraction

```java
// ❌ SAI - Precision loss
class PriceComparator implements Comparator<Product> {
    @Override
    public int compare(Product p1, Product p2) {
        return (int)(p1.price - p2.price); // BUG!
    }
}
```

**Vấn đề:**
1. **Precision loss:**
   - `p1.price = 10.9`, `p2.price = 10.1`
   - `10.9 - 10.1 = 0.8`
   - `(int)0.8 = 0` → Xem như bằng nhau!

2. **Wrong order:**
   - `p1.price = 5.7`, `p2.price = 5.2`
   - `5.7 - 5.2 = 0.5`
   - `(int)0.5 = 0` → Wrong!

3. **Overflow (với Long):**
   - `Long.MAX_VALUE - Long.MIN_VALUE` → Overflow

### Cách đúng:

**Cách 1: Dùng Double.compare() ✅**
```java
class PriceComparator implements Comparator<Product> {
    @Override
    public int compare(Product p1, Product p2) {
        return Double.compare(p1.price, p2.price); // ĐÚNG!
    }
}
```

**Cách 2: Dùng Comparator.comparing() (Java 8+) ✅**
```java
products.sort(Comparator.comparing(Product::getPrice));
```

**Cách 3: Dùng compareTo() cho wrapper class ✅**
```java
return Double.valueOf(p1.price).compareTo(Double.valueOf(p2.price));
```

**Tại sao Double.compare() đúng?**
```java
// Implementation của Double.compare()
public static int compare(double d1, double d2) {
    if (d1 < d2) return -1;
    if (d1 > d2) return 1;
    // Handle NaN, +0.0, -0.0
    return 0;
}
```

**Quy tắc:**
- **int, short, byte:** Có thể dùng subtraction (nếu chắc không overflow)
- **long, float, double:** PHẢI dùng `.compare()` hoặc `.compareTo()`

---

## Câu 4: Advanced Map Operations - merge(), computeIfAbsent(), compute()?

**Đáp án:**

### 1. merge() - Merge 2 maps với logic custom

**Signature:**
```java
V merge(K key, V value, BiFunction<V, V, V> remappingFunction)
```

**Use case: Cộng values của 2 maps**
```java
Map<String, Integer> map1 = new HashMap<>();
map1.put("A", 10);
map1.put("B", 20);

Map<String, Integer> map2 = new HashMap<>();
map2.put("B", 5);
map2.put("C", 15);

// Merge map2 vào map1, cộng values nếu key trùng
map2.forEach((k, v) -> map1.merge(k, v, Integer::sum));

// Result: {A=10, B=25, C=15}
```

**Cách hoạt động:**
- Nếu key chưa tồn tại: put(key, value)
- Nếu key đã tồn tại: put(key, remappingFunction(oldValue, newValue))

### 2. computeIfAbsent() - Chỉ compute nếu key chưa có

**Signature:**
```java
V computeIfAbsent(K key, Function<K, V> mappingFunction)
```

**Use case: Nested collections**
```java
Map<String, List<Integer>> map = new HashMap<>();

// ❌ SAI - NullPointerException
map.get("A").add(1);

// ❌ Dài dòng
if (!map.containsKey("A")) {
    map.put("A", new ArrayList<>());
}
map.get("A").add(1);

// ✅ ĐÚNG - Clean & concise
map.computeIfAbsent("A", k -> new ArrayList<>()).add(1);
```

**Ví dụ khác: Word index**
```java
Map<String, List<Integer>> wordIndex = new HashMap<>();
String[] words = {"java", "python", "java", "go", "python"};

for (int i = 0; i < words.length; i++) {
    wordIndex.computeIfAbsent(words[i], k -> new ArrayList<>()).add(i);
}
// Result: {java=[0,2], python=[1,4], go=[3]}
```

### 3. computeIfPresent() - Chỉ compute nếu key đã có

**Signature:**
```java
V computeIfPresent(K key, BiFunction<K, V, V> remappingFunction)
```

**Use case: Update existing values**
```java
Map<String, Integer> scores = new HashMap<>();
scores.put("Alice", 80);
scores.put("Bob", 90);

// Tăng điểm Alice lên 10%
scores.computeIfPresent("Alice", (k, v) -> v + 10);
// Alice = 90

// Charlie không tồn tại → không làm gì
scores.computeIfPresent("Charlie", (k, v) -> v + 10);
// Charlie = null (không được thêm)
```

### 4. compute() - Compute bất kể key có hay không

```java
V compute(K key, BiFunction<K, V, V> remappingFunction)
```

**Use case: Toggle boolean**
```java
Map<String, Boolean> flags = new HashMap<>();
flags.put("debug", true);

// Toggle debug flag
flags.compute("debug", (k, v) -> v == null ? true : !v);
```

### So sánh:

| Method | Key không tồn tại | Key tồn tại | Return |
|--------|-------------------|-------------|--------|
| `merge()` | Put value | Apply function | New value |
| `computeIfAbsent()` | Compute & put | Không làm gì | Existing value |
| `computeIfPresent()` | Không làm gì | Compute & update | New value |
| `compute()` | Compute & put | Compute & update | New value |

---

## Câu 5: HashMap vs ConcurrentHashMap - Race conditions trong multi-threading?

**Đáp án:**

### Vấn đề với HashMap trong multi-threading

**Test case:**
```java
Map<Integer, Integer> hashMap = new HashMap<>();
int NUM_THREADS = 100;
int OPERATIONS = 1000;

for (int i = 0; i < NUM_THREADS; i++) {
    new Thread(() -> {
        for (int j = 0; j < OPERATIONS; j++) {
            hashMap.put(j, j);
        }
    }).start();
}

// Expected size: 1000
// Actual size: ???
```

**Kết quả có thể xảy ra:**
1. ❌ **ConcurrentModificationException**
2. ❌ **Size != 1000** (ví dụ: 987, 1024, 1122...)
3. ❌ **Infinite loop** trong get() (circular linked list)
4. ❌ **Program hang** hoặc crash

**Nguyên nhân:**

**1. Race condition trong size counter:**
```java
// Thread A và Thread B cùng put
// Thread A: read size = 10, put entry, write size = 11
// Thread B: read size = 10, put entry, write size = 11
// Expected: 12, Actual: 11 → Lost update!
```

**2. Internal array corruption:**
- HashMap dùng array + linked list/tree
- Multiple threads cùng resize array → pointers conflict
- Tạo circular linked list → infinite loop khi get()

**3. Lost entries:**
- Thread A và Thread B cùng put vào cùng bucket
- Cả 2 đều nghĩ bucket rỗng
- Entry của 1 thread bị overwrite

### ConcurrentHashMap giải quyết như thế nào?

**Java 8+ Mechanism:**

**1. Node locking (Lock striping):**
- Chỉ lock bucket đang modify, không lock toàn bộ map
- Multiple threads có thể modify different buckets đồng thời
- Performance tốt hơn synchronized map

**2. CAS (Compare-And-Swap) operations:**
```java
// Pseudo code
do {
    oldValue = get(key);
    newValue = compute(oldValue);
} while (!compareAndSwap(key, oldValue, newValue));
```
- Atomic operations ở CPU level
- Không cần lock cho read operations

**3. Safe iteration:**
- Weakly consistent iterator (không fail-fast)
- Có thể iterate trong khi other threads modify
- May not reflect latest updates, nhưng không throw exception

**4. Atomic methods:**
```java
// Atomic operations
concurrentMap.putIfAbsent("key", 1);
concurrentMap.replace("key", 1, 2);
concurrentMap.computeIfAbsent("key", k -> expensiveComputation());
```

### So sánh Performance:

| Scenario | HashMap | Hashtable | ConcurrentHashMap |
|----------|---------|-----------|-------------------|
| Single thread read | Fastest | Slow (lock) | Fast |
| Single thread write | Fastest | Slow (lock) | Fast |
| Multi-thread read | ❌ Unsafe | Slow (lock all) | Fast (no lock) |
| Multi-thread write | ❌ Corrupt | Very slow (lock all) | Fast (lock bucket) |
| Mixed read/write | ❌ Unsafe | Slow | Fast |

### Best Practices:

**Khi nào dùng HashMap:**
- ✅ Single-threaded application
- ✅ Local variables (thread-confined)
- ✅ Read-only maps (after initialization)

**Khi nào dùng ConcurrentHashMap:**
- ✅ Multi-threaded application
- ✅ Shared mutable state
- ✅ High concurrency scenarios
- ✅ Thay thế Hashtable (legacy)

**Khi nào dùng Collections.synchronizedMap:**
- ✅ Need null keys/values
- ✅ Low concurrency
- ⚠️ Must manually synchronize iteration:
```java
Map<K, V> syncMap = Collections.synchronizedMap(new HashMap<>());
synchronized(syncMap) {
    for (Map.Entry<K, V> entry : syncMap.entrySet()) {
        // Iterate
    }
}
```

---

## Câu 6: Nested Collections - Cấu trúc dữ liệu phức tạp?

**Đáp án:**

### Nested Collections Pattern

**Use case: GradeBook system**
```
Student → Subjects → Scores
Alice → [Math: 90, Physics: 85]
Bob → [Math: 80, Chemistry: 92]
```

**Cấu trúc:**
```java
Map<String, List<Map<String, Integer>>> grades;
// String: Student name
// List: List of subjects
// Map: {Subject: Score}
```

### Implementation Patterns:

**1. Initialization:**
```java
Map<String, List<Map<String, Integer>>> grades = new HashMap<>();
```

**2. Add entry - Cách 1 (Verbose):**
```java
public void addGrade(String student, String subject, int score) {
    // Check nếu student chưa có
    if (!grades.containsKey(student)) {
        grades.put(student, new ArrayList<>());
    }

    // Thêm subject score
    grades.get(student).add(Map.of(subject, score));
}
```

**3. Add entry - Cách 2 (Concise) ✅:**
```java
public void addGrade(String student, String subject, int score) {
    grades.computeIfAbsent(student, k -> new ArrayList<>())
          .add(Map.of(subject, score));
}
```

**4. Get specific grade:**
```java
public int getGrade(String student, String subject) {
    return grades.get(student).stream()
        .filter(map -> map.containsKey(subject))
        .findFirst()
        .map(map -> map.get(subject))
        .orElse(0); // hoặc throw exception
}
```

**5. Calculate average:**
```java
public double getAverageForStudent(String student) {
    return grades.get(student).stream()
        .flatMap(map -> map.values().stream())
        .mapToInt(Integer::intValue)
        .average()
        .orElse(0.0);
}
```

**6. Get all students with score > threshold in a subject:**
```java
public List<String> getStudentsAboveThreshold(String subject, int threshold) {
    return grades.entrySet().stream()
        .filter(entry -> entry.getValue().stream()
            .anyMatch(map -> map.getOrDefault(subject, 0) > threshold))
        .map(Map.Entry::getKey)
        .collect(Collectors.toList());
}
```

### Alternative Structures:

**Option 1: Flatten structure**
```java
// Better cho simple queries
Map<String, Map<String, Integer>> grades;
// Alice → {Math: 90, Physics: 85}

grades.computeIfAbsent("Alice", k -> new HashMap<>())
      .put("Math", 90);

int score = grades.get("Alice").get("Math");
```

**Option 2: Custom class (Object-Oriented)**
```java
class GradeBook {
    private Map<String, Student> students = new HashMap<>();
}

class Student {
    private String name;
    private Map<String, Integer> grades = new HashMap<>();

    public void addGrade(String subject, int score) {
        grades.put(subject, score);
    }

    public double getAverage() {
        return grades.values().stream()
            .mapToInt(Integer::intValue)
            .average()
            .orElse(0.0);
    }
}

// Usage - Much cleaner!
GradeBook gb = new GradeBook();
Student alice = gb.getOrCreateStudent("Alice");
alice.addGrade("Math", 90);
```

**Khi nào dùng Nested Collections:**
- ✅ Quick prototyping
- ✅ Dynamic structure (không biết trước schema)
- ✅ Deserialization từ JSON

**Khi nào dùng Custom Classes:**
- ✅ Production code
- ✅ Complex business logic
- ✅ Need type safety
- ✅ Better maintainability

---

## Câu 7: Set Operations - Union, Intersection, Difference?

**Đáp án:**

### Set Theory Operations

**1. Union (Hợp): A ∪ B**
- Tất cả elements từ cả 2 sets
```java
public static Set<Integer> union(Set<Integer> set1, Set<Integer> set2) {
    Set<Integer> result = new HashSet<>(set1); // ⚠️ Create copy!
    result.addAll(set2);
    return result;
}

// Example:
// A = {1, 2, 3}
// B = {3, 4, 5}
// A ∪ B = {1, 2, 3, 4, 5}
```

**2. Intersection (Giao): A ∩ B**
- Chỉ elements có trong CẢ 2 sets
```java
public static Set<Integer> intersection(Set<Integer> set1, Set<Integer> set2) {
    Set<Integer> result = new HashSet<>(set1); // ⚠️ Create copy!
    result.retainAll(set2);
    return result;
}

// Example:
// A = {1, 2, 3}
// B = {2, 3, 4}
// A ∩ B = {2, 3}
```

**3. Difference (Hiệu): A - B**
- Elements trong A nhưng KHÔNG trong B
```java
public static Set<Integer> difference(Set<Integer> set1, Set<Integer> set2) {
    Set<Integer> result = new HashSet<>(set1); // ⚠️ Create copy!
    result.removeAll(set2);
    return result;
}

// Example:
// A = {1, 2, 3, 4}
// B = {3, 4, 5}
// A - B = {1, 2}
```

**4. Symmetric Difference: (A - B) ∪ (B - A)**
- Elements trong A hoặc B nhưng KHÔNG trong cả 2
```java
public static Set<Integer> symmetricDifference(Set<Integer> set1, Set<Integer> set2) {
    Set<Integer> result = new HashSet<>(set1);
    for (Integer element : set2) {
        if (!result.add(element)) {
            result.remove(element); // Đã có thì remove
        }
    }
    return result;
}

// Example:
// A = {1, 2, 3}
// B = {2, 3, 4}
// (A - B) ∪ (B - A) = {1, 4}
```

### ⚠️ Critical Mistake - Không tạo copy:

```java
// ❌ SAI - Modify input set
public static Set<Integer> union(Set<Integer> set1, Set<Integer> set2) {
    set1.addAll(set2); // Modify set1!
    return set1;
}

// Test:
Set<Integer> a = new HashSet<>(Arrays.asList(1, 2, 3));
Set<Integer> b = new HashSet<>(Arrays.asList(3, 4, 5));
Set<Integer> result = union(a, b);
System.out.println(a); // {1, 2, 3, 4, 5} - Đã bị modify!
```

**Quy tắc:** Luôn tạo copy mới, không modify input parameters!

### Stream API approach:

```java
// Union
Set<Integer> union = Stream.concat(set1.stream(), set2.stream())
    .collect(Collectors.toSet());

// Intersection
Set<Integer> intersection = set1.stream()
    .filter(set2::contains)
    .collect(Collectors.toSet());

// Difference
Set<Integer> difference = set1.stream()
    .filter(e -> !set2.contains(e))
    .collect(Collectors.toSet());
```

---

## Câu 8: Performance Testing - Đo đạc chính xác performance?

**Đáp án:**

### Cách đo performance đúng:

**1. Sử dụng System.nanoTime() (không dùng currentTimeMillis)**
```java
long startTime = System.nanoTime();
// Code to test
long endTime = System.nanoTime();
long duration = endTime - startTime;
System.out.println("Duration: " + duration + " ns");
```

**Tại sao dùng nanoTime()?**
- `currentTimeMillis()`: Wall-clock time, có thể bị system clock adjustment
- `nanoTime()`: Monotonic time, không bị ảnh hưởng bởi system clock

**2. Warm-up JVM trước khi test:**
```java
// Warm-up
for (int i = 0; i < 10000; i++) {
    list.add(i);
}

// Real test
long start = System.nanoTime();
for (int i = 0; i < 100000; i++) {
    list.add(i);
}
long end = System.nanoTime();
```

**3. Test nhiều lần và tính trung bình:**
```java
long totalTime = 0;
int iterations = 10;

for (int i = 0; i < iterations; i++) {
    long start = System.nanoTime();
    // Test code
    long end = System.nanoTime();
    totalTime += (end - start);
}

long avgTime = totalTime / iterations;
System.out.println("Average: " + avgTime + " ns");
```

**4. Test với different sizes:**
```java
int[] sizes = {1000, 10000, 100000, 1000000};
for (int size : sizes) {
    long duration = testOperation(size);
    System.out.println("Size: " + size + ", Time: " + duration + " ns");
}
```

### ArrayList vs LinkedList Performance Example:

```java
public static void compareAddLast(int n) {
    // ArrayList
    List<Integer> arrayList = new ArrayList<>();
    long start1 = System.nanoTime();
    for (int i = 0; i < n; i++) {
        arrayList.add(i);
    }
    long end1 = System.nanoTime();

    // LinkedList
    List<Integer> linkedList = new LinkedList<>();
    long start2 = System.nanoTime();
    for (int i = 0; i < n; i++) {
        linkedList.add(i);
    }
    long end2 = System.nanoTime();

    System.out.println("ArrayList addLast: " + (end1 - start1) + " ns");
    System.out.println("LinkedList addLast: " + (end2 - start2) + " ns");

    // Calculate winner
    String winner = (end1 - start1) < (end2 - start2) ? "ArrayList" : "LinkedList";
    System.out.println("Winner: " + winner);
}
```

**Expected Results:**
- **Add at end**: ArrayList ≈ LinkedList (both O(1))
- **Add at beginning**: LinkedList >> ArrayList (O(1) vs O(n))
- **Get by index**: ArrayList >> LinkedList (O(1) vs O(n))

---

## Câu 9: Thread.join() - Tại sao cần chờ threads?

**Đáp án:**

### Vấn đề khi không dùng join():

```java
// ❌ SAI - Race condition
Map<Integer, Integer> map = new HashMap<>();

for (int i = 0; i < 10; i++) {
    new Thread(() -> {
        map.put(1, 1); // Takes time
    }).start();
}

System.out.println("Size: " + map.size()); // In ngay lập tức
// Output: 0 hoặc 1 (threads chưa chạy xong!)
```

**Vấn đề:**
- Main thread không đợi worker threads
- In kết quả trước khi threads hoàn thành
- Kết quả sai!

### Giải pháp: Dùng thread.join()

```java
// ✅ ĐÚNG
Map<Integer, Integer> map = new HashMap<>();
List<Thread> threads = new ArrayList<>();

// Start all threads
for (int i = 0; i < 10; i++) {
    Thread t = new Thread(() -> {
        map.put(1, 1);
    });
    t.start();
    threads.add(t);
}

// Wait for all threads to finish
try {
    for (Thread t : threads) {
        t.join(); // Block until thread finishes
    }
} catch (InterruptedException e) {
    e.printStackTrace();
}

// Now safe to check result
System.out.println("Size: " + map.size()); // Đúng!
```

**join() hoạt động thế nào?**
- Main thread gọi `workerThread.join()`
- Main thread BLOCK cho đến khi workerThread finish
- Sau đó main thread tiếp tục

**Visualization:**
```
Main:    [Start threads] ──┐
                            │ join()
Thread1:     [────────────] ┘
Thread2:       [──────────] ┘
Thread3:         [────────] ┘
                            │
Main:                       └──> [Check results]
```

### join() with timeout:

```java
// Wait maximum 1000ms
thread.join(1000);

// Check nếu thread còn chạy
if (thread.isAlive()) {
    System.out.println("Thread timeout!");
    thread.interrupt(); // Request stop
}
```

### Alternative: CountDownLatch

```java
CountDownLatch latch = new CountDownLatch(10);

for (int i = 0; i < 10; i++) {
    new Thread(() -> {
        try {
            map.put(1, 1);
        } finally {
            latch.countDown();
        }
    }).start();
}

latch.await(); // Wait for count to reach 0
System.out.println("All threads done!");
```

---

## Câu 10: Best Practices - Tổng hợp kinh nghiệm thực tế?

**Đáp án:**

### 1. Choosing the Right Collection

```java
// ✅ ĐÚNG - Choose based on use case
// Need random access? → ArrayList
List<String> names = new ArrayList<>();

// Need frequent insert/delete at beginning? → LinkedList
List<String> queue = new LinkedList<>();

// Need unique elements? → HashSet
Set<String> uniqueNames = new HashSet<>();

// Need sorted unique elements? → TreeSet
Set<String> sortedNames = new TreeSet<>();

// Need key-value pairs? → HashMap
Map<String, Integer> ages = new HashMap<>();
```

### 2. Specify Initial Capacity

```java
// ❌ SAI - Multiple resizes
List<Integer> list = new ArrayList<>();
for (int i = 0; i < 10000; i++) {
    list.add(i); // Resize multiple times: 10→20→40→80→...
}

// ✅ ĐÚNG - Avoid resizes
List<Integer> list = new ArrayList<>(10000);
for (int i = 0; i < 10000; i++) {
    list.add(i); // No resize needed
}
```

### 3. Immutable Collections

```java
// ❌ SAI - Mutable collection exposed
public class Config {
    private List<String> allowedUsers = new ArrayList<>();

    public List<String> getAllowedUsers() {
        return allowedUsers; // Caller có thể modify!
    }
}

// ✅ ĐÚNG - Return unmodifiable view
public List<String> getAllowedUsers() {
    return Collections.unmodifiableList(allowedUsers);
}

// ✅ ĐÚNG - Return copy
public List<String> getAllowedUsers() {
    return new ArrayList<>(allowedUsers);
}

// ✅ BEST - Java 9+ Immutable
List<String> list = List.of("A", "B", "C"); // Immutable
Set<String> set = Set.of("A", "B", "C");
Map<String, Integer> map = Map.of("A", 1, "B", 2);
```

### 4. Avoid null in Collections

```java
// ❌ SAI - null values cause problems
List<String> names = new ArrayList<>();
names.add(null);
names.add("Alice");

for (String name : names) {
    System.out.println(name.toUpperCase()); // NullPointerException!
}

// ✅ ĐÚNG - Use Optional
List<Optional<String>> names = new ArrayList<>();
names.add(Optional.empty());
names.add(Optional.of("Alice"));

for (Optional<String> name : names) {
    System.out.println(name.orElse("Unknown").toUpperCase());
}

// ✅ ĐÚNG - Filter nulls
List<String> validNames = names.stream()
    .filter(Objects::nonNull)
    .collect(Collectors.toList());
```

### 5. Use Appropriate Iterator

```java
// ❌ CHẬM - Get by index in LinkedList
LinkedList<String> list = new LinkedList<>();
for (int i = 0; i < list.size(); i++) { // O(n²)
    System.out.println(list.get(i)); // O(n) each call
}

// ✅ NHANH - Use iterator
for (String item : list) { // O(n)
    System.out.println(item);
}
```

### 6. Clear vs New

```java
// Option 1: Clear existing
list.clear(); // O(n), keeps capacity

// Option 2: Create new
list = new ArrayList<>(); // O(1), loses capacity

// Best practice: Clear if reusing, New if different size expected
```

### 7. contains() Performance

```java
// ❌ CHẬM - O(n) for List
List<String> list = new ArrayList<>(Arrays.asList("A", "B", "C"));
if (list.contains("B")) { } // O(n)

// ✅ NHANH - O(1) for Set
Set<String> set = new HashSet<>(Arrays.asList("A", "B", "C"));
if (set.contains("B")) { } // O(1)

// Rule: If you need frequent contains() → Use Set!
```

### 8. Remove while iterating

```java
// ❌ SAI
for (String item : list) {
    if (condition) {
        list.remove(item); // ConcurrentModificationException
    }
}

// ✅ ĐÚNG - Option 1: Iterator
Iterator<String> it = list.iterator();
while (it.hasNext()) {
    if (condition) {
        it.remove();
    }
}

// ✅ ĐÚNG - Option 2: removeIf
list.removeIf(item -> condition);

// ✅ ĐÚNG - Option 3: Stream
list = list.stream()
    .filter(item -> !condition)
    .collect(Collectors.toList());
```

### 9. Concurrent Collections

```java
// ❌ SAI - HashMap in multi-threading
Map<String, Integer> map = new HashMap<>(); // Not thread-safe

// ❌ SAI - Synchronized wrapper (slow)
Map<String, Integer> map = Collections.synchronizedMap(new HashMap<>());

// ✅ ĐÚNG - ConcurrentHashMap
Map<String, Integer> map = new ConcurrentHashMap<>();
```

### 10. equals() and hashCode() Contract

```java
// ❌ SAI - Override equals() but not hashCode()
class Student {
    private int id;

    @Override
    public boolean equals(Object obj) {
        return this.id == ((Student) obj).id;
    }
    // hashCode() not overridden → Different objects considered different in HashMap
}

// ✅ ĐÚNG - Override both
class Student {
    private int id;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Student)) return false;
        return this.id == ((Student) obj).id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
```

**Contract rules:**
1. If `a.equals(b)` → `a.hashCode() == b.hashCode()`
2. If `a.hashCode() != b.hashCode()` → `!a.equals(b)`
3. equals() phải: reflexive, symmetric, transitive, consistent

---

## Tổng kết

File này cover các chủ đề nâng cao:
- ✅ PriorityQueue & Heap algorithms
- ✅ Fail-fast Iterator & ConcurrentModificationException
- ✅ Comparator best practices với Double.compare()
- ✅ Advanced Map operations (merge, computeIfAbsent)
- ✅ Multi-threading với HashMap vs ConcurrentHashMap
- ✅ Nested Collections patterns
- ✅ Set theory operations
- ✅ Performance testing techniques
- ✅ Thread synchronization với join()
- ✅ Production-ready best practices

**Khi nào đọc file này:**
- Chuẩn bị phỏng vấn senior/mid-level
- Đã master file `02-Collections-Questions.md`
- Cần hiểu sâu về Collections internals
- Làm việc với multi-threading applications
