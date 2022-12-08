-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Tempo de geração: 05-Dez-2022 às 00:53
-- Versão do servidor: 10.4.22-MariaDB
-- versão do PHP: 7.4.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `locadora`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `Cliente`
--

CREATE TABLE `Cliente` (
  `id` int(4) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `telefone` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `Emprestimo`
--

CREATE TABLE `Emprestimo` (
  `id` int(4) NOT NULL,
  `data_emprestimo` datetime DEFAULT NULL,
  `data_entrega` datetime DEFAULT NULL,
  `obs` varchar(255) DEFAULT NULL,
  `valorEmprestimo` int(255) NOT NULL,
  `fk_filme` int(255) NOT NULL,
  `fk_cliente` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `Filmes`
--

CREATE TABLE `Filmes` (
  `id` int(4) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `ano` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `Cliente`
--
ALTER TABLE `Cliente`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `Emprestimo`
--
ALTER TABLE `Emprestimo`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_emprestimo_cliente_idx` (`fk_cliente`) USING BTREE,
  ADD KEY `fk_emprestimo_filme_idx` (`fk_filme`) USING BTREE;

--
-- Índices para tabela `Filmes`
--
ALTER TABLE `Filmes`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `Cliente`
--
ALTER TABLE `Cliente`
  MODIFY `id` int(4) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `Emprestimo`
--
ALTER TABLE `Emprestimo`
  MODIFY `id` int(4) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `Filmes`
--
ALTER TABLE `Filmes`
  MODIFY `id` int(4) NOT NULL AUTO_INCREMENT;

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `Emprestimo`
--
ALTER TABLE `Emprestimo`
  ADD CONSTRAINT `Emprestimo_ibfk_1` FOREIGN KEY (`fk_cliente`) REFERENCES `Cliente` (`id`),
  ADD CONSTRAINT `Emprestimo_ibfk_2` FOREIGN KEY (`fk_filme`) REFERENCES `Filmes` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;