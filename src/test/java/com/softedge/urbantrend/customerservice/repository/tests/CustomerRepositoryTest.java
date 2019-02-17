package com.softedge.urbantrend.customerservice.repository.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.NoSuchElementException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.softedge.urbantrend.customerservice.model.Customer;
import com.softedge.urbantrend.customerservice.model.Customer.CustomerStatus;
import com.softedge.urbantrend.customerservice.repository.CustomerRepository;


/*
        @DataJpaTest provides some standard setup needed for testing the persistence layer:

		1) configuring H2, an in-memory database
		2) setting Hibernate, Spring Data, and the DataSource
		3) performing an @EntityScan
		4) turning on SQL logging
*/

/*
 		To carry out some DB operation,we need some records already setup in our database.
		To setup such data,we can use TestEntityManager.The TestEntityManager provided by Spring Boot is an 
		alternative to the standard JPA EntityManager that provides methods commonly used when writing tests.
*/

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Test
	public void testAuthenticateCustomerWithValidCredentials() {
		Customer customer = new Customer();
		customer.setCustomerEmail("john.doe@gmail.com");
		customer.setCustomerPassword("john@123");
		customer.setCustomerName("John Doe");
		customer.setCustomerStatus(CustomerStatus.ACTIVE);
		customer.setGender("Male");
		
		entityManager.persist(customer);
		entityManager.flush();
		
		Customer newCustomer = customerRepository.
			authenticateCustomer("john.doe@gmail.com", "john@123");
		
		assertNotNull(newCustomer);		
	}
	
	@Test
	public void testChangeCustomerStatusWithSuccess() {
		Customer customer = new Customer();
		customer.setCustomerEmail("john.doe@gmail.com");
		customer.setCustomerPassword("john@123");
		customer.setCustomerName("John Doe");
		customer.setCustomerStatus(CustomerStatus.ACTIVE);
		customer.setGender("Male");
		
		Customer customer1 = entityManager.persist(customer);
		entityManager.flush();
		
		customerRepository.changeCustomerStatus(CustomerStatus.INACTIVE, customer1.getCustomerId());
				
		assertEquals(CustomerStatus.INACTIVE, 
				customerRepository.findById(customer1.getCustomerId()).get().getCustomerStatus());
	
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testDeleteCustomerSuccessfully() {
		Customer customer = new Customer();
		customer.setCustomerEmail("john.doe@gmail.com");
		customer.setCustomerPassword("john@123");
		customer.setCustomerName("John Doe");
		customer.setCustomerStatus(CustomerStatus.ACTIVE);
		customer.setGender("Male");
		
		Customer customer1 = entityManager.persist(customer);
		entityManager.flush();
		
		assertNotNull(customerRepository.findById(customer1.getCustomerId()).get());
		entityManager.remove(customer1);
		entityManager.flush();
		
		customerRepository.findById(customer1.getCustomerId()).get();
		
	}
}
















