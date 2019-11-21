package com.jaba.webapp.configuration;

import com.jaba.webapp.datafiller.audit.AllocationDataFiller;
import com.jaba.webapp.datafiller.audit.ConstantAllocationDataFiller;
import com.jaba.webapp.datafiller.item.ConstantItemDataFiller;
import com.jaba.webapp.datafiller.item.ItemDataFiller;
import com.jaba.webapp.datafiller.user.ConstantUserDataFiller;
import com.jaba.webapp.datafiller.user.UserDataFiller;
import com.jaba.webapp.domain.user.ClientUser;
import com.jaba.webapp.domain.user.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;

@Configuration
public class InjectionConfiguration {

    @Bean
    public ItemDataFiller getItemDataFiller() {
        return new ConstantItemDataFiller();
    }

    @Bean
    public UserDataFiller getUserDataFiller() { return new ConstantUserDataFiller(); }

    @Bean
    public AllocationDataFiller geAllocationDataFiller() { return new ConstantAllocationDataFiller(); }

    @Bean
    @RequestScope
    public User getUserModel() {return new ClientUser(); }

}
