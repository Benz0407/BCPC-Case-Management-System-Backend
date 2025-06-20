package com.bcpc.cms.repository.account;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bcpc.cms.entity.account.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByUsername(String username);

}
