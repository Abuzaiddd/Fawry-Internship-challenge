import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ShippingService {
    public static void sendToShipping(List<Shippable> items, ShoppingCart cart) {
        System.out.println("\n** Shipment Notice **");

        Map<String, Long> itemCounts = items.stream()
                .collect(Collectors.groupingBy(Shippable::getName, Collectors.counting()));

        double totalWeight = 0;

        for (Map.Entry<String, Long> entry : itemCounts.entrySet()) {
            String productName = entry.getKey();
            long quantity = entry.getValue();
            // Find the product in the cart to get its weight
            Product product = cart.getItems().keySet().stream()
                                  .filter(p -> p.getName().equals(productName))
                                  .findFirst().orElse(null);

            if (product instanceof Shippable) {
                double itemWeight = ((Shippable) product).getWeight();
                totalWeight += itemWeight * quantity;
                System.out.println(quantity + "x " + productName + " " + (itemWeight * 1000) + "g");
            }
        }

        System.out.println("Total package weight: " + String.format("%.2f", totalWeight) + "kg\n");
    }
}