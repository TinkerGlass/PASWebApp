package com.jaba.webapp.controller;

import com.jaba.webapp.domain.item.Album;
import com.jaba.webapp.domain.item.FanSticker;
import com.jaba.webapp.domain.item.Item;
import com.jaba.webapp.domain.item.Video;
import com.jaba.webapp.service.item.ItemManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.ApplicationScope;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@ApplicationScope
@Controller
public class ItemController {

    private ItemManager itemService;
    private Item addingItem;

    @Autowired
    public ItemController(ItemManager itemService){
        this.itemService = itemService;
    }

    @ModelAttribute("allAlbumGenre")
    public List<Album.Genre> albumGenres() {
        return Arrays.asList(Album.Genre.values());
    }

    @ModelAttribute("allVideoGenre")
    public List<Video.Genre> videoGenres() { return Arrays.asList(Video.Genre.values()); }

    @GetMapping(value = "/products")
    public String showCustomers(Model model) {
        List<Item> arrayList = itemService.getAllItems();
        model.addAttribute("items", arrayList);
        return "items";
    }


    @RequestMapping(value = "/products/newitem", method = RequestMethod.GET)
    public String showSubmitForm(@RequestParam(value = "type", defaultValue = "Album") Item item, FanSticker sticker, Model model) {
        model.addAttribute("item", item);
        return "addItem";
    }

    @RequestMapping(value = "/products/newitem", method = RequestMethod.POST)
    public String addNewItem(@Valid @ModelAttribute Item item,
                             final BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            if(item.getSticker() != null)
                return "addSticker";
            else
                return "addItem";
        }

        if(item.getSticker() == null)
            return "addSticker";

        try {
            itemService.addItem(item);
        } catch(Exception e) {
            return "addSticker";
        }

        return "redirect:/products";
    }
}
