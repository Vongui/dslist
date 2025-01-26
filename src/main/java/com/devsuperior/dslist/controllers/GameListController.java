package com.devsuperior.dslist.controllers;

import com.devsuperior.dslist.dto.GameListDTO;
import com.devsuperior.dslist.dto.GameMinDTO;
import com.devsuperior.dslist.dto.ReplacementDTO;
import com.devsuperior.dslist.services.GameListService;
import com.devsuperior.dslist.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/lists")
public class GameListController {

    @Autowired
    private GameListService gameListService;
    @Autowired
    private GameService gameService;

    @GetMapping
    public List<GameListDTO> findGameList(){
        List<GameListDTO> result = gameListService.findGameList();
        return result;
    }

    //Por o endpoint ser localizado em "/lists" eh necessário que fica alocado no GamelistController, ao inves de ficar
    //no GameController
    @GetMapping(value = "/{listId}/games")
    public List<GameMinDTO> findByList(@PathVariable long listId){
        List<GameMinDTO> result = gameService.findByList(listId);
        return result;
    }

    @PostMapping(value = "/{listId}/replacement")
    public void move(@PathVariable long listId, @RequestBody ReplacementDTO body){
        gameListService.move(listId, body.getSourceIndex(), body.getDestinationIndex());
    }

}
