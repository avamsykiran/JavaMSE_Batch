package com.bta.statement.services;

import java.time.LocalDate;

import com.bta.statement.exceptions.StatementException;
import com.bta.statement.models.Statement;

public interface StatementService {
	Statement getStatement(long ahid,LocalDate start,LocalDate end) throws StatementException;
}
