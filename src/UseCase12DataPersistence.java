import java.util.*;
import java.io.*;

public class UseCase12DataPersistence {

    static final String FILE_NAME = "bookings_data.txt";

    static class Booking {
        String bookingId;
        String guestName;
        String roomType;
        int nights;
        int totalAmount;

        Booking(String bookingId, String guestName, String roomType, int nights, int totalAmount) {
            this.bookingId   = bookingId;
            this.guestName   = guestName;
            this.roomType    = roomType;
            this.nights      = nights;
            this.totalAmount = totalAmount;
        }

        String toFileString() {
            return bookingId + "," + guestName + "," + roomType + "," + nights + "," + totalAmount;
        }

        static Booking fromFileString(String line) {
            String[] parts = line.split(",");
            return new Booking(parts[0], parts[1], parts[2],
                    Integer.parseInt(parts[3]), Integer.parseInt(parts[4]));
        }

        @Override
        public String toString() {
            return String.format("%-8s %-15s %-10s %-8d Rs.%d",
                    bookingId, guestName, roomType, nights, totalAmount);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Booking> bookings = loadBookings();

        System.out.println("========================================");
        System.out.println(" Book My Stay - Data Persistence");
        System.out.println("========================================");
        System.out.println("1. Add New Booking");
        System.out.println("2. View All Bookings (Recovery)");
        System.out.println("3. Exit");
        System.out.print("Choose an option: ");

        int choice = Integer.parseInt(sc.nextLine().trim());

        switch (choice) {
            case 1:
                System.out.print("Booking ID   : "); String id     = sc.nextLine().trim();
                System.out.print("Guest Name   : "); String name   = sc.nextLine().trim();
                System.out.print("Room Type    : "); String room   = sc.nextLine().trim();
                System.out.print("Nights       : "); int nights    = Integer.parseInt(sc.nextLine().trim());
                System.out.print("Total Amount : "); int amount    = Integer.parseInt(sc.nextLine().trim());

                Booking newBooking = new Booking(id, name, room, nights, amount);
                bookings.add(newBooking);
                saveBookings(bookings);
                System.out.println("\n[SUCCESS] Booking saved to file: " + FILE_NAME);
                break;

            case 2:
                System.out.println("\n--- Recovered Bookings from File ---");
                if (bookings.isEmpty()) {
                    System.out.println("No bookings found in file.");
                } else {
                    System.out.printf("%-8s %-15s %-10s %-8s %-12s%n",
                            "ID", "Guest", "Room", "Nights", "Amount");
                    System.out.println("------------------------------------------------------");
                    for (Booking b : bookings) System.out.println(b);
                    System.out.println("\nTotal Records Recovered: " + bookings.size());
                }
                break;

            case 3:
                System.out.println("Exiting... Data is safely persisted.");
                break;

            default:
                System.out.println("Invalid option.");
        }
        sc.close();
    }

    static void saveBookings(List<Booking> bookings) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Booking b : bookings) {
                bw.write(b.toFileString());
                bw.newLine();
            }
            System.out.println("[SAVE] " + bookings.size() + " booking(s) written to " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("[ERROR] Failed to save bookings: " + e.getMessage());
        }
    }

    static List<Booking> loadBookings() {
        List<Booking> list = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("[INFO] No existing data file found. Starting fresh.");
            return list;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    list.add(Booking.fromFileString(line));
                }
            }
            System.out.println("[RECOVERY] " + list.size() + " booking(s) loaded from " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("[ERROR] Failed to load bookings: " + e.getMessage());
        }
        return list;
    }
}
