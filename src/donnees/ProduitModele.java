package donnees;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import metier.Devise;
import metier.FamilleProduit;
import metier.Produit;
import metier.ProduitAbstrait;
import metier.ProduitType;
import metier.Service;
import metier.TVA;

/**
 *
 * @author Mamy Sitraka
 */
public class ProduitModele {

    private final Database db;
    private final Connection connexion;

    public ProduitModele() {
        db = new Database();
        connexion = db.getConnetion();
    }

    public ArrayList<ProduitAbstrait> getTousLesProduits() throws SQLException { //De type produit et service
        ArrayList<ProduitAbstrait> produits = null;
        PreparedStatement pst;
        ResultSet res;
        int id_produit, id_produit_type, id_tva, id_familly_produit, id_devise, quantite_stock;
        double taux_tva, montant_unitaire, remise;
        String designation_type, description, symbole, designation_famille, remarque, unite, reference_produit, designation_produit;
        //requete preparee pour récupérer tous les devises
        pst = connexion.prepareStatement("select * from produit as p join produit_type as pt on p.produit_type_id=pt.id_produit_type join famille_produit as fp on fp.id_famille_produit=p.famille_produit_id join tva as t on t.id_tva=p.tva_id join devise as d on d.id_devise=p.devise_id");

        res = pst.executeQuery();
        if (res != null) { //Tester si la requête retourne des résultats non vides
            produits = new ArrayList<>();

            while (res.next()) { //Parcourir tous les résultats

                id_tva = res.getInt("id_tva");
                taux_tva = res.getDouble("taux_tva");
                remarque = res.getString("remarque");
                id_familly_produit = res.getInt("id_famille_produit");
                designation_famille = res.getString("designation_famille");
                id_devise = res.getInt("id_devise");
                description = res.getString("description");
                symbole = res.getString("symbole");

                id_produit_type = res.getInt("id_produit_type");
                designation_type = res.getString("designation_type");
                id_produit = res.getInt("id_produit");
                montant_unitaire = res.getDouble("montant_unitaire");
                unite = res.getString("unite");
                remise = res.getDouble("remise");
                quantite_stock = res.getInt("quantite_stock");
                reference_produit = res.getString("reference_produit");
                designation_produit = res.getString("designation_produit");

                //Creation des objets
                Devise devise = new Devise(id_devise, description, symbole);
                TVA tva = new TVA(id_tva, taux_tva, remarque);
                FamilleProduit familleProduit = new FamilleProduit(id_familly_produit, designation_famille);
                ProduitType produitType = new ProduitType(id_produit_type, designation_type);
                if (designation_type.equals("produit")) {

                    Produit produit = new Produit(quantite_stock, id_produit, familleProduit, produitType, reference_produit, designation_produit, montant_unitaire, unite, remise, devise, tva);
                    produits.add(produit);

                } else if (designation_type.equals("service")) {

                    Service service = new Service(id_produit, familleProduit, produitType, reference_produit, designation_produit, montant_unitaire, unite, remise, devise, tva);
                    produits.add(service);

                }

            }

        }
        return produits;
    }

