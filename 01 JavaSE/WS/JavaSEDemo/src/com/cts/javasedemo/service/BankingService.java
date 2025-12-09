package com.cts.javasedemo.service;

import com.cts.javasedemo.exceptions.BankingException;
import com.cts.javasedemo.models.BankAccount;

public class BankingService {

	public void deposite(BankAccount acc, double amt) throws BankingException {

		if (amt < 0) {
			throw new BankingException("Negative amount can not be deppositred");
		}

		if (acc != null) {
			acc.setBalance(acc.getBalance() + amt);
		}
	}

	public void withdraw(BankAccount acc, double amt) throws BankingException {

		if (amt < 0) {
			throw new BankingException("Negative amount can not be withdrawn");
		}

		if (acc != null) {
		
			if(acc.getBalance()<amt) {
				throw new BankingException("Insufficeint balance!");
			}
			
		
			acc.setBalance(acc.getBalance() - amt);
		}
	}
}
