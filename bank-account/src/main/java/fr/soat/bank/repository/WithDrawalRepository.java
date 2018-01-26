package fr.soat.bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.soat.bank.model.WithDrawalTransaction;

@Repository
public interface WithDrawalRepository extends CrudRepository<WithDrawalTransaction, Long> {

	/**
	 * Method to get all Transaction retieveMoney in account by accountId
	 * 
	 * @param accountId
	 * @return <fr.soat.bank.model.WithDrawalTransaction>
	 */
	@Query("select wthdrawaltr from WithDrawalTransaction wthdrawaltr inner join wthdrawaltr.account acc where acc.accountId=?1")
	public List<WithDrawalTransaction> getAllWithDrawalransactionAccount(String accountId);
}
