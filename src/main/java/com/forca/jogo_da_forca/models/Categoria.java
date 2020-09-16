package com.forca.jogo_da_forca.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@SuppressWarnings("SpellCheckingInspection")
@Entity(name = "categorias")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Categoria {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private String categoria_col;


    @ManyToMany(mappedBy = "categorias")
    @JsonIgnore
    private List<Palavra> palavras;

    public Categoria() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoria_col() {
        return categoria_col;
    }

    public void setCategoria_col(String categoria_col) {
        this.categoria_col = categoria_col;
    }

    public List<Palavra> getPalavras() {
        return palavras;
    }

    public void setPalavras(List<Palavra> palavras) {
        this.palavras = palavras;
    }
}
