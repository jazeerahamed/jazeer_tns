package Com.TNS.Fooddelivery.Entities;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import Com.TNS.Fooddelivery.Entities.FoodDeliverySystem;
// ---------------- FoodDeliverySystem ----------------
public class FoodDeliverySystem {
    public static Scanner sc = new Scanner(System.in);
    public static Map<Integer, Restaurant> restaurants = new HashMap<>();
    public static Map<Integer, Customer> customers = new HashMap<>();
    public static Map<Integer, Order> orders = new HashMap<>();
    public static Map<Integer, DeliveryPerson> deliveryPersons = new HashMap<>();

    public static int restaurantCounter = 1001;
    public static int foodCounter = 102;
    public static int customerCounter = 1;
    public static int orderCounter = 01;
    public static int deliveryCounter = 001;

    public static void main(String[] args) {
        setupSampleData();

        while(true) {
            try {
                System.out.println("\n1. Admin Menu\n2. Customer Menu\n3. Exit");
                System.out.print(" Choose Option (1-3): ");
                int choice = sc.nextInt();
                if(choice==1) adminMenu();
                else if(choice==2) customerMenu();
                else break;
            } catch(InputMismatchException e) {
                System.out.println(" Invalid input! Please enter numbers only.");
                sc.nextLine();
            } catch(Exception e) {
                System.out.println(" Unexpected error: " + e.getMessage());
            }
        }
    }

    // ---------------- Admin Menu ----------------
    public static void adminMenu() {
        while(true) {
            try {
                System.out.println("\n--- Admin Menu ---");
                System.out.println("1. Add Restaurant");
                System.out.println("2. Remove Restaurant");
                System.out.println("3. Add Food Item to Restaurant");
                System.out.println("4. Remove Food Item from Restaurant");
                System.out.println("5. View Restaurants & Menus");
                System.out.println("6. View Orders");
                System.out.println("7. Add Delivery Person");
                System.out.println("8. Assign Delivery Person to Order");
                System.out.println("9. Track Orders");
                System.out.println("10. Back");

                System.out.print(" Choose Option (1-10): ");
                int choice = sc.nextInt();

                switch(choice) {
                    case 1:
                        sc.nextLine();
                        System.out.print("Enter Restaurant Name: ");
                        String name = sc.nextLine();
                        Restaurant r = new Restaurant(restaurantCounter, name);
                        restaurants.put(restaurantCounter, r);
                        System.out.println(" Restaurant Added with ID " + restaurantCounter);
                        restaurantCounter++;
                        break;

                    case 2:
                        System.out.print("Enter Restaurant ID to remove: ");
                        int ridRemove = sc.nextInt();
                        if(restaurants.remove(ridRemove)!=null)
                            System.out.println(" Restaurant removed successfully.");
                        else
                            System.out.println(" Restaurant ID not found!");
                        break;

                    case 3:
                        System.out.print("Enter Restaurant ID: ");
                        int ridAddFood = sc.nextInt();
                        r = restaurants.get(ridAddFood);
                        if(r==null){ System.out.println(" Invalid Restaurant ID"); break; }
                        sc.nextLine();
                        System.out.print("Enter Food Name: ");
                        String fname = sc.nextLine();
                        System.out.print("Enter Price: ");
                        double price = sc.nextDouble();
                        if(price <=0){ System.out.println(" Price must be greater than zero."); break; }
                        FoodItem f = new FoodItem(foodCounter, fname, price);
                        r.addFoodItem(f);
                        System.out.println(" Food Added with ID " + foodCounter);
                        foodCounter++;
                        break;

                    case 4:
                        System.out.print("Enter Restaurant ID: ");
                        int ridRemoveFood = sc.nextInt();
                        r = restaurants.get(ridRemoveFood);
                        if(r==null){ System.out.println(" Invalid Restaurant ID"); break; }
                        if(r.getMenu().isEmpty()){ System.out.println(" No food items available to remove."); break; }
                        System.out.println("Menu:");
                        for(FoodItem fi : r.getMenu()) System.out.println(fi);
                        System.out.print("Enter Food ID to Remove: ");
                        int fid = sc.nextInt();
                        if(r.removeFoodItem(fid))
                            System.out.println(" Food Removed.");
                        else
                            System.out.println(" Food ID not found");
                        break;

                    case 5:
                        if(restaurants.isEmpty()){ System.out.println(" No restaurants available."); break; }
                        for(Restaurant rest : restaurants.values()){
                            System.out.println(rest);
                            for(FoodItem fi : rest.getMenu()) System.out.println("   " + fi);
                        }
                        break;

                    case 6:
                        if(orders.isEmpty()){ System.out.println(" No orders placed yet."); break; }
                        for(Order o : orders.values()){
                            System.out.println("Order ID: " + o.getId() + " | Customer: " + o.getCustomer().getName() +
                                    " | Status: " + o.getStatus() +
                                    " | Delivery Person: " + (o.getDeliveryPerson()!=null ? o.getDeliveryPerson().getName()+" ("+o.getDeliveryPerson().getContact()+")" : "Not Assigned"));
                        }
                        break;

                    case 7:
                        sc.nextLine();
                        System.out.print("Enter Delivery Person Name: ");
                        String dname = sc.nextLine();
                        System.out.print("Enter Contact Number: ");
                        long dcontact = sc.nextLong();
                        DeliveryPerson dp = new DeliveryPerson(deliveryCounter, dname, dcontact);
                        deliveryPersons.put(deliveryCounter, dp);
                        System.out.println(" Delivery Person Added with ID " + deliveryCounter);
                        deliveryCounter++;
                        break;

                    case 8:
                        System.out.print("Enter Order ID to assign: ");
                        int oid = sc.nextInt();
                        Order o = orders.get(oid);
                        if(o==null){ System.out.println(" Invalid Order ID"); break; }
                        if(deliveryPersons.isEmpty()){ System.out.println(" No Delivery Persons available!"); break; }
                        System.out.println("Available Delivery Persons:");
                        for(DeliveryPerson d : deliveryPersons.values())
                            System.out.println(d.getId() + ". " + d.getName() + " (" + d.getContact() + ")");
                        System.out.print("Enter Delivery Person ID: ");
                        int did = sc.nextInt();
                        dp = deliveryPersons.get(did);
                        if(dp==null){ System.out.println(" Invalid Delivery Person ID"); break; }
                        o.setDeliveryPerson(dp);
                        o.setStatus("Assigned to " + dp.getName());
                        System.out.println(" Delivery Person Assigned: " + dp.getName());
                        break;

                    case 9:
                        if(orders.isEmpty()){ System.out.println(" No orders to track."); break; }
                        for(Order ord : orders.values()){
                            System.out.println("Order ID: " + ord.getId() +
                                    " | Status: " + ord.getStatus() +
                                    " | Delivery: " + (ord.getDeliveryPerson()!=null ? ord.getDeliveryPerson().getName()+" ("+ord.getDeliveryPerson().getContact()+")" : "Not Assigned"));
                        }
                        break;

                    case 10: return;

                    default: System.out.println(" Invalid choice!");
                }
            } catch(InputMismatchException e){
                System.out.println(" Invalid input! Please enter numbers only.");
                sc.nextLine();
            } catch(Exception e){
                System.out.println(" Error: " + e.getMessage());
            }
        }
    }

