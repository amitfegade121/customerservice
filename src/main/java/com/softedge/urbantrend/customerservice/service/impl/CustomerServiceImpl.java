package com.softedge.urbantrend.customerservice.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softedge.urbantrend.customerservice.exception.CustomerNotFoundException;
import com.softedge.urbantrend.customerservice.model.Customer;
import com.softedge.urbantrend.customerservice.model.Customer.CustomerStatus;
import com.softedge.urbantrend.customerservice.repository.CustomerRepository;
import com.softedge.urbantrend.customerservice.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Customer registerNewCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public Customer authenticateCustomer(Customer customer) throws CustomerNotFoundException {
		Customer existingCustomer = 
				customerRepository.authenticateCustomer(customer.getCustomerEmail(), customer.getCustomerPassword());
		if(existingCustomer == null)
			throw new CustomerNotFoundException("Invalid email or password.");
		else
			return existingCustomer;
	}

	@Override
	public Customer updateCustomerProfileInformation(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public void changeCustomerStatus(CustomerStatus customerStatus, long customerId) {
		customerRepository.changeCustomerStatus(customerStatus, customerId);
	}

	@Override
	public Customer findCustomerById(long customerId) throws CustomerNotFoundException {
		Optional<Customer> customer = customerRepository.findById(customerId);
		if(customer.isPresent())
			return customer.get();
		else
			throw new CustomerNotFoundException("Customer with specified customer id doesn't exist.");
	}

}
