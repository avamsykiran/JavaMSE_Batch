package com.bta.statement.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bta.statement.models.AccountHolder;

@FeignClient(name="bta-accounts-service",url = "http://localhost:9100")
public interface AccountsClient {
	
	@GetMapping("/{ahId}/exists")
	public Boolean checkAccountHolderExists(@PathVariable("ahId") Long ahId);
	
	@GetMapping("/{ahId}")
	public AccountHolder getAccountHolder(@PathVariable("ahId") Long ahId);
}