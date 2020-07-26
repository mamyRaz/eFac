package donnees;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import metier.Client;
import metier.Escompte;
import metier.Facture;
import metier.LigneExpedition;
import metier.LigneFacture;
import metier.LigneProduit;
import metier.Paiement;
import metier.Produit;
import metier.Rabais;
import metier.Remise;
import metier.Ristourne;
import metier.Service;

/**
 *
 * @author Mamy Sitraka
 */
public class FactureModele {

    private final Database db;
    private final Connection connexion;

    public FactureModele() {
        db = new Database();
        connexion = db.getConnetion();
    }

    public ArrayList<Facture> getTousFacture() throws SQLException {
        ArrayList<Facture> factures = null;
        PreparedStatement pst;
        ResultSet res;

        /*
            les attributs de la facture
         */
        int id_facture, client_id, entreprise_id, numero_facture;
        String facture_type, etat_facture, remarques;
        LocalDate date_facture, date_delais;
        double reductions_commerciales, reductions_financieres, total_ht, total_tva, total_ttc, acompte, netAPayer, net_commerciale, net_financier, solde_impayees, solde_payees;

        //requete preparee pour récupérer toutes les factures
        pst = connexion.prepareStatement("select * from facture order by numero_facture desc");

        res = pst.executeQuery();
        if (res != null) { //Tester si la requête retourne des résultats non vides
            ClientModele clientModele = new ClientModele();
            EntrepriseModele entrepriseModele = new EntrepriseModele();
            factures = new ArrayList<>();

            while (res.next()) { //Parcourir tous les résultats
                id_facture = res.getInt("id_facture");
                client_id = res.getInt("client_id");
                entreprise_id = res.getInt("entreprise_id");
                facture_type = res.getString("facture_type");
                numero_facture = res.getInt("numero_facture");
                date_facture = res.getDate("date_facture").toLocalDate();
                date_delais = res.getDate("date_delais").toLocalDate();
                etat_facture = res.getString("etat_facture");
                reductions_commerciales = res.getDouble("reductions_commerciales");
                reductions_financieres = res.getDouble("reductions_financieres");
                total_ht = res.getDouble("total_ht");
                total_tva = res.getDouble("total_tva");
                total_ttc = res.getDouble("total_ttc");
                acompte = res.getDouble("acompte");
                netAPayer = res.getDouble("netAPayer");
                remarques = res.getString("remarques");
                net_commerciale = res.getDouble("net_commerciale");
                net_financier = res.getDouble("net_financier");
                solde_impayees = res.getDouble("solde_impayees");
                solde_payees = res.getDouble("solde_payees");

                Facture facture = new Facture(id_facture, numero_facture, facture_type, date_facture, date_delais, remarques, solde_impayees, solde_payees, reductions_commerciales, net_commerciale, reductions_financieres, net_financier, total_ht, total_tva, total_ttc, acompte, netAPayer, etat_facture);

                facture.setLignes(getLigne(id_facture));
                facture.setClient(clientModele.getClient(client_id));
                facture.setEntreprise(entrepriseModele.getEntreprise(entreprise_id));
                if (getTousEscomptes(id_facture) != null) {
                    facture.setEscomptes(getTousEscomptes(id_facture));
                }
                if (getTousPaiements(id_facture) != null) {
                    facture.setPaiements(getTousPaiements(id_facture));
                }

                factures.add(facture);
            }

        }
        return factures;
    }

