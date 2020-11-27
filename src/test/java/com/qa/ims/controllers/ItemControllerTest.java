package com.qa.ims.controllers;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.any;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.qa.ims.controller.ItemController;
import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.Utils;



@RunWith(MockitoJUnitRunner.class)
public class ItemControllerTest{

	@Mock
	private ItemDAO DAO;
	
	@Mock
	private Utils utils;
	
	@InjectMocks
	private ItemController controller;
	
	@Test
	public void testCreate() {
		
		final String itemNAME = "PS2"; 
		final Double itemPrice = 200.0;
		final Item created = new Item( itemNAME, itemPrice);
	
		Mockito.when(utils.getString()).thenReturn(itemNAME);
		Mockito.when(utils.getDouble()).thenReturn(itemPrice);
		Mockito.when(DAO.create(any(Item.class))).thenReturn(created);

		assertEquals(created , controller.create());
		
		Mockito.verify(utils, Mockito.times(1)).getString();
		Mockito.verify(utils, Mockito.times(1)).getDouble();
		Mockito.verify(DAO, Mockito.times(1)).create(any(Item.class));
	}
	
	@Test
	public void testReadAll() {
		List<Item> items = new ArrayList<>();
		items.add(new Item(1L, "PS3", 200.00));

		Mockito.when(DAO.readAll()).thenReturn(items);

		assertEquals(items, controller.readAll());

		Mockito.verify(DAO, Mockito.times(1)).readAll();
	}

	
	@Test
	public void testUpdate() {
		Item updated = new Item(1L, "PS1", 100.00);

		Mockito.when(this.utils.getLong()).thenReturn(1L);
		Mockito.when(this.utils.getString()).thenReturn("PS1");
		Mockito.when(this.utils.getDouble()).thenReturn(100.00);
		Mockito.when(this.DAO.update(updated)).thenReturn(updated);

		assertEquals(updated, this.controller.update());

		Mockito.verify(this.utils, Mockito.times(1)).getLong();
		Mockito.verify(this.utils, Mockito.times(1)).getString();
		Mockito.verify(this.utils, Mockito.times(1)).getDouble();
		Mockito.verify(this.DAO, Mockito.times(1)).update(updated);
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
	