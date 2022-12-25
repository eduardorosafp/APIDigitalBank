package br.com.eduar.bank.services;

import br.com.eduar.bank.data.operations.v1.*;
import br.com.eduar.bank.data.vo.v1.AccountVO;
import br.com.eduar.bank.exceptions.*;
import br.com.eduar.bank.mapper.DozerMapper;
import br.com.eduar.bank.model.*;
import br.com.eduar.bank.repositories.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.logging.*;

@Service
public class AccountServices implements UserDetailsService {

	@Autowired
	AccountRepository repository;
	AccountVO vo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		var account = repository.findByUsername(username);
		if (account == null) throw new UsernameNotFoundException("User not found!");
		vo = DozerMapper.parseObject(account, AccountVO.class);
		return new CustomUserDetails(account);
	}

	public AccountVO createAccount(AccountVO accountVO) {
		if (accountVO.getAccountName() == null || accountVO.getAccountPassword() == null) throw new NullPointerException("Not possible create a account with null fields");
		if (accountVO.getAccountName().isBlank() || accountVO.getAccountPassword().isBlank()) throw new IllegalArgumentException("Not possible create a account with empty fields");
		if (accountVO.getAccountBalance() == null || accountVO.getAccountBalance().toString().isBlank()) accountVO.setAccountBalance(0D);

		Account verifyDoesNotExists = repository.findByUsername(accountVO.getAccountName());
		if(verifyDoesNotExists != null) throw new RepeatedAccountException();
		accountVO.setAccountPassword(new BCryptPasswordEncoder(10).encode(accountVO.getAccountPassword()));

		Account entity = DozerMapper.parseObject(accountVO, Account.class);
		entity.setPermissions(Arrays.asList(new Permission(1L)));
		repository.save(entity);
		return accountVO;
	}

	public String deposit(Deposit deposit) {
		if (deposit.getAccountName() == null || deposit.getDepositValue() == null) throw new NullPointerException("Enter a value in the parameters");
		if (deposit.getAccountName().isBlank() || deposit.getDepositValue().toString().isBlank()) throw new IllegalArgumentException("Enter a valid value in the parameters");
		if (deposit.getDepositValue() <= 0) throw new InvalidValueException();

		Account entity = repository.findByUsername(deposit.getAccountName());
		if (entity == null) throw new RequiredObjectIsNullException("This account does not exists, please register in /api/bank/v1/create");

		entity.setAccountBalance(entity.getAccountBalance() + deposit.getDepositValue());
		repository.save(entity);
		return "the deposit is completed";
	}// end of the deposit method

	public String transfer(Transfer transfer) {

		if(vo == null) throw new InvalidJWTAuthException("");
		if (transfer.getDestinyAccountName().equals(vo.getAccountName())) throw new IllegalArgumentException("Not possible transfer money to yourself");
		if (transfer.getValueTransfer() > vo.getAccountBalance() || transfer.getValueTransfer() <= 0) throw new InvalidValueException();
		Account originEntity = repository.findByUsername(vo.getAccountName());

		if (transfer.getDestinyAccountName() == null || transfer.getValueTransfer() == null) throw new NullPointerException("Enter a value in the parameters");
		if (transfer.getDestinyAccountName().isBlank() || transfer.getValueTransfer().toString().isBlank()) throw new IllegalArgumentException("Enter a valid value in the parameters");

		Account destinyEntity = repository.findByUsername(transfer.getDestinyAccountName());
		if (destinyEntity == null) throw new RequiredObjectIsNullException();

		originEntity.setAccountBalance(vo.getAccountBalance() - transfer.getValueTransfer());
		repository.save(originEntity);
		destinyEntity.setAccountBalance(destinyEntity.getAccountBalance() + transfer.getValueTransfer());
		repository.save(destinyEntity);
		return "Transfer successful";
	}// end of the transfer method

//	public String deleteAccount() {
//		if (account == null) throw new NullPointerException();
//		repository.deleteById(account.getId());
//		return "the account has been deleted";
//	}// end of the delete method

}