    public ArrayList<Facture> getFacturePayees(int client_id) throws SQLException {
        ArrayList<Facture> factures = null;
        PreparedStatement pst;
        ResultSet res;

        /*
            les attributs de la facture
         */
        int id_facture, entreprise_id, numero_facture;
        String facture_type, etat_facture, remarques;
        LocalDate date_facture, date_delais;
        double reductions_commerciales, reductions_financieres, total_ht, total_tva, total_ttc, acompte, netAPayer, net_commerciale, net_financier, solde_impayees, solde_payees;

        //requete preparee pour récupérer toutes les factures
        pst = connexion.prepareStatement("select * from facture where client_id=? and etat_facture='payee' order by numero_facture desc");
        pst.setInt(1, client_id);

        res = pst.executeQuery();
        if (res != null) { //Tester si la requête retourne des résultats non vides
            ClientModele clientModele = new ClientModele();
            EntrepriseModele entrepriseModele = new EntrepriseModele();
            factures = new ArrayList<>();

            while (res.next()) { //Parcourir tous les résultats
                id_facture = res.getInt("id_facture");
                entreprise_id = res.getInt("entreprise_id");
                facture_type = res.getString("facture_type");
                numero_facture = res.getInt("numero_facture");
                date_facture = res.getDate("date_facture").toLocalDate();
                date_delais = res.getDate("date_delais").toLocalDate();
                etat_facture = res.getString("etat_facture");
                reductions_commerciales = res.getDouble("reductions_commerciales");
                reductions_financieres = res.getDouble("reductions_financieres");
                total_ht = res.getDouble("total_ht");
                total_tva = res.getDouble("total_tva");
                total_ttc = res.getDouble("total_ttc");
                acompte = res.getDouble("acompte");
                netAPayer = res.getDouble("netAPayer");
                remarques = res.getString("remarques");
                net_commerciale = res.getDouble("net_commerciale");
                net_financier = res.getDouble("net_financier");
                solde_impayees = res.getDouble("solde_impayees");
                solde_payees = res.getDouble("solde_payees");

                Facture facture = new Facture(id_facture, numero_facture, facture_type, date_facture, date_delais, remarques, solde_impayees, solde_payees, reductions_commerciales, net_commerciale, reductions_financieres, net_financier, total_ht, total_tva, total_ttc, acompte, netAPayer, etat_facture);

                facture.setLignes(getLigne(id_facture));
                facture.setClient(clientModele.getClient(client_id));
                facture.setEntreprise(entrepriseModele.getEntreprise(entreprise_id));
                if (getTousEscomptes(id_facture) != null) {
                    facture.setEscomptes(getTousEscomptes(id_facture));
                }
                if (getTousPaiements(id_facture) != null) {
                    facture.setPaiements(getTousPaiements(id_facture));
                }

                factures.add(facture);
            }

        }
        return factures;
    }

    public ArrayList<Facture> getFactureImpayees(int client_id) throws SQLException {
        ArrayList<Facture> factures = null;
        PreparedStatement pst;
        ResultSet res;

        /*
            les attributs de la facture
         */
        int id_facture, entreprise_id, numero_facture;
        String facture_type, etat_facture, remarques;
        LocalDate date_facture, date_delais;
        double reductions_commerciales, reductions_financieres, total_ht, total_tva, total_ttc, acompte, netAPayer, net_commerciale, net_financier, solde_impayees, solde_payees;

        //requete preparee pour récupérer toutes les factures
        pst = connexion.prepareStatement("select * from facture where client_id=? and etat_facture='impayee' order by numero_facture desc");
        pst.setInt(1, client_id);

        res = pst.executeQuery();
        if (res != null) { //Tester si la requête retourne des résultats non vides
            ClientModele clientModele = new ClientModele();
            EntrepriseModele entrepriseModele = new EntrepriseModele();
            factures = new ArrayList<>();

            while (res.next()) { //Parcourir tous les résultats
                id_facture = res.getInt("id_facture");
                entreprise_id = res.getInt("entreprise_id");
                facture_type = res.getString("facture_type");
                numero_facture = res.getInt("numero_facture");
                date_facture = res.getDate("date_facture").toLocalDate();
                date_delais = res.getDate("date_delais").toLocalDate();
                etat_facture = res.getString("etat_facture");
                reductions_commerciales = res.getDouble("reductions_commerciales");
                reductions_financieres = res.getDouble("reductions_financieres");
                total_ht = res.getDouble("total_ht");
                total_tva = res.getDouble("total_tva");
                total_ttc = res.getDouble("total_ttc");
                acompte = res.getDouble("acompte");
                netAPayer = res.getDouble("netAPayer");
                remarques = res.getString("remarques");
                net_commerciale = res.getDouble("net_commerciale");
                net_financier = res.getDouble("net_financier");
                solde_impayees = res.getDouble("solde_impayees");
                solde_payees = res.getDouble("solde_payees");

                Facture facture = new Facture(id_facture, numero_facture, facture_type, date_facture, date_delais, remarques, solde_impayees, solde_payees, reductions_commerciales, net_commerciale, reductions_financieres, net_financier, total_ht, total_tva, total_ttc, acompte, netAPayer, etat_facture);

                facture.setLignes(getLigne(id_facture));
                facture.setClient(clientModele.getClient(client_id));
                facture.setEntreprise(entrepriseModele.getEntreprise(entreprise_id));
                if (getTousEscomptes(id_facture) != null) {
                    facture.setEscomptes(getTousEscomptes(id_facture));
                }
                if (getTousPaiements(id_facture) != null) {
                    facture.setPaiements(getTousPaiements(id_facture));
                }

                factures.add(facture);
            }

        }
        return factures;
    }

