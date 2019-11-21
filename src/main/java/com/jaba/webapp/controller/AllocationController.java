package com.jaba.webapp.controller;

import com.jaba.webapp.domain.audit.AllocationInfo;
import com.jaba.webapp.service.audit.AllocationManager;
import com.jaba.webapp.service.audit.AllocationManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class AllocationController {

    private AllocationManager allocationService;

    @Autowired
    public AllocationController(AllocationManager allocationService) { this.allocationService = allocationService; }


    @GetMapping(value = "/allocations")
    public String showAllocation(Model model) {
        List<AllocationInfo> arrayList = allocationService.getAllAllocations();
        model.addAttribute("allocations", arrayList);
        return "allocations";
    }
}
