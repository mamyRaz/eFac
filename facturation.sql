-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  mar. 11 juin 2019 à 00:26
-- Version du serveur :  5.7.24
-- Version de PHP :  5.6.40

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `facturation`
--

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

DROP TABLE IF EXISTS `client`;
CREATE TABLE IF NOT EXISTS `client` (
  `id_client` int(11) NOT NULL AUTO_INCREMENT,
  `client_type_id` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `adresse` varchar(255) NOT NULL,
  `telephone` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `siret` varchar(255) NOT NULL,
  PRIMARY KEY (`id_client`),
  KEY `clientfk_client_type_id` (`client_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`id_client`, `client_type_id`, `nom`, `prenom`, `adresse`, `telephone`, `email`, `siret`) VALUES
(1, 1, 'Villar', 'Anthony', '45 rue des Blazers 68100 Mulhouse', '0789021144', 'antony@gmail.com', ''),
(2, 1, 'Kapellen', 'Stéphanie', '12 rue des bastards 68350 Brunstatt', '0678320012', 'stephanie@hotmail.com', ''),
(3, 2, 'Gobags', '', '45 rue de la vallee 75150 Paris', '0812101399', 'contact@gobags.com', '802 954 785 00030 ');

-- --------------------------------------------------------

--
-- Structure de la table `client_type`
--

DROP TABLE IF EXISTS `client_type`;
CREATE TABLE IF NOT EXISTS `client_type` (
  `id_client_type` int(11) NOT NULL AUTO_INCREMENT,
  `designation_type` varchar(255) NOT NULL,
  PRIMARY KEY (`id_client_type`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `client_type`
--

INSERT INTO `client_type` (`id_client_type`, `designation_type`) VALUES
(1, 'personne'),
(2, 'entreprise');

-- --------------------------------------------------------

--
-- Structure de la table `devise`
--

DROP TABLE IF EXISTS `devise`;
CREATE TABLE IF NOT EXISTS `devise` (
  `id_devise` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) NOT NULL,
  `symbole` varchar(10) NOT NULL,
  PRIMARY KEY (`id_devise`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `devise`
--

INSERT INTO `devise` (`id_devise`, `description`, `symbole`) VALUES
(1, 'Euro', '€'),
(2, 'Dollar', '$');

-- --------------------------------------------------------

--
-- Structure de la table `entreprise`
--

DROP TABLE IF EXISTS `entreprise`;
CREATE TABLE IF NOT EXISTS `entreprise` (
  `id_entreprise` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) NOT NULL,
  `nif` int(11) NOT NULL,
  `stat` int(11) NOT NULL,
  `capital_social` double NOT NULL,
  `adresse` varchar(255) NOT NULL,
  `telephone` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`id_entreprise`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `entreprise`
--

INSERT INTO `entreprise` (`id_entreprise`, `nom`, `nif`, `stat`, `capital_social`, `adresse`, `telephone`, `email`) VALUES
(1, 'eKandra', 1251003245, 325569900, 12000000000000, 'lot VL 12 Ambavinaky Antananarivo 101', '0345222377', 'contact@ekandra.com');

-- --------------------------------------------------------

--
-- Structure de la table `escompte`
--

DROP TABLE IF EXISTS `escompte`;
CREATE TABLE IF NOT EXISTS `escompte` (
  `id_escompte` int(11) NOT NULL AUTO_INCREMENT,
  `facture_id` int(11) NOT NULL,
  `echeance_jour` int(11) NOT NULL DEFAULT '0',
  `echeance_date` date NOT NULL,
  `taux_escompte` double NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_escompte`),
  KEY `escomptefk_facture_id` (`facture_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `escompte`
--

INSERT INTO `escompte` (`id_escompte`, `facture_id`, `echeance_jour`, `echeance_date`, `taux_escompte`) VALUES
(1, 3, 0, '2019-05-26', 1.5),
(2, 3, 2, '2019-05-28', 0.5),
(3, 5, 0, '2019-05-27', 1),
(4, 6, 1, '2019-05-30', 1),
(5, 7, 0, '2019-05-29', 2),
(6, 16, 2, '2019-06-05', 3);

-- --------------------------------------------------------

--
-- Structure de la table `facture`
--

DROP TABLE IF EXISTS `facture`;
CREATE TABLE IF NOT EXISTS `facture` (
  `id_facture` int(11) NOT NULL AUTO_INCREMENT,
  `client_id` int(11) NOT NULL,
  `entreprise_id` int(11) NOT NULL,
  `facture_type` varchar(255) NOT NULL,
  `numero_facture` int(11) NOT NULL,
  `date_facture` date NOT NULL,
  `date_delais` date NOT NULL,
  `etat_facture` varchar(255) NOT NULL,
  `reductions_commerciales` double NOT NULL DEFAULT '0',
  `reductions_financieres` double NOT NULL DEFAULT '0',
  `total_ht` double NOT NULL DEFAULT '0',
  `total_tva` double NOT NULL DEFAULT '0',
  `total_ttc` double NOT NULL DEFAULT '0',
  `acompte` double NOT NULL DEFAULT '0',
  `netAPayer` double NOT NULL,
  `remarques` text NOT NULL,
  `net_commerciale` double NOT NULL DEFAULT '0',
  `net_financier` double NOT NULL DEFAULT '0',
  `solde_impayees` double NOT NULL DEFAULT '0',
  `solde_payees` double NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_facture`),
  KEY `facturefk_client_id` (`client_id`),
  KEY `facturefk_entreprise_id` (`entreprise_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `facture`
--

INSERT INTO `facture` (`id_facture`, `client_id`, `entreprise_id`, `facture_type`, `numero_facture`, `date_facture`, `date_delais`, `etat_facture`, `reductions_commerciales`, `reductions_financieres`, `total_ht`, `total_tva`, `total_ttc`, `acompte`, `netAPayer`, `remarques`, `net_commerciale`, `net_financier`, `solde_impayees`, `solde_payees`) VALUES
(1, 1, 1, 'doit', 1, '2019-05-26', '2019-05-27', 'impayee', 0, 0, 564.97, 112.994, 677.964, 0, 677.964, '', 564.97, 0, 677.964, 0),
(3, 2, 1, 'doit', 2, '2019-05-26', '2019-05-30', 'payee', 0.4, 0, 29.580000000000002, 3.0740000000000003, 32.654, 2, 30.654000000000003, 'escomptes accordés', 29.580000000000002, 0, 0, 31),
(5, 1, 1, 'doit', 3, '2019-05-27', '2019-05-31', 'payee', 0, 0, 100, 20, 120, 0, 120, '', 100, 0, 0, 120),
(6, 1, 1, 'doit', 4, '2019-05-29', '2019-05-31', 'payee', 2, 0, 33, 3.99, 36.989999999999995, 0, 36.989999999999995, 'avec frais d\'expedition', 18, 0, 0, 37),
(7, 1, 1, 'doit', 5, '2019-05-29', '2019-05-31', 'payee', 1.75, 0, 73.22, 9.82275, 83.04275, 0, 83.04275, 'commande en ligne', 48.22, 0, 0, 82),
(9, 3, 1, 'doit', 6, '2019-05-29', '2019-05-31', 'payee', 0, 0, 30, 6, 36, 0, 36, 'par faute du client', 30, 0, 0, 36),
(10, 2, 1, 'doit', 7, '2019-05-29', '2019-05-31', 'payee', 0, 0, 4500.99, 900.198, 5401.188, 0, 5401.188, '', 4500.99, 0, 0, 5401),
(11, 2, 1, 'doit', 8, '2019-05-29', '2019-05-29', 'payee', 0, 0, 100, 19, 119, 0, 119, '', 100, 0, 0, 119),
(12, 1, 1, 'doit', 9, '2019-05-30', '2019-05-30', 'payee', 0, 0, 30, 1.65, 31.65, 0, 31.65, '', 30, 0, 0, 32),
(13, 1, 1, 'doit', 10, '2019-05-29', '2019-05-29', 'payee', 0, 0, 9.98, 1.9960000000000002, 11.976, 0, 11.976, '', 9.98, 0, 0, 12),
(14, 1, 1, 'doit', 11, '2019-05-29', '2019-05-29', 'payee', 0, 0, 4.99, 0.9980000000000001, 5.988, 0, 5.988, '', 4.99, 0, 0, 6),
(15, 1, 1, 'doit', 12, '2019-05-29', '2019-05-30', 'payee', 0, 0, 9.98, 1.9960000000000002, 11.976, 0, 11.976, '', 9.98, 0, 0, 12),
(16, 2, 1, 'doit', 13, '2019-06-03', '2019-06-26', 'payee', 0, 0, 5, 0.275, 5.275, 0, 5.275, 'autre', 5, 0, 0, 5);

-- --------------------------------------------------------

--
-- Structure de la table `famille_produit`
--

DROP TABLE IF EXISTS `famille_produit`;
CREATE TABLE IF NOT EXISTS `famille_produit` (
  `id_famille_produit` int(11) NOT NULL AUTO_INCREMENT,
  `designation_famille` varchar(255) NOT NULL,
  PRIMARY KEY (`id_famille_produit`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `famille_produit`
--

INSERT INTO `famille_produit` (`id_famille_produit`, `designation_famille`) VALUES
(1, 'Alimentaire'),
(2, 'Vestimentaire'),
(3, 'Matériel informatique'),
(4, 'Cosmétique '),
(5, 'Main d\'oeuvre'),
(6, 'autre'),
(7, 'Materiel bureau'),
(8, 'Piece moto');

-- --------------------------------------------------------

--
-- Structure de la table `ligne_facture`
--

DROP TABLE IF EXISTS `ligne_facture`;
CREATE TABLE IF NOT EXISTS `ligne_facture` (
  `id_ligne_facture` int(11) NOT NULL AUTO_INCREMENT,
  `facture_id` int(11) NOT NULL,
  `produit_id` int(11) DEFAULT NULL COMMENT 'ce champ peut être vide s''il n''y a pas de produit pour la ligne',
  `ligne_type` varchar(255) NOT NULL,
  `numero_ligne` int(11) NOT NULL COMMENT 'numéro de la ligne sur la facture',
  `designation_ligne` varchar(255) NOT NULL,
  `quantite` int(11) NOT NULL,
  `montant_unitaire` double NOT NULL DEFAULT '0',
  `rabais` double NOT NULL DEFAULT '0' COMMENT 'taux rabais',
  `montant_rabais` double NOT NULL DEFAULT '0',
  `remise` double NOT NULL DEFAULT '0' COMMENT 'taux remise',
  `montant_remise` double NOT NULL DEFAULT '0',
  `ristourne` double NOT NULL DEFAULT '0' COMMENT 'taux ristourne',
  `montant_ristourne` double NOT NULL DEFAULT '0',
  `montant_ht` int(11) NOT NULL,
  `montant_tva` int(11) NOT NULL,
  `montant_ttc` int(11) NOT NULL,
  `reductions_commerciales` double NOT NULL DEFAULT '0',
  `net_commerciale` double NOT NULL DEFAULT '0',
  `tva` double NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_ligne_facture`),
  KEY `facture_fk1` (`facture_id`),
  KEY `ligne_facturefk_produit_id` (`produit_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `ligne_facture`
--

INSERT INTO `ligne_facture` (`id_ligne_facture`, `facture_id`, `produit_id`, `ligne_type`, `numero_ligne`, `designation_ligne`, `quantite`, `montant_unitaire`, `rabais`, `montant_rabais`, `remise`, `montant_remise`, `ristourne`, `montant_ristourne`, `montant_ht`, `montant_tva`, `montant_ttc`, `reductions_commerciales`, `net_commerciale`, `tva`) VALUES
(1, 1, 2, 'produit', 1, 'PRCOS01 Head&shoulders 30cl', 3, 4.99, 0, 0, 0, 0, 0, 0, 15, 3, 18, 0, 14.97, 0),
(2, 1, 3, 'service', 2, ' Développement site web de eKandra', 1, 550, 0, 0, 0, 0, 0, 0, 550, 110, 660, 0, 550, 0),
(5, 3, 1, 'produit', 1, 'PRAL01 Yaourt toplait', 4, 5, 0, 0, 2, 0.4, 0, 0, 20, 1, 21, 0.4, 19.6, 0),
(6, 3, 2, 'produit', 2, 'PRCOS01 Head&shoulders 30cl', 2, 4.99, 0, 0, 0, 0, 0, 0, 10, 2, 12, 0, 9.98, 0),
(7, 5, 0, 'autre', 1, 'charge suplementaire', 1, 100, 0, 0, 0, 0, 0, 0, 100, 20, 120, 0, 100, 20),
(8, 6, 1, 'produit', 1, 'PRAL01 Yaourt toplait', 4, 5, 0, 0, 10, 2, 0, 0, 18, 1, 19, 2, 18, 5.5),
(9, 6, 0, 'expedition', 3, 'livraison', 1, 15, 0, 0, 0, 0, 0, 0, 15, 3, 18, 0, 0, 20),
(10, 7, 2, 'produit', 1, 'PRCOS01 Head&shoulders 30cl', 3, 4.99, 0, 0, 0, 0, 0, 0, 15, 3, 18, 0, 14.97, 20),
(11, 7, 1, 'produit', 3, 'PRAL01 Yaourt toplait', 7, 5, 0, 0, 5, 1.75, 0, 0, 33, 2, 35, 1.75, 33.25, 5.5),
(12, 7, 0, 'expedition', 5, 'livraison et conditionnement', 1, 25, 0, 0, 0, 0, 0, 0, 25, 5, 30, 0, 0, 20),
(13, 9, 0, 'autre', 1, '3 verres cassés par le client', 3, 10, 0, 0, 0, 0, 0, 0, 30, 6, 36, 0, 30, 20),
(14, 10, 3, 'service', 1, ' Développement site web de eKandra', 1, 4500.99, 0, 0, 0, 0, 0, 0, 4501, 900, 5401, 0, 4500.99, 20),
(15, 11, 0, 'autre', 1, 'imprevu', 1, 100, 0, 0, 0, 0, 0, 0, 100, 19, 119, 0, 100, 19),
(16, 12, 1, 'produit', 1, 'PRAL01 Yaourt toplait', 6, 5, 0, 0, 0, 0, 0, 0, 30, 2, 32, 0, 30, 5.5),
(17, 13, 2, 'produit', 1, 'PRCOS01 Head&shoulders 30cl', 2, 4.99, 0, 0, 0, 0, 0, 0, 10, 2, 12, 0, 9.98, 20),
(18, 14, 2, 'produit', 1, 'PRCOS01 Head&shoulders 30cl', 1, 4.99, 0, 0, 0, 0, 0, 0, 5, 1, 6, 0, 4.99, 20),
(19, 15, 2, 'produit', 1, 'PRCOS01 Head&shoulders 30cl', 2, 4.99, 0, 0, 0, 0, 0, 0, 10, 2, 12, 0, 9.98, 20),
(20, 16, 1, 'produit', 2, 'PRAL01 Yaourt toplait', 1, 5, 0, 0, 0, 0, 0, 0, 5, 0, 5, 0, 5, 5.5);

-- --------------------------------------------------------

--
-- Structure de la table `paiement`
--

DROP TABLE IF EXISTS `paiement`;
CREATE TABLE IF NOT EXISTS `paiement` (
  `id_paiement` int(11) NOT NULL AUTO_INCREMENT,
  `facture_id` int(11) NOT NULL,
  `escompte_id` int(11) DEFAULT NULL COMMENT 'ce champ peut être vide s''il n''y a pas d''escompte',
  `date_paiement` date NOT NULL,
  `mode_paiement` varchar(255) NOT NULL,
  `montant_paiement` int(11) NOT NULL,
  PRIMARY KEY (`id_paiement`),
  KEY `fature_id` (`facture_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `paiement`
--

INSERT INTO `paiement` (`id_paiement`, `facture_id`, `escompte_id`, `date_paiement`, `mode_paiement`, `montant_paiement`) VALUES
(1, 6, 4, '2019-05-29', 'carte bancaire', 37),
(2, 1, 0, '2019-05-29', 'espece', 678),
(4, 3, 0, '2019-05-29', 'espece', 31),
(7, 5, 0, '2019-05-29', 'espece', 120),
(8, 7, 5, '2019-05-29', 'espece', 82),
(9, 9, 0, '2019-05-29', 'espece', 36),
(10, 10, 0, '2019-05-29', 'espece', 5401),
(11, 11, 0, '2019-05-29', 'espece', 119),
(12, 12, 0, '2019-05-29', 'espece', 32),
(13, 13, 0, '2019-05-29', 'espece', 12),
(14, 14, 0, '2019-05-29', 'espece', 6),
(15, 15, 0, '2019-05-29', 'espece', 12),
(16, 16, 6, '2019-06-03', 'carte bancaire', 5);

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

DROP TABLE IF EXISTS `produit`;
CREATE TABLE IF NOT EXISTS `produit` (
  `id_produit` int(11) NOT NULL AUTO_INCREMENT,
  `produit_type_id` int(11) NOT NULL,
  `famille_produit_id` int(11) NOT NULL,
  `devise_id` int(11) NOT NULL,
  `tva_id` int(11) NOT NULL,
  `reference_produit` varchar(255) NOT NULL,
  `designation_produit` varchar(255) NOT NULL,
  `montant_unitaire` double NOT NULL DEFAULT '0',
  `quantite_stock` int(11) NOT NULL DEFAULT '0',
  `unite` varchar(255) NOT NULL COMMENT 'Exemple: par pack, par pièce',
  `remise` double NOT NULL DEFAULT '0' COMMENT 'taux remise',
  PRIMARY KEY (`id_produit`),
  KEY `produitfk_tva_id` (`tva_id`),
  KEY `produitfk_famille_produit_id` (`famille_produit_id`),
  KEY `produitfk_produit_type_id` (`produit_type_id`),
  KEY `produitfk_devise_id` (`devise_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `produit`
--

INSERT INTO `produit` (`id_produit`, `produit_type_id`, `famille_produit_id`, `devise_id`, `tva_id`, `reference_produit`, `designation_produit`, `montant_unitaire`, `quantite_stock`, `unite`, `remise`) VALUES
(1, 1, 1, 1, 2, 'PRAL01', 'Yaourt toplait', 5, 95, 'pack(s)', 0),
(2, 1, 1, 1, 1, 'PRCOS01', 'Head&shoulders 30cl', 4.99, 50, 'pièce', 0),
(3, 2, 5, 1, 1, '', 'Développement site web de eKandra', 4500.99, 0, 'main d\'oeuvre', 0),
(4, 1, 1, 1, 2, 'PRAL02', 'Fromage Le President', 2, 23, '', 0);

-- --------------------------------------------------------

--
-- Structure de la table `produit_type`
--

DROP TABLE IF EXISTS `produit_type`;
CREATE TABLE IF NOT EXISTS `produit_type` (
  `id_produit_type` int(11) NOT NULL AUTO_INCREMENT,
  `designation_type` varchar(255) NOT NULL,
  PRIMARY KEY (`id_produit_type`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `produit_type`
--

INSERT INTO `produit_type` (`id_produit_type`, `designation_type`) VALUES
(1, 'produit'),
(2, 'service'),
(3, 'autre'),
(4, 'expedition');

-- --------------------------------------------------------

--
-- Structure de la table `profil`
--

DROP TABLE IF EXISTS `profil`;
CREATE TABLE IF NOT EXISTS `profil` (
  `id_profil` int(11) NOT NULL AUTO_INCREMENT,
  `designation_profil` varchar(255) NOT NULL,
  `commentaire` text NOT NULL,
  PRIMARY KEY (`id_profil`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `profil`
--

INSERT INTO `profil` (`id_profil`, `designation_profil`, `commentaire`) VALUES
(1, 'admin', 'administrateur du système '),
(2, 'commerciale', 'Des vendeurs qui établissent les factures'),
(3, 'comptable', 'Ceux qui comptabilisent les factures'),
(4, 'dirigeant', 'Les directeurs');

-- --------------------------------------------------------

--
-- Structure de la table `rabais`
--

DROP TABLE IF EXISTS `rabais`;
CREATE TABLE IF NOT EXISTS `rabais` (
  `id_rabais` int(11) NOT NULL AUTO_INCREMENT,
  `taux_rabais` double NOT NULL DEFAULT '0',
  `commentaires` varchar(255) NOT NULL,
  PRIMARY KEY (`id_rabais`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `test`
--

DROP TABLE IF EXISTS `test`;
CREATE TABLE IF NOT EXISTS `test` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `chaine` varchar(255) NOT NULL,
  `entier` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `test`
--

INSERT INTO `test` (`id`, `chaine`, `entier`) VALUES
(1, 'a', 42),
(2, 'b', 35),
(3, 'c', NULL),
(4, 'c3284d0f94606de1fd2af172aba15bf3', 0);

-- --------------------------------------------------------

--
-- Structure de la table `tva`
--

DROP TABLE IF EXISTS `tva`;
CREATE TABLE IF NOT EXISTS `tva` (
  `id_tva` int(11) NOT NULL AUTO_INCREMENT,
  `taux_tva` double NOT NULL DEFAULT '0' COMMENT 'en pourcentage',
  `remarque` varchar(255) NOT NULL,
  PRIMARY KEY (`id_tva`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `tva`
--

INSERT INTO `tva` (`id_tva`, `taux_tva`, `remarque`) VALUES
(1, 20, 'taux général en France'),
(2, 5.5, 'taux spécial pour les produits alimentaires en France');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `id_utilisateur` int(11) NOT NULL AUTO_INCREMENT,
  `profil_id` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `login` varchar(255) NOT NULL,
  `password` text NOT NULL,
  PRIMARY KEY (`id_utilisateur`),
  KEY `utilisateurfk_profile_id` (`profil_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id_utilisateur`, `profil_id`, `nom`, `prenom`, `login`, `password`) VALUES
(1, 1, 'Razafindrakoto', 'Mamy Sitraka', 'admin', '21232F297A57A5A743894A0E4A801FC3');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `client`
--
ALTER TABLE `client`
  ADD CONSTRAINT `clientfk_client_type_id` FOREIGN KEY (`client_type_id`) REFERENCES `client_type` (`id_client_type`);

--
-- Contraintes pour la table `escompte`
--
ALTER TABLE `escompte`
  ADD CONSTRAINT `escomptefk_facture_id` FOREIGN KEY (`facture_id`) REFERENCES `facture` (`id_facture`);

--
-- Contraintes pour la table `facture`
--
ALTER TABLE `facture`
  ADD CONSTRAINT `facturefk_client_id` FOREIGN KEY (`client_id`) REFERENCES `client` (`id_client`),
  ADD CONSTRAINT `facturefk_entreprise_id` FOREIGN KEY (`entreprise_id`) REFERENCES `entreprise` (`id_entreprise`);

--
-- Contraintes pour la table `ligne_facture`
--
ALTER TABLE `ligne_facture`
  ADD CONSTRAINT `ligne_facturefk_facture_id` FOREIGN KEY (`facture_id`) REFERENCES `facture` (`id_facture`) ON UPDATE CASCADE;

--
-- Contraintes pour la table `paiement`
--
ALTER TABLE `paiement`
  ADD CONSTRAINT `paiement_ibfk_1` FOREIGN KEY (`facture_id`) REFERENCES `facture` (`id_facture`);

--
-- Contraintes pour la table `produit`
--
ALTER TABLE `produit`
  ADD CONSTRAINT `produitfk_devise_id` FOREIGN KEY (`devise_id`) REFERENCES `devise` (`id_devise`),
  ADD CONSTRAINT `produitfk_famille_produit_id` FOREIGN KEY (`famille_produit_id`) REFERENCES `famille_produit` (`id_famille_produit`),
  ADD CONSTRAINT `produitfk_produit_type_id` FOREIGN KEY (`produit_type_id`) REFERENCES `produit_type` (`id_produit_type`),
  ADD CONSTRAINT `produitfk_tva_id` FOREIGN KEY (`tva_id`) REFERENCES `tva` (`id_tva`);

--
-- Contraintes pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD CONSTRAINT `utilisateurfk_profile_id` FOREIGN KEY (`profil_id`) REFERENCES `profil` (`id_profil`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
