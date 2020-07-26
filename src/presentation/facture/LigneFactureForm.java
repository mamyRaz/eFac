package presentation.facture;

import donnees.Database;
import donnees.FactureModele;
import java.awt.Toolkit;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import metier.Devise;
import metier.Facture;
import metier.FamilleProduit;
import metier.LigneExpedition;
import metier.LigneFacture;
import metier.LigneProduit;
import metier.Produit;
import metier.ProduitType;
import metier.Rabais;
import metier.Remise;
import metier.Ristourne;
import metier.Service;
import metier.TVA;
import org.jdesktop.swingx.autocomplete.*;
import presentation.client.InfosClientFrame;

/**
 *
 * @author Mamy Sitraka
 */
public class LigneFactureForm extends javax.swing.JDialog {

    private FactureForm factureForm;
    private Facture facture;
    private LigneFacture ligneFacture;
    private final Database db;
    private final Connection connexion;
    private DefaultComboBoxModel produitServiceComboboxModel;
    private DefaultComboBoxModel cbxLigneTypeModel;
    private DefaultComboBoxModel cbxDevisemodel;
    private FactureModele factureModele;

    public LigneFactureForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        db = new Database();
        connexion = db.getConnetion();
        factureModele = new FactureModele();
        this.setTitle("Nouvelle ligne facture");
    }

    public LigneFactureForm(java.awt.Frame parent, boolean modal, FactureForm factureFrom, Facture facture) throws SQLException {
        this(parent, modal);
        this.factureForm = factureFrom;
        this.facture = facture;
        initialiserValeurComposants();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        cbxLigneType = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        lblProduitService = new javax.swing.JLabel();
        txtReferenceProduitService = new javax.swing.JTextField();
        txtDesigantion = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cbxProduitService = new javax.swing.JComboBox<>();
        lblReferenceProduit = new javax.swing.JLabel();
        spnQuantite = new javax.swing.JSpinner();
        spnMontantUnitaire = new javax.swing.JSpinner();
        spnTVA = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        txtQuantiteStockProduit = new javax.swing.JTextField();
        lblQuantiteStock = new javax.swing.JLabel();
        txtUniteProduitService = new javax.swing.JTextField();
        cbxDevise = new javax.swing.JComboBox<>();
        panelReduction = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        spnRemise = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        spnRistourne = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lblReductionCommerciales = new javax.swing.JLabel();
        lblMontantHT = new javax.swing.JLabel();
        lblValeurReductionsCommerciales = new javax.swing.JLabel();
        lblValeurMontantHT = new javax.swing.JLabel();
        lblMontantTVA = new javax.swing.JLabel();
        lblMontantTTC = new javax.swing.JLabel();
        lblValeurMontantTVA = new javax.swing.JLabel();
        lblNetCommercial = new javax.swing.JLabel();
        lblValeurNetCommercial = new javax.swing.JLabel();
        lblValeurMontantTTC = new javax.swing.JLabel();
        btnCalculerLigne = new javax.swing.JButton();
        btnAnnulerLigneFacture = new javax.swing.JButton();
        btnEnregistrerLigneFacture = new javax.swing.JButton();
        cbxRabais = new javax.swing.JComboBox<>();
        lblCommentairesRabais = new javax.swing.JLabel();
        lblCommentairesRistourne = new javax.swing.JLabel();
        lblCommentairesRemise = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setIconImage(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        cbxLigneType.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbxLigneType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxLigneTypeActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setText("Ligne type");

        lblProduitService.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblProduitService.setText("Produit/Service");

        txtReferenceProduitService.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtReferenceProduitService.setEnabled(false);

        txtDesigantion.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtDesigantion.setEnabled(false);

        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setText("Désignation");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel7.setText("Quantité");

        jLabel8.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel8.setText("Montant unitaire");

        jLabel9.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel9.setText("TVA");

        cbxProduitService.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbxProduitService.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxProduitServiceActionPerformed(evt);
            }
        });

        lblReferenceProduit.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblReferenceProduit.setText("Référence");

        spnQuantite.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        spnQuantite.setModel(new javax.swing.SpinnerNumberModel(1, 0, null, 1));

        spnMontantUnitaire.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        spnMontantUnitaire.setModel(new javax.swing.SpinnerNumberModel(0.0d, 0.0d, null, 1.0d));

        spnTVA.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        spnTVA.setModel(new javax.swing.SpinnerNumberModel(0.0d, 0.0d, 100.0d, 1.0d));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText("%");

        txtQuantiteStockProduit.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtQuantiteStockProduit.setEnabled(false);

        lblQuantiteStock.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblQuantiteStock.setText("Quantité en stock");

        txtUniteProduitService.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtUniteProduitService.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(90, 90, 90)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(spnQuantite, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtUniteProduitService, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblReferenceProduit)
                                    .addComponent(lblQuantiteStock)
                                    .addComponent(lblProduitService))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbxProduitService, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtQuantiteStockProduit, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtReferenceProduitService, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(44, 44, 44))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cbxLigneType, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDesigantion, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addGap(63, 63, 63)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(spnMontantUnitaire, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(spnTVA, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1)
                            .addComponent(cbxDevise, 0, 115, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbxLigneType, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDesigantion, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(spnQuantite, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblQuantiteStock)
                            .addComponent(txtQuantiteStockProduit, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUniteProduitService, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbxProduitService, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblProduitService))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtReferenceProduitService, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblReferenceProduit))
                        .addGap(45, 45, 45)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(spnMontantUnitaire, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxDevise, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(spnTVA, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)))
        );

        jLabel10.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel10.setText("Rabais");

        spnRemise.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        spnRemise.setModel(new javax.swing.SpinnerNumberModel(0.0d, 0.0d, 100.0d, 1.0d));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("%");

        jLabel11.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel11.setText("Ristourne");

        spnRistourne.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        spnRistourne.setModel(new javax.swing.SpinnerNumberModel(0.0d, 0.0d, 100.0d, 1.0d));

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("%");

        jLabel12.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel12.setText("Remise");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setText("%");

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        lblReductionCommerciales.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblReductionCommerciales.setText("Réductions commérciales :");

        lblMontantHT.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblMontantHT.setText("Montant HT :");

        lblValeurReductionsCommerciales.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblValeurReductionsCommerciales.setText("0.0");

        lblValeurMontantHT.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblValeurMontantHT.setText("0.0");

        lblMontantTVA.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblMontantTVA.setText("Montant TVA :");

        lblMontantTTC.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblMontantTTC.setText("Montant TTC :");

        lblValeurMontantTVA.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblValeurMontantTVA.setText("0.0");

        lblNetCommercial.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblNetCommercial.setText("Net commercial :");

        lblValeurNetCommercial.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblValeurNetCommercial.setText("0.0");

        lblValeurMontantTTC.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblValeurMontantTTC.setText("0.0");

        btnCalculerLigne.setBackground(new java.awt.Color(0, 170, 245));
        btnCalculerLigne.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        btnCalculerLigne.setForeground(new java.awt.Color(255, 255, 255));
        btnCalculerLigne.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icons8_pos_terminal_25px.png"))); // NOI18N
        btnCalculerLigne.setText("Calculer la ligne");
        btnCalculerLigne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalculerLigneActionPerformed(evt);
            }
        });

        btnAnnulerLigneFacture.setBackground(new java.awt.Color(0, 170, 245));
        btnAnnulerLigneFacture.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        btnAnnulerLigneFacture.setForeground(new java.awt.Color(255, 255, 255));
        btnAnnulerLigneFacture.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icons8_delete_sign_25px.png"))); // NOI18N
        btnAnnulerLigneFacture.setText("Annuler");
        btnAnnulerLigneFacture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnnulerLigneFactureActionPerformed(evt);
            }
        });

        btnEnregistrerLigneFacture.setBackground(new java.awt.Color(0, 170, 245));
        btnEnregistrerLigneFacture.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        btnEnregistrerLigneFacture.setForeground(new java.awt.Color(255, 255, 255));
        btnEnregistrerLigneFacture.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icons8_micro_sd_25px.png"))); // NOI18N
        btnEnregistrerLigneFacture.setText("Enregistrer");
        btnEnregistrerLigneFacture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnregistrerLigneFactureActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnEnregistrerLigneFacture, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCalculerLigne, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAnnulerLigneFacture, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(lblReductionCommerciales)
                            .addGap(18, 18, 18)
                            .addComponent(lblValeurReductionsCommerciales, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblMontantHT)
                                .addComponent(lblNetCommercial)
                                .addComponent(lblMontantTVA)
                                .addComponent(lblMontantTTC))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblValeurNetCommercial, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblValeurMontantHT, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblValeurMontantTVA, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblValeurMontantTTC, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(511, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblReductionCommerciales)
                    .addComponent(lblValeurReductionsCommerciales))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblValeurNetCommercial)
                    .addComponent(lblNetCommercial))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMontantHT)
                    .addComponent(lblValeurMontantHT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMontantTVA)
                    .addComponent(lblValeurMontantTVA))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMontantTTC)
                    .addComponent(lblValeurMontantTTC))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEnregistrerLigneFacture, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCalculerLigne, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAnnulerLigneFacture, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cbxRabais.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbxRabais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxRabaisActionPerformed(evt);
            }
        });

        lblCommentairesRabais.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        lblCommentairesRistourne.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        lblCommentairesRemise.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        javax.swing.GroupLayout panelReductionLayout = new javax.swing.GroupLayout(panelReduction);
        panelReduction.setLayout(panelReductionLayout);
        panelReductionLayout.setHorizontalGroup(
            panelReductionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelReductionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelReductionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel12)
                    .addComponent(jLabel11))
                .addGap(98, 98, 98)
                .addGroup(panelReductionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(spnRemise, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                    .addComponent(spnRistourne, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                    .addComponent(cbxRabais, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(panelReductionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelReductionLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCommentairesRabais, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelReductionLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCommentairesRemise, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelReductionLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCommentairesRistourne, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelReductionLayout.setVerticalGroup(
            panelReductionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelReductionLayout.createSequentialGroup()
                .addContainerGap(57, Short.MAX_VALUE)
                .addGroup(panelReductionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelReductionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(jLabel10)
                        .addComponent(lblCommentairesRabais))
                    .addComponent(cbxRabais, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelReductionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spnRemise, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel12)
                    .addComponent(lblCommentairesRemise))
                .addGap(18, 18, 18)
                .addGroup(panelReductionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spnRistourne, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel11)
                    .addComponent(lblCommentairesRistourne))
                .addGap(48, 48, 48)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelReduction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelReduction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private boolean setComboboxProduitService(int id_produit_type) throws SQLException {
        produitServiceComboboxModel = new DefaultComboBoxModel();
        PreparedStatement pst;
        ResultSet res;
        int id_produit, id_tva, id_familly_produit, id_devise, quantite_stock;
        double taux_tva, montant_unitaire, remise;
        String designation_type = null, description, symbole, designation_famille, remarque, unite, reference_produit, designation_produit;
        //requete preparee pour récupérer tous les devises
        pst = connexion.prepareStatement("select * from produit as p join produit_type as pt on p.produit_type_id=pt.id_produit_type join famille_produit as fp on fp.id_famille_produit=p.famille_produit_id join tva as t on t.id_tva=p.tva_id join devise as d on d.id_devise=p.devise_id where p.produit_type_id=?");
        pst.setInt(1, id_produit_type);

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

                switch (designation_type) {
                    case "produit":
                        //Si le type de poduit préselectionné est de type produit
                        Produit produit = new Produit(quantite_stock, id_produit, familleProduit, produitType, reference_produit, designation_produit, montant_unitaire, unite, remise, devise, tva);
                        produitServiceComboboxModel.addElement(produit); //Ajouter l'objet dans le ComboboxModel
                        break;
                    case "service":
                        //Si le type de poduit préselectionné est de type service
                        Service service = new Service(id_produit, familleProduit, produitType, reference_produit, designation_produit, montant_unitaire, unite, remise, devise, tva);
                        produitServiceComboboxModel.addElement(service); //Ajouter l'objet dans le ComboboxModel
                        break;
                    default:
                        //Vider le combobox produit/service pour une ligne facture de type réduction
                        DefaultComboBoxModel comboboxModel = (DefaultComboBoxModel) cbxProduitService.getModel();
                        comboboxModel.removeAllElements();
                        break;
                }
            }

            cbxProduitService.setModel(produitServiceComboboxModel);//Inserer le comboboxModel dans le composant
            if (designation_type.equals("produit")) { //Si le type de produit préselectionné est de type produit
                txtQuantiteStockProduit.setVisible(true);//Faire apparaître le champ quantité de stock avec son label
                lblQuantiteStock.setVisible(true);
            } else if (designation_type.equals("service")) { //Si le type de produit préselectionné est de type service
                txtQuantiteStockProduit.setVisible(false);//Masquer le champ quantité de stock avec son label
                lblQuantiteStock.setVisible(false);
            }
            /*
            Ici c'est l'autocompletion du combobox Produit/service
             */
            AutoCompleteDecorator.decorate(cbxProduitService);
            return true;
        } else {

            JOptionPane.showMessageDialog(null, "Produit introuvable dans la base de données !", "Erreur",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private boolean initialiserComboboxProduitType() throws SQLException {
        cbxLigneTypeModel = new DefaultComboBoxModel();
        PreparedStatement pst;
        ResultSet res;
        int id_produit_type;
        String designation_type;
        //requete preparee pour récupérer tous les devises
        pst = connexion.prepareStatement("select * from produit_type");

        res = pst.executeQuery();
        if (res != null) { //Tester si la requête retourne des résultats non vides
            while (res.next()) { //Parcourir tous les résultats
                id_produit_type = res.getInt("id_produit_type");
                designation_type = res.getString("designation_type");

                ProduitType produitType = new ProduitType(id_produit_type, designation_type); //Création d'un objet ProduitType
                cbxLigneTypeModel.addElement(produitType); //Ajouter l'objet dans le ComboboxModel
            }

            cbxLigneType.setModel(cbxLigneTypeModel);//Inserer le comboboxModel dans le composant

            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Type de ligne facture introuvable dans la base de données !", "Erreur",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private boolean initialiserComboboxDevise() throws SQLException {
        cbxDevisemodel = new DefaultComboBoxModel();
        PreparedStatement pst;
        ResultSet res;
        int id_devise;
        String description, symbole;
        //requete preparee pour récupérer tous les devises
        pst = connexion.prepareStatement("select * from devise");

        res = pst.executeQuery();
        if (res != null) { //Tester si la requête retourne des résultats non vides
            while (res.next()) { //Parcourir tous les résultats
                id_devise = res.getInt("id_devise");
                description = res.getString("description");
                symbole = res.getString("symbole");

                Devise devise = new Devise(id_devise, description, symbole);
                cbxDevisemodel.addElement(devise); //Ajouter l'objet dans le ComboboxModel
            }

            cbxDevise.setModel(cbxDevisemodel);//Inserer le comboboxModel dans le composant

            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Devise introuvable dans la base de données !", "Erreur",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private boolean initialiserComboboxRabais() throws SQLException {
        DefaultComboBoxModel cbxRabaisModel = new DefaultComboBoxModel();
        cbxRabaisModel.addElement(new Rabais(0.0, "")); //Valeur par defaut

        if (!factureModele.getTousRabais().isEmpty()) { //Tester si la requête retourne des résultats non vides
            for (Rabais rabais : factureModele.getTousRabais()) {

                cbxRabaisModel.addElement(rabais); //Ajouter l'objet dans le ComboboxModel
            }

            cbxRabais.setModel(cbxRabaisModel);//Inserer le comboboxModel dans le composant

            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Rabais introuvable dans la base de données !", "Erreur",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private boolean initialiserRemiseClient() throws SQLException {
        ProduitType produitType = (ProduitType) cbxLigneType.getSelectedItem();
        ArrayList<Remise> remises = null;
        if (produitType != null) {

            if (produitType.getDesignation().equals("produit")) {
                Produit produit = (Produit) cbxProduitService.getSelectedItem();

                if (produit != null) {

                    int nombreProduits = factureModele.nombreProduitsCommande(facture.getClient(), produit);
                    remises = factureModele.getTousRemise();

                    if (remises != null) {

                        for (Remise remise : remises) {
                            if (nombreProduits > remise.getNombreProduits()) { //Si le nombre de produits commandés dépasse le seuil
                                spnRemise.setValue(remise.getTauxRemise());
                                lblCommentairesRemise.setText("Le client a commandé plus de " + remise.getNombreProduits() + " sur ce produit: " + nombreProduits + " produits.");
                                return true;
                            }
                        }

                    } else {
                        return false;
                    }

                } else {
                    return false;
                }

            } else {
                return false;
            }

        } else {
            return false;
        }
        return false;
    }

    private boolean initialiserRistourneClient() throws SQLException {
        InfosClientFrame infosClientFrame = new InfosClientFrame(facture.getClient());
        ArrayList<Ristourne> ristournes = null;

        int nombreCommandes = infosClientFrame.getTotalFacture();
        ristournes = factureModele.getTousRistourne();

        if (ristournes != null) {
            for (Ristourne ristourne : ristournes) {
                if (nombreCommandes > ristourne.getNombreCommandes()) {
                    spnRistourne.setValue(ristourne.getTauxRistourne());
                    lblCommentairesRistourne.setText("Le client a dépassé la/les " + ristourne.getNombreCommandes() + " commande(s): " + nombreCommandes + " commande(s)");
                }
                break; //Récupérer la première valeur de la ristourne pour la sécurité
            }
        } else {
            return false;
        }

        return true;
    }

    private void initialiserValeurComposants() throws SQLException {
        //Remplir le combobox type de produit. Les donnes sont récuperées de la base de données 
        initialiserComboboxProduitType();
        initialiserComboboxDevise();
        initialiserComboboxRabais();
        initialiserRistourneClient();
        setIcon();
    }

    private boolean calculerLigne() {
        ProduitType produitType = (ProduitType) cbxLigneType.getSelectedItem();
        Rabais rabaisSelectionnee = (Rabais) cbxRabais.getSelectedItem();
        boolean reussi = true;
        /*
            Les attributs ci-dessous sont des attributs communs aux trois types de produit
         */
        int dernierNumeroLigne = facture.getDernierNumeroLigne();
        int nouveauNumeroLigne = ++dernierNumeroLigne;
        int quantite = (Integer) spnQuantite.getValue();
        double tva = Double.parseDouble(spnTVA.getValue().toString());
        double montantUnitaire = (double) spnMontantUnitaire.getValue();
        double rabais = rabaisSelectionnee.getTaux();
        double remise = Double.parseDouble(spnRemise.getValue().toString());
        double ristourne = Double.parseDouble(spnRistourne.getValue().toString());


        /*
        Les instruction ci-dessous sont les traitements spécifique pour chaque type de produit
         */
        switch (produitType.getDesignation()) {
            case "produit":
                //Si le type est un produit
                Produit produit = (Produit) cbxProduitService.getSelectedItem();
                if (produit == null) {
                    reussi = false;
                    JOptionPane.showMessageDialog(null, "Veuillez selectionner un produit !", "Erreur",
                            JOptionPane.ERROR_MESSAGE);
                    break;
                } else {

                    if (quantite > produit.getQuantite_stock()) { //Si la quantité saisie est supérieure à la quantité disponible dans le stock
                        reussi = false;
                        JOptionPane.showMessageDialog(null, "Quantité en stock non disponible !", "Erreur",
                                JOptionPane.ERROR_MESSAGE);
                        break;
                    } else {
                        LigneProduit ligneProduit = new LigneProduit(nouveauNumeroLigne, produitType.getDesignation(), quantite, montantUnitaire, tva, produit, rabais, remise, ristourne);
                        ligneProduit.calculerLigne();
                        ligneFacture = ligneProduit;
                        break;
                    }

                }

            case "service":
                //Si le type est un service
                Service service = (Service) cbxProduitService.getSelectedItem();
                if (service == null) {
                    reussi = false;
                    JOptionPane.showMessageDialog(null, "Veuillez selectionner un produit !", "Erreur",
                            JOptionPane.ERROR_MESSAGE);
                    break;
                } else {
                    LigneProduit ligneProduit = new LigneProduit(nouveauNumeroLigne, produitType.getDesignation(), quantite, montantUnitaire, tva, service, rabais, remise, ristourne);
                    ligneProduit.calculerLigne();
                    ligneFacture = ligneProduit;
                    break;
                }

            case "expedition":
                //Si le type est expedition
                String designationExpedition = txtDesigantion.getText();
                //Les lignes de type expédition instancie la classe LigneExpedition
                ligneFacture = new LigneExpedition(nouveauNumeroLigne, produitType.getDesignation(), designationExpedition, quantite, montantUnitaire, tva);
                ligneFacture.calculerLigne();
                break;

            default:
                //autre type
                String designationAutre = txtDesigantion.getText();
                //Les produits de type autre instancie la classe LigneProduit
                ligneFacture = new LigneProduit(nouveauNumeroLigne, produitType.getDesignation(), designationAutre, quantite, montantUnitaire, tva, rabais, remise, ristourne);
                ligneFacture.calculerLigne();
                break;
        }
        return reussi;
    }

    private void afficherResultatCalculLigne() {
        if (ligneFacture != null) {
            if (ligneFacture instanceof LigneExpedition) {
                LigneExpedition ligneExpedition = (LigneExpedition) ligneFacture;
                lblValeurReductionsCommerciales.setText("0 " + cbxDevise.getSelectedItem());
                lblValeurNetCommercial.setText("0 " + cbxDevise.getSelectedItem());
                lblValeurMontantHT.setText(String.valueOf(new BigDecimal(ligneExpedition.getMontantHT()).setScale(2, BigDecimal.ROUND_UP)) + " " + cbxDevise.getSelectedItem());
                lblValeurMontantTVA.setText(String.valueOf(new BigDecimal(ligneExpedition.getMontantTVA()).setScale(2, BigDecimal.ROUND_UP)) + " " + cbxDevise.getSelectedItem());
                lblValeurMontantTTC.setText(String.valueOf(new BigDecimal(ligneExpedition.getMontantTTC()).setScale(2, BigDecimal.ROUND_UP)) + " " + cbxDevise.getSelectedItem());
            } else {
                LigneProduit ligneProduit = (LigneProduit) ligneFacture;
                lblValeurReductionsCommerciales.setText(String.valueOf(new BigDecimal(ligneProduit.getReductionsCommerciales()).setScale(2, BigDecimal.ROUND_UP)) + " " + cbxDevise.getSelectedItem());
                lblValeurNetCommercial.setText(String.valueOf(new BigDecimal(ligneProduit.getNetCommercial()).setScale(2, BigDecimal.ROUND_UP)) + " " + cbxDevise.getSelectedItem());
                lblValeurMontantHT.setText(String.valueOf(new BigDecimal(ligneFacture.getMontantHT()).setScale(2, BigDecimal.ROUND_UP)) + " " + cbxDevise.getSelectedItem());
                lblValeurMontantTVA.setText(String.valueOf(new BigDecimal(ligneFacture.getMontantTVA()).setScale(2, BigDecimal.ROUND_UP)) + " " + cbxDevise.getSelectedItem());
                lblValeurMontantTTC.setText(String.valueOf(new BigDecimal(ligneFacture.getMontantTTC()).setScale(2, BigDecimal.ROUND_UP)) + " " + cbxDevise.getSelectedItem());
            }

        }
    }

    private void enregistrerLigne() {
        facture.ajouterLigne(ligneFacture); //Ajouter l'objet LigneFacture dans la collection de lignes de l'objet facture
        factureForm.ajouterNouvelleLigne(ligneFacture); //Ajouter ce nouvel objet LigneFacture dans le tableau de la facture
        factureForm.mettreAJourFacture();
        facture.incrementerNumeroLigne();
        factureForm.configurerCbxClientFacture();
        this.dispose();
    }

    private void cbxLigneTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxLigneTypeActionPerformed
        ProduitType produitType = (ProduitType) cbxLigneType.getSelectedItem();
        if (produitType.getDesignation().equals("autre")) {
            try {
                //Si la ligne facture selectionnée est de type autre
                txtDesigantion.setEnabled(true);
                txtUniteProduitService.setEnabled(true);

                //Vider le combobox produit/service pour une ligne facture de type réduction
                DefaultComboBoxModel comboboxModel = (DefaultComboBoxModel) cbxProduitService.getModel();
                comboboxModel.removeAllElements();

                //Reinitialiser le combobox devise
                initialiserComboboxDevise();

            } catch (SQLException ex) {
                Logger.getLogger(LigneFactureForm.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (produitType.getDesignation().equals("expedition")) {
            try {

                cbxRabais.setEnabled(false);
                spnRemise.setEnabled(false);
                spnRistourne.setEnabled(false);
                //Si la ligne facture selectionnée est de type autre
                txtDesigantion.setEnabled(true);
                txtUniteProduitService.setEnabled(true);
                spnTVA.setValue(20);

                //Vider le combobox produit/service pour une ligne facture de type réduction
                DefaultComboBoxModel comboboxModel = (DefaultComboBoxModel) cbxProduitService.getModel();
                comboboxModel.removeAllElements();

                //Reinitialiser le combobox devise
                initialiserComboboxDevise();

            } catch (SQLException ex) {
                Logger.getLogger(LigneFactureForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {

            try {
                //Remplir le combobox produit/service en produit ou service selon l'id du type de produit préselectionné dans le combobox type de produit
                setComboboxProduitService(produitType.getId());

            } catch (SQLException ex) {
                Logger.getLogger(LigneFactureForm.class.getName()).log(Level.SEVERE, null, ex);
            }
            txtDesigantion.setEnabled(false);
            txtUniteProduitService.setEnabled(false);
        }
    }//GEN-LAST:event_cbxLigneTypeActionPerformed

    private void cbxProduitServiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxProduitServiceActionPerformed
        if (cbxProduitService.getSelectedItem() instanceof Produit) {
            try {

                Produit produit = (Produit) cbxProduitService.getSelectedItem();
                txtReferenceProduitService.setText(produit.getReference());
                txtDesigantion.setText(produit.getDesignation());
                txtQuantiteStockProduit.setVisible(true);//Faire apparaître le champ quantité de stock avec son label
                lblQuantiteStock.setVisible(true);
                txtQuantiteStockProduit.setText(String.valueOf(produit.getQuantite_stock()));
                spnMontantUnitaire.setValue(produit.getMontant_unitaire());
                txtUniteProduitService.setText(produit.getUnite());
                DefaultComboBoxModel modele = (DefaultComboBoxModel) cbxDevise.getModel();
                modele.removeAllElements();
                modele.addElement(produit.getDevise());
                cbxDevise.setModel(modele);
                spnTVA.setValue(produit.getTva().getTaux());
                if (!initialiserRemiseClient()) {
                    spnRemise.setValue(produit.getRemise());
                }

            } catch (SQLException ex) {
                Logger.getLogger(LigneFactureForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (cbxProduitService.getSelectedItem() instanceof Service) {
            Service service = (Service) cbxProduitService.getSelectedItem();
            txtReferenceProduitService.setText(service.getReference());
            txtDesigantion.setText(service.getDesignation());
            txtQuantiteStockProduit.setVisible(false);//Masquer le champ quantité de stock avec son label
            lblQuantiteStock.setVisible(false);
            spnMontantUnitaire.setValue(service.getMontant_unitaire());
            txtUniteProduitService.setText(service.getUnite());
            DefaultComboBoxModel modele = (DefaultComboBoxModel) cbxDevise.getModel();
            modele.removeAllElements();
            modele.addElement(service.getDevise());
            cbxDevise.setModel(modele);
            spnTVA.setValue(service.getTva().getTaux());
            spnRemise.setValue(service.getRemise());
        }
    }//GEN-LAST:event_cbxProduitServiceActionPerformed

    private void btnCalculerLigneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalculerLigneActionPerformed
        //Ici c'est pour calculer une ligne de facture selon le formulaire et juste afficher les résultats mais pas enregistrer
        if (calculerLigne()) {
            afficherResultatCalculLigne(); //Affiche le resultat du calcul de la ligne sur les labels
        }
    }//GEN-LAST:event_btnCalculerLigneActionPerformed

    private void btnAnnulerLigneFactureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnnulerLigneFactureActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnAnnulerLigneFactureActionPerformed

    private void btnEnregistrerLigneFactureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnregistrerLigneFactureActionPerformed
        if (calculerLigne()) {
            afficherResultatCalculLigne(); //Affiche le resultat du calcul de la ligne sur les labels
            //Ajouter cet objet ligne dans l'objet facture
            enregistrerLigne();
        }
    }//GEN-LAST:event_btnEnregistrerLigneFactureActionPerformed

    private void cbxRabaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxRabaisActionPerformed
        Rabais rabais = (Rabais) cbxRabais.getSelectedItem();
        if (rabais != null) {
            lblCommentairesRabais.setText(rabais.getCommentaires());
        }
    }//GEN-LAST:event_cbxRabaisActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                LigneFactureForm dialog = new LigneFactureForm(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage("icons8_bullish_filled_75px.png"));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnnulerLigneFacture;
    private javax.swing.JButton btnCalculerLigne;
    private javax.swing.JButton btnEnregistrerLigneFacture;
    private javax.swing.JComboBox<String> cbxDevise;
    private javax.swing.JComboBox<String> cbxLigneType;
    private javax.swing.JComboBox<String> cbxProduitService;
    private javax.swing.JComboBox<String> cbxRabais;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lblCommentairesRabais;
    private javax.swing.JLabel lblCommentairesRemise;
    private javax.swing.JLabel lblCommentairesRistourne;
    private javax.swing.JLabel lblMontantHT;
    private javax.swing.JLabel lblMontantTTC;
    private javax.swing.JLabel lblMontantTVA;
    private javax.swing.JLabel lblNetCommercial;
    private javax.swing.JLabel lblProduitService;
    private javax.swing.JLabel lblQuantiteStock;
    private javax.swing.JLabel lblReductionCommerciales;
    private javax.swing.JLabel lblReferenceProduit;
    private javax.swing.JLabel lblValeurMontantHT;
    private javax.swing.JLabel lblValeurMontantTTC;
    private javax.swing.JLabel lblValeurMontantTVA;
    private javax.swing.JLabel lblValeurNetCommercial;
    private javax.swing.JLabel lblValeurReductionsCommerciales;
    private javax.swing.JPanel panelReduction;
    private javax.swing.JSpinner spnMontantUnitaire;
    private javax.swing.JSpinner spnQuantite;
    private javax.swing.JSpinner spnRemise;
    private javax.swing.JSpinner spnRistourne;
    private javax.swing.JSpinner spnTVA;
    private javax.swing.JTextField txtDesigantion;
    private javax.swing.JTextField txtQuantiteStockProduit;
    private javax.swing.JTextField txtReferenceProduitService;
    private javax.swing.JTextField txtUniteProduitService;
    // End of variables declaration//GEN-END:variables
}
