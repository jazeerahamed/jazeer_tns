package Com.TNS.Fooddelivery.Entities;

import java.util.*;

public class Restaurant {
    private int id;
    private String name;
    private List<FoodItem> menu = new ArrayList<>();

    public Restaurant(int id, String name){ 
        this.id = id; 
        this.name = name; 
    }

    // Getters
    public int getId(){ return id; }
    public String getName(){ return name; }
    public List<FoodItem> getMenu(){ return menu; }

    // Setters
    public void setId(int id){ this.id = id; }
    public void setName(String name){ this.name = name; }
    public void setMenu(List<FoodItem> menu){ this.menu = menu; }

    public void addFoodItem(FoodItem f){ menu.add(f); }
    public boolean removeFoodItem(int fid){ return menu.removeIf(f -> f.getId()==fid); }

    public String toString(){ return "Restaurant " + id + ": " + name; }
}

