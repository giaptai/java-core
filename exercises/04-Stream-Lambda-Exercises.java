import java.util.*;
import java.util.function.*;
import java.util.stream.*;

/**
 * BÀI TẬP THỰC HÀNH - STREAM API & LAMBDA EXPRESSIONS
 */

public class StreamLambdaExercises {

    // ===== BÀI 1: LAMBDA BASICS =====

    /**
     * Viết lambda expressions cho các Functional Interfaces
     */
    public static void lambdaBasics() {
        // TODO: Predicate - Kiểm tra số chẵn
        // Predicate<Integer> isEven = ...

        // TODO: Function - String length
        // Function<String, Integer> length = ...

        // TODO: Consumer - Print uppercase
        // Consumer<String> printUpper = ...

        // TODO: Supplier - Random number
        // Supplier<Double> random = ...

        // TODO: BiFunction - Concatenate strings
        // BiFunction<String, String, String> concat = ...
    }

    // ===== BÀI 2: METHOD REFERENCE =====

    /**
     * Chuyển lambda thành method reference
     */
    public static void methodReferenceExamples() {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

        // TODO: Chuyển lambda -> method reference
        // names.forEach(s -> System.out.println(s));
        // names.forEach(System.out::println);

        // TODO: Chuyển lambda -> method reference
        // names.stream().map(s -> s.toUpperCase()).collect(Collectors.toList());
        // names.stream().map(String::toUpperCase).collect(Collectors.toList());
    }

    // ===== BÀI 3: STREAM FILTER =====

    /**
     * Lọc danh sách số
     */
    public static List<Integer> filterEvenNumbers(List<Integer> numbers) {
        // TODO: Lọc số chẵn
        return null;
    }

    public static List<Integer> filterGreaterThan(List<Integer> numbers, int threshold) {
        // TODO: Lọc số > threshold
        return null;
    }

    // ===== BÀI 4: STREAM MAP =====

    /**
     * Transform elements
     */
    public static List<String> toUpperCase(List<String> strings) {
        // TODO: Convert tất cả thành uppercase
        return null;
    }

    public static List<Integer> getLengths(List<String> strings) {
        // TODO: Lấy độ dài của từng string
        return null;
    }

    // ===== BÀI 5: STREAM FLATMAP =====

    /**
     * Flatten nested structures
     */
    public static List<Integer> flattenLists(List<List<Integer>> nestedLists) {
        // TODO: Flatten list of lists thành 1 list
        return null;
    }

    public static List<Character> stringToCharList(String str) {
        // TODO: Convert "abc" -> ['a', 'b', 'c']
        return null;
    }

    // ===== BÀI 6: STREAM REDUCE =====

    /**
     * Aggregate operations
     */
    public static int sum(List<Integer> numbers) {
        // TODO: Tính tổng dùng reduce
        return 0;
    }

    public static int product(List<Integer> numbers) {
        // TODO: Tính tích dùng reduce
        return 0;
    }

    public static Optional<Integer> max(List<Integer> numbers) {
        // TODO: Tìm max dùng reduce
        return Optional.empty();
    }

    public static String concatenate(List<String> strings) {
        // TODO: Nối tất cả strings
        return "";
    }

    // ===== BÀI 7: STREAM COLLECT =====

    /**
     * Collect to different data structures
     */
    public static List<String> collectToList(Stream<String> stream) {
        // TODO: Collect to List
        return null;
    }

    public static Set<String> collectToSet(Stream<String> stream) {
        // TODO: Collect to Set
        return null;
    }

    public static String joinStrings(List<String> strings, String delimiter) {
        // TODO: Join với delimiter (Collectors.joining)
        return "";
    }

    // ===== BÀI 8: GROUPING & PARTITIONING =====

    static class Person {
        String name;
        int age;
        String city;

        public Person(String name, int age, String city) {
            this.name = name;
            this.age = age;
            this.city = city;
        }

        public String getName() { return name; }
        public int getAge() { return age; }
        public String getCity() { return city; }

        @Override
        public String toString() {
            return name + " (" + age + ", " + city + ")";
        }
    }

    /**
     * Group people by city
     */
    public static Map<String, List<Person>> groupByCity(List<Person> people) {
        // TODO: groupingBy city
        return null;
    }

    /**
     * Group people by age range
     */
    public static Map<String, List<Person>> groupByAgeRange(List<Person> people) {
        // TODO: groupingBy age range (< 18: "Minor", >= 18: "Adult")
        return null;
    }

    /**
     * Partition people by age >= 18
     */
    public static Map<Boolean, List<Person>> partitionByAdult(List<Person> people) {
        // TODO: partitioningBy age >= 18
        return null;
    }

    // ===== BÀI 9: COUNTING & SUMMARIZING =====

    /**
     * Count people by city
     */
    public static Map<String, Long> countByCity(List<Person> people) {
        // TODO: groupingBy + counting
        return null;
    }

