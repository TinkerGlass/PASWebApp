package com.jaba.webapp.controller;

import com.jaba.webapp.domain.audit.AllocationInfo;
import com.jaba.webapp.domain.item.Item;
import com.jaba.webapp.service.audit.AllocationManager;
import com.jaba.webapp.service.audit.AllocationManagerImpl;
import com.jaba.webapp.service.item.ItemManager;
import com.jaba.webapp.service.user.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Controller
public class AllocationController {

    private AllocationManager allocationService;
    private ItemManager itemService;
    private UserManager userService;

    @Autowired
    public AllocationController(AllocationManager allocationService,
                                ItemManager itemService,
                                UserManager userService) {
        this.allocationService = allocationService;
        this.itemService = itemService;
        this.userService = userService;
    }





    @GetMapping(value = "/allocations")
    public String showAllocation(Model model) {
        List<AllocationInfo> arrayList = allocationService.getAllAllocations();
        model.addAttribute("allocations", arrayList);
        return "allocations";
    }




    @RequestMapping(value = "/loans/addLoan/{id}", method = RequestMethod.GET)
    public String showLoanForm(AllocationInfo allocation,
                               @PathVariable Long id,
                               Model model) {
        model.addAttribute("item", itemService.getItemById(id));
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("allocation", allocation);
        return "loan";
    }

    @RequestMapping(value = "/loans/addLoan", method = RequestMethod.POST)
    public String addNewLoan(@Valid @ModelAttribute AllocationInfo allocation,
                             final BindingResult bindingResult) {
        Date now = Calendar.getInstance().getTime();
        allocation.setStartTime(now);
        allocationService.addAllocation(allocation);
        return "redirect:/products";
    }
}
