package fr.soat.bank.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.soat.bank.model.Customer;

/**
 * interface repository for class Customer
 * @author formation
 *
 */
@Repository 
public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
