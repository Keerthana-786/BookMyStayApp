import java.util.*;

public class UseCase10BookingCancellation {

    static class Booking {
        String bookingId;
        String guestName;
        String roomType;
        int nights;
        boolean cancelled;

        Booking(String bookingId, String guestName, String roomType, int nights) {
            this.bookingId  = bookingId;
            this.guestName  = guestName;
            this.roomType   = roomType;
            this.nights     = nights;
            this.cancelled  = false;
        }
    }

    static Map<String, Integer> inventory = new LinkedHashMap<>();
    static List<Booking> bookings = new ArrayList<>();

    static {
        inventory.put("Standard", 3);
        inventory.put("Deluxe",   2);
        inventory.put("Suite",    1);

        bookings.add(new Booking("BK001", "Arjun Kumar",  "Deluxe",   2));
        bookings.add(new Booking("BK002", "Priya Sharma", "Suite",    3));
        bookings.add(new Booking("BK003", "Ravi Patel",   "Standard", 1));

        inventory.put("Standard", inventory.get("Standard") - 1);
        inventory.put("Deluxe",   inventory.get("Deluxe")   - 1);
        inventory.put("Suite",    inventory.get("Suite")    - 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("========================================");
        System.out.println("  Book My Stay - Cancellation Portal");
        System.out.println("========================================");
        printInventory();
        printBookings();

        System.out.print("\nEnter Booking ID to cancel: ");
        String id = sc.nextLine().trim();
        cancelBooking(id);

        System.out.println("\n--- Updated Inventory After Cancellation ---");
        printInventory();
        sc.close();
    }

    static void cancelBooking(String id) {
        for (Booking b : bookings) {
            if (b.bookingId.equalsIgnoreCase(id)) {
                if (b.cancelled) {
                    System.out.println("[INFO] Booking " + id + " is already cancelled.");
                    return;
                }
                b.cancelled = true;
                inventory.put(b.roomType, inventory.get(b.roomType) + 1);
                System.out.println("\n[SUCCESS] Booking " + id + " for " + b.guestName + " has been cancelled.");
                System.out.println("[ROLLBACK] " + b.roomType + " room inventory restored.");
                return;
            }
        }
        System.out.println("[ERROR] Booking ID " + id + " not found.");
    }

    static void printInventory() {
        System.out.println("\nCurrent Room Inventory:");
        for (Map.Entry<String, Integer> e : inventory.entrySet()) {
            System.out.println("  " + e.getKey() + " : " + e.getValue() + " available");
        }
    }

    static void printBookings() {
        System.out.println("\nActive Bookings:");
        System.out.printf("%-8s %-15s %-10s %-8s %-10s%n", "ID", "Guest", "Room", "Nights", "Status");
        System.out.println("------------------------------------------------------");
        for (Booking b : bookings) {
            System.out.printf("%-8s %-15s %-10s %-8d %-10s%n",
                    b.bookingId, b.guestName, b.roomType, b.nights,
                    b.cancelled ? "Cancelled" : "Active");
        }
    }
}
