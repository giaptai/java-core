package booking;

import java.lang.Math;
import java.lang.Thread;

public class BookingService {
    private Room room;

    public BookingService(Room room) {
        this.room = room;
    }

    // solution 1
    protected synchronized boolean bookRoom(String username) {

        // 1. check available
        if (!room.available) {
            System.out.println("[" + username + "] ❌ Phòng đã hết (bookedBy: " + room.bookedBy + ")");
            return false;
        }

        // 2. if room empty
        room.available = false;
        room.bookedBy = username;

        // 3. payment
        boolean paymentOK = processPayment(username);
        if (!paymentOK) {
            room.available = true;
            room.bookedBy = null;
            return false;
        }

        return true;
    }

    protected boolean processPayment(String username) {
        // Giả lập: 50% thành công, 50% fail
        System.out.println("[" + username + "] Processing payment...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }

        boolean success = username.equals("User2");
        System.out.println("[" + username + "] Payment: " +
                (success ? "SUCCESS" : "FAILED"));
        return success;
    }

    public static void main(String[] args) {
        Room room101 = new Room(101);
        room101.available = true;

        BookingService service = new BookingService(room101);

        // Thread 1: User1
        Thread t1 = new Thread(() -> {
            boolean result = service.bookRoom("User1");
            System.out.println("User1: " + result);
        });

        // Thread 2: User2
        Thread t2 = new Thread(() -> {
            boolean result = service.bookRoom("User2");
            System.out.println("User2: " + result);
        });

        Thread t3 = new Thread(() -> {
            boolean result = service.bookRoom("User3");
            System.out.println("User3: " + result);
        });

        t3.start();
        t2.start();
        t1.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
