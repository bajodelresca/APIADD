/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.Controllers;

import App.Exceptions.RecordNotFoundException;
import App.Model.Game;
import App.Services.GameService;
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @author espin
 */
@SpringBootApplication
@EnableSwagger2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/game")
public class GameServiceController {

    @Autowired
    GameService service;

    @ApiOperation(value = "getAllItems", notes = "Esta función nos devolverá una lista todos los partidos, junto a una respuesta HTTP")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = Game.class),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @GetMapping
    public ResponseEntity<List<Game>> getAllItems() {
        List<Game> list = service.getAllTeams();

        return new ResponseEntity<List<Game>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation(value = "getItemById", notes = "Esta función nos devolverá un partido por id, junto a una respuesta HTTP")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = Game.class),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @GetMapping("/{id}")
    public ResponseEntity<Game> getItemById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        Game entity = service.getTeamById(id);

        return new ResponseEntity<Game>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation(value = "createItem", notes = "Esta función creara un partido, junto a una respuesta HTTP")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = Game.class),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @PostMapping
    public ResponseEntity<Game> createItem(@Valid @RequestBody Game myItem) {
        Game created = service.createTeam(myItem);
        return new ResponseEntity<Game>(created, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation(value = "UpdateItem", notes = "Esta función actualizara un partido, junto a una respuesta HTTP")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = Game.class),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @PutMapping
    public ResponseEntity<Game> UpdateItem(@Valid @RequestBody Game myItem)
            throws RecordNotFoundException {
        Game updated = service.createTeam(myItem);
        return new ResponseEntity<Game>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation(value = "deleteItemById", notes = "Esta función borrara un partido, junto a una respuesta HTTP")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = Game.class),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @DeleteMapping("/{id}")
    public HttpStatus deleteItemById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        service.deleteTeamById(id);
        return HttpStatus.ACCEPTED;
    }
    @ApiOperation(value = "GetallItembIDORDER", notes = "Esta función devuelve una lista de equipos ordenada por Points, junto a una respuesta HTTP")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = Game.class),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @GetMapping("/ids")
    public ResponseEntity<List<Game>> getAllItemsPoints() {
        List<Game> list = service.getAllGamesid();

        return new ResponseEntity<List<Game>>(list, new HttpHeaders(), HttpStatus.OK);
    }
@ApiOperation(value = "getGameByTeam", notes = "Esta función buscara un partido por equipo, junto a una respuesta HTTP")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = Game.class),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @GetMapping("/byteam/{id}")
    public ResponseEntity<List<Game>> getGameByTeam(@PathVariable("id") Long id) throws RecordNotFoundException {
        List<Game> list = service.getGamesbyTeam(id);

        return new ResponseEntity<List<Game>>(list, new HttpHeaders(), HttpStatus.OK);
    }


}
