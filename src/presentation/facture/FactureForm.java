package presentation.facture;

import donnees.Database;
import donnees.ProduitModele;
import java.awt.Toolkit;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import metier.Client;
import metier.ClientType;
import metier.Entreprise;
import metier.Escompte;
import metier.Facture;
import metier.LigneFacture;
import metier.LigneProduit;
import org.jdesktop.swingx.autocomplete.*;

/**
 *
 * @author Mamy Sitraka
 */
public class FactureForm extends javax.swing.JFrame {

    private Facture facture;
    private final Database db;
    private final Connection connexion;
    private DefaultComboBoxModel entrepriseComboboxModel;
    private DefaultComboBoxModel clientComboboxModel;
    private final SimpleDateFormat defaultDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private DefaultTableModel tablemodele;
    private FactureMainFrame factureMainFrame;
    private ProduitModele produitModele;

    public FactureForm() throws SQLException {
        initComponents();
        this.setTitle("Nouvelle facture");
        db = new Database();
        connexion = db.getConnetion();
        produitModele = new ProduitModele();
        initialiserFacture();
        initialiserValeurComposants();
    }

    public FactureForm(FactureMainFrame factureMainFrame) throws SQLException {
        this();
        this.factureMainFrame = factureMainFrame;
    }

    private int getNumeroSuivant() throws SQLException {
        PreparedStatement pst;
        ResultSet res;
        int num;
        //requete preparee pour récupérer le numéro de la dernière facture enregistrée
        pst = connexion.prepareStatement("select MAX(numero_facture) as numeroMax from facture");

        res = pst.executeQuery();
        if (res.next()) {
            //On incremente le numéro de la dernière facture enregistrée
            num = res.getInt("numeroMax") + 1;
            //System.out.println("numeroMax: "+num);
            return num;
        } else {
            return 1;
        }
    }

    private void initialiserTableauEsompte() {
        tblEscompte.setEnabled(false);
        btnAjouterLigneEscompte.setVisible(false);
        btnSupprimerLigneEscompte.setVisible(false);
    }

