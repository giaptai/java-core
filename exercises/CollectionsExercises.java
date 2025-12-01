import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Iterator;
import java.lang.Thread;

/**
 * BÀI TẬP THỰC HÀNH - COLLECTIONS FRAMEWORK
 *
 * Hướng dẫn:
 * 1. Đọc kỹ yêu cầu từng bài
 * 2. Implement các method với // TODO
 * 3. Uncomment test code trong main() để kiểm tra
 */

public class CollectionsExercises {

    // ===== BÀI 1: ARRAYLIST BASICS =====

    /**
     * Cho một danh sách số nguyên, tìm:
     * - Giá trị lớn nhất
     * - Giá trị nhỏ nhất
     * - Tổng tất cả các số
     * - Trung bình cộng
     */
    public static class ListStats {
        public static int findMax(List<Integer> numbers) {
            // TODO: Implement
            return numbers.stream().mapToInt(Integer::intValue).max().getAsInt();
        }

        public static int findMin(List<Integer> numbers) {
            // TODO: Implement
            return Collections.min(numbers);
        }

        public static int sum(List<Integer> numbers) {
            // TODO: Implement
            return numbers.stream().mapToInt(value -> value.intValue()).sum();
        }

        public static double average(List<Integer> numbers) {
            // TODO: Implement
            return numbers.stream().mapToInt(value -> value.intValue()).average().getAsDouble();
        }
    }

    // ===== BÀI 2: ARRAYLIST VS LINKEDLIST =====

    /**
     * So sánh performance giữa ArrayList và LinkedList:
     * - Add elements vào cuối
     * - Add elements vào đầu
     * - Get element tại index giữa
     * - Remove element tại đầu
     */
    public static class PerformanceComparison {
        public static void compareAddLast(int size) {
            // TODO: So sánh thời gian add vào cuối ArrayList vs LinkedList

            // Array List
            long startTime = System.nanoTime();
            List<Integer> aList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                aList.add(i);
            }
            long arrayTime = System.nanoTime() - startTime;
            // END Array List

            // Linked List
            startTime = System.nanoTime();
            List<Integer> linkedList = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                linkedList.add(i);
            }
            long linkedTime = System.nanoTime() - startTime;
            // END Linked List

