package com.jaba.webapp.datafiller.audit;

import com.jaba.webapp.domain.audit.AllocationInfo;
import com.jaba.webapp.domain.item.Album;
import com.jaba.webapp.domain.item.FanSticker;
import com.jaba.webapp.domain.item.Item;
import com.jaba.webapp.domain.user.ClientUser;
import com.jaba.webapp.domain.user.User;
import com.jaba.webapp.repository.audit.AuditRepository;
import com.jaba.webapp.repository.item.ItemRepository;
import com.jaba.webapp.repository.specification.item.ItemSpecification;
import com.jaba.webapp.repository.specification.user.UserSpecification;
import com.jaba.webapp.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class ConstantAllocationDataFiller implements  AllocationDataFiller {

    private UserRepository userRepository;
    private ItemRepository itemRepository;

    @Override
    public void fillAllocations(AuditRepository auditRepository) {
        List<User> users = userRepository.find(UserSpecification.all());
        List<Item> items = itemRepository.find(ItemSpecification.all());

        final AllocationInfo[] allocations = {
                new AllocationInfo(items.get(0), users.get(0), Date.from(Instant.ofEpochSecond(1572545382))),
                new AllocationInfo(items.get(0), users.get(1), Date.from(Instant.ofEpochSecond(1572958800))),
                new AllocationInfo(items.get(1), users.get(2), Date.from(Instant.ofEpochSecond(1573046460))),
                new AllocationInfo(items.get(2), users.get(3), Date.from(Instant.ofEpochSecond(1573219260))),
        };

        allocations[0].setEndTime(Date.from(Instant.ofEpochSecond(1572890982)));
        allocations[1].setEndTime(Date.from(Instant.ofEpochSecond(1572989400)));

        for(AllocationInfo allocation : allocations){
            auditRepository.addAllocation(allocation);
        }
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setItemRepository(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }
}