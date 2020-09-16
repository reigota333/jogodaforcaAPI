package com.forca.jogo_da_forca.controllers;


import com.forca.jogo_da_forca.exceptions.ResourceNotFoundException;
import com.forca.jogo_da_forca.models.Dificuldade;
import com.forca.jogo_da_forca.repositories.DificuldadeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@SuppressWarnings("SpellCheckingInspection")
@RestController
@RequestMapping("api/dificuldades")
@CrossOrigin(value = "*")
public class DificuldadeController {

    @Autowired
    private DificuldadeRepository dificuldadeRepository;

    @GetMapping  //GET ALL DIFICULTIES------------------------------------------
    public List<Dificuldade> list() {
        return dificuldadeRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")  //GET DIFICULTIE BY ID -------------------------------------
    public ResponseEntity<Dificuldade> get(@PathVariable Integer id) {
        try {
            return new ResponseEntity<Dificuldade>(dificuldadeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException()), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi encontrada a dificuldade");
        }
    }

    @PostMapping  //CREATE DIFICULTIE --------------------------------------------------------
    public ResponseEntity<Dificuldade> create(@RequestBody final Dificuldade dificuldade) {
        try {
            return new ResponseEntity<Dificuldade>(dificuldadeRepository.saveAndFlush(dificuldade), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao Adicionar Dificuldade");
        }
    }

       //DELETE DIFICULTIE ----------------------------------------------------
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Integer id) {
        try {
            dificuldadeRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao Apagar dificuldade");
        }
    }

    //UPDATE DIFICULTIE ------------------------------------------------------------

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<Dificuldade> update(@PathVariable Integer id, @RequestBody Dificuldade dificuldade) {
        try {
            return new ResponseEntity<Dificuldade>(dificuldadeRepository.findById(id).map(dif -> {
                BeanUtils.copyProperties(dificuldade, dif, "id");
                return dificuldadeRepository.saveAndFlush(dif);
            }).orElseThrow(() -> new ResourceNotFoundException()), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao Alterar Dificuldade");
        }
    }
}
