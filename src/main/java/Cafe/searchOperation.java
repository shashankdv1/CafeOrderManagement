package Cafe;

import java.sql.Blob;

public class searchOperation {
	
	private String itemName;
	private String itemType;
	private Blob itemImage;
	private String description;
	private int price;
	searchOperation(String itemName,String itemType,Blob itemImage,String description,int price)
	{
		this.itemName=itemName;
		this.itemType=itemType;
		this.itemImage=itemImage;
		this.description=description;
		this.price=price;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public Blob getItemImage() {
		return itemImage;
	}
	public void setItemImage(Blob itemImage) {
		this.itemImage = itemImage;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
}
