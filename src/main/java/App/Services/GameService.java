/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.Services;

import App.Exceptions.RecordNotFoundException;
import App.Model.Game;
import App.Repositories.GameRepository;
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
public class GameService {
     @Autowired
    GameRepository repository;
     
    public List<Game> getAllTeams()
    {
        List<Game> TeamList = repository.findAll();
         
        if(TeamList.size() > 0) {
            return TeamList;
        } else {
            return new ArrayList<Game>();
        }
    }
     
    public Game getTeamById(Long id) throws RecordNotFoundException
    {
        Optional<Game> team = repository.findById(id);
         
        if(team.isPresent()) {
            return team.get();
        } else {
            throw new RecordNotFoundException("No item record exist for given id",id);
        }
    }
    public Game createTeam(Game entity){
        entity = repository.save(entity);
        return entity;
    }
    public Game UpdateTeam(Game entity) throws RecordNotFoundException
    {
    	    	
    	if(entity.getId()!=null)
    	{
    	  Optional<Game> game = repository.findById(entity.getId());
        
            if(game.isPresent())
            {
                Game newEntity = game.get();
                //newEntity.setId(entity.getId());
                newEntity.setJornada(entity.getJornada());
                newEntity.setResultado(entity.getResultado());
                newEntity.setT(entity.getT());
                

                newEntity = repository.save(newEntity);

                return newEntity;
            } else {
                throw new RecordNotFoundException("Item not found",entity.getId());
            }
        }else{
    		throw new RecordNotFoundException("No id of item given",0l);
    	}	    
 }
     
    public void deleteTeamById(Long id) throws RecordNotFoundException
    {
        Optional<Game> team = repository.findById(id);
         
        if(team.isPresent())
        {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No item record exist for given id",id);
        }
    }    

}
