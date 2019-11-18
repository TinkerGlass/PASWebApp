package com.jaba.webapp.controller;

import com.jaba.webapp.datafiller.item.ConstantItemDataFiller;
import com.jaba.webapp.datafiller.item.ItemDataFiller;
import com.jaba.webapp.domain.item.Item;
import com.jaba.webapp.repository.item.ItemRepository;
import com.jaba.webapp.repository.item.ItemRepositoryImpl;
import com.jaba.webapp.repository.specification.item.AlbumSpecification;
import com.jaba.webapp.repository.specification.item.ItemSpecification;
import com.jaba.webapp.service.ItemManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ItemController {

    private ItemManagerImpl itemService;

    @Autowired
    public ItemController(ItemManagerImpl itemService){
        this.itemService = itemService;
    }

    @GetMapping(value = "/products")
    public String showCustomers(Model model) {
        List<Item> arrayList = itemService.getAllItems();
        model.addAttribute("items", arrayList);
        model.addAttribute("message", "dzialam");
        return "items";
    }
}
