package com.jaba.webapp.repository.audit;

import com.jaba.webapp.domain.audit.AllocationInfo;
import com.jaba.webapp.repository.specification.Specification;

import java.util.List;

public interface AuditRepository {
    List<AllocationInfo> find(Specification<AllocationInfo> specification);
    void addAllocation(AllocationInfo allocation);
    void removeAllocation(Long allocationId);
    void updateAllocation(AllocationInfo allocation);
}
