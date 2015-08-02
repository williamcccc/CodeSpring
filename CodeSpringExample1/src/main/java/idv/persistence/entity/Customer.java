package idv.persistence.entity;

public class Customer {
	
		private int customer_id;
		private String customer_name;
		private String customer_description; 
	  
	  public int getCustomerId() {
	   return customer_id;
	  }
	  public void setCustomerId(int customerId) {
	   this.customer_id = customerId;
	  }
	  public String getCustomerName() {
	   return customer_name;
	  }
	  public void setCustomerName(String customerName) {
	   this.customer_name = customerName;
	  }
	  public String getCustomerDescription() {
	   return customer_description;
	  }
	  public void setCustomerDescription(String customerDescription) {
	   this.customer_description = customerDescription;
	  }
	  

	  
	  @Override
	  public String toString() {
	   return "Customer [customer_id=" + customer_id + ", customer_name=" + customer_name
	     + ", customer_description=" + customer_description + "]";
	  }
	   
}
