package com.softedge.urbantrend.customerservice.service;

import com.softedge.urbantrend.customerservice.exception.CustomerNotFoundException;
import com.softedge.urbantrend.customerservice.model.Customer;
import com.softedge.urbantrend.customerservice.model.Customer.CustomerStatus;

public interface CustomerService {

	public Customer registerNewCustomer(Customer customer);

	public Customer authenticateCustomer(Customer customer) throws CustomerNotFoundException;

	public Customer updateCustomerProfileInformation(Customer customer);

	public void changeCustomerStatus(CustomerStatus customerStatus, long customerId);
	
	public Customer findCustomerById(long customerId) throws CustomerNotFoundException;
}
