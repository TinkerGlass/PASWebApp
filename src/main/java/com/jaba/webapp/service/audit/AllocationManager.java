package com.jaba.webapp.service.audit;

import com.jaba.webapp.domain.audit.AllocationInfo;

import java.util.List;

public interface AllocationManager{
     List<AllocationInfo> getAllAllocations();
     AllocationInfo getAllocationById(Long id);
     void updateAllocation(AllocationInfo allocation);
     void addAllocation(AllocationInfo allocation);
     void removeAllocation(Long id);
}
