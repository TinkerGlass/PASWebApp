package com.jaba.webapp.controller;

import com.jaba.webapp.breadcrumbs.annotation.Breadcrumb;
import com.jaba.webapp.domain.item.Album;
import com.jaba.webapp.domain.item.Item;
import com.jaba.webapp.domain.item.Video;
import com.jaba.webapp.exceptions.ApplicationException;
import com.jaba.webapp.service.item.ItemManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@Scope("request")
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

    @Breadcrumb(label="items.list.title", depth=0, family = {"items", "loan"})
    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String showItems(Model model) {
        List<Item> arrayList = itemService.getAllItems();
        model.addAttribute("items", arrayList);
        return "items";
    }

    @Breadcrumb(label="items.add.title", depth=1, family = {"items"})
    @RequestMapping(value = "/products/newitem", method = RequestMethod.GET)
    public String showSubmitForm(@RequestParam(value = "type", defaultValue = "Album") Item item, Model model) {
        model.addAttribute("item", item);
        return "addItem";
    }

    @Breadcrumb(label="items.add.title", depth=1, family = {"items"})
    @RequestMapping(value = "/products/newitem", method = RequestMethod.POST)
    public String addNewItem(@Valid @ModelAttribute Item item,
                             final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            if (item.getSticker() != null)
                return "addSticker";
            else
                return "addItem";
        }

        if (item.getSticker() == null)
            return "addSticker";

        try {
            itemService.addItem(item);
        } catch (Exception e) {
            return "addSticker";
        }

        return "redirect:/products";
    }





    @RequestMapping(value = "/products/modifyitem/{id}", method = RequestMethod.GET)
    public String showModifyForm(@PathVariable Long id, Model model) {
        model.addAttribute("item", itemService.getItemById(id));
        return "modifyItem";
    }



    @RequestMapping(value = "/products/modifyitem", method = RequestMethod.POST)
    public String showModifySubmit(@Valid @ModelAttribute Item item,
                                   final BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
                return "modifyItem";
        }

        return "modifySticker";
    }

    @RequestMapping(value = "/products/modifyitem/sticker", method = RequestMethod.POST)
    public String modifySticker(@Valid @ModelAttribute Item item,
                                   final BindingResult bindingResult,
                                    @ModelAttribute("errors") ArrayList<ApplicationException> errors){
        if (bindingResult.hasErrors()) {
            if (item.getSticker() != null)
                return "modifySticker";
            else
                return "modifyItem";
        }

        if (item.getSticker() == null)
            return "modifySticker";

        try {
            itemService.updateItem(item);
        } catch (ApplicationException e) {
            errors.add(e);
            return "modifySticker";
        }

        return "redirect:/products";
    }


    @RequestMapping(value = "/products/delete/{id}", method = RequestMethod.GET)
    public String removeItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return "redirect:/products";
    }

}
