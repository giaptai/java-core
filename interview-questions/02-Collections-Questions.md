# Câu hỏi phỏng vấn về Collections Framework

## Câu 1: Java Collections Framework là gì? Hierarchy của nó như thế nào?

**Đáp án:**

Java Collections Framework là một kiến trúc thống nhất để lưu trữ và thao tác với nhóm các object.

### Hierarchy chính:

```
Collection (Interface)
├── List (Interface)
│   ├── ArrayList
│   ├── LinkedList
│   └── Vector
│       └── Stack
├── Set (Interface)
│   ├── HashSet
│   ├── LinkedHashSet
│   └── TreeSet
└── Queue (Interface)
    ├── PriorityQueue
    └── Deque (Interface)
        └── ArrayDeque

Map (Interface - không kế thừa Collection)
├── HashMap
├── LinkedHashMap
├── TreeMap
└── Hashtable
```

**Các interface chính:**
- **Collection**: Interface gốc
- **List**: Danh sách có thứ tự, cho phép duplicate
- **Set**: Không cho phép duplicate
- **Queue**: FIFO (First In First Out)
- **Map**: Key-Value pairs, key không duplicate

---

## Câu 2: Phân biệt ArrayList và LinkedList?

**Đáp án:**

| Tiêu chí | ArrayList | LinkedList |
|----------|-----------|------------|
| Cấu trúc | Dynamic array | Doubly linked list |
| Random Access | O(1) - Nhanh | O(n) - Chậm |
| Insert/Delete đầu/giữa | O(n) - Chậm (phải shift) | O(1) - Nhanh |
| Insert/Delete cuối | O(1) - Nhanh | O(1) - Nhanh |
| Memory | Ít hơn (chỉ lưu data) | Nhiều hơn (lưu data + 2 pointers) |
| Truy cập | Dùng index | Duyệt từ head/tail |
| Implement | List | List, Deque, Queue |

**Khi nào dùng:**
- **ArrayList**: Truy cập ngẫu nhiên nhiều, ít thêm/xóa
- **LinkedList**: Thêm/xóa ở đầu/giữa nhiều, ít truy cập ngẫu nhiên

```java
// ArrayList
List<String> arrayList = new ArrayList<>();
arrayList.add("Java");      // O(1)
arrayList.get(0);           // O(1) - Nhanh
arrayList.add(0, "Python"); // O(n) - Chậm (shift elements)

// LinkedList
List<String> linkedList = new LinkedList<>();
linkedList.add("Java");     // O(1)
linkedList.get(0);          // O(n) - Chậm
linkedList.add(0, "Python");// O(1) - Nhanh
```

---

## Câu 3: Phân biệt HashSet, LinkedHashSet và TreeSet?

**Đáp án:**

| Tiêu chí | HashSet | LinkedHashSet | TreeSet |
|----------|---------|---------------|---------|
| Thứ tự | Không đảm bảo | Theo thứ tự insert | Sắp xếp (natural/comparator) |
| Cấu trúc | Hash table | Hash table + Linked list | Red-Black Tree |
| Performance | O(1) add, remove, contains | O(1) nhưng chậm hơn HashSet | O(log n) |
| Null | Cho phép 1 null | Cho phép 1 null | Không cho phép null |
| Memory | Ít nhất | Trung bình | Nhiều nhất |

```java
// HashSet - Không thứ tự
Set<Integer> hashSet = new HashSet<>();
hashSet.add(3);
hashSet.add(1);
hashSet.add(2);
System.out.println(hashSet); // [1, 2, 3] hoặc [3, 1, 2] - không đảm bảo

// LinkedHashSet - Giữ thứ tự insert
Set<Integer> linkedHashSet = new LinkedHashSet<>();
linkedHashSet.add(3);
linkedHashSet.add(1);
linkedHashSet.add(2);
System.out.println(linkedHashSet); // [3, 1, 2] - giữ thứ tự

// TreeSet - Tự động sắp xếp
Set<Integer> treeSet = new TreeSet<>();
treeSet.add(3);
treeSet.add(1);
treeSet.add(2);
System.out.println(treeSet); // [1, 2, 3] - sorted
```

---

## Câu 4: HashMap hoạt động như thế nào?

**Đáp án:**

### Cấu trúc nội bộ:
- Array of buckets (mỗi bucket là LinkedList hoặc Tree)
- Mỗi entry chứa: key, value, hash, next

### Cách hoạt động:

1. **PUT operation:**
```java
map.put(key, value);
```
   - Tính hashCode() của key
   - Tính index: `index = hash(key) & (n-1)` (n = array length)
   - Nếu bucket rỗng: thêm entry mới
   - Nếu bucket có sẵn:
     - Key trùng → update value
     - Key khác → thêm vào LinkedList (collision)
   - Nếu LinkedList > 8 phần tử → chuyển thành Tree (Java 8+)

