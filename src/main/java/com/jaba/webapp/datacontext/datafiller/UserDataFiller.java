package com.jaba.webapp.datacontext.datafiller;

import com.jaba.webapp.domain.user.AdministratorUser;
import com.jaba.webapp.domain.user.ClientUser;
import com.jaba.webapp.domain.user.ResourceManagerUser;

import java.util.List;

public interface UserDataFiller {
    public void fillAdministrators(List<AdministratorUser> list);
    public void fillClients(List<ClientUser> list);
    public void fillResourceManagers(List<ResourceManagerUser> list);
}