    /**
     * Calculate average age by city
     */
    public static Map<String, Double> averageAgeByCity(List<Person> people) {
        // TODO: groupingBy + averagingInt
        return null;
    }

    // ===== BÀI 10: SORTING =====

    /**
     * Sort people by different criteria
     */
    public static List<Person> sortByAge(List<Person> people) {
        // TODO: Sort by age ascending
        return null;
    }

    public static List<Person> sortByNameDescending(List<Person> people) {
        // TODO: Sort by name descending
        return null;
    }

    public static List<Person> sortByCityThenAge(List<Person> people) {
        // TODO: Sort by city, then by age
        return null;
    }

    // ===== BÀI 11: DISTINCT & LIMIT & SKIP =====

    /**
     * Get unique elements
     */
    public static List<Integer> getUnique(List<Integer> numbers) {
        // TODO: Remove duplicates
        return null;
    }

    /**
     * Get first N elements
     */
    public static List<Integer> getFirstN(List<Integer> numbers, int n) {
        // TODO: limit(n)
        return null;
    }

    /**
     * Skip first N and get remaining
     */
    public static List<Integer> skipFirstN(List<Integer> numbers, int n) {
        // TODO: skip(n)
        return null;
    }

    // ===== BÀI 12: MATCHING =====

    /**
     * Check conditions
     */
    public static boolean hasEven(List<Integer> numbers) {
        // TODO: anyMatch - Có số chẵn không?
        return false;
    }

    public static boolean allPositive(List<Integer> numbers) {
        // TODO: allMatch - Tất cả đều dương?
        return false;
    }

    public static boolean noneNegative(List<Integer> numbers) {
        // TODO: noneMatch - Không có số âm?
        return false;
    }

    // ===== BÀI 13: FINDING =====

    /**
     * Find elements
     */
    public static Optional<String> findFirstStartsWithA(List<String> strings) {
        // TODO: Tìm string đầu tiên bắt đầu bằng 'A'
        return Optional.empty();
    }

    public static Optional<Integer> findAnyEven(List<Integer> numbers) {
        // TODO: Tìm bất kỳ số chẵn nào
        return Optional.empty();
    }

    // ===== BÀI 14: OPTIONAL =====

    /**
     * Sử dụng Optional
     */
    public static String getNameOrDefault(Person person) {
        // TODO: Return name hoặc "Unknown" nếu person null
        return "";
    }

    public static void printNameIfPresent(Optional<Person> optionalPerson) {
        // TODO: In name nếu person present
    }

    public static int getAgeOrThrow(Optional<Person> optionalPerson) {
        // TODO: Return age hoặc throw exception nếu empty
        return 0;
    }

    // ===== BÀI 15: COMPLEX STREAM OPERATIONS =====

    static class Product {
        String name;
        String category;
        double price;
        int stock;

        public Product(String name, String category, double price, int stock) {
            this.name = name;
            this.category = category;
            this.price = price;
            this.stock = stock;
        }

        public String getName() { return name; }
        public String getCategory() { return category; }
        public double getPrice() { return price; }
        public int getStock() { return stock; }

        @Override
        public String toString() {
            return String.format("%s (%s) $%.2f [%d]", name, category, price, stock);
        }
    }

    /**
     * Tìm sản phẩm đắt nhất trong mỗi category
     */
    public static Map<String, Optional<Product>> mostExpensiveByCategory(List<Product> products) {
        // TODO: groupingBy category + maxBy price
        return null;
    }

    /**
     * Tính tổng giá trị inventory theo category (price * stock)
     */
    public static Map<String, Double> totalValueByCategory(List<Product> products) {
        // TODO: groupingBy + summingDouble(p -> p.price * p.stock)
        return null;
    }

    /**
     * Lấy top N sản phẩm đắt nhất
     */
    public static List<Product> getTopNExpensive(List<Product> products, int n) {
        // TODO: sorted by price descending + limit
        return null;
    }

    /**
     * Lấy tên tất cả sản phẩm Electronics có price > 100
     */
    public static List<String> getExpensiveElectronicsNames(List<Product> products) {
        // TODO: filter category + filter price + map name
        return null;
    }

    // ===== BÀI 16: PARALLEL STREAMS =====

    /**
     * So sánh sequential vs parallel stream
     */
    public static long sumSequential(List<Integer> numbers) {
        // TODO: stream().mapToInt().sum()
        return 0;
    }

    public static long sumParallel(List<Integer> numbers) {
        // TODO: parallelStream().mapToInt().sum()
        return 0;
    }

    // ===== BÀI 17: INFINITE STREAMS =====

    /**
     * Tạo infinite streams
     */
    public static List<Integer> getFirstNEvenNumbers(int n) {
        // TODO: Stream.iterate(0, i -> i + 2).limit(n)
        return null;
    }

    public static List<Double> getRandomNumbers(int count) {
        // TODO: Stream.generate(Math::random).limit(count)
        return null;
    }

