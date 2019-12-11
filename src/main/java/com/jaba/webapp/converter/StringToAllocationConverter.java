package com.jaba.webapp.converter;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.jaba.webapp.domain.audit.AllocationInfo;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToAllocationConverter implements Converter<String, AllocationInfo> {
    @Override
    public AllocationInfo convert(String source) {
        if(source.equalsIgnoreCase("allocation")) {
            return new AllocationInfo();
        }  else {
            throw new IllegalArgumentException("Unknown user type: " + source);
        }
    }
}
