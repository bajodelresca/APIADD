/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.Repositories;

import App.Model.Item;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author espin
 */
@Repository
public interface ItemRepository
        extends JpaRepository<Item, Long> {

    @Query(
    value="SELECT * FROM items AS i WHERE i.title LIKE %?1%",
            nativeQuery=true)
    public List<Item> getByTitle(String title);
 
}
