package com.bcpc.cms.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bcpc.cms.repository.account.AccountRepository;

@Service
public class AuthenticationImpl implements UserDetailsService {

    private AccountRepository accountRepository;

    public AuthenticationImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String accountId) throws UsernameNotFoundException {
        return accountRepository.findById(Long.valueOf(accountId))
                .orElseThrow(() -> new UsernameNotFoundException("Account not found"));
    }
}
