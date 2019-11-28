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




    @RequestMapping(value = "/allocation/delete/{id}", method = RequestMethod.GET)
    public String removeItem(@PathVariable Long id) {
        allocationService.removeAllocation(id);
        return "redirect:/allocations";
    }



    @RequestMapping(value = "/loans/addLoan/{id}", method = RequestMethod.GET)
    public String showLoanForm(String userId,
                               String itemId,
                               @PathVariable Long id,
                               Model model) {
        model.addAttribute("item", itemService.getItemById(id));
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("userId", userId);
        model.addAttribute("itemId", itemId);
        return "loan";
    }

    @RequestMapping(value = "/loans/addLoan", method = RequestMethod.POST)
    public String addNewLoan(@ModelAttribute("userId") String userId,
                             @ModelAttribute("itemId") String itemId,
                             final BindingResult bindingResult) {
        Date now = Calendar.getInstance().getTime();
        AllocationInfo allocation = new AllocationInfo(itemService.getItemById((long) 1), userService.getUserById((long) 2), now);
        allocationService.addAllocation(allocation);
        return "redirect:/products";
    }
}
