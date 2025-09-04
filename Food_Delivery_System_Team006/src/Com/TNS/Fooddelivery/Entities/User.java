package Com.TNS.Fooddelivery.Entities;

public class User {
	
	    private int id;
	    private String name;
	    private long contact;

	    public User(int id, String name, long contact) {
	        this.id = id; this.name = name; this.contact = contact;
	    }
	    public int getId() { return id; }
	    public String getName() { return name; }
	    public String toString() { return name; }
	}


