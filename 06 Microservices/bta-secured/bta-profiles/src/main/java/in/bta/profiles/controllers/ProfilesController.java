package in.bta.profiles.controllers;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import in.bta.security.JwtAuthUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.bta.profiles.entities.AccountHolder;
import in.bta.profiles.exceptions.AccountHolderException;
import in.bta.profiles.services.AccountHolderService;

@RestController
@RequestMapping("/")
public class ProfilesController {

	@Autowired
	private AccountHolderService ahService;
	
	@GetMapping
	public ResponseEntity<List<AccountHolder>> getAll(@AuthenticationPrincipal Jwt jwt) {
		JwtAuthUtils.requireAdmin(jwt);
		return ResponseEntity.ok(ahService.getAll());
	}
	
	@GetMapping("/{ahId}")
	public ResponseEntity<AccountHolder> getById(@AuthenticationPrincipal Jwt jwt, @PathVariable("ahId") Long ahId) {
		JwtAuthUtils.requireAdminOrOwn(jwt, ahId);
		AccountHolder ah = ahService.getById(ahId);
		return ah != null ? ResponseEntity.ok(ah) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/{ahId}/exists")
	public ResponseEntity<Boolean> existsById(@AuthenticationPrincipal Jwt jwt, @PathVariable("ahId") Long ahId) {
		JwtAuthUtils.requireAdmin(jwt);
		return ResponseEntity.ok(ahService.existsById(ahId));
	}
	
	@PostMapping
	public ResponseEntity<AccountHolder> add(@AuthenticationPrincipal Jwt jwt,
			@RequestBody @Valid AccountHolder ah, BindingResult bindingResult) throws AccountHolderException {
		JwtAuthUtils.requireAdmin(jwt);
		if (bindingResult.hasErrors()) {
			throw new AccountHolderException(bindingResult.getAllErrors().stream()
					.map(err -> err.getDefaultMessage()).reduce((m1, m2) -> m1 + "," + m2).orElse(null));
		}
		return new ResponseEntity<>(ahService.add(ah), HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<AccountHolder> update(@AuthenticationPrincipal Jwt jwt,
			@RequestBody @Valid AccountHolder ah, BindingResult bindingResult) throws AccountHolderException {
		JwtAuthUtils.requireAdmin(jwt);
		if (bindingResult.hasErrors()) {
			throw new AccountHolderException(bindingResult.getAllErrors().stream()
					.map(err -> err.getDefaultMessage()).reduce((m1, m2) -> m1 + "," + m2).orElse(null));
		}
		return new ResponseEntity<>(ahService.update(ah), HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/{ahId}")
	public ResponseEntity<Void> deleteById(@AuthenticationPrincipal Jwt jwt, @PathVariable("ahId") Long ahId)
			throws AccountHolderException {
		JwtAuthUtils.requireAdmin(jwt);
		ahService.deleteById(ahId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	// Authorization checks delegated to shared utility

}
