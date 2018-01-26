package fr.soat.bank.services;

import fr.soat.bank.model.Account;

public interface AccountServices {
	
	/**
	 * Method to get account by accountId 
	 * @param accountId
	 * @return fr.soat.bank.model.Account
	 */
	public Account getAccountbyId(String accountId);
}
