/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.Services;

import App.Exceptions.RecordNotFoundException;
import App.Model.Team;
import App.Repositories.PlayerRepository;
import App.Repositories.TeamRepository;
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
public class TeamService {
     @Autowired
    TeamRepository repository;
     
    public List<Team> getAllTeams()
    {
        List<Team> TeamList = repository.findAll();
         
        if(TeamList.size() > 0) {
            return TeamList;
        } else {
            return new ArrayList<Team>();
        }
    }
     
    public Team getTeamById(Long id) throws RecordNotFoundException
    {
        Optional<Team> team = repository.findById(id);
         
        if(team.isPresent()) {
            return team.get();
        } else {
            throw new RecordNotFoundException("No item record exist for given id",id);
        }
    }
    public Team createTeam(Team entity){
        entity = repository.save(entity);
        return entity;
    }
    public Team UpdateTeam(Team entity) throws RecordNotFoundException
    {
    	    	
    	if(entity.getId()!=null)
    	{
    	  Optional<Team> team = repository.findById(entity.getId());
        
            if(team.isPresent())
            {
                Team newEntity = team.get();
                //newEntity.setId(entity.getId());
                newEntity.setName(entity.getName());
                newEntity.setImage(entity.getImage());
                newEntity.setGames(entity.getGames());
                newEntity.setMatcheswon(entity.getMatcheswon());
                newEntity.setLostmatches(entity.getLostmatches());
                newEntity.setTiedmatches(entity.getTiedmatches());
                newEntity.setGoals(entity.getGoals());
                newEntity.setGoalsc(entity.getGoalsc());
                newEntity.setCreatedate(entity.getCreatedate());
                newEntity.setPoints(entity.getPoints());
                newEntity.setMatches(entity.getMatches());

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
        Optional<Team> team = repository.findById(id);
         
        if(team.isPresent())
        {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No item record exist for given id",id);
        }
    }
    
    public List<Team> geteTeamByTitle(String name) {
        List<Team> teamList = repository.getByName(name);
         
        if(teamList.size() > 0) {
            return teamList;
        } else {
            return new ArrayList<Team>();
        }
    }
    
     public Team searchName(String name){
        Team p= repository.searchName(name);
        if(p!=null){
            return p;
        }else{
            return new Team();
        }
    }
    
    public List<Team> getAllTeamsPoints()
    {
        List<Team> TeamList = repository.getByPoints();
         
        if(TeamList.size() > 0) {
            return TeamList;
        } else {
            return new ArrayList<Team>();
        }
    }
}
