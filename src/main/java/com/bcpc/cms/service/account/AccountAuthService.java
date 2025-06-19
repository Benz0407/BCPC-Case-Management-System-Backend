package com.bcpc.cms.service.account;

import java.util.HashMap;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bcpc.cms.dto.AccountDTO;
import com.bcpc.cms.entity.account.Account;
import com.bcpc.cms.enums.AccountType;
import com.bcpc.cms.exception.ResourceNotFoundException;
import com.bcpc.cms.repository.account.AccountRepository;

@Service
public class AccountAuthService {

    private final AccountRepository accountRepository;
    private final JWTUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AccountAuthService(AccountRepository accountRepository, JWTUtils jwtUtils,
            PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.accountRepository = accountRepository;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public AccountDTO register(AccountDTO registerResponse) {

        AccountDTO response = new AccountDTO();

        try {
            Account accounts = new Account();
            accounts.setUsername(registerResponse.getUsername());
            accounts.setPassword(passwordEncoder.encode(registerResponse.getPassword()));
            accounts.setRole(AccountType.valueOf(registerResponse.getRole()));
            accounts.setEmail(registerResponse.getEmail());
            accounts.setEnabled(true);

            Account savedAccount = accountRepository.save(accounts);
            if (savedAccount.getAccountId() > 0) {
                response.setAccounts((savedAccount));
                response.setMessage("Account save successfully!");
                response.setStatusCode(200);
            }
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setError(e.getMessage());
        }

        return response;
    }

    public AccountDTO login(AccountDTO loginRequest) {

        AccountDTO response = new AccountDTO();

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getAccountId(),
                            loginRequest.getPassword()));
            Account account = accountRepository.findById(loginRequest.getAccountId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Account not found with id: " + loginRequest.getAccountId()));
            String token = jwtUtils.generateToken(account);
            String refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), account);

            response.setStatusCode(200);
            response.setToken(token);
            response.setRole(account.getRole().toString());
            response.setRefreshToken(refreshToken);
            response.setAccountId(account.getAccountId());
            response.setExpirationTime("6Hrs");
            response.setMessage("Login successful!");

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
        }

        return response;
    }

}
