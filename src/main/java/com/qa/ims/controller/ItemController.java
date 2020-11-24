package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.Utils;

/**
 * Takes in item details for CRUD functionality
 *
 */
public class ItemController implements CrudController<Item> {

	public static final Logger LOGGER = LogManager.getLogger();

	private ItemDAO itemDAO;
	private Utils utils;

	public ItemController(ItemDAO itemDAO, Utils utils) {
		super();
		this.itemDAO = itemDAO;
		this.utils = utils;
		
	}
	
	/**
	 * Reads all customers to the logger
	 */
	@Override
	public List<Item> readAll() {
		List<Item> items = itemDAO.readAll();
		for (Item item : items) {
			LOGGER.info(item.toString());
		}
		return items;
	}
	
	/**
	 * Creates a customer by taking in user input
	 * private Long itemID;
	private String itemName; 
	private Double itemPrice;
	
	 */
	@Override 
	public Item create() {
		LOGGER.info("Please enter a Item ID");
		Long itemID = utils.getLong();		
		LOGGER.info("Please enter a Item Name");
		String itemName = utils.getString();
		LOGGER.info("Please enter a Price");
		Double itemPrice = utils.getDouble();
		Item item = ItemDAO.create(new Item(itemID, itemName, itemPrice));
		LOGGER.info("Item created");
		return item;
	}
	/**
	 * Updates an existing item by taking in user input
	 */
	@Override
	public Item update() {
		LOGGER.info("Please enter the id of the item you would like to update");
		Long itemID = utils.getLong();
		LOGGER.info("Please enter the item name");
		String itemName = utils.getString();
		LOGGER.info("Please enter the price of the item");
		Double itemPrice = utils.getDouble();
		Item item = ItemDAO.update(new Item(itemID, itemName, itemPrice));
		LOGGER.info("Item Updated");
		return item;
	}
	
	/**
	 * Deletes an existing item by the id of the customer
	 * 
	 * @return
	 */
	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the item you would like to delete");
		Long itemID = utils.getLong();
		return ItemDAO.delete(itemID);
	}
	
}	
