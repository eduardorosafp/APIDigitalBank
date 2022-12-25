package br.com.eduar.bank.data.vo.v1.security;

import java.io.*;
import java.util.*;

public class TokenVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String accountName;
    private Boolean authenticated;
    private Date created;
    private Date expiration;
    private String accessToken;

    public TokenVO() {}

    public TokenVO(String accountName, Boolean authenticated, Date created, Date expiration, String accessToken) {
        this.accountName = accountName;
        this.authenticated = authenticated;
        this.created = created;
        this.expiration = expiration;
        this.accessToken = accessToken;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Boolean getAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(Boolean authenticated) {
        this.authenticated = authenticated;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TokenVO tokenVO = (TokenVO) o;
        return Objects.equals(accountName, tokenVO.accountName) && Objects.equals(authenticated, tokenVO.authenticated) && Objects.equals(created, tokenVO.created) && Objects.equals(expiration, tokenVO.expiration) && Objects.equals(accessToken, tokenVO.accessToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountName, authenticated, created, expiration, accessToken);
    }
}