package fr.soat.bank.mvc.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import fr.soat.bank.util.DateSerializer;
import io.swagger.annotations.ApiModelProperty;

public class TransactionAccountDomain {

	@JsonProperty("numberAccount")
	@ApiModelProperty(notes = " account number for customer")
	private String numberAccount;

	@JsonProperty("moneyOfTransaction")
	@ApiModelProperty(notes = "money of transaction")
	private Double moneyOfTransaction;

	@JsonProperty("operationName")
	@ApiModelProperty(notes = "operation name of this transaction")
	private String opertaionName;
	
	@JsonProperty("createdDateTransaction")
	@JsonSerialize(using=DateSerializer.class)
	@ApiModelProperty(notes = "Date of transaction")
	private Date createdDateTransaction;
	
	public String getNumberAccount() {
		return numberAccount;
	}

	public void setNumberAccount(String numberAccount) {
		this.numberAccount = numberAccount;
	}

	public Double getMoneyOfTransaction() {
		return moneyOfTransaction;
	}

	public void setMoneyOfTransaction(Double moneyOfTransaction) {
		this.moneyOfTransaction = moneyOfTransaction;
	}

	public String getOpertaionName() {
		return opertaionName;
	}

	public void setOpertaionName(String opertaionName) {
		this.opertaionName = opertaionName;
	}

	public Date getCreatedDateTransaction() {
		return createdDateTransaction;
	}

	public void setCreatedDateTransaction(Date createdDateTransaction) {
		this.createdDateTransaction = createdDateTransaction;
	}

}
