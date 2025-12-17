package booking;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.IOException;
import java.sql.Connection;

public class BookingDatabase {

    // Method A throws IOException
    public void methodA() throws IOException {
        throw new IOException("Error");
    }

    // Method B gọi A → Phải xử lý hoặc tiếp tục throws
    public void methodB() throws IOException {
        methodA(); // Nếu không try-catch → Phải khai báo throws
    }

    // Method C xử lý luôn
    public void methodC() {
        try {
            methodA();
        } catch (IOException e) {
            e.printStackTrace(); // Xử lý xong, không throws nữa
        }
    }

    public Connection connection() throws SQLException, ClassNotFoundException {
        try {
            Class.forName("");
            Connection conn = DriverManager.getConnection("url");
            return conn;
        } catch (SQLException e) {
            throw e;
        } catch (ClassNotFoundException e) {
            throw e;
        }
    }

}
