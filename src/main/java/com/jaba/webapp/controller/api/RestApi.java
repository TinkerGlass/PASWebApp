package com.jaba.webapp.controller.api;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.exc.InvalidTypeIdException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.jaba.webapp.controller.jsonviews.JSONViews;
import com.jaba.webapp.domain.item.Item;
import com.jaba.webapp.exceptions.ApplicationException;
import com.jaba.webapp.service.item.ItemManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
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

    @ResponseStatus(value= HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ MismatchedInputException.class })
    public void handleMismatchedInputException() { }

    @ExceptionHandler({ ApplicationException.class })
    public ResponseEntity<?> handleApplicationException(ApplicationException ex) {
        switch(ex.getReason()) {
            case ITEM_ID_DOESNT_EXIST:
                return ResponseEntity.notFound().build();
            default:
                return ResponseEntity.unprocessableEntity().build();
        }
    }

    @RequestMapping(value = "/api/items", method = RequestMethod.GET)
    @ResponseBody
    public List<Item> getItemsRest(){
        return  itemService.getAllItems();
    }

    @RequestMapping(value = "/api/items/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Item> getItemRest(@PathVariable Long id){
        Item item = itemService.getItemById(id);
        if(item == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(item);
    }


    @RequestMapping(value = "/api/items/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<?> deleteItemsRest(@PathVariable Long id) {
        itemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }


    @RequestMapping(value = "/api/items", method = RequestMethod.POST)
    @ResponseBody
    public String setItemRest(@RequestBody Item item){
        itemService.addItem(item);
        return "sucess";
    }


    @RequestMapping(value = "/api/items/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public String updateItemRest(@RequestBody Item item, @PathVariable Long id){
        item.setId(id);
        itemService.updateItem(item);
        return "sucess";
    }
}
