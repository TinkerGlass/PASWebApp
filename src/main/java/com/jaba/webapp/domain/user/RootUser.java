package com.jaba.webapp.domain.user;

public class RootUser {
    private static AdministratorUser rootUser;

    public static AdministratorUser getRootUser() {
        if(rootUser == null) {
            rootUser = new AdministratorUser(0, "root", null, true);
            return  rootUser;
        } else {
            return rootUser;
        }
    }
}
