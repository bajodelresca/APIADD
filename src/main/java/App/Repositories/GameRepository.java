/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.Repositories;

import App.Model.Game;
import App.Model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author espin
 */
public interface GameRepository  extends JpaRepository<Game, Long>  {
    
}
