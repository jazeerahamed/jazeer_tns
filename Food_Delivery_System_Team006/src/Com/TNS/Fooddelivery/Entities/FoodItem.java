package Com.TNS.Fooddelivery.Entities;

public class FoodItem {
    private int id;
    private String name;
    private double price;

    public FoodItem(int id,String name,double price){ 
        this.id = id; 
        this.name = name; 
        this.price = price; 
    }

    // Getters
    public int getId(){ return id; }
    public String getName(){ return name; }
    public double getPrice(){ return price; }

    // Setters
    public void setId(int id){ this.id = id; }
    public void setName(String name){ this.name = name; }
    public void setPrice(double price){ this.price = price; }

    public String toString(){ return id + ". " + name + " - ₹" + price; }
}


