package com.jaba.webapp.controller;

import com.jaba.webapp.datafiller.item.ConstantItemDataFiller;
import com.jaba.webapp.datafiller.item.ItemDataFiller;
import com.jaba.webapp.domain.item.Album;
import com.jaba.webapp.domain.item.FanSticker;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;
import java.util.Date;
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
        return "items";
    }


    @GetMapping(value = "/products/newitem")
    public String showSubmitForm() {
        return "addItem";
    }

    @PostMapping(value = "/products/newitem/add")
    public String addNewItem(@ModelAttribute("title") String itemTitle,
                             @ModelAttribute("price") String itemPrice,
                             @ModelAttribute("release") String releaseDate,
                             @ModelAttribute("author") String authorName,
                             @ModelAttribute("genre") String genreName,
                             @ModelAttribute("tracks") String tracksNumber,
                             Model model) {
        Item item = new Album(itemTitle,
                authorName,
                Album.Genre.CLASSICAL,
                new Date(),
                Integer.parseInt(tracksNumber),
                new BigDecimal(itemPrice),
                new FanSticker()
        );
        itemService.addItem(item);

        return showCustomers(model);
    }
}
