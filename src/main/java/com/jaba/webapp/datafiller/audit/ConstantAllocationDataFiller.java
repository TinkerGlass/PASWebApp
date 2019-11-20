package com.jaba.webapp.datafiller.audit;

import com.jaba.webapp.domain.audit.AllocationInfo;
import com.jaba.webapp.domain.item.Album;
import com.jaba.webapp.domain.item.FanSticker;
import com.jaba.webapp.domain.user.ClientUser;
import com.jaba.webapp.repository.audit.AuditRepository;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;

public class ConstantAllocationDataFiller implements  AllocationDataFiller {

    private final FanSticker sticker =  new FanSticker("You're a fan", "Greatest fan yeah woo hoo no idea what this sticker thing is", 2137);

    final AllocationInfo[] allocations = {
            new AllocationInfo(new Album("Unknown Pleasures", "Joy Division", Album.Genre.ROCK, Date.from(Instant.ofEpochSecond(298209600)), 10, BigDecimal.valueOf(3999, 2), sticker),
                    new ClientUser("BorryOllo", "flash", true),
                    Date.from(Instant.ofEpochSecond(1497614400))),
            new AllocationInfo(new Album("Unknown Pleasures", "Joy Division", Album.Genre.ROCK, Date.from(Instant.ofEpochSecond(298209600)), 10, BigDecimal.valueOf(3999, 2), sticker),
                    new ClientUser("BorryOllo", "flash", true),
                    Date.from(Instant.ofEpochSecond(1497614400))),
            new AllocationInfo(new Album("Unknown Pleasures", "Joy Division", Album.Genre.ROCK, Date.from(Instant.ofEpochSecond(298209600)), 10, BigDecimal.valueOf(3999, 2), sticker),
                    new ClientUser("BorryOllo", "flash", true),
                    Date.from(Instant.ofEpochSecond(1497614400))),
            new AllocationInfo(new Album("Unknown Pleasures", "Joy Division", Album.Genre.ROCK, Date.from(Instant.ofEpochSecond(298209600)), 10, BigDecimal.valueOf(3999, 2), sticker),
                    new ClientUser("BorryOllo", "flash", true),
                    Date.from(Instant.ofEpochSecond(1497614400)))

    };

    @Override
    public void fillAllocations(AuditRepository auditRepository) {
            for(AllocationInfo allocation : allocations){
                auditRepository.addAllocation(allocation);
            }
    }
}