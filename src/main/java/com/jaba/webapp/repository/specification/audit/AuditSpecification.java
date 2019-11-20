package com.jaba.webapp.repository.specification.audit;

import com.jaba.webapp.domain.audit.AllocationInfo;
import com.jaba.webapp.repository.specification.Specification;

import java.util.Date;

public class AuditSpecification{
    public static Specification<AllocationInfo> afterStartDate(Date date) { return allocation -> allocation.getStartTime().after(date);  }

    public static Specification<AllocationInfo> afterEndDate(Date date) { return allocation -> allocation.getEndTime().after(date);  }

    public static Specification<AllocationInfo> beforeStartDate(Date date) { return allocation -> allocation.getStartTime().before(date);  }

    public static Specification<AllocationInfo> beforeEndDate(Date date) { return allocation -> allocation.getEndTime().before(date);  }

    public static Specification<AllocationInfo> all() {
        return allocation -> true;
    }
}
