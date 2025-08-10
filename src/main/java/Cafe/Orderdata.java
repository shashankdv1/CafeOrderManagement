package Cafe;

public class Orderdata {
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
	public float getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(int orderPrice) {
		this.orderPrice = orderPrice;
	}

    
}
