-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Dec 22, 2017 at 11:38 AM
-- Server version: 10.1.24-MariaDB
-- PHP Version: 7.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";

CREATE DATABASE IF NOT EXISTS bookabookDB;
USE bookabookDB;

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bookabookDB`
--

-- --------------------------------------------------------

--
-- Table structure for table `Account`
--

CREATE TABLE `Account` (
  `email` varchar(128) NOT NULL,
  `password` varchar(32) NOT NULL,
  `path_foto` varchar(128) NOT NULL,
  `tipo` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `Admin`
--

CREATE TABLE `Admin` (
  `id` int(16) NOT NULL,
  `nome` varchar(64) NOT NULL,
  `cognome` varchar(64) NOT NULL,
  `email` varchar(128) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `Autore`
--

CREATE TABLE `Autore` (
  `id` int(16) NOT NULL,
  `nome` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `Biblioteca`
--

CREATE TABLE `Biblioteca` (
  `isil` varchar(32) NOT NULL,
  `nome` varchar(64) NOT NULL,
  `status` varchar(32) NOT NULL,
  `via` varchar(64) NOT NULL,
  `citta` varchar(64) NOT NULL,
  `civico` varchar(8) NOT NULL,
  `id_admin` int(16) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `Bibliotecario`
--

CREATE TABLE `Bibliotecario` (
  `id` int(16) NOT NULL,
  `nome` varchar(64) NOT NULL,
  `cognome` varchar(64) NOT NULL,
  `status` varchar(32) NOT NULL,
  `email` varchar(128) NOT NULL,
  `isil` varchar(32) NOT NULL,
  `tipo` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `Indirizzo`
--

CREATE TABLE `Indirizzo` (
  `via` varchar(64) NOT NULL,
  `citta` varchar(64) NOT NULL,
  `civico` varchar(8) NOT NULL,
  `provincia` varchar(4) NOT NULL,
  `cap` varchar(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `Libro`
--

CREATE TABLE `Libro` (
  `isbn` varchar(16) NOT NULL,
  `titolo` varchar(32) NOT NULL,
  `editore` varchar(32) NOT NULL,
  `data_pubblicazione` date NOT NULL,
  `descrizione` text NOT NULL,
  `disponibilita` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `Libro_Autore`
--

CREATE TABLE `Libro_Autore` (
  `id_autore` int(16) NOT NULL,
  `isbn` varchar(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `Persona`
--

CREATE TABLE `Persona` (
  `id` int(16) NOT NULL,
  `nome` varchar(64) NOT NULL,
  `cognome` varchar(64) NOT NULL,
  `email` varchar(128) DEFAULT NULL,
  `num_documento` varchar(32) NOT NULL,
  `via` varchar(64) NOT NULL,
  `civico` varchar(8) NOT NULL,
  `citta` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `Posizione`
--

CREATE TABLE `Posizione` (
  `etichetta` varchar(64) NOT NULL,
  `num_copie` int(4) NOT NULL,
  `num_copie_totali` int(4) NOT NULL,
  `isil` varchar(32) NOT NULL,
  `isbn` varchar(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `Prenotazione`
--

CREATE TABLE `Prenotazione` (
  `id` int(16) NOT NULL,
  `data_creazione` date NOT NULL,
  `data_scadenza` date NOT NULL,
  `data_consegna` date NOT NULL,
  `id_persona` int(16) NOT NULL,
  `isbn` varchar(16) NOT NULL,
  `isil` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `Telefono`
--

CREATE TABLE `Telefono` (
  `prefisso` varchar(8) NOT NULL,
  `numero` varchar(16) NOT NULL,
  `id_persona` int(16) DEFAULT NULL,
  `isil` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Account`
--
ALTER TABLE `Account`
  ADD PRIMARY KEY (`email`);

--
-- Indexes for table `Admin`
--
ALTER TABLE `Admin`
  ADD PRIMARY KEY (`id`),
  ADD KEY `email` (`email`);

--
-- Indexes for table `Autore`
--
ALTER TABLE `Autore`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `Biblioteca`
--
ALTER TABLE `Biblioteca`
  ADD PRIMARY KEY (`isil`),
  ADD KEY `id_admin` (`id_admin`),
  ADD KEY `via` (`via`,`citta`,`civico`);

--
-- Indexes for table `Bibliotecario`
--
ALTER TABLE `Bibliotecario`
  ADD PRIMARY KEY (`id`),
  ADD KEY `email` (`email`),
  ADD KEY `isil` (`isil`);

--
-- Indexes for table `Indirizzo`
--
ALTER TABLE `Indirizzo`
  ADD PRIMARY KEY (`via`,`citta`,`civico`);

--
-- Indexes for table `Libro`
--
ALTER TABLE `Libro`
  ADD PRIMARY KEY (`isbn`);

--
-- Indexes for table `Libro_Autore`
--
ALTER TABLE `Libro_Autore`
  ADD PRIMARY KEY (`id_autore`,`isbn`),
  ADD KEY `isbn` (`isbn`);

