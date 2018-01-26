package fr.soat.bank.resources;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.soat.bank.model.Account;
import fr.soat.bank.model.DepositTransaction;
import fr.soat.bank.model.WithDrawalTransaction;
import fr.soat.bank.mvc.model.AccountDomain;
import fr.soat.bank.mvc.model.TransactionAccountDomain;
import fr.soat.bank.repository.DepositTransactionRepository;
import fr.soat.bank.repository.WithDrawalRepository;
import fr.soat.bank.services.AccountServices;
import fr.soat.bank.services.TransactionAccountServices;
import io.swagger.annotations.ApiOperation;

@RestController
public class OperationBankController {

	@Autowired
	private AccountServices accountServices;
	@Autowired
	private DepositTransactionRepository depositTransactionRepository;
	@Autowired
	private WithDrawalRepository withDrawalRepository;

	@Autowired
	private TransactionAccountServices transactionAccountService;

	private Logger LOGGER = LoggerFactory.getLogger(OperationBankController.class);

	/**
	 * Method to save a money in your account
	 * 
	 * @param accountDomain
	 * @param response
	 * @return ResponseEntity<java.lang.String>
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/api/operation/depositmoney", consumes = "application/json")
	@ApiOperation(value = "deposit money in your account")
	public ResponseEntity<String> depositMoney(@RequestBody AccountDomain accountDomain, HttpServletResponse response) {
		if (accountDomain.getMontantTransaction().equals(0.0) || accountDomain.getNumberAccount().equals(null)) {

			LOGGER.error("a money transaction is null or account number is null");
			return ResponseEntity.status(HttpStatus.FORBIDDEN)
					.body("can you check your deposit money or account identifiant");
		} else {
			Account account = accountServices.getAccountbyId(accountDomain.getNumberAccount());

			if (account==null) {
				LOGGER.error("your account not found by account number = " + accountDomain.getNumberAccount());
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("your account not found");
			} else {

				DepositTransaction depositTransaction = new DepositTransaction();
				Double newBalance = account.getBalance() + accountDomain.getMontantTransaction();
				account.setBalance(newBalance);
				account.setUpdatedDate(new Date());
				depositTransaction.setAccount(account);
				depositTransaction.setMontantTransaction(accountDomain.getMontantTransaction());
				depositTransaction.setCreatedDate(new Date());
				depositTransactionRepository.save(depositTransaction);
				return ResponseEntity.status(HttpStatus.OK).body("your account is updated");
			}
		}
	}

	/**
	 * Method to retieve a sum money in your account
	 * 
	 * @param accountDomain
	 * @param response
	 * @return ResponseEntity<java.lang.String>
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/api/operation/retrievemoney", consumes = "application/json")
	@ApiOperation(value = "retrieve money in your account")
	public ResponseEntity<String> retrieveMoney(@RequestBody AccountDomain accountDomain,
			HttpServletResponse response) {
		if (accountDomain.getMontantTransaction().equals(0.0) || accountDomain.getNumberAccount().equals(null)) {

			return ResponseEntity.status(HttpStatus.FORBIDDEN)
					.body("can you check your deposit money or account identifiant");
		} else {
			Account account = accountServices.getAccountbyId(accountDomain.getNumberAccount());

			if (account.equals(null)) {

				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("your account not found");

			} else if (account.getBalance() < accountDomain.getMontantTransaction()) {

				return ResponseEntity.status(HttpStatus.FORBIDDEN).body("insufficient money in your account");
			} else {

				WithDrawalTransaction withDrawal = new WithDrawalTransaction();
				Double newBalance = account.getBalance() - accountDomain.getMontantTransaction();
				account.setBalance(newBalance);
				account.setUpdatedDate(new Date());
				withDrawal.setAccount(account);
				withDrawal.setMontantTransaction(accountDomain.getMontantTransaction());
				withDrawal.setCreatedDate(new Date());
				withDrawalRepository.save(withDrawal);
				return ResponseEntity.status(HttpStatus.OK).body("your account is updated");
			}
		}
	}

	/**
	 * Method to get all operation in your account
	 * 
	 * @param accountDomain
	 * @param response
	 * @return ResponseEntity<java.util.Collection>
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/api/operation/all", consumes = "application/json")
	@ApiOperation(value = "show all transaction in your account")
	public ResponseEntity<List<TransactionAccountDomain>> getAllOperation(@RequestParam(required = true) String accountNumber,
			HttpServletResponse response) {

		if (accountNumber.equals(null)) {
			LOGGER.error("your account number is null");
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} else {
			List<TransactionAccountDomain> trAccountDomainLists = transactionAccountService
					.getAllTransactionAccount(accountNumber);
			if (trAccountDomainLists.isEmpty()) {
				LOGGER.error("i don't find any  transaction in your account = " + accountNumber);
				  return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			} else {
				 Collections.sort(trAccountDomainLists, Collections.reverseOrder(( tr1,  tr2)->tr1.getCreatedDateTransaction().compareTo(tr1.getCreatedDateTransaction())));
				 return new ResponseEntity<List<TransactionAccountDomain>>(trAccountDomainLists, HttpStatus.OK);
			}
		}
	}
}
