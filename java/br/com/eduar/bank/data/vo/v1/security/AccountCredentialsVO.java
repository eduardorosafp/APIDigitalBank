package br.com.eduar.bank.data.vo.v1.security;

import java.io.*;
import java.util.*;

public class AccountCredentialsVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String accountName;
    private String accountPassword;

    public AccountCredentialsVO(String accountName, String accountPassword) {
        this.accountName = accountName;
        this.accountPassword = accountPassword;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountCredentialsVO that = (AccountCredentialsVO) o;
        return Objects.equals(accountName, that.accountName) && Objects.equals(accountPassword, that.accountPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountName, accountPassword);
    }
}
