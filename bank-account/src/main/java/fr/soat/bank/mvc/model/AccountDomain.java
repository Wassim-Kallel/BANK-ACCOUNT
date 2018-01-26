package fr.soat.bank.mvc.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class AccountDomain {

	@JsonProperty("numberAccount")
	@ApiModelProperty(notes = "number account for customer")
	private String numberAccount;

	@JsonProperty("montantTransaction")
	@ApiModelProperty(notes = "amout money of this transaction")
	private Double montantTransaction;

	public String getNumberAccount() {
		return numberAccount;
	}

	public void setNumberAccount(String numberAccount) {
		this.numberAccount = numberAccount;
	}

	public Double getMontantTransaction() {
		return montantTransaction;
	}

	public void setMontantTransaction(Double montantTransaction) {
		this.montantTransaction = montantTransaction;
	}

}
