/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.Model;

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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 *
 * @author espin
 */
@Entity
@Table(name = "team")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(name = "name")
    private String name;
    @Column(name = "image", columnDefinition = "Text")
    private String image;
    @Column(name = "games")
    private Long games;
    @Column(name = "matcheswon")
    private Long matcheswon;
    @Column(name = "lostmatches")
    private Long lostmatches;
    @Column(name = "tiedmatches")
    private Long tiedmatches;
    @Column(name = "goals")
    private Long goals;
    @Column(name = "goalsc")
    private Long goalsc;
    @Column(name = "createdate")
    private Long createdate;
    @Column(name = "points")
    private Long points;

    @JsonIgnoreProperties(value={"team"},allowSetters = true)
    @OneToMany(mappedBy = "team", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    protected Set<Player> repertorio;

    @Column(name = "JORNADAS")
    @JoinTable(name = "jornadas", joinColumns = @JoinColumn(name = "FK_TEAM", nullable = false), inverseJoinColumns = @JoinColumn(name = "FK_GAME", nullable = false))
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnoreProperties(value = {"t"}, allowSetters = true)
    private List<Game> matches;

    public List<Game> getMatches() {
        return matches;
    }

    public void setMatches(List<Game> matches) {
        if (matches == null) {
            matches = new ArrayList<Game>();
        }
        this.matches = matches;
        for (Game exercise : matches) {
            List<Team> list = exercise.getT();
            if (list == null) {
                list = new ArrayList<Team>();
            }
            if (!list.contains(this)) {
                list.add(this);
            }
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

    public Long getMatcheswon() {
        return matcheswon;
    }

    public void setMatcheswon(Long matcheswon) {
        this.matcheswon = matcheswon;
    }

    public Long getLostmatches() {
        return lostmatches;
    }

    public void setLostmatches(Long lostmatches) {
        this.lostmatches = lostmatches;
    }

    public Long getTiedmatches() {
        return tiedmatches;
    }

    public void setTiedmatches(Long tiedmatches) {
        this.tiedmatches = tiedmatches;
    }

    public Long getGoals() {
        return goals;
    }

    public void setGoals(Long goals) {
        this.goals = goals;
    }

    public Long getGoalsc() {
        return goalsc;
    }

    public void setGoalsc(Long goalsc) {
        this.goalsc = goalsc;
    }

    public Long getCreatedate() {
        return createdate;
    }

    public Long getPoints() {
        return points;
    }

    public void setPoints(Long points) {
        this.points = points;
    }

    public void setCreatedate(Long createdate) {
        this.createdate = createdate;
    }

    public Set<Player> getRepertorio() {
        return repertorio;
    }

    public void setRepertorio(Set<Player> repertorio) {
        if (repertorio == null) {
            repertorio = new HashSet<Player>();
        }
        this.repertorio = repertorio;
        for (Player p : repertorio) {
            p.setTeam(this);
        }
    }

}
