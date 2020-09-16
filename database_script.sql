-- MySQL Script generated by MySQL Workbench
-- Wed Sep  9 07:48:53 2020
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema jogo_da_forca
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema jogo_da_forca
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `jogo_da_forca`;
CREATE SCHEMA IF NOT EXISTS `jogo_da_forca` DEFAULT CHARACTER SET utf8 ;
USE `jogo_da_forca` ;

-- -----------------------------------------------------
-- Table `jogo_da_forca`.`dificuldade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jogo_da_forca`.`dificuldades` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `dificuldade_col` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jogo_da_forca`.`palavra`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jogo_da_forca`.`palavras` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `dificuldade_id` INT NOT NULL,
  `palavra_col` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_palavra_dificuldade1_idx` (`dificuldade_id` ASC) VISIBLE,
  CONSTRAINT `fk_palavra_dificuldade1`
    FOREIGN KEY (`dificuldade_id`)
    REFERENCES `jogo_da_forca`.`dificuldades` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jogo_da_forca`.`categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jogo_da_forca`.`categorias` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `categoria_col` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jogo_da_forca`.`palavra_has_categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jogo_da_forca`.`palavras_categorias` (
  `palavra_id` INT NOT NULL,
  `categoria_id` INT NOT NULL,
  PRIMARY KEY (`palavra_id`, `categoria_id`),
  INDEX `fk_palavra_has_categoria_categoria1_idx` (`categoria_id` ASC) VISIBLE,
  INDEX `fk_palavra_has_categoria_palavra_idx` (`palavra_id` ASC) VISIBLE,
  CONSTRAINT `fk_palavra_has_categoria_palavra`
    FOREIGN KEY (`palavra_id`)
    REFERENCES `jogo_da_forca`.`palavras` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_palavra_has_categoria_categoria1`
    FOREIGN KEY (`categoria_id`)
    REFERENCES `jogo_da_forca`.`categorias` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;