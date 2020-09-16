package com.forca.jogo_da_forca.controllers;

import com.forca.jogo_da_forca.exceptions.ResourceNotFoundException;
import com.forca.jogo_da_forca.models.Categoria;
import com.forca.jogo_da_forca.models.Palavra;
import com.forca.jogo_da_forca.repositories.CategoriaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@SuppressWarnings("SpellCheckingInspection")
@RestController
@RequestMapping("api/categorias")
@CrossOrigin(value = "*")
public class CategoriaController {
    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping  //GET ALL CATEGORIA ----------------------------------------------
    public List<Categoria> list() {
        return categoriaRepository.findAll();
    }

    @GetMapping        //GET CATEGORIA BY ID---------------------------------------
    @RequestMapping("{id}")
    public ResponseEntity<Categoria> get(@PathVariable Integer id) {
        try {
            return new ResponseEntity<Categoria>(categoriaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException()), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada");
        }
    }

    @PostMapping  //ADICIONAR CATEGORIA --------------------------------------------------------------------------------
    public ResponseEntity<Categoria> create(@RequestBody final Categoria categoria) {
        try {
            return new ResponseEntity<Categoria>(categoriaRepository.saveAndFlush(categoria), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao Adicionar Categoria");
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)   //APAGAR CATEGORIA -------------------------------------
    public void delete(@PathVariable Integer id) {
        try {
            categoriaRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao Apagar Categoria (Apaga primeiro as palavras que contêm esta categoria)");
        }
    }   

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)  //ALTERAR CATEGORIA -------------------------------------
    public ResponseEntity<Categoria> update(@PathVariable Integer id, @RequestBody Categoria categoria) {
        try {
            return new ResponseEntity<Categoria>(categoriaRepository.findById(id).map(cat -> {
                BeanUtils.copyProperties(categoria, cat, "id");
                return categoriaRepository.saveAndFlush(cat);
            }).orElseThrow(() -> new ResourceNotFoundException()), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao Alterar Categoria");
        }
    }
}
