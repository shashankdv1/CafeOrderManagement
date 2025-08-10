package Cafe;
public class AddItemsToCart {
private String email;
private String id;
private String name;
private String price;
public AddItemsToCart(String id,String name,String price)
{
	
	this.id=id;
	this.name=name;
	this.price=price;
}
public AddItemsToCart()
{
	
}
public String getEmail()
{
	return email;
}
public void setEmail(String email)
{
	this.email= email;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getPrice() {
	return price;
}
public void setPrice(String price) {
	this.price = price;
}

}
