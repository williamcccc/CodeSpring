package idv.controller;

import idv.persistence.dao.CustomerManagerService;
import idv.persistence.dao.exception.InvalidCustomerIdException;
import idv.persistence.entity.Customer;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller 
@RequestMapping("/customerManagerApp")
public class CustomerManagerController {
 
	CustomerManagerService customerManagerService=new CustomerManagerService();

	@RequestMapping(value = { "/home"}, method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView model = new ModelAndView();
		model.setViewName("customer/customers");
		return model;
	}
	@RequestMapping(value = { "/customer/{customerId}"}, method = RequestMethod.GET)
	public ModelAndView customerDetail(@PathVariable int customerId) throws InvalidCustomerIdException {

		ModelAndView model = new ModelAndView();
		model.addObject("customerObject", customerManagerService.getCustomerById(customerId));
		model.setViewName("customer/customerDetail");				
		return model;
	}	
	@RequestMapping(value="/customers",method = RequestMethod.GET,headers="Accept=application/json") 
	@ResponseBody 
	public List<Customer> getAllCustomers() {	 
		List<Customer> customerList=customerManagerService.getAllCustomers();
		return customerList;
	}
	@RequestMapping(value = { "/customer/json/{customerId}"}, method = RequestMethod.GET,headers="Accept=application/json")
	@ResponseBody
	public Customer jsonCustomerDetail(@PathVariable int customerId) throws InvalidCustomerIdException {
		return customerManagerService.getCustomerById(customerId);		
	}		
	@RequestMapping(value="/customer/{customerId}",method = RequestMethod.DELETE,headers="Accept=application/json")
	@ResponseBody 
	public List<Customer> deleteCustomer(@PathVariable int customerId) throws InvalidCustomerIdException {		 
		 //System.out.println("size:"+customerManagerService.getAllCustomers().size());	 
		 customerManagerService.deleteCustomer(customerId);	 
		 //System.out.println("size:"+customerManagerService.getAllCustomers().size());	 
		 return customerManagerService.getAllCustomers();		 
	} 
	

	@RequestMapping(value="/customer/insert",method = RequestMethod.POST,headers="Accept=application/json")
	@ResponseBody 
	public List<Customer> addCustomer(@RequestBody Customer customer) throws InvalidCustomerIdException {
	 
		Customer c = new Customer();
		c.setCustomerName(customer.getCustomerName());
		c.setCustomerDescription(customer.getCustomerDescription());

		customerManagerService.addCustomer(c);
	
		return customerManagerService.getAllCustomers();	
	}
 
		
	
	@ExceptionHandler(InvalidCustomerIdException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorMessage handleInternalServerErrorException(InvalidCustomerIdException e, HttpServletRequest req) {
	    return new ErrorMessage(e);
	}	
}
