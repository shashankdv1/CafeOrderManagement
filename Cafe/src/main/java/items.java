package Cafe;

import java.sql.Blob;

public class items {
	private int item_Id;
	private String item_name;
	private String item_description;
	private String item_price;
	 private Blob item_image;
public items(int item_Id,String item_name,String item_description, Blob item_image,String item_price){
	this.item_Id=item_Id;
	this.item_name=item_name;
	this.item_description=item_description;
	this.item_image=item_image;
	this.item_price=item_price;
}
public int getId()
{
	return item_Id;
}
public String getName()
{
	return item_name;
}
public String getItem()
{
	return item_description;
}
public Blob getImage()
{
	return item_image;
}
public String getPrice()
{
	return item_price;
}

}
