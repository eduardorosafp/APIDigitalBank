package br.com.eduar.bank.controllers;

import br.com.eduar.bank.data.operations.v1.Deposit;
import br.com.eduar.bank.data.operations.v1.Transfer;
import br.com.eduar.bank.data.vo.v1.AccountVO;
import br.com.eduar.bank.services.AccountServices;
import br.com.eduar.bank.util.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bank/v1/")
public class AccountController {

	@Autowired
	private AccountServices service;
	
	@PostMapping(
			value = "create",
			produces = MediaType.APPLICATION_JSON,
			consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<AccountVO> create(@RequestBody AccountVO accountVO){
		return new ResponseEntity<>(service.createAccount(accountVO), HttpStatus.CREATED);
	}

	@PutMapping(
			value = "deposit",
			produces = MediaType.APPLICATION_JSON,
			consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<String> deposit (@RequestBody Deposit deposit) {
		return new ResponseEntity<>(service.deposit(deposit), HttpStatus.OK);
	}

	@PutMapping(
			value = "transfer",
			produces = MediaType.APPLICATION_JSON,
			consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<String> transfer(@RequestBody Transfer transfer) {
		return new ResponseEntity<>(service.transfer(transfer), HttpStatus.OK);
	}

//	@PreAuthorize("hasAuthority('ADMIN')")
//	public String deleteAccount() {return service.deleteAccount();}
//
}
