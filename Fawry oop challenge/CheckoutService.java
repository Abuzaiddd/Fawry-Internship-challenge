import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CheckoutService {

    private static final double SHIPPING_FEE_PER_KG = 10.0;

    public static void checkout(Customer customer, ShoppingCart cart) {
        // Rule: Cart cannot be empty
        if (cart.isEmpty()) {
            System.err.println("Error: Cart is empty.");
            return;
        }

        // Rule: Check for expired or out of stock products
        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            Product product = entry.getKey();
            if (product.isExpired()) {
                System.err.println("Error: Product '" + product.getName() + "' is expired.");
                return;
            }
            if (product.getQuantity() < entry.getValue()) {
                System.err.println("Error: Product '" + product.getName() + "' is out of stock.");
                return;
            }
        }

        double subtotal = cart.getSubtotal();
        List<Shippable> shippableItems = new ArrayList<>();
        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            if (entry.getKey() instanceof Shippable) {
                for (int i = 0; i < entry.getValue(); i++) {
                     shippableItems.add((Shippable) entry.getKey());
                }
            }
        }

        double shippingFees = calculateShippingFees(shippableItems);
        double totalAmount = subtotal + shippingFees;

        // Rule: Customer balance must be sufficient
        if (customer.getBalance() < totalAmount) {
            System.err.println("Error: Insufficient balance. Required: $" + totalAmount + ", Available: $" + customer.getBalance());
            return;
        }

        // Process payment
        customer.deductBalance(totalAmount);

        // Update product quantities
        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            entry.getKey().reduceQuantity(entry.getValue());
        }

        // Print shipping notice if applicable
        if (!shippableItems.isEmpty()) {
            ShippingService.sendToShipping(shippableItems, cart);
        }

        // Print checkout receipt
        printReceipt(cart, subtotal, shippingFees, totalAmount, customer);
    }

    private static double calculateShippingFees(List<Shippable> items) {
        double totalWeight = items.stream().mapToDouble(Shippable::getWeight).sum();
        return totalWeight * SHIPPING_FEE_PER_KG;
    }

    private static void printReceipt(ShoppingCart cart, double subtotal, double shipping, double total, Customer customer) {
        System.out.println("** Checkout Receipt **");
        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            System.out.println(entry.getValue() + "x " + entry.getKey().getName() + " " + (entry.getKey().getPrice() * entry.getValue()));
        }
        System.out.println("----------------------");
        System.out.println("Subtotal: $" + subtotal);
        System.out.println("Shipping: $" + shipping);
        System.out.println("Paid Amount: $" + total);
        System.out.println("----------------------");
        System.out.println(customer.getName() + "'s new balance: $" + String.format("%.2f", customer.getBalance()));
        System.out.println("END.");
    }
}