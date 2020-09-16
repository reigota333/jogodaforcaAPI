package com.forca.jogo_da_forca.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;

@SuppressWarnings("SpellCheckingInspection")
@Entity(name = "dificuldades")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Dificuldade {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private String dificuldade_col;

    @OneToMany(mappedBy = "dificuldade")
    @JsonIgnore
    private Set<Palavra> palavras;

    public Dificuldade() {

    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDificuldade_col() {
        return dificuldade_col;
    }

    public void setDificuldade_col(String dificuldade_col) {
        this.dificuldade_col = dificuldade_col;
    }

    public Set<Palavra> getPalavras() {
        return palavras;
    }

    public void setPalavras(Set<Palavra> palavras) {
        this.palavras = palavras;
    }
}
