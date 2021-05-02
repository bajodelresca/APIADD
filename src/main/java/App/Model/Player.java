/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

/**
 *
 * @author espin
 */
@Entity
@Table(name = "player")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "image", columnDefinition="Text")
    private String image;
    @Column(name = "games")
    private Long games;
    @Column(name = "goals")
    private Long goals;
    @Column(name = "assists")
    private Long assists;
    @Column(name = "yellowcards")
    private Long yellowcards;
    @Column(name = "redcards")
    private Long redcards;
    @Column(name = "mvp")
    private Long mvp;
    
    
    //@JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER ,cascade = {CascadeType.MERGE})
    @JoinColumn(name = "idequipo")
    @JsonIgnoreProperties(value={"repertorio"},allowSetters = true)    
    protected Team team;

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        if(team==null){
        team=new Team();
        }
        this.team = team;
        Set<Player> list=this.team.getRepertorio();
        if(list==null){
            list=new HashSet<Player>();
            
        }
        if(!list.contains(this)){
            list.add(this);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getGames() {
        return games;
    }

    public void setGames(Long games) {
        this.games = games;
    }

    public Long getGoals() {
        return goals;
    }

    public void setGoals(Long goals) {
        this.goals = goals;
    }

    public Long getAssists() {
        return assists;
    }

    public void setAssists(Long assists) {
        this.assists = assists;
    }

    public Long getYellowcards() {
        return yellowcards;
    }

    public void setYellowcards(Long yellowcards) {
        this.yellowcards = yellowcards;
    }

    public Long getRedcards() {
        return redcards;
    }

    public void setRedcards(Long redcards) {
        this.redcards = redcards;
    }

    public Long getMvp() {
        return mvp;
    }

    public void setMvp(Long mvp) {
        this.mvp = mvp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Player{" + "id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", image=" + image + ", games=" + games + ", goals=" + goals + ", assists=" + assists + ", yellowcards=" + yellowcards + ", redcards=" + redcards + ", mvp=" + mvp + ", team=" + team + '}';
    }

    
    
    
    
    
    
   
}
