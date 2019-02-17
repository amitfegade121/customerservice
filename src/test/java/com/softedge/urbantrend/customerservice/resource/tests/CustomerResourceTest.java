package com.softedge.urbantrend.customerservice.resource.tests;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.softedge.urbantrend.customerservice.exception.CustomerNotFoundException;
import com.softedge.urbantrend.customerservice.model.Customer;
import com.softedge.urbantrend.customerservice.model.Customer.CustomerStatus;
import com.softedge.urbantrend.customerservice.resource.CustomerResource;
import com.softedge.urbantrend.customerservice.service.CustomerService;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = CustomerResource.class)
public class CustomerResourceTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CustomerService customerService;

	@Test
	public void testRegisterNewCustomerWithSuccess() throws Exception {

		String customerData = "{\"customerName\":\"Tushar\",\"customerPassword\":\"root\",\"customerContact\":\"1231231231\",\"customerEmail\":\"test@123.com\",\"gender\":\"male\",\"customerStatus\":\"ACTIVE\",\"customerAddress\":[{\"addressLine1\":\"Vashi Infotech park\",\"addressLine2\":\"near raghuleela mall\",\"city\":\"Vashi\",\"state\":\"Maharashtra\",\"postalCode\":\"400007\"}]}";

		Customer customer = new Customer();
		customer.setCustomerName("Tushar");
		customer.setCustomerPassword("root");
		customer.setCustomerContact("1231231231");
		customer.setCustomerEmail("test@123.com");
		customer.setGender("male");
		customer.setCustomerStatus(CustomerStatus.ACTIVE);

		when(customerService.registerNewCustomer(Mockito.isA(Customer.class))).thenReturn(customer);

		mockMvc.perform(post("/api/customers").contentType(MediaType.APPLICATION_JSON_UTF8).content(customerData))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.customerName").value("Tushar"))
				.andExpect(jsonPath("$.customerContact").value("1231231231"))
				.andExpect(jsonPath("$.gender").value("male"));

	}
	
	@Test
	public void testFindCustomerByIdWithSuccess() throws Exception {
		
		Customer customer = new Customer();
		customer.setCustomerId(121);
		customer.setCustomerName("Tushar");
		customer.setCustomerPassword("root");
		customer.setCustomerContact("1231231231");
		customer.setCustomerEmail("test@123.com");
		customer.setGender("male");
		customer.setCustomerStatus(CustomerStatus.ACTIVE);
		
		when(customerService.findCustomerById(121)).thenReturn(customer);
		
		mockMvc.perform(get("/api/customers/121").accept(MediaType.APPLICATION_JSON))
		             .andDo(print())
		             .andExpect(status().isOk())
		             .andExpect(jsonPath("$.customerName").value("Tushar"))
		             .andExpect(jsonPath("$.customerContact").value("1231231231"));
	}
}
