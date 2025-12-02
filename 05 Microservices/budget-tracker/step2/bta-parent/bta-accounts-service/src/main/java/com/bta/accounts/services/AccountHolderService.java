package com.bta.accounts.services;

import java.util.List;

import com.bta.accounts.entities.AccountHolder;
import com.bta.accounts.exceptions.AccountHolderException;

public interface AccountHolderService {
	AccountHolder add(AccountHolder ah) throws AccountHolderException;
	AccountHolder getById(Long ahId);
	List<AccountHolder> getAll();
	boolean existsById(Long ahId);	
}