    public ArrayList<LigneFacture> getLigne(int id_facture) throws SQLException {
        ArrayList<LigneFacture> lignes = null;
        ProduitModele produitModele;
        PreparedStatement pst;
        ResultSet res;

        /*
            les attributs de les lignes de la facture
         */
        int id_ligne_facture, produit_id, numero_ligne, quantite;
        double montant_unitaire, montant_tva, rabais, remise, ristourne, montant_rabais, montant_remise, montant_ristourne, montant_ht, montant_ttc, reductions_commerciales, net_commerciale, tva;
        String ligne_type, designation_ligne;
        /*
            les attributs du produit
         */
        String designation_type;

        pst = connexion.prepareStatement("select * from ligne_facture as l left join produit as p on l.produit_id=p.id_produit left join produit_type as pt on p.produit_type_id=pt.id_produit_type left join famille_produit as fp on p.famille_produit_id=fp.id_famille_produit left join devise as d on p.devise_id=d.id_devise left join tva as t on p.tva_id=t.id_tva where l.facture_id=?");
        pst.setInt(1, id_facture);
        res = pst.executeQuery();
        if (res != null) { //Tester si la requête retourne des résultats non vides
            lignes = new ArrayList<>();

            while (res.next()) {

                ligne_type = res.getString("ligne_type");
                if (ligne_type.equals("produit")) {
                    LigneProduit ligneProduit = new LigneProduit();

                    id_ligne_facture = res.getInt("id_ligne_facture");
                    ligneProduit.setId(id_ligne_facture);

                    ligneProduit.setLigneType(ligne_type);

                    produit_id = res.getInt("produit_id");
                    numero_ligne = res.getInt("numero_ligne");
                    ligneProduit.setNumero(numero_ligne);

                    quantite = res.getInt("quantite");
                    ligneProduit.setQuantite(quantite);

                    montant_unitaire = res.getDouble("montant_unitaire");
                    ligneProduit.setMontantUnitaire(montant_unitaire);

                    montant_tva = res.getDouble("montant_tva");
                    ligneProduit.setMontantTVA(montant_tva);

                    rabais = res.getDouble("rabais");
                    ligneProduit.setRabais(rabais);

                    montant_rabais = res.getDouble("montant_rabais");
                    ligneProduit.setMontantRabais(montant_rabais);

                    remise = res.getDouble("remise");
                    ligneProduit.setRemise(remise);

                    montant_remise = res.getDouble("montant_remise");
                    ligneProduit.setMontantRemise(montant_remise);

                    ristourne = res.getDouble("ristourne");
                    ligneProduit.setRistourne(ristourne);

                    montant_ristourne = res.getDouble("montant_ristourne");
                    ligneProduit.setMontantRistourne(montant_ristourne);

                    montant_ht = res.getDouble("montant_ht");
                    ligneProduit.setMontantHT(montant_ht);

                    montant_ttc = res.getDouble("montant_ttc");
                    ligneProduit.setMontantTTC(montant_ttc);

                    reductions_commerciales = res.getDouble("reductions_commerciales");
                    ligneProduit.setReductionsCommerciales(reductions_commerciales);

                    net_commerciale = res.getDouble("net_commerciale");
                    ligneProduit.setNetCommercial(net_commerciale);

                    designation_ligne = res.getString("designation_ligne");
                    ligneProduit.setDesignation(designation_ligne);

                    tva = res.getDouble("tva");
                    ligneProduit.setTva(tva);

                    if (produit_id != 0) { //Si la ligne facture a un produit dans la base de données
                        produitModele = new ProduitModele();
                        designation_type = res.getString("designation_type");

                        if (designation_type.equals("produit")) {

                            Produit produit = produitModele.getProduit(produit_id);
                            ligneProduit.setProduit(produit);

                            lignes.add(ligneProduit);

                        } else if (designation_type.equals("service")) {
                            Service service = produitModele.getService(produit_id);
                            ligneProduit.setProduit(service);

                            lignes.add(ligneProduit);
                        }
                    } else {

                        lignes.add(ligneProduit);
                    }
                } else if (ligne_type.equals("expedition")) {

                    LigneExpedition ligneExpedition = new LigneExpedition();

                    id_ligne_facture = res.getInt("id_ligne_facture");
                    ligneExpedition.setId(id_ligne_facture);

                    ligneExpedition.setLigneType(ligne_type);

                    numero_ligne = res.getInt("numero_ligne");
                    ligneExpedition.setNumero(numero_ligne);

                    quantite = res.getInt("quantite");
                    ligneExpedition.setQuantite(quantite);

                    montant_unitaire = res.getDouble("montant_unitaire");
                    ligneExpedition.setMontantUnitaire(montant_unitaire);

                    montant_tva = res.getDouble("montant_tva");
                    ligneExpedition.setMontantTVA(montant_tva);

                    montant_ht = res.getDouble("montant_ht");
                    ligneExpedition.setMontantHT(montant_ht);

                    montant_ttc = res.getDouble("montant_ttc");
                    ligneExpedition.setMontantTTC(montant_ttc);

                    designation_ligne = res.getString("designation_ligne");
                    ligneExpedition.setDesignation(designation_ligne);

                    tva = res.getDouble("tva");
                    ligneExpedition.setTva(tva);

                    lignes.add(ligneExpedition);
                }

            } //fin while

        } //fin premier if

        return lignes;
    }

