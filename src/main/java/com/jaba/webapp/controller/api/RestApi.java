package com.jaba.webapp.controller.api;

import com.fasterxml.jackson.annotation.JsonView;
import com.jaba.webapp.controller.jsonviews.JSONViews;
import com.jaba.webapp.domain.item.Item;
import com.jaba.webapp.service.item.ItemManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.ApplicationScope;

import javax.validation.Valid;
import java.util.List;

@ApplicationScope
@Controller
public class RestApi {

    private ItemManager itemService;

    @Autowired
    public RestApi(ItemManager itemService){
        this.itemService = itemService;
    }


    @RequestMapping(value = "/api/items", method = RequestMethod.GET)
    @ResponseBody
    public List<Item> getItemsRest(){
        return  itemService.getAllItems();
    }

    @RequestMapping(value = "/api/items/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Item getItemRest(@PathVariable Long id){
        return  itemService.getItemById(id);
    }


    @RequestMapping(value = "/api/items/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteItemsRest(@PathVariable Long id) {
        itemService.deleteItem(id);
        return "sucess";
    }


    @RequestMapping(value = "/api/items", method = RequestMethod.POST)
    @ResponseBody
    public String setItemRest(@RequestBody @Valid Item item){
        itemService.addItem(item);
        return "sucess";
    }


    @RequestMapping(value = "/api/items/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public String updateItemRest(@RequestBody @Valid Item item, @PathVariable Long id){
        item.setId(id);
        itemService.updateItem(item);
        return "sucess";
    }
}