2. **GET operation:**
```java
map.get(key);
```
   - Tính hashCode() của key
   - Tìm bucket tương ứng
   - Duyệt LinkedList/Tree để tìm key bằng equals()

### Collision Resolution:
- **Java 7**: Chaining với LinkedList
- **Java 8+**: LinkedList → Tree khi size > 8

### Load Factor & Capacity:
- **Initial Capacity**: 16 (default)
- **Load Factor**: 0.75 (default)
- **Threshold**: capacity × load factor
- Khi size > threshold → resize (double capacity) → rehashing

```java
// HashMap với custom capacity và load factor
Map<String, Integer> map = new HashMap<>(32, 0.8f);

// Tại sao override hashCode() và equals()?
class Student {
    private int id;
    private String name;

    @Override
    public int hashCode() {
        return Objects.hash(id); // Tính hash để tìm bucket
    }

    @Override
    public boolean equals(Object obj) {
        // So sánh trong bucket để tìm chính xác key
        if (this == obj) return true;
        if (!(obj instanceof Student)) return false;
        Student s = (Student) obj;
        return id == s.id;
    }
}
```

---

## Câu 5: Phân biệt HashMap, LinkedHashMap và TreeMap?

**Đáp án:**

| Tiêu chí | HashMap | LinkedHashMap | TreeMap |
|----------|---------|---------------|---------|
| Thứ tự | Không đảm bảo | Theo insert order | Sorted (natural/comparator) |
| Cấu trúc | Hash table | Hash table + Doubly-linked list | Red-Black Tree |
| Performance | O(1) get, put | O(1) nhưng chậm hơn HashMap | O(log n) |
| Null key | 1 null key | 1 null key | Không cho phép null key |
| Synchronization | Không synchronized | Không synchronized | Không synchronized |

```java
// HashMap
Map<Integer, String> hashMap = new HashMap<>();
hashMap.put(3, "Three");
hashMap.put(1, "One");
hashMap.put(2, "Two");
// Output: Không đảm bảo thứ tự

// LinkedHashMap - Giữ thứ tự insertion
Map<Integer, String> linkedMap = new LinkedHashMap<>();
linkedMap.put(3, "Three");
linkedMap.put(1, "One");
linkedMap.put(2, "Two");
// Output: {3=Three, 1=One, 2=Two}

// TreeMap - Tự động sort theo key
Map<Integer, String> treeMap = new TreeMap<>();
treeMap.put(3, "Three");
treeMap.put(1, "One");
treeMap.put(2, "Two");
// Output: {1=One, 2=Two, 3=Three}
```

---

## Câu 6: Phân biệt HashMap và Hashtable?

**Đáp án:**

| Tiêu chí | HashMap | Hashtable |
|----------|---------|-----------|
| Thread-safe | Không | Có (synchronized) |
| Performance | Nhanh hơn | Chậm hơn |
| Null key/value | Cho phép 1 null key, nhiều null values | Không cho phép |
| Iterator | Fail-fast | Fail-safe (Enumeration) |
| Legacy | Không | Legacy class (từ Java 1.0) |
| Recommended | Dùng cho single-thread | Dùng ConcurrentHashMap thay thế |

```java
// HashMap - Not thread-safe
Map<String, Integer> hashMap = new HashMap<>();
hashMap.put(null, 1);        // OK
hashMap.put("key", null);    // OK

// Hashtable - Thread-safe
Map<String, Integer> hashtable = new Hashtable<>();
// hashtable.put(null, 1);   // NullPointerException
// hashtable.put("key", null); // NullPointerException

// Better alternative for thread-safety
Map<String, Integer> concurrentMap = new ConcurrentHashMap<>();
```

---

## Câu 7: Fail-fast và Fail-safe Iterator là gì?

**Đáp án:**

### Fail-fast Iterator
- Ném `ConcurrentModificationException` nếu collection bị modify trong khi iterate
- Dùng cho: ArrayList, HashMap, HashSet
```java
List<String> list = new ArrayList<>(Arrays.asList("A", "B", "C"));

for (String item : list) {
    if (item.equals("B")) {
        list.remove(item); // ConcurrentModificationException
    }
}

// Cách fix: Dùng Iterator
Iterator<String> it = list.iterator();
while (it.hasNext()) {
    String item = it.next();
    if (item.equals("B")) {
        it.remove(); // OK - Dùng iterator.remove()
    }
}
```

### Fail-safe Iterator
- Làm việc trên copy của collection, không ném exception
- Dùng cho: ConcurrentHashMap, CopyOnWriteArrayList
```java
CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>(
    Arrays.asList("A", "B", "C")
);

for (String item : list) {
    if (item.equals("B")) {
        list.remove(item); // OK - Không exception
    }
}
```

---

## Câu 8: Comparable và Comparator khác nhau như thế nào?

**Đáp án:**

