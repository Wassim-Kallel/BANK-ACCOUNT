package fr.soat.bank.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.soat.bank.model.DepositTransaction;
import fr.soat.bank.model.WithDrawalTransaction;
import fr.soat.bank.mvc.model.TransactionAccountDomain;
import fr.soat.bank.repository.DepositTransactionRepository;
import fr.soat.bank.repository.WithDrawalRepository;
import fr.soat.bank.services.TransactionAccountServices;

@Service
public class TransactionAccountServicesImpl implements TransactionAccountServices {

	@Autowired
	private WithDrawalRepository  withDrawalAccountRepository;
	
	@Autowired
	private DepositTransactionRepository depositTransactionRepository;

	private Logger LOGGER = LoggerFactory.getLogger(TransactionAccountServicesImpl.class);

	@Override
	public List<DepositTransaction> getAllDepositTransactionAccount(String accountId) {
		List<DepositTransaction> deptransactionAccounts = depositTransactionRepository
				.getAllDepositTransactionAccount(accountId);
		if (deptransactionAccounts.isEmpty() || deptransactionAccounts==null) {
			LOGGER.error(" I dont found any transaction in your account =" + accountId);
			return null;
		} else {

			return deptransactionAccounts;
		}
	}

	@Override
	public List<WithDrawalTransaction> getAllWithDrawalransactionAccount(String accountId) {
		List<WithDrawalTransaction> retievetransactionAccounts = withDrawalAccountRepository
				.getAllWithDrawalransactionAccount(accountId);
		if (retievetransactionAccounts.isEmpty() || retievetransactionAccounts==null) {
			LOGGER.info("I dont found any withdrawal transaction  in your account =" + accountId);
			return null;
		} else {

			return retievetransactionAccounts;
		}
	}

	@Override
	public List<TransactionAccountDomain> getAllTransactionAccount(String accountId) {

		List<TransactionAccountDomain> trAccDomains = new ArrayList();
		List<DepositTransaction> depMoneyLists = getAllDepositTransactionAccount(accountId);

		if (depMoneyLists==null) {
			LOGGER.info("I dont found any deposit money transaction in your account");
		} else {

			for (DepositTransaction deptr : depMoneyLists) {
				TransactionAccountDomain dep = new TransactionAccountDomain();
				dep.setMoneyOfTransaction(deptr.getMontantTransaction());
				dep.setNumberAccount(deptr.getAccount().getAccountId());
				dep.setOpertaionName("Deposit Money");
				dep.setCreatedDateTransaction(deptr.getCreatedDate());
				trAccDomains.add(dep);
			}

		}
		List<WithDrawalTransaction> wthDrawalLists = getAllWithDrawalransactionAccount(accountId);
		if (wthDrawalLists==null) {
			LOGGER.info("I dont found any withdrawal transaction in your account");
		} else {

			for (WithDrawalTransaction wthdrawal : wthDrawalLists) {
				TransactionAccountDomain dep = new TransactionAccountDomain();
				dep.setMoneyOfTransaction(wthdrawal.getMontantTransaction());
				dep.setNumberAccount(wthdrawal.getAccount().getAccountId());
				dep.setOpertaionName("With Drawal Money");
				dep.setCreatedDateTransaction(wthdrawal.getCreatedDate());
				trAccDomains.add(dep);
			}
		}
		return trAccDomains;
	}
}
