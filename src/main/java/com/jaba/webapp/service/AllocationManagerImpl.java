package com.jaba.webapp.service;

import com.jaba.webapp.domain.audit.AllocationInfo;
import com.jaba.webapp.repository.audit.AuditRepository;
import com.jaba.webapp.repository.specification.audit.AuditSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllocationManagerImpl implements  AllocationManager{

    private AuditRepository auditRepository;

    @Override
    public List<AllocationInfo> getAllAllocations() { return auditRepository.find(AuditSpecification.all()); }

    @Override
    public AllocationInfo getAllocationById(Long id) {
        return null;
    }

    @Override
    public void deleteAllocation(Long id) { auditRepository.removeAllocation(id); }

    @Override
    public void updateAllocation(AllocationInfo allocation) { auditRepository.updateAllocation(allocation); }

    @Override
    public void addAllocation(AllocationInfo allocation) { auditRepository.addAllocation(allocation); }


    @Autowired
    public void setAuditRepository(AuditRepository auditRepository) {
        this.auditRepository = auditRepository;
    }
}
