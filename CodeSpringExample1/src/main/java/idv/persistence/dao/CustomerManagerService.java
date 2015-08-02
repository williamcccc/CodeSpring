package idv.persistence.dao;

import idv.persistence.dao.exception.InvalidCustomerIdException;
import idv.persistence.entity.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class CustomerManagerService {
	
	private static HashMap<Integer,Customer> hm = CustomerManagerService.init();
	private static Integer idIndex = 4;
	
	public CustomerManagerService() {

	}

	private static HashMap<Integer,Customer> init()
	{
		HashMap<Integer,Customer> hm = new HashMap<Integer,Customer>();
		
		Customer t = new Customer();
		t.setCustomerId(1);
		t.setCustomerName("Jeff Liu");
		t.setCustomerDescription("Jeff Liu is a handsome man");
		hm.put(1, t);
		
		t = new Customer();
		t.setCustomerId(2);
		t.setCustomerName("Fitt Chiao");
		t.setCustomerDescription("Fitt Chiao works hard");
		hm.put(2, t);

		t = new Customer();
		t.setCustomerId(3);
		t.setCustomerName("Ms Strong");
		t.setCustomerDescription("A Strong women");
		hm.put(3, t);
		
		return hm;
	}
 
	public void addCustomer(Customer customer) throws InvalidCustomerIdException {
	  
		customer.setCustomerId(idIndex);
		CustomerManagerService.hm.put(idIndex, customer);
		idIndex++;
	}
 
	public void addCustomerWithId(Customer customer) throws InvalidCustomerIdException {
		Customer hmCustomer = hm.get(customer.getCustomerId());
		if(customer.getCustomerId() < 0) throw new InvalidCustomerIdException("CustomerId should be > 0!");
		if(customer.getCustomerId() > 0 && hmCustomer != null) throw new InvalidCustomerIdException("Customer already exists!");
	  
		CustomerManagerService.hm.put(customer.getCustomerId(), customer);
		idIndex++;
	}
	
	public void deleteCustomer(int customerId) throws InvalidCustomerIdException {
	 
	  Customer hmCustomer = hm.get(customerId);
	  if(hmCustomer == null) throw new InvalidCustomerIdException("No such Customer");
	  hm.remove(hmCustomer.getCustomerId());	   
	}
 
 	public List<Customer> getAllCustomers() {
		List<Customer> customerList = new ArrayList<Customer>();
	  
		for(Customer hmCustomer : hm.values())
		{
			Customer task = new Customer();
			task.setCustomerId(hmCustomer.getCustomerId());
			task.setCustomerName(hmCustomer.getCustomerName());
			task.setCustomerDescription(hmCustomer.getCustomerDescription());
			customerList.add(task);
		}	
		return customerList;
 	}
 
	public Customer getCustomerById(int customerId) throws InvalidCustomerIdException {
		
		Customer customer = new Customer();		
		Customer hmCustomer = hm.get(customerId);
		if(hmCustomer == null) throw new InvalidCustomerIdException("No such Customer");
		customer.setCustomerId(customerId);;
		customer.setCustomerDescription(hmCustomer.getCustomerDescription());
		customer.setCustomerName(hmCustomer.getCustomerName());		  
		return customer;

	}
}
