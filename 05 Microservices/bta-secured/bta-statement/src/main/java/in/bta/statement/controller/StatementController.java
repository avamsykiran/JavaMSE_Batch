package in.bta.statement.controller;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import in.bta.security.JwtAuthUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.bta.statement.exceptions.StatementException;
import in.bta.statement.models.Statement;
import in.bta.statement.services.StatementService;

@RestController
@RequestMapping("/")
public class StatementController {

	@Autowired
	private StatementService statementService;
	
	@GetMapping("/{ahId}/{year}")
	public ResponseEntity<Statement> getAnnualStatement(
			@AuthenticationPrincipal Jwt jwt, @PathVariable("ahId") Long ahId,
			@PathVariable("year") int year) throws StatementException {

		JwtAuthUtils.requireAdminOrOwn(jwt, ahId);
		LocalDate start = LocalDate.of(year, Month.JANUARY, 1);
		LocalDate end = start.with(TemporalAdjusters.lastDayOfYear());
		return ResponseEntity.ok(statementService.getStatement(ahId, start, end));
	}

	@GetMapping("/{ahId}/{year}/{month}")
	public ResponseEntity<Statement> getMonthlyStatement(
			@AuthenticationPrincipal Jwt jwt, @PathVariable("ahId") Long ahId,
			@PathVariable("year") int year, @PathVariable("month") Month month) throws StatementException {

		JwtAuthUtils.requireAdminOrOwn(jwt, ahId);
		LocalDate start = LocalDate.of(year, month, 1);
		LocalDate end = start.with(TemporalAdjusters.lastDayOfMonth());
		return ResponseEntity.ok(statementService.getStatement(ahId, start, end));

	}

	// Authorization checks delegated to shared utility
}
