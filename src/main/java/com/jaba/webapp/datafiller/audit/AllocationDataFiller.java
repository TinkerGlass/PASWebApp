package com.jaba.webapp.datafiller.audit;

import com.jaba.webapp.repository.audit.AuditRepository;

public interface AllocationDataFiller {
    void fillAllocations(AuditRepository auditRepository);
}
