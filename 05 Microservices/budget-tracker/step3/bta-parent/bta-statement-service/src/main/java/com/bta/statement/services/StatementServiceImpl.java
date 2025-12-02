package com.bta.statement.services;

import java.time.LocalDate;
import java.util.List;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bta.statement.exceptions.StatementException;
import com.bta.statement.models.AccountHolder;
import com.bta.statement.models.Statement;
import com.bta.statement.models.Txn;
import com.bta.statement.models.TxnType;

@Service
public class StatementServiceImpl implements StatementService {
	
	@Autowired
	private AccountsClient accountsClient;
	
	@Autowired
	private TxnsClient txnsClient;

	private double computeTotal(List<Txn> txns,TxnType type) {
		return txns.stream().filter(t->t.getType()==type).mapToDouble(Txn::getAmount).sum();
	}
	
	@Override
	public Statement getStatement(long ahid, LocalDate start, LocalDate end) throws StatementException {
		if(!accountsClient.checkAccountHolderExists(ahid))
			throw new StatementException("Account does not exists");
		
		AccountHolder ah = accountsClient.getAccountHolder(ahid);
		List<Txn> txns = txnsClient.getTxns(ahid, start, end);
		ah.setCurrentBalance(txnsClient.getBalance(ahid));
		double totalCredit=computeTotal(txns, TxnType.CREDIT);
		double totalDebit=computeTotal(txns, TxnType.DEBIT);
		double statementBalance = totalCredit-totalDebit;
		
		return 
				new Statement(ah,
						new TreeSet<>(txns), 
						totalCredit, 
						totalDebit, 
						statementBalance, 
						start, 
						end);
	}

}
