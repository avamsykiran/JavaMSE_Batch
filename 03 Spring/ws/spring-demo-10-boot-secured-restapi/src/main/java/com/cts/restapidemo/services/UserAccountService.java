package com.cts.restapidemo.services;

import com.cts.restapidemo.entities.UserAccount;
import com.cts.restapidemo.exceptions.InvalidRequestBodyException;

public interface UserAccountService {
	UserAccount createUser(UserAccount user) throws InvalidRequestBodyException;
}
