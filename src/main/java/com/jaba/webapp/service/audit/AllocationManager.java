package com.jaba.webapp.service.audit;

import com.jaba.webapp.domain.audit.AllocationInfo;
import com.jaba.webapp.exceptions.ApplicationException;

import java.util.List;

public interface AllocationManager{
     List<AllocationInfo> getAllAllocations();
     AllocationInfo getAllocationById(Long id);
     void updateAllocation(AllocationInfo allocation);
     void addAllocation(AllocationInfo allocation);
     void addAllocation(Long userId, Long itemId) throws ApplicationException;
     void removeAllocation(Long id);
}
