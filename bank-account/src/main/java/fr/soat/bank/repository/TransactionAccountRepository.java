package fr.soat.bank.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.soat.bank.model.TransactionAccount;

/**
 * interface repository for Transaction class
 * 
 * @author formation
 *
 */
@Repository
public interface TransactionAccountRepository extends CrudRepository<TransactionAccount, Long> {




}
