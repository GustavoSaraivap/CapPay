package com.capgemini.cappay.service;

import com.capgemini.cappay.dto.AccountBalanceDto;
import com.capgemini.cappay.dto.AccountDto;
import com.capgemini.cappay.enums.AccountStatus;
import com.capgemini.cappay.enums.AccountType;
import com.capgemini.cappay.exception.CapPayException;
import com.capgemini.cappay.model.Account;
import com.capgemini.cappay.model.User;
import com.capgemini.cappay.repository.AccountRepository;
import com.capgemini.cappay.utils.CsvHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserService userService;

    public Account save(AccountDto accountDto) throws CapPayException {
        User user = userService.getById(accountDto.getUserId());

        if (accountRepository.getAccountByUserId(user) != null) {
            throw new CapPayException(":::ESTE USUÁRIO JÁ POSSUI CONTA:::" +
                                      ":::THIS USER ALREADY HAS AN ACCOUNT!!:::");
        }

        Account account = new Account();
        account.setUserId(user);
        account.setAccountType(AccountType.valueOf(accountDto.getAccountType()));
        account.setCreationDate(new Date());

        if (Objects.equals(account.getAccountType().toString(), "CC")) {
            if (account.getCreationDate().getTime() > 17) {
                account.setAccountStatus(AccountStatus.PENDING);
            } else {
                account.setAccountStatus(AccountStatus.ACTIVE);
            }
        } else if (Objects.equals(account.getAccountType().toString(), "PP")) {
            account.setAccountStatus(AccountStatus.ACTIVE);
        }

        account.setBalance(0L);

        return accountRepository.save(account);
    }

    public Account updateBalance(AccountBalanceDto accountBalanceDto, Long id) throws CapPayException {
        Optional<Account> account = accountRepository.findById(id);

        if (accountBalanceDto.getBalance() < 0) {
            throw new CapPayException(":::VOCÊ NÃO PODE ATUALIZAR SEU SALDO COM VALOR NEGATIVO!:::" +
                                      ":::YOU CANNOT UPDATE YOUR BALANCE WITH NEGATIVE VALUE!:::");
        }

        account.get().setBalance(accountBalanceDto.getBalance());
        return accountRepository.save(account.get());
    }

    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    public ByteArrayInputStream getActiveAccountsByMonth(String month) throws IOException {
        List<Account> accounts = accountRepository.getActiveAccountsByMonth(month);
        return CsvHelper.accountToCSV(accounts);
    }
}
