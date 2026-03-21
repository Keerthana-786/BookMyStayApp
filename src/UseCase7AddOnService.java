import java.util.*;

public class UseCase7AddOnService {
    static Map<String, Integer> addOnPrices = new LinkedHashMap<>();

    static {
        addOnPrices.put("Breakfast", 300);
        addOnPrices.put("WiFi", 100);
        addOnPrices.put("Parking", 200);
        addOnPrices.put("Gym Access", 150);
        addOnPrices.put("Airport Pickup", 500);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> selectedAddOns = new ArrayList<>();
        int totalCost = 0;

        System.out.println("========================================");
        System.out.println("     Welcome to Book My Stay App");
        System.out.println("========================================");
        System.out.println("\nAvailable Add-On Services:");
        System.out.println("---------------------------");

        List<String> services = new ArrayList<>(addOnPrices.keySet());
        for (int i = 0; i < services.size(); i++) {
            System.out.printf("%d. %-20s Rs. %d%n", (i + 1), services.get(i), addOnPrices.get(services.get(i)));
        }

        System.out.println("\nEnter the numbers of add-ons you want (comma separated), or 0 to skip:");
        String input = sc.nextLine().trim();

        if (!input.equals("0")) {
            String[] choices = input.split(",");
            for (String choice : choices) {
                try {
                    int idx = Integer.parseInt(choice.trim()) - 1;
                    if (idx >= 0 && idx < services.size()) {
                        String service = services.get(idx);
                        selectedAddOns.add(service);
                        totalCost += addOnPrices.get(service);
                    } else {
                        System.out.println("Invalid option: " + (idx + 1));
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input: " + choice);
                }
            }
        }

        System.out.println("\n========================================");
        System.out.println("         Your Selected Add-Ons");
        System.out.println("========================================");
        if (selectedAddOns.isEmpty()) {
            System.out.println("No add-ons selected.");
        } else {
            for (String addOn : selectedAddOns) {
                System.out.printf("%-20s Rs. %d%n", addOn, addOnPrices.get(addOn));
            }
            System.out.println("---------------------------");
            System.out.println("Total Add-On Cost: Rs. " + totalCost);
        }
        System.out.println("========================================");
        sc.close();
    }
}
