import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Optional;
import java.util.Set;
import java.util.Map;
import java.util.function.Predicate;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.BiFunction;

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
        Predicate<Integer> isEven = (t) -> t % 2 == 0;

        // TODO: Function - String length
        Function<String, Integer> length = (a) -> a.length();

        // TODO: Consumer - Print uppercase
        Consumer<String> printUpper = (t) -> System.out.println(t.toUpperCase());

        // TODO: Supplier - Random number
        Supplier<Double> random = () -> Math.random();

        // TODO: BiFunction - Concatenate strings
        BiFunction<String, String, String> concat = (a, b) -> a + b;
    }

    // ===== BÀI 2: METHOD REFERENCE =====

    /**
     * Chuyển lambda thành method reference
     */
    public static void methodReferenceExamples() {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

        // TODO: Chuyển lambda -> method reference
        // names.forEach(s -> System.out.println(s));
        names.forEach(System.out::println);

        // TODO: Chuyển lambda -> method reference
        // names.stream().map(s -> s.toUpperCase()).collect(Collectors.toList());
        names.stream().map(String::toUpperCase).collect(Collectors.toList());
    }

    // ===== BÀI 3: STREAM FILTER =====

    /**
     * Lọc danh sách số
     */
    public static List<Integer> filterEvenNumbers(List<Integer> numbers) {
        // TODO: Lọc số chẵn
        return numbers.stream().filter(t -> t % 2 == 0).toList();
    }

    public static List<Integer> filterGreaterThan(List<Integer> numbers, int threshold) {
        // TODO: Lọc số > threshold
        return numbers.stream().filter(t -> t > threshold).toList();
    }

    // ===== BÀI 4: STREAM MAP =====

    /**
     * Transform elements
     */
    public static List<String> toUpperCase(List<String> strings) {
        // TODO: Convert tất cả thành uppercase
        return strings.stream().map(String::toUpperCase).toList();
    }

    public static List<Integer> getLengths(List<String> strings) {
        // TODO: Lấy độ dài của từng string
        return strings.stream().map(String::length).toList();
    }

    // ===== BÀI 5: STREAM FLATMAP =====

    /**
     * Flatten nested structures
     */
    public static List<Integer> flattenLists(List<List<Integer>> nestedLists) {
        // TODO: Flatten list of lists thành 1 list
        return nestedLists.stream().flatMap(t -> t.stream()).toList();
    }

    public static List<Character> stringToCharList(String str) {
        // TODO: Convert "abc" -> ['a', 'b', 'c']
        return str.chars().mapToObj(c -> (char) c).toList();
    }

    // ===== BÀI 6: STREAM REDUCE =====

    /**
     * Aggregate operations
     */
    public static int sum(List<Integer> numbers) {
        // TODO: Tính tổng dùng reduce
        return numbers.stream().reduce(0, (t, u) -> t + u);
    }

    public static int product(List<Integer> numbers) {
        // TODO: Tính tích dùng reduce
        return numbers.stream().reduce(1, (a, b) -> a * b);
    }

    public static Optional<Integer> max(List<Integer> numbers) {
        // TODO: Tìm max dùng reduce
        return numbers.stream().reduce((a, b) -> a > b ? a : b);
    }

    public static String concatenate(List<String> strings) {
        // TODO: Nối tất cả strings
        return strings.stream().reduce("", (a, b) -> a + b);
    }

    // ===== BÀI 7: STREAM COLLECT =====

    /**
     * Collect to different data structures
     */
    public static List<String> collectToList(Stream<String> stream) {
        // TODO: Collect to List
        return stream.toList();
    }

    public static Set<String> collectToSet(Stream<String> stream) {
        // TODO: Collect to Set
        return stream.collect(Collectors.toSet());
    }

    public static String joinStrings(List<String> strings, String delimiter) {
        // TODO: Join với delimiter (Collectors.joining)
        return strings.stream().collect(Collectors.joining(delimiter));
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

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public String getCity() {
            return city;
        }

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
        return people.stream().collect(Collectors.groupingBy(t -> t.getCity()));
    }

    /**
     * Group people by age range
     */
    public static Map<String, List<Person>> groupByAgeRange(List<Person> people) {
        // TODO: groupingBy age range (< 18: "Minor", >= 18: "Adult")
        return people.stream().collect(Collectors.groupingBy(t -> t.getAge() < 18 ? "Minor" : "Adult"));
    }

    /**
     * Partition people by age >= 18
     */
    public static Map<Boolean, List<Person>> partitionByAdult(List<Person> people) {
        // TODO: partitioningBy age >= 18
        return people.stream().collect(Collectors.partitioningBy(a -> a.getAge() >= 18));
    }

    // ===== BÀI 9: COUNTING & SUMMARIZING =====

    /**
     * Count people by city
     */
    public static Map<String, Long> countByCity(List<Person> people) {
        // TODO: groupingBy + counting
        return people.stream().collect(Collectors.groupingBy(Person::getCity, Collectors.counting()));
    }

    /**
     * Calculate average age by city
     */
    public static Map<String, Double> averageAgeByCity(List<Person> people) {
        // TODO: groupingBy + averagingInt
        return people.stream().collect(Collectors.groupingBy(Person::getCity, Collectors.averagingInt(Person::getAge)));
    }

    // ===== BÀI 10: SORTING =====

    /**
     * Sort people by different criteria
     */
    public static List<Person> sortByAge(List<Person> people) {
        // TODO: Sort by age ascending
        return people.stream().sorted(Comparator.comparingInt(value -> value.getAge())).toList();
    }

    public static List<Person> sortByNameDescending(List<Person> people) {
        // TODO: Sort by name descending
        // return people.stream().sorted((o1, o2) ->
        // o2.getName().compareTo(o1.getName())).toList();
        return people.stream()
                .sorted(Comparator.comparing((Person t) -> t.getName(), Collections.reverseOrder()))
                .toList();
    }

    public static List<Person> sortByCityThenAge(List<Person> people) {
        // TODO: Sort by city, then by age
        return people.stream()
                .sorted((o1, o2) -> {
                    int cityCompare = o1.getCity().compareTo(o2.getCity());
                    if (cityCompare != 0) {
                        return cityCompare;
                    }
                    return Integer.compare(o1.getAge(), o2.getAge());
                })
                .toList();

        // ways 2 use method reference
        // return
        // people.stream().sorted(Comparator.comparing(Person::getCity).thenComparing(Person::getAge)).toList();
    }

    // ===== BÀI 11: DISTINCT & LIMIT & SKIP =====

    /**
     * Get unique elements
     */
    public static List<Integer> getUnique(List<Integer> numbers) {
        // TODO: Remove duplicates
        return numbers.stream().distinct().toList();
    }

    /**
     * Get first N elements
     */
    public static List<Integer> getFirstN(List<Integer> numbers, int n) {
        // TODO: limit(n)
        return numbers.stream().limit(n).toList();
    }

    /**
     * Skip first N and get remaining
     */
    public static List<Integer> skipFirstN(List<Integer> numbers, int n) {
        // TODO: skip(n)
        return numbers.stream().skip(n).toList();
    }

    // ===== BÀI 12: MATCHING =====

    /**
     * Check conditions
     */
    public static boolean hasEven(List<Integer> numbers) {
        // TODO: anyMatch - Có số chẵn không?
        return numbers.stream().anyMatch(t -> t % 2 == 0);
    }

    public static boolean allPositive(List<Integer> numbers) {
        // TODO: allMatch - Tất cả đều dương?
        return numbers.stream().allMatch(t -> t > 0);
    }

    public static boolean noneNegative(List<Integer> numbers) {
        // TODO: noneMatch - Không có số âm?
        return numbers.stream().noneMatch(t -> t < 0);
    }

    // ===== BÀI 13: FINDING =====

    /**
     * Find elements
     */
    public static Optional<String> findFirstStartsWithA(List<String> strings) {
        // TODO: Tìm string đầu tiên bắt đầu bằng 'A'
        return strings.stream().filter(t -> t.matches("^A.*")).findFirst();
    }

    public static Optional<Integer> findAnyEven(List<Integer> numbers) {
        // TODO: Tìm bất kỳ số chẵn nào
        return numbers.stream().filter(t -> t % 2 == 0).findAny();
    }

    // ===== BÀI 14: OPTIONAL =====

    /**
     * Sử dụng Optional
     */
    public static String getNameOrDefault(Person person) {
        // TODO: Return name hoặc "Unknown" nếu person null
        return Optional.ofNullable(person).map(Person::getName).orElse("Unknown");
    }

    public static void printNameIfPresent(Optional<Person> optionalPerson) {
        // TODO: In name nếu person present
        optionalPerson.ifPresent(p -> System.out.println(p.getName()));
        // way 2
        // if (optionalPerson.isPresent()) {
        // System.out.println(optionalPerson.get().getName());
        // }
    }

    public static int getAgeOrThrow(Optional<Person> optionalPerson) {
        // TODO: Return age hoặc throw exception nếu empty
        return optionalPerson.orElseThrow(() -> new RuntimeException("Age?")).getAge();
        // return 0;
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

        public String getName() {
            return name;
        }

        public String getCategory() {
            return category;
        }

        public double getPrice() {
            return price;
        }

        public int getStock() {
            return stock;
        }

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
        return products.stream().collect(Collectors.groupingBy(t -> t.getCategory(),
                Collectors.maxBy((o1, o2) -> Double.compare(o1.getPrice(), o2.getPrice()))));
    }

    /**
     * Tính tổng giá trị inventory theo category (price * stock)
     */
    public static Map<String, Double> totalValueByCategory(List<Product> products) {
        // TODO: groupingBy + summingDouble(p -> p.price * p.stock)
        return products.stream().collect(Collectors.groupingBy(Product::getCategory,
                Collectors.summingDouble(p -> p.getPrice() * p.getStock())));
    }

    /**
     * Lấy top N sản phẩm đắt nhất
     */
    public static List<Product> getTopNExpensive(List<Product> products, int n) {
        // TODO: sorted by price descending + limit
        return products.stream()
                .sorted(Comparator.comparing(Product::getPrice, Collections.reverseOrder()))
                .limit(n)
                .toList();
    }

    /**
     * Lấy tên tất cả sản phẩm Electronics có price > 100
     */
    public static List<String> getExpensiveElectronicsNames(List<Product> products) {
        // TODO: filter category + filter price + map name
        return products.stream().filter(t -> t.getCategory().equals("Electronics"))
                .filter(t -> t.getPrice() > 100).map(t -> t.getName()).toList();
    }

    // ===== BÀI 16: PARALLEL STREAMS =====

    /**
     * So sánh sequential vs parallel stream
     */
    public static long sumSequential(List<Integer> numbers) {
        // TODO: stream().mapToInt().sum()
        return numbers.stream().mapToInt(value -> value.intValue()).sum();
    }

    public static long sumParallel(List<Integer> numbers) {
        // TODO: parallelStream().mapToInt().sum()
        return numbers.parallelStream().mapToInt(value -> value.intValue()).sum();
    }

    // ===== BÀI 17: INFINITE STREAMS =====

    /**
     * Tạo infinite streams
     */
    public static List<Integer> getFirstNEvenNumbers(int n) {
        // TODO: Stream.iterate(0, i -> i + 2).limit(n)
        return Stream.iterate(0, i -> i + 2).limit(n).toList();
    }

    public static List<Double> getRandomNumbers(int count) {
        // TODO: Stream.generate(Math::random).limit(count)
        return Stream.generate(() -> Math.random()).limit(count).toList();
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
        IntSummaryStatistics stats = numbers.stream().mapToInt(Integer::intValue).summaryStatistics();
        NumberStats numberStats = new NumberStats();
        numberStats.count = (int) stats.getCount();
        numberStats.sum = (int) stats.getSum();
        numberStats.average = stats.getAverage();
        numberStats.min = stats.getMin();
        numberStats.max = stats.getMax();
        return numberStats;
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

        public String getCustomerId() {
            return customerId;
        }

        public double getAmount() {
            return amount;
        }
    }

    /**
     * Tìm top 3 customers có tổng transaction amount cao nhất
     */
    public static List<String> getTop3Customers(List<Transaction> transactions) {
        // TODO: groupingBy customer + summingDouble amount + sort + limit
        return transactions.stream()
                .collect(
                        Collectors.groupingBy(
                                Transaction::getCustomerId,
                                Collectors.summingDouble(Transaction::getAmount)))
                .entrySet().stream()
                .sorted((o1, o2) -> Double.compare(o2.getValue(), o1.getValue()))
                .limit(3)
                .map(t -> t.getKey())
                .toList();
    }

    /**
     * Lọc transactions có amount > 1000
     * Nhóm theo customer
     * Tính tổng amount cho mỗi customer
     */
    public static Map<String, Double> getHighValueCustomers(List<Transaction> transactions) {
        // TODO: filter + groupingBy + summingDouble
        return transactions.stream().filter(t -> t.getAmount() > 1000)
                .collect(Collectors.groupingBy(t -> t.getCustomerId(), Collectors.summingDouble(t -> t.getAmount())));
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

        public List<String> getItems() {
            return items;
        }
    }

    public static Set<String> getAllUniqueItems(List<Order> orders) {
        // TODO: flatMap + distinct + collect to Set
        return orders.stream().flatMap(t -> t.getItems().stream()).collect(Collectors.toSet());
    }

    // ===== MAIN - TEST CODE =====

    public static void main(String[] args) {
        System.out.println("=== BÀI 1: LAMBDA BASICS ===");
        lambdaBasics();

        System.out.println("\n=== BÀI 3: FILTER ===");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println("Even: " + filterEvenNumbers(numbers));
        System.out.println(">5: " + filterGreaterThan(numbers, 5));

        System.out.println("\n=== BÀI 4: MAP ===");
        List<String> words = Arrays.asList("java", "python", "javascript");
        System.out.println("Upper: " + toUpperCase(words));
        System.out.println("Lengths: " + getLengths(words));

        System.out.println("\n=== BÀI 5: FLATMAP ===");
        List<List<Integer>> nested = Arrays.asList(
        Arrays.asList(1, 2, 3),
        Arrays.asList(4, 5),
        Arrays.asList(6, 7, 8, 9)
        );
        System.out.println("Flattened: " + flattenLists(nested));

        System.out.println("\n=== BÀI 6: REDUCE ===");
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println("Sum: " + sum(nums));
        System.out.println("Product: " + product(nums));
        System.out.println("Max: " + max(nums));

        System.out.println("\n=== BÀI 8: GROUPING ===");
        List<Person> people = Arrays.asList(
        new Person("Alice", 25, "NYC"),
        new Person("Bob", 30, "LA"),
        new Person("Charlie", 35, "NYC"),
        new Person("David", 15, "LA")
        );
        System.out.println("By city: " + groupByCity(people));
        System.out.println("By adult: " + partitionByAdult(people));

        System.out.println("\n=== BÀI 10: SORTING ===");
        System.out.println("By age: " + sortByAge(people));
        System.out.println("By name desc: " + sortByNameDescending(people));

        System.out.println("\n=== BÀI 15: COMPLEX OPERATIONS ===");
        List<Product> products = Arrays.asList(
        new Product("Laptop", "Electronics", 999.99, 10),
        new Product("Mouse", "Electronics", 29.99, 50),
        new Product("Desk", "Furniture", 299.99, 5),
        new Product("Chair", "Furniture", 199.99, 8)
        );
        System.out.println("Top 2: " + getTopNExpensive(products, 2));
        System.out.println("Total value: " + totalValueByCategory(products));

        System.out.println("\n=== BÀI 19: TRANSACTIONS ===");
        List<Transaction> transactions = Arrays.asList(
        new Transaction("T1", "C1", 1500, "2024-01-01"),
        new Transaction("T2", "C2", 500, "2024-01-02"),
        new Transaction("T3", "C1", 2000, "2024-01-03"),
        new Transaction("T4", "C3", 3000, "2024-01-04")
        );
        System.out.println("Top 3: " + getTop3Customers(transactions));
    }
}
