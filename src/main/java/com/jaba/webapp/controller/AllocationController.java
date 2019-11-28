package com.jaba.webapp.controller;

import com.jaba.webapp.breadcrumbs.annotation.Breadcrumb;
import com.jaba.webapp.domain.audit.AllocationInfo;
import com.jaba.webapp.exceptions.ApplicationException;
import com.jaba.webapp.service.audit.AllocationManager;
import com.jaba.webapp.service.item.ItemManager;
import com.jaba.webapp.service.user.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

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

    @Breadcrumb(label = "allocations.list.title", depth = 0, family = {"allocations"})
    @RequestMapping(value = "/allocations", method = RequestMethod.GET)
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

    @Breadcrumb(label = "loans.add.title", depth = 1, family = {"loan"})
    @RequestMapping(value = "/loans/addLoan/{id}", method = RequestMethod.GET)
    public String showLoanForm(@PathVariable Long id,
                               Model model) {
        model.addAttribute("item", itemService.getItemById(id));
        model.addAttribute("users", userService.getAllActiveUsers());
        return "loan";
    }

    @Breadcrumb(label = "loans.add.title", depth = 1, family = {"loan"})
    @RequestMapping(value = "/loans/addLoan", method = RequestMethod.POST)
    public String addNewLoan(@ModelAttribute("userId") Long userId,
                             @ModelAttribute("itemId") Long itemId,
                             Model model) {
        try {
            allocationService.addAllocation(userId, itemId);
        } catch (ApplicationException e) {
            List<ApplicationException> errors = new ArrayList<ApplicationException>();
            errors.add(e);
            model.addAttribute("errors", errors);
            model.addAttribute("item", itemService.getItemById(itemId));
            model.addAttribute("users", userService.getAllActiveUsers());
            return "loan";
        }

        return "redirect:/products";
    }

    @RequestMapping(value = "/loans/removeLoan/{id}", method = RequestMethod.GET)
    public String removeLoan(@PathVariable(name = "id") Long itemId) {
        try {
            allocationService.finishAllocation(itemId);
        } catch (ApplicationException e) {

        }
        return "redirect:/products";
    }
}