    public ArrayList<Escompte> getTousEscomptes(int id_facture) throws SQLException {
        ArrayList<Escompte> escomptes = null;
        PreparedStatement pst;
        ResultSet res;
        int id_escompte, echeance_jour;
        LocalDate echeance_date;
        double taux_escompte;
        //requete preparee pour récupérer tous les devises
        pst = connexion.prepareStatement("select * from escompte as e where e.facture_id=?");
        pst.setInt(1, id_facture);

        res = pst.executeQuery();
        if (res != null) { //Tester si la requête retourne des résultats non vides
            escomptes = new ArrayList<>();

            while (res.next()) { //Parcourir tous les résultats
                id_escompte = res.getInt("id_escompte");
                echeance_jour = res.getInt("echeance_jour");
                echeance_date = res.getDate("echeance_date").toLocalDate();
                taux_escompte = res.getDouble("taux_escompte");

                Escompte escompte = new Escompte(id_escompte, echeance_jour, echeance_date, taux_escompte);
                escomptes.add(escompte);
            }
            return escomptes;

        }
        return escomptes;
    }

    public Escompte getEscompte(int id_escompte) throws SQLException {
        Escompte escompte = null;
        PreparedStatement pst;
        ResultSet res;
        int echeance_jour;
        LocalDate echeance_date;
        double taux_escompte;
        //requete preparee pour récupérer tous les devises
        pst = connexion.prepareStatement("select * from escompte as e where e.id_escompte=?");
        pst.setInt(1, id_escompte);

        res = pst.executeQuery();
        if (res != null) { //Tester si la requête retourne des résultats non vides
            while (res.next()) { //Parcourir tous les résultats
                echeance_jour = res.getInt("echeance_jour");
                echeance_date = res.getDate("echeance_date").toLocalDate();
                taux_escompte = res.getDouble("taux_escompte");

                escompte = new Escompte(id_escompte, echeance_jour, echeance_date, taux_escompte);
                return escompte;
            }
        }
        return escompte;
    }

