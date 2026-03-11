import java.util.LinkedList;
import java.util.Queue;

class BookingRequest {
    private String guestName;
    private String roomType;
    private int nights;

    public BookingRequest(String guestName, String roomType, int nights) {
        this.guestName = guestName;
        this.roomType  = roomType;
        this.nights    = nights;
    }

    public String getGuestName() { return guestName; }
    public String getRoomType()  { return roomType;  }
    public int    getNights()    { return nights;    }

    @Override
    public String toString() {
        return "Guest: " + guestName + " | Room: " + roomType + " | Nights: " + nights;
    }
}

class BookingQueue {
    private Queue<BookingRequest> queue;

    public BookingQueue() {
        queue = new LinkedList<>();
    }

    public void addRequest(BookingRequest request) {
        queue.offer(request);
        System.out.println("  [Queued] " + request);
    }

    public BookingRequest processNext() {
        return queue.poll();
    }

    public int getQueueSize() {
        return queue.size();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}

public class UseCase5BookingRequestQueue {
    public static void main(String[] args) {

        System.out.println("===========================================");
        System.out.println("   BookMyStay - Hotel Booking System       ");
        System.out.println("   Use Case 5: Booking Request Queue       ");
        System.out.println("   Version   : 5.0                         ");
        System.out.println("===========================================");
        System.out.println();

        BookingQueue bookingQueue = new BookingQueue();

        System.out.println("Step 1: Adding Booking Requests (FCFS)");
        System.out.println("-------------------------------------------");
        bookingQueue.addRequest(new BookingRequest("Alice",   "Single Room", 3));
        bookingQueue.addRequest(new BookingRequest("Bob",     "Double Room", 2));
        bookingQueue.addRequest(new BookingRequest("Charlie", "Suite Room",  5));
        bookingQueue.addRequest(new BookingRequest("Diana",   "Single Room", 1));
        System.out.println();

        System.out.println("Step 2: Processing Requests (First-Come-First-Served)");
        System.out.println("-------------------------------------------");
        while (!bookingQueue.isEmpty()) {
            BookingRequest request = bookingQueue.processNext();
            System.out.println("  [Processing] " + request);
        }
        System.out.println();
        System.out.println("  All requests processed.");
        System.out.println();
        System.out.println("  Application terminated successfully.");
    }
}
