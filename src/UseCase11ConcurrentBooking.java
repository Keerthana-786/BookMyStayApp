import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class UseCase11ConcurrentBooking {

    static final int TOTAL_ROOMS = 5;
    static AtomicInteger availableRooms = new AtomicInteger(TOTAL_ROOMS);
    static List<String> bookingLog = Collections.synchronizedList(new ArrayList<>());

    static class BookingRequest implements Runnable {
        String guestName;

        BookingRequest(String guestName) {
            this.guestName = guestName;
        }

        @Override
        public void run() {
            System.out.println("[REQUEST] " + guestName + " is trying to book a room...");
            synchronized (UseCase11ConcurrentBooking.class) {
                if (availableRooms.get() > 0) {
                    availableRooms.decrementAndGet();
                    String bookingId = "BK" + String.format("%03d", bookingLog.size() + 1);
                    bookingLog.add(bookingId + " -> " + guestName);
                    System.out.println("[SUCCESS] " + guestName +
                            " booked successfully. ID: " + bookingId +
                            " | Rooms Left: " + availableRooms.get());
                } else {
                    System.out.println("[FAILED ] No rooms available for " + guestName);
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("========================================");
        System.out.println("  Book My Stay - Concurrent Booking");
        System.out.println("  Total Rooms Available: " + TOTAL_ROOMS);
        System.out.println("========================================\n");

        String[] guests = {
            "Arjun", "Priya", "Ravi", "Sneha", "Kiran",
            "Meera", "Vikram", "Ananya", "Suresh", "Divya"
        };

        ExecutorService executor = Executors.newFixedThreadPool(5);

        for (String guest : guests) {
            executor.submit(new BookingRequest(guest));
            Thread.sleep(100);
        }

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);

        System.out.println("\n========================================");
        System.out.println("          Final Booking Log");
        System.out.println("========================================");
        if (bookingLog.isEmpty()) {
            System.out.println("No bookings were made.");
        } else {
            for (String log : bookingLog) {
                System.out.println(log);
            }
        }
        System.out.println("Total Booked: " + bookingLog.size() + "/" + TOTAL_ROOMS);
        System.out.println("Rooms Remaining: " + availableRooms.get());
        System.out.println("========================================");
    }
}
