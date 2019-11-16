package com.jaba.webapp.uglytests.controllCRUD;

import com.jaba.webapp.domain.item.Item;
import com.jaba.webapp.repository.item.ItemRepository;
import com.jaba.webapp.repository.specification.item.ItemSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UglyTestController {

    private ItemRepository itemRepository;

    //Items
    @GetMapping("/items")
    private List<Item> getAllItems(){
        return itemRepository.find(ItemSpecification.all());
    }

    @GetMapping("/item/{id}")
    private Item getItemById(@PathVariable("id") Long id) {
        return itemRepository.find(ItemSpecification.byId(id)).get(0);
    }

    @GetMapping("/item")
    private List<Item> getItem(@RequestParam String title) {
        return itemRepository.find(ItemSpecification.byTitle(title));
    }

    @GetMapping("/itemstest")
    private void insertItem(){
        //dataContext.insertItem(new Item(1, new AuditInfo(), "title", "author", Item.Genre.CLASSICAL, new Date(), 1));
    }

    @DeleteMapping("/item/{id}")
    private void removeItem(@PathVariable("id") Long id){
        itemRepository.removeItem(id);
    }

    @Autowired
    public void setItemRepository(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }
}
