package com.iskcon.EthicraftAPI.service;

import java.util.Optional;

import com.iskcon.EthicraftAPI.domain.Role;
import com.iskcon.EthicraftAPI.domain.User;

public class ThreadRequestService {
    private static ThreadLocal<RequestService> requestServiceThreadLocal = ThreadLocal.withInitial(RequestService::new);

    private static RequestService get() {
        return requestServiceThreadLocal.get();
    }

    public static void setCurrentRole(Role currentRole) {
        get().setCurrentRole(currentRole);
    }

    public static void setCurrentUser(User currentUser) {
        get().setCurrentUser(currentUser);
    }

    public static Role fetchCurrentRole() {
        return get().fetchCurrentRole();
    }

    public static User fetchCurrentUser() {
        return Optional.ofNullable(get().fetchCurrentUser()).orElse(new User());
    }

    public static void remove() {
        requestServiceThreadLocal.remove();
    }
}
