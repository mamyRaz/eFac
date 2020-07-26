package presentation.facture;

import com.itextpdf.text.DocumentException;
import donnees.Database;
import donnees.FactureModele;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import metier.Facture;
import metier.FacturePdf;
import metier.Utilisateur;

public class FactureMainFrame extends javax.swing.JFrame {

    private Utilisateur utilisateur;
    private ArrayList<Facture> factures;
    private final SimpleDateFormat affichageDateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private final Database db;
    private final Connection connexion;
    private int totalFacture;
    private int totalPayees;
    private int totalImpayees;
    private double totalSoldeImpayees;

    public FactureMainFrame() throws SQLException {
        initComponents();
        db = new Database();
        connexion = db.getConnetion();
        initialiserListeFactures();
        initialiserValeurComposants();
    }

    public FactureMainFrame(Utilisateur u) throws SQLException {
        this();
        this.utilisateur = u;
        configurerComposantsUtilisateur();
    }

    public void ajouterFacture(Facture facture) {
        //On met toujours une nouvelle facture en indice 0 c'est-à-dire, en en-tête de la liste
        factures.add(0, facture);
    }

    public void supprimerFacture(Facture facture) {
        factures.remove(facture);
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    private void initialiserListeFactures() {
        factures = new ArrayList<>();

    }

    public void afficherTousFactures() throws SQLException {

        DefaultTableModel tableFactureModele = (DefaultTableModel) tblFacture.getModel(); //Récupérer le modèle de la table des factures
        if (tableFactureModele != null) {
            tableFactureModele.setRowCount(0); //Vider le modele de la table facture
        }

        FactureModele factureModele = new FactureModele(); //La classe contenant les requêtes sur les factures
        factures = factureModele.getTousFacture();
        /*
            Les variables ici concernent les statistiques. A chaque mise à jour du tableau, on établit la statistique.
            Donc chaque variable devrait être initialisée
         */
        totalPayees = 0;
        totalImpayees = 0;
        totalSoldeImpayees = 0;
        totalFacture = factures.size();

        if (factures.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Le liste des factures est vide !", "Information",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            Object row[];
            for (Facture facture : factures) {
                row = new Object[9];
                row[0] = facture; //Execute la méthode toString de la classe Facture qui affiche le numéro de la ligne et le type de produit (produit, service, reduction)
                row[1] = facture.getClient();
                row[2] = affichageDateFormat.format(Date.valueOf(facture.getDateFacture()));
                row[3] = affichageDateFormat.format(Date.valueOf(facture.getDateDelais()));
                row[4] = (new BigDecimal(facture.getTotalTTC()).setScale(2, BigDecimal.ROUND_UP)) + " €";
                row[5] = (new BigDecimal(facture.getNetAPayer()).setScale(2, BigDecimal.ROUND_UP)) + " €";
                row[6] = facture.getEtat();
                row[7] = (new BigDecimal(facture.getSoldeImpayees()).setScale(2, BigDecimal.ROUND_UP)) + " €";
                row[8] = (new BigDecimal(facture.getSoldePayees()).setScale(2, BigDecimal.ROUND_UP)) + " €";

                if (facture.getEtat().equals("payee")) {
                    totalPayees++;
                } else if (facture.getEtat().equals("impayee")) {
                    totalImpayees++;
                }

                totalSoldeImpayees += facture.getSoldeImpayees();

                tableFactureModele.addRow(row);
            }
        }

        mettreAJourStatistique(); //Affichage des statistiques
    }

    public void mettreAJourFactureDansListe(int indice, Facture f) { //Mise a jour de notre ArrayList
        factures.set(indice, f);
    }

    public void mettreAJourStatistique() {
        lblTotalFacture.setText(String.valueOf(totalFacture));
        lblTotalPayees.setText(String.valueOf(totalPayees));
        lblTotalImpayees.setText(String.valueOf(totalImpayees));
        lblTotalSolde.setText((new BigDecimal(totalSoldeImpayees).setScale(2, BigDecimal.ROUND_UP)) + " €");
    }

    private void initialiserTableauDesFacture() throws SQLException {
        tblFacture.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 14));
        tblFacture.getTableHeader().setBackground(Color.WHITE);
        tblFacture.setRowHeight(35);
        tblFacture.setFont(new Font("Arial", Font.PLAIN, 13));
        tblFacture.setBackground(Color.WHITE);