            // output
            System.out.println("Add Last:");
            System.out.printf("ArrayList:  %d ms%n", arrayTime / 1_000_000);
            System.out.printf("LinkedList: %d ms%n", linkedTime / 1_000_000);
            System.out.println("Winner: " + (arrayTime < linkedTime ? "ArrayList" : "LinkedList"));
        }

        public static void compareAddFirst(int size) {
            // TODO: So sánh thời gian add vào đầu ArrayList vs LinkedList

            // Array List
            long startTime = System.nanoTime();
            List<Integer> aList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                aList.addFirst(i);
            }
            long arrayTime = System.nanoTime() - startTime;
            // END Array List

            // Linked List
            startTime = System.nanoTime();
            List<Integer> linkedList = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                linkedList.addFirst(i);
            }
            long linkedTime = System.nanoTime() - startTime;
            // END Linked List

            // output
            System.out.println("\nAdd First:");
            System.out.printf("ArrayList:  %d ms%n", arrayTime / 1_000_000);
            System.out.printf("LinkedList: %d ms%n", linkedTime / 1_000_000);
            System.out.println("Winner: " + (arrayTime < linkedTime ? "ArrayList" : "LinkedList"));
        }

        public static void compareRandomAccess(int size) {
            // TODO: So sánh thời gian truy cập ngẫu nhiên
            List<Integer> aList = new ArrayList<>();
            List<Integer> linkedList = new LinkedList<>();

            for (int i = 0; i < size; i++) {
                aList.add(i);
                linkedList.add(i);
            }

            // ArrayList random access
            long startTime = System.nanoTime();
            for (int i = 0; i < size; i++) {
                aList.get(i);
            }
            long arrayTime = System.nanoTime() - startTime;
            // End ArrayList random access

            // LinkedList random access
            startTime = System.nanoTime();
            for (int i = 0; i < size; i++) {
                linkedList.get(i);
            }
            long linkedTime = System.nanoTime() - startTime;
            // end LinkedList random access

            // In kết quả
            System.out.println("\nRandom Access:");
            System.out.printf("ArrayList:  %d ms%n", arrayTime / 1_000_000);
            System.out.printf("LinkedList: %d ms%n", linkedTime / 1_000_000);
            System.out.println("Winner: " + (arrayTime < linkedTime ? "ArrayList" : "LinkedList"));
        }
    }

    // ===== BÀI 3: HASHSET - REMOVE DUPLICATES =====

    /**
     * Cho một List chứa duplicates, loại bỏ duplicates:
     * - Sử dụng HashSet
     * - Giữ nguyên thứ tự ban đầu (dùng LinkedHashSet)
     */
    public static List<Integer> removeDuplicates(List<Integer> list) {
        // TODO: Implement using HashSet
        Set<Integer> set = new HashSet<>(list);
        return set.stream().toList();
    }

    public static List<Integer> removeDuplicatesKeepOrder(List<Integer> list) {
        // TODO: Implement using LinkedHashSet
        Set<Integer> set = new LinkedHashSet<>(list);
        return set.stream().toList();
    }

    // ===== BÀI 4: HASHMAP - WORD FREQUENCY =====

    /**
     * Đếm tần suất xuất hiện của mỗi từ trong một đoạn text
     * Input: "java is great java is fun"
     * Output: {java=2, is=2, great=1, fun=1}
     */
    public static Map<String, Integer> wordFrequency(String text) {
        text.trim();
        String[] listText = text.split(" ");
        // TODO: Implement
        Map<String, Integer> frequency = new HashMap<>();
        for (String t : listText) {
            frequency.put(t, frequency.getOrDefault(t, 0) + 1);
        }
        return frequency;
    }

    // ===== BÀI 5: HASHMAP - GROUP BY =====

    /**
     * Cho danh sách students, nhóm theo grade
     * Input: [(Alice, A), (Bob, B), (Charlie, A)]
     * Output: {A=[Alice, Charlie], B=[Bob]}
     */
    static class StudentRecord {
        String name;
        String grade;

        public StudentRecord(String name, String grade) {
            this.name = name;
            this.grade = grade;
        }
    }

    public static Map<String, List<String>> groupByGrade(List<StudentRecord> students) {
        // TODO: Implement
        Map<String, List<String>> studentGrade = new HashMap<>();
        for (StudentRecord s : students) {
            studentGrade.putIfAbsent(s.grade, new ArrayList<>());
            studentGrade.get(s.grade).add(s.name);
        }
        return studentGrade;
    }

    // ===== BÀI 6: TREEMAP - SORTED MAP =====

    /**
     * Tạo một phonebook (tên -> số điện thoại) được sắp xếp theo tên
     * - addContact(name, phone)
     * - getPhone(name)
     * - getAllContactsSorted()
     */
    public static class PhoneBook {
        // TODO: Sử dụng TreeMap
        private TreeMap<String, String> contacts;

        public PhoneBook() {
            // TODO: Initialize
            this.contacts = new TreeMap<>();
        }

        public void addContact(String name, String phone) {
            // TODO: Implement
            contacts.put(name, phone);
        }

        public String getPhone(String name) {
            // TODO: Implement
            return contacts.get(name);
        }

        public List<String> getAllContactsSorted() {
            // TODO: Trả về tất cả contacts đã sort
            return contacts.keySet().stream().toList();
        }
    }

    // ===== BÀI 7: HASHMAP VS TREEMAP VS LINKEDHASHMAP =====

    /**
     * Demo sự khác biệt về thứ tự giữa các loại Map
     */
    public static void demonstrateMapsOrdering() {
        // TODO: Tạo 3 maps, add cùng data, in ra để thấy sự khác biệt
        Map<String, Integer> map = Map.ofEntries(
                Map.entry("Long", 1),
                Map.entry("Hoai", 25),
                Map.entry("An", 13));
        // HashMap - Không đảm bảo thứ tự
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.putAll(map);
        // LinkedHashMap - Theo thứ tự insert
        LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.putAll(map);
        // TreeMap - Sorted theo key
        TreeMap<String, Integer> treeMap = new TreeMap<>();
        treeMap.putAll(map);

        // output
        System.out.println("HashMap: " + hashMap);
        System.out.println("LinkedHashMap: " + linkedHashMap);
        System.out.println("TreeMap: " + treeMap);
    }

    // ===== BÀI 8: SET OPERATIONS =====

    /**
     * Implement các phép toán tập hợp:
     * - Union (Hợp)
     * - Intersection (Giao)
     * - Difference (Hiệu)
     */
    public static Set<Integer> union(Set<Integer> set1, Set<Integer> set2) {
        // TODO: Implement
        Set<Integer> result = new HashSet<>(set1);
        result.addAll(set2);
        return result;
    }

    public static Set<Integer> intersection(Set<Integer> set1, Set<Integer> set2) {
        // TODO: Implement
        Set<Integer> result = new HashSet<>(set1);
        result.retainAll(set2);
        return result;
    }

    public static Set<Integer> difference(Set<Integer> set1, Set<Integer> set2) {
        // TODO: Implement (set1 - set2)
        Set<Integer> result = new HashSet<>(set1);
        result.removeAll(set2);
        return result;
    }

    // ===== BÀI 9: PRIORITY QUEUE =====

    /**
     * Tìm K số lớn nhất trong một mảng sử dụng PriorityQueue
     * Input: [3, 1, 4, 1, 5, 9, 2, 6], k=3
     * Output: [9, 6, 5]
     */
    public static List<Integer> findKLargest(int[] nums, int k) {
        // TODO: Sử dụng PriorityQueue
        Queue<Integer> pq = new PriorityQueue<>();
        for (int num : nums) {
            pq.offer(num);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        List<Integer> result = new ArrayList<>(pq);
        Collections.sort(result, Collections.reverseOrder());
        return result;
    }

    // ===== BÀI 10: COLLECTIONS UTILITY =====

    /**
     * Sử dụng Collections utility class:
     * - Sort một list (tăng dần và giảm dần)
     * - Shuffle một list
     * - Tìm min/max
     * - Binary search
     * - Reverse list
     * - Frequency of element
     */
    public static class CollectionsUtilDemo {
        public static void sortAscending(List<Integer> list) {
            // TODO: Sort tăng dần
            Collections.sort(list);
        }

        public static void sortDescending(List<Integer> list) {
            // TODO: Sort giảm dần
            Collections.sort(list, Collections.reverseOrder());
        }

        public static void shuffleList(List<Integer> list) {
            // TODO: Shuffle
            Collections.shuffle(list);
        }

        public static int findMinimum(List<Integer> list) {
            // TODO: Tìm min
            return Collections.min(list);
        }

        public static int binarySearch(List<Integer> sortedList, int target) {
            // TODO: Binary search
            return Collections.binarySearch(sortedList, target);
        }

        public static int countOccurrences(List<Integer> list, int element) {
            // TODO: Đếm số lần xuất hiện
            return Collections.frequency(list, element);
        }
    }

    // ===== BÀI 11: ITERATOR & FAIL-FAST =====

    /**
     * Demo fail-fast iterator và cách fix
     */
    public static void demonstrateFailFast() {
        List<String> list = new ArrayList<>(Arrays.asList("A", "B", "C", "D"));

        // TODO: Demo lỗi ConcurrentModificationException
        // for (String item : list) {
        // if (item.equals("B")) {
        // list.remove(item); // Lỗi!
        // }
        // }

        // TODO: Fix bằng Iterator
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String item = it.next();
            if (item.equals("B")) {
                it.remove();
            }
        }
    }

    // ===== BÀI 12: CUSTOM SORTING =====

    /**
     * Tạo class Product và sort theo nhiều tiêu chí
     */
    static class Product implements Comparable<Product> {
        String name;
        double price;
        int quantity;

        public Product(String name, double price, int quantity) {
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        }

        @Override
        public String toString() {
            return String.format("%s ($%.2f, qty:%d)", name, price, quantity);
        }

        @Override
        public int compareTo(Product p) {
            return this.name.compareTo(p.name);
        }
    }

    static class PriceComparator implements Comparator<Product> {
        @Override
        public int compare(Product o1, Product o2) {
            return Double.compare(o1.price, o2.price);
        }
    }

    static class QuantityComparator implements Comparator<Product> {
        @Override
        public int compare(Product p1, Product p2) {
            return p1.quantity - p2.quantity;
        }
        // solution 2 is changes order p2.qunatity - p2.quantity
    }

    /**
     * Sort products theo:
     * - Price (tăng dần)
     * - Name (alphabetically)
     * - Quantity (giảm dần)
     */
    public static void sortByPrice(List<Product> products) {
        // TODO: Implement
        Collections.sort(products, new PriceComparator());
    }

    public static void sortByName(List<Product> products) {
        // TODO: Implement
        Collections.sort(products);
    }

    public static void sortByQuantityDesc(List<Product> products) {
        // TODO: Implement
        Collections.sort(products, new QuantityComparator().reversed());
    }

    // ===== BÀI 13: MAP OPERATIONS =====

    /**
     * Các operations nâng cao với Map:
     * - Merge 2 maps
     * - Invert map (swap key-value)
     * - Filter map by value
     */
    public static Map<String, Integer> mergeMaps(
            Map<String, Integer> map1,
            Map<String, Integer> map2) {
        // TODO: Merge 2 maps, nếu key trùng thì cộng values

        Map<String, Integer> result = new HashMap<>(map1);

        map2.forEach((k, v) -> result.merge(k, v, (t, u) -> t + u));

        // use method reference
        // map2.forEach((k,v)-> result.merge(k, v, Integer::sum));
        return result;
    }

    public static Map<Integer, String> invertMap(Map<String, Integer> map) {
        // TODO: Swap key-value
        Map<Integer, String> swapMap = new HashMap<>();

        for (Entry<String, Integer> s : map.entrySet()) {
            swapMap.put(s.getValue(), s.getKey());
        }
        return swapMap;
    }

    public static Map<String, Integer> filterByValue(
            Map<String, Integer> map,
            int threshold) {
        // TODO: Chỉ giữ entries có value > threshold

        return map.entrySet().stream().filter(t -> t.getValue() > threshold)
                .collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue()));
    }

    // ===== BÀI 14: CONCURRENT COLLECTIONS =====

    /**
     * So sánh HashMap vs ConcurrentHashMap trong multi-threading
     */
    public static void demonstrateConcurrentHashMap() {
        // TODO: Tạo HashMap và ConcurrentHashMap
        Map<Integer, Integer> hashMap = new HashMap<>();
        Map<Integer, Integer> currHashMap = new ConcurrentHashMap<>();
        List<Thread> threads = new ArrayList<>();
        int NUM_THREADS = 100;
        int OPERATIONS = 1000;
        
        // TODO: Chạy multiple threads cùng modify
        // Hash Map
        for (int i = 0; i < NUM_THREADS; i++) {
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < OPERATIONS; j++) {
                        hashMap.put(j, j);
                    }
                }
            });
            t1.start();
            threads.add(t1);
        }
        // Concurrent Hash Map
        for (int j = 0; j < NUM_THREADS; j++) {
            Thread t2 = new Thread(() -> {
                for (int i = 0; i < OPERATIONS; i++) {
                    currHashMap.put(i, i);
                }
            });
            t2.start();
            threads.add(t2);
        }

        // wait all threads
        try {
            for (Thread t : threads) {
                t.join();
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        // TODO: Quan sát sự khác biệt
        System.out.println("HashMap size: " + hashMap.size());
        System.out.println("ConcurrentHashMap size: " + currHashMap.size());
        System.out.println("Expected size: " + OPERATIONS);
    }

    // ===== BÀI 15: NESTED COLLECTIONS =====

    /**
     * Tạo cấu trúc dữ liệu phức tạp:
     * Map<String, List<Map<String, Integer>>>
     * Ví dụ: Lưu điểm của học sinh theo môn học
     * {
     * "Alice": [{"Math": 90}, {"Physics": 85}],
     * "Bob": [{"Math": 80}, {"Physics": 90}]
     * }
     */
    public static class GradeBook {
        // TODO: Implement data structure
        private Map<String, List<Map<String, Integer>>> grades;

        public GradeBook() {
            grades = new HashMap<>();
        }

        public void addGrade(String student, String subject, int score) {
            // TODO: Implement
            if (!grades.containsKey(student)) {
                grades.put(student, new ArrayList<>());
            }
            grades.get(student).add(Map.of(subject, score));

            // way 2:
            grades.computeIfAbsent(student, k -> new ArrayList<>()).add(Map.of(subject, score));
        }

        public int getGrade(String student, String subject) {
            // TODO: Implement
            Map<String, Integer> stuGrade = grades.get(student).stream().filter(t -> t.containsKey(subject)).findFirst()
                    .orElse(null);
            return stuGrade.get(subject);
        }

        public double getAverageForStudent(String student) {
            // TODO: Tính điểm trung bình của student
            double avgGrade = grades.get(student).stream().flatMap(t -> t.values().stream())
                    .mapToDouble(value -> value.doubleValue()).average().orElse(0.0);
            return avgGrade;
        }
    }

    // ===== MAIN - TEST CODE =====

    public static void main(String[] args) {
        System.out.println("=== BÀI 1: LIST STATS ===");
        List<Integer> numbers = Arrays.asList(5, 2, 8, 1, 9, 3);
        System.out.println("Max: " + ListStats.findMax(numbers));
        System.out.println("Min: " + ListStats.findMin(numbers));
        System.out.println("Sum: " + ListStats.sum(numbers));
        System.out.println("Average: " + ListStats.average(numbers));

        System.out.println("\n=== BÀI 2: PERFORMANCE COMPARISON ===");
        PerformanceComparison.compareAddLast(100000);
        PerformanceComparison.compareAddFirst(100000);

        System.out.println("\n=== BÀI 3: REMOVE DUPLICATES ===");
        List<Integer> withDups = Arrays.asList(1, 2, 2, 3, 3, 3, 4);
        System.out.println("Original: " + withDups);
        System.out.println("No duplicates: " + removeDuplicates(withDups));
        System.out.println("Keep order: " + removeDuplicatesKeepOrder(withDups));

        System.out.println("\n=== BÀI 4: WORD FREQUENCY ===");
        String text = "java is great java is fun java programming";
        System.out.println(wordFrequency(text));

        System.out.println("\n=== BÀI 5: GROUP BY GRADE ===");
        List<StudentRecord> students = Arrays.asList(
                new StudentRecord("Alice", "A"),
                new StudentRecord("Bob", "B"),
                new StudentRecord("Charlie", "A"),
                new StudentRecord("David", "C"),
                new StudentRecord("Eve", "B"));
        System.out.println(groupByGrade(students));

        System.out.println("\n=== BÀI 6: PHONEBOOK ===");
        PhoneBook pb = new PhoneBook();
        pb.addContact("Alice", "123-456");
        pb.addContact("Charlie", "789-012");
        pb.addContact("Bob", "345-678");
        System.out.println("Sorted contacts: " + pb.getAllContactsSorted());

        System.out.println("\n=== BÀI 7: MAPS ORDERING ===");
        demonstrateMapsOrdering();

        System.out.println("\n=== BÀI 8: SET OPERATIONS ===");
        Set<Integer> set1 = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
        Set<Integer> set2 = new HashSet<>(Arrays.asList(4, 5, 6, 7, 8));
        System.out.println("Union: " + union(set1, set2));
        System.out.println("Intersection: " + intersection(set1, set2));
        System.out.println("Difference: " + difference(set1, set2));

        System.out.println("\n=== BÀI 9: K LARGEST ===");
        int[] nums = { 3, 1, 4, 1, 5, 9, 2, 6, 5 };
        System.out.println("3 largest: " + findKLargest(nums, 3));

        System.out.println("\n=== BÀI 10: COLLECTIONS UTIL ===");
        List<Integer> list = new ArrayList<>(Arrays.asList(5, 2, 8, 1, 9));
        CollectionsUtilDemo.sortAscending(list);
        System.out.println("Sorted: " + list);

        System.out.println("\n=== BÀI 11: FAIL-FAST ===");
        demonstrateFailFast();

        System.out.println("\n=== BÀI 12: CUSTOM SORTING ===");
        List<Product> products = Arrays.asList(
                new Product("Laptop", 999.99, 5),
                new Product("Mouse", 29.99, 50),
                new Product("Keyboard", 79.99, 20));
        sortByPrice(products);
        System.out.println("By price: " + products);

        System.out.println("\n=== BÀI 13: MAP OPERATIONS ===");
        Map<String, Integer> map1 = Map.of("a", 1, "b", 2);
        Map<String, Integer> map2 = Map.of("b", 3, "c", 4);
        System.out.println("Merged: " + mergeMaps(map1, map2));

        System.out.println("\n=== BÀI 14: CONCURRENT HASHMAP ===");
        demonstrateConcurrentHashMap();

        System.out.println("\n=== BÀI 15: GRADE BOOK ===");
        GradeBook gb = new GradeBook();
        gb.addGrade("Alice", "Math", 90);
        gb.addGrade("Alice", "Physics", 85);
        System.out.println("Alice Math: " + gb.getGrade("Alice", "Math"));
        System.out.println("Alice Average: " + gb.getAverageForStudent("Alice"));
    }
}
