/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.Repositories;

import App.Model.Game;
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
public interface GameRepository  extends JpaRepository<Game, Long>  {
    @Query(
    value="SELECT * FROM game ORDER BY id DESC LIMIT 1;",
            nativeQuery=true)
    public List<Game> getByidorder();
@Query(
    value="SELECT * FROM JORNADAS WHERE fk_team=1",
            nativeQuery=true)
    public List<Game> getByTeamId(Long id);

}