    // ===== BÀI 18: CUSTOM COLLECTORS =====

    /**
     * Statistics về numbers
     */
    public static class NumberStats {
        int count;
        int sum;
        double average;
        int min;
        int max;

        @Override
        public String toString() {
            return String.format("Count: %d, Sum: %d, Avg: %.2f, Min: %d, Max: %d",
                    count, sum, average, min, max);
        }
    }

    public static NumberStats calculateStats(List<Integer> numbers) {
        // TODO: Sử dụng IntSummaryStatistics
        return null;
    }

    // ===== BÀI 19: REAL WORLD EXAMPLE =====

    static class Transaction {
        String id;
        String customerId;
        double amount;
        String date;

        public Transaction(String id, String customerId, double amount, String date) {
            this.id = id;
            this.customerId = customerId;
            this.amount = amount;
            this.date = date;
        }

        public String getCustomerId() { return customerId; }
        public double getAmount() { return amount; }
    }

    /**
     * Tìm top 3 customers có tổng transaction amount cao nhất
     */
    public static List<String> getTop3Customers(List<Transaction> transactions) {
        // TODO: groupingBy customer + summingDouble amount + sort + limit
        return null;
    }

    /**
     * Lọc transactions có amount > 1000
     * Nhóm theo customer
     * Tính tổng amount cho mỗi customer
     */
    public static Map<String, Double> getHighValueCustomers(List<Transaction> transactions) {
        // TODO: filter + groupingBy + summingDouble
        return null;
    }

    // ===== BÀI 20: ADVANCED FLATMAP =====

    /**
     * Cho danh sách orders, mỗi order có nhiều items
     * Lấy tất cả unique item names
     */
    static class Order {
        String orderId;
        List<String> items;

        public Order(String orderId, List<String> items) {
            this.orderId = orderId;
            this.items = items;
        }

        public List<String> getItems() { return items; }
    }

    public static Set<String> getAllUniqueItems(List<Order> orders) {
        // TODO: flatMap + distinct + collect to Set
        return null;
    }


    // ===== MAIN - TEST CODE =====

    public static void main(String[] args) {
        System.out.println("=== BÀI 1: LAMBDA BASICS ===");
        // lambdaBasics();

        System.out.println("\n=== BÀI 3: FILTER ===");
        // List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        // System.out.println("Even: " + filterEvenNumbers(numbers));
        // System.out.println(">5: " + filterGreaterThan(numbers, 5));

        System.out.println("\n=== BÀI 4: MAP ===");
        // List<String> words = Arrays.asList("java", "python", "javascript");
        // System.out.println("Upper: " + toUpperCase(words));
        // System.out.println("Lengths: " + getLengths(words));

        System.out.println("\n=== BÀI 5: FLATMAP ===");
        // List<List<Integer>> nested = Arrays.asList(
        //     Arrays.asList(1, 2, 3),
        //     Arrays.asList(4, 5),
        //     Arrays.asList(6, 7, 8, 9)
        // );
        // System.out.println("Flattened: " + flattenLists(nested));

        System.out.println("\n=== BÀI 6: REDUCE ===");
        // List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5);
        // System.out.println("Sum: " + sum(nums));
        // System.out.println("Product: " + product(nums));
        // System.out.println("Max: " + max(nums));

        System.out.println("\n=== BÀI 8: GROUPING ===");
        // List<Person> people = Arrays.asList(
        //     new Person("Alice", 25, "NYC"),
        //     new Person("Bob", 30, "LA"),
        //     new Person("Charlie", 35, "NYC"),
        //     new Person("David", 15, "LA")
        // );
        // System.out.println("By city: " + groupByCity(people));
        // System.out.println("By adult: " + partitionByAdult(people));

        System.out.println("\n=== BÀI 10: SORTING ===");
        // System.out.println("By age: " + sortByAge(people));
        // System.out.println("By name desc: " + sortByNameDescending(people));

        System.out.println("\n=== BÀI 15: COMPLEX OPERATIONS ===");
        // List<Product> products = Arrays.asList(
        //     new Product("Laptop", "Electronics", 999.99, 10),
        //     new Product("Mouse", "Electronics", 29.99, 50),
        //     new Product("Desk", "Furniture", 299.99, 5),
        //     new Product("Chair", "Furniture", 199.99, 8)
        // );
        // System.out.println("Top 2: " + getTopNExpensive(products, 2));
        // System.out.println("Total value: " + totalValueByCategory(products));

        System.out.println("\n=== BÀI 19: TRANSACTIONS ===");
        // List<Transaction> transactions = Arrays.asList(
        //     new Transaction("T1", "C1", 1500, "2024-01-01"),
        //     new Transaction("T2", "C2", 500, "2024-01-02"),
        //     new Transaction("T3", "C1", 2000, "2024-01-03"),
        //     new Transaction("T4", "C3", 3000, "2024-01-04")
        // );
        // System.out.println("Top 3: " + getTop3Customers(transactions));
    }
}
