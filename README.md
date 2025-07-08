# E-commerce System OOP Challenge

This is a simple command-line Java program that simulates a mini online store. It was built as a full-stack development internship challenge to showcase **Object-Oriented Programming (OOP)** concepts by managing products, customers, shopping carts, and a checkout process.

---

## What It Does

The program:
- Sets up sample products and a customer with a starting balance.
- Simulates adding products to a shopping cart.
- Handles checkout, validating:
  - Stock availability
  - Product expiration
  - Customer's available balance

---

## Features

### Product Types
- **Standard Products**: With name, price, and stock count.
- **Expirable Products**: Like Cheese and Biscuits.
- **Shippable Products**: Like a TV, which have weight.
- **Combined Types**: Products that are both expirable and shippable.

### Shopping Cart
- Customers can add products to a cart.
- Prevents adding more than available stock.

### Checkout Process
- Calculates total price of items.
- Adds shipping fee based on total weight.
- Validates:
  - Empty cart
  - Insufficient balance
  - Out-of-stock or expired products

### Shipping Details
- Prints a shipment notice listing items that need to be shipped and their total weight.

---

## How It's Built (OOP Concepts)

- **Inheritance**:  
  `ExpirableProduct` and `ShippableProduct` extend the base `Product` class.

- **Interfaces**:  
  The `Shippable` interface ensures all shippable products implement `getWeight()`.

- **Encapsulation**:  
  Class data like price and balance are private, accessible via public methods only.

---

## Project Files

| File                         | Description                                       |
|-----------------------------|---------------------------------------------------|
| `Main.java`                 | Entry point. Sets up data and runs the program.  |
| `Product.java`              | Base class for all products.                     |
| `ExpirableProduct.java`     | Extends `Product` with expiration logic.         |
| `Shippable.java`            | Interface for products that can be shipped.      |
| `ShippableProduct.java`     | Adds shipping weight to a product.               |
| `ExpirableShippableProduct.java` | Combines expiration and shipping features.  |
| `Customer.java`             | Represents a customer with name and balance.     |
| `ShoppingCart.java`         | Manages cart items and quantities.               |
| `CheckoutService.java`      | Handles checkout logic.                          |
| `ShippingService.java`      | Prints shipping information.                     |

---
![Screenshot 2025-07-09 013814](https://github.com/user-attachments/assets/15830e55-4ba2-465e-be74-ec8226e7f686)
![Screenshot 2025-07-09 013829](https://github.com/user-attachments/assets/d3bab52b-628e-41f2-9f96-b12b9cb44b5a)
