import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

class Reservation {
    private String guestName;
    private String roomType;
    private int nights;
    private String confirmationId;

    public Reservation(String guestName, String roomType, int nights, String confirmationId) {
        this.guestName      = guestName;
        this.roomType       = roomType;
        this.nights         = nights;
        this.confirmationId = confirmationId;
    }

    public String getGuestName()      { return guestName;      }
    public String getRoomType()       { return roomType;       }
    public int    getNights()         { return nights;         }
    public String getConfirmationId() { return confirmationId; }

    @Override
    public String toString() {
        return "[" + confirmationId + "] Guest: " + guestName +
               " | Room: " + roomType + " | Nights: " + nights;
    }
}

class AllocationRequest {
    private String guestName;
    private String roomType;
    private int nights;

    public AllocationRequest(String guestName, String roomType, int nights) {
        this.guestName = guestName;
        this.roomType  = roomType;
        this.nights    = nights;
    }

    public String getGuestName() { return guestName; }
    public String getRoomType()  { return roomType;  }
    public int    getNights()    { return nights;    }
}

class RoomAllocationSystem {
    private HashMap<String, Integer>    inventory;
    private Queue<AllocationRequest>    requestQueue;
    private HashMap<String, Reservation> confirmedReservations;
    private int confirmationCounter;

    public RoomAllocationSystem() {
        inventory             = new HashMap<>();
        requestQueue          = new LinkedList<>();
        confirmedReservations = new HashMap<>();
        confirmationCounter   = 1000;

        inventory.put("Single Room", 3);
        inventory.put("Double Room", 2);
        inventory.put("Suite Room",  1);
    }

    public void submitRequest(String guestName, String roomType, int nights) {
        requestQueue.offer(new AllocationRequest(guestName, roomType, nights));
        System.out.println("  [Requested] " + guestName + " -> " + roomType + " for " + nights + " nights");
    }

    public void processAllRequests() {
        System.out.println("-------------------------------------------");
        while (!requestQueue.isEmpty()) {
            AllocationRequest req = requestQueue.poll();
            String roomType = req.getRoomType();

            if (inventory.getOrDefault(roomType, 0) > 0) {
                inventory.put(roomType, inventory.get(roomType) - 1);
                String confirmationId = "BMS" + (++confirmationCounter);
                Reservation reservation = new Reservation(
                    req.getGuestName(), roomType, req.getNights(), confirmationId);
                confirmedReservations.put(confirmationId, reservation);
                System.out.println("  [Confirmed] " + reservation);
            } else {
                System.out.println("  [Rejected]  " + req.getGuestName() +
                                   " -> " + roomType + " not available");
            }
        }
    }

    public void displayInventory() {
        System.out.println("===========================================");
        System.out.println("   Remaining Inventory                     ");
        System.out.println("===========================================");
        for (HashMap.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.printf("  %-15s : %d remaining%n", entry.getKey(), entry.getValue());
        }
        System.out.println("===========================================");
    }

    public void displayConfirmedReservations() {
        System.out.println("===========================================");
        System.out.println("   Confirmed Reservations                  ");
        System.out.println("===========================================");
        for (Reservation r : confirmedReservations.values()) {
            System.out.println("  " + r);
        }
        System.out.println("===========================================");
    }
}

public class UseCase6RoomAllocation {
    public static void main(String[] args) {

        System.out.println("===========================================");
        System.out.println("   BookMyStay - Hotel Booking System       ");
        System.out.println("   Use Case 6: Reservation & Allocation    ");
        System.out.println("   Version   : 6.0                         ");
        System.out.println("===========================================");
        System.out.println();

        RoomAllocationSystem system = new RoomAllocationSystem();

        System.out.println("Step 1: Submitting Booking Requests");
        System.out.println("-------------------------------------------");
        system.submitRequest("Alice",   "Single Room", 3);
        system.submitRequest("Bob",     "Double Room", 2);
        system.submitRequest("Charlie", "Suite Room",  5);
        system.submitRequest("Diana",   "Single Room", 1);
        system.submitRequest("Eve",     "Suite Room",  2);
        System.out.println();

        System.out.println("Step 2: Processing & Allocating Rooms (FCFS)");
        system.processAllRequests();
        System.out.println();

        System.out.println("Step 3: Confirmed Reservations");
        system.displayConfirmedReservations();
        System.out.println();

        System.out.println("Step 4: Remaining Inventory After Allocation");
        system.displayInventory();
        System.out.println();
        System.out.println("  Application terminated successfully.");
    }
}
