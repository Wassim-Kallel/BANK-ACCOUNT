package fr.soat.bank.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.soat.bank.model.Account;

/**
 * interface repository for class Account
 * 
 * @author formation
 *
 */

@Repository
@Transactional
public interface AccountRepository extends CrudRepository<Account, Long> {
	/**
	 * Method to get account by accountId
	 * 
	 * @param accountId
	 * @return fr.soat.bank.model.Account
	 */
	@Query("select account from Account account where account.accountId = ?1")
	public Account getAccountbyId(String accountId);

}