--
-- Indexes for table `Persona`
--
ALTER TABLE `Persona`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `num_documento` (`num_documento`),
  ADD KEY `email` (`email`),
  ADD KEY `via` (`via`,`citta`,`civico`);

--
-- Indexes for table `Posizione`
--
ALTER TABLE `Posizione`
  ADD PRIMARY KEY (`etichetta`,`isil`,`isbn`),
  ADD KEY `isbn` (`isbn`),
  ADD KEY `isil` (`isil`);

--
-- Indexes for table `Prenotazione`
--
ALTER TABLE `Prenotazione`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_persona` (`id_persona`),
  ADD KEY `isil` (`isil`),
  ADD KEY `isbn` (`isbn`);

--
-- Indexes for table `Telefono`
--
ALTER TABLE `Telefono`
  ADD PRIMARY KEY (`prefisso`,`numero`),
  ADD KEY `id_persona` (`id_persona`),
  ADD KEY `isil` (`isil`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Admin`
--
ALTER TABLE `Admin`
  MODIFY `id` int(16) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `Autore`
--
ALTER TABLE `Autore`
  MODIFY `id` int(16) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `Bibliotecario`
--
ALTER TABLE `Bibliotecario`
  MODIFY `id` int(16) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `Persona`
--
ALTER TABLE `Persona`
  MODIFY `id` int(16) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `Prenotazione`
--
ALTER TABLE `Prenotazione`
  MODIFY `id` int(16) NOT NULL AUTO_INCREMENT;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `Admin`
--
ALTER TABLE `Admin`
  ADD CONSTRAINT `Admin_ibfk_1` FOREIGN KEY (`email`) REFERENCES `Account` (`email`) ON UPDATE CASCADE;

--
-- Constraints for table `Biblioteca`
--
ALTER TABLE `Biblioteca`
  ADD CONSTRAINT `Biblioteca_ibfk_1` FOREIGN KEY (`id_admin`) REFERENCES `Admin` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `Biblioteca_ibfk_2` FOREIGN KEY (`via`,`citta`,`civico`) REFERENCES `Indirizzo` (`via`, `citta`, `civico`) ON UPDATE CASCADE;

--
-- Constraints for table `Bibliotecario`
--
ALTER TABLE `Bibliotecario`
  ADD CONSTRAINT `Bibliotecario_ibfk_1` FOREIGN KEY (`email`) REFERENCES `Account` (`email`) ON UPDATE CASCADE,
  ADD CONSTRAINT `Bibliotecario_ibfk_2` FOREIGN KEY (`isil`) REFERENCES `Biblioteca` (`isil`) ON UPDATE CASCADE;

--
-- Constraints for table `Libro_Autore`
--
ALTER TABLE `Libro_Autore`
  ADD CONSTRAINT `Libro_Autore_ibfk_1` FOREIGN KEY (`id_autore`) REFERENCES `Autore` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `Libro_Autore_ibfk_2` FOREIGN KEY (`isbn`) REFERENCES `Libro` (`isbn`) ON UPDATE CASCADE;

--
-- Constraints for table `Persona`
--
ALTER TABLE `Persona`
  ADD CONSTRAINT `Persona_ibfk_1` FOREIGN KEY (`email`) REFERENCES `Account` (`email`) ON UPDATE CASCADE,
  ADD CONSTRAINT `Persona_ibfk_2` FOREIGN KEY (`via`,`citta`,`civico`) REFERENCES `Indirizzo` (`via`, `citta`, `civico`) ON UPDATE CASCADE;

--
-- Constraints for table `Posizione`
--
ALTER TABLE `Posizione`
  ADD CONSTRAINT `Posizione_ibfk_1` FOREIGN KEY (`isbn`) REFERENCES `Libro` (`isbn`) ON UPDATE CASCADE,
  ADD CONSTRAINT `Posizione_ibfk_2` FOREIGN KEY (`isil`) REFERENCES `Biblioteca` (`isil`) ON UPDATE CASCADE;

--
-- Constraints for table `Prenotazione`
--
ALTER TABLE `Prenotazione`
  ADD CONSTRAINT `Prenotazione_ibfk_1` FOREIGN KEY (`id_persona`) REFERENCES `Persona` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `Prenotazione_ibfk_2` FOREIGN KEY (`isil`) REFERENCES `Biblioteca` (`isil`) ON UPDATE CASCADE,
  ADD CONSTRAINT `Prenotazione_ibfk_3` FOREIGN KEY (`isbn`) REFERENCES `Libro` (`isbn`) ON UPDATE CASCADE;

--
-- Constraints for table `Telefono`
--
ALTER TABLE `Telefono`
  ADD CONSTRAINT `Telefono_ibfk_1` FOREIGN KEY (`id_persona`) REFERENCES `Persona` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `Telefono_ibfk_2` FOREIGN KEY (`isil`) REFERENCES `Biblioteca` (`isil`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
