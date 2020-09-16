package com.forca.jogo_da_forca.controllers;

import com.forca.jogo_da_forca.interfaces.GameInterface;
import com.forca.jogo_da_forca.models.Game;
import com.forca.jogo_da_forca.models.Palavra;
import com.forca.jogo_da_forca.object.CharObject;
import com.forca.jogo_da_forca.object.HitObject;
import com.forca.jogo_da_forca.object.StringObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("api/game")
@CrossOrigin(value = "*")
public class GameController {

    @PostMapping  //INICIA O JOGO E ENVIA A LENGTH DA PALAVRA -----------------------------------------------
    public ResponseEntity<Integer> startGame(@RequestBody Palavra palavra) {
        try {
            return new ResponseEntity<Integer>(Game.getInstance().startGame(palavra), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao Iniciar o Jogo");
        }
    }

    @PostMapping  //TENTA ACERTAR NUMA LETRA ------------------------------------------------
    @RequestMapping("/tryhit")
    public ResponseEntity<HitObject> tryHit(@RequestBody CharObject c) {
        try {
            return new ResponseEntity<HitObject>(Game.getInstance().tryHit(c), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao tentar acertar em Letra");
        }
    }

    @PostMapping  //TENTA ACERTAR NA PALAVRA -------------------------------------------------
    @RequestMapping("/try")
    public ResponseEntity<HitObject> tryWord(@RequestBody StringObject palavra) {
        try {
            return new ResponseEntity<HitObject>(Game.getInstance().tryWord(palavra), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao tentar advinhar a palavra");
        }
    }
}
