package com.softedge.urbantrend.customerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.softedge.urbantrend.customerservice.model.Customer;
import com.softedge.urbantrend.customerservice.model.Customer.CustomerStatus;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Customer SET customerStatus = ?1 WHERE customerId = ?2")
	public void changeCustomerStatus(CustomerStatus customerStatus, long customerId);

	@Query("FROM Customer WHERE customerEmail = ?1 AND customerPassword = ?2")
	public Customer authenticateCustomer(String customerEmail, String customerPassword);
}
