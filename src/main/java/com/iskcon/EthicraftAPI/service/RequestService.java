package com.iskcon.EthicraftAPI.service;

import com.iskcon.EthicraftAPI.domain.Role;
import com.iskcon.EthicraftAPI.domain.User;

public class RequestService {

    private Role currentRole;
    private User currentUser;

    public void setCurrentRole(Role currentRole) {
        this.currentRole = currentRole;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public Role fetchCurrentRole() {
        return this.currentRole;
    }

    public User fetchCurrentUser() {
        return this.currentUser;
    }
}