| Tiêu chí | Comparable | Comparator |
|----------|------------|------------|
| Package | java.lang | java.util |
| Method | compareTo(T o) | compare(T o1, T o2) |
| Vị trí | Trong class cần sort | Class riêng biệt |
| Số cách sort | 1 (natural ordering) | Nhiều cách |
| Sửa đổi class | Phải sửa class gốc | Không cần sửa |

### Comparable - Natural Ordering
```java
public class Student implements Comparable<Student> {
    private int id;
    private String name;

    @Override
    public int compareTo(Student other) {
        return this.id - other.id; // Sort theo id
    }
}

// Sử dụng
List<Student> students = new ArrayList<>();
Collections.sort(students); // Dùng compareTo()
```

### Comparator - Custom Ordering
```java
// Comparator 1: Sort theo tên
class NameComparator implements Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2) {
        return s1.getName().compareTo(s2.getName());
    }
}

// Comparator 2: Sort theo tuổi
class AgeComparator implements Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2) {
        return s1.getAge() - s2.getAge();
    }
}

// Sử dụng
Collections.sort(students, new NameComparator());
Collections.sort(students, new AgeComparator());

// Java 8: Lambda
students.sort((s1, s2) -> s1.getName().compareTo(s2.getName()));
students.sort(Comparator.comparing(Student::getName));
```

---

## Câu 9: Collections và Arrays utility class có những methods gì?

**Đáp án:**

### Collections Class

```java
List<Integer> list = new ArrayList<>(Arrays.asList(3, 1, 4, 1, 5));

// Sorting
Collections.sort(list);                    // [1, 1, 3, 4, 5]
Collections.reverse(list);                 // [5, 4, 3, 1, 1]
Collections.shuffle(list);                 // Random order

// Searching
int index = Collections.binarySearch(list, 3); // Index của 3

// Min/Max
int min = Collections.min(list);           // 1
int max = Collections.max(list);           // 5

// Frequency
int count = Collections.frequency(list, 1); // 2

// Replace
Collections.replaceAll(list, 1, 10);       // Replace tất cả 1 thành 10

// Synchronized/Unmodifiable
List<Integer> syncList = Collections.synchronizedList(list);
List<Integer> unmodList = Collections.unmodifiableList(list);

// Singleton
Set<String> singleton = Collections.singleton("Java");

// Empty collections
List<String> emptyList = Collections.emptyList();
Set<String> emptySet = Collections.emptySet();
Map<String, String> emptyMap = Collections.emptyMap();
```

### Arrays Class

```java
int[] arr = {3, 1, 4, 1, 5};

// Sorting
Arrays.sort(arr);                          // [1, 1, 3, 4, 5]

// Binary Search
int index = Arrays.binarySearch(arr, 3);   // Index của 3

// Fill
Arrays.fill(arr, 0);                       // Fill tất cả = 0

// Compare
int[] arr2 = {1, 1, 3, 4, 5};
boolean equal = Arrays.equals(arr, arr2);  // true

// Copy
int[] copy = Arrays.copyOf(arr, arr.length);
int[] range = Arrays.copyOfRange(arr, 0, 3);

// To List
List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

// To String
String str = Arrays.toString(arr);         // "[1, 1, 3, 4, 5]"

// Stream (Java 8+)
int sum = Arrays.stream(arr).sum();
```

---

## Câu 10: ConcurrentHashMap hoạt động như thế nào?

**Đáp án:**

### Đặc điểm:
- Thread-safe mà không cần synchronize toàn bộ map
- Performance tốt hơn Hashtable
- Không cho phép null key/value

### Cơ chế:

**Java 7:**
- Segment locking (chia thành 16 segments)
- Mỗi segment là 1 HashTable riêng
- Các thread khác nhau lock các segment khác nhau

**Java 8+:**
- Node locking (lock từng bucket thay vì segment)
- CAS (Compare-And-Swap) operations
- Chỉ lock khi cần thiết
- Bucket > 8 → chuyển thành Tree

```java
// ConcurrentHashMap
Map<String, Integer> concurrentMap = new ConcurrentHashMap<>();

// Thread-safe operations
concurrentMap.put("key", 1);
concurrentMap.get("key");
concurrentMap.remove("key");

// Atomic operations
concurrentMap.putIfAbsent("key", 1);
concurrentMap.replace("key", 1, 2);
concurrentMap.computeIfAbsent("key", k -> 1);
concurrentMap.computeIfPresent("key", (k, v) -> v + 1);

// Bulk operations (parallel)
concurrentMap.forEach(1, (k, v) -> System.out.println(k + "=" + v));
```

**So sánh:**
- **Hashtable**: Lock toàn bộ map → Chậm
- **ConcurrentHashMap**: Lock từng bucket → Nhanh hơn
- **Collections.synchronizedMap**: Wrapper với lock → Performance trung bình
