package idv.testing.persistence.dao;

import java.util.List;

import idv.persistence.dao.CustomerManagerService;
import idv.persistence.dao.exception.InvalidCustomerIdException;
import idv.persistence.entity.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
@ContextConfiguration(locations = { "classpath:spring-test-config.xml" })
public class CustomerDaoTests extends AbstractTestNGSpringContextTests {
	
	@Autowired
	private CustomerManagerService customerService;
	
	@Test
	public void testGetAllCustomers() {
		
		List<Customer> taskList = customerService.getAllCustomers();
		Assert.assertNotNull(taskList);

	}
	@Test(expectedExceptions = InvalidCustomerIdException.class)
	public void testGetInvalidCustomer() throws InvalidCustomerIdException
	{
		customerService.getCustomerById(2000);
	}	
	@Test(expectedExceptions = InvalidCustomerIdException.class)
	public void testDeleteInvalidCustomer() throws InvalidCustomerIdException
	{
		customerService.deleteCustomer(3000);
	}	
	@Test(expectedExceptions = InvalidCustomerIdException.class)
	public void testAddExistsCustomerId() throws InvalidCustomerIdException
	{
		Customer c = new Customer();
		c.setCustomerId(1);
		c.setCustomerName("Name");
		c.setCustomerDescription("Description");
		customerService.addCustomerWithId(c);
	}
	@Test
	public void testAddCustomerWithId() throws InvalidCustomerIdException
	{
		Customer c = new Customer();
		c.setCustomerId(3030);
		c.setCustomerName("Name");
		c.setCustomerDescription("Description");
		customerService.addCustomerWithId(c);
		
		customerService.deleteCustomer(3030);
	}	
	@Test
	public void testAddCustomer() throws InvalidCustomerIdException
	{
		Customer c = new Customer();
		c.setCustomerId(1000);
		c.setCustomerName("Name");
		c.setCustomerDescription("Description");
		customerService.addCustomer(c);
	}
	@Test
	public void testDeleteCustomer() throws InvalidCustomerIdException
	{
		customerService.deleteCustomer(1);
		
		Customer c = new Customer();
		c.setCustomerId(1);
		c.setCustomerName("Name");
		c.setCustomerDescription("Description");
		customerService.addCustomerWithId(c);
	}
	
}
