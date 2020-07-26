package presentation.produit;

import donnees.ProduitModele;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
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
public class ProduitForm extends javax.swing.JDialog {

    private String typeOperation;
    private ProduitAbstrait produit;
    private ProduitMainFrame produitMainFrame;
    private ProduitModele produitModele;

    public ProduitForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public ProduitForm(java.awt.Frame parent, boolean modal, ProduitMainFrame produitMainFrame, String typeOperation) throws SQLException {
        this(parent, modal);
        this.produitMainFrame = produitMainFrame;
        this.typeOperation = typeOperation;
        produitModele = new ProduitModele();
        initialiserValeurComposants();
    }

    public ProduitForm(java.awt.Frame parent, boolean modal, ProduitMainFrame produitMainFrame, String typeOperation, ProduitAbstrait produit) throws SQLException {
        this(parent, modal, produitMainFrame, typeOperation);
        this.produit = produit;

        if (typeOperation.equals("modification")) {
            initialiserFormulaire();
        } else {
            JOptionPane.showMessageDialog(null, "Le produit à éditer ne peut pas être récupéré !", "Erreur",
                    JOptionPane.ERROR_MESSAGE);

        }
    }

    private void initialiserValeurComposants() throws SQLException {
        initialiserTitre();
        initialiserComboboxType();
        initialiserComboboxFamille();
        initialiserComboboxDevise();
        initialiserComboboxTVA();
        lblNouvelleFamille.setVisible(false);
        txtNouvelleFamille.setVisible(false);
        if (typeOperation.equals("modification")) {
            btnNouvelleFamille.setVisible(false);
            lblQuantite.setVisible(false);
            spnQuantite.setVisible(false);
        }
    }

    private void initialiserTitre() {
        if (typeOperation.equals("nouveau")) {
            setTitle("Nouveau produit");
        } else {
            setTitle("Modification d'un produit");
        }
    }

