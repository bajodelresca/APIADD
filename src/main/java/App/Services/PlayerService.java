/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.Services;

import App.Exceptions.RecordNotFoundException;
import App.Model.Player;
import App.Repositories.PlayerRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author espin
 */
@Service
public class PlayerService {

    @Autowired
    PlayerRepository repository;

    public List<Player> getAllItems() {
        List<Player> playerList = repository.findAll();

        if (playerList.size() > 0) {
            return playerList;
        } else {
            return new ArrayList<Player>();
        }
    }

    public Player getPlayerById(Long id) throws RecordNotFoundException {
        Optional<Player> player = repository.findById(id);

        if (player.isPresent()) {
            return player.get();
        } else {
            throw new RecordNotFoundException("No item record exist for given id", id);
        }
    }

    public Player createPlayer(Player entity) {
        entity = repository.save(entity);
        return entity;
    }

    public Player UpdatePlayer(Player entity) throws RecordNotFoundException {

        if (entity.getId() != null) {
            Optional<Player> player = repository.findById(entity.getId());

            if (player.isPresent()) {
                Player newEntity = player.get();
                //newEntity.setId(entity.getId());
                newEntity.setName(entity.getName());
                newEntity.setImage(entity.getImage());
                newEntity.setGames(entity.getGames());
                newEntity.setGoals(entity.getGoals());
                newEntity.setAssists(entity.getAssists());
                newEntity.setYellowcards(entity.getYellowcards());
                newEntity.setRedcards(entity.getRedcards());
                newEntity.setMvp(entity.getMvp());
                newEntity.setTeam(entity.getTeam());

                newEntity = repository.save(newEntity);

                return newEntity;
            } else {
                throw new RecordNotFoundException("Item not found", entity.getId());
            }
        } else {
            throw new RecordNotFoundException("No id of item given", 0l);
        }
    }

    public void deletePlayerById(Long id) throws RecordNotFoundException {
        Optional<Player> player = repository.findById(id);

        if (player.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No item record exist for given id", id);
        }
    }

    public List<Player> getPlayerByTitle(String name) {
        List<Player> playerList = repository.getByName(name);

        if (playerList.size() > 0) {
            return playerList;
        } else {
            return new ArrayList<Player>();
        }
    }

    public Player searchCount(String email, String password) {
        Player p = repository.searchCount(email, password);
        if (p != null) {
            return p;
        } else {
            return new Player();
        }
    }

    public Player searchEmail(String email) {
        Player p = repository.searchEmail(email);
        if (p != null) {
            return p;
        } else {
            return new Player();
        }
    }

    public List<Player> getPlayerByTeam(Long id) {
        List<Player> list = repository.getPlayerByTeam(id);

        if (list.size() > 0) {
            return list;
        } else {
            return new ArrayList<Player>();
        }
    }

    /*public void updateplayerteam(Long idequipo,Long id) throws RecordNotFoundException
    {
        Optional<Player> player = repository.findById(id);
         
        if(player.isPresent())
        {
            repository.updateteam(idequipo, id);
        } else {
            throw new RecordNotFoundException("No item record exist for given id",id);
        }
    }*/
}
