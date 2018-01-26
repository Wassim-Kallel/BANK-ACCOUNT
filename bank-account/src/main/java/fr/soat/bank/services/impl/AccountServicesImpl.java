package fr.soat.bank.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.soat.bank.model.Account;
import fr.soat.bank.repository.AccountRepository;
import fr.soat.bank.services.AccountServices;

@Service
public class AccountServicesImpl implements AccountServices {

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public Account getAccountbyId(String accountId) {
		Account account = accountRepository.getAccountbyId(accountId);
		if (account.equals(null))
			return null;
		return account;
	}

}
