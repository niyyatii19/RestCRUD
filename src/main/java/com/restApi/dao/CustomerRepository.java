package com.restApi.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restApi.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
