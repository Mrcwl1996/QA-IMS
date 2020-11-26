package com.qa.ims.persistence.domain;

import java.util.ArrayList;
import java.util.List;

public class Order {

	private Long orderID;
	private Long customerID;
	private List<Item> itemList;
	private Double totalPrice;
	
	
	public Order(Long customerID) {
		super();
		this.customerID = customerID;
		this.itemList = new ArrayList<>();
		this.totalPrice = 0.0;
	}
	
	
	public Order(Long orderID, Long customerID) {
		super();
		this.orderID = orderID;
		this.itemList = new ArrayList<>();
		this.customerID = customerID;
		this.totalPrice = 0.0;
		
	}


	public void calcTotalPrice() {
		Double currentTotalPrice = 0.0;
		for(Item item: this.itemList) {
			currentTotalPrice += item.getItemPrice();
		}
		this.totalPrice = currentTotalPrice;
	}
	
	public Long getOrderID() {
		return this.orderID;
	}

	public void setOrderID(Long orderID) {
		this.orderID = orderID;
	}

	public Long getCustomerID() {
		return this.customerID;
	}

	public void setCustomerID(Long customerID) {
		this.customerID = customerID;
	}

	public List<Item> getItemList() {
		return this.itemList;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}

	public Double getTotalPrice() {
		this.calcTotalPrice();
		return this.totalPrice;
	}

	public String toString() {
		String itemListSTR = "";
		for(Item item: this.itemList) {
			itemListSTR += "\n" + item.toString();
		}
		return "Order ID: " + orderID + "\nCustomer ID: " + customerID + "\nItem List:" + itemListSTR + "\nTotal Price: " + totalPrice;
		
	}
	
	public void addItem(Item item) {
		this.itemList.add(item);
		this.totalPrice = this.totalPrice + item.getItemPrice();
	}
	
	public void removeItem(Item item) {
		this.itemList.remove(item);
		this.totalPrice = this.totalPrice - item.getItemPrice();
	}
	
	
	
	
	
}