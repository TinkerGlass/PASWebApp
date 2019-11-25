package com.jaba.webapp.controller;

import com.jaba.webapp.domain.item.Album;
import com.jaba.webapp.domain.item.FanSticker;
import com.jaba.webapp.domain.item.Item;
import com.jaba.webapp.domain.item.Video;
import com.jaba.webapp.service.item.ItemManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.ApplicationScope;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@ApplicationScope
@Controller
public class ItemController {

    private ItemManager itemService;

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

//    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
//    public String addUser(@RequestParam(value = "type", defaultValue = "client") User user, Model model) {
//        model.addAttribute("user", user);
//        return "addUser";
//    }


    @RequestMapping(value = "/products/newitem", method = RequestMethod.GET)
    public String showSubmitForm(@RequestParam(value = "type", defaultValue = "Album") Item item, Model model) {
        model.addAttribute("item", item);
        return "addItem";
    }

//    String title,
//    String director,
//    Genre[] genres,
//    Date releaseDate,
//    int minutes,
//    BigDecimal price,
//    FanSticker sticker,
//    boolean status


    @RequestMapping(value = "/products/newitem/add", method = RequestMethod.POST)
    public String addNewItem(@ModelAttribute("title") String itemTitle,
                             @ModelAttribute("price") String itemPrice,
                             @ModelAttribute("release") String releaseDate,
                             @ModelAttribute("owner") String authorName,
                             @ModelAttribute("genre") String genreName,
                             @ModelAttribute("numeric") String tracksNumber,
                             Model model) {
        Item item = new Album(itemTitle,
                authorName,
                Album.Genre.CLASSICAL,
                new Date(),
                Integer.parseInt(tracksNumber),
                new BigDecimal(itemPrice),
                new FanSticker(),
                true
        );
        itemService.addItem(item);

        return "redirect:/products";
    }
}
