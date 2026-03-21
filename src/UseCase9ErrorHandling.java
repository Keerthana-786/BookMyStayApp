import java.util.*;

public class UseCase9ErrorHandling {

    static final Map<String, Integer> roomPrices = new HashMap<>();
    static final int MAX_NIGHTS = 30;
    static final int MIN_NIGHTS = 1;

    static {
        roomPrices.put("Standard", 1500);
        roomPrices.put("Deluxe",   2000);
        roomPrices.put("Suite",    3000);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("========================================");
        System.out.println("     Book My Stay - Booking Form");
        System.out.println("========================================");

        String guestName = getValidName(sc);
        String roomType  = getValidRoomType(sc);
        int nights       = getValidNights(sc);
        String checkIn   = getValidDate(sc, "Check-in date (YYYY-MM-DD)");
        String checkOut  = getValidDate(sc, "Check-out date (YYYY-MM-DD)");

        int totalCost = roomPrices.get(roomType) * nights;

        System.out.println("\n========================================");
        System.out.println("        Booking Summary");
        System.out.println("========================================");
        System.out.println("Guest Name  : " + guestName);
        System.out.println("Room Type   : " + roomType);
        System.out.println("Nights      : " + nights);
        System.out.println("Check-In    : " + checkIn);
        System.out.println("Check-Out   : " + checkOut);
        System.out.println("Total Cost  : Rs. " + totalCost);
        System.out.println("========================================");
        sc.close();
    }

    static String getValidName(Scanner sc) {
        while (true) {
            System.out.print("Enter guest name: ");
            String name = sc.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("[ERROR] Name cannot be empty. Please try again.");
            } else if (!name.matches("[a-zA-Z ]+")) {
                System.out.println("[ERROR] Name must contain only letters and spaces.");
            } else {
                return name;
            }
        }
    }

    static String getValidRoomType(Scanner sc) {
        while (true) {
            System.out.print("Enter room type (Standard / Deluxe / Suite): ");
            String type = sc.nextLine().trim();
            if (roomPrices.containsKey(type)) {
                return type;
            }
            System.out.println("[ERROR] Invalid room type. Choose from: Standard, Deluxe, Suite.");
        }
    }

    static int getValidNights(Scanner sc) {
        while (true) {
            System.out.print("Enter number of nights (" + MIN_NIGHTS + "-" + MAX_NIGHTS + "): ");
            try {
                int nights = Integer.parseInt(sc.nextLine().trim());
                if (nights < MIN_NIGHTS || nights > MAX_NIGHTS) {
                    System.out.println("[ERROR] Nights must be between " + MIN_NIGHTS + " and " + MAX_NIGHTS + ".");
                } else {
                    return nights;
                }
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] Please enter a valid number.");
            }
        }
    }

    static String getValidDate(Scanner sc, String label) {
        while (true) {
            System.out.print("Enter " + label + ": ");
            String date = sc.nextLine().trim();
            if (date.matches("\\d{4}-\\d{2}-\\d{2}")) {
                return date;
            }
            System.out.println("[ERROR] Invalid date format. Use YYYY-MM-DD.");
        }
    }
}
