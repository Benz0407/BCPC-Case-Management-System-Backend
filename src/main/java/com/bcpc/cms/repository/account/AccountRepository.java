package com.bcpc.cms.repository.account;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bcpc.cms.entity.account.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
