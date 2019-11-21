package com.jaba.webapp.repository.audit;

import com.jaba.webapp.datafiller.audit.AllocationDataFiller;
import com.jaba.webapp.domain.audit.AllocationInfo;
import com.jaba.webapp.repository.specification.Specification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class AuditRepositoryImpl implements AuditRepository {

    private final List<AllocationInfo> allocations = new ArrayList<>();

    public AuditRepositoryImpl() {}


    @Autowired(required = false)
    public AuditRepositoryImpl(AllocationDataFiller filler) { filler.fillAllocations(this); }


    @Override
    public List<AllocationInfo> find(Specification<AllocationInfo> specification) {
        synchronized (allocations) {
            return allocations.stream()
                    .filter(specification::matches)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public void addAllocation(AllocationInfo allocation) {
        synchronized (allocations) {
            if (allocation.getId() != null && allocations.contains(allocation))
                throw new IllegalArgumentException(String.format("Allocation with ID %d already exists", allocation.getId()));
            allocation.setId(getNextID());
            allocations.add(allocation);
        }
    }

    @Override
    public void removeAllocation(Long allocationId) {
        synchronized (allocations) {
            allocations.removeIf(allocation -> allocation.getId().equals(allocationId));
        }
    }

    @Override
    public void updateAllocation(AllocationInfo allocation) {
        synchronized (allocations) {
            int index = allocations.indexOf(allocation);
            if (index == -1)
                throw new IllegalArgumentException(String.format("Allocation with ID %d doesn't exist", allocation.getId()));
            allocations.set(index, allocation);
        }
    }

    private static final AtomicLong _nextID = new AtomicLong(0);
    public static Long getNextID() {
        return _nextID.getAndIncrement();
    }
    private void setNextID(Long value) {
        _nextID.set(value);
    }
}
