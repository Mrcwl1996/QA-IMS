package com.qa.ims.persistence.domain;

public class Item {
	
	private Long itemID;
	private String itemName; 
	private Double itemPrice;
	
	
	
	public Item(Long itemID, String itemName, Double itemPrice) {
		super();
		this.itemID = itemID;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
	}


	//getters and Setter
	

	public Long getItemID() {
		return itemID;
	}



	public void setItemID(Long itemID) {
		this.itemID = itemID;
	}



	public String getItemName() {
		return itemName;
	}



	public void setItemName(String itemName) {
		this.itemName = itemName;
	}



	public Double getItemPrice() {
		return itemPrice;
	}



	public void setItemPrice(Double itemPrice) {
		this.itemPrice = itemPrice;
	} 
	
	
	@Override
	public String toString() {
		return "item ID:" + itemID + " Item name:" + itemName + " Price:" + itemPrice;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (itemID == null) {
			if (other.itemName != null)
				return false;
		} else if (!itemName.equals(other.itemName))
			return false;
		if (itemID == null) {
			if (other.itemID != null)
				return false;
		} else if (!itemID.equals(other.itemID))
			return false;
		if (itemPrice == null) {
			if (other.itemPrice != null)
				return false;
		} else if (!itemPrice.equals(other.itemPrice))
			return false;
		return true;
	}
}