    public ArrayList<Produit> getTousProduits() throws SQLException { //Juste le type produit
        ArrayList<Produit> produits = null;
        PreparedStatement pst;
        ResultSet res;
        int id_produit, id_produit_type, id_tva, id_familly_produit, id_devise, quantite_stock;
        double taux_tva, montant_unitaire, remise;
        String designation_type = null, description, symbole, designation_famille, remarque, unite, reference_produit, designation_produit;
        //requete preparee pour récupérer tous les devises
        pst = connexion.prepareStatement("select * from produit as p join produit_type as pt on p.produit_type_id=pt.id_produit_type join famille_produit as fp on fp.id_famille_produit=p.famille_produit_id join tva as t on t.id_tva=p.tva_id join devise as d on d.id_devise=p.devise_id where pt.designation_type=produit");

        res = pst.executeQuery();
        if (res != null) { //Tester si la requête retourne des résultats non vides
            produits = new ArrayList<>();

            while (res.next()) { //Parcourir tous les résultats     

                //Attributs des autres abjets liés à la classe produit
                id_tva = res.getInt("id_tva");
                taux_tva = res.getDouble("taux_tva");
                remarque = res.getString("remarque");
                id_familly_produit = res.getInt("id_famille_produit");
                designation_famille = res.getString("designation_famille");
                id_devise = res.getInt("id_devise");
                description = res.getString("description");
                symbole = res.getString("symbole");

                /*Recuperation des attributs de l'objet Produit*/
                id_produit_type = res.getInt("id_produit_type");
                designation_type = res.getString("designation_type");
                id_produit = res.getInt("id_produit");
                montant_unitaire = res.getDouble("montant_unitaire");
                unite = res.getString("unite");
                remise = res.getDouble("remise");
                quantite_stock = res.getInt("quantite_stock");
                reference_produit = res.getString("reference_produit");
                designation_produit = res.getString("designation_produit");

                //Creation des objets
                Devise devise = new Devise(id_devise, description, symbole);
                TVA tva = new TVA(id_tva, taux_tva, remarque);
                FamilleProduit familleProduit = new FamilleProduit(id_familly_produit, designation_famille);
                ProduitType produitType = new ProduitType(id_produit_type, designation_type);
                Produit produit = new Produit(quantite_stock, id_produit, familleProduit, produitType, reference_produit, designation_produit, montant_unitaire, unite, remise, devise, tva);

                produits.add(produit);
            }

        }
        return produits;
    }

    public Produit getProduit(int id_produit) throws SQLException {
        Produit produit = null;
        PreparedStatement pst;
        ResultSet res;
        int id_produit_type, id_tva, id_familly_produit, id_devise, quantite_stock;
        double taux_tva, montant_unitaire, remise;
        String designation_type = null, description, symbole, designation_famille, remarque, unite, reference_produit, designation_produit;
        //requete preparee pour récupérer tous les devises
        pst = connexion.prepareStatement("select * from produit as p join produit_type as pt on p.produit_type_id=pt.id_produit_type join famille_produit as fp on fp.id_famille_produit=p.famille_produit_id join tva as t on t.id_tva=p.tva_id join devise as d on d.id_devise=p.devise_id where p.id_produit=?");
        pst.setInt(1, id_produit);

        res = pst.executeQuery();
        if (res != null) { //Tester si la requête retourne des résultats non vides
            while (res.next()) { //Parcourir tous les résultats
                //Attributs des autres abjets liés à la classe produit
                id_tva = res.getInt("id_tva");
                taux_tva = res.getDouble("taux_tva");
                remarque = res.getString("remarque");
                id_familly_produit = res.getInt("id_famille_produit");
                designation_famille = res.getString("designation_famille");
                id_devise = res.getInt("id_devise");
                description = res.getString("description");
                symbole = res.getString("symbole");

                /*Recuperation des attributs de l'objet Produit*/
                id_produit_type = res.getInt("id_produit_type");
                designation_type = res.getString("designation_type");
                montant_unitaire = res.getDouble("montant_unitaire");
                unite = res.getString("unite");
                remise = res.getDouble("remise");
                quantite_stock = res.getInt("quantite_stock");
                reference_produit = res.getString("reference_produit");
                designation_produit = res.getString("designation_produit");

                //Creation des objets
                Devise devise = new Devise(id_devise, description, symbole);
                TVA tva = new TVA(id_tva, taux_tva, remarque);
                FamilleProduit familleProduit = new FamilleProduit(id_familly_produit, designation_famille);
                ProduitType produitType = new ProduitType(id_produit_type, designation_type);

                produit = new Produit(quantite_stock, id_produit, familleProduit, produitType, reference_produit, designation_produit, montant_unitaire, unite, remise, devise, tva);
            }

        }
        return produit;
    }