    // ---------------- Customer Menu ----------------
    public static void customerMenu() {
        while(true) {
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
                int choice = sc.nextInt();

                switch(choice){
                    case 1: registerCustomer(); break;
                    case 2: viewFoodItems(); break;
                    case 3: addFoodToCart(); break;
                    case 4: viewCart(); break;
                    case 5: placeOrder(); break;
                    case 6: trackOrder(); break;
                    case 7: return;
                    default: System.out.println(" Invalid choice!");
                }
            } catch(InputMismatchException e){
                System.out.println(" Invalid input! Please enter numbers only.");
                sc.nextLine();
            } catch(Exception e){
                System.out.println(" Error: " + e.getMessage());
            }
        }
    }

    // ---------------- Customer Functions ----------------
    public static void registerCustomer() {
        try{
            sc.nextLine();
            System.out.print("Enter Name: ");
            String name = sc.nextLine();
            System.out.print("Enter Contact Number: ");
            long contact = sc.nextLong();

            Customer c = new Customer(customerCounter, name, contact);
            customers.put(customerCounter, c);

            System.out.println(" Registered Successfully. Your Customer ID = " + customerCounter);
            customerCounter++;
        } catch(InputMismatchException e){
            System.out.println(" Invalid contact number!");
            sc.nextLine();
        }
    }

    public static void viewFoodItems() {
        if(restaurants.isEmpty()) { System.out.println("No restaurants available."); return; }
        for(Restaurant r : restaurants.values()) {
            System.out.println(r);
            for(FoodItem f : r.getMenu()) System.out.println("   " + f);
        }
    }

    public static void addFoodToCart() {
        try {
            System.out.print("Enter your Customer ID: ");
            int cid = sc.nextInt();
            Customer c = customers.get(cid);
            if(c==null){ System.out.println(" Invalid Customer ID."); return; }

            viewFoodItems();
            System.out.print("Enter Restaurant ID: ");
            int rid = sc.nextInt();
            Restaurant r = restaurants.get(rid);
            if(r==null){ System.out.println("Invalid Restaurant ID"); return; }

            System.out.print("Enter Food ID: ");
            int fid = sc.nextInt();
            FoodItem chosen = r.getMenu().stream().filter(f -> f.getId()==fid).findFirst().orElse(null);
            if(chosen==null){ System.out.println("Invalid Food ID"); return; }

            System.out.print("Enter Quantity: ");
            int qty = sc.nextInt();
            if(qty <=0){ System.out.println("Quantity must be positive."); return; }

            c.getCart().addItem(chosen, qty);
            System.out.println(" Added to Cart!");
        } catch(InputMismatchException e){
            System.out.println(" Invalid input!");
            sc.nextLine();
        }
    }

    public static void viewCart() {
        try{
            System.out.print("Enter your Customer ID: ");
            int cid = sc.nextInt();
            Customer c = customers.get(cid);
            if(c==null){ System.out.println(" Invalid Customer ID."); return; }
            System.out.println(c.getCart());
        } catch(InputMismatchException e){
            System.out.println(" Invalid input!");
            sc.nextLine();
        }
    }

    public static void placeOrder() {
        try{
            System.out.print("Enter your Customer ID: ");
            int cid = sc.nextInt();
            Customer c = customers.get(cid);
            if(c==null){ System.out.println(" Invalid Customer ID."); return; }
            if(c.getCart().getItems().isEmpty()){ System.out.println(" Your cart is empty!"); return; }

            Order o = new Order(orderCounter, c, new HashMap<>(c.getCart().getItems()));
            if(!deliveryPersons.isEmpty()) {
                List<DeliveryPerson> dpList = new ArrayList<>(deliveryPersons.values());
                DeliveryPerson dp = dpList.get(new Random().nextInt(dpList.size()));
                o.setDeliveryPerson(dp);
                o.setStatus("Assigned to " + dp.getName());
            } else {
                o.setStatus("Pending Assignment");
            }
            orders.put(orderCounter, o);
            c.getCart().clearCart();
            System.out.println(" Order Placed! Your Order ID = " + orderCounter);
            orderCounter++;
        } catch(InputMismatchException e){
            System.out.println(" Invalid input!");
            sc.nextLine();
        }
    }

    public static void trackOrder() {
        try{
            System.out.print("Enter your Order ID: ");
            int oid = sc.nextInt();
            Order o = orders.get(oid);
            if(o==null){ System.out.println(" Invalid Order ID."); return; }

            System.out.println("Order Status: " + o.getStatus() +
                    " | Delivery Person: " + (o.getDeliveryPerson()!=null ? o.getDeliveryPerson() : "Not Assigned"));
        } catch(InputMismatchException e){
            System.out.println(" Invalid input!");
            sc.nextLine();
        }
    }

    // ---------------- Sample Data ----------------
    public static void setupSampleData() {
        Restaurant r1 = new Restaurant(restaurantCounter, "South Indian Tiffins");
        r1.addFoodItem(new FoodItem(foodCounter++, "Idly",30));
        r1.addFoodItem(new FoodItem(foodCounter++, "Vada",20));
        r1.addFoodItem(new FoodItem(foodCounter++, "Dosa",50));

        Restaurant r2 = new Restaurant(restaurantCounter+1, "North Indian Specials");
        r2.addFoodItem(new FoodItem(foodCounter++, "Paneer Butter Masala",120));
        r2.addFoodItem(new FoodItem(foodCounter++, "Biryani",150));
        r2.addFoodItem(new FoodItem(foodCounter++, "Fried Rice",100));

        restaurants.put(r1.getId(), r1);
        restaurants.put(r2.getId(), r2);
        restaurantCounter += 2;

        deliveryPersons.put(deliveryCounter, new DeliveryPerson(deliveryCounter++, "Ravi", 9876543210L));
        deliveryPersons.put(deliveryCounter, new DeliveryPerson(deliveryCounter++, "Sita", 9123456780L));
        deliveryPersons.put(deliveryCounter, new DeliveryPerson(deliveryCounter++, "John", 9988776655L));
    }
}



