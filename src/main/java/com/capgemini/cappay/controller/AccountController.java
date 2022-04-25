package com.capgemini.cappay.controller;

import com.capgemini.cappay.dto.AccountBalanceDto;
import com.capgemini.cappay.dto.AccountDto;
import com.capgemini.cappay.exception.CapPayException;
import com.capgemini.cappay.model.Account;
import com.capgemini.cappay.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Register a new account according to the specified type CC (Current Account) or PP (Savings Account)")
    public ResponseEntity<Account> save(@RequestBody AccountDto accountDto) throws CapPayException {
        Account account = accountService.save(accountDto);
        return ResponseEntity.ok(account);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Updates the current account balance, informing the\n" +
            "account identifier and amount.")
    public ResponseEntity<Account> updateBalance(@PathVariable(name = "id") Long id,
                                                 @RequestBody AccountBalanceDto accountBalanceDto) throws CapPayException {
        Account updatedAccount = accountService.updateBalance(accountBalanceDto, id);
        return ResponseEntity.ok(updatedAccount);
    }

    @GetMapping("/{month}")
    @Operation(summary = "Returns a report (CSV) with active accounts and\n" +
            "that were opened in month X (Example: april, 04).")
    public ResponseEntity<InputStreamResource> getReportByMonth(@PathVariable(name = "month") String month) throws IOException {
        String filename = "accounts.csv";
        InputStreamResource file = new InputStreamResource(accountService.getActiveAccountsByMonth(month));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(file);
    }

    @GetMapping
    @Operation(summary = "List all registered accounts")
    public ResponseEntity<List<Account>> getAccounts() {
        List<Account> accounts = accountService.getAccounts();
        return ResponseEntity.ok(accounts);
    }
}
