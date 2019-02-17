package com.softedge.urbantrend.customerservice.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softedge.urbantrend.customerservice.exception.CustomerNotFoundException;
import com.softedge.urbantrend.customerservice.model.Customer;
import com.softedge.urbantrend.customerservice.model.Customer.CustomerStatus;
import com.softedge.urbantrend.customerservice.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerResource {

	@Autowired
	private CustomerService customerService;

	@PostMapping("/customers")
	public ResponseEntity<Customer> registerNewCustomer(@RequestBody Customer customer) {
		return new ResponseEntity<Customer>(customerService.registerNewCustomer(customer), HttpStatus.OK);
	}

	@PostMapping("/customers/authenticate")
	public ResponseEntity<Customer> authenticateCustomer(@RequestBody Customer customer) {
		ResponseEntity<Customer> response = null;
		try {
			Customer existingCustomer = customerService.authenticateCustomer(customer);
			response = new ResponseEntity<Customer>(existingCustomer, HttpStatus.OK);
		}
		catch(CustomerNotFoundException ex) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return response;
	}

	@PutMapping("/customers")
	public ResponseEntity<Customer> updateCustomerProfileInformation(@RequestBody Customer customer) {
		return new ResponseEntity<Customer>(customerService.updateCustomerProfileInformation(customer), HttpStatus.OK);
	}

	@PutMapping("/customers/{customerId}/{customerStatus}")
	public ResponseEntity<Void> changeCustomerStatus(@PathVariable long customerId,
			@PathVariable String customerStatus) {
		CustomerStatus status = null;
		if (customerStatus.equalsIgnoreCase("ACTIVE"))
			status = CustomerStatus.ACTIVE;
		else if (customerStatus.equalsIgnoreCase("INACTIVE"))
			status = CustomerStatus.INACTIVE;
		customerService.changeCustomerStatus(status, customerId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping("/customers/{customerId}")
	public ResponseEntity<Customer> findCustomerById(@PathVariable long customerId) {
		ResponseEntity<Customer> response = null;
		try {
			Customer existingCustomer = customerService.findCustomerById(customerId);
			response = new ResponseEntity<Customer>(existingCustomer, HttpStatus.OK);
		}
		catch(CustomerNotFoundException ex) {
			response = new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
		}
		return response;
	}

}
