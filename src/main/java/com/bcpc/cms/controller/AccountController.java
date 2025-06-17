package com.bcpc.cms.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bcpc.cms.dto.AccountDTO;
import com.bcpc.cms.service.account.AccountAuthService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
public class AccountController {

    private final AccountAuthService auth;

    public AccountController(AccountAuthService auth) {
        this.auth = auth;
    }

    @PostMapping("/register")
    public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountDTO accountDTO) {
        AccountDTO response = auth.register(accountDTO);
        return ResponseEntity.ok(response);
    }

}
