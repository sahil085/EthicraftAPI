package com.iskcon.EthicraftAPI.securityConfig.jwt;

import java.io.Serializable;
import java.util.List;


public class JwtResponse implements Serializable {
    private static final long         serialVersionUID = -8091879091924046844L;
    private              String       jwtToken;
    private              String       userName;
    private              List<String> authorities;

    public JwtResponse(String jwtToken, List<String> authorities, String userName) {
        this.jwtToken = jwtToken;
        this.authorities = authorities;
        this.userName = userName;
    }

    public String getToken() {
        return this.jwtToken;
    }

    public String getUserName() {
        return userName;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

}