        afficherTousFactures();
    }

    private void initialiserValeurComposants() throws SQLException {
        initialiserTableauDesFacture();
        setIcon();
    }
    
    private void configurerComposantsUtilisateur(){
        switch(utilisateur.getProfil().getDesignation()){
            case "comptable": 
                panelBtnPayerFacture.setVisible(false);
                panelBtnNouvelleFacture.setVisible(false);
                break;
            case "dirigeant": 
                panelBtnPayerFacture.setVisible(false);
                panelBtnNouvelleFacture.setVisible(false);
                break;
        }           
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        panelBtnNouvelleFacture = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        panelBtnModifierFacture = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        panelBtnPayerFacture = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblTotalFacture = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        lblTotalImpayees = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        lblTotalPayees = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        lblTotalSolde = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblFacture = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        lblReductionCommerciales = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lblReductionCommerciales2 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        lblReductionCommerciales3 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        lblReductionCommerciales4 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        lblDetailsNumeroFacture = new javax.swing.JLabel();
        lblDetailsTypeClient = new javax.swing.JLabel();
        lblDetailsNomClient = new javax.swing.JLabel();
        lblDetailsPrenomClient = new javax.swing.JLabel();
        lblDetailsAdresseClient = new javax.swing.JLabel();
        lblDetailsEmailClient = new javax.swing.JLabel();
        lblDetailsTelephoneClient = new javax.swing.JLabel();
        lblDetailsReductionsCommerciales = new javax.swing.JLabel();
        lblDetailsReductionsFinancieres = new javax.swing.JLabel();
        lblDetailsTotalHT = new javax.swing.JLabel();
        lblDetailsTotalTVA = new javax.swing.JLabel();
        lblDetailsTotalTTC = new javax.swing.JLabel();
        lblDetailsEtatFacture = new javax.swing.JLabel();
        lblDetailsAcompte = new javax.swing.JLabel();
        lblDetailsSolde = new javax.swing.JLabel();
        lblDetailsTypeFactureDate = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        lblDetailsNetCommerciale = new javax.swing.JLabel();
        lblDetailsNetFinancier = new javax.swing.JLabel();
        lblDetailsRemarques = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tableau de bord - factures");
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(0, 170, 245));

        panelBtnNouvelleFacture.setBackground(new java.awt.Color(0, 170, 245));
        panelBtnNouvelleFacture.setPreferredSize(new java.awt.Dimension(130, 85));
        panelBtnNouvelleFacture.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelBtnNouvelleFactureMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelBtnNouvelleFactureMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelBtnNouvelleFactureMousePressed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Nouvelle facture");

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icons8_plus_30px.png"))); // NOI18N

        javax.swing.GroupLayout panelBtnNouvelleFactureLayout = new javax.swing.GroupLayout(panelBtnNouvelleFacture);
        panelBtnNouvelleFacture.setLayout(panelBtnNouvelleFactureLayout);
        panelBtnNouvelleFactureLayout.setHorizontalGroup(
            panelBtnNouvelleFactureLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnNouvelleFactureLayout.createSequentialGroup()
                .addGroup(panelBtnNouvelleFactureLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBtnNouvelleFactureLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel15))
                    .addGroup(panelBtnNouvelleFactureLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel14)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        panelBtnNouvelleFactureLayout.setVerticalGroup(
            panelBtnNouvelleFactureLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnNouvelleFactureLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addGap(18, 18, 18))
        );

        panelBtnModifierFacture.setBackground(new java.awt.Color(0, 170, 245));
        panelBtnModifierFacture.setPreferredSize(new java.awt.Dimension(130, 85));
        panelBtnModifierFacture.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelBtnModifierFactureMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelBtnModifierFactureMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelBtnModifierFactureMousePressed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Imprimer facture");

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icons8_print_30px_4.png"))); // NOI18N

        javax.swing.GroupLayout panelBtnModifierFactureLayout = new javax.swing.GroupLayout(panelBtnModifierFacture);
        panelBtnModifierFacture.setLayout(panelBtnModifierFactureLayout);
        panelBtnModifierFactureLayout.setHorizontalGroup(
            panelBtnModifierFactureLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnModifierFactureLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22)
                .addContainerGap(16, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBtnModifierFactureLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel23)
                .addGap(47, 47, 47))
        );
        panelBtnModifierFactureLayout.setVerticalGroup(
            panelBtnModifierFactureLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnModifierFactureLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel22)
                .addGap(18, 18, 18))
        );

        panelBtnPayerFacture.setBackground(new java.awt.Color(0, 170, 245));
        panelBtnPayerFacture.setPreferredSize(new java.awt.Dimension(130, 85));
        panelBtnPayerFacture.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelBtnPayerFactureMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelBtnPayerFactureMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelBtnPayerFactureMousePressed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Acquitter facture");

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icons8_money_bag_euro_30px.png"))); // NOI18N

        javax.swing.GroupLayout panelBtnPayerFactureLayout = new javax.swing.GroupLayout(panelBtnPayerFacture);
        panelBtnPayerFacture.setLayout(panelBtnPayerFactureLayout);
        panelBtnPayerFactureLayout.setHorizontalGroup(
            panelBtnPayerFactureLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnPayerFactureLayout.createSequentialGroup()
                .addGroup(panelBtnPayerFactureLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBtnPayerFactureLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel24))
                    .addGroup(panelBtnPayerFactureLayout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jLabel25)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        panelBtnPayerFactureLayout.setVerticalGroup(
            panelBtnPayerFactureLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnPayerFactureLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel24)
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(panelBtnNouvelleFacture, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelBtnModifierFacture, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(panelBtnPayerFacture, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBtnNouvelleFacture, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(panelBtnModifierFacture, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(panelBtnPayerFacture, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        lblTotalFacture.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 24)); // NOI18N
        lblTotalFacture.setForeground(new java.awt.Color(0, 170, 245));
        lblTotalFacture.setText("0");

        jLabel9.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jLabel9.setText("Total");

        jPanel13.setBackground(new java.awt.Color(0, 170, 245));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(222, Short.MAX_VALUE))
            .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblTotalFacture, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addContainerGap())
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(35, 35, 35)
                    .addComponent(lblTotalFacture)
                    .addContainerGap(35, Short.MAX_VALUE)))
        );

        lblTotalImpayees.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 24)); // NOI18N
        lblTotalImpayees.setForeground(new java.awt.Color(255, 51, 51));
        lblTotalImpayees.setText("0");

        jLabel11.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jLabel11.setText("Total");

        jPanel10.setBackground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(222, Short.MAX_VALUE))
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblTotalImpayees, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addContainerGap())
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(35, 35, 35)
                    .addComponent(lblTotalImpayees)
                    .addContainerGap(35, Short.MAX_VALUE)))
        );

        lblTotalPayees.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 24)); // NOI18N
        lblTotalPayees.setForeground(new java.awt.Color(0, 153, 51));
        lblTotalPayees.setText("0");

        jLabel10.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jLabel10.setText("Total");

        jPanel12.setBackground(new java.awt.Color(0, 153, 51));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(222, Short.MAX_VALUE))
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblTotalPayees, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addContainerGap())
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(35, 35, 35)
                    .addComponent(lblTotalPayees)
                    .addContainerGap(35, Short.MAX_VALUE)))
        );

        lblTotalSolde.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 24)); // NOI18N
        lblTotalSolde.setForeground(new java.awt.Color(255, 51, 51));
        lblTotalSolde.setText("0");

        jLabel12.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jLabel12.setText("Total");

        jPanel11.setBackground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(222, Short.MAX_VALUE))
            .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblTotalSolde, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addContainerGap())
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addGap(35, 35, 35)
                    .addComponent(lblTotalSolde)
                    .addContainerGap(35, Short.MAX_VALUE)))
        );

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 170, 245));
        jLabel1.setText("Nombre de factures");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 51));
        jLabel2.setText("Payées");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 51, 51));
        jLabel3.setText("Impayées");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 51, 51));
        jLabel4.setText("Soldes des impayées");

        jScrollPane1.setBackground(new java.awt.Color(247, 247, 247));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        tblFacture.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tblFacture.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Numéro", "Nom Client", "Date facture", "Delais de règlement", "Total TTC", "Net A Payer", "Etat", "Solde des impayées", "Solde des payées"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblFacture.setGridColor(new java.awt.Color(230, 230, 230));
        tblFacture.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblFacture.setRowHeight(35);
        tblFacture.setSelectionBackground(java.awt.SystemColor.controlHighlight);
        tblFacture.setShowVerticalLines(false);
        tblFacture.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblFactureMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblFacture);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 767, Short.MAX_VALUE)
        );

        lblReductionCommerciales.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        lblReductionCommerciales.setText("Détails d'une facture");

        lblReductionCommerciales2.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        lblReductionCommerciales2.setText("Totaux");

        jLabel13.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel13.setText("Type");

        jLabel16.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel16.setText("Identifiant");

        jLabel17.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel17.setText("Nom");

        jLabel18.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel18.setText("Prénom");

        jLabel19.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel19.setText("Adresse");

        jLabel26.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel26.setText("Téléphone");

        jLabel27.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel27.setText("email");

        lblReductionCommerciales3.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        lblReductionCommerciales3.setText("Définition");

        jLabel28.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel28.setText("Réd. commérciales");

        jLabel29.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel29.setText("Réd. financières");

        jLabel30.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel30.setText("Total Hors taxes");

        jLabel31.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel31.setText("Total taxes");

        jLabel32.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel32.setText("Total TTC");

        jLabel33.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel33.setText("Acompte");

        jLabel34.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel34.setText("Etat");

        lblReductionCommerciales4.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        lblReductionCommerciales4.setText("Détails règlement");

        jLabel35.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel35.setText("Remarques");

        jLabel36.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel36.setText("Reste à acquitter");

        lblDetailsNumeroFacture.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lblDetailsTypeClient.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lblDetailsNomClient.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lblDetailsPrenomClient.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lblDetailsAdresseClient.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lblDetailsEmailClient.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lblDetailsTelephoneClient.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lblDetailsReductionsCommerciales.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lblDetailsReductionsFinancieres.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lblDetailsTotalHT.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lblDetailsTotalTVA.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lblDetailsTotalTTC.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lblDetailsEtatFacture.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lblDetailsAcompte.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lblDetailsSolde.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lblDetailsTypeFactureDate.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel53.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel53.setText("Net commercial");

        jLabel54.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel54.setText("Net financier");

        lblDetailsNetCommerciale.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lblDetailsNetFinancier.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lblDetailsRemarques.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1186, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblReductionCommerciales3)
                                    .addComponent(lblReductionCommerciales2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(171, 171, 171)
                                .addComponent(lblReductionCommerciales)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jSeparator1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator2)
                            .addComponent(jSeparator4)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel30)
                                                .addComponent(jLabel31)
                                                .addComponent(jLabel32)
                                                .addComponent(jLabel33)
                                                .addComponent(jLabel34))
                                            .addGap(18, 18, 18)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(lblDetailsTotalHT, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(lblDetailsTotalTVA, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(lblDetailsTotalTTC, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(lblDetailsEtatFacture, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(lblDetailsAcompte, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel53)
                                                .addComponent(jLabel28)
                                                .addComponent(jLabel29)
                                                .addComponent(jLabel54))
                                            .addGap(18, 18, 18)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(lblDetailsReductionsCommerciales, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(lblDetailsReductionsFinancieres, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(lblDetailsNetCommerciale, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(lblDetailsNetFinancier, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblReductionCommerciales4, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(16, 16, 16)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel36)
                                            .addComponent(jLabel35))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblDetailsRemarques, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblDetailsSolde, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 15, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel26)
                                    .addComponent(jLabel27)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17)
                                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(1, 1, 1))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel13))))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblDetailsNomClient, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblDetailsPrenomClient, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblDetailsAdresseClient, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblDetailsEmailClient, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblDetailsTelephoneClient, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(lblDetailsTypeFactureDate, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(lblDetailsNumeroFacture, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblDetailsTypeClient, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 608, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblReductionCommerciales)
                        .addGap(1, 1, 1)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblReductionCommerciales3)
                                    .addComponent(lblDetailsTypeFactureDate))
                                .addGap(11, 11, 11)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel16)
                                    .addComponent(lblDetailsNumeroFacture))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13)
                                    .addComponent(lblDetailsTypeClient))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel17)
                                    .addComponent(lblDetailsNomClient))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel18)
                                    .addComponent(lblDetailsPrenomClient))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel19)
                                    .addComponent(lblDetailsAdresseClient))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel26)
                                    .addComponent(lblDetailsTelephoneClient))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel27)
                                    .addComponent(lblDetailsEmailClient))
                                .addGap(18, 18, 18)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblReductionCommerciales2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28)
                            .addComponent(lblDetailsReductionsCommerciales))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel53)
                            .addComponent(lblDetailsNetCommerciale))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel29)
                            .addComponent(lblDetailsReductionsFinancieres))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel54)
                            .addComponent(lblDetailsNetFinancier))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30)
                            .addComponent(lblDetailsTotalHT))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31)
                            .addComponent(lblDetailsTotalTVA))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel32)
                            .addComponent(lblDetailsTotalTTC))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel33)
                            .addComponent(lblDetailsAcompte))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel34)
                            .addComponent(lblDetailsEtatFacture))
                        .addGap(13, 13, 13)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblReductionCommerciales4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel36)
                            .addComponent(lblDetailsSolde))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel35)
                            .addComponent(lblDetailsRemarques)))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void colorerPanelEnSurvol(JPanel panel) {
        panel.setBackground(new java.awt.Color(0, 145, 255));
    }

    private void colorerParDefaut(JPanel panel) {
        panel.setBackground(new java.awt.Color(0, 170, 245));
    }

    private void panelBtnNouvelleFactureMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnNouvelleFactureMouseExited
        colorerParDefaut(panelBtnNouvelleFacture);
    }//GEN-LAST:event_panelBtnNouvelleFactureMouseExited

    private void panelBtnNouvelleFactureMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnNouvelleFactureMouseEntered
        colorerPanelEnSurvol(panelBtnNouvelleFacture);
    }//GEN-LAST:event_panelBtnNouvelleFactureMouseEntered

    private void panelBtnModifierFactureMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnModifierFactureMouseEntered
        colorerPanelEnSurvol(panelBtnModifierFacture);
    }//GEN-LAST:event_panelBtnModifierFactureMouseEntered

    private void panelBtnPayerFactureMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnPayerFactureMouseEntered
        colorerPanelEnSurvol(panelBtnPayerFacture);
    }//GEN-LAST:event_panelBtnPayerFactureMouseEntered

    private void panelBtnModifierFactureMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnModifierFactureMouseExited
        colorerParDefaut(panelBtnModifierFacture);
    }//GEN-LAST:event_panelBtnModifierFactureMouseExited

    private void panelBtnPayerFactureMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnPayerFactureMouseExited
        colorerParDefaut(panelBtnPayerFacture);
    }//GEN-LAST:event_panelBtnPayerFactureMouseExited

    private void panelBtnNouvelleFactureMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnNouvelleFactureMousePressed
        try {

            FactureForm factureForm = new FactureForm(this);
            factureForm.setLocationRelativeTo(null);
            factureForm.setVisible(true);

        } catch (SQLException ex) {
            Logger.getLogger(FactureMainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_panelBtnNouvelleFactureMousePressed

    private void tblFactureMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblFactureMouseClicked
        //On clique sur une ligne de la table facture
        int ligneSelectionnee = tblFacture.getSelectedRow();
        DefaultTableModel modele = (DefaultTableModel) tblFacture.getModel();
        Facture facture = (Facture) modele.getValueAt(ligneSelectionnee, 0);

        lblDetailsTypeFactureDate.setText(facture.getType().toUpperCase() + " - " + affichageDateFormat.format(Date.valueOf(facture.getDateFacture())));
        lblDetailsNumeroFacture.setText(String.valueOf(facture.getNumero()));
        lblDetailsTypeClient.setText(facture.getClient().getClientType().getDesignation_type());
        lblDetailsNomClient.setText(facture.getClient().getNom());
        lblDetailsPrenomClient.setText(facture.getClient().getPrenom());
        lblDetailsAdresseClient.setText(facture.getClient().getAdresse());
        lblDetailsTelephoneClient.setText(facture.getClient().getTelephone());
        lblDetailsEmailClient.setText(facture.getClient().getEmail());
        lblDetailsReductionsCommerciales.setText((new BigDecimal(facture.getReductionsCommerciales()).setScale(2, BigDecimal.ROUND_UP)) + " €");
        lblDetailsNetCommerciale.setText((new BigDecimal(facture.getNetCommerciale()).setScale(2, BigDecimal.ROUND_UP)) + " €");
        lblDetailsReductionsFinancieres.setText((new BigDecimal(facture.getReductionsFinancieres()).setScale(2, BigDecimal.ROUND_UP)) + " €");
        lblDetailsNetFinancier.setText((new BigDecimal(facture.getNetFinancier()).setScale(2, BigDecimal.ROUND_UP)) + " €");
        lblDetailsTotalHT.setText((new BigDecimal(facture.getTotalHT()).setScale(2, BigDecimal.ROUND_UP)) + " €");
        lblDetailsTotalTVA.setText((new BigDecimal(facture.getTotalTVA()).setScale(2, BigDecimal.ROUND_UP)) + " €");
        lblDetailsTotalTTC.setText((new BigDecimal(facture.getTotalTTC()).setScale(2, BigDecimal.ROUND_UP)) + " €");
        lblDetailsAcompte.setText((new BigDecimal(facture.getAcompte()).setScale(2, BigDecimal.ROUND_UP)) + " €");
        lblDetailsEtatFacture.setText(facture.getEtat());
        lblDetailsSolde.setText((new BigDecimal(facture.getSoldeImpayees()).setScale(2, BigDecimal.ROUND_UP)) + " €");
        lblDetailsRemarques.setText(facture.getRemarques());
    }//GEN-LAST:event_tblFactureMouseClicked

    private void panelBtnPayerFactureMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnPayerFactureMousePressed
        if (tblFacture.getSelectedRowCount() > 0) {
            DefaultTableModel tableFactureModele = (DefaultTableModel) tblFacture.getModel();
            //Récupérer la facture dans la première colonne de la ligne selectionnée
            Facture facture = (Facture) tableFactureModele.getValueAt(tblFacture.getSelectedRow(), 0);

            if (facture.getEtat().equals("impayee")) {
                PaiementForm paiementForm = new PaiementForm(this, true, facture, this);
                paiementForm.setLocationRelativeTo(this);
                paiementForm.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Cette facture est déjà réglée", "Information",
                        JOptionPane.INFORMATION_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Veuillez selectionner la facture à acquitter", "Erreur",
                    JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_panelBtnPayerFactureMousePressed

    private void panelBtnModifierFactureMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnModifierFactureMousePressed
        if (tblFacture.getSelectedRowCount() > 0) {
            DefaultTableModel tableFactureModele = (DefaultTableModel) tblFacture.getModel();
            //Récupérer la facture dans la première colonne de la ligne selectionnée
            Facture facture = (Facture) tableFactureModele.getValueAt(tblFacture.getSelectedRow(), 0);

            if (facture != null) {

                FacturePdf facturePdf = new FacturePdf();
                try {

                    facturePdf.creer(facture);

                } catch (FileNotFoundException | DocumentException ex) {
                    JOptionPane.showMessageDialog(null, "Un document de même nom est ouvert dans un autre programme: "+ex, "Erreur",
                            JOptionPane.ERROR_MESSAGE);
                }

            }

        } else {
            JOptionPane.showMessageDialog(null, "Veuillez selectionner la facture à imprimer", "Erreur",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_panelBtnModifierFactureMousePressed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new FactureMainFrame().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(FactureMainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage("icons8_bullish_filled_75px.png"));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel lblDetailsAcompte;
    private javax.swing.JLabel lblDetailsAdresseClient;
    private javax.swing.JLabel lblDetailsEmailClient;
    private javax.swing.JLabel lblDetailsEtatFacture;
    private javax.swing.JLabel lblDetailsNetCommerciale;
    private javax.swing.JLabel lblDetailsNetFinancier;
    private javax.swing.JLabel lblDetailsNomClient;
    private javax.swing.JLabel lblDetailsNumeroFacture;
    private javax.swing.JLabel lblDetailsPrenomClient;
    private javax.swing.JLabel lblDetailsReductionsCommerciales;
    private javax.swing.JLabel lblDetailsReductionsFinancieres;
    private javax.swing.JLabel lblDetailsRemarques;
    private javax.swing.JLabel lblDetailsSolde;
    private javax.swing.JLabel lblDetailsTelephoneClient;
    private javax.swing.JLabel lblDetailsTotalHT;
    private javax.swing.JLabel lblDetailsTotalTTC;
    private javax.swing.JLabel lblDetailsTotalTVA;
    private javax.swing.JLabel lblDetailsTypeClient;
    private javax.swing.JLabel lblDetailsTypeFactureDate;
    private javax.swing.JLabel lblReductionCommerciales;
    private javax.swing.JLabel lblReductionCommerciales2;
    private javax.swing.JLabel lblReductionCommerciales3;
    private javax.swing.JLabel lblReductionCommerciales4;
    private javax.swing.JLabel lblTotalFacture;
    private javax.swing.JLabel lblTotalImpayees;
    private javax.swing.JLabel lblTotalPayees;
    private javax.swing.JLabel lblTotalSolde;
    private javax.swing.JPanel panelBtnModifierFacture;
    private javax.swing.JPanel panelBtnNouvelleFacture;
    private javax.swing.JPanel panelBtnPayerFacture;
    public static javax.swing.JTable tblFacture;
    // End of variables declaration//GEN-END:variables
}