    public ArrayList<Service> getTousServices() throws SQLException {
        ArrayList<Service> services = new ArrayList<>();
        PreparedStatement pst;
        ResultSet res;
        int id_produit, id_produit_type, id_tva, id_familly_produit, id_devise;
        double taux_tva, montant_unitaire, remise;
        String designation_type = null, description, symbole, designation_famille, remarque, unite, reference_produit, designation_produit;
        //requete preparee pour récupérer tous les devises
        pst = connexion.prepareStatement("select * from produit as p join produit_type as pt on p.produit_type_id=pt.id_produit_type join famille_produit as fp on fp.id_famille_produit=p.famille_produit_id join tva as t on t.id_tva=p.tva_id join devise as d on d.id_devise=p.devise_id where pt.designation_type=produit");

        res = pst.executeQuery();
        if (res != null) { //Tester si la requête retourne des résultats non vides
            while (res.next()) { //Parcourir tous les résultats
                //Attributs des autres abjets liés à la classe produit
                id_tva = res.getInt("id_tva");
                taux_tva = res.getDouble("taux_tva");
                remarque = res.getString("remarque");
                id_familly_produit = res.getInt("id_famille_produit");
                designation_famille = res.getString("designation_famille");
                id_devise = res.getInt("id_devise");
                description = res.getString("description");
                symbole = res.getString("symbole");

                /*Recuperation des attributs de l'objet Service*/
                id_produit_type = res.getInt("id_produit_type");
                designation_type = res.getString("designation_type");
                id_produit = res.getInt("id_produit");
                montant_unitaire = res.getDouble("montant_unitaire");
                unite = res.getString("unite");
                remise = res.getDouble("remise");
                reference_produit = res.getString("reference_produit");
                designation_produit = res.getString("designation_produit");

                //Creation des objets
                Devise devise = new Devise(id_devise, description, symbole);
                TVA tva = new TVA(id_tva, taux_tva, remarque);
                FamilleProduit familleProduit = new FamilleProduit(id_familly_produit, designation_famille);
                ProduitType produitType = new ProduitType(id_produit_type, designation_type);
                Service service = new Service(id_produit, familleProduit, produitType, reference_produit, designation_produit, montant_unitaire, unite, remise, devise, tva);

                services.add(service);
            }

        } else {

            JOptionPane.showMessageDialog(null, "Il n'y a aucun service dans la base de données !", "Erreur",
                    JOptionPane.ERROR_MESSAGE);
        }
        return services;
    }

    public Service getService(int id_produit) throws SQLException {
        Service service = null;
        PreparedStatement pst;
        ResultSet res;
        int id_produit_type, id_tva, id_familly_produit, id_devise;
        double taux_tva, montant_unitaire, remise;
        String designation_type = null, description, symbole, designation_famille, remarque, unite, reference_produit, designation_produit;
        //requete preparee pour récupérer tous les devises
        pst = connexion.prepareStatement("select * from produit as p join produit_type as pt on p.produit_type_id=pt.id_produit_type join famille_produit as fp on fp.id_famille_produit=p.famille_produit_id join tva as t on t.id_tva=p.tva_id join devise as d on d.id_devise=p.devise_id where p.id_produit=?");
        pst.setInt(1, id_produit);

        res = pst.executeQuery();
        if (res != null) { //Tester si la requête retourne des résultats non vides
            while (res.next()) { //Parcourir tous les résultats
                //Attributs des autres abjets liés à la classe produit
                id_tva = res.getInt("id_tva");
                taux_tva = res.getDouble("taux_tva");
                remarque = res.getString("remarque");
                id_familly_produit = res.getInt("id_famille_produit");
                designation_famille = res.getString("designation_famille");
                id_devise = res.getInt("id_devise");
                description = res.getString("description");
                symbole = res.getString("symbole");

                /*Recuperation des attributs de l'objet Produit*/
                id_produit_type = res.getInt("id_produit_type");
                designation_type = res.getString("designation_type");
                montant_unitaire = res.getDouble("montant_unitaire");
                unite = res.getString("unite");
                remise = res.getDouble("remise");
                reference_produit = res.getString("reference_produit");
                designation_produit = res.getString("designation_produit");

                //Creation des objets
                Devise devise = new Devise(id_devise, description, symbole);
                TVA tva = new TVA(id_tva, taux_tva, remarque);
                FamilleProduit familleProduit = new FamilleProduit(id_familly_produit, designation_famille);
                ProduitType produitType = new ProduitType(id_produit_type, designation_type);

                service = new Service(id_produit, familleProduit, produitType, reference_produit, designation_produit, montant_unitaire, unite, remise, devise, tva);
            }

        }
        return service;
    }

