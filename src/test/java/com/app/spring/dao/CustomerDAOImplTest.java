package com.app.spring.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.app.spring.model.Customer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
@Transactional
public class CustomerDAOImplTest {

	@Autowired
	private CustomerDAO customerDAO;

	@Test
	public void addCustomerPersistsAndListsIt() {
		Customer c = new Customer();
		c.setName("Ada Lovelace");
		c.setAddress("London");
		customerDAO.addCustomer(c);

		List<Customer> customers = customerDAO.listCustomers();
		assertEquals(1, customers.size());
		assertEquals("Ada Lovelace", customers.get(0).getName());
	}

	@Test
	public void updateAndRemoveCustomer() {
		Customer c = new Customer();
		c.setName("Grace Hopper");
		c.setAddress("New York");
		customerDAO.addCustomer(c);

		Customer saved = customerDAO.listCustomers().get(0);
		saved.setAddress("Arlington");
		customerDAO.updateCustomer(saved);

		Customer fetched = customerDAO.getCustomerById(saved.getId());
		assertEquals("Arlington", fetched.getAddress());

		customerDAO.removeCustomer(saved.getId());
		assertTrue(customerDAO.listCustomers().isEmpty());
	}

}
