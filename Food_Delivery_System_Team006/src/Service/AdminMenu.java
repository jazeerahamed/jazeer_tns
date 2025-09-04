package Service;

import java.util.InputMismatchException;
import Com.TNS.Fooddelivery.Entities.*;
import Com.TNS.Fooddelivery.Entities.DeliveryPerson;
import Com.TNS.Fooddelivery.Entities.FoodItem;
import Com.TNS.Fooddelivery.Entities.Order;
import Com.TNS.Fooddelivery.Entities.Restaurant;
 

public class AdminMenu {
    public static void adminMenu() {
        while (true) {
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
                int choice = FoodDeliverySystem.sc.nextInt();   

                switch (choice) {
                    case 1:
                        FoodDeliverySystem.sc.nextLine();
                        System.out.print("Enter Restaurant Name: ");
                        String name = FoodDeliverySystem.sc.nextLine();
                        Restaurant r = new Restaurant(FoodDeliverySystem.restaurantCounter, name);
                        FoodDeliverySystem.restaurants.put(FoodDeliverySystem.restaurantCounter, r);
                        System.out.println(" Restaurant Added with ID " + FoodDeliverySystem.restaurantCounter);
                        FoodDeliverySystem.restaurantCounter++;
                        break;

                    case 2:
                        System.out.print("Enter Restaurant ID to remove: ");
                        int ridRemove = FoodDeliverySystem.sc.nextInt();
                        if (FoodDeliverySystem.restaurants.remove(ridRemove) != null)
                            System.out.println(" Restaurant removed successfully.");
                        else
                            System.out.println(" Restaurant ID not found!");
                        break;

                    case 3:
                        System.out.print("Enter Restaurant ID: ");
                        int ridAddFood = FoodDeliverySystem.sc.nextInt();
                        r = FoodDeliverySystem.restaurants.get(ridAddFood);
                        if (r == null) {
                            System.out.println(" Invalid Restaurant ID");
                            break;
                        }
                        FoodDeliverySystem.sc.nextLine();
                        System.out.print("Enter Food Name: ");
                        String fname = FoodDeliverySystem.sc.nextLine();
                        System.out.print("Enter Price: ");
                        double price = FoodDeliverySystem.sc.nextDouble();
                        if (price <= 0) {
                            System.out.println(" Price must be greater than zero.");
                            break;
                        }
                        FoodItem f = new FoodItem(FoodDeliverySystem.foodCounter, fname, price);
                        r.addFoodItem(f);
                        System.out.println(" Food Added with ID " + FoodDeliverySystem.foodCounter);
                        FoodDeliverySystem.foodCounter++;
                        break;

                    case 4:
                        System.out.print("Enter Restaurant ID: ");
                        int ridRemoveFood = FoodDeliverySystem.sc.nextInt();
                        r = FoodDeliverySystem.restaurants.get(ridRemoveFood);
                        if (r == null) {
                            System.out.println(" Invalid Restaurant ID");
                            break;
                        }
                        if (r.getMenu().isEmpty()) {
                            System.out.println(" No food items available to remove.");
                            break;
                        }
                        System.out.println("Menu:");
                        for (FoodItem fi : r.getMenu()) System.out.println(fi);
                        System.out.print("Enter Food ID to Remove: ");
                        int fid = FoodDeliverySystem.sc.nextInt();
                        if (r.removeFoodItem(fid))
                            System.out.println(" Food Removed.");
                        else
                            System.out.println(" Food ID not found");
                        break;

                    case 5:
                        if (FoodDeliverySystem.restaurants.isEmpty()) {
                            System.out.println(" No restaurants available.");
                            break;
                        }
                        for (Restaurant rest : FoodDeliverySystem.restaurants.values()) {
                            System.out.println(rest);
                            for (FoodItem fi : rest.getMenu()) System.out.println("   " + fi);
                        }
                        break;

                    case 6:
                        if (FoodDeliverySystem.orders.isEmpty()) {
                            System.out.println(" No orders placed yet.");
                            break;
                        }
                        for (Order o : FoodDeliverySystem.orders.values()) {
                            System.out.println("Order ID: " + o.getId() + " | Customer: " + o.getCustomer().getName() +
                                    " | Status: " + o.getStatus() +
                                    " | Delivery Person: " + (o.getDeliveryPerson() != null ? o.getDeliveryPerson().getName() + " (" + o.getDeliveryPerson().getContact() + ")" : "Not Assigned"));
                        }
                        break;

                    case 7:
                        FoodDeliverySystem.sc.nextLine();
                        System.out.print("Enter Delivery Person Name: ");
                        String dname = FoodDeliverySystem.sc.nextLine();
                        System.out.print("Enter Contact Number: ");
                        long dcontact = FoodDeliverySystem.sc.nextLong();
                        DeliveryPerson dp = new DeliveryPerson(FoodDeliverySystem.deliveryCounter, dname, dcontact);
                        FoodDeliverySystem.deliveryPersons.put(FoodDeliverySystem.deliveryCounter, dp);
                        System.out.println(" Delivery Person Added with ID " + FoodDeliverySystem.deliveryCounter);
                        FoodDeliverySystem.deliveryCounter++;
                        break;

                    case 8:
                        System.out.print("Enter Order ID to assign: ");
                        int oid = FoodDeliverySystem.sc.nextInt();
                        Order o = FoodDeliverySystem.orders.get(oid);
                        if (o == null) {
                            System.out.println(" Invalid Order ID");
                            break;
                        }
                        if (FoodDeliverySystem.deliveryPersons.isEmpty()) {
                            System.out.println(" No Delivery Persons available!");
                            break;
                        }
                        System.out.println("Available Delivery Persons:");
                        for (DeliveryPerson d : FoodDeliverySystem.deliveryPersons.values())
                            System.out.println(d.getId() + ". " + d.getName() + " (" + d.getContact() + ")");
                        System.out.print("Enter Delivery Person ID: ");
                        int did = FoodDeliverySystem.sc.nextInt();
                        dp = FoodDeliverySystem.deliveryPersons.get(did);
                        if (dp == null) {
                            System.out.println(" Invalid Delivery Person ID");
                            break;
                        }
                        o.setDeliveryPerson(dp);
                        o.setStatus("Assigned to " + dp.getName());
                        System.out.println(" Delivery Person Assigned: " + dp.getName());
                        break;

                    case 9:
                        if (FoodDeliverySystem.orders.isEmpty()) {
                            System.out.println(" No orders to track.");
                            break;
                        }
                        for (Order ord : FoodDeliverySystem.orders.values()) {
                            System.out.println("Order ID: " + ord.getId() +
                                    " | Status: " + ord.getStatus() +
                                    " | Delivery: " + (ord.getDeliveryPerson() != null ? ord.getDeliveryPerson().getName() + " (" + ord.getDeliveryPerson().getContact() + ")" : "Not Assigned"));
                        }
                        break;

                    case 10:
                        return;

                    default:
                        System.out.println(" Invalid choice!");
                }
            } catch (InputMismatchException e) {
                System.out.println(" Invalid input! Please enter numbers only.");
                FoodDeliverySystem.sc.nextLine();
            } catch (Exception e) {
                System.out.println(" Error: " + e.getMessage());
            }
        }
    }
}
