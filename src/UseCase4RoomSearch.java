import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class RoomSearchInventory {
    private HashMap<String, Integer> inventory;
    private HashMap<String, Double>  prices;

    public RoomSearchInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room",  2);
        inventory.put("Deluxe Room", 4);

        prices = new HashMap<>();
        prices.put("Single Room",  89.99);
        prices.put("Double Room", 149.99);
        prices.put("Suite Room",  299.99);
        prices.put("Deluxe Room", 199.99);
    }

    public boolean isAvailable(String roomType) {
        return inventory.getOrDefault(roomType, 0) > 0;
    }

    public List<String> searchAvailableRooms() {
        List<String> available = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            if (entry.getValue() > 0) {
                available.add(entry.getKey());
            }
        }
        return available;
    }

    public List<String> searchByMaxPrice(double maxPrice) {
        List<String> result = new ArrayList<>();
        for (String roomType : inventory.keySet()) {
            if (prices.getOrDefault(roomType, 0.0) <= maxPrice && inventory.get(roomType) > 0) {
                result.add(roomType);
            }
        }
        return result;
    }

    public void displaySearchResult(List<String> results) {
        if (results.isEmpty()) {
            System.out.println("  No rooms found matching your criteria.");
        } else {
            for (String roomType : results) {
                System.out.printf("  %-15s | Count: %d | Price: $%.2f%n",
                    roomType,
                    inventory.get(roomType),
                    prices.get(roomType));
            }
        }
    }
}

public class UseCase4RoomSearch {
    public static void main(String[] args) {

        System.out.println("===========================================");
        System.out.println("   BookMyStay - Hotel Booking System       ");
        System.out.println("   Use Case 4: Room Search & Availability  ");
        System.out.println("   Version   : 4.0                         ");
        System.out.println("===========================================");
        System.out.println();

        RoomSearchInventory inventory = new RoomSearchInventory();

        System.out.println("Step 1: Search All Available Rooms");
        System.out.println("-------------------------------------------");
        inventory.displaySearchResult(inventory.searchAvailableRooms());
        System.out.println();

        System.out.println("Step 2: Check Specific Room Availability");
        System.out.println("-------------------------------------------");
        System.out.println("  Single Room : " + (inventory.isAvailable("Single Room") ? "Available" : "Not Available"));
        System.out.println("  Suite Room  : " + (inventory.isAvailable("Suite Room")  ? "Available" : "Not Available"));
        System.out.println();

        System.out.println("Step 3: Search Rooms Under $150/night");
        System.out.println("-------------------------------------------");
        inventory.displaySearchResult(inventory.searchByMaxPrice(150.0));
        System.out.println();
        System.out.println("  Application terminated successfully.");
    }
}
