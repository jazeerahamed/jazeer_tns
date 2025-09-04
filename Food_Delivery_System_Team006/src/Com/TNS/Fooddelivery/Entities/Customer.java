package Com.TNS.Fooddelivery.Entities;

public class Customer {
    private int id;
    private String name;
    private long contact;
    private Cart cart = new Cart();

    public Customer(int id,String name,long contact){ 
        this.id = id; 
        this.name = name; 
        this.contact = contact; 
    }

    // Getters
    public int getId(){ return id; }
    public String getName(){ return name; }
    public long getContact(){ return contact; }
    public Cart getCart(){ return cart; }

    // Setters
    public void setId(int id){ this.id = id; }
    public void setName(String name){ this.name = name; }
    public void setContact(long contact){ this.contact = contact; }
    public void setCart(Cart cart){ this.cart = cart; }
}
