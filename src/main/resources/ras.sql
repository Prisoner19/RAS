SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `ras` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `ras` ;

-- -----------------------------------------------------
-- Table `ras`.`Distrito`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ras`.`Distrito` ;

CREATE TABLE IF NOT EXISTS `ras`.`Distrito` (
  `idDistrito` INT NOT NULL AUTO_INCREMENT,
  `Distrito` VARCHAR(45) NULL,
  `Vigencia` TINYINT(1) NOT NULL,
  PRIMARY KEY (`idDistrito`))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ras`.`Proveedor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ras`.`Proveedor` ;

CREATE TABLE IF NOT EXISTS `ras`.`Proveedor` (
  `idProveedor` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(45) NULL,
  `Direccion` VARCHAR(45) NULL,
  `Telefono` VARCHAR(45) NULL,
  `Vigencia` TINYINT(1) NULL,
  `Distrito_idDistrito` INT NOT NULL,
  PRIMARY KEY (`idProveedor`),
  INDEX `fk_Proveedor_Distrito1_idx` (`Distrito_idDistrito` ASC),
  CONSTRAINT `fk_Proveedor_Distrito1`
  FOREIGN KEY (`Distrito_idDistrito`)
  REFERENCES `ras`.`Distrito` (`idDistrito`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ras`.`Compra`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ras`.`Compra` ;

CREATE TABLE IF NOT EXISTS `ras`.`Compra` (
  `idCompra` INT NOT NULL AUTO_INCREMENT,
  `Fecha` DATE NULL,
  `Total` DECIMAL(10,2) NULL,
  `Vigencia` TINYINT(1) NOT NULL,
  `Proveedor_idProveedor` INT NOT NULL,
  PRIMARY KEY (`idCompra`),
  INDEX `fk_Compra_Proveedor1_idx` (`Proveedor_idProveedor` ASC),
  CONSTRAINT `fk_Compra_Proveedor1`
  FOREIGN KEY (`Proveedor_idProveedor`)
  REFERENCES `ras`.`Proveedor` (`idProveedor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ras`.`Categoria`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ras`.`Categoria` ;

CREATE TABLE IF NOT EXISTS `ras`.`Categoria` (
  `idCategoria` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(45) NULL,
  `Descripcion` VARCHAR(45) NULL,
  `Vigencia` TINYINT(1) NOT NULL,
  PRIMARY KEY (`idCategoria`))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ras`.`Equipo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ras`.`Equipo` ;

CREATE TABLE IF NOT EXISTS `ras`.`Equipo` (
  `idEquipo` INT NOT NULL AUTO_INCREMENT,
  `Codigo` VARCHAR(45) NULL,
  `Nombre` VARCHAR(45) NULL,
  `Descripcion` VARCHAR(45) NULL,
  `Costo` DECIMAL(10,2) NULL,
  `Stock` INT NULL,
  `Vigencia` TINYINT(1) NOT NULL,
  `Categoria_idCategoria` INT NOT NULL,
  PRIMARY KEY (`idEquipo`),
  INDEX `fk_Equipo_Categoria1_idx` (`Categoria_idCategoria` ASC),
  CONSTRAINT `fk_Equipo_Categoria1`
  FOREIGN KEY (`Categoria_idCategoria`)
  REFERENCES `ras`.`Categoria` (`idCategoria`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ras`.`Proyecto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ras`.`Proyecto` ;

CREATE TABLE IF NOT EXISTS `ras`.`Proyecto` (
  `idProyecto` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(45) NULL,
  `Descripcion` VARCHAR(145) NULL,
  `Inicio` DATE NULL,
  `Fin` DATE NULL,
  `CostoPersonal` DECIMAL(10,2) NULL,
  `CostoEquipo` DECIMAL(10,2) NULL,
  `CostoOtros` DECIMAL(10,2) NULL,
  `Cierre` TINYINT(1) NULL,
  `CostoMaterialReal` DECIMAL(10,2) NULL,
  `CostoPersonalReal` DECIMAL(10,2) NULL,
  `CostoOtrosReal` DECIMAL(10,2) NULL,
  `Vigencia` TINYINT(1) NOT NULL,
  PRIMARY KEY (`idProyecto`))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ras`.`Rol`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ras`.`Rol` ;

CREATE TABLE IF NOT EXISTS `ras`.`Rol` (
  `idRol` INT NOT NULL AUTO_INCREMENT,
  `Descripcion` VARCHAR(45) NULL,
  `Vigencia` TINYINT(1) NOT NULL,
  PRIMARY KEY (`idRol`))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ras`.`Usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ras`.`Usuario` ;

CREATE TABLE IF NOT EXISTS `ras`.`Usuario` (
  `idUsuario` INT NOT NULL AUTO_INCREMENT,
  `Login` VARCHAR(45) NOT NULL,
  `Password` VARCHAR(45) NOT NULL,
  `Vigencia` TINYINT(1) NOT NULL,
  `Rol_idRol` INT NULL,
  PRIMARY KEY (`idUsuario`),
  INDEX `fk_Usuario_Rol1_idx` (`Rol_idRol` ASC),
  CONSTRAINT `fk_Usuario_Rol1`
  FOREIGN KEY (`Rol_idRol`)
  REFERENCES `ras`.`Rol` (`idRol`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ras`.`LogConsulta`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ras`.`LogConsulta` ;

CREATE TABLE IF NOT EXISTS `ras`.`LogConsulta` (
  `idLogConsulta` INT NOT NULL AUTO_INCREMENT,
  `FechaConsulta` DATETIME NULL,
  `Usuario_idUsuario` INT NOT NULL,
  `Equipo_idEquipo` INT NOT NULL,
  `Compra_idCompra` INT NOT NULL,
  `Proyecto_idProyecto` INT NOT NULL,
  PRIMARY KEY (`idLogConsulta`),
  INDEX `fk_LogConsulta_Usuario1_idx` (`Usuario_idUsuario` ASC),
  INDEX `fk_LogConsulta_Equipo1_idx` (`Equipo_idEquipo` ASC),
  INDEX `fk_LogConsulta_Compra1_idx` (`Compra_idCompra` ASC),
  INDEX `fk_LogConsulta_Proyecto1_idx` (`Proyecto_idProyecto` ASC),
  CONSTRAINT `fk_LogConsulta_Usuario1`
  FOREIGN KEY (`Usuario_idUsuario`)
  REFERENCES `ras`.`Usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_LogConsulta_Equipo1`
  FOREIGN KEY (`Equipo_idEquipo`)
  REFERENCES `ras`.`Equipo` (`idEquipo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_LogConsulta_Compra1`
  FOREIGN KEY (`Compra_idCompra`)
  REFERENCES `ras`.`Compra` (`idCompra`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_LogConsulta_Proyecto1`
  FOREIGN KEY (`Proyecto_idProyecto`)
  REFERENCES `ras`.`Proyecto` (`idProyecto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ras`.`DetalleCompra`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ras`.`DetalleCompra` ;

CREATE TABLE IF NOT EXISTS `ras`.`DetalleCompra` (
  `Compra_idCompra` INT NOT NULL,
  `Equipo_idEquipo` INT NOT NULL,
  `Cantidad` INT NULL,
  `TotalDetalle` DECIMAL(10,2) NULL,
  PRIMARY KEY (`Compra_idCompra`, `Equipo_idEquipo`),
  INDEX `fk_Compra_has_Equipo_Equipo1_idx` (`Equipo_idEquipo` ASC),
  INDEX `fk_Compra_has_Equipo_Compra1_idx` (`Compra_idCompra` ASC),
  CONSTRAINT `fk_Compra_has_Equipo_Compra1`
  FOREIGN KEY (`Compra_idCompra`)
  REFERENCES `ras`.`Compra` (`idCompra`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Compra_has_Equipo_Equipo1`
  FOREIGN KEY (`Equipo_idEquipo`)
  REFERENCES `ras`.`Equipo` (`idEquipo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ras`.`EquipoAsignado`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ras`.`EquipoAsignado` ;

CREATE TABLE IF NOT EXISTS `ras`.`EquipoAsignado` (
  `Equipo_idEquipo` INT NOT NULL,
  `Proyecto_idProyecto` INT NOT NULL,
  PRIMARY KEY (`Equipo_idEquipo`, `Proyecto_idProyecto`),
  INDEX `fk_Equipo_has_Proyecto_Proyecto1_idx` (`Proyecto_idProyecto` ASC),
  INDEX `fk_Equipo_has_Proyecto_Equipo1_idx` (`Equipo_idEquipo` ASC),
  CONSTRAINT `fk_Equipo_has_Proyecto_Equipo1`
  FOREIGN KEY (`Equipo_idEquipo`)
  REFERENCES `ras`.`Equipo` (`idEquipo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Equipo_has_Proyecto_Proyecto1`
  FOREIGN KEY (`Proyecto_idProyecto`)
  REFERENCES `ras`.`Proyecto` (`idProyecto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ras`.`Opcion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ras`.`Opcion` ;

CREATE TABLE IF NOT EXISTS `ras`.`Opcion` (
  `idOpcion` INT NOT NULL AUTO_INCREMENT,
  `Descripcion` VARCHAR(45) NULL,
  `Ruta` VARCHAR(45) NULL,
  `Vigencia` TINYINT(1) NOT NULL,
  `id_menu_padre` INT NULL,
  PRIMARY KEY (`idOpcion`),
  INDEX `fk_Opcion_Opcion1_idx` (`id_menu_padre` ASC),
  CONSTRAINT `fk_Opcion_Opcion1`
  FOREIGN KEY (`id_menu_padre`)
  REFERENCES `ras`.`Opcion` (`idOpcion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ras`.`Personal`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ras`.`Personal` ;

CREATE TABLE IF NOT EXISTS `ras`.`Personal` (
  `idPersonal` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(45) NULL,
  `Profesion` VARCHAR(45) NULL,
  `Registro` DATE NULL,
  `Celular` VARCHAR(45) NULL,
  `Email` VARCHAR(45) NULL,
  `Vigencia` TINYINT(1) NOT NULL,
  PRIMARY KEY (`idPersonal`))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ras`.`OpcionAsignadas`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ras`.`OpcionAsignadas` ;

CREATE TABLE IF NOT EXISTS `ras`.`OpcionAsignadas` (
  `Rol_idRol` INT NOT NULL,
  `Opcion_idOpcion` INT NOT NULL,
  PRIMARY KEY (`Rol_idRol`, `Opcion_idOpcion`),
  INDEX `fk_Rol_has_Opcion_Opcion1_idx` (`Opcion_idOpcion` ASC),
  INDEX `fk_Rol_has_Opcion_Rol1_idx` (`Rol_idRol` ASC),
  CONSTRAINT `fk_Rol_has_Opcion_Rol1`
  FOREIGN KEY (`Rol_idRol`)
  REFERENCES `ras`.`Rol` (`idRol`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Rol_has_Opcion_Opcion1`
  FOREIGN KEY (`Opcion_idOpcion`)
  REFERENCES `ras`.`Opcion` (`idOpcion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ras`.`PersonalAsignado`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ras`.`PersonalAsignado` ;

CREATE TABLE IF NOT EXISTS `ras`.`PersonalAsignado` (
  `Proyecto_idProyecto` INT NOT NULL,
  `Personal_idPersonal` INT NOT NULL,
  `Pago` DECIMAL(10,2) NULL,
  `Tarea` VARCHAR(145) NULL,
  `Inicio` DATE NULL,
  `Fin` DATE NULL,
  PRIMARY KEY (`Proyecto_idProyecto`, `Personal_idPersonal`),
  INDEX `fk_Proyecto_has_Personal_Personal1_idx` (`Personal_idPersonal` ASC),
  INDEX `fk_Proyecto_has_Personal_Proyecto1_idx` (`Proyecto_idProyecto` ASC),
  CONSTRAINT `fk_Proyecto_has_Personal_Proyecto1`
  FOREIGN KEY (`Proyecto_idProyecto`)
  REFERENCES `ras`.`Proyecto` (`idProyecto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Proyecto_has_Personal_Personal1`
  FOREIGN KEY (`Personal_idPersonal`)
  REFERENCES `ras`.`Personal` (`idPersonal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ras`.`OtroGasto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ras`.`OtroGasto` ;

CREATE TABLE IF NOT EXISTS `ras`.`OtroGasto` (
  `idOtroGasto` INT NOT NULL AUTO_INCREMENT,
  `Partida` VARCHAR(45) NULL,
  `Descripcion` VARCHAR(45) NULL,
  `Monto` DECIMAL(10,2) NULL,
  `Registro` DATE NULL,
  `Vigencia` TINYINT(1) NOT NULL,
  `Proyecto_idProyecto` INT NOT NULL,
  PRIMARY KEY (`idOtroGasto`),
  INDEX `fk_OtroGasto_Proyecto1_idx` (`Proyecto_idProyecto` ASC),
  CONSTRAINT `fk_OtroGasto_Proyecto1`
  FOREIGN KEY (`Proyecto_idProyecto`)
  REFERENCES `ras`.`Proyecto` (`idProyecto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

