import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Stream;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

interface ok {
    void okt();

    // Static method
    static int add(int a, int b) {
        return a + b;
    }
}

@FunctionalInterface
interface okcon extends ok {
    static int add(int a, int b) {
        return a + b + 3;
    }
}

abstract class hmm {
    static int add(int a, int b) {
        return a + b;
    }

    abstract void crushT();
}

class hmmok extends hmm {
    static int add(int a, int b) {
        return a + b;
    }

    @Override
    protected void crushT() {
        System.out.println("Sdsds");
    }
}

class Person2 {
    String name;

    Person2(String name) {
        this.name = name;
    }
}

class Counter {
    private int count = 0;

    // Method 1
    public void increment() {
        count++;
    }

    // Method 2
    public synchronized void incrementSync() {
        count++;
    }

    // Method 3
    protected volatile int volatileCount = 0;

    public void incrementVolatile() {
        volatileCount++;
    }
}

public class Kk {
    static void change(Person2 p) {
        p.name = "Bob"; // Thay đổi object (OK)
        // p = new Person2("Charlie"); // Chỉ thay đổi local reference
    }

    public static int test() {
        int x = 1;
        try {
            return x; // Ghi nhớ x = 1
        } finally {
            x = 2; // Thay đổi x KHÔNG ảnh hưởng return value
        }
        // Return 1, không phải 2!
    }

    public static StringBuilder testObject() {
        StringBuilder sb = new StringBuilder("A");
        try {
            return sb; // Ghi nhớ reference của sb
        } finally {
            sb.append("B"); // Thay đổi object → ảnh hưởng!
        }
        // Return "AB", vì reference giống nhau
    }

    public static String testObject2() {
        String sb = new String("S");
        try {
            return sb;
        } finally {
            sb += "sssss";
        }
    }

    public static int test1() {
        try {
            System.out.println("A");
            return 1;
        } catch (Exception e) {
            System.out.println("B");
            return 2;
        } finally {
            System.out.println("C");
        }
    }

    public static int testThrowFinal() {
        try {
            throw new IOException("Original");
        } catch (IOException e) {
            throw new SQLException("From catch"); // Ghi nhớ exception
        } finally {
            throw new RuntimeException("From finally"); // GHI ĐÈ!
        }
    }

    public static void fileHEHEH() {
        File ss = new File("ssss.txt");
        try {
            if (ss.createNewFile()) {
                System.out.println("Created successfully");
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {

        }
        ss.exists();
        // read
        try (BufferedReader reader = new BufferedReader(new FileReader("ssss.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // write
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("ssss.txt"))) {
            writer.write("Hello World");
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void s() throws SQLException {
        Connection conn = DriverManager.getConnection("url", "user", "password");

        String sql = "SELECT * FROM rooms WHERE id = ?";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, 101);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            System.out.println(id + " - " + name);
        }

        // close resource
        rs.close();
        ps.close();
        conn.close();
    }

    public static void main(String[] args) {
        // String sq = null;
        // String s = Optional.ofNullable(sq).orElse("HEEH");
        // System.out.println(s);
        // Counter counter = new Counter();
        // Thread[] threads = new Thread[1000];
        // for (int i = 0; i < 1000; i++) {
        // threads[i] = new Thread(() -> {
        // for (int j = 0; j < 1000; j++) {
        // counter.incrementVolatile(); // Hoặc incrementSync() hoặc incrementVolatile()
        // }
        // });
        // threads[i].start();
        // }
        // for (Thread t : threads) {
        // try {
        // t.join();
        // } catch (InterruptedException e) {
        // e.printStackTrace();
        // }
        // }
        // System.out.println("Final count: " + counter.volatileCount);
        // System.out.println(testObject());
        // System.out.println(testObject2());
        // System.out.println(test());
        // System.out.println(test1());
        System.out.println(testThrowFinal());
    }
}