import java.util.HashMap;
import java.util.Map;

class RoomInventory {
    private HashMap<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room",  2);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public void updateAvailability(String roomType, int count) {
        if (inventory.containsKey(roomType)) {
            inventory.put(roomType, count);
            System.out.println("  [Updated] " + roomType + " -> " + count + " rooms available");
        } else {
            System.out.println("  [Error] Room type not found: " + roomType);
        }
    }

    public void addRoomType(String roomType, int count) {
        inventory.put(roomType, count);
        System.out.println("  [Added] " + roomType + " with " + count + " rooms");
    }

    public void displayInventory() {
        System.out.println("===========================================");
        System.out.println("   Current Room Inventory                  ");
        System.out.println("===========================================");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.printf("  %-15s : %d rooms available%n", entry.getKey(), entry.getValue());
        }
        System.out.println("===========================================");
    }
}

public class UseCase3InventorySetup {
    public static void main(String[] args) {

        System.out.println("===========================================");
        System.out.println("   BookMyStay - Hotel Booking System       ");
        System.out.println("   Use Case 3: Centralized Inventory       ");
        System.out.println("   Version   : 3.1 (Refactored)            ");
        System.out.println("===========================================");
        System.out.println();

        RoomInventory roomInventory = new RoomInventory();

        System.out.println("Step 1: Initial Inventory State");
        roomInventory.displayInventory();
        System.out.println();

        System.out.println("Step 2: Checking Availability (O(1) Lookup)");
        System.out.println("-------------------------------------------");
        System.out.println("  Single Room : " + roomInventory.getAvailability("Single Room") + " available");
        System.out.println("  Double Room : " + roomInventory.getAvailability("Double Room") + " available");
        System.out.println("  Suite Room  : " + roomInventory.getAvailability("Suite Room")  + " available");
        System.out.println();

        System.out.println("Step 3: Updating Availability");
        System.out.println("-------------------------------------------");
        roomInventory.updateAvailability("Single Room", 3);
        roomInventory.updateAvailability("Suite Room",  0);
        System.out.println();

        System.out.println("Step 4: Adding New Room Type (Scalability Demo)");
        System.out.println("-------------------------------------------");
        roomInventory.addRoomType("Deluxe Room", 4);
        System.out.println();

        System.out.println("Step 5: Updated Inventory State");
        roomInventory.displayInventory();
        System.out.println();
        System.out.println("  Application terminated successfully.");
    }
}
