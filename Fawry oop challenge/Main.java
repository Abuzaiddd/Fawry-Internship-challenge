import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        //intiallizing the products in the store
        System.out.println("Initializing products...");
        ExpirableShippableProduct cheese = new ExpirableShippableProduct("Cheese", 100.0, 10, 0.2, LocalDate.now().plusMonths(6));
        ExpirableShippableProduct biscuits = new ExpirableShippableProduct("Biscuits", 50.0, 20, 0.35, LocalDate.now().plusYears(1));
        ExpirableShippableProduct milk = new ExpirableShippableProduct("milk", 45.0, 20, 1.0, LocalDate.now().plusYears(1));
        ShippableProduct tv = new ShippableProduct("TV", 1200.0, 5, 15.0);
        Product scratchCard = new Product("Mobile Scratch Card", 25.0, 50);
        ExpirableProduct expiredCheese = new ExpirableProduct("Expired Cheese", 90.0, 5, LocalDate.now().minusDays(1));
        System.out.println("Products initialized.\n");

        //intiallizing a customer
        System.out.println("Initializing customer...");
        Customer customer = new Customer("Ahmed Abuzaid", 2500.0);
        System.out.println("Customer '" + customer.getName() + "' with balance $" + customer.getBalance() + " created.\n");

        //adding products to carts
        System.out.println("Customer is adding products to the cart...");
        ShoppingCart cart = new ShoppingCart();
        try {
            cart.add(cheese, 2);
            cart.add(tv, 1);
            cart.add(scratchCard, 4);
            cart.add(biscuits, 3);
            cart.add(milk, 2);
        } catch (IllegalArgumentException e) {
            System.err.println("Error adding product to cart: " + e.getMessage());
        }
        System.out.println("Products added to cart.\n");

        //checkout on the current cart
        System.out.println("--- Performing Checkout ---");
        CheckoutService.checkout(customer, cart);
        System.out.println("---------------------------\n");

         
        // some test cases to test all the expected cases available

        // Case 1: empty cart
        ShoppingCart emptyCart = new ShoppingCart();
        CheckoutService.checkout(customer, emptyCart);
        System.out.println("----------------------------------\n");

        // Case 2: Insufficient balance
        Customer poorCustomer = new Customer("Jane Smith", 100.0);
        ShoppingCart expensiveCart = new ShoppingCart();
        try {
            expensiveCart.add(tv, 1);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
        CheckoutService.checkout(poorCustomer, expensiveCart);
        System.out.println("----------------------------------\n");


        // Case 3: out of stock product
        ShoppingCart outOfStockCart = new ShoppingCart();
        try {
            outOfStockCart.add(tv, 10); // Only 5 are available
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
        System.out.println("----------------------------------\n");


        // Case 4: expired product
        ShoppingCart expiredProductCart = new ShoppingCart();
        try {
            expiredProductCart.add(expiredCheese, 1);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
        CheckoutService.checkout(customer, expiredProductCart);
        System.out.println("-----------------------------\n");
        
    }
}