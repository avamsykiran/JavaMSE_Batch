package com.bta.accounts.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bta.accounts.entities.AccountHolder;

public interface AccountHolderRepo extends JpaRepository<AccountHolder, Long> {

}
