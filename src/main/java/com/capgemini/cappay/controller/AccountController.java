package com.capgemini.cappay.controller;

import com.capgemini.cappay.dto.AccountDto;
import com.capgemini.cappay.exception.CapPayException;
import com.capgemini.cappay.model.Account;
import com.capgemini.cappay.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<Account> save(@RequestBody AccountDto accountDto) throws CapPayException {
        Account account = accountService.save(accountDto);
        return ResponseEntity.ok(account);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Account> updateBalance(@PathVariable(name = "id") Long id,
                                                 @RequestBody AccountDto accountDto) throws CapPayException {
        Account updatedAccount = accountService.updateBalance(accountDto, id);
        return ResponseEntity.ok(updatedAccount);
    }

    @GetMapping("/{month}")
    public ResponseEntity<InputStreamResource> getReportByMonth(@PathVariable(name = "month") String month) throws IOException {
//        return accountService.getActiveAccountsByMonth(month);
        String filename = "tutorials.csv";
        InputStreamResource file = new InputStreamResource(accountService.getActiveAccountsByMonth(month));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(file);
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAccounts() {
        List<Account> accounts = accountService.getAccounts();
        return ResponseEntity.ok(accounts);
    }
}
