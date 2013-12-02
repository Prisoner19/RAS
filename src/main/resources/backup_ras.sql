CREATE DATABASE  IF NOT EXISTS `ras` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ras`;
-- MySQL dump 10.13  Distrib 5.5.34, for debian-linux-gnu (x86_64)
--
-- Host: 127.0.0.1    Database: ras
-- ------------------------------------------------------
-- Server version	5.5.34-0ubuntu0.13.10.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Categoria`
--

DROP TABLE IF EXISTS `Categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Categoria` (
  `idCategoria` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) DEFAULT NULL,
  `Descripcion` varchar(45) DEFAULT NULL,
  `Vigencia` tinyint(1) NOT NULL,
  PRIMARY KEY (`idCategoria`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Categoria`
--

LOCK TABLES `Categoria` WRITE;
/*!40000 ALTER TABLE `Categoria` DISABLE KEYS */;
/*!40000 ALTER TABLE `Categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Compra`
--

DROP TABLE IF EXISTS `Compra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Compra` (
  `idCompra` int(11) NOT NULL AUTO_INCREMENT,
  `Fecha` date DEFAULT NULL,
  `Total` decimal(10,2) DEFAULT NULL,
  `Vigencia` tinyint(1) NOT NULL,
  `Proveedor_idProveedor` int(11) NOT NULL,
  PRIMARY KEY (`idCompra`),
  KEY `fk_Compra_Proveedor1_idx` (`Proveedor_idProveedor`),
  CONSTRAINT `fk_Compra_Proveedor1` FOREIGN KEY (`Proveedor_idProveedor`) REFERENCES `Proveedor` (`idProveedor`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Compra`
--

LOCK TABLES `Compra` WRITE;
/*!40000 ALTER TABLE `Compra` DISABLE KEYS */;
/*!40000 ALTER TABLE `Compra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DetalleCompra`
--

DROP TABLE IF EXISTS `DetalleCompra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DetalleCompra` (
  `Compra_idCompra` int(11) NOT NULL,
  `Equipo_idEquipo` int(11) NOT NULL,
  `Cantidad` int(11) DEFAULT NULL,
  `TotalDetalle` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`Compra_idCompra`,`Equipo_idEquipo`),
  KEY `fk_Compra_has_Equipo_Equipo1_idx` (`Equipo_idEquipo`),
  KEY `fk_Compra_has_Equipo_Compra1_idx` (`Compra_idCompra`),
  CONSTRAINT `fk_Compra_has_Equipo_Compra1` FOREIGN KEY (`Compra_idCompra`) REFERENCES `Compra` (`idCompra`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Compra_has_Equipo_Equipo1` FOREIGN KEY (`Equipo_idEquipo`) REFERENCES `Equipo` (`idEquipo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DetalleCompra`
--

LOCK TABLES `DetalleCompra` WRITE;
/*!40000 ALTER TABLE `DetalleCompra` DISABLE KEYS */;
/*!40000 ALTER TABLE `DetalleCompra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Distrito`
--

DROP TABLE IF EXISTS `Distrito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Distrito` (
  `idDistrito` int(11) NOT NULL AUTO_INCREMENT,
  `Distrito` varchar(45) DEFAULT NULL,
  `Vigencia` tinyint(1) NOT NULL,
  PRIMARY KEY (`idDistrito`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Distrito`
--

LOCK TABLES `Distrito` WRITE;
/*!40000 ALTER TABLE `Distrito` DISABLE KEYS */;
/*!40000 ALTER TABLE `Distrito` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Equipo`
--

DROP TABLE IF EXISTS `Equipo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Equipo` (
  `idEquipo` int(11) NOT NULL AUTO_INCREMENT,
  `Codigo` varchar(45) DEFAULT NULL,
  `Nombre` varchar(45) DEFAULT NULL,
  `Descripcion` varchar(45) DEFAULT NULL,
  `Costo` decimal(10,2) DEFAULT NULL,
  `Stock` int(11) DEFAULT NULL,
  `Vigencia` tinyint(1) NOT NULL,
  `Categoria_idCategoria` int(11) NOT NULL,
  PRIMARY KEY (`idEquipo`),
  KEY `fk_Equipo_Categoria1_idx` (`Categoria_idCategoria`),
  CONSTRAINT `fk_Equipo_Categoria1` FOREIGN KEY (`Categoria_idCategoria`) REFERENCES `Categoria` (`idCategoria`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Equipo`
--

LOCK TABLES `Equipo` WRITE;
/*!40000 ALTER TABLE `Equipo` DISABLE KEYS */;
/*!40000 ALTER TABLE `Equipo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `EquipoAsignado`
--

DROP TABLE IF EXISTS `EquipoAsignado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EquipoAsignado` (
  `Equipo_idEquipo` int(11) NOT NULL,
  `Proyecto_idProyecto` int(11) NOT NULL,
  PRIMARY KEY (`Equipo_idEquipo`,`Proyecto_idProyecto`),
  KEY `fk_Equipo_has_Proyecto_Proyecto1_idx` (`Proyecto_idProyecto`),
  KEY `fk_Equipo_has_Proyecto_Equipo1_idx` (`Equipo_idEquipo`),
  CONSTRAINT `fk_Equipo_has_Proyecto_Equipo1` FOREIGN KEY (`Equipo_idEquipo`) REFERENCES `Equipo` (`idEquipo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Equipo_has_Proyecto_Proyecto1` FOREIGN KEY (`Proyecto_idProyecto`) REFERENCES `Proyecto` (`idProyecto`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EquipoAsignado`
--

LOCK TABLES `EquipoAsignado` WRITE;
/*!40000 ALTER TABLE `EquipoAsignado` DISABLE KEYS */;
/*!40000 ALTER TABLE `EquipoAsignado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `LogConsulta`
--

DROP TABLE IF EXISTS `LogConsulta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `LogConsulta` (
  `idLogConsulta` int(11) NOT NULL AUTO_INCREMENT,
  `FechaConsulta` datetime DEFAULT NULL,
  `Usuario_idUsuario` int(11) NOT NULL,
  `Equipo_idEquipo` int(11) NOT NULL,
  `Compra_idCompra` int(11) NOT NULL,
  `Proyecto_idProyecto` int(11) NOT NULL,
  PRIMARY KEY (`idLogConsulta`),
  KEY `fk_LogConsulta_Usuario1_idx` (`Usuario_idUsuario`),
  KEY `fk_LogConsulta_Equipo1_idx` (`Equipo_idEquipo`),
  KEY `fk_LogConsulta_Compra1_idx` (`Compra_idCompra`),
  KEY `fk_LogConsulta_Proyecto1_idx` (`Proyecto_idProyecto`),
  CONSTRAINT `fk_LogConsulta_Usuario1` FOREIGN KEY (`Usuario_idUsuario`) REFERENCES `Usuario` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_LogConsulta_Equipo1` FOREIGN KEY (`Equipo_idEquipo`) REFERENCES `Equipo` (`idEquipo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_LogConsulta_Compra1` FOREIGN KEY (`Compra_idCompra`) REFERENCES `Compra` (`idCompra`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_LogConsulta_Proyecto1` FOREIGN KEY (`Proyecto_idProyecto`) REFERENCES `Proyecto` (`idProyecto`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `LogConsulta`
--

LOCK TABLES `LogConsulta` WRITE;
/*!40000 ALTER TABLE `LogConsulta` DISABLE KEYS */;
/*!40000 ALTER TABLE `LogConsulta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Opcion`
--

DROP TABLE IF EXISTS `Opcion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Opcion` (
  `idOpcion` int(11) NOT NULL AUTO_INCREMENT,
  `Descripcion` varchar(45) DEFAULT NULL,
  `TextoOpcion` varchar(45) DEFAULT NULL,
  `Ruta` varchar(45) DEFAULT NULL,
  `Vigencia` tinyint(1) NOT NULL,
  `id_menu_padre` int(11) DEFAULT NULL,
  PRIMARY KEY (`idOpcion`),
  KEY `fk_Opcion_Opcion1_idx` (`id_menu_padre`),
  CONSTRAINT `fk_Opcion_Opcion1` FOREIGN KEY (`id_menu_padre`) REFERENCES `Opcion` (`idOpcion`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Opcion`
--

LOCK TABLES `Opcion` WRITE;
/*!40000 ALTER TABLE `Opcion` DISABLE KEYS */;
INSERT INTO `Opcion` VALUES (1,'Compras','Compras',NULL,1,NULL),(2,'Inventario','Inventario',NULL,1,NULL),(3,'Personal','Personal',NULL,1,NULL),(4,'Proyecto','Proyecto',NULL,1,NULL),(5,'security','Seguridad',NULL,1,NULL),(6,'historialCompras','Historico','pages/Compras/historialCompras.xhtml',1,1),(7,'mantCompras','Detalle','pages/Compras/mantCompras.xhtml',1,1),(8,'mantProveedor','Proveedor','pages/Compras/mantProveedor.xhtml',1,1),(9,'mantCategoria','Categoria','pages/Inventario/mantCategoria.xhtml',1,2),(10,'mantEquipo','Detalle Equipo','pages/Inventario/mantEquipo.xhtml',1,2),(11,'mantPersonal','Detalle','pages/Personal/mantPersonal.xhtml',1,3),(12,'detalleProyecto','Detalle','pages/Proyecto/detalleProyecto.xhtml',1,4),(13,'mantProyecto','Gestion','pages/Proyecto/mantProyecto.xhtml',1,4),(14,'mantRoles','Gestion de Roles','pages/security/mantRoles.xhtml',1,5),(15,'Usuario','Usuario','pages/security/Usuario.xhtml',1,5);
/*!40000 ALTER TABLE `Opcion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `OpcionAsignadas`
--

DROP TABLE IF EXISTS `OpcionAsignadas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `OpcionAsignadas` (
  `Rol_idRol` int(11) NOT NULL,
  `Opcion_idOpcion` int(11) NOT NULL,
  PRIMARY KEY (`Rol_idRol`,`Opcion_idOpcion`),
  KEY `fk_Rol_has_Opcion_Opcion1_idx` (`Opcion_idOpcion`),
  KEY `fk_Rol_has_Opcion_Rol1_idx` (`Rol_idRol`),
  CONSTRAINT `fk_Rol_has_Opcion_Rol1` FOREIGN KEY (`Rol_idRol`) REFERENCES `Rol` (`idRol`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Rol_has_Opcion_Opcion1` FOREIGN KEY (`Opcion_idOpcion`) REFERENCES `Opcion` (`idOpcion`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `OpcionAsignadas`
--

LOCK TABLES `OpcionAsignadas` WRITE;
/*!40000 ALTER TABLE `OpcionAsignadas` DISABLE KEYS */;
INSERT INTO `OpcionAsignadas` VALUES (1,1),(1,2),(1,3),(1,4),(1,5);
/*!40000 ALTER TABLE `OpcionAsignadas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `OtroGasto`
--

DROP TABLE IF EXISTS `OtroGasto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `OtroGasto` (
  `idOtroGasto` int(11) NOT NULL AUTO_INCREMENT,
  `Partida` varchar(45) DEFAULT NULL,
  `Descripcion` varchar(45) DEFAULT NULL,
  `Monto` decimal(10,2) DEFAULT NULL,
  `Registro` date DEFAULT NULL,
  `Vigencia` tinyint(1) NOT NULL,
  `Proyecto_idProyecto` int(11) NOT NULL,
  PRIMARY KEY (`idOtroGasto`),
  KEY `fk_OtroGasto_Proyecto1_idx` (`Proyecto_idProyecto`),
  CONSTRAINT `fk_OtroGasto_Proyecto1` FOREIGN KEY (`Proyecto_idProyecto`) REFERENCES `Proyecto` (`idProyecto`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `OtroGasto`
--

LOCK TABLES `OtroGasto` WRITE;
/*!40000 ALTER TABLE `OtroGasto` DISABLE KEYS */;
/*!40000 ALTER TABLE `OtroGasto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Personal`
--

DROP TABLE IF EXISTS `Personal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Personal` (
  `idPersonal` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) DEFAULT NULL,
  `Profesion` varchar(45) DEFAULT NULL,
  `Registro` date DEFAULT NULL,
  `Celular` varchar(45) DEFAULT NULL,
  `Email` varchar(45) DEFAULT NULL,
  `Vigencia` tinyint(1) NOT NULL,
  PRIMARY KEY (`idPersonal`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Personal`
--

LOCK TABLES `Personal` WRITE;
/*!40000 ALTER TABLE `Personal` DISABLE KEYS */;
/*!40000 ALTER TABLE `Personal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PersonalAsignado`
--

DROP TABLE IF EXISTS `PersonalAsignado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PersonalAsignado` (
  `Proyecto_idProyecto` int(11) NOT NULL,
  `Personal_idPersonal` int(11) NOT NULL,
  `Pago` decimal(10,2) DEFAULT NULL,
  `Tarea` varchar(145) DEFAULT NULL,
  `Inicio` date DEFAULT NULL,
  `Fin` date DEFAULT NULL,
  PRIMARY KEY (`Proyecto_idProyecto`,`Personal_idPersonal`),
  KEY `fk_Proyecto_has_Personal_Personal1_idx` (`Personal_idPersonal`),
  KEY `fk_Proyecto_has_Personal_Proyecto1_idx` (`Proyecto_idProyecto`),
  CONSTRAINT `fk_Proyecto_has_Personal_Proyecto1` FOREIGN KEY (`Proyecto_idProyecto`) REFERENCES `Proyecto` (`idProyecto`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Proyecto_has_Personal_Personal1` FOREIGN KEY (`Personal_idPersonal`) REFERENCES `Personal` (`idPersonal`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PersonalAsignado`
--

LOCK TABLES `PersonalAsignado` WRITE;
/*!40000 ALTER TABLE `PersonalAsignado` DISABLE KEYS */;
/*!40000 ALTER TABLE `PersonalAsignado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Proveedor`
--

DROP TABLE IF EXISTS `Proveedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Proveedor` (
  `idProveedor` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) DEFAULT NULL,
  `Direccion` varchar(45) DEFAULT NULL,
  `Telefono` varchar(45) DEFAULT NULL,
  `Vigencia` tinyint(1) DEFAULT NULL,
  `Distrito_idDistrito` int(11) NOT NULL,
  PRIMARY KEY (`idProveedor`),
  KEY `fk_Proveedor_Distrito1_idx` (`Distrito_idDistrito`),
  CONSTRAINT `fk_Proveedor_Distrito1` FOREIGN KEY (`Distrito_idDistrito`) REFERENCES `Distrito` (`idDistrito`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Proveedor`
--

LOCK TABLES `Proveedor` WRITE;
/*!40000 ALTER TABLE `Proveedor` DISABLE KEYS */;
/*!40000 ALTER TABLE `Proveedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Proyecto`
--

DROP TABLE IF EXISTS `Proyecto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Proyecto` (
  `idProyecto` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) DEFAULT NULL,
  `Descripcion` varchar(145) DEFAULT NULL,
  `Inicio` date DEFAULT NULL,
  `Fin` date DEFAULT NULL,
  `CostoPersonal` decimal(10,2) DEFAULT NULL,
  `CostoEquipo` decimal(10,2) DEFAULT NULL,
  `CostoOtros` decimal(10,2) DEFAULT NULL,
  `Cierre` tinyint(1) DEFAULT NULL,
  `CostoMaterialReal` decimal(10,2) DEFAULT NULL,
  `CostoPersonalReal` decimal(10,2) DEFAULT NULL,
  `CostoOtrosReal` decimal(10,2) DEFAULT NULL,
  `Vigencia` tinyint(1) NOT NULL,
  PRIMARY KEY (`idProyecto`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Proyecto`
--

LOCK TABLES `Proyecto` WRITE;
/*!40000 ALTER TABLE `Proyecto` DISABLE KEYS */;
INSERT INTO `Proyecto` VALUES (1,'RUQU','DASD','2013-11-12',NULL,120.00,130.00,20.00,NULL,NULL,NULL,NULL,1);
/*!40000 ALTER TABLE `Proyecto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Rol`
--

DROP TABLE IF EXISTS `Rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Rol` (
  `idRol` int(11) NOT NULL AUTO_INCREMENT,
  `Descripcion` varchar(45) DEFAULT NULL,
  `Vigencia` tinyint(1) NOT NULL,
  PRIMARY KEY (`idRol`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Rol`
--

LOCK TABLES `Rol` WRITE;
/*!40000 ALTER TABLE `Rol` DISABLE KEYS */;
INSERT INTO `Rol` VALUES (1,'Administrador',1);
/*!40000 ALTER TABLE `Rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Usuario`
--

DROP TABLE IF EXISTS `Usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Usuario` (
  `idUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `Login` varchar(45) NOT NULL,
  `Password` varchar(45) NOT NULL,
  `Vigencia` tinyint(1) NOT NULL,
  `Rol_idRol` int(11) DEFAULT NULL,
  PRIMARY KEY (`idUsuario`),
  KEY `fk_Usuario_Rol1_idx` (`Rol_idRol`),
  CONSTRAINT `fk_Usuario_Rol1` FOREIGN KEY (`Rol_idRol`) REFERENCES `Rol` (`idRol`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Usuario`
--

LOCK TABLES `Usuario` WRITE;
/*!40000 ALTER TABLE `Usuario` DISABLE KEYS */;
INSERT INTO `Usuario` VALUES (1,'admin','admin',1,1);
/*!40000 ALTER TABLE `Usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-11-29 19:21:51
