package in.bta.txns.controllers;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import in.bta.security.JwtAuthUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.bta.txns.entities.AccountHolder;
import in.bta.txns.entities.Txn;
import in.bta.txns.exceptions.AccountHolderException;
import in.bta.txns.exceptions.TxnException;
import in.bta.txns.services.TxnService;

@RestController
@RequestMapping("/")
public class TxnController {

	@Autowired
	private TxnService txnService;

	@GetMapping("/balances")
	public ResponseEntity<List<AccountHolder>> getBalances(@AuthenticationPrincipal Jwt jwt)
			throws AccountHolderException {
		JwtAuthUtils.requireAdmin(jwt);
		return ResponseEntity.ok(txnService.getAllAccounts());
	}
	
	@GetMapping("/{ahId}/balance")
	public ResponseEntity<Double> getBalance(@AuthenticationPrincipal Jwt jwt, @PathVariable("ahId") Long ahId)
			throws AccountHolderException {
		JwtAuthUtils.requireAdminOrOwn(jwt, ahId);
		return ResponseEntity.ok(txnService.getBalance(ahId));
	}
	
	@GetMapping("/{ahId}/{start}/{end}")
	public ResponseEntity<List<Txn>> getTxns(
			@AuthenticationPrincipal Jwt jwt,
			@PathVariable("ahId") Long ahId,
			@PathVariable("start") @DateTimeFormat(iso = ISO.DATE) LocalDate start,
			@PathVariable("end") @DateTimeFormat(iso = ISO.DATE) LocalDate end)
			throws AccountHolderException {
		JwtAuthUtils.requireAdminOrOwn(jwt, ahId);
		return ResponseEntity.ok(txnService.getPeriodicTxnsByAhId(ahId, start, end));
	}
	
	@PostMapping
	public ResponseEntity<Txn> addTxn(@AuthenticationPrincipal Jwt jwt, @RequestBody @Valid Txn txn,
			BindingResult results) throws TxnException, AccountHolderException {
		JwtAuthUtils.requireAdminOrOwn(jwt, txn.getHolder().getAhId());
		return saveTxn(txn, results, "ADD");
	}
	
	@PutMapping
	public ResponseEntity<Txn> updateTxn(@AuthenticationPrincipal Jwt jwt, @RequestBody @Valid Txn txn,
			BindingResult results) throws TxnException, AccountHolderException {
		JwtAuthUtils.requireAdminOrOwn(jwt, txn.getHolder().getAhId());
		return saveTxn(txn, results, "UPDATE");
	}

	private ResponseEntity<Txn> saveTxn(Txn txn, BindingResult results, String operation)
			throws TxnException, AccountHolderException {
		if (results.hasErrors())
			throw new TxnException(results.getAllErrors().stream()
					.map(err -> err.getDefaultMessage()).reduce((m1, m2) -> m1 + "," + m2).orElse(null));

		ResponseEntity<Txn> re = null;
		if (operation.equals("ADD"))
			re = new ResponseEntity<Txn>(txnService.add(txn), HttpStatus.CREATED);
		else
			re = new ResponseEntity<Txn>(txnService.update(txn), HttpStatus.ACCEPTED);
		return re;
	}

	// Authorization checks delegated to shared utility
}
