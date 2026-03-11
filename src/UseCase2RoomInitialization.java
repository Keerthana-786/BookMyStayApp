// ============================================================
//  BookMyStay - Hotel Booking Management System
//  Use Case 2 : Basic Room Types & Static Availability
//  Version    : 2.1 (Refactored)
//
//  Key Concepts:
//  - Abstract Class         : Room (cannot be instantiated)
//  - Inheritance            : SingleRoom, DoubleRoom, SuiteRoom extend Room
//  - Polymorphism           : Room[] holds all concrete types
//  - Encapsulation          : Room attributes via getters only
//  - Static Availability    : boolean variables (UC2 limitation)
//  - Separation of Domain   : Room = what it IS, availability = STATE
// ============================================================

// ── Abstract Class : Room ────────────────────────────────────
abstract class Room {

    private String roomType;
    private int numberOfBeds;
    private double sizeInSqFt;
    private double pricePerNight;

    public Room(String roomType, int numberOfBeds, double sizeInSqFt, double pricePerNight) {
        this.roomType      = roomType;
        this.numberOfBeds  = numberOfBeds;
        this.sizeInSqFt    = sizeInSqFt;
        this.pricePerNight = pricePerNight;
    }

    public String getRoomType()      { return roomType;      }
    public int    getNumberOfBeds()  { return numberOfBeds;  }
    public double getSizeInSqFt()    { return sizeInSqFt;    }
    public double getPricePerNight() { return pricePerNight; }

    public abstract String getAmenities();

    public void displayRoomDetails() {
        System.out.println("+-----------------------------------------+");
        System.out.println("  Room Type      : " + roomType);
        System.out.println("  Number of Beds : " + numberOfBeds);
        System.out.printf ("  Size           : %.1f sq ft%n", sizeInSqFt);
        System.out.printf ("  Price / Night  : $%.2f%n", pricePerNight);
        System.out.println("  Amenities      : " + getAmenities());
        System.out.println("+-----------------------------------------+");
    }
}

// ── Concrete Class : SingleRoom ──────────────────────────────
class SingleRoom extends Room {
    public SingleRoom() {
        super("Single Room", 1, 200.0, 89.99);
    }

    @Override
    public String getAmenities() {
        return "Wi-Fi, TV, Mini Fridge, Work Desk";
    }
}

// ── Concrete Class : DoubleRoom ──────────────────────────────
class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double Room", 2, 350.0, 149.99);
    }

    @Override
    public String getAmenities() {
        return "Wi-Fi, TV, Mini Fridge, Work Desk, Sofa, Coffee Maker";
    }
}

// ── Concrete Class : SuiteRoom ───────────────────────────────
class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite Room", 3, 650.0, 299.99);
    }

    @Override
    public String getAmenities() {
        return "Wi-Fi, Smart TV, Mini Bar, Jacuzzi, Living Area, Butler Service, Ocean View";
    }
}

// ── Main Class ───────────────────────────────────────────────
public class UseCase2RoomInitialization {
    public static void main(String[] args) {

        System.out.println("===========================================");
        System.out.println("   BookMyStay - Hotel Booking System       ");
        System.out.println("   Use Case 2: Room Types & Availability   ");
        System.out.println("   Version   : 2.1 (Refactored)            ");
        System.out.println("===========================================");
        System.out.println();

        // Polymorphism - Room[] holds all concrete types uniformly
        Room[] rooms = {
            new SingleRoom(),
            new DoubleRoom(),
            new SuiteRoom()
        };

        // Static Availability - individual boolean variables (UC2 limitation)
        boolean singleRoomAvailable = true;
        boolean doubleRoomAvailable = true;
        boolean suiteRoomAvailable  = false;

        boolean[] availability = {
            singleRoomAvailable,
            doubleRoomAvailable,
            suiteRoomAvailable
        };

        // Display Room Details + Availability
        System.out.println("Available Room Types");
        System.out.println("-------------------------------------------");
        System.out.println();

        for (int i = 0; i < rooms.length; i++) {
            rooms[i].displayRoomDetails();
            System.out.println("  Availability   : " + (availability[i] ? "Available" : "Not Available"));
            System.out.println();
        }

        // Availability Summary
        System.out.println("===========================================");
        System.out.println("   Availability Summary                    ");
        System.out.println("===========================================");
        for (int i = 0; i < rooms.length; i++) {
            System.out.printf("  %-15s : %s%n",
                rooms[i].getRoomType(),
                availability[i] ? "Available" : "Not Available");
        }
        System.out.println("===========================================");
        System.out.println();

        System.out.println("[Design Note]");
        System.out.println("  Availability is stored in individual boolean");
        System.out.println("  variables. This approach does not scale.");
        System.out.println("  Use Case 3 introduces HashMap to centralise");
        System.out.println("  inventory management.");
        System.out.println();
        System.out.println("  Application terminated successfully.");
    }
}
