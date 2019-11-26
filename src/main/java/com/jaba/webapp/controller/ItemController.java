package com.jaba.webapp.controller;

import com.jaba.webapp.domain.item.Album;
import com.jaba.webapp.domain.item.FanSticker;
import com.jaba.webapp.domain.item.Item;
import com.jaba.webapp.domain.item.Video;
import com.jaba.webapp.exceptions.ApplicationException;
import com.jaba.webapp.service.item.ItemManager;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.ApplicationScope;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
        if(bindingResult.hasErrors())
            return "addItem";
        try {
            addingItem = item;
        } catch(Exception e) {
            return "addItem";
        }

        return "addSticker";
    }


    @RequestMapping(value = "/products/sticker", method = RequestMethod.GET)
    public String addStickerForm(@RequestParam(value = "type", defaultValue = "sticker") FanSticker sticker, Model model) {
        model.addAttribute("sticker", sticker);
        return "addSticker";
    }

    @RequestMapping(value = "/products/sticker", method = RequestMethod.POST)
    public String addSticker(@Valid @ModelAttribute FanSticker sticker,
                                 final BindingResult bindingResult){
        addingItem.setSticker(sticker);
        itemService.addItem(addingItem);
        return "redirect:/products";
    }

}
