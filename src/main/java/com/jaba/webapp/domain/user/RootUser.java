package com.jaba.webapp.domain.user;

public class RootUser {
    private static AdministratorUser rootUser;

    private static AdministratorUser getRootUser() {
        if(rootUser == null) {
            rootUser = new AdministratorUser("root", null, true);
            return  rootUser;
        } else {
            return rootUser;
        }
    }
}
