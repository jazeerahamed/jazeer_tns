package Com.TNS.Fooddelivery.Entities;

import java.util.*;

public class Cart {
    private Map<FoodItem,Integer> items = new HashMap<>();

    // Getters
    public Map<FoodItem,Integer> getItems(){ return items; }

    // Setters
    public void setItems(Map<FoodItem,Integer> items){ this.items = items; }

    public void addItem(FoodItem f,int qty){ items.put(f, items.getOrDefault(f,0)+qty); }
    public void clearCart(){ items.clear(); }

    public String toString(){
        if(items.isEmpty()) return "Cart is empty.";
        StringBuilder sb = new StringBuilder("Your Cart:\n");
        double total=0;
        for(Map.Entry<FoodItem,Integer> e: items.entrySet()){
            sb.append(e.getKey().toString()+" x"+e.getValue()+"\n");
            total += e.getKey().getPrice() * e.getValue();
        }
        sb.append("Total: ₹" + total);
        return sb.toString();
    }
}
