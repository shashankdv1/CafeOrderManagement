package Cafe;

public class Orderdata {
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

	public float getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(int orderPrice) {
		this.orderPrice = orderPrice;
	}

    
}
