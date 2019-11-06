package com.jaba.webapp.datacontext;

import com.jaba.webapp.domain.user.AdministratorUser;
import com.jaba.webapp.domain.user.ClientUser;
import com.jaba.webapp.domain.user.ResourceManagerUser;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class UserDataContext {
    private List<AdministratorUser> administratorUsers = Collections.synchronizedList(new ArrayList<AdministratorUser>());
    private List<ClientUser> clientUsers = Collections.synchronizedList(new ArrayList<ClientUser>());
    private List<ResourceManagerUser> resourceManagerUsers = Collections.synchronizedList(new ArrayList<ResourceManagerUser>());

    public UserDataContext() {

    }

    public List<AdministratorUser> getAdministratorUsers() {
        return administratorUsers;
    }

    public List<ClientUser> getClientUsers() {
        return clientUsers;
    }

    public List<ResourceManagerUser> getResourceManagerUsers() {
        return resourceManagerUsers;
    }
}
