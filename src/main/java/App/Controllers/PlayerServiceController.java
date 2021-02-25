/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.Controllers;

import App.Exceptions.RecordNotFoundException;
import App.Model.Player;
import App.Services.PlayerService;
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
//prueba
@SpringBootApplication
@EnableSwagger2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/player")
public class PlayerServiceController {

    @Autowired
    PlayerService service;

    @ApiOperation(value = "getAllItems", notes = "Esta función nos devolverá una lista todos los jugadores, junto a una respuesta HTTP")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = Player.class),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @GetMapping
    public ResponseEntity<List<Player>> getAllItems() {
        List<Player> list = service.getAllItems();

        return new ResponseEntity<List<Player>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation(value = "getItemById", notes = "Esta función nos devolverá un jugador por id, junto a una respuesta HTTP")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = Player.class),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @GetMapping("/{id}")
    public ResponseEntity<Player> getItemById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        Player entity = service.getPlayerById(id);

        return new ResponseEntity<Player>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation(value = "getItemsByTitle", notes = "Esta función nos devolverá una lista de jugadores por nombre, junto a una respuesta HTTP")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = Player.class),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @GetMapping("/search/{name}")
    public ResponseEntity<List<Player>> getItemsByTitle(@PathVariable("name") String title) {
        List<Player> list = service.getPlayerByTitle(title);

        return new ResponseEntity<List<Player>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation(value = "createItem", notes = "Esta función creara un jugador, junto a una respuesta HTTP")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = Player.class),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @PostMapping
    public ResponseEntity<Player> createItem(@Valid @RequestBody Player myItem) {
        Player created = service.createPlayer(myItem);
        return new ResponseEntity<Player>(created, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation(value = "UpdateItem", notes = "Esta función actualizará un jugador, junto a una respuesta HTTP")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = Player.class),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @PutMapping
    public ResponseEntity<Player> UpdateItem(@Valid @RequestBody Player myItem)
            throws RecordNotFoundException {
        Player updated = service.createPlayer(myItem);
        return new ResponseEntity<Player>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation(value = "deleteItemById", notes = "Esta función borrara un jugador, junto a una respuesta HTTP")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = Player.class),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @DeleteMapping("/{id}")
    public HttpStatus deleteItemById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        service.deletePlayerById(id);
        return HttpStatus.ACCEPTED;
    }

    @ApiOperation(value = "deleteItemById", notes = "Esta función buscara un jugador por correo y contraseña, junto a una respuesta HTTP")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = Player.class),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @GetMapping("/search/{email}/{password}")
    public ResponseEntity<Player> getItemsByCount(@PathVariable("email") String email, @PathVariable("password") String password) {
        Player count = service.searchCount(email, password);

        return new ResponseEntity<Player>(count, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation(value = "getItemsByEmail", notes = "Esta función buscara un jugador por correo, junto a una respuesta HTTP")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = Player.class),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @GetMapping("/searching/{email}")
    public ResponseEntity<Player> getItemsByEmail(@PathVariable("email") String email) {
        Player count = service.searchEmail(email);

        return new ResponseEntity<Player>(count, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation(value = "getPlayerByTeam", notes = "Esta función buscara un jugador por equipo, junto a una respuesta HTTP")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = Player.class),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @GetMapping("/team/{id}")
    public ResponseEntity<List<Player>> getPlayerByTeam(@PathVariable("id") Long id) throws RecordNotFoundException {
        List<Player> list = service.getPlayerByTeam(id);

        return new ResponseEntity<List<Player>>(list, new HttpHeaders(), HttpStatus.OK);
    }
}
