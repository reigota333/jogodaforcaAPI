package com.forca.jogo_da_forca.repositories;

import com.forca.jogo_da_forca.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}
