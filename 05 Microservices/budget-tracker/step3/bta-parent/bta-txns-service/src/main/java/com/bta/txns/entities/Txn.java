package com.bta.txns.entities;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

@Entity
@Table(name="txns")
public class Txn implements Comparable<Txn> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long txnId;
	@NotBlank(message = "header is a mandate field")
	private String header;
	
	@NotNull(message = "txnDate is a mandate field")
	@PastOrPresent(message = "txnDate can not be of future")
	@DateTimeFormat(iso=ISO.DATE)
	private LocalDate txnDate;
	
	@Enumerated
	@NotNull(message = "type is a mandate field")
	private TxnType type;
	
	@NotNull(message = "amount is a mandate field")
	@Min(value = 0,message = "amount can not be negative")
	private Double amount;
	
	@ManyToOne
	@JsonProperty(access = Access.WRITE_ONLY)
	private AccountHolder holder;

	@Override
	public int compareTo(Txn t) {
		return txnId==null?1:txnId.compareTo(t.txnId);
	}
	
	public Txn() {
		// TODO Auto-generated constructor stub
	}

	public Txn(Long txnId, @NotBlank(message = "header is a mandate field") String header,
			@NotNull(message = "txnDate is a mandate field") @PastOrPresent(message = "txnDate can not be of future") LocalDate txnDate,
			@NotNull(message = "type is a mandate field") TxnType type,
			@NotNull(message = "amount is a mandate field") @Min(value = 0, message = "amount can not be negative") Double amount,
			AccountHolder holder) {
		super();
		this.txnId = txnId;
		this.header = header;
		this.txnDate = txnDate;
		this.type = type;
		this.amount = amount;
		this.holder = holder;
	}

	public Long getTxnId() {
		return txnId;
	}

	public void setTxnId(Long txnId) {
		this.txnId = txnId;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public LocalDate getTxnDate() {
		return txnDate;
	}

	public void setTxnDate(LocalDate txnDate) {
		this.txnDate = txnDate;
	}

	public TxnType getType() {
		return type;
	}

	public void setType(TxnType type) {
		this.type = type;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public AccountHolder getHolder() {
		return holder;
	}

	public void setHolder(AccountHolder holder) {
		this.holder = holder;
	}

		
}
