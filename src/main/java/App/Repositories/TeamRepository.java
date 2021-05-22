/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.Repositories;

import App.Model.Team;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author espin
 */
@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    @Query(
    value="SELECT * FROM team AS i WHERE i.name LIKE %?1%",
            nativeQuery=true)
    public List<Team> getByName(String name);
    
    @Query(
    value="SELECT * FROM team AS i WHERE i.name LIKE ?1",
            nativeQuery=true)
     public Team searchName(String name);
     
      @Query(
    value="SELECT * FROM team ORDER BY points DESC;",
            nativeQuery=true)
    public List<Team> getByPoints();
}
