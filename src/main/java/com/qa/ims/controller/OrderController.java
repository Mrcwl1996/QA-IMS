package com.qa.ims.controller;


import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;


public class OrderController implements CrudController<Order>{

	public static final Logger LOGGER = LogManager.getLogger();
	private OrderDAO orderDAO;
	private Utils utils;
	private ItemDAO itemDAO;

	public OrderController(OrderDAO orderDAO, ItemDAO itemDAO, Utils utils) {
		super();
		this.orderDAO = orderDAO;
		this.itemDAO = itemDAO;
		this.utils = utils;
				
	}

	@Override
	public List<Order> readAll() {
		List<Order> orders = orderDAO.readAll();
		for (Order order : orders) {
			LOGGER.info(order.toString());
		}
		return orders;
	}

	@Override
	public Order create() {
		LOGGER.info("Please enter a Customer ID");
		Long customerID = utils.getLong();
		Order order = orderDAO.create(new Order(customerID));
		LOGGER.info("Order created");
		return order;
	}

	@Override
	public Order update() {
		LOGGER.info("Please enter the id of the order you would like to update");
		Long orderID = utils.getLong();
		LOGGER.info("Please enter the id of the customer on the order");
		Long customerID = utils.getLong();
		String addRemove = "";
		while (!addRemove.equals("ADD") && !addRemove.equals("REMOVE")) {
			LOGGER.info("please enter ADD or REMOVE");
			addRemove = utils.getString().toUpperCase(); 
		}
		LOGGER.info("please enter itemID that you would like to update");
		Long itemID = utils.getLong();
		Order order = orderDAO.readOrder(orderID);
		order.setCustomerID(customerID);
		if (addRemove.equals("ADD")) {
			order.addItem(itemDAO.readItem(itemID));
		}else {
			order.removeItem(itemDAO.readItem(itemID));
		}
		order = orderDAO.update(order);
		LOGGER.info("Order Updated");
		return order;
		
	}

	@Override
		public int delete() {
			LOGGER.info("Please enter the id of the order you would like to delete");
			Long orderID = utils.getLong();
			return orderDAO.delete(orderID);
	}
	
	

	
	
	
	
	
}
