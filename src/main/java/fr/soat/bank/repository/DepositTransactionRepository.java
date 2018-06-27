package fr.soat.bank.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.soat.bank.model.DepositTransaction;

@Repository
@Transactional
public interface DepositTransactionRepository extends CrudRepository<DepositTransaction, Long> {

	/**
	 * Method to get all Transaction depositMoney in account by accountId
	 * 
	 * @param accountId
	 * @return <fr.soat.bank.model.DepositTransaction>
	 */
	@Query("select deptr from DepositTransaction deptr inner join deptr.account acc where acc.accountId=?1")
	public List<DepositTransaction> getAllDepositTransactionAccount(String accountId);
}
