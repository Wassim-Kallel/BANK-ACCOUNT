package fr.soat.bank.services;

import java.util.Collection;
import java.util.List;

import fr.soat.bank.model.DepositTransaction;
import fr.soat.bank.model.WithDrawalTransaction;
import fr.soat.bank.mvc.model.TransactionAccountDomain;

public interface TransactionAccountServices {

	/**
	 * Method to get all Transaction depositMoney in account by accountId
	 * 
	 * @param accountId
	 * @return <fr.soat.bank.model.DepositTransaction>
	 */
	public List<DepositTransaction> getAllDepositTransactionAccount(String accountId);

	/**
	 * Method to get all Transaction retieveMoney in account by accountId
	 * 
	 * @param accountId
	 * @return <fr.soat.bank.model.WithDrawalTransaction>
	 */
	public List<WithDrawalTransaction> getAllWithDrawalransactionAccount(String accountId);

	/**
	 * Method to get All transaction account deposit money and withdrawal
	 * 
	 * @param <java.lang.String>
	 *            accountId
	 * @return java.util.Collection<fr.soat.bank.mvc.model.TransactionAccountDomain>
	 */
	public List<TransactionAccountDomain> getAllTransactionAccount(String accountId);
}
