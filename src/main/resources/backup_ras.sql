CREATE DATABASE  IF NOT EXISTS `ras` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ras`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: ras
-- ------------------------------------------------------
-- Server version	5.5.28-log

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
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categoria` (
  `idCategoria` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) DEFAULT NULL,
  `Descripcion` varchar(45) DEFAULT NULL,
  `Vigencia` tinyint(1) NOT NULL,
  PRIMARY KEY (`idCategoria`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES (1,'RUQU','ABBB U',1);
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `compra`
--

DROP TABLE IF EXISTS `compra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `compra` (
  `idCompra` int(11) NOT NULL AUTO_INCREMENT,
  `Fecha` date DEFAULT NULL,
  `Total` decimal(10,2) DEFAULT NULL,
  `Vigencia` tinyint(1) NOT NULL,
  `Proveedor_idProveedor` int(11) NOT NULL,
  `Descuento` decimal(10,2) NOT NULL DEFAULT '0.00',
  `NroGuiaRemision` varchar(20) NOT NULL,
  PRIMARY KEY (`idCompra`),
  UNIQUE KEY `NroGuiaRemision_UNIQUE` (`NroGuiaRemision`),
  KEY `fk_Compra_Proveedor1_idx` (`Proveedor_idProveedor`),
  CONSTRAINT `fk_Compra_Proveedor1` FOREIGN KEY (`Proveedor_idProveedor`) REFERENCES `proveedor` (`idProveedor`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compra`
--

LOCK TABLES `compra` WRITE;
/*!40000 ALTER TABLE `compra` DISABLE KEYS */;
/*!40000 ALTER TABLE `compra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detallecompra`
--

DROP TABLE IF EXISTS `detallecompra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `detallecompra` (
  `Compra_idCompra` int(11) NOT NULL,
  `Equipo_idEquipo` int(11) NOT NULL,
  `Cantidad` int(11) DEFAULT NULL,
  `TotalDetalle` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`Compra_idCompra`,`Equipo_idEquipo`),
  KEY `fk_Compra_has_Equipo_Equipo1_idx` (`Equipo_idEquipo`),
  KEY `fk_Compra_has_Equipo_Compra1_idx` (`Compra_idCompra`),
  CONSTRAINT `fk_Compra_has_Equipo_Compra1` FOREIGN KEY (`Compra_idCompra`) REFERENCES `compra` (`idCompra`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Compra_has_Equipo_Equipo1` FOREIGN KEY (`Equipo_idEquipo`) REFERENCES `equipo` (`idEquipo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detallecompra`
--

LOCK TABLES `detallecompra` WRITE;
/*!40000 ALTER TABLE `detallecompra` DISABLE KEYS */;
/*!40000 ALTER TABLE `detallecompra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `distrito`
--

DROP TABLE IF EXISTS `distrito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `distrito` (
  `idDistrito` int(11) NOT NULL AUTO_INCREMENT,
  `Distrito` varchar(45) DEFAULT NULL,
  `Vigencia` tinyint(1) NOT NULL,
  PRIMARY KEY (`idDistrito`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `distrito`
--

LOCK TABLES `distrito` WRITE;
/*!40000 ALTER TABLE `distrito` DISABLE KEYS */;
INSERT INTO `distrito` VALUES (1,'SJL',1);
/*!40000 ALTER TABLE `distrito` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipo`
--

DROP TABLE IF EXISTS `equipo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equipo` (
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
  CONSTRAINT `fk_Equipo_Categoria1` FOREIGN KEY (`Categoria_idCategoria`) REFERENCES `categoria` (`idCategoria`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipo`
--

LOCK TABLES `equipo` WRITE;
/*!40000 ALTER TABLE `equipo` DISABLE KEYS */;
/*!40000 ALTER TABLE `equipo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipoasignado`
--

DROP TABLE IF EXISTS `equipoasignado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equipoasignado` (
  `Equipo_idEquipo` int(11) NOT NULL,
  `Proyecto_idProyecto` int(11) NOT NULL,
  `Fecha` date DEFAULT NULL,
  `Cantidad` int(11) NOT NULL,
  `PrecioUnit` decimal(10,2) NOT NULL,
  PRIMARY KEY (`Equipo_idEquipo`,`Proyecto_idProyecto`),
  KEY `fk_Equipo_has_Proyecto_Proyecto1_idx` (`Proyecto_idProyecto`),
  KEY `fk_Equipo_has_Proyecto_Equipo1_idx` (`Equipo_idEquipo`),
  CONSTRAINT `fk_Equipo_has_Proyecto_Equipo1` FOREIGN KEY (`Equipo_idEquipo`) REFERENCES `equipo` (`idEquipo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Equipo_has_Proyecto_Proyecto1` FOREIGN KEY (`Proyecto_idProyecto`) REFERENCES `proyecto` (`idProyecto`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipoasignado`
--

LOCK TABLES `equipoasignado` WRITE;
/*!40000 ALTER TABLE `equipoasignado` DISABLE KEYS */;
/*!40000 ALTER TABLE `equipoasignado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `logconsulta`
--

DROP TABLE IF EXISTS `logconsulta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `logconsulta` (
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
  CONSTRAINT `fk_LogConsulta_Compra1` FOREIGN KEY (`Compra_idCompra`) REFERENCES `compra` (`idCompra`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_LogConsulta_Equipo1` FOREIGN KEY (`Equipo_idEquipo`) REFERENCES `equipo` (`idEquipo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_LogConsulta_Proyecto1` FOREIGN KEY (`Proyecto_idProyecto`) REFERENCES `proyecto` (`idProyecto`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_LogConsulta_Usuario1` FOREIGN KEY (`Usuario_idUsuario`) REFERENCES `usuario` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `logconsulta`
--

LOCK TABLES `logconsulta` WRITE;
/*!40000 ALTER TABLE `logconsulta` DISABLE KEYS */;
/*!40000 ALTER TABLE `logconsulta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `opcion`
--

DROP TABLE IF EXISTS `opcion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `opcion` (
  `idOpcion` int(11) NOT NULL AUTO_INCREMENT,
  `Descripcion` varchar(45) DEFAULT NULL,
  `TextoOpcion` varchar(45) DEFAULT NULL,
  `Ruta` varchar(45) DEFAULT NULL,
  `Vigencia` tinyint(1) NOT NULL,
  `id_menu_padre` int(11) DEFAULT NULL,
  PRIMARY KEY (`idOpcion`),
  KEY `fk_Opcion_Opcion1_idx` (`id_menu_padre`),
  CONSTRAINT `fk_Opcion_Opcion1` FOREIGN KEY (`id_menu_padre`) REFERENCES `opcion` (`idOpcion`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `opcion`
--

LOCK TABLES `opcion` WRITE;
/*!40000 ALTER TABLE `opcion` DISABLE KEYS */;
INSERT INTO `opcion` VALUES (1,'Compras','Compras',NULL,1,NULL),(2,'Inventario','Inventario',NULL,1,NULL),(3,'Personal','Personal',NULL,1,NULL),(4,'Proyecto','Proyecto',NULL,1,NULL),(5,'security','Seguridad',NULL,1,NULL),(6,'historialCompras','Historico','pages/Compras/historialCompras.xhtml',1,1),(7,'mantCompras','Detalle','pages/Compras/mantCompras.xhtml',1,1),(8,'mantProveedor','Proveedor','pages/Compras/mantProveedor.xhtml',1,1),(9,'mantCategoria','Categoria','pages/Inventario/mantCategoria.xhtml',1,2),(10,'mantEquipo','Detalle Equipo','pages/Inventario/mantEquipo.xhtml',1,2),(11,'mantPersonal','Detalle','pages/Personal/mantPersonal.xhtml',1,3),(12,'detalleProyecto','Detalle','pages/Proyecto/detalleProyecto.xhtml',1,4),(13,'mantProyecto','Gestion','pages/Proyecto/mantProyecto.xhtml',1,4),(14,'mantRoles','Gestion de Roles','pages/security/mantRoles.xhtml',1,5),(15,'Usuario','Usuario','pages/security/Usuario.xhtml',1,5);
/*!40000 ALTER TABLE `opcion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `opcionasignadas`
--

DROP TABLE IF EXISTS `opcionasignadas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `opcionasignadas` (
  `Rol_idRol` int(11) NOT NULL,
  `Opcion_idOpcion` int(11) NOT NULL,
  PRIMARY KEY (`Rol_idRol`,`Opcion_idOpcion`),
  KEY `fk_Rol_has_Opcion_Opcion1_idx` (`Opcion_idOpcion`),
  KEY `fk_Rol_has_Opcion_Rol1_idx` (`Rol_idRol`),
  CONSTRAINT `fk_Rol_has_Opcion_Opcion1` FOREIGN KEY (`Opcion_idOpcion`) REFERENCES `opcion` (`idOpcion`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Rol_has_Opcion_Rol1` FOREIGN KEY (`Rol_idRol`) REFERENCES `rol` (`idRol`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `opcionasignadas`
--

LOCK TABLES `opcionasignadas` WRITE;
/*!40000 ALTER TABLE `opcionasignadas` DISABLE KEYS */;
INSERT INTO `opcionasignadas` VALUES (1,1),(1,2),(3,2),(1,3),(1,4),(1,5),(2,7),(2,11);
/*!40000 ALTER TABLE `opcionasignadas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `otrogasto`
--

DROP TABLE IF EXISTS `otrogasto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `otrogasto` (
  `idOtroGasto` int(11) NOT NULL AUTO_INCREMENT,
  `Partida` varchar(45) DEFAULT NULL,
  `Descripcion` varchar(45) DEFAULT NULL,
  `Monto` decimal(10,2) DEFAULT NULL,
  `Registro` date DEFAULT NULL,
  `Vigencia` tinyint(1) NOT NULL,
  `Proyecto_idProyecto` int(11) NOT NULL,
  PRIMARY KEY (`idOtroGasto`),
  KEY `fk_OtroGasto_Proyecto1_idx` (`Proyecto_idProyecto`),
  CONSTRAINT `fk_OtroGasto_Proyecto1` FOREIGN KEY (`Proyecto_idProyecto`) REFERENCES `proyecto` (`idProyecto`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `otrogasto`
--

LOCK TABLES `otrogasto` WRITE;
/*!40000 ALTER TABLE `otrogasto` DISABLE KEYS */;
/*!40000 ALTER TABLE `otrogasto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personal`
--

DROP TABLE IF EXISTS `personal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personal` (
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
-- Dumping data for table `personal`
--

LOCK TABLES `personal` WRITE;
/*!40000 ALTER TABLE `personal` DISABLE KEYS */;
/*!40000 ALTER TABLE `personal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personalasignado`
--

DROP TABLE IF EXISTS `personalasignado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personalasignado` (
  `Proyecto_idProyecto` int(11) NOT NULL,
  `Personal_idPersonal` int(11) NOT NULL,
  `Pago` decimal(10,2) DEFAULT NULL,
  `Tarea` varchar(145) DEFAULT NULL,
  `Inicio` date DEFAULT NULL,
  `Fin` date DEFAULT NULL,
  PRIMARY KEY (`Proyecto_idProyecto`,`Personal_idPersonal`),
  KEY `fk_Proyecto_has_Personal_Personal1_idx` (`Personal_idPersonal`),
  KEY `fk_Proyecto_has_Personal_Proyecto1_idx` (`Proyecto_idProyecto`),
  CONSTRAINT `fk_Proyecto_has_Personal_Personal1` FOREIGN KEY (`Personal_idPersonal`) REFERENCES `personal` (`idPersonal`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Proyecto_has_Personal_Proyecto1` FOREIGN KEY (`Proyecto_idProyecto`) REFERENCES `proyecto` (`idProyecto`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personalasignado`
--

LOCK TABLES `personalasignado` WRITE;
/*!40000 ALTER TABLE `personalasignado` DISABLE KEYS */;
/*!40000 ALTER TABLE `personalasignado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proveedor`
--

DROP TABLE IF EXISTS `proveedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proveedor` (
  `idProveedor` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) DEFAULT NULL,
  `Direccion` varchar(45) DEFAULT NULL,
  `Telefono` varchar(45) DEFAULT NULL,
  `Vigencia` tinyint(1) DEFAULT NULL,
  `Distrito_idDistrito` int(11) NOT NULL,
  PRIMARY KEY (`idProveedor`),
  KEY `fk_Proveedor_Distrito1_idx` (`Distrito_idDistrito`),
  CONSTRAINT `fk_Proveedor_Distrito1` FOREIGN KEY (`Distrito_idDistrito`) REFERENCES `distrito` (`idDistrito`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedor`
--

LOCK TABLES `proveedor` WRITE;
/*!40000 ALTER TABLE `proveedor` DISABLE KEYS */;
/*!40000 ALTER TABLE `proveedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proyecto`
--

DROP TABLE IF EXISTS `proyecto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proyecto` (
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
-- Dumping data for table `proyecto`
--

LOCK TABLES `proyecto` WRITE;
/*!40000 ALTER TABLE `proyecto` DISABLE KEYS */;
/*!40000 ALTER TABLE `proyecto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rol` (
  `idRol` int(11) NOT NULL AUTO_INCREMENT,
  `Descripcion` varchar(45) DEFAULT NULL,
  `Vigencia` tinyint(1) NOT NULL,
  PRIMARY KEY (`idRol`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES (1,'Administrador',1),(2,'Worker1',1),(3,'Worker2',1);
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `idUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `Login` varchar(45) NOT NULL,
  `Password` varchar(120) NOT NULL,
  `Vigencia` tinyint(1) NOT NULL,
  `Rol_idRol` int(11) DEFAULT NULL,
  PRIMARY KEY (`idUsuario`),
  KEY `fk_Usuario_Rol1_idx` (`Rol_idRol`),
  CONSTRAINT `fk_Usuario_Rol1` FOREIGN KEY (`Rol_idRol`) REFERENCES `rol` (`idRol`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'admin','$2a$10$WI6zWni/y5ZnO5.y1HPHX.Z3shzTBXrYcjMEcwc4ybwwZpbYlIzwe',1,1),(2,'dtowong','$2a$10$7cUXKhvBU0yMwXyNgbCt6.qdble0I0c.NdFQAb2io2KndRw4MLFJa',1,2),(3,'germancruz','$2a$10$wM7brphrbKqY7Zf0NHfvIejsC4/3d8wlg..XMH5sDkHyuB3v6RDZi',1,3);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-12-13 15:05:36
