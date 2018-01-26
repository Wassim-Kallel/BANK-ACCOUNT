package fr.soat.bank.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "T_DEPOSITTRANSACTION")
@DiscriminatorValue(value="DEPOSITACCOUNT")
public class DepositTransaction extends TransactionAccount {

	private static final long serialVersionUID = -7029139897741716388L;

}
