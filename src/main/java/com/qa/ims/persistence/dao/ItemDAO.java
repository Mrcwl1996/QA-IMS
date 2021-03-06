package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.DBUtils;



public class ItemDAO implements Dao<Item> {
	
	public static final Logger LOGGER = LogManager.getLogger();

	@Override
	public Item modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long itemID = resultSet.getLong("ItemID");
		String itemName = resultSet.getString("ItemName");
		Double itemPrice = resultSet.getDouble("ItemPrice");
		return new Item(itemID, itemName, itemPrice);
	} 

	/**
	 * Reads all items from the database
	 * 
	 * @return A list of items
	 */
	@Override
	public List<Item> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * from items");) {
			List<Item> items = new ArrayList<>();
			while (resultSet.next()) {
				items.add(modelFromResultSet(resultSet));
			}
			return items;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}
	public Item readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM items ORDER BY ItemID DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Creates a item in the database
	 * 
	 * @param item - takes in a item object. itemID will be ignored
	 */
	
	public Item create(Item item) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("INSERT INTO items(ItemName, ItemPrice) values('" + item.getItemName() + "','" +item.getItemPrice() + "')");
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	public Item readItem(Long itemID) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM items where ItemID = " + itemID);) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	/**
	 * Updates a customer in the database
	 * 
	 * @param customer - takes in a item object, the itemID field will be used to
	 *                 update that customer in the database
	 * @return
	 */
	public Item update(Item item) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("update items set ItemPrice ='" + item.getItemPrice() + "', ItemName ='"
					+ item.getItemName()+ "' where ItemID =" + item.getItemID());
			
			return readItem(item.getItemID());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	/**
	 * Deletes a item in the database
	 * 
	 * @param id - id of the customer
	 */

	@Override
	public int delete(long itemID) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			return statement.executeUpdate("DELETE FROM items WHERE ItemID = " + itemID);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}

}
