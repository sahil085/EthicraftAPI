package com.iskcon.EthicraftAPI.securityservices;

import com.iskcon.EthicraftAPI.domain.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class CurrentUser {


    public static User getCurrentUser()
    {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();

        User userInfo= (User)authentication.getPrincipal();

        return userInfo;
    }


}
