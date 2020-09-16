package com.forca.jogo_da_forca.repositories;

import com.forca.jogo_da_forca.models.Dificuldade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DificuldadeRepository extends JpaRepository<Dificuldade, Integer> {
}