    private boolean initialiserComboboxType() throws SQLException {
        DefaultComboBoxModel typeComboboxModel = new DefaultComboBoxModel();

        if (!produitModele.getTousTypes().isEmpty()) { //Tester si la requête retourne des résultats non vides
            for (ProduitType produitType : produitModele.getTousTypes()) {
                if (produitType.getDesignation().equals("produit") || produitType.getDesignation().equals("service")) {
                    typeComboboxModel.addElement(produitType); //Ajouter l'objet dans le ComboboxModel
                }
            }

            cbxType.setModel(typeComboboxModel);//Inserer le comboboxModel dans le composant

            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Profil utilisateur introuvable dans la base de données !", "Erreur",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private boolean initialiserComboboxFamille() throws SQLException {
        DefaultComboBoxModel familleComboboxModel = new DefaultComboBoxModel();

        if (!produitModele.getToutesFamilles().isEmpty()) { //Tester si la requête retourne des résultats non vides
            for (FamilleProduit famille : produitModele.getToutesFamilles()) {

                familleComboboxModel.addElement(famille); //Ajouter l'objet dans le ComboboxModel
            }

            cbxFamille.setModel(familleComboboxModel);//Inserer le comboboxModel dans le composant

            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Profil utilisateur introuvable dans la base de données !", "Erreur",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private boolean initialiserComboboxDevise() throws SQLException {
        DefaultComboBoxModel cbxDeviseModel = new DefaultComboBoxModel();

        if (!produitModele.getTousDevises().isEmpty()) { //Tester si la requête retourne des résultats non vides
            for (Devise devise : produitModele.getTousDevises()) {

                cbxDeviseModel.addElement(devise); //Ajouter l'objet dans le ComboboxModel
            }

            cbxDevise.setModel(cbxDeviseModel);//Inserer le comboboxModel dans le composant

            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Les devises introuvables dans la base de données !", "Erreur",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private boolean initialiserComboboxTVA() throws SQLException {
        DefaultComboBoxModel cbxTVAModel = new DefaultComboBoxModel();

        if (!produitModele.getTousTVA().isEmpty()) { //Tester si la requête retourne des résultats non vides
            for (TVA tva : produitModele.getTousTVA()) {

                cbxTVAModel.addElement(tva); //Ajouter l'objet dans le ComboboxModel
            }

            cbxTVA.setModel(cbxTVAModel);//Inserer le comboboxModel dans le composant

            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Les TVA introuvables dans la base de données !", "Erreur",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private void initialiserFormulaire() {
        txtReference.setText(produit.getReference());
        txtDesignation.setText(produit.getDesignation());
        spnMontantUnitaire.setValue(produit.getMontant_unitaire());
        spnRemise.setValue(produit.getRemise());
    }

    private boolean enregistrerProduit() throws SQLException {
        FamilleProduit nouvelleFamille = null;
        if (txtNouvelleFamille.isVisible()) {
            if (txtNouvelleFamille.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Veuillez remplir le champ nouvelle famille de produit", "Erreur",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }

            if (produitModele.isFamilleExiste(txtNouvelleFamille.getText())) {
                JOptionPane.showMessageDialog(null, "Cette famille de produit existe déjà. Choisissez un autre nom", "Erreur",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }

            if (produitModele.enregistrerFamille(txtNouvelleFamille.getText())) {
                //Enregistrement de la nouvelle famille de produit
                nouvelleFamille = produitModele.getFamille(txtNouvelleFamille.getText());

            } else {
                JOptionPane.showMessageDialog(null, "Erreur lors de l'nregistrement de la nouvelle famille produit", "Erreur",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }

        }
        if (!txtDesignation.getText().equals("")) {
            ProduitType produitType = (ProduitType) cbxType.getSelectedItem();
            FamilleProduit famille = (FamilleProduit) cbxFamille.getSelectedItem();
            Devise devise = (Devise) cbxDevise.getSelectedItem();
            TVA tva = (TVA) cbxTVA.getSelectedItem();

            if (produitType != null && famille != null && devise != null) {
                if (produitType.getDesignation().equals("produit")) {

                    Produit article = new Produit();
                    article.setProduit_type(produitType);
                    if (nouvelleFamille == null) {
                        article.setFamille(famille);
                    } else {
                        article.setFamille(nouvelleFamille);
                    }
                    article.setDevise(devise);
                    article.setTva(tva);
                    article.setReference(txtReference.getText());
                    article.setDesignation(txtDesignation.getText());
                    article.setUnite(txtUnite.getText());
                    article.setMontant_unitaire((double) spnMontantUnitaire.getValue());
                    article.setQuantite_stock((int) spnQuantite.getValue());
                    if (typeOperation.equals("modification")) {
                        article.setId(produit.getId()); // pour la modification on a besoin de connaître d'identifiant du produit à modifier qui est passé par le constructeur mais cela n'implique rien pour l'ajout
                    }
                    produit = article;

                } else if (produitType.getDesignation().equals("service")) {

                    Service service = new Service();
                    service.setProduit_type(produitType);
                    if (nouvelleFamille == null) {
                        service.setFamille(famille);
                    } else {
                        service.setFamille(nouvelleFamille);
                    }
                    service.setDevise(devise);
                    service.setTva(tva);
                    service.setReference(txtReference.getText());
                    service.setDesignation(txtDesignation.getText());
                    service.setUnite(txtUnite.getText());
                    service.setMontant_unitaire((double) spnMontantUnitaire.getValue());
                    if (typeOperation.equals("modification")) {
                        service.setId(produit.getId());
                    }
                    produit = service;

                }

            } else {
                JOptionPane.showMessageDialog(null, "Veuillez selectionner le type de produit et la famille ainsi que la devise", "Erreur",
                        JOptionPane.ERROR_MESSAGE);
                return false;

            }
        } else {
            JOptionPane.showMessageDialog(null, "Veuillez bien remplir au moins le champ designation", "Erreur",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txtNouvelleFamille = new javax.swing.JTextField();
        txtDesignation = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblQuantite = new javax.swing.JLabel();
        cbxFamille = new javax.swing.JComboBox<>();
        lblFamille = new javax.swing.JLabel();
        cbxType = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        btnNouvelleFamille = new javax.swing.JButton();
        txtReference = new javax.swing.JTextField();
        lblNouvelleFamille = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        spnRemise = new javax.swing.JSpinner();
        spnMontantUnitaire = new javax.swing.JSpinner();
        jLabel14 = new javax.swing.JLabel();
        cbxDevise = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        spnQuantite = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        txtUnite = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbxTVA = new javax.swing.JComboBox<>();
        btnEnregistrerProduit = new javax.swing.JButton();
        btnAnnulerProduit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(0, 170, 245));

        jLabel21.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Informations Produit");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(240, 240, 240), null));

        txtNouvelleFamille.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        txtDesignation.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setText("Designation");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setText("Référence");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel7.setText("Prix unitaire HT");

        lblQuantite.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblQuantite.setText("Quanité à ajouter");

        cbxFamille.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxFamilleActionPerformed(evt);
            }
        });

        lblFamille.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblFamille.setText("Famille");

        cbxType.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbxType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxTypeActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel11.setText("Type");

        btnNouvelleFamille.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnNouvelleFamille.setText("+");
        btnNouvelleFamille.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNouvelleFamilleActionPerformed(evt);
            }
        });

        txtReference.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lblNouvelleFamille.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblNouvelleFamille.setText("Designation nouvelle famille");

        jLabel13.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel13.setText("Taux de remise");

        spnRemise.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        spnRemise.setModel(new javax.swing.SpinnerNumberModel(0.0d, 0.0d, 100.0d, 1.0d));

        spnMontantUnitaire.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        spnMontantUnitaire.setModel(new javax.swing.SpinnerNumberModel(0.0d, null, null, 1.0d));

        jLabel14.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel14.setText("TVA");

        cbxDevise.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxDeviseActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel15.setText("Devise");

        spnQuantite.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        spnQuantite.setModel(new javax.swing.SpinnerNumberModel(1, 0, null, 1));

        jLabel3.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel3.setText("%");

        txtUnite.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel9.setText("Unité");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel4.setText("%");

        cbxTVA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxTVAActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(lblFamille)
                                .addGap(632, 632, 632)
                                .addComponent(btnNouvelleFamille))
                            .addComponent(jLabel11)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(207, 207, 207)
                        .addComponent(spnMontantUnitaire, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNouvelleFamille)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel15)
                            .addComponent(lblQuantite)
                            .addComponent(jLabel14)
                            .addComponent(jLabel9)
                            .addComponent(jLabel13))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(spnQuantite, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDesignation)
                            .addComponent(txtNouvelleFamille)
                            .addComponent(cbxFamille, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbxType, 0, 479, Short.MAX_VALUE)
                            .addComponent(txtReference)
                            .addComponent(cbxDevise, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUnite)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(cbxTVA, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(spnRemise, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4)))))
                .addContainerGap(111, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(cbxType, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxFamille, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFamille)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(btnNouvelleFamille, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNouvelleFamille, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNouvelleFamille))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtReference, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtDesignation, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spnMontantUnitaire, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxDevise, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spnQuantite, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblQuantite))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtUnite, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(cbxTVA, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spnRemise, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel4))
                .addGap(21, 21, 21))
        );

        btnEnregistrerProduit.setBackground(new java.awt.Color(0, 170, 245));
        btnEnregistrerProduit.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        btnEnregistrerProduit.setForeground(new java.awt.Color(255, 255, 255));
        btnEnregistrerProduit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icons8_micro_sd_25px.png"))); // NOI18N
        btnEnregistrerProduit.setText("Enregistrer");
        btnEnregistrerProduit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnregistrerProduitActionPerformed(evt);
            }
        });

        btnAnnulerProduit.setBackground(new java.awt.Color(0, 170, 245));
        btnAnnulerProduit.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        btnAnnulerProduit.setForeground(new java.awt.Color(255, 255, 255));
        btnAnnulerProduit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icons8_delete_sign_25px.png"))); // NOI18N
        btnAnnulerProduit.setText("Annuler");
        btnAnnulerProduit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnnulerProduitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnEnregistrerProduit, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAnnulerProduit, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEnregistrerProduit, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAnnulerProduit, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbxFamilleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxFamilleActionPerformed
        lblNouvelleFamille.setVisible(false);
        txtNouvelleFamille.setVisible(false);
    }//GEN-LAST:event_cbxFamilleActionPerformed

    private void btnEnregistrerProduitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnregistrerProduitActionPerformed
        if (typeOperation.equals("nouveau")) {

            try {

                if (enregistrerProduit()) {

                    if (produitModele.enregistrerProduit(produit)) {
                        try {

                            produitMainFrame.afficherTousProduits();
                            JOptionPane.showMessageDialog(null, "Produit bien enregistré", "Information",
                                    JOptionPane.INFORMATION_MESSAGE);
                            this.dispose();

                        } catch (SQLException ex) {
                            Logger.getLogger(ProduitForm.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }

            } catch (SQLException ex) {
                Logger.getLogger(ProduitForm.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {

            try {

                if (enregistrerProduit()) {
                    if (produitModele.modifierProduit(produit)) {
                        produitMainFrame.afficherTousProduits();
                        JOptionPane.showMessageDialog(null, "Utilisateur à jour", "Information",
                                JOptionPane.INFORMATION_MESSAGE);
                        this.dispose();
                    }
                }

            } catch (SQLException ex) {
                Logger.getLogger(ProduitForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnEnregistrerProduitActionPerformed

    private void btnAnnulerProduitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnnulerProduitActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnAnnulerProduitActionPerformed

    private void cbxTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTypeActionPerformed

    }//GEN-LAST:event_cbxTypeActionPerformed

    private void cbxDeviseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxDeviseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxDeviseActionPerformed

    private void btnNouvelleFamilleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNouvelleFamilleActionPerformed
        lblNouvelleFamille.setVisible(true);
        txtNouvelleFamille.setVisible(true);
    }//GEN-LAST:event_btnNouvelleFamilleActionPerformed

    private void cbxTVAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTVAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxTVAActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ProduitForm dialog = new ProduitForm(new javax.swing.JFrame(), true);
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnnulerProduit;
    private javax.swing.JButton btnEnregistrerProduit;
    private javax.swing.JButton btnNouvelleFamille;
    private javax.swing.JComboBox<String> cbxDevise;
    private javax.swing.JComboBox<String> cbxFamille;
    private javax.swing.JComboBox<String> cbxTVA;
    private javax.swing.JComboBox<String> cbxType;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lblFamille;
    private javax.swing.JLabel lblNouvelleFamille;
    private javax.swing.JLabel lblQuantite;
    private javax.swing.JSpinner spnMontantUnitaire;
    private javax.swing.JSpinner spnQuantite;
    private javax.swing.JSpinner spnRemise;
    private javax.swing.JTextField txtDesignation;
    private javax.swing.JTextField txtNouvelleFamille;
    private javax.swing.JTextField txtReference;
    private javax.swing.JTextField txtUnite;
    // End of variables declaration//GEN-END:variables
}
