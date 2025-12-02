package com.bta.txns.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bta.txns.entities.AccountHolder;

public interface AccountHolderRepo extends JpaRepository<AccountHolder,Long> {

}
