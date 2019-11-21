package com.jaba.webapp.service.audit;

import com.jaba.webapp.domain.audit.AllocationInfo;
import com.jaba.webapp.repository.audit.AuditRepository;
import com.jaba.webapp.repository.specification.audit.AuditSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllocationManagerImpl implements AllocationManager {

    private AuditRepository auditRepository;

    @Override
    public List<AllocationInfo> getAllAllocations() { return auditRepository.find(AuditSpecification.all()); }

    @Override
    public AllocationInfo getAllocationById(Long id) {
        List<AllocationInfo> list = auditRepository.find(AuditSpecification.byId(id));
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public void updateAllocation(AllocationInfo allocation) { auditRepository.updateAllocation(allocation); }

    @Override
    public void addAllocation(AllocationInfo allocation) { auditRepository.addAllocation(allocation); }

    @Override
    public void removeAllocation(Long id) {
        List<AllocationInfo> allocation = auditRepository.find(AuditSpecification.byId(id));
        if(allocation.isEmpty())
            return;
        if(allocation.get(0).getEndTime() != null)
            throw new IllegalArgumentException(String.format("Allocation with id %d is already finished. Removal forbidden.", allocation.get(0).getId()));
        auditRepository.removeAllocation(id);
    }

    @Autowired
    public void setAuditRepository(AuditRepository auditRepository) {
        this.auditRepository = auditRepository;
    }
}
