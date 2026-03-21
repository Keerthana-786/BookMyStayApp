import java.util.*;
import java.text.SimpleDateFormat;

public class UseCase8BookingHistory {

    static class Booking {
        String bookingId;
        String guestName;
        String roomType;
        int nights;
        int totalAmount;
        String date;

        Booking(String bookingId, String guestName, String roomType, int nights, int totalAmount, String date) {
            this.bookingId = bookingId;
            this.guestName = guestName;
            this.roomType = roomType;
            this.nights = nights;
            this.totalAmount = totalAmount;
            this.date = date;
        }
    }

    public static void main(String[] args) {
        List<Booking> bookingHistory = new ArrayList<>();
        bookingHistory.add(new Booking("BK001", "Arjun Kumar",  "Deluxe",  2, 4000, "2025-01-10"));
        bookingHistory.add(new Booking("BK002", "Priya Sharma", "Suite",   3, 9000, "2025-02-14"));
        bookingHistory.add(new Booking("BK003", "Ravi Patel",   "Standard",1, 1500, "2025-03-05"));
        bookingHistory.add(new Booking("BK004", "Sneha Roy",    "Deluxe",  4, 8000, "2025-03-18"));
        bookingHistory.add(new Booking("BK005", "Kiran Nair",   "Suite",   2, 6000, "2025-04-22"));

        Scanner sc = new Scanner(System.in);

        System.out.println("========================================");
        System.out.println("   Book My Stay - Booking History");
        System.out.println("========================================");
        System.out.println("1. View All Bookings");
        System.out.println("2. Search by Guest Name");
        System.out.println("3. View Summary Report");
        System.out.print("Choose an option: ");

        int choice = Integer.parseInt(sc.nextLine().trim());

        switch (choice) {
            case 1:
                printAllBookings(bookingHistory);
                break;
            case 2:
                System.out.print("Enter guest name to search: ");
                String name = sc.nextLine().trim();
                searchByName(bookingHistory, name);
                break;
            case 3:
                printSummary(bookingHistory);
                break;
            default:
                System.out.println("Invalid option.");
        }
        sc.close();
    }

    static void printAllBookings(List<Booking> list) {
        System.out.println("\n--- All Bookings ---");
        System.out.printf("%-8s %-15s %-10s %-8s %-12s %-12s%n",
                "ID", "Guest", "Room", "Nights", "Amount", "Date");
        System.out.println("------------------------------------------------------------------");
        for (Booking b : list) {
            System.out.printf("%-8s %-15s %-10s %-8d Rs.%-9d %-12s%n",
                    b.bookingId, b.guestName, b.roomType, b.nights, b.totalAmount, b.date);
        }
    }

    static void searchByName(List<Booking> list, String name) {
        System.out.println("\n--- Search Results for: " + name + " ---");
        boolean found = false;
        for (Booking b : list) {
            if (b.guestName.equalsIgnoreCase(name)) {
                System.out.printf("ID: %s | Room: %s | Nights: %d | Amount: Rs.%d | Date: %s%n",
                        b.bookingId, b.roomType, b.nights, b.totalAmount, b.date);
                found = true;
            }
        }
        if (!found) System.out.println("No bookings found for: " + name);
    }

    static void printSummary(List<Booking> list) {
        int totalBookings = list.size();
        int totalRevenue = 0;
        Map<String, Integer> roomCount = new HashMap<>();
        for (Booking b : list) {
            totalRevenue += b.totalAmount;
            roomCount.put(b.roomType, roomCount.getOrDefault(b.roomType, 0) + 1);
        }
        System.out.println("\n========== Summary Report ==========");
        System.out.println("Total Bookings  : " + totalBookings);
        System.out.println("Total Revenue   : Rs. " + totalRevenue);
        System.out.println("Bookings by Room Type:");
        for (Map.Entry<String, Integer> entry : roomCount.entrySet()) {
            System.out.println("  " + entry.getKey() + " : " + entry.getValue());
        }
        System.out.println("=====================================");
    }
}
