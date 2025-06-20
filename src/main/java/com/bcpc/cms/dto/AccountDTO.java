package com.bcpc.cms.dto;

import com.bcpc.cms.entity.account.Account;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountDTO {

    private int statusCode;
    private String error;
    private String message;
    private String token;
    private String refreshToken;
    private String expirationTime;
    private String password;
    private Long accountId;
    private String username;
    private String email;
    private String role;
    private boolean enabled;
    private Account accounts;
}
