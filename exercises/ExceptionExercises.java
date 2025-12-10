import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * BÀI TẬP THỰC HÀNH - EXCEPTION HANDLING
 */

public class ExceptionExercises {

    // ===== BÀI 1: BASIC TRY-CATCH =====

    /**
     * Viết method chia 2 số, xử lý ArithmeticException khi chia cho 0
     */
    public static double divide(int a, int b) {
        // TODO: Implement với try-catch
        try {
            return a / b;
        } catch (ArithmeticException e) {
            System.err.println("Can't divide ZERO !");
            return Double.NaN;
        }
    }

    // ===== BÀI 2: MULTIPLE CATCH BLOCKS =====

    /**
     * Parse string thành integer, xử lý các exception:
     * - NumberFormatException: Khi string không phải số
     * - NullPointerException: Khi string null
     */
    public static int parseInteger(String str) {
        // TODO: Implement với multiple catch blocks
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            System.err.println("Must a format number");
        } catch (NullPointerException e) {
            System.err.println("can't be null");
        }
        return 0;
    }

    // ===== BÀI 3: FINALLY BLOCK =====

    /**
     * Đọc file, đảm bảo luôn đóng file ngay cả khi có exception
     */
    public static String readFile(String filename) {
        // TODO: Sử dụng try-catch-finally
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(filename);
            br = new BufferedReader(fr);
            return br.readLine();
        } catch (IOException e) {
            // Handle
            e.printStackTrace();
            return null;
        } finally {
            // Close resources
            try {
                if (br != null)
                    br.close();
                if (fr != null)
                    fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // ===== BÀI 4: TRY-WITH-RESOURCES =====

    /**
     * Đọc file sử dụng try-with-resources (Java 7+)
     */
    public static String readFileWithResources(String filename) {
        // TODO: Implement try-with-resources
        try (InputStream fip = new FileInputStream(filename)) {
            StringBuilder sb = new StringBuilder();
            int byteRead = 0;
            while ((byteRead = fip.read()) != -1) {
                sb.append((char) byteRead);
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // ===== BÀI 5: THROW KEYWORD =====

    /**
     * Validate tuổi: Phải >= 18
     * Ném IllegalArgumentException nếu tuổi < 18
     */
    public static void validateAge(int age) {
        // TODO: Ném exception nếu age < 18
        if (age < 18) {
            throw new IllegalArgumentException("You 18 yet ?");
        }
        System.out.println("Yeah Are you ready for the game ??");
    }

    // ===== BÀI 6: THROWS KEYWORD =====

    /**
     * Method đọc file và khai báo throws IOException
     */
    public static List<String> readAllLines(String filename) throws IOException {
        // TODO: Đọc tất cả dòng từ file, không catch IOException
        try (FileReader fileReader = new FileReader(filename);
                BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            List<String> list = new ArrayList<>();
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                list.add(line);
            }
            return list;
        }
    }

    // ===== BÀI 7: CUSTOM CHECKED EXCEPTION =====

    /**
     * Tạo custom checked exception: InsufficientBalanceException
     */
    static class InsufficientBalanceException extends Exception {
        private double currentBalance;
        private double requestedAmount;

        // TODO: Constructor với message, currentBalance, requestedAmount
        public InsufficientBalanceException(String message, double currentBalance, double requestedAmount) {
            super(message);
            this.currentBalance = currentBalance;
            this.requestedAmount = requestedAmount;
        }

        // TODO: Getter methods
        public double getCurrentBalance() {
            return this.currentBalance;
        }

        public double getRequestedAmount() {
            return this.requestedAmount;
        }
    }

    /**
     * BankAccount với method withdraw() ném InsufficientBalanceException
     */
    static class BankAccount {
        private double balance;

        public BankAccount(double initialBalance) {
            this.balance = initialBalance;
        }

        public void withdraw(double amount) throws InsufficientBalanceException {
            // TODO: Kiểm tra balance, ném exception nếu không đủ
            if (this.balance < amount) {
                throw new InsufficientBalanceException("Insufficient balance", this.balance, amount);
            }
            this.balance -= amount;
        }

        public void deposit(double amount) {
            if (amount > 0) {
                balance += amount;
            }
        }

        public double getBalance() {
            return balance;
        }
    }

    // ===== BÀI 8: CUSTOM UNCHECKED EXCEPTION =====

    /**
     * Tạo custom unchecked exception: InvalidEmailException
     */
    static class InvalidEmailException extends RuntimeException {
        // TODO: Constructor với message
        public InvalidEmailException() {
            super("invalid email");
        }
    }

    /**
     * Validate email format
     */
    public static void validateEmail(String email) {
        // TODO: Kiểm tra email có chứa '@' và '.'
        if (email.matches(".*@.*\\..")) {
            System.out.println("Valid email");
            return;
        }
        // TODO: Ném InvalidEmailException nếu không hợp lệ
        throw new InvalidEmailException();
    }

    // ===== BÀI 9: EXCEPTION CHAINING =====

    /**
     * Tạo custom exception DatabaseException với cause
     */
    static class DatabaseException extends Exception {
        public DatabaseException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    /**
     * Simulate database connection failure
     */
    public static void connectToDatabase(String url) throws DatabaseException {
        try {
            // Simulate connection failure
            if (url == null || url.isEmpty()) {
                throw new IOException("Invalid URL");
            }
            // Connection logic...
        } catch (IOException e) {
            // TODO: Wrap IOException trong DatabaseException
            throw new DatabaseException("Failed to connect to database", e);
        }
    }

    // ===== BÀI 10: MULTI-CATCH (Java 7+) =====

    /**
     * Xử lý nhiều exception types trong 1 catch block
     */
    public static void multiCatchExample(String filename, String index) {
        try {
            // Read file
            FileReader fr = new FileReader(filename);

            // Parse index
            int idx = Integer.parseInt(index);

            // Array access
            int[] array = { 1, 2, 3 };
            System.out.println(array[idx]);
            fr.close();
        } catch (IOException | NumberFormatException | ArrayIndexOutOfBoundsException e) {
            // TODO: Handle tất cả exceptions
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ===== BÀI 11: RETHROWING EXCEPTIONS =====

    /**
     * Catch exception, log, rồi throw lại
     */
    public static void processFile(String filename) throws IOException {
        try {
            FileReader fr = new FileReader(filename);
            // Process...
            fr.close();
        } catch (IOException e) {
            // TODO: Log exception
            System.err.println("Error processing file: " + e.getMessage());
            // TODO: Rethrow
            throw e;
        }
    }

    // ===== BÀI 12: EXCEPTION IN CONSTRUCTOR =====

    /**
     * Tạo class File với constructor ném exception
     */
    static class MyFile {
        private String path;
        private FileReader reader;

        public MyFile(String path) throws FileNotFoundException {
            // TODO: Validate path
            if (path == null || path.isEmpty()) {
                throw new IllegalArgumentException("Path cannot be null or empty");
            }
            this.path = path;
            // TODO: Tạo FileReader, có thể ném FileNotFoundException
            this.reader = new FileReader(this.path);
        }

        public void close() throws IOException {
            if (reader != null) {
                reader.close();
            }
        }
    }

    // ===== BÀI 13: EXCEPTION HANDLING BEST PRACTICES =====

    /**
     * BAD: Catch exception nhưng không làm gì
     */
    public static void badExample1(String filename) {
        try {
            FileReader fr = new FileReader(filename);
            fr.close();
        } catch (Exception e) {
            // Empty catch - BAD!
        }
    }

    /**
     * BAD: Catch Exception quá chung
     */
    public static void badExample2(String filename) {
        try {
            FileReader fr = new FileReader(filename);
            fr.close();
        } catch (Exception e) {
            // Quá chung - BAD!
            e.printStackTrace();
        }
    }

    /**
     * GOOD: Catch specific exception và handle properly
     */
    public static void goodExample(String filename) {
        // TODO: Implement đúng cách
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(filename);
            br = new BufferedReader(fr);

            String line = "";
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            br.close();
            fr.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filename);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // ===== BÀI 14: AUTOCLOSEABLE CUSTOM CLASS =====

    /**
     * Tạo class DatabaseConnection implements AutoCloseable
     * Để có thể dùng với try-with-resources
     */
    static class DatabaseConnection implements AutoCloseable {
        private String connectionString;
        private boolean isOpen;

        public DatabaseConnection(String connectionString) throws Exception {
            this.connectionString = connectionString;
            // TODO: Simulate connection
            System.out.println("Opening connection: " + connectionString);
            this.isOpen = true;
        }

        public void executeQuery(String query) throws Exception {
            if (!isOpen) {
                throw new Exception("Connection is closed");
            }
            System.out.println("Executing: " + query);
        }

        @Override
        public void close() throws Exception {
            // TODO: Close connection
            System.out.println("Closing connection");
            this.isOpen = false;
        }
    }

    /**
     * Sử dụng DatabaseConnection với try-with-resources
     */
    public static void useDatabaseConnection() {
        // TODO: Implement try-with-resources
        try (DatabaseConnection conn = new DatabaseConnection("jdbc:mysql://localhost:1123/test")) {
            conn.executeQuery("SELECT * FROM tests");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ===== BÀI 15: EXCEPTION TRANSLATION =====

    /**
     * Chuyển low-level exception thành high-level exception
     */
    static class UserService {
        /**
         * Load user từ file
         * Translate IOException thành UserNotFoundException
         */
        static class UserNotFoundException extends Exception {
            public UserNotFoundException(String message, Throwable cause) {
                super(message, cause);
            }
        }

        public static String loadUser(String userId) throws UserNotFoundException {
            try {
                // Simulate loading from file
                FileReader fr = new FileReader("users/" + userId + ".txt");
                // Read user data...
                return "User data";
            } catch (FileNotFoundException e) {
                // TODO: Translate to UserNotFoundException
                throw new UserNotFoundException("User not found: " + userId, e);
            }
        }
    }

    // ===== MAIN - TEST CODE =====

    public static void main(String[] args) {
        System.out.println("=== BÀI 1: BASIC TRY-CATCH ===");
        System.out.println(divide(10, 2)); // 5.0
        System.out.println(divide(10, 0)); // Handle exception

        System.out.println("\n=== BÀI 2: MULTIPLE CATCH ===");
        System.out.println(parseInteger("123")); // 123
        System.out.println(parseInteger("abc")); // Handle NumberFormatException
        System.out.println(parseInteger(null)); // Handle NullPointerException

        System.out.println("\n=== BÀI 3: FINALLY ===");
        System.out.println(readFile("test.txt"));

        System.out.println("\n=== BÀI 4: TRY-WITH-RESOURCES ===");
        System.out.println(readFileWithResources("test.txt"));

        System.out.println("\n=== BÀI 5: THROW ===");
        try {
            validateAge(25); // OK
            validateAge(15); // Exception
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n=== BÀI 6: THROWS ===");
        try {
            List<String> lines = readAllLines("test.txt");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== BÀI 7: CUSTOM CHECKED EXCEPTION ===");
        BankAccount account = new BankAccount(1000);
        try {
            account.withdraw(1500);
        } catch (InsufficientBalanceException e) {
            System.out.println(e.getMessage());
            System.out.println("Current: " + e.getCurrentBalance());
            System.out.println("Requested: " + e.getRequestedAmount());
        }

        System.out.println("\n=== BÀI 8: CUSTOM UNCHECKED EXCEPTION ===");
        try {
            validateEmail("valid@email.com"); // OK
            validateEmail("invalid-email"); // Exception
        } catch (InvalidEmailException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n=== BÀI 9: EXCEPTION CHAINING ===");
        try {
            connectToDatabase("");
        } catch (DatabaseException e) {
            System.out.println(e.getMessage());
            System.out.println("Caused by: " + e.getCause());
        }

        System.out.println("\n=== BÀI 10: MULTI-CATCH ===");
        // multiCatchExample("nonexistent.txt", "5");

        System.out.println("\n=== BÀI 14: AUTOCLOSEABLE ===");
        // useDatabaseConnection();

        System.out.println("\n=== BÀI 15: EXCEPTION TRANSLATION ===");
        try {
            String user = UserService.loadUser("user123");
        } catch (UserService.UserNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Original cause: " + e.getCause());
        }
    }
}