    public boolean supprimerProduit(int id_produit) {
        try {
            PreparedStatement pst;

            pst = connexion.prepareStatement("delete from produit where id_produit=?");
            pst.setInt(1, id_produit);

            if (pst.executeUpdate() <= 0) {
                JOptionPane.showMessageDialog(null, "Erreur d'execution de suppression du produit dans la base de données !", "Erreur",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(FactureModele.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erreur de suppression du produit !", "Erreur",
                    JOptionPane.ERROR_MESSAGE);
        }
        return true;
    }

    public ArrayList<Devise> getTousDevises() throws SQLException {
        ArrayList<Devise> devises = null;
        PreparedStatement pst;
        ResultSet res;
        int id;
        String description, symbole;
        //requete preparee pour récupérer tous les devises
        pst = connexion.prepareStatement("select * from devise");

        res = pst.executeQuery();
        if (res != null) { //Tester si la requête retourne des résultats non vides
            devises = new ArrayList<>();
            while (res.next()) { //Parcourir tous les résultats
                id = res.getInt("id_devise");
                description = res.getString("description");
                symbole = res.getString("symbole");

                Devise devise = new Devise(id, description, symbole); //Création d'un objet devise
                devises.add(devise);
            }

            return devises;
        }

        return devises;
    }

    public ArrayList<ProduitType> getTousTypes() throws SQLException {
        ArrayList<ProduitType> produitTypes = null;
        PreparedStatement pst;
        ResultSet res;
        int id_produit_type;
        String designation_type;
        //requete preparee pour récupérer tous les devises
        pst = connexion.prepareStatement("select * from produit_type");

        res = pst.executeQuery();
        if (res != null) { //Tester si la requête retourne des résultats non vides
            produitTypes = new ArrayList<>();
            while (res.next()) { //Parcourir tous les résultats
                id_produit_type = res.getInt("id_produit_type");
                designation_type = res.getString("designation_type");

                ProduitType produitType = new ProduitType(id_produit_type, designation_type); //Création d'un objet ProduitType
                produitTypes.add(produitType);
            }

            return produitTypes;
        }

        return produitTypes;
    }

    public ArrayList<FamilleProduit> getToutesFamilles() throws SQLException {
        ArrayList<FamilleProduit> familleProduits = null;
        PreparedStatement pst;
        ResultSet res;
        int id_famille_produit;
        String designation_famille;
        //requete preparee pour récupérer toutes les familles
        pst = connexion.prepareStatement("select * from famille_produit");

        res = pst.executeQuery();
        if (res != null) { //Tester si la requête retourne des résultats non vides
            familleProduits = new ArrayList<>();
            while (res.next()) { //Parcourir tous les résultats
                id_famille_produit = res.getInt("id_famille_produit");
                designation_famille = res.getString("designation_famille");

                FamilleProduit famille = new FamilleProduit(id_famille_produit, designation_famille); //Création d'un objet FamilleProduit
                familleProduits.add(famille);
            }

            return familleProduits;
        }

        return familleProduits;
    }

    public boolean enregistrerFamille(String designation_famille) {
        try {
            PreparedStatement pst;

            pst = connexion.prepareStatement("insert into famille_produit (designation_famille) values (?)");
            pst.setString(1, designation_famille);

            //Insertion dans la table utilisateur
            if (pst.executeUpdate() <= 0) {
                JOptionPane.showMessageDialog(null, "Erreur d'execution de l'enregistrement de la famille de produit dans la base de données !", "Erreur",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(FactureModele.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erreur de l'enregistrement de la famille !", "Erreur",
                    JOptionPane.ERROR_MESSAGE);
        }
        return true;
    }

    public FamilleProduit getFamille(String designation_famille) throws SQLException {
        FamilleProduit famille = null;
        PreparedStatement pst;
        ResultSet res;
        int id_famille_produit;
        //requete preparee pour récupérer la famille d'un produit par rapport à son designation
        pst = connexion.prepareStatement("select * from famille_produit where designation_famille=?");
        pst.setString(1, designation_famille);

        res = pst.executeQuery();
        if (res != null) { //Tester si la requête retourne des résultats non vides
            while (res.next()) {
                //Attributs des autres abjets liés à la classe produit
                id_famille_produit = res.getInt("id_famille_produit");

                famille = new FamilleProduit(id_famille_produit, designation_famille); //Création d'un objet FamilleProduit
                break;
            }

        }
        return famille;
    }

    public boolean isFamilleExiste(String designation_famille) throws SQLException {
        PreparedStatement pst;
        ResultSet res;

        pst = connexion.prepareStatement("select * from famille_produit where designation_famille=?");
        pst.setString(1, designation_famille);
        res = pst.executeQuery();
        if (res.next()) {
            return true;
        }
        return false; // si res est null la fonction retourne false ou faux
    }

    public ArrayList<TVA> getTousTVA() throws SQLException {
        ArrayList<TVA> TVAs = null;
        PreparedStatement pst;
        ResultSet res;
        int id_tva;
        double taux_tva;
        String remarque;
        //requete preparee pour récupérer toutes les familles
        pst = connexion.prepareStatement("select * from tva");

        res = pst.executeQuery();
        if (res != null) { //Tester si la requête retourne des résultats non vides
            TVAs = new ArrayList<>();
            while (res.next()) { //Parcourir tous les résultats
                id_tva = res.getInt("id_tva");
                taux_tva = res.getDouble("taux_tva");
                remarque = res.getString("remarque");

                TVA tva = new TVA(id_tva, taux_tva, remarque); //Création d'un objet FamilleProduit
                TVAs.add(tva);
            }

            return TVAs;
        }

        return TVAs;
    }

    public int getQuantiteStock(int id_produit) throws SQLException {
        PreparedStatement pst;
        ResultSet res;

        //requete preparee pour récupérer toutes les familles
        pst = connexion.prepareStatement("select quantite_stock from produit where id_produit=?");

        pst.setInt(1, id_produit);

        res = pst.executeQuery();
        if (res != null) { //Tester si la requête retourne des résultats non vides
            while (res.next()) { //Parcourir tous les résultats
                return res.getInt("quantite_stock");
            }
        }

        return -1;
    }

    public boolean miseAJourStock(int id_produit, int nouvelleQuantite) throws SQLException {
        PreparedStatement pst;

        pst = connexion.prepareStatement("update produit set quantite_stock=? where id_produit=?");

        pst.setInt(1, nouvelleQuantite);
        pst.setInt(2, id_produit);

        if (pst.executeUpdate() <= 0) {
            JOptionPane.showMessageDialog(null, "Erreur d'execution de la modification du client dans la base de données !", "Erreur",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    public boolean enregistrerProduit(ProduitAbstrait produit) {
        try {
            PreparedStatement pst;

            pst = connexion.prepareStatement("insert into produit (produit_type_id,famille_produit_id,devise_id,tva_id,reference_produit,designation_produit,montant_unitaire,quantite_stock,unite,remise) values (?,?,?,?,?,?,?,?,?,?)");

            if (produit.getProduit_type() != null) {

                if (produit.getFamille() != null) {

                    if (produit.getDevise() != null) {

                        if (produit.getTva() != null) {

                            pst.setInt(1, produit.getProduit_type().getId());
                            pst.setInt(2, produit.getFamille().getId());
                            pst.setInt(3, produit.getDevise().getId());
                            pst.setInt(4, produit.getTva().getId());
                            pst.setString(5, produit.getReference());
                            pst.setString(6, produit.getDesignation());
                            pst.setDouble(7, produit.getMontant_unitaire());
                            if (produit instanceof Produit) {
                                Produit article = (Produit) produit;
                                pst.setInt(8, article.getQuantite_stock());
                            } else {
                                pst.setInt(8, Types.NULL);
                            }
                            pst.setString(9, produit.getUnite());
                            pst.setDouble(10, produit.getRemise());

                        } else {
                            JOptionPane.showMessageDialog(null, "TVA inconnu pour l'enregistrement du produit !", "Erreur",
                                    JOptionPane.ERROR_MESSAGE);
                            return false;
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Devise inconnue pour l'enregistrement du produit !", "Erreur",
                                JOptionPane.ERROR_MESSAGE);
                        return false;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Famille de produit inconnue pour l'enregistrement !", "Erreur",
                            JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Type de produit inconnu pour l'enregistrement !", "Erreur",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }
            //Insertion dans la table utilisateur
            if (pst.executeUpdate() <= 0) {
                JOptionPane.showMessageDialog(null, "Erreur d'execution de l'enregistrement du produit dans la base de données !", "Erreur",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(FactureModele.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erreur de l'enregistrement du produit !", "Erreur",
                    JOptionPane.ERROR_MESSAGE);
        }
        return true;
    }

    public boolean modifierProduit(ProduitAbstrait produit) {
        try {
            PreparedStatement pst;

            pst = connexion.prepareStatement("update produit set produit_type_id=?, famille_produit_id=?, devise_id=?, tva_id=?, reference_produit=?, designation_produit=?, montant_unitaire=?, unite=?, remise=? where id_produit=?");

            if (produit.getProduit_type() != null) {

                if (produit.getFamille() != null) {

                    if (produit.getDevise() != null) {

                        if (produit.getTva() != null) {

                            pst.setInt(1, produit.getProduit_type().getId());
                            pst.setInt(2, produit.getFamille().getId());
                            pst.setInt(3, produit.getDevise().getId());
                            pst.setInt(4, produit.getTva().getId());
                            pst.setString(5, produit.getReference());
                            pst.setString(6, produit.getDesignation());
                            pst.setDouble(7, produit.getMontant_unitaire());
                            pst.setString(8, produit.getUnite());
                            pst.setDouble(9, produit.getRemise());
                            pst.setInt(10, produit.getId());

                        } else {
                            JOptionPane.showMessageDialog(null, "TVA inconnu lors de la modification du produit !", "Erreur",
                                    JOptionPane.ERROR_MESSAGE);
                            return false;
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Devise inconnue lors de la modification du produit !", "Erreur",
                                JOptionPane.ERROR_MESSAGE);
                        return false;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Famille de produit inconuue lors de la modification du produit !", "Erreur",
                            JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Type de produit inconnu lors de la modification du produit !", "Erreur",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }
            //Insertion dans la table produit
            if (pst.executeUpdate() <= 0) {
                JOptionPane.showMessageDialog(null, "Erreur d'execution de la modification du produit dans la base de données !", "Erreur",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(FactureModele.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erreur de la modification du produit !", "Erreur",
                    JOptionPane.ERROR_MESSAGE);
        }
        return true;
    }

    public boolean miseAJourTVA(TVA tva) throws SQLException {
        PreparedStatement pst;

        pst = connexion.prepareStatement("update tva set taux_tva=?, remarque=? where id_tva=?");

        pst.setDouble(1, tva.getTaux());
        pst.setString(2, tva.getDescription());
        pst.setInt(3, tva.getId());

        if (pst.executeUpdate() <= 0) {
            JOptionPane.showMessageDialog(null, "Erreur d'execution de la modification du TVA dans la base de données !", "Erreur",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    public boolean enregistrerTVA(TVA tva) {
        try {
            PreparedStatement pst;

            pst = connexion.prepareStatement("insert into tva (taux_tva,remarque) values (?,?)");

            pst.setDouble(1, tva.getTaux());
            pst.setString(2, tva.getDescription());

            //Insertion dans la table TVA
            if (pst.executeUpdate() <= 0) {
                JOptionPane.showMessageDialog(null, "Erreur d'execution de l'enregistrement du TVA dans la base de données !", "Erreur",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(FactureModele.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erreur de l'enregistrement du TVA !", "Erreur",
                    JOptionPane.ERROR_MESSAGE);
        }
        return true;
    }
}
