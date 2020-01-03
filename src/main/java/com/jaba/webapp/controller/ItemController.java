package com.jaba.webapp.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.jaba.webapp.breadcrumbs.annotation.Breadcrumb;
import com.jaba.webapp.controller.dto.UserSearchRequest;
import com.jaba.webapp.domain.item.Album;
import com.jaba.webapp.domain.item.Item;
import com.jaba.webapp.domain.item.Video;
import com.jaba.webapp.domain.user.User;
import com.jaba.webapp.exceptions.ApplicationException;
import com.jaba.webapp.service.item.ItemManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
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

    @Breadcrumb(label="items.list.title", depth=0, family = {"items", "loan", "itemsModify"})
    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String showItems(Model model) {
        List<Item> arrayList = itemService.getAllItems();
        model.addAttribute("items", arrayList);
        return "items";
    }

    @RequestMapping(value = "/items-json", method = RequestMethod.GET)
    @ResponseBody
    public List<Item> showItemsAjax() {
        return itemService.getAllItems();
    }

    @RequestMapping(value = "/add-item-album-json", method = RequestMethod.POST)
    @ResponseBody
    public String addItemAjax(@RequestBody Item item) {
        itemService.addItem(item);
        return "sucess.";
    }

    @RequestMapping(value = "/update-item-album-json", method = RequestMethod.PUT)
    @ResponseBody
    public String updateItemAjax(@RequestBody Item updatedItem,
                                 @PathVariable("id") long itemId) {
        Item item = itemService.getItemById(itemId);
        item = updatedItem;
        itemService.addItem(item);
        return "sucess.";
    }


    @RequestMapping(value = "/delete-item-album-json/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteItemAjax(@PathVariable("id") long itemId) {
        itemService.deleteItem(itemId);
        return "sucess.";
    }


    @Breadcrumb(label="items.add.title", depth=1, family = {"items"})
    @RequestMapping(value = "/products/newitem", method = RequestMethod.GET)
    public String showSubmitForm(@RequestParam(value = "type", defaultValue = "Album") Item item, Model model) {
        model.addAttribute("item", item);
        if(item instanceof Album)
            model.addAttribute("allAlbumGenre", Arrays.asList(Album.Genre.values()));
        else
            model.addAttribute("allVideoGenre", Arrays.asList(Video.Genre.values()));
        return "addItem";
    }

    @Breadcrumb(label="items.add.title", depth=1, family = {"items"})
    @RequestMapping(value = "/products/newitem", method = RequestMethod.POST)
    public String addNewItem(@Valid @ModelAttribute Item item,
                             final BindingResult bindingResult,
                             Model model,
                             RedirectAttributes ra) {

        if(item instanceof Album)
            model.addAttribute("allAlbumGenre", Arrays.asList(Album.Genre.values()));
        else
            model.addAttribute("allVideoGenre", Arrays.asList(Video.Genre.values()));

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
            ArrayList<ApplicationException> errors = new ArrayList<>();
            errors.add(new ApplicationException(500, "Unknown error occured."));
            model.addAttribute("errors", errors);
            return "addSticker";
        }

        return "redirect:/products";
    }

    @Breadcrumb(label="items.modify.title", depth=1, family = {"itemsModify"})
    @RequestMapping(value = "/products/modifyitem/{id}", method = RequestMethod.GET)
    public String showModifyForm(@PathVariable Long id, Model model) {
        Item item = itemService.getItemById(id);
        model.addAttribute("item", item);
        if(item instanceof Album)
            model.addAttribute("allAlbumGenre", Arrays.asList(Album.Genre.values()));
        else
            model.addAttribute("allVideoGenre", Arrays.asList(Video.Genre.values()));
        return "modifyItem";
    }

    @Breadcrumb(label="items.modify.title", depth=1, family = {"itemsModify"})
    @RequestMapping(value = "/products/modifyitem/{id}", method = RequestMethod.POST)
    public String showModifySubmit(@Valid @ModelAttribute Item item,
                                   final BindingResult bindingResult, Model model){
        if(item instanceof Album)
            model.addAttribute("allAlbumGenre", Arrays.asList(Album.Genre.values()));
        else
            model.addAttribute("allVideoGenre", Arrays.asList(Video.Genre.values()));

        if (bindingResult.hasErrors()) {
                return "modifyItem";
        }

        return "modifySticker";
    }

    @Breadcrumb(label="items.modify.title", depth=1, family = {"itemModify"})
    @RequestMapping(value = "/products/modifyitem/sticker", method = RequestMethod.POST)
    public String modifySticker(@Valid @ModelAttribute Item item,
                                   final BindingResult bindingResult, Model model, RedirectAttributes ra){
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
        } catch (Exception e) {
            ArrayList<ApplicationException> errors = new ArrayList<>();
            errors.add(new ApplicationException(500, "Unknown error occured."));
            model.addAttribute("errors", errors);
            return "modifySticker";
        }

        return "redirect:/products";
    }

    @RequestMapping(value = "/products/delete/{id}", method = RequestMethod.GET)
    public String removeItem(@PathVariable Long id, RedirectAttributes ra) {
        itemService.deleteItem(id);
        return "redirect:/products";
    }

}
