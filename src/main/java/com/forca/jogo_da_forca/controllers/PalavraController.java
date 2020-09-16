package com.forca.jogo_da_forca.controllers;

import com.forca.jogo_da_forca.exceptions.ResourceNotFoundException;
import com.forca.jogo_da_forca.models.Palavra;
import com.forca.jogo_da_forca.repositories.DificuldadeRepository;
import com.forca.jogo_da_forca.repositories.PalavraRepository;
import org.apache.tomcat.jni.Error;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("SpellCheckingInspection")
@RestController
@RequestMapping("api/palavras")
@CrossOrigin(value = "*")
public class PalavraController {

    @Autowired
    private PalavraRepository palavraRepository;

    @Autowired
    private DificuldadeRepository dificuldadeRepository;

    @GetMapping
    @RequestMapping("/{dif}/{cat}")   //GET WORD BY CONFIG -----------------------------
    public ResponseEntity<Palavra> get(@PathVariable("dif") Integer dif, @PathVariable("cat") Integer cat) {
        try {
            if (palavraHelperSend(dif, cat) != null) {
                return new ResponseEntity<Palavra>(palavraHelperSend(dif, cat), HttpStatus.OK);
            } else {
                throw new ResourceNotFoundException();
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não existe nenhuma palavra com essa configuração");
        }
    }

    @GetMapping    //GET ALL WORDS  ------------------------------------
    public List<Palavra> list() {
        return palavraRepository.findAll();
    }

    @GetMapping             //GET WORD BY ID -----------------------------
    @RequestMapping("/{id}")
    public ResponseEntity<Palavra> get(@PathVariable Integer id) {
        try {
            return new ResponseEntity<Palavra>(palavraRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException()), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Palavra não encontrada");
        }
    }

    @PostMapping           //ADD WORD ------------------------------------------
    @RequestMapping("/dificuldade/{difId}")
    public ResponseEntity<Palavra> create(@PathVariable(value = "difId") Integer difId, @RequestBody Palavra palavra) {
        try {
            return new ResponseEntity<Palavra>(dificuldadeRepository.findById(difId).map(dif -> {
                palavra.setDificuldade(dif);
                return palavraRepository.saveAndFlush(palavra);
            }).orElseThrow(() -> new ResourceNotFoundException("difId" + difId + "not found")), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro a adicionar Palavra");
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)   //DELETE --------------------
    public void delete(@PathVariable Integer id) {
        try {
            palavraRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao Apagar palavra");
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)       //Update Palavra -----------------
    public ResponseEntity<Palavra> update(@PathVariable Integer id, @RequestBody Palavra palavra) {
        try {
            return new ResponseEntity<Palavra>(palavraRepository.findById(id).map(pal -> {
                BeanUtils.copyProperties(palavra, pal, "id");
                return palavraRepository.saveAndFlush(pal);
            }).orElseThrow(() -> new ResourceNotFoundException()),HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro a alterar palavra");
        }
    }

    //METHOD TO CHOOSE WHICH METHOD SHOULD BE CALLED WITH THE CONFIGURATION GIVEN -------------------------------------

    public Palavra palavraHelperSend(Integer dif, Integer cat) {
        //CALL RANDOM METHOD
        if (dif == 0 && cat == 0) return palavraRepository.getRandom();
            //CAL CAT METHOD
        else if (dif == 0 && cat != 0) return palavraRepository.getCatPalavra(cat);
            //CALL METHOD DIF
        else if (dif != 0 && cat == 0) return palavraRepository.getDifPalavra(dif);
            //CALL NORMAL METHOD
        else return palavraRepository.getPalavra(cat, dif);
    }
}
