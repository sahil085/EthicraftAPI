package com.iskcon.EthicraftAPI.constants;

import java.util.Arrays;
import java.util.List;

public interface RoleConstant {

     String ROLE_SUPER_ADMIN="SUPER ADMIN";
     String ROLE_ADMIN="ADMIN";
     String ROLE_USER="USER";
     String ROLE_EEO = "EEO";
     String ROLE_MEMBER ="MEMBER";
     String ROLE_CA="CAMPUS AMBASSADOR";

     List<String> ROLES = Arrays.asList(ROLE_SUPER_ADMIN,ROLE_ADMIN,ROLE_USER,ROLE_EEO,ROLE_MEMBER,ROLE_CA);


}
