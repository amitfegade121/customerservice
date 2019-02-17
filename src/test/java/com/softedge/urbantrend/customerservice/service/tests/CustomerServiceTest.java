package com.softedge.urbantrend.customerservice.service.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.softedge.urbantrend.customerservice.exception.CustomerNotFoundException;
import com.softedge.urbantrend.customerservice.model.Customer;
import com.softedge.urbantrend.customerservice.model.Customer.CustomerStatus;
import com.softedge.urbantrend.customerservice.repository.CustomerRepository;
import com.softedge.urbantrend.customerservice.service.CustomerService;
import com.softedge.urbantrend.customerservice.service.impl.CustomerServiceImpl;

@RunWith(SpringRunner.class)
public class CustomerServiceTest {

	@TestConfiguration
	static class CustomerServiceImplTestContextConfiguration {

		@Bean
		public CustomerService getCustomerService() {
			return new CustomerServiceImpl();
		}
	}

	@Autowired
	private CustomerService customerService;

	@MockBean
	private CustomerRepository customerRepository;

	@Test
	public void testAuthenticateCustomerWithValidCredentials() throws CustomerNotFoundException {

		Customer customer = new Customer();
		customer.setCustomerEmail("john.doe@gmail.com");
		customer.setCustomerPassword("john@123");
		customer.setCustomerName("John Doe");
		customer.setCustomerStatus(CustomerStatus.ACTIVE);
		customer.setGender("Male");

		when(customerRepository.authenticateCustomer("john.doe@gmail.com", "john@123"))
		.thenReturn(customer);
		
		assertNotNull(customerService.authenticateCustomer(customer));
		verify(customerRepository, times(1))
		               .authenticateCustomer("john.doe@gmail.com", "john@123");
	}
	
	@Test
	public void testChangeCustomerStatusWithSuccess() {
		Customer customer = new Customer();
		customer.setCustomerId(101);
		customer.setCustomerEmail("john.doe@gmail.com");
		customer.setCustomerPassword("john@123");
		customer.setCustomerName("John Doe");
		customer.setCustomerStatus(CustomerStatus.INACTIVE);
		customer.setGender("Male");
		
		/*
		 * doAnswer()
		 * when(customerRepository).changeCustomerStatus(CustomerStatus.ACTIVE, 101);
		 */
		
		assertEquals(CustomerStatus.ACTIVE, customer.getCustomerStatus());
	}
	
}
