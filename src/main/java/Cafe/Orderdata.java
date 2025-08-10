package Cafe;

public class Orderdata {
<<<<<<< HEAD
    private String ItemName;
    private float orderPrice;
    private String email;
    public Orderdata(String email,String ItemName,float orderPrice) {
        this.email=email;
    	this.ItemName = ItemName;
        this.orderPrice = orderPrice;
    }
    public String getEmail()
    {
    	return email;
    }
    public void setEmail(String email)
    {
    	this.email=email;
    }

	public void setItemName(String ItemName) {
		 this.ItemName=ItemName;
	}

	public String getItemName() {
		return ItemName;
	}
=======
    private int itemId;
    private String orderName;
    private String username;
    private float orderPrice;

    public Orderdata(int itemId, String orderName, String username,float orderPrice) {
        this.itemId = itemId;
        this.orderName = orderName;
        this.username=username;
        this.orderPrice = orderPrice;
    }

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

>>>>>>> 5802e278c7dd8859a3975d4d65621f37f6aa7f0b
	public float getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(int orderPrice) {
		this.orderPrice = orderPrice;
	}

    
}
