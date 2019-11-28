package com.jaba.webapp.service.audit;

import com.jaba.webapp.domain.audit.AllocationInfo;
import com.jaba.webapp.domain.item.Item;
import com.jaba.webapp.domain.user.User;
import com.jaba.webapp.exceptions.ApplicationException;
import com.jaba.webapp.repository.audit.AuditRepository;
import com.jaba.webapp.repository.item.ItemRepository;
import com.jaba.webapp.repository.specification.audit.AuditSpecification;
import com.jaba.webapp.repository.specification.item.ItemSpecification;
import com.jaba.webapp.repository.specification.user.UserSpecification;
import com.jaba.webapp.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class AllocationManagerImpl implements AllocationManager {

    private AuditRepository auditRepository;
    private ItemRepository itemRepository;
    private UserRepository userRepository;

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
    public void addAllocation(Long userId, Long itemId) throws ApplicationException {
        List<Item> items = itemRepository.find(ItemSpecification.byId(itemId));
        if(items.isEmpty())
            throw new ApplicationException(ApplicationException.ErrorCode.ITEM_ID_DOESNT_EXIST);

        if(!items.get(0).isAvailable())
            throw new ApplicationException(ApplicationException.ErrorCode.ITEM_UNAVAILABLE);

        List<User> users = userRepository.find(UserSpecification.byId(userId));
        if(users.isEmpty())
            throw new ApplicationException(ApplicationException.ErrorCode.USER_ID_DOESNT_EXIST);

        AllocationInfo allocationInfo = new AllocationInfo(items.get(0), users.get(0), Date.from(Instant.now()));
        auditRepository.addAllocation(allocationInfo);

        items.get(0).setAvailable(false);
        itemRepository.updateItem(items.get(0));
    }

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

    @Autowired
    public void setItemRepository(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
