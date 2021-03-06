/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.Controllers;

import App.Exceptions.RecordNotFoundException;
import App.Model.Item;
import App.Services.ItemService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 *
 * @author espin
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/item")
public class ItemServiceController
{
    @Autowired
    ItemService service;
 
    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> list = service.getAllItems();
 
        return new ResponseEntity<List<Item>>(list, new HttpHeaders(), HttpStatus.OK);
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable("id") Long id)
                                                    throws RecordNotFoundException {
    	Item entity = service.getItemById(id);
 
        return new ResponseEntity<Item>(entity, new HttpHeaders(), HttpStatus.OK);
    }
    
    @GetMapping("/search/{title}")
    public ResponseEntity<List<Item>> getItemsByTitle(@PathVariable("title") String title) {
    	List<Item> list = service.getItemsByTitle(title);
 
        return new ResponseEntity<List<Item>>(list, new HttpHeaders(), HttpStatus.OK);
    }
 
   @PostMapping
    public ResponseEntity<Item> createItem(@Valid @RequestBody Item myItem){
    	Item created = service.createItem(myItem);
        return new ResponseEntity<Item>(created, new HttpHeaders(), HttpStatus.OK);
    }
    
    @PutMapping
    public ResponseEntity<Item> UpdateItem(@Valid @RequestBody Item myItem)
                                                    throws RecordNotFoundException {
    	Item updated = service.createItem(myItem);
        return new ResponseEntity<Item>(updated, new HttpHeaders(), HttpStatus.OK);
    }
 
    @DeleteMapping("/{id}")
    public HttpStatus deleteItemById(@PathVariable("id") Long id)
                                                    throws RecordNotFoundException {
        service.deleteItemById(id);
        return HttpStatus.ACCEPTED;
    }
 
}
 