    public ArrayList<Paiement> getTousPaiements(int id_facture) throws SQLException {
        ArrayList<Paiement> paiements = null;
        PreparedStatement pst;
        ResultSet res;
        int id_paiement, escompte_id;
        LocalDate date_paiement;
        String mode_paiement;
        double montant_paiement;

        //requete preparee pour récupérer tous les devises
        pst = connexion.prepareStatement("select * from paiement as p where p.facture_id=?");
        pst.setInt(1, id_facture);

        res = pst.executeQuery();
        if (res != null) { //Tester si la requête retourne des résultats non vides
            paiements = new ArrayList<>();

            while (res.next()) { //Parcourir tous les résultats
                id_paiement = res.getInt("id_paiement");
                escompte_id = res.getInt("escompte_id");
                date_paiement = res.getDate("date_paiement").toLocalDate();
                mode_paiement = res.getString("mode_paiement");
                montant_paiement = res.getDouble("montant_paiement");

                Paiement paiement = new Paiement(id_paiement, date_paiement, mode_paiement, montant_paiement);
                if (escompte_id != 0) {
                    paiement.setEscompte(getEscompte(escompte_id));
                }
                paiements.add(paiement);
            }
            return paiements;

        }

        return paiements;
    }

    public boolean enregistrerPaiement(Paiement paiement) {
        try {
            PreparedStatement pst;

            pst = connexion.prepareStatement("insert into paiement (facture_id,escompte_id,date_paiement,mode_paiement,montant_paiement) values (?,?,?,?,?)");

            pst.setInt(1, paiement.getFacture().getId());
            if (paiement.getEscompte() != null) {
                pst.setInt(2, paiement.getEscompte().getId());
            } else {
                pst.setInt(2, Types.NULL);
            }
            pst.setDate(3, Date.valueOf(paiement.getDatePaiement()));
            pst.setString(4, paiement.getModePaiement());
            pst.setDouble(5, paiement.getMontantPaiement());

            //Insertion dans la table facture
            if (pst.executeUpdate() <= 0) {
                JOptionPane.showMessageDialog(null, "Erreur d'execution de l'enregistrement du paiement dans la base de données !", "Erreur",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(FactureModele.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erreur d'enregistrement du paiement !", "Erreur",
                    JOptionPane.ERROR_MESSAGE);
        }
        return true;
    }

    public boolean miseAJourSolde(int id_facture, double solde_impayees, double solde_payees, String etat_facture) {
        try {
            PreparedStatement pst;

            pst = connexion.prepareStatement("update facture set etat_facture=?, solde_impayees=?, solde_payees=? where id_facture=?");

            pst.setString(1, etat_facture);
            pst.setDouble(2, solde_impayees);
            pst.setDouble(3, solde_payees);
            pst.setInt(4, id_facture);

            if (pst.executeUpdate() <= 0) {
                JOptionPane.showMessageDialog(null, "Erreur d'execution de mise à jour solde facture", "Erreur",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return true;
    }

    public boolean miseAJourRabais(ArrayList<Rabais> rabaisList) {
        try {
            PreparedStatement pst;

            //Vider la table rabais
            pst = connexion.prepareStatement("TRUNCATE table rabais");
            pst.executeUpdate();

            for (Rabais rabais : rabaisList) {
                pst = connexion.prepareStatement("insert into rabais (taux_rabais,commentaires) values (?,?)");

                pst.setDouble(1, rabais.getTaux());
                pst.setString(2, rabais.getCommentaires());

                if (pst.executeUpdate() <= 0) {
                    JOptionPane.showMessageDialog(null, "Erreur d'execution de l'enregistrement des rabais dans la base de données !", "Erreur",
                            JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(FactureModele.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erreur d'enregistrement des rabais !", "Erreur",
                    JOptionPane.ERROR_MESSAGE);
        }
        return true;
    }

    public ArrayList<Rabais> getTousRabais() throws SQLException {
        ArrayList<Rabais> rabaisList = null;
        PreparedStatement pst;
        ResultSet res;
        int id_rabais;
        String commentaires;
        double taux_rabais;

        //requete preparée pour récupérer tous les rabais
        pst = connexion.prepareStatement("select * from rabais");

        res = pst.executeQuery();
        if (res != null) { //Tester si la requête retourne des résultats non vides
            rabaisList = new ArrayList<>();

            while (res.next()) { //Parcourir tous les résultats
                id_rabais = res.getInt("id_rabais");
                commentaires = res.getString("commentaires");
                taux_rabais = res.getDouble("taux_rabais");

                Rabais rabais = new Rabais(id_rabais, taux_rabais, commentaires);

                rabaisList.add(rabais);
            }
            return rabaisList;
        }
        return rabaisList;
    }

    public ArrayList<Ristourne> getTousRistourne() throws SQLException {
        ArrayList<Ristourne> ristournes = null;
        PreparedStatement pst;
        ResultSet res;
        int id_ristourne;
        int nombre_commandes;
        double taux_ristourne;

        //requete preparée pour récupérer tous les rabais
        pst = connexion.prepareStatement("select * from ristourne");

        res = pst.executeQuery();
        if (res != null) { //Tester si la requête retourne des résultats non vides
            ristournes = new ArrayList<>();

            while (res.next()) { //Parcourir tous les résultats
                id_ristourne = res.getInt("id_ristourne");
                nombre_commandes = res.getInt("nombre_commandes");
                taux_ristourne = res.getDouble("taux_ristourne");

                Ristourne ristourne = new Ristourne(id_ristourne, nombre_commandes, taux_ristourne);

                ristournes.add(ristourne);
            }
            return ristournes;
        }
        return ristournes;
    }

    public boolean miseAJourRistourne(Ristourne ristourne) {
        try {
            PreparedStatement pst;

            //Vider la table rabais
            pst = connexion.prepareStatement("TRUNCATE table ristourne");
            pst.executeUpdate();

            if (ristourne != null) {
                pst = connexion.prepareStatement("insert into ristourne (nombre_commandes,taux_ristourne) values (?,?)");

                pst.setInt(1, ristourne.getNombreCommandes());
                pst.setDouble(2, ristourne.getTauxRistourne());

                if (pst.executeUpdate() <= 0) {
                    JOptionPane.showMessageDialog(null, "Erreur d'execution de l'enregistrement ristourne dans la base de données !", "Erreur",
                            JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(FactureModele.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erreur d'enregistrement ristourne !", "Erreur",
                    JOptionPane.ERROR_MESSAGE);
        }
        return true;
    }

    public ArrayList<Remise> getTousRemise() throws SQLException {
        ArrayList<Remise> remises = null;
        PreparedStatement pst;
        ResultSet res;
        int id_remise;
        int nombre_produits;
        double taux_remise;

        //requete preparée pour récupérer tous les remises
        pst = connexion.prepareStatement("select * from remise");

        res = pst.executeQuery();
        if (res != null) { //Tester si la requête retourne des résultats non vides
            remises = new ArrayList<>();

            while (res.next()) { //Parcourir tous les résultats
                id_remise = res.getInt("id_remise");
                nombre_produits = res.getInt("nombre_produits");
                taux_remise = res.getDouble("taux_remise");

                Remise ristourne = new Remise(id_remise, nombre_produits, taux_remise);

                remises.add(ristourne);
            }
            return remises;
        }
        return remises;
    }

    public boolean miseAJourRemise(Remise remise) {
        try {
            PreparedStatement pst;

            //Vider la table rabais
            pst = connexion.prepareStatement("TRUNCATE table remise");
            pst.executeUpdate();

            if (remise != null) {
                pst = connexion.prepareStatement("insert into remise (nombre_produits,taux_remise) values (?,?)");

                pst.setInt(1, remise.getNombreProduits());
                pst.setDouble(2, remise.getTauxRemise());

                if (pst.executeUpdate() <= 0) {
                    JOptionPane.showMessageDialog(null, "Erreur d'execution de l'enregistrement remise dans la base de données !", "Erreur",
                            JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(FactureModele.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erreur d'enregistrement remise !", "Erreur",
                    JOptionPane.ERROR_MESSAGE);
        }
        return true;
    }
    
    public ArrayList<Facture> getFactureClient(int id_client) throws SQLException{
        ArrayList<Facture> factures = null;
        PreparedStatement pst;
        ResultSet res;

        /*
            les attributs de la facture
         */
        int id_facture, entreprise_id, numero_facture;
        String facture_type, etat_facture, remarques;
        LocalDate date_facture, date_delais;
        double reductions_commerciales, reductions_financieres, total_ht, total_tva, total_ttc, acompte, netAPayer, net_commerciale, net_financier, solde_impayees, solde_payees;

        //requete preparee pour récupérer toutes les factures
        pst = connexion.prepareStatement("select * from facture where client_id=? order by numero_facture desc");
        pst.setInt(1, id_client);
        
        res = pst.executeQuery();
        if (res != null) { //Tester si la requête retourne des résultats non vides
            ClientModele clientModele = new ClientModele();
            EntrepriseModele entrepriseModele = new EntrepriseModele();
            factures = new ArrayList<>();

            while (res.next()) { //Parcourir tous les résultats
                id_facture = res.getInt("id_facture");
                entreprise_id = res.getInt("entreprise_id");
                facture_type = res.getString("facture_type");
                numero_facture = res.getInt("numero_facture");
                date_facture = res.getDate("date_facture").toLocalDate();
                date_delais = res.getDate("date_delais").toLocalDate();
                etat_facture = res.getString("etat_facture");
                reductions_commerciales = res.getDouble("reductions_commerciales");
                reductions_financieres = res.getDouble("reductions_financieres");
                total_ht = res.getDouble("total_ht");
                total_tva = res.getDouble("total_tva");
                total_ttc = res.getDouble("total_ttc");
                acompte = res.getDouble("acompte");
                netAPayer = res.getDouble("netAPayer");
                remarques = res.getString("remarques");
                net_commerciale = res.getDouble("net_commerciale");
                net_financier = res.getDouble("net_financier");
                solde_impayees = res.getDouble("solde_impayees");
                solde_payees = res.getDouble("solde_payees");

                Facture facture = new Facture(id_facture, numero_facture, facture_type, date_facture, date_delais, remarques, solde_impayees, solde_payees, reductions_commerciales, net_commerciale, reductions_financieres, net_financier, total_ht, total_tva, total_ttc, acompte, netAPayer, etat_facture);

                facture.setLignes(getLigne(id_facture));
                facture.setClient(clientModele.getClient(id_client));
                facture.setEntreprise(entrepriseModele.getEntreprise(entreprise_id));
                if (getTousEscomptes(id_facture) != null) {
                    facture.setEscomptes(getTousEscomptes(id_facture));
                }
                if (getTousPaiements(id_facture) != null) {
                    facture.setPaiements(getTousPaiements(id_facture));
                }

                factures.add(facture);
            }

        }
        return factures;
    }
    
    public int nombreProduitsCommande(Client client, Produit produitSurNouvelleFacture) throws SQLException{
        int nombreProduits = 0;
        for (Facture facture: getFactureClient(client.getId())){
            for(LigneFacture ligneFacture : facture.getLignes()){
                
                if(ligneFacture.getLigneType().equals("produit")){
                    LigneProduit ligneProduit = (LigneProduit)ligneFacture;
                    
                    if(ligneProduit.getProduit() instanceof Produit){
                        Produit produit = (Produit)ligneProduit.getProduit();
                        
                        if(produit.getId() == produitSurNouvelleFacture.getId()){
                            nombreProduits++;
                        }
                    }
                }
                
            }
        }
        
        return nombreProduits;
    }
}
