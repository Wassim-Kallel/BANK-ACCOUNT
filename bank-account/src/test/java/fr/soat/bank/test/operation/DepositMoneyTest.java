package fr.soat.bank.test.operation;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import fr.soat.bank.model.Account;
import fr.soat.bank.model.DepositTransaction;
import fr.soat.bank.repository.DepositTransactionRepository;
import fr.soat.bank.services.AccountServices;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DepositMoneyTest {

	@Autowired
	private AccountServices accountservices;
	@Autowired
	private DepositTransactionRepository depositTransactionRepository;


	@Test
	public void testgetaccountbyId() {
		Account account = accountservices.getAccountbyId("FR00101");
		Assert.notNull(account);
		DepositTransaction depositTransaction = new DepositTransaction();
		depositTransaction.setAccount(account);
		depositTransaction.setCreatedDate(new Date());
		depositTransaction.setMontantTransaction(50.00);
		depositTransactionRepository.save(depositTransaction);
		List<DepositTransaction> transactionAccountList = depositTransactionRepository
				.getAllDepositTransactionAccount(account.getAccountId());
		Assert.notEmpty(transactionAccountList);

	}
}