    private boolean initialiserComboboxEntreprise() throws SQLException {
        entrepriseComboboxModel = new DefaultComboBoxModel();
        PreparedStatement pst;
        ResultSet res;
        int id, nif, stat;
        double capital_social;
        String nom, adresse, telephone, email;
        //requete preparee pour récupérer tous les devises
        pst = connexion.prepareStatement("select * from entreprise");

        res = pst.executeQuery();
        if (res != null) { //Tester si la requête retourne des résultats non vides
            while (res.next()) { //Parcourir tous les résultats
                id = res.getInt("id_entreprise");
                nif = res.getInt("nif");
                stat = res.getInt("stat");
                capital_social = res.getDouble("capital_social");
                nom = res.getString("nom");
                adresse = res.getString("adresse");
                telephone = res.getString("telephone");
                email = res.getString("email");

                Entreprise entreprise = new Entreprise(id, nom, nif, stat, capital_social, adresse, telephone, email); //Création d'un objet entreprise
                entrepriseComboboxModel.addElement(entreprise); //Ajouter l'objet dans le ComboboxModel
            }

            cbxEntrepriseFacture.setModel(entrepriseComboboxModel);//Inserer le comboboxModel dans le composant

            return true;
        } else {

            JOptionPane.showMessageDialog(null, "entreprise introuvable dans la base de données !", "Erreur",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private boolean initialiserComboboxClient() throws SQLException {
        clientComboboxModel = new DefaultComboBoxModel();
        PreparedStatement pst;
        ResultSet res;
        int id, client_type_id;
        String nom, prenom, adresse, telephone, email, designation_type, siret;
        //requete preparee pour récupérer tous les devises
        pst = connexion.prepareStatement("select * from client as c join client_type as ct on c.client_type_id=ct.id_client_type");

        res = pst.executeQuery();
        if (res != null) { //Tester si la requête retourne des résultats non vides
            while (res.next()) { //Parcourir tous les résultats
                id = res.getInt("id_client");
                nom = res.getString("nom");
                prenom = res.getString("prenom");
                adresse = res.getString("adresse");
                telephone = res.getString("telephone");
                email = res.getString("email");
                client_type_id = res.getInt("client_type_id");
                designation_type = res.getString("designation_type");
                siret = res.getString("siret");

                ClientType clientType = new ClientType(client_type_id, designation_type);
                Client client = new Client(id, clientType, nom, prenom, adresse, telephone, email, siret); //Création d'un objet entreprise
                clientComboboxModel.addElement(client); //Ajouter l'objet dans le ComboboxModel
            }

            cbxClientFacture.setModel(clientComboboxModel);//Inserer le comboboxModel dans le composant
            /*
            Ici c'est l'autocompletion du combobox client
             */
            AutoCompleteDecorator.decorate(cbxClientFacture);
            return true;
        } else {

            JOptionPane.showMessageDialog(null, "Client introuvable dans la base de données !", "Erreur",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private void initialiserFacture() throws SQLException {
        facture = new Facture();
        facture.setNumero(getNumeroSuivant());
    }

    private void initialiserValeurComposants() throws SQLException {
        setIcon();
        txtNumeroFacture.setText(String.valueOf(facture.getNumero()));
        initialiserTableauEsompte();
        initialiserComboboxEntreprise();
        initialiserComboboxClient();

    }

    public void ajouterNouvelleLigne(LigneFacture ligne) {
        tablemodele = (DefaultTableModel) tblLigneFacture.getModel(); //Récupérer le modèle de la table des lignes
        Object row[] = new Object[9];
        row[0] = ligne; //Execute la méthode toString de la classe LigneFacture qui affiche le numéro de la ligne et le type de produit (produit, service, reduction)
        row[1] = ligne.getDesignation();
        row[2] = ligne.getQuantite();
        row[3] = ligne.getMontantUnitaire();
        if (ligne instanceof LigneProduit) {
            LigneProduit ligneProduit = (LigneProduit) ligne;
            row[4] = ligneProduit.getRabais();
            row[5] = ligneProduit.getRemise();
            row[6] = ligneProduit.getRistourne();
        } else {
            row[4] = "0";
            row[5] = "0";
            row[6] = "0";
        }
        row[7] = ligne.getTva();
        row[8] = ligne.getMontantHT();
        tablemodele.addRow(row);

    }

    private boolean tableauEscompteEstVide() {
        DefaultTableModel tableEscompteModele = (DefaultTableModel) tblEscompte.getModel();
        int nbrLignes = tableEscompteModele.getRowCount();

        //Verifier si le tableau n'est pas vide
        for (int i = 0; i < nbrLignes; i++) {
            if ((tableEscompteModele.getValueAt(i, 0) != null) || (tableEscompteModele.getValueAt(i, 1) != null)) {
                return false;
            }
        }
        return true;
    }

    private void afficherResultatCalculFacture() {
        //Affichage des totaux
        lblValeurTotalHT.setText(String.valueOf(new BigDecimal(facture.getTotalHT()).setScale(2, BigDecimal.ROUND_UP)) + " €");
        lblValeurRéductionsCommerciales.setText(String.valueOf(new BigDecimal(facture.getReductionsCommerciales()).setScale(2, BigDecimal.ROUND_UP)) + " €");
        lblValeurNetCommercial.setText(String.valueOf(new BigDecimal(facture.getNetCommerciale()).setScale(2, BigDecimal.ROUND_UP)) + " €");
        lblValeurReductionsFinancieres.setText(String.valueOf(new BigDecimal(facture.getReductionsFinancieres()).setScale(2, BigDecimal.ROUND_UP)) + " €");
        lblValeurNetFinancier.setText(String.valueOf(new BigDecimal(facture.getNetFinancier()).setScale(2, BigDecimal.ROUND_UP)) + " €");
        lblValeurTotalTVA.setText(String.valueOf(new BigDecimal(facture.getTotalTVA()).setScale(2, BigDecimal.ROUND_UP)) + " €");
        lblValeurTotalTTC.setText(String.valueOf(new BigDecimal(facture.getTotalTTC()).setScale(2, BigDecimal.ROUND_UP)) + " €");
        lblValeurNetAPayer.setText(String.valueOf(new BigDecimal(facture.getNetAPayer()).setScale(2, BigDecimal.ROUND_UP)) + " €");
    }

    public void mettreAJourFacture() {
        double acompte = (double) spnAcompte.getValue();
        facture.setAcompte(acompte);
        facture.calculerFactureSansEscompte();
        afficherResultatCalculFacture();
    }

    private boolean enregistrerFacture() {
        //Tester si la date facture et la date de règlement sont bien remplies
        if ((txtDateFacture.getDate() == null) || (txtDateReglement.getDate() == null)) {
            JOptionPane.showMessageDialog(null, "Veuillez remplir la date facture et la date de delais de règlement !", "Erreur",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            //Tester si la date facture est bien inférieur ou égale à la date de delais de règlement
            if (txtDateFacture.getDate().after(txtDateReglement.getDate())) {
                JOptionPane.showMessageDialog(null, "La date facture doit être avant la date de delais de règlement, ou les deux dates doivent être les mêmes si le règlement est au comptant", "Erreur",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        LocalDate dateFacture = LocalDate.parse(defaultDateFormat.format(txtDateFacture.getDate()));
        LocalDate dateDelais = LocalDate.parse(defaultDateFormat.format(txtDateReglement.getDate()));

        Client client = (Client) cbxClientFacture.getSelectedItem();
        if (client == null) {
            JOptionPane.showMessageDialog(null, "Veuillez selectionner le client !", "Erreur",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        Entreprise entreprise = (Entreprise) cbxEntrepriseFacture.getSelectedItem();
        if (entreprise == null) {
            JOptionPane.showMessageDialog(null, "Veuillez selectionner l'entreprise !", "Erreur",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        String type = (String) cbxTypeFacture.getSelectedItem();
        String remarques = txtRemarquesFacture.getText();

        //Parcourir chaque ligne du tableau d'escompte si la case est cochée
        if (chxEscompte.isSelected()) {
            if (tableauEscompteEstVide()) {
                JOptionPane.showMessageDialog(null, "Le tableau d'escompte est vide. Veuillez décocher la case escompte s'il n'est pas utilisé!", "Erreur",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            } else {

                DefaultTableModel tableEscompteModele = (DefaultTableModel) tblEscompte.getModel();
                int nbrLignes = tableEscompteModele.getRowCount();

                for (int i = 0; i < nbrLignes; i++) {
                    // Si l'une des colonnes dans une ligne est vide
                    if (((tableEscompteModele.getValueAt(i, 0) == null) && (tableEscompteModele.getValueAt(i, 1) != null)) || ((tableEscompteModele.getValueAt(i, 0) != null) && (tableEscompteModele.getValueAt(i, 1) == null))) {
                        JOptionPane.showMessageDialog(null, "Veuillez bien remplir le tableau d'escompte !", "Erreur",
                                JOptionPane.ERROR_MESSAGE);
                        return false;
                    } else {
                        if ((tableEscompteModele.getValueAt(i, 0) != null) && (tableEscompteModele.getValueAt(i, 1) != null)) {
                            int echeance = (Integer) tableEscompteModele.getValueAt(i, 0);
                            double taux = (double) tableEscompteModele.getValueAt(i, 1);

                            //Définir la date de chaque échéance selon le nombre de jour saisi dans le tableau d'escompte
                            LocalDate echeance_date = dateFacture.plusDays(echeance);

                            // Si le nombre de jour spécifié dans le tableau d'escompte dépasse le delais de règlement
                            if (echeance_date.isAfter(dateDelais)) {
                                JOptionPane.showMessageDialog(null, "Veuillez bien verifier si le nombre de jour dans le tableau d'escompte ne dépasse pas le delais de règlement !", "Erreur",
                                        JOptionPane.ERROR_MESSAGE);
                                return false;
                            }

                            Escompte escompte = new Escompte(echeance, echeance_date, taux);

                            facture.ajouterEscompte(escompte);
                        }
                    }
                }
            }
        }

        //Enregistrer tous les attributs de la factures
        facture.setSoldeImpayees(facture.getNetAPayer()); //Lorsqu'on enregistre la facture mais elle n'est pas encore payée pour l'instant
        facture.setSoldePayees(0);
        facture.setClient(client);
        facture.setDateFacture(dateFacture);
        facture.setDateDelais(dateDelais);
        facture.setEntreprise(entreprise);
        facture.setType(type);
        facture.setRemarques(remarques);

        return true;
    }

    private boolean insererFactureDansBD() {
        try {
            PreparedStatement pst;
            pst = connexion.prepareStatement("insert into facture (client_id,entreprise_id,facture_type,numero_facture,date_facture,date_delais,etat_facture,reductions_commerciales,reductions_financieres,total_ht,total_tva,total_ttc,acompte,netAPayer,remarques,net_commerciale,net_financier,solde_impayees) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

            pst.setInt(1, facture.getClient().getId());
            pst.setInt(2, facture.getEntreprise().getId());
            pst.setString(3, facture.getType());
            pst.setInt(4, facture.getNumero());
            pst.setDate(5, Date.valueOf(facture.getDateFacture()));
            pst.setDate(6, Date.valueOf(facture.getDateDelais()));
            pst.setString(7, facture.getEtat());
            pst.setDouble(8, facture.getReductionsCommerciales());
            pst.setDouble(9, facture.getReductionsFinancieres());
            pst.setDouble(10, facture.getTotalHT());
            pst.setDouble(11, facture.getTotalTVA());
            pst.setDouble(12, facture.getTotalTTC());
            pst.setDouble(13, facture.getAcompte());
            pst.setDouble(14, facture.getNetAPayer());
            pst.setString(15, facture.getRemarques());
            pst.setDouble(16, facture.getNetCommerciale());
            pst.setDouble(17, facture.getNetFinancier());
            pst.setDouble(18, facture.getSoldeImpayees());

            //Insertion dans la table facture
            if (pst.executeUpdate() <= 0) {
                JOptionPane.showMessageDialog(null, "Erreur d'ajout de la facture dans la base de données !", "Erreur",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }

            //Recuperer l'id de la derniere facture pour inserer dans la table ligne_facture
            ResultSet res;
            int id_facture = 0;
            pst = connexion.prepareStatement("select id_facture from facture as f  where f.numero_facture=?");
            pst.setInt(1, facture.getNumero());

            res = pst.executeQuery();
            if (res != null) { //Tester si la requête retourne des résultats non vides
                while (res.next()) { //Parcourir tous les résultats

                    id_facture = res.getInt("id_facture");

                }
            } else {
                JOptionPane.showMessageDialog(null, "L'identification de la dernière facture introuvable !", "Erreur",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }

            //Inserer dans la table ligne_facture
            for (Iterator<LigneFacture> it = facture.getLignes().iterator(); it.hasNext();) {

                LigneFacture ligne = it.next();

                pst = connexion.prepareStatement("insert into ligne_facture (facture_id,produit_id,ligne_type,numero_ligne,designation_ligne,quantite,montant_unitaire,rabais,montant_rabais,remise,montant_remise,ristourne,montant_ristourne,montant_ht,montant_tva,montant_ttc,reductions_commerciales,net_commerciale,tva) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                pst.setInt(1, id_facture);
                if (ligne instanceof LigneProduit) {

                    LigneProduit ligneProduit = (LigneProduit) ligne;

                    if (ligneProduit.getProduit() != null) { //Pour le produit et service
                        pst.setInt(2, ligneProduit.getProduit().getId());
                    } else {
                        pst.setInt(2, Types.NULL); //pour le type autre
                    }

                } else {
                    pst.setInt(2, Types.NULL);
                }
                pst.setString(3, ligne.getLigneType());
                pst.setInt(4, ligne.getNumero());
                pst.setString(5, ligne.getDesignation());
                pst.setInt(6, ligne.getQuantite());
                pst.setDouble(7, ligne.getMontantUnitaire());
                if (ligne instanceof LigneProduit) {

                    LigneProduit ligneProduit = (LigneProduit) ligne;
                    pst.setDouble(8, ligneProduit.getRabais());
                    pst.setDouble(9, ligneProduit.getMontantRabais());
                    pst.setDouble(10, ligneProduit.getRemise());
                    pst.setDouble(11, ligneProduit.getMontantRemise());
                    pst.setDouble(12, ligneProduit.getRistourne());
                    pst.setDouble(13, ligneProduit.getMontantRistourne());
                    pst.setDouble(17, ligneProduit.getReductionsCommerciales());
                    pst.setDouble(18, ligneProduit.getNetCommercial());

                    //Mise à jour de la quantité en stock
                    if (ligneProduit.getProduit().getDesignation().equals("produit")) {
                        int quantiteStock = produitModele.getQuantiteStock(ligneProduit.getId());
                        if (quantiteStock == -1) {
                            JOptionPane.showMessageDialog(null, "La quantite en stock n'est pas disponible pour certains produits pour cette facture. Veuillez verifier la quantité en stock !", "Erreur",
                                    JOptionPane.ERROR_MESSAGE);
                            return false;
                        }
                        int quantiteRestante = quantiteStock - (ligneProduit.getQuantite());
                        produitModele.miseAJourStock(ligneProduit.getId(), quantiteRestante);
                    }

                } else {
                    pst.setDouble(8, 0); //pour les types expeditions
                    pst.setDouble(9, 0);
                    pst.setDouble(10, 0);
                    pst.setDouble(11, 0);
                    pst.setDouble(12, 0);
                    pst.setDouble(13, 0);
                    pst.setDouble(17, 0);
                    pst.setDouble(18, 0);
                }

                pst.setDouble(14, ligne.getMontantHT());
                pst.setDouble(15, ligne.getMontantTVA());
                pst.setDouble(16, ligne.getMontantTTC());
                pst.setDouble(19, ligne.getTva());

                if (pst.executeUpdate() <= 0) {
                    JOptionPane.showMessageDialog(null, "Erreur d'ajout des lignes facture dans la base de données !", "Erreur",
                            JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }

            if (!facture.getEscomptes().isEmpty()) {
                //Insertion dans la table escompte si le tableau d'escompte n'est pas vide
                for (Escompte escompte : facture.getEscomptes()) {
                    pst = connexion.prepareStatement("insert into escompte (facture_id,echeance_jour,echeance_date,taux_escompte) values (?,?,?,?)");

                    pst.setInt(1, id_facture);
                    pst.setInt(2, escompte.getEcheance());
                    pst.setDate(3, Date.valueOf(escompte.getEcheanceDate()));
                    pst.setDouble(4, escompte.getTaux());

                    if (pst.executeUpdate() <= 0) {
                        JOptionPane.showMessageDialog(null, "Erreur d'ajout de la facture dans la base de données !", "Erreur",
                                JOptionPane.ERROR_MESSAGE);
                        return false;
                    }
                }

            }

            JOptionPane.showMessageDialog(null, "Ajout de la facture avec succès", "Information",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return true;
    }

    public void configurerCbxClientFacture() {

        if (facture.getDernierNumeroLigne() != 0) {
            cbxClientFacture.setEnabled(false);
        } else if (facture.getDernierNumeroLigne() == 0) {
            cbxClientFacture.setEnabled(true);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        cbxClientFacture = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtPrenomClient = new javax.swing.JTextField();
        txtNomClient = new javax.swing.JTextField();
        txtAdresseClient = new javax.swing.JTextField();
        txtTelephoneClient = new javax.swing.JTextField();
        txtEmailClient = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        txtDateFacture = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNumeroFacture = new javax.swing.JTextField();
        cbxEntrepriseFacture = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtDateReglement = new com.toedter.calendar.JDateChooser();
        cbxTypeFacture = new javax.swing.JComboBox<>();
        jLabel31 = new javax.swing.JLabel();
        btnAjouterLigneFacture = new javax.swing.JButton();
        btnSupprimerLigneFacture = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        lblTotalHT = new javax.swing.JLabel();
        lblValeurTotalHT = new javax.swing.JLabel();
        lblTotalTVA = new javax.swing.JLabel();
        lblValeurTotalTVA = new javax.swing.JLabel();
        lblTotalTTC = new javax.swing.JLabel();
        lblValeurTotalTTC = new javax.swing.JLabel();
        lblAcompte = new javax.swing.JLabel();
        lblTotalReduction = new javax.swing.JLabel();
        lblValeurRéductionsCommerciales = new javax.swing.JLabel();
        spnAcompte = new javax.swing.JSpinner();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblEscompte = new javax.swing.JTable();
        chxEscompte = new javax.swing.JCheckBox();
        btnAjouterLigneEscompte = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        lblDevise1 = new javax.swing.JLabel();
        lblDevise2 = new javax.swing.JLabel();
        lblDevise3 = new javax.swing.JLabel();
        btnSupprimerLigneEscompte = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtRemarquesFacture = new javax.swing.JTextArea();
        jLabel32 = new javax.swing.JLabel();
        btnEnregistrerFacture = new javax.swing.JButton();
        btnAcquitter = new javax.swing.JButton();
        btnAnnulerFacture = new javax.swing.JButton();
        lblNetAPayer = new javax.swing.JLabel();
        lblValeurNetAPayer = new javax.swing.JLabel();
        lblTotalHT1 = new javax.swing.JLabel();
        lblValeurNetCommercial = new javax.swing.JLabel();
        lblTotalHT2 = new javax.swing.JLabel();
        lblValeurReductionsFinancieres = new javax.swing.JLabel();
        lblTotalHT3 = new javax.swing.JLabel();
        lblValeurNetFinancier = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLigneFacture = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        cbxClientFacture.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbxClientFacture.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxClientFacture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxClientFactureActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setText("Client");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setText("Nom");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setText("Prénom(s)");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel7.setText("Adresse");

        jLabel8.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel8.setText("Téléphone");

        jLabel9.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel9.setText("email");

        txtPrenomClient.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtPrenomClient.setEnabled(false);

        txtNomClient.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtNomClient.setEnabled(false);

        txtAdresseClient.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtAdresseClient.setEnabled(false);

        txtTelephoneClient.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtTelephoneClient.setEnabled(false);

        txtEmailClient.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtEmailClient.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(73, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addGap(56, 56, 56)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTelephoneClient, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAdresseClient, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(cbxClientFacture, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtPrenomClient, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNomClient, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtEmailClient, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(36, 36, 36)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(45, 45, 45))
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(28, 28, 28)
                                .addComponent(jLabel7)
                                .addGap(45, 45, 45))
                            .addComponent(jLabel8))
                        .addGap(45, 45, 45))
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(cbxClientFacture, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(txtNomClient, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtPrenomClient, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtAdresseClient, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTelephoneClient, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtEmailClient, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        txtDateFacture.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtDateFacture.setPreferredSize(new java.awt.Dimension(100, 22));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText("Date facture");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("Facture numéro");

        txtNumeroFacture.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtNumeroFacture.setEnabled(false);

        cbxEntrepriseFacture.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbxEntrepriseFacture.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Entreprise");

        jLabel10.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel10.setText("Date delais règlement");

        txtDateReglement.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        cbxTypeFacture.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbxTypeFacture.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "doit", "avoir" }));

        jLabel31.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel31.setText("Type de facture");

        btnAjouterLigneFacture.setBackground(new java.awt.Color(0, 170, 245));
        btnAjouterLigneFacture.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        btnAjouterLigneFacture.setForeground(new java.awt.Color(255, 255, 255));
        btnAjouterLigneFacture.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icons8_plus_20px_1.png"))); // NOI18N
        btnAjouterLigneFacture.setText("Nouvelle ligne");
        btnAjouterLigneFacture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAjouterLigneFactureActionPerformed(evt);
            }
        });

        btnSupprimerLigneFacture.setBackground(new java.awt.Color(0, 170, 245));
        btnSupprimerLigneFacture.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        btnSupprimerLigneFacture.setForeground(new java.awt.Color(255, 255, 255));
        btnSupprimerLigneFacture.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icons8_delete_sign_20px.png"))); // NOI18N
        btnSupprimerLigneFacture.setText("Supprimer ligne");
        btnSupprimerLigneFacture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupprimerLigneFactureActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel31)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbxTypeFacture, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel10))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(cbxEntrepriseFacture, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtNumeroFacture, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtDateFacture, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtDateReglement, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnAjouterLigneFacture, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSupprimerLigneFacture, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(267, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtDateFacture, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtNumeroFacture, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbxEntrepriseFacture, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtDateReglement, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(28, 28, 28)
                                .addComponent(jLabel2)
                                .addGap(45, 45, 45))
                            .addComponent(jLabel3))
                        .addGap(28, 28, 28)
                        .addComponent(jLabel10)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxTypeFacture, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31))
                .addGap(34, 34, 34)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAjouterLigneFacture)
                    .addComponent(btnSupprimerLigneFacture))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(134, 134, 134))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        lblTotalHT.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblTotalHT.setText("Total HT :");

        lblValeurTotalHT.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblValeurTotalHT.setText("0.0");

        lblTotalTVA.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblTotalTVA.setText("Total TVA :");

        lblValeurTotalTVA.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblValeurTotalTVA.setText("0.0");

        lblTotalTTC.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblTotalTTC.setText("Total TTC :");

        lblValeurTotalTTC.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblValeurTotalTTC.setText("0.0");

        lblAcompte.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblAcompte.setText("Acompte :");

        lblTotalReduction.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblTotalReduction.setText("Réductions commerciales :");

        lblValeurRéductionsCommerciales.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblValeurRéductionsCommerciales.setText("0.0");

        spnAcompte.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        spnAcompte.setModel(new javax.swing.SpinnerNumberModel(0.0d, 0.0d, null, 1.0d));

        tblEscompte.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        tblEscompte.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null}
            },
            new String [] {
                "Echéance (en jour)", "Taux ( en %)"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblEscompte.setEnabled(false);
        tblEscompte.setFocusable(false);
        tblEscompte.setGridColor(new java.awt.Color(195, 195, 195));
        tblEscompte.setRowHeight(25);
        tblEscompte.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tblEscompte);

        chxEscompte.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        chxEscompte.setText("Escompte");
        chxEscompte.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chxEscompteItemStateChanged(evt);
            }
        });

        btnAjouterLigneEscompte.setBackground(new java.awt.Color(0, 170, 245));
        btnAjouterLigneEscompte.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        btnAjouterLigneEscompte.setForeground(new java.awt.Color(255, 255, 255));
        btnAjouterLigneEscompte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icons8_plus_20px_1.png"))); // NOI18N
        btnAjouterLigneEscompte.setText("Nouvelle ligne");
        btnAjouterLigneEscompte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAjouterLigneEscompteActionPerformed(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel22.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icons8_error_50px.png"))); // NOI18N
        jLabel22.setText("ATTENTION !");

        lblDevise1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblDevise1.setText("Dans le tableau d'escompte ci-dessus, l'échéance est exprimée en nombre");

        lblDevise2.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblDevise2.setText("de jours (sans virgule), à compter de la date de la facture sans dépasser le ");

        lblDevise3.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblDevise3.setText("delais de règlement. Pour le paiement au comptant, mettez 0 jour.");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(lblDevise1, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
                        .addGap(23, 23, 23))
                    .addComponent(lblDevise2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDevise3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblDevise1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDevise2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDevise3)
                .addGap(36, 36, 36))
        );

        btnSupprimerLigneEscompte.setBackground(new java.awt.Color(0, 170, 245));
        btnSupprimerLigneEscompte.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        btnSupprimerLigneEscompte.setForeground(new java.awt.Color(255, 255, 255));
        btnSupprimerLigneEscompte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icons8_delete_sign_20px.png"))); // NOI18N
        btnSupprimerLigneEscompte.setText("Supprimer ligne");
        btnSupprimerLigneEscompte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupprimerLigneEscompteActionPerformed(evt);
            }
        });

        txtRemarquesFacture.setColumns(20);
        txtRemarquesFacture.setRows(5);
        jScrollPane3.setViewportView(txtRemarquesFacture);

        jLabel32.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel32.setText("Autres remarques");

        btnEnregistrerFacture.setBackground(new java.awt.Color(0, 170, 245));
        btnEnregistrerFacture.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        btnEnregistrerFacture.setForeground(new java.awt.Color(255, 255, 255));
        btnEnregistrerFacture.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icons8_micro_sd_25px.png"))); // NOI18N
        btnEnregistrerFacture.setText("Enregistrer");
        btnEnregistrerFacture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnregistrerFactureActionPerformed(evt);
            }
        });

        btnAcquitter.setBackground(new java.awt.Color(0, 170, 245));
        btnAcquitter.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        btnAcquitter.setForeground(new java.awt.Color(255, 255, 255));
        btnAcquitter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icons8_pos_terminal_25px.png"))); // NOI18N
        btnAcquitter.setText("Acquitter");
        btnAcquitter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAcquitterActionPerformed(evt);
            }
        });

        btnAnnulerFacture.setBackground(new java.awt.Color(0, 170, 245));
        btnAnnulerFacture.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        btnAnnulerFacture.setForeground(new java.awt.Color(255, 255, 255));
        btnAnnulerFacture.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icons8_delete_sign_25px.png"))); // NOI18N
        btnAnnulerFacture.setText("Annuler");
        btnAnnulerFacture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnnulerFactureActionPerformed(evt);
            }
        });

        lblNetAPayer.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        lblNetAPayer.setText("NET A PAYER :");

        lblValeurNetAPayer.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        lblValeurNetAPayer.setText("0.0");

        lblTotalHT1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblTotalHT1.setText("Net commercial :");

        lblValeurNetCommercial.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblValeurNetCommercial.setText("0.0");

        lblTotalHT2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblTotalHT2.setText("Réductions financières :");

        lblValeurReductionsFinancieres.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblValeurReductionsFinancieres.setText("0.0");

        lblTotalHT3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblTotalHT3.setText("Net financier :");

        lblValeurNetFinancier.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblValeurNetFinancier.setText("0.0");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(chxEscompte)
                        .addGap(18, 18, 18)
                        .addComponent(btnAjouterLigneEscompte, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSupprimerLigneEscompte, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel32)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addComponent(lblAcompte)
                                        .addGap(27, 27, 27)
                                        .addComponent(spnAcompte, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(lblTotalTVA)
                                            .addComponent(lblTotalTTC)
                                            .addComponent(lblTotalHT))
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addGap(27, 27, 27)
                                                .addComponent(lblValeurTotalHT, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(lblValeurTotalTTC, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(lblValeurTotalTVA, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addComponent(lblTotalReduction)
                                        .addGap(27, 27, 27)
                                        .addComponent(lblValeurRéductionsCommerciales, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addComponent(lblTotalHT1)
                                        .addGap(27, 27, 27)
                                        .addComponent(lblValeurNetCommercial, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addComponent(lblTotalHT2)
                                        .addGap(27, 27, 27)
                                        .addComponent(lblValeurReductionsFinancieres, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addComponent(lblTotalHT3)
                                        .addGap(27, 27, 27)
                                        .addComponent(lblValeurNetFinancier, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnEnregistrerFacture, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnAcquitter, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnAnnulerFacture, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(lblNetAPayer)
                        .addGap(27, 27, 27)
                        .addComponent(lblValeurNetAPayer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblValeurRéductionsCommerciales)
                            .addComponent(lblTotalReduction))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTotalHT1)
                            .addComponent(lblValeurNetCommercial))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(lblValeurReductionsFinancieres)
                                .addGap(3, 3, 3))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(lblTotalHT2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTotalHT3)
                            .addComponent(lblValeurNetFinancier))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblValeurTotalHT)
                            .addComponent(lblTotalHT, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTotalTVA)
                            .addComponent(lblValeurTotalTVA))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblValeurTotalTTC)
                            .addComponent(lblTotalTTC))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(spnAcompte, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAcompte))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNetAPayer)
                            .addComponent(lblValeurNetAPayer))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEnregistrerFacture, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAcquitter, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAnnulerFacture, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chxEscompte)
                            .addComponent(btnAjouterLigneEscompte)
                            .addComponent(btnSupprimerLigneEscompte)
                            .addComponent(jLabel32))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        tblLigneFacture.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Numéro", "Designation", "Quantité", "Montant U", "Rabais", "Remise", "Ristourne", "TVA", "Montant HT"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                true, true, true, true, true, true, true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblLigneFacture.setGridColor(new java.awt.Color(195, 195, 195));
        tblLigneFacture.setRowHeight(27);
        jScrollPane1.setViewportView(tblLigneFacture);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbxClientFactureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxClientFactureActionPerformed
        Client client = (Client) cbxClientFacture.getSelectedItem();
        txtNomClient.setText(client.getNom());
        txtPrenomClient.setText(client.getPrenom());
        txtAdresseClient.setText(client.getAdresse());
        txtTelephoneClient.setText(client.getTelephone());
        txtEmailClient.setText(client.getEmail());
    }//GEN-LAST:event_cbxClientFactureActionPerformed

    private void chxEscompteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chxEscompteItemStateChanged
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            tblEscompte.setEnabled(true);
            btnAjouterLigneEscompte.setVisible(true);
            btnSupprimerLigneEscompte.setVisible(true);
        } else {
            tblEscompte.setEnabled(false);
            btnAjouterLigneEscompte.setVisible(false);
            btnSupprimerLigneEscompte.setVisible(false);
            //Vider le tableau d'esompte
            DefaultTableModel tableModele = (DefaultTableModel) tblEscompte.getModel();
            tableModele.setRowCount(0);
        }
    }//GEN-LAST:event_chxEscompteItemStateChanged

    private void btnAjouterLigneEscompteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAjouterLigneEscompteActionPerformed
        DefaultTableModel tableModele = (DefaultTableModel) tblEscompte.getModel();
        Object[] ligne = new Object[2];
        tableModele.addRow(ligne);
    }//GEN-LAST:event_btnAjouterLigneEscompteActionPerformed

    private void btnSupprimerLigneEscompteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupprimerLigneEscompteActionPerformed
        DefaultTableModel tableModele = (DefaultTableModel) tblEscompte.getModel();
        int ligneSelectionnee = tblEscompte.getSelectedRow();
        if (ligneSelectionnee != -1) // Si une ligne est bien selectionnée
        {
            tableModele.removeRow(ligneSelectionnee);
        }
    }//GEN-LAST:event_btnSupprimerLigneEscompteActionPerformed

    private void btnAjouterLigneFactureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAjouterLigneFactureActionPerformed
        try {

            Client client = (Client) cbxClientFacture.getSelectedItem();

            if (client != null) {

                facture.setClient(client);
                LigneFactureForm ligneFactureForm = new LigneFactureForm(this, true, this, facture);
                ligneFactureForm.setLocationRelativeTo(null);
                ligneFactureForm.setVisible(true);

            } else {

                JOptionPane.showMessageDialog(null, "Veuillez selectionner le client. Si la facture contient au moins une ligne, vous ne pouvez plus le modifier ultérieurement !", "Erreur",
                        JOptionPane.ERROR_MESSAGE);
                return;

            }

        } catch (SQLException ex) {
            Logger.getLogger(FactureForm.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnAjouterLigneFactureActionPerformed

    private void btnSupprimerLigneFactureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupprimerLigneFactureActionPerformed
        DefaultTableModel tableModele = (DefaultTableModel) tblLigneFacture.getModel();
        int ligneSelectionnee = tblLigneFacture.getSelectedRow();
        LigneFacture ligneFacture = null;
        if (ligneSelectionnee != -1) // Si une ligne est bien selectionnée
        {

            ligneFacture = (LigneFacture) tableModele.getValueAt(ligneSelectionnee, 0);
            facture.supprimerLigne(ligneFacture);
            tableModele.removeRow(ligneSelectionnee);
        }

        mettreAJourFacture();
        facture.decrementerNumeroLigne();

        configurerCbxClientFacture();
    }//GEN-LAST:event_btnSupprimerLigneFactureActionPerformed

    private void btnEnregistrerFactureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnregistrerFactureActionPerformed
        mettreAJourFacture(); //Mettre à jour la facture pour être à l'abri des changements sur le formulaire
        if (enregistrerFacture()) { //enregistrer dans l'objet facture tous les attributs récupérés du formulaire
            if (insererFactureDansBD()) {
                try {

                    factureMainFrame.afficherTousFactures(); //vider le tableau des factures et recharger
                    this.dispose();

                } catch (SQLException ex) {
                    Logger.getLogger(FactureForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }//GEN-LAST:event_btnEnregistrerFactureActionPerformed

    private void btnAcquitterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAcquitterActionPerformed
        //Ici c'est pour calculer une ligne de facture selon le formulaire et juste afficher les résultats mais pas enregistrer
        /*calculerLigne();
        afficherResultatCalculLigne(); //Affiche le resultat du calcul de la ligne sur les labels*/
    }//GEN-LAST:event_btnAcquitterActionPerformed

    private void btnAnnulerFactureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnnulerFactureActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnAnnulerFactureActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {

                    new FactureForm().setVisible(true);

                } catch (SQLException ex) {
                    Logger.getLogger(FactureForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage("icons8_bullish_filled_75px.png"));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAcquitter;
    private javax.swing.JButton btnAjouterLigneEscompte;
    private javax.swing.JButton btnAjouterLigneFacture;
    private javax.swing.JButton btnAnnulerFacture;
    private javax.swing.JButton btnEnregistrerFacture;
    private javax.swing.JButton btnSupprimerLigneEscompte;
    private javax.swing.JButton btnSupprimerLigneFacture;
    private javax.swing.JComboBox<String> cbxClientFacture;
    private javax.swing.JComboBox<String> cbxEntrepriseFacture;
    private javax.swing.JComboBox<String> cbxTypeFacture;
    private javax.swing.JCheckBox chxEscompte;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblAcompte;
    private javax.swing.JLabel lblDevise1;
    private javax.swing.JLabel lblDevise2;
    private javax.swing.JLabel lblDevise3;
    private javax.swing.JLabel lblNetAPayer;
    private javax.swing.JLabel lblTotalHT;
    private javax.swing.JLabel lblTotalHT1;
    private javax.swing.JLabel lblTotalHT2;
    private javax.swing.JLabel lblTotalHT3;
    private javax.swing.JLabel lblTotalReduction;
    private javax.swing.JLabel lblTotalTTC;
    private javax.swing.JLabel lblTotalTVA;
    private javax.swing.JLabel lblValeurNetAPayer;
    private javax.swing.JLabel lblValeurNetCommercial;
    private javax.swing.JLabel lblValeurNetFinancier;
    private javax.swing.JLabel lblValeurReductionsFinancieres;
    private javax.swing.JLabel lblValeurRéductionsCommerciales;
    private javax.swing.JLabel lblValeurTotalHT;
    private javax.swing.JLabel lblValeurTotalTTC;
    private javax.swing.JLabel lblValeurTotalTVA;
    private javax.swing.JSpinner spnAcompte;
    private javax.swing.JTable tblEscompte;
    private javax.swing.JTable tblLigneFacture;
    private javax.swing.JTextField txtAdresseClient;
    private com.toedter.calendar.JDateChooser txtDateFacture;
    private com.toedter.calendar.JDateChooser txtDateReglement;
    private javax.swing.JTextField txtEmailClient;
    private javax.swing.JTextField txtNomClient;
    private javax.swing.JTextField txtNumeroFacture;
    private javax.swing.JTextField txtPrenomClient;
    private javax.swing.JTextArea txtRemarquesFacture;
    private javax.swing.JTextField txtTelephoneClient;
    // End of variables declaration//GEN-END:variables
}
