package Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;

import Com.TNS.Fooddelivery.Entities.Customer;
import Com.TNS.Fooddelivery.Entities.DeliveryPerson;
import Com.TNS.Fooddelivery.Entities.FoodItem;
import Com.TNS.Fooddelivery.Entities.Order;
import Com.TNS.Fooddelivery.Entities.Restaurant;
import Com.TNS.Fooddelivery.Entities.*;
public class CustomerMenu {
    public static void customerMenu() {
        while (true) {
            try {
                System.out.println("\n--- Customer Menu ---");
                System.out.println("1. Add Customer");
                System.out.println("2. View Food Items");
                System.out.println("3. Add Food to Cart");
                System.out.println("4. View Cart");
                System.out.println("5. Place Order");
                System.out.println("6. Track Order");
                System.out.println("7. Back");

                System.out.print(" Choose Option (1-7): ");
                int choice = FoodDeliverySystem.sc.nextInt();   

                switch (choice) {
                    case 1: registerCustomer(); break;
                    case 2: viewFoodItems(); break;
                    case 3: addFoodToCart(); break;
                    case 4: viewCart(); break;
                    case 5: placeOrder(); break;
                    case 6: trackOrder(); break;
                    case 7: return;
                    default: System.out.println(" Invalid choice!");
                }
            } catch (InputMismatchException e) {
                System.out.println(" Invalid input! Please enter numbers only.");
                FoodDeliverySystem.sc.nextLine();
            } catch (Exception e) {
                System.out.println(" Error: " + e.getMessage());
            }
        }
    }

    // ---------------- Customer Functions ----------------
    public static void registerCustomer() {
        try {
            FoodDeliverySystem.sc.nextLine();
            System.out.print("Enter Name: ");
            String name = FoodDeliverySystem.sc.nextLine();
            System.out.print("Enter Contact Number: ");
            long contact = FoodDeliverySystem.sc.nextLong();

            Customer c = new Customer(FoodDeliverySystem.customerCounter, name, contact);
            FoodDeliverySystem.customers.put(FoodDeliverySystem.customerCounter, c);

            System.out.println(" Registered Successfully. Your Customer ID = " + FoodDeliverySystem.customerCounter);
            FoodDeliverySystem.customerCounter++;
        } catch (InputMismatchException e) {
            System.out.println(" Invalid contact number!");
            FoodDeliverySystem.sc.nextLine();
        }
    }

    public static void viewFoodItems() {
        if (FoodDeliverySystem.restaurants.isEmpty()) {
            System.out.println("No restaurants available.");
            return;
        }
        for (Restaurant r : FoodDeliverySystem.restaurants.values()) {
            System.out.println(r);
            for (FoodItem f : r.getMenu()) System.out.println("   " + f);
        }
    }

    public static void addFoodToCart() {
        try {
            System.out.print("Enter your Customer ID: ");
            int cid = FoodDeliverySystem.sc.nextInt();
            Customer c = FoodDeliverySystem.customers.get(cid);
            if (c == null) { System.out.println(" Invalid Customer ID."); return; }

            viewFoodItems();
            System.out.print("Enter Restaurant ID: ");
            int rid = FoodDeliverySystem.sc.nextInt();
            Restaurant r = FoodDeliverySystem.restaurants.get(rid);
            if (r == null) { System.out.println("Invalid Restaurant ID"); return; }

            System.out.print("Enter Food ID: ");
            int fid = FoodDeliverySystem.sc.nextInt();
            FoodItem chosen = r.getMenu().stream().filter(f -> f.getId() == fid).findFirst().orElse(null);
            if (chosen == null) { System.out.println("Invalid Food ID"); return; }

            System.out.print("Enter Quantity: ");
            int qty = FoodDeliverySystem.sc.nextInt();
            if (qty <= 0) { System.out.println("Quantity must be positive."); return; }

            c.getCart().addItem(chosen, qty);
            System.out.println(" Added to Cart!");
        } catch (InputMismatchException e) {
            System.out.println(" Invalid input!");
            FoodDeliverySystem.sc.nextLine();
        }
    }

    public static void viewCart() {
        try {
            System.out.print("Enter your Customer ID: ");
            int cid = FoodDeliverySystem.sc.nextInt();
            Customer c = FoodDeliverySystem.customers.get(cid);
            if (c == null) { System.out.println(" Invalid Customer ID."); return; }
            System.out.println(c.getCart());
        } catch (InputMismatchException e) {
            System.out.println(" Invalid input!");
            FoodDeliverySystem.sc.nextLine();
        }
    }

    public static void placeOrder() {
        try {
            System.out.print("Enter your Customer ID: ");
            int cid = FoodDeliverySystem.sc.nextInt();
            Customer c = FoodDeliverySystem.customers.get(cid);
            if (c == null) { System.out.println(" Invalid Customer ID."); return; }
            if (c.getCart().getItems().isEmpty()) { System.out.println(" Your cart is empty!"); return; }

            Order o = new Order(FoodDeliverySystem.orderCounter, c, new HashMap<>(c.getCart().getItems()));
            if (!FoodDeliverySystem.deliveryPersons.isEmpty()) {
                List<DeliveryPerson> dpList = new ArrayList<>(FoodDeliverySystem.deliveryPersons.values());
                DeliveryPerson dp = dpList.get(new Random().nextInt(dpList.size()));
                o.setDeliveryPerson(dp);
                o.setStatus("Assigned to " + dp.getName());
            } else {
                o.setStatus("Pending Assignment");
            }
            FoodDeliverySystem.orders.put(FoodDeliverySystem.orderCounter, o);
            c.getCart().clearCart();
            System.out.println(" Order Placed! Your Order ID = " + FoodDeliverySystem.orderCounter);
            FoodDeliverySystem.orderCounter++;
        } catch (InputMismatchException e) {
            System.out.println(" Invalid input!");
            FoodDeliverySystem.sc.nextLine();
        }
    }

    public static void trackOrder() {
        try {
            System.out.print("Enter your Order ID: ");
            int oid = FoodDeliverySystem.sc.nextInt();
            Order o = FoodDeliverySystem.orders.get(oid);
            if (o == null) { System.out.println(" Invalid Order ID."); return; }

            System.out.println("Order Status: " + o.getStatus() +
                    " | Delivery Person: " + (o.getDeliveryPerson() != null ? o.getDeliveryPerson() : "Not Assigned"));
        } catch (InputMismatchException e) {
            System.out.println(" Invalid input!");
            FoodDeliverySystem.sc.nextLine();
        }
    }
}
