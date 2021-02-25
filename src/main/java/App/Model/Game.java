/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

/**
 *
 * @author espin
 */
@Entity
@Table(name = "game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "jornada")
    private Long jornada;
    @Column(name = "resultado")
    private String resultado;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "matches", cascade = CascadeType.MERGE)
    @JsonIgnoreProperties(value = {"matches"}, allowSetters = true)
    private List<Team> t;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getJornada() {
        return jornada;
    }

    public void setJornada(Long jornada) {
        this.jornada = jornada;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public List<Team> getT() {
        return t;
    }

    public void setT(List<Team> t) {
        if (t == null) {
            t = new ArrayList<Team>();
        }
        this.t = t;
        for (Team team : t) {
            List<Game> list = team.getMatches();
            if (list == null) {
                list = new ArrayList<Game>();
            }
            if (!list.contains(this)) {
                list.add(this);
            }
        }
    }

    @Override
    public String toString() {
        return "Game{" + "id=" + id + ", jornada=" + jornada + ", resultado=" + resultado + ", t=" + t + '}';
    }

}
