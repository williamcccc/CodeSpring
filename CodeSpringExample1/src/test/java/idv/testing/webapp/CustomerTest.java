package idv.testing.webapp;

import idv.persistence.dao.CustomerManagerService;
import idv.persistence.entity.Customer;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@ContextConfiguration(locations = { "classpath:spring-test-config.xml" })
public class CustomerTest extends AuthenticatedContextTests {	
		
	private String uri = "";
	@Autowired
	private CustomerManagerService customerService;
	@Value("${server.customer.path}")
	private String CUSTOMER_SERVICE_PATH;	
	private RestTemplate statefulRest;
	
	@BeforeClass
	public void setup() {		
		super.setup();
		statefulRest = super.getStatefulRest();	
	}	
	@Test
	public void getInvalidSingleCustomerTest() {
		try		
		{
			uri = this.getCustomerRelativePath("customer/json/{id}");			
			statefulRest.getForObject(uri, Customer.class, this.getNewMapWithId("100"));
		}catch(HttpClientErrorException ex)
		{
			Assert.assertEquals(ex.getStatusCode().toString(), HttpStatus.BAD_REQUEST.toString());			
			System.out.println(ex.getResponseBodyAsString());
		}		
	}
	@Test
	public void getSingleCustomerPageTest() {
		uri = this.getCustomerRelativePath("customer/{id}");	     		
		String result = statefulRest.getForObject(uri, String.class, this.getNewMapWithId("1"));		
		System.out.println(result);
	}		
	@Test
	public void getAllCustomerTest() {		
		uri = this.getCustomerRelativePath("customers");		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<LinkedHashMap> emps = statefulRest.getForObject(uri, List.class);

		Assert.assertNotNull(emps);
		Assert.assertTrue(emps.size() >=1);
	}
	@Test
	public void getValidSingleCustomerTest() {
		uri = this.getCustomerRelativePath("customer/json/{id}");
		Customer c = statefulRest.getForObject(uri, Customer.class, this.getNewMapWithId("1"));
		Assert.assertNotNull(c);
		Assert.assertEquals(1, c.getCustomerId());
	}			
	@SuppressWarnings("unchecked")
	public void deleteValidSingleCustomerTest() {
		
		int size = 0;
		
		// get size for emps
		uri = this.getCustomerRelativePath("customers");				
		@SuppressWarnings("rawtypes")
		List<LinkedHashMap> emps = statefulRest.getForObject(uri, List.class);

		size = emps.size();
		Assert.assertNotNull(emps);
		Assert.assertTrue(emps.size() >=1);
		
		//delete one customer
		uri = this.getCustomerRelativePath("customer/{id}");		
		statefulRest.delete(uri, this.getNewMapWithId("1"));

		//size = size + 1...
		uri = this.getCustomerRelativePath("customers");			
		emps = statefulRest.getForObject(uri, List.class);
		
		Assert.assertNotNull(emps);
		Assert.assertTrue(emps.size() + 1 == size);
		
		Customer c = new Customer();
		c.setCustomerId(1);
		c.setCustomerName("Name");
		c.setCustomerDescription("Description");
		try		
		{
			customerService.addCustomerWithId(c);
		}catch(Exception e)
		{
			e.printStackTrace();
		}		
	}		
	//post insert one customer and check customers quantity must plus 1
	@SuppressWarnings("unchecked")
	@Test
	public void postInsertSingleCustomerTest() {

		int size = 0;
		String allCustomersUri = this.getCustomerRelativePath("customers");
		uri = this.getCustomerRelativePath("customer/insert");				
		
		@SuppressWarnings("rawtypes")
		List<LinkedHashMap> emps = statefulRest.getForObject(allCustomersUri, List.class);
		//check for initial data
		Assert.assertNotNull(emps);
		size = emps.size() + 1;		
				
		Customer c = new Customer();
		c.setCustomerDescription("myDescription");
		c.setCustomerName("postInsertSingleCustomerTest");
				
		// Make the HTTP POST request, marshaling the request to JSON, and the response to a List<LinkedHashMap>	
		emps = statefulRest.postForObject(uri, c, List.class);

		Assert.assertNotNull(emps);
		Assert.assertTrue(emps.size() >= size);		
	}		


	
	

	private Map<String, String> getNewMapWithId(String id)
	{
	    Map<String, String> params = new HashMap<String, String>();
	    params.put("id", id);
	    return params;
	}
	
	private String getCustomerRelativePath(String s)
	{
		return super.getApplicationUrl(CUSTOMER_SERVICE_PATH + s);
	}
}
