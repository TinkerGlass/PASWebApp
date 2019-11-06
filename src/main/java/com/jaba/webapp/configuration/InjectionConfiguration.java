package com.jaba.webapp.configuration;

import com.jaba.webapp.datacontext.datafiller.ConstantItemDataFiller;
import com.jaba.webapp.datacontext.datafiller.ItemDataFiller;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InjectionConfiguration {

    @Bean
    public ItemDataFiller getDataFiller() {
        return new ConstantItemDataFiller();
    }
}
