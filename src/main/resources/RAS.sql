SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `ras` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `ras` ;

-- -----------------------------------------------------
-- Table `ras`.`Proveedor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ras`.`Proveedor` ;

CREATE  TABLE IF NOT EXISTS `ras`.`Proveedor` (
  `idProveedor` INT NOT NULL AUTO_INCREMENT ,
  `Nombre` VARCHAR(45) NULL ,
  `Direccion` VARCHAR(45) NULL ,
  `Telefono` VARCHAR(45) NULL ,
  `RUC` VARCHAR(15) NULL ,
  `NombreContacto` VARCHAR(70) NULL ,
  `EmailContacto` VARCHAR(70) NULL ,
  `Vigencia` TINYINT(1) NULL ,
  PRIMARY KEY (`idProveedor`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ras`.`Compra`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ras`.`Compra` ;

CREATE  TABLE IF NOT EXISTS `ras`.`Compra` (
  `idCompra` INT NOT NULL AUTO_INCREMENT ,
  `Fecha` DATE NULL ,
  `Total` DECIMAL(10,2) NULL ,
  `Vigencia` TINYINT(1) NOT NULL ,
  `Proveedor_idProveedor` INT NOT NULL ,
  `Descuento` DECIMAL(10,2) NOT NULL DEFAULT 0 ,
  `NroGuiaRemision` VARCHAR(20) NOT NULL ,
  PRIMARY KEY (`idCompra`) ,
  INDEX `fk_Compra_Proveedor1_idx` (`Proveedor_idProveedor` ASC) ,
  UNIQUE INDEX `NroGuiaRemision_UNIQUE` (`NroGuiaRemision` ASC) ,
  CONSTRAINT `fk_Compra_Proveedor1`
    FOREIGN KEY (`Proveedor_idProveedor` )
    REFERENCES `ras`.`Proveedor` (`idProveedor` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ras`.`Categoria`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ras`.`Categoria` ;

CREATE  TABLE IF NOT EXISTS `ras`.`Categoria` (
  `idCategoria` INT NOT NULL AUTO_INCREMENT ,
  `Nombre` VARCHAR(45) NULL ,
  `Descripcion` VARCHAR(45) NULL ,
  `Vigencia` TINYINT(1) NOT NULL ,
  PRIMARY KEY (`idCategoria`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ras`.`Equipo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ras`.`Equipo` ;

CREATE  TABLE IF NOT EXISTS `ras`.`Equipo` (
  `idEquipo` INT NOT NULL AUTO_INCREMENT ,
  `Codigo` VARCHAR(45) NULL ,
  `Nombre` VARCHAR(45) NULL ,
  `Descripcion` VARCHAR(45) NULL ,
  `Costo` DECIMAL(10,2) NULL ,
  `Stock` INT NULL ,
  `Vigencia` TINYINT(1) NOT NULL ,
  `Categoria_idCategoria` INT NOT NULL ,
  PRIMARY KEY (`idEquipo`) ,
  INDEX `fk_Equipo_Categoria1_idx` (`Categoria_idCategoria` ASC) ,
  CONSTRAINT `fk_Equipo_Categoria1`
    FOREIGN KEY (`Categoria_idCategoria` )
    REFERENCES `ras`.`Categoria` (`idCategoria` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ras`.`Proyecto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ras`.`Proyecto` ;

CREATE  TABLE IF NOT EXISTS `ras`.`Proyecto` (
  `idProyecto` INT NOT NULL AUTO_INCREMENT ,
  `Nombre` VARCHAR(45) NULL ,
  `Descripcion` VARCHAR(145) NULL ,
  `NombreCliente` VARCHAR(70) NULL ,
  `Inicio` DATE NULL ,
  `Fin` DATE NULL ,
  `FinEstimado` DATE NULL ,
  `CostoPersonal` DECIMAL(10,2) NULL ,
  `CostoEquipo` DECIMAL(10,2) NULL ,
  `CostoOtros` DECIMAL(10,2) NULL ,
  `Cierre` TINYINT(1) NULL ,
  `CostoMaterialReal` DECIMAL(10,2) NULL ,
  `CostoPersonalReal` DECIMAL(10,2) NULL ,
  `CostoOtrosReal` DECIMAL(10,2) NULL ,
  `Vigencia` TINYINT(1) NOT NULL ,
  PRIMARY KEY (`idProyecto`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ras`.`Rol`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ras`.`Rol` ;

CREATE  TABLE IF NOT EXISTS `ras`.`Rol` (
  `idRol` INT NOT NULL AUTO_INCREMENT ,
  `Descripcion` VARCHAR(45) NULL ,
  `Vigencia` TINYINT(1) NOT NULL ,
  PRIMARY KEY (`idRol`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ras`.`Usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ras`.`Usuario` ;

CREATE  TABLE IF NOT EXISTS `ras`.`Usuario` (
  `idUsuario` INT NOT NULL AUTO_INCREMENT ,
  `Login` VARCHAR(45) NOT NULL ,
  `Password` VARCHAR(120) NOT NULL ,
  `Vigencia` TINYINT(1) NOT NULL ,
  `Rol_idRol` INT NULL ,
  PRIMARY KEY (`idUsuario`) ,
  INDEX `fk_Usuario_Rol1_idx` (`Rol_idRol` ASC) ,
  CONSTRAINT `fk_Usuario_Rol1`
    FOREIGN KEY (`Rol_idRol` )
    REFERENCES `ras`.`Rol` (`idRol` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ras`.`LogConsulta`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ras`.`LogConsulta` ;

CREATE  TABLE IF NOT EXISTS `ras`.`LogConsulta` (
  `idLogConsulta` INT NOT NULL AUTO_INCREMENT ,
  `FechaConsulta` DATETIME NULL ,
  `Usuario_idUsuario` INT NOT NULL ,
  `Equipo_idEquipo` INT NOT NULL ,
  `Compra_idCompra` INT NOT NULL ,
  `Proyecto_idProyecto` INT NOT NULL ,
  PRIMARY KEY (`idLogConsulta`) ,
  INDEX `fk_LogConsulta_Usuario1_idx` (`Usuario_idUsuario` ASC) ,
  INDEX `fk_LogConsulta_Equipo1_idx` (`Equipo_idEquipo` ASC) ,
  INDEX `fk_LogConsulta_Compra1_idx` (`Compra_idCompra` ASC) ,
  INDEX `fk_LogConsulta_Proyecto1_idx` (`Proyecto_idProyecto` ASC) ,
  CONSTRAINT `fk_LogConsulta_Usuario1`
    FOREIGN KEY (`Usuario_idUsuario` )
    REFERENCES `ras`.`Usuario` (`idUsuario` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_LogConsulta_Equipo1`
    FOREIGN KEY (`Equipo_idEquipo` )
    REFERENCES `ras`.`Equipo` (`idEquipo` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_LogConsulta_Compra1`
    FOREIGN KEY (`Compra_idCompra` )
    REFERENCES `ras`.`Compra` (`idCompra` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_LogConsulta_Proyecto1`
    FOREIGN KEY (`Proyecto_idProyecto` )
    REFERENCES `ras`.`Proyecto` (`idProyecto` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ras`.`DetalleCompra`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ras`.`DetalleCompra` ;

CREATE  TABLE IF NOT EXISTS `ras`.`DetalleCompra` (
  `Compra_idCompra` INT NOT NULL ,
  `Equipo_idEquipo` INT NOT NULL ,
  `Cantidad` INT NULL ,
  `TotalDetalle` DECIMAL(10,2) NULL ,
  PRIMARY KEY (`Compra_idCompra`, `Equipo_idEquipo`) ,
  INDEX `fk_Compra_has_Equipo_Equipo1_idx` (`Equipo_idEquipo` ASC) ,
  INDEX `fk_Compra_has_Equipo_Compra1_idx` (`Compra_idCompra` ASC) ,
  CONSTRAINT `fk_Compra_has_Equipo_Compra1`
    FOREIGN KEY (`Compra_idCompra` )
    REFERENCES `ras`.`Compra` (`idCompra` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Compra_has_Equipo_Equipo1`
    FOREIGN KEY (`Equipo_idEquipo` )
    REFERENCES `ras`.`Equipo` (`idEquipo` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ras`.`EquipoAsignado`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ras`.`EquipoAsignado` ;

CREATE  TABLE IF NOT EXISTS `ras`.`EquipoAsignado` (
  `Equipo_idEquipo` INT NOT NULL ,
  `Proyecto_idProyecto` INT NOT NULL ,
  `Fecha` DATE NULL ,
  `Cantidad` INT NOT NULL ,
  `PrecioUnit` DECIMAL(10,2) NOT NULL ,
  PRIMARY KEY (`Equipo_idEquipo`, `Proyecto_idProyecto`) ,
  INDEX `fk_Equipo_has_Proyecto_Proyecto1_idx` (`Proyecto_idProyecto` ASC) ,
  INDEX `fk_Equipo_has_Proyecto_Equipo1_idx` (`Equipo_idEquipo` ASC) ,
  CONSTRAINT `fk_Equipo_has_Proyecto_Equipo1`
    FOREIGN KEY (`Equipo_idEquipo` )
    REFERENCES `ras`.`Equipo` (`idEquipo` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Equipo_has_Proyecto_Proyecto1`
    FOREIGN KEY (`Proyecto_idProyecto` )
    REFERENCES `ras`.`Proyecto` (`idProyecto` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ras`.`Opcion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ras`.`Opcion` ;

CREATE  TABLE IF NOT EXISTS `ras`.`Opcion` (
  `idOpcion` INT NOT NULL AUTO_INCREMENT ,
  `Descripcion` VARCHAR(45) NULL ,
  `TextoOpcion` VARCHAR(45) NULL ,
  `Ruta` VARCHAR(45) NULL ,
  `Vigencia` TINYINT(1) NOT NULL ,
  `id_menu_padre` INT NULL ,
  PRIMARY KEY (`idOpcion`) ,
  INDEX `fk_Opcion_Opcion1_idx` (`id_menu_padre` ASC) ,
  CONSTRAINT `fk_Opcion_Opcion1`
    FOREIGN KEY (`id_menu_padre` )
    REFERENCES `ras`.`Opcion` (`idOpcion` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ras`.`Personal`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ras`.`Personal` ;

CREATE  TABLE IF NOT EXISTS `ras`.`Personal` (
  `idPersonal` INT NOT NULL AUTO_INCREMENT ,
  `Nombre` VARCHAR(45) NULL ,
  `Profesion` VARCHAR(45) NULL ,
  `Registro` DATE NULL ,
  `Celular` VARCHAR(45) NULL ,
  `Email` VARCHAR(45) NULL ,
  `Vigencia` TINYINT(1) NOT NULL ,
  PRIMARY KEY (`idPersonal`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ras`.`OpcionAsignadas`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ras`.`OpcionAsignadas` ;

CREATE  TABLE IF NOT EXISTS `ras`.`OpcionAsignadas` (
  `Rol_idRol` INT NOT NULL ,
  `Opcion_idOpcion` INT NOT NULL ,
  PRIMARY KEY (`Rol_idRol`, `Opcion_idOpcion`) ,
  INDEX `fk_Rol_has_Opcion_Opcion1_idx` (`Opcion_idOpcion` ASC) ,
  INDEX `fk_Rol_has_Opcion_Rol1_idx` (`Rol_idRol` ASC) ,
  CONSTRAINT `fk_Rol_has_Opcion_Rol1`
    FOREIGN KEY (`Rol_idRol` )
    REFERENCES `ras`.`Rol` (`idRol` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Rol_has_Opcion_Opcion1`
    FOREIGN KEY (`Opcion_idOpcion` )
    REFERENCES `ras`.`Opcion` (`idOpcion` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ras`.`PersonalAsignado`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ras`.`PersonalAsignado` ;

CREATE  TABLE IF NOT EXISTS `ras`.`PersonalAsignado` (
  `Proyecto_idProyecto` INT NOT NULL ,
  `Personal_idPersonal` INT NOT NULL ,
  `Pago` DECIMAL(10,2) NULL ,
  `Tarea` VARCHAR(145) NULL ,
  `Inicio` DATE NULL ,
  `Fin` DATE NULL ,
  PRIMARY KEY (`Proyecto_idProyecto`, `Personal_idPersonal`) ,
  INDEX `fk_Proyecto_has_Personal_Personal1_idx` (`Personal_idPersonal` ASC) ,
  INDEX `fk_Proyecto_has_Personal_Proyecto1_idx` (`Proyecto_idProyecto` ASC) ,
  CONSTRAINT `fk_Proyecto_has_Personal_Proyecto1`
    FOREIGN KEY (`Proyecto_idProyecto` )
    REFERENCES `ras`.`Proyecto` (`idProyecto` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Proyecto_has_Personal_Personal1`
    FOREIGN KEY (`Personal_idPersonal` )
    REFERENCES `ras`.`Personal` (`idPersonal` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ras`.`Partida`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ras`.`Partida` ;

CREATE  TABLE IF NOT EXISTS `ras`.`Partida` (
  `idPartida` INT NOT NULL AUTO_INCREMENT ,
  `Descripcion` VARCHAR(45) NOT NULL ,
  `Vigencia` TINYINT(1) NOT NULL DEFAULT true ,
  PRIMARY KEY (`idPartida`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ras`.`OtroGasto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ras`.`OtroGasto` ;

CREATE  TABLE IF NOT EXISTS `ras`.`OtroGasto` (
  `idOtroGasto` INT NOT NULL AUTO_INCREMENT ,
  `Partida` INT NOT NULL ,
  `Descripcion` VARCHAR(45) NULL ,
  `Monto` DECIMAL(10,2) NULL ,
  `Registro` DATE NULL ,
  `Vigencia` TINYINT(1) NOT NULL ,
  `Proyecto_idProyecto` INT NOT NULL ,
  PRIMARY KEY (`idOtroGasto`) ,
  INDEX `fk_OtroGasto_Proyecto1_idx` (`Proyecto_idProyecto` ASC) ,
  INDEX `fk_OtroGasto_Partida1_idx` (`Partida` ASC) ,
  CONSTRAINT `fk_OtroGasto_Proyecto1`
    FOREIGN KEY (`Proyecto_idProyecto` )
    REFERENCES `ras`.`Proyecto` (`idProyecto` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_OtroGasto_Partida1`
    FOREIGN KEY (`Partida` )
    REFERENCES `ras`.`Partida` (`idPartida` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `ras` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `ras`.`Rol`
-- -----------------------------------------------------
START TRANSACTION;
USE `ras`;
INSERT INTO `ras`.`Rol` (`idRol`, `Descripcion`, `Vigencia`) VALUES (1, 'Administrador', 1);
INSERT INTO `ras`.`Rol` (`idRol`, `Descripcion`, `Vigencia`) VALUES (2, 'Worker1', 1);
INSERT INTO `ras`.`Rol` (`idRol`, `Descripcion`, `Vigencia`) VALUES (3, 'Worker2', 1);

COMMIT;

-- -----------------------------------------------------
-- Data for table `ras`.`Usuario`
-- -----------------------------------------------------
START TRANSACTION;
USE `ras`;
INSERT INTO `ras`.`Usuario` (`idUsuario`, `Login`, `Password`, `Vigencia`, `Rol_idRol`) VALUES (1, 'admin', '$2a$10$WI6zWni/y5ZnO5.y1HPHX.Z3shzTBXrYcjMEcwc4ybwwZpbYlIzwe', 1, 1);
INSERT INTO `ras`.`Usuario` (`idUsuario`, `Login`, `Password`, `Vigencia`, `Rol_idRol`) VALUES (2, 'dtowong', '$2a$10$7cUXKhvBU0yMwXyNgbCt6.qdble0I0c.NdFQAb2io2KndRw4MLFJa', 1, 2);
INSERT INTO `ras`.`Usuario` (`idUsuario`, `Login`, `Password`, `Vigencia`, `Rol_idRol`) VALUES (3, 'germancruz', '$2a$10$wM7brphrbKqY7Zf0NHfvIejsC4/3d8wlg..XMH5sDkHyuB3v6RDZi', 1, 3);

COMMIT;

-- -----------------------------------------------------
-- Data for table `ras`.`Opcion`
-- -----------------------------------------------------
START TRANSACTION;
USE `ras`;
INSERT INTO `ras`.`Opcion` (`idOpcion`, `Descripcion`, `TextoOpcion`, `Ruta`, `Vigencia`, `id_menu_padre`) VALUES (1, 'Compras', 'Compras', 'NULL', 1, NULL);
INSERT INTO `ras`.`Opcion` (`idOpcion`, `Descripcion`, `TextoOpcion`, `Ruta`, `Vigencia`, `id_menu_padre`) VALUES (4, 'Inventario', 'Inventario', 'NULL', 1, NULL);
INSERT INTO `ras`.`Opcion` (`idOpcion`, `Descripcion`, `TextoOpcion`, `Ruta`, `Vigencia`, `id_menu_padre`) VALUES (5, 'Personal', 'Personal', 'NULL', 1, NULL);
INSERT INTO `ras`.`Opcion` (`idOpcion`, `Descripcion`, `TextoOpcion`, `Ruta`, `Vigencia`, `id_menu_padre`) VALUES (2, 'Proyecto', 'Proyecto', 'NULL', 1, NULL);
INSERT INTO `ras`.`Opcion` (`idOpcion`, `Descripcion`, `TextoOpcion`, `Ruta`, `Vigencia`, `id_menu_padre`) VALUES (3, 'security', 'Seguridad', 'NULL', 1, NULL);
INSERT INTO `ras`.`Opcion` (`idOpcion`, `Descripcion`, `TextoOpcion`, `Ruta`, `Vigencia`, `id_menu_padre`) VALUES (7, 'mantCompras', 'Detalle', 'pages/Compras/mantCompras.xhtml', 1, 1);
INSERT INTO `ras`.`Opcion` (`idOpcion`, `Descripcion`, `TextoOpcion`, `Ruta`, `Vigencia`, `id_menu_padre`) VALUES (8, 'mantProveedor', 'Proveedor', 'pages/Compras/mantProveedor.xhtml', 1, 1);
INSERT INTO `ras`.`Opcion` (`idOpcion`, `Descripcion`, `TextoOpcion`, `Ruta`, `Vigencia`, `id_menu_padre`) VALUES (9, 'mantCategoria', 'Categoria', 'pages/Inventario/mantCategoria.xhtml', 1, 4);
INSERT INTO `ras`.`Opcion` (`idOpcion`, `Descripcion`, `TextoOpcion`, `Ruta`, `Vigencia`, `id_menu_padre`) VALUES (10, 'mantEquipo', 'Detalle Equipo', 'pages/Inventario/mantEquipo.xhtml', 1, 4);
INSERT INTO `ras`.`Opcion` (`idOpcion`, `Descripcion`, `TextoOpcion`, `Ruta`, `Vigencia`, `id_menu_padre`) VALUES (11, 'mantPersonal', 'Detalle', 'pages/Personal/mantPersonal.xhtml', 1, 5);
INSERT INTO `ras`.`Opcion` (`idOpcion`, `Descripcion`, `TextoOpcion`, `Ruta`, `Vigencia`, `id_menu_padre`) VALUES (13, 'mantProyecto', 'Gestion', 'pages/Proyecto/mantProyecto.xhtml', 1, 2);
INSERT INTO `ras`.`Opcion` (`idOpcion`, `Descripcion`, `TextoOpcion`, `Ruta`, `Vigencia`, `id_menu_padre`) VALUES (14, 'mantRoles', 'Gestion de Roles', 'pages/security/mantRoles.xhtml', 1, 3);
INSERT INTO `ras`.`Opcion` (`idOpcion`, `Descripcion`, `TextoOpcion`, `Ruta`, `Vigencia`, `id_menu_padre`) VALUES (15, 'Usuario', 'Usuario', 'pages/security/Usuario.xhtml', 1, 3);
INSERT INTO `ras`.`Opcion` (`idOpcion`, `Descripcion`, `TextoOpcion`, `Ruta`, `Vigencia`, `id_menu_padre`) VALUES (16, 'mantPartida', 'Partida', 'pages/Proyecto/mantPartida.xhtml', 1, 2);

COMMIT;

-- -----------------------------------------------------
-- Data for table `ras`.`OpcionAsignadas`
-- -----------------------------------------------------
START TRANSACTION;
USE `ras`;
INSERT INTO `ras`.`OpcionAsignadas` (`Rol_idRol`, `Opcion_idOpcion`) VALUES (1, 1);
INSERT INTO `ras`.`OpcionAsignadas` (`Rol_idRol`, `Opcion_idOpcion`) VALUES (1, 2);
INSERT INTO `ras`.`OpcionAsignadas` (`Rol_idRol`, `Opcion_idOpcion`) VALUES (3, 2);
INSERT INTO `ras`.`OpcionAsignadas` (`Rol_idRol`, `Opcion_idOpcion`) VALUES (1, 3);
INSERT INTO `ras`.`OpcionAsignadas` (`Rol_idRol`, `Opcion_idOpcion`) VALUES (1, 4);
INSERT INTO `ras`.`OpcionAsignadas` (`Rol_idRol`, `Opcion_idOpcion`) VALUES (1, 5);
INSERT INTO `ras`.`OpcionAsignadas` (`Rol_idRol`, `Opcion_idOpcion`) VALUES (2, 7);
INSERT INTO `ras`.`OpcionAsignadas` (`Rol_idRol`, `Opcion_idOpcion`) VALUES (2, 11);

COMMIT;
