import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private Map<Product, Integer> items = new HashMap<>();

    public void add(Product product, int quantity) {
        if (product.getQuantity() < quantity) {
            throw new IllegalArgumentException("Not enough stock for " + product.getName() + ".");
        }
        if (product.isExpired()) {
            throw new IllegalArgumentException(product.getName() + " is expired.");
        }
        items.put(product, items.getOrDefault(product, 0) + quantity);
    }

    public Map<Product, Integer> getItems() {
        return items;
    }

    public double getSubtotal() {
        return items.entrySet().stream()
                    .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                    .sum();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}