package com.bta.accounts.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bta.accounts.entities.AccountHolder;
import com.bta.accounts.exceptions.AccountHolderException;
import com.bta.accounts.repos.AccountHolderRepo;

@Service
public class AccountHolderServiceImpl implements AccountHolderService{

	@Autowired
	private AccountHolderRepo ahRepo;
	
	@Override
	public AccountHolder add(AccountHolder ah) throws AccountHolderException {
		if(ah.getAhId()!=null && ahRepo.existsById(ah.getAhId()))
			throw new AccountHolderException("A record with the given id already found");
		return ahRepo.save(ah);
	}

	@Override
	public AccountHolder getById(Long ahId) {
		return ahRepo.findById(ahId).orElse(null);
	}

	@Override
	public List<AccountHolder> getAll() {
		return ahRepo.findAll();
	}

	@Override
	public boolean existsById(Long ahId) {
		return ahRepo.existsById(ahId);
	}

}
