package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.DBUtils;

public class OrderDAO implements Dao<Order>{

	public static final Logger LOGGER = LogManager.getLogger();

	public Order modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long orderID = resultSet.getLong("orderID");
		Long customerID = resultSet.getLong("customerID");		
		return new Order(orderID, customerID);
	
	}
	
	/**
	 * Reads all orders from the database
	 * 
	 * @return A list of orders
	 */
	@Override
	public List<Order> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("Select * from orders");) {
			List<Order> orders = new ArrayList<>();
			while (resultSet.next()) {
				Order order = modelFromResultSet(resultSet);
				order = this.readOrderItems(order); 
				orders.add(order);
			}
			return orders;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}
		
	public Order readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders ORDER BY orderID DESC LIMIT 1");) {
			resultSet.next();
			Order order = modelFromResultSet(resultSet);
			return readOrderItems(order);			
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
		
	}
	
	/**
	 * Creates a order in the database
	 * 
	 * @param order - takes in a customer object. id will be ignored
	 */
	
	public Order create(Order order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("INSERT INTO orders(`customerID`) VALUES(" + order.getCustomerID() + ");");
			Order order2 = this.readLatest(); 
			Long orderID = order2.getOrderID();
			for(Item item : order2.getItemList()) { 
				this.insertItemEntry(item , orderID);
			}
			return this.readOrder(orderID);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage()); 
		}
		return null;
	}
	
	public void insertItemEntry(Item item, Long orderID) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("INSERT INTO orders_items (`orderID`, `itemID`) VALUES (" + orderID + ","+ item.getItemID() + ")");
		}
		catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage()); 
		}		
	}
	
	public Order readOrderItems(Order order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSetItems = statement.executeQuery("SELECT * FROM orders_items where orderID = " + order.getOrderID());
				) {
			ItemDAO itemDAO = new ItemDAO();
			while (resultSetItems.next()) {
				Long itemID = resultSetItems.getLong("itemID");
				Item item =	itemDAO.readItem(itemID);
				order.addItem(item); 		
			}
			return order;
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		
		return null;
	}
	
	
	public Order readOrder(Long orderID) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSetOrders = statement.executeQuery("SELECT * FROM orders where orderID = " + orderID);
				) {
			resultSetOrders.next();
			Order order = this.modelFromResultSet(resultSetOrders);
			return this.readOrderItems(order);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		
		return null;
	}
	/**
	 * Updates a order in the database
	 */

	@Override
	public Order update(Order order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("UPDATE orders set orderID = " + order.getOrderID() + ", customerID =" + order.getCustomerID() +" WHERE orderID = " + order.getOrderID() );
			statement.executeUpdate("DELETE FROM orders_items WHERE orderID = " + order.getOrderID() );
			for(Item item : order.getItemList()) {
				this.insertItemEntry(item, order.getOrderID());
			}
			return readOrder(order.getOrderID());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	public int delete(long orderID) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("DELETE FROM orders_items WHERE orderID = " + orderID );
			return statement.executeUpdate("DELETE FROM orders WHERE orderID = " + orderID);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}
}
