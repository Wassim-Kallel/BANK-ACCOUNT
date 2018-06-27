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
import fr.soat.bank.model.WithDrawalTransaction;
import fr.soat.bank.repository.WithDrawalRepository;
import fr.soat.bank.services.AccountServices;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WithDrawalMoneyTest {

	@Autowired
	private AccountServices accountservices;
	@Autowired
	private WithDrawalRepository withDrawalRepository;



	@Test
	public void testgetaccountbyId() {
		Account account = accountservices.getAccountbyId("FR00101");
		Assert.notNull(account);
		WithDrawalTransaction retieveTransaction = new WithDrawalTransaction();
		retieveTransaction.setAccount(account);
		retieveTransaction.setCreatedDate(new Date());
		retieveTransaction.setMontantTransaction(50.00);
		withDrawalRepository.save(retieveTransaction);
		List<WithDrawalTransaction> transactionAccountList = withDrawalRepository.getAllWithDrawalransactionAccount(account.getAccountId());
		Assert.notEmpty(transactionAccountList);

	}
}
