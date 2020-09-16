package com.forca.jogo_da_forca.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@SuppressWarnings("SpellCheckingInspection")
@Entity(name = "palavras")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Palavra {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private String palavra_col;

    @ManyToOne
    @JoinColumn(name = "dificuldade_id", referencedColumnName = "id")
    private Dificuldade dificuldade;

    @ManyToMany
    @JoinTable(
            name = "palavras_categorias",
            joinColumns = @JoinColumn(name = "palavra_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id", referencedColumnName = "id"))
    private List<Categoria> categorias;

    public Palavra() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPalavra_col() {
        return palavra_col;
    }

    public void setPalavra_col(String palavra_col) {
        this.palavra_col = palavra_col;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

   public Dificuldade getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(Dificuldade dificuldade) {
        this.dificuldade = dificuldade;
    }


}
