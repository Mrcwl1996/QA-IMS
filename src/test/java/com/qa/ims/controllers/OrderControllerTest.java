package com.qa.ims.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.controller.CustomerController;
import com.qa.ims.controller.OrderController;
import com.qa.ims.persistence.dao.CustomerDAO;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)

public class OrderControllerTest {

	@Mock
	private Utils utils;

	@Mock
	private OrderDAO DAO;

	@InjectMocks
	private OrderController controller;
	
	@Test
	public void testCreate() {
		
		final Long orderID = 1L; 
		final Long customerID = 1L;
		final Order created = new Order( orderID, customerID);
	
		Mockito.when(utils.getLong()).thenReturn(orderID);
		Mockito.when(utils.getLong()).thenReturn(customerID);
		Mockito.when(DAO.create(any(Order.class))).thenReturn(created);

		assertEquals(created , controller.create());
		
		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(DAO, Mockito.times(1)).create(any(Order.class));
	}
	
	@Test
	public void testReadAll() {
		List<Order> orders = new ArrayList<>();
		orders.add(new Order(1L, 1L));

		Mockito.when(DAO.readAll()).thenReturn(orders);

		assertEquals(orders, controller.readAll());

		Mockito.verify(DAO, Mockito.times(1)).readAll();
	}
	
	
	
	@Test
	public void testDelete() {
		final long ID = 1L;

		Mockito.when(utils.getLong()).thenReturn(ID);
		Mockito.when(DAO.delete(ID)).thenReturn(1);

		assertEquals(1L, this.controller.delete());

		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(DAO, Mockito.times(1)).delete(ID);
	
	}
	
}
