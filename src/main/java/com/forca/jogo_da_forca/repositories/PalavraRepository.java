package com.forca.jogo_da_forca.repositories;

import com.forca.jogo_da_forca.models.Palavra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PalavraRepository extends JpaRepository<Palavra, Integer> {
    @Query(value = "SELECT p.id, p.dificuldade_id, p.palavra_col FROM jogo_da_forca.palavras p LEFT JOIN jogo_da_forca.palavras_categorias c ON p.id = c.palavra_id ORDER BY rand(CURRENT_TIMESTAMP()) LIMIT 1", nativeQuery = true)
    public Palavra getRandom();

    @Query(value = "SELECT p.id, p.dificuldade_id, p.palavra_col FROM jogo_da_forca.palavras p LEFT JOIN jogo_da_forca.palavras_categorias c ON p.id = c.palavra_id WHERE c.categoria_id = ?1 AND p.dificuldade_id = ?2 ORDER BY rand(CURRENT_TIMESTAMP()) LIMIT 1", nativeQuery = true)
    public Palavra getPalavra(Integer cat, Integer dif);

    @Query(value = "SELECT p.id, p.dificuldade_id, p.palavra_col FROM jogo_da_forca.palavras p LEFT JOIN jogo_da_forca.palavras_categorias c ON p.id = c.palavra_id WHERE p.dificuldade_id = ?1 ORDER BY rand(CURRENT_TIMESTAMP()) LIMIT 1", nativeQuery = true)
    public Palavra getDifPalavra(Integer dif);

    @Query(value = "SELECT p.id, p.dificuldade_id, p.palavra_col FROM jogo_da_forca.palavras p LEFT JOIN jogo_da_forca.palavras_categorias c ON p.id = c.palavra_id WHERE c.categoria_id = ?1 ORDER BY rand(CURRENT_TIMESTAMP()) LIMIT 1", nativeQuery = true)
    public Palavra getCatPalavra(Integer cat);
}
