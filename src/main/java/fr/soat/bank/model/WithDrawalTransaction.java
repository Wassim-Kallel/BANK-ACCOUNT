package fr.soat.bank.model;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "T_WITHDRAWALTRANSACTION")
@DiscriminatorValue(value = "WITHDRAWAL")
public class WithDrawalTransaction extends TransactionAccount implements Serializable {

}
