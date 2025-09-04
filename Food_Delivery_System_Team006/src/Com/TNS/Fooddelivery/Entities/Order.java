package Com.TNS.Fooddelivery.Entities;

import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Order {
    private int id;
    private Customer customer;
    private Map<FoodItem,Integer> items;
    private String status = "Pending";
    private DeliveryPerson deliveryPerson;

    public Order(int id, Customer c, Map<FoodItem,Integer> items){ 
        this.id = id; 
        this.customer = c; 
        this.items = new HashMap<>(items);
    }

    // Getters
    public int getId(){ return id; }
    public Customer getCustomer(){ return customer; }
    public Map<FoodItem,Integer> getItems(){ return items; }
    public DeliveryPerson getDeliveryPerson(){ return deliveryPerson; }
    public String getStatus(){ return status; }

    // Setters
    public void setId(int id){ this.id = id; }
    public void setCustomer(Customer customer){ this.customer = customer; }
    public void setItems(Map<FoodItem,Integer> items){ this.items = items; }
    public void setDeliveryPerson(DeliveryPerson dp){ this.deliveryPerson = dp; }
    public void setStatus(String status){ this.status = status; }
}