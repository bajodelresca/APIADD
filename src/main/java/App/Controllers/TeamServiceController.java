/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.Controllers;

import App.Exceptions.RecordNotFoundException;
import App.Model.Team;
import App.Services.PlayerService;
import App.Services.TeamService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @author espin
 */
@SpringBootApplication
@EnableSwagger2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/team")
public class TeamServiceController {

    @Autowired
    TeamService service;

    @ApiOperation(value = "getAllItems", notes = "Esta función nos devolverá una lista todos los equipos, junto a una respuesta HTTP")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = Team.class),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @GetMapping
    public ResponseEntity<List<Team>> getAllItems() {
        List<Team> list = service.getAllTeams();

        return new ResponseEntity<List<Team>>(list, new HttpHeaders(), HttpStatus.OK);
    }
    
    @ApiOperation(value = "getItemById", notes = "Esta función nos devolverá un equipo por id, junto a una respuesta HTTP")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = Team.class),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @GetMapping("/{id}")
    public ResponseEntity<Team> getItemById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        Team entity = service.getTeamById(id);

        return new ResponseEntity<Team>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    
    @ApiOperation(value = "getItemsByTitle", notes = "Esta función nos devolverá una lista de equipos, junto a una respuesta HTTP")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = Team.class),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @GetMapping("/search/{name}")
    public ResponseEntity<List<Team>> getItemsByTitle(@PathVariable("name") String title) {
        List<Team> list = service.geteTeamByTitle(title);

        return new ResponseEntity<List<Team>>(list, new HttpHeaders(), HttpStatus.OK);
    }
    
    
    
    @ApiOperation(value = "createItem", notes = "Esta función creara un equipo, junto a una respuesta HTTP")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = Team.class),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @PostMapping
    public ResponseEntity<Team> createItem(@Valid @RequestBody Team myItem) {
        Team created = service.createTeam(myItem);
        return new ResponseEntity<Team>(created, new HttpHeaders(), HttpStatus.OK);
    }
    
    

    @ApiOperation(value = "UpdateItem", notes = "Esta función actualiza un equipo, junto a una respuesta HTTP")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = Team.class),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @PutMapping
    public ResponseEntity<Team> UpdateItem(@Valid @RequestBody Team myItem)
            throws RecordNotFoundException {
        Team updated = service.createTeam(myItem);
        return new ResponseEntity<Team>(updated, new HttpHeaders(), HttpStatus.OK);
    }
    
    
    
    

     @ApiOperation(value = "deleteItemById", notes = "Esta función borra un equipo, junto a una respuesta HTTP")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = Team.class),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @DeleteMapping("/{id}")
    public HttpStatus deleteItemById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        service.deleteTeamById(id);
        return HttpStatus.ACCEPTED;
    }

    
    @ApiOperation(value = "getItemsByName", notes = "Esta función busca un equipo, junto a una respuesta HTTP")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = Team.class),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @GetMapping("/searching/{name}")
    public ResponseEntity<Team> getItemsByName(@PathVariable("name") String name) {
        Team count = service.searchName(name);

        return new ResponseEntity<Team>(count, new HttpHeaders(), HttpStatus.OK);
    }

    
    @ApiOperation(value = "getAllItemsPoints", notes = "Esta función devuelve una lista de equipos ordenada por Points, junto a una respuesta HTTP")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = Team.class),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @GetMapping("/clasification")
    public ResponseEntity<List<Team>> getAllItemsPoints() {
        List<Team> list = service.getAllTeamsPoints();

        return new ResponseEntity<List<Team>>(list, new HttpHeaders(), HttpStatus.OK);
    }
}
