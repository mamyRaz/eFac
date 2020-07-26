package presentation.client;

import donnees.FactureModele;
import java.awt.Toolkit;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import metier.Client;
import metier.Facture;
import presentation.facture.PaiementForm;

/**
 *
 * @author Mamy Sitraka
 */
public class InfosClientFrame extends javax.swing.JFrame {

    private ArrayList<Facture> facturesPayees;
    private ArrayList<Facture> facturesImpayees;
    private final SimpleDateFormat affichageDateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private int totalFacture;
    private int totalPayees;
    private int totalImpayees;
    private double totalSoldePayees;
    private double totalSoldeImpayees;
    private Client client;
    private FactureModele factureModele;

    public InfosClientFrame() {
        initComponents();
        factureModele = new FactureModele();
    }

    public InfosClientFrame(Client client) throws SQLException {
        this();
        this.client = client;
        initialiserValeurComposants();
    }

    private void initialiserValeurComposants() throws SQLException {
        initialiserInfosClient();
        initialiserTableauFacturesPayees();
        initialiserTableauFacturesImpayees();
        mettreAJourStatistique(); //Affichage des statistiques
        setIcon();
    }

    private void initialiserInfosClient() {
        lblIdClient.setText(String.valueOf(client.getId()));
        lblTypeClient.setText(client.getClientType().getDesignation_type());
        lblNomClient.setText(client.getNom());
        if (client.getClientType().getDesignation_type().equals("entreprise")) {
            lblSIRET.setText(client.getSIRET());
            lblPrenomClient.setVisible(false);
            lblLibellePrenom.setVisible(false);
        } else {
            lblPrenomClient.setText(client.getPrenom());
            lblSIRET.setVisible(false);
            lblLibelleSIRET.setVisible(false);
        }
        lblAdresseClient.setText(client.getAdresse());
        lblTelephoneClient.setText(client.getTelephone());
        lblEmailClient.setText(client.getEmail());
    }

    private void initialiserTableauFacturesPayees() throws SQLException {
        DefaultTableModel tableFacturePayeesModele = (DefaultTableModel) tblFacturePayees.getModel(); //Récupérer le modèle de la table des factures
        if (tableFacturePayeesModele != null) {
            tableFacturePayeesModele.setRowCount(0); //Vider le modele de la table
        }

        factureModele = new FactureModele(); //La classe contenant les requêtes sur les factures
        facturesPayees = factureModele.getFacturePayees(client.getId());

        totalPayees = 0;
        totalSoldePayees = 0;

        if (!facturesPayees.isEmpty()) {
            Object row[];
            for (Facture facture : facturesPayees) {
                row = new Object[7];
                row[0] = facture; //Execute la méthode toString de la classe Facture qui affiche le numéro de la ligne et le type de produit (produit, service, reduction)
                row[1] = affichageDateFormat.format(Date.valueOf(facture.getDateFacture()));
                row[2] = affichageDateFormat.format(Date.valueOf(facture.getDateDelais()));
                row[3] = (new BigDecimal(facture.getTotalTTC()).setScale(2, BigDecimal.ROUND_UP)) + " €";
                row[4] = (new BigDecimal(facture.getNetAPayer()).setScale(2, BigDecimal.ROUND_UP)) + " €";
                row[5] = facture.getEtat();
                row[6] = (new BigDecimal(facture.getSoldePayees()).setScale(2, BigDecimal.ROUND_UP)) + " €";

                if (facture.getEtat().equals("payee")) {
                    totalPayees++;
                }

                totalSoldePayees += facture.getSoldePayees();

                tableFacturePayeesModele.addRow(row);
            }
        }

    }

    private void initialiserTableauFacturesImpayees() throws SQLException {
        DefaultTableModel tableFactureImpayeesModele = (DefaultTableModel) tblFactureImpayees.getModel(); //Récupérer le modèle de la table des factures
        if (tableFactureImpayeesModele != null) {
            tableFactureImpayeesModele.setRowCount(0); //Vider le modele de la table
        }

        factureModele = new FactureModele(); //La classe contenant les requêtes sur les factures
        facturesImpayees = factureModele.getFactureImpayees(client.getId());

        totalImpayees = 0;
        totalSoldeImpayees = 0;

        if (!facturesImpayees.isEmpty()) {
            Object row[];
            for (Facture facture : facturesImpayees) {
                row = new Object[7];
                row[0] = facture; //Execute la méthode toString de la classe Facture qui affiche le numéro de la ligne et le type de produit (produit, service, reduction)
                row[1] = affichageDateFormat.format(Date.valueOf(facture.getDateFacture()));
                row[2] = affichageDateFormat.format(Date.valueOf(facture.getDateDelais()));
                row[3] = (new BigDecimal(facture.getTotalTTC()).setScale(2, BigDecimal.ROUND_UP)) + " €";
                row[4] = (new BigDecimal(facture.getNetAPayer()).setScale(2, BigDecimal.ROUND_UP)) + " €";
                row[5] = facture.getEtat();
                row[6] = (new BigDecimal(facture.getSoldeImpayees()).setScale(2, BigDecimal.ROUND_UP)) + " €";

                if (facture.getEtat().equals("impayee")) {
                    totalImpayees++;
                }

                totalSoldeImpayees += facture.getSoldeImpayees();

                tableFactureImpayeesModele.addRow(row);
            }
        }
    }

    private void mettreAJourStatistique() {
        totalFacture = totalPayees + totalImpayees;
        lblTotalFacture.setText(String.valueOf(totalFacture) + " commandes");
        lblSoldePayees.setText(String.valueOf((new BigDecimal(totalSoldePayees).setScale(2, BigDecimal.ROUND_UP))) + " €");
        lblSoldeImpayees.setText(String.valueOf((new BigDecimal(totalSoldeImpayees).setScale(2, BigDecimal.ROUND_UP))) + " €");
        lblNombrePayees.setText(String.valueOf(totalPayees) + " factures");
        lblNombreImpayees.setText(String.valueOf(totalImpayees) + " factures");
    }

    public void afficherFacturesClients() throws SQLException {
        initialiserTableauFacturesPayees();
        initialiserTableauFacturesImpayees();
        mettreAJourStatistique(); //Affichage des statistiques
    }

    public int getTotalFacture() {
        return totalFacture;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lblLibellePrenom = new javax.swing.JLabel();
        lblIdClient = new javax.swing.JLabel();
        lblTypeClient = new javax.swing.JLabel();
        lblNomClient = new javax.swing.JLabel();
        lblPrenomClient = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        lblEmailClient = new javax.swing.JLabel();
        lblTelephoneClient = new javax.swing.JLabel();
        lblAdresseClient = new javax.swing.JLabel();
        lblLibelleSIRET = new javax.swing.JLabel();
        lblSIRET = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        lblNombrePayees = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        lblSoldePayees = new javax.swing.JLabel();
        btnAjouterLigneFacture1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblFacturePayees = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        btnAjouterLigneFacture = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        lblSoldeImpayees = new javax.swing.JLabel();
        lblNombreImpayees = new javax.swing.JLabel();
        btnPayerFacture = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblFactureImpayees = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        lblTotalFacture = new javax.swing.JLabel();
        btnFermer = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Informations conernant un client");
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(240, 240, 240), null));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel16.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel16.setText("Identifiant");

        jLabel13.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel13.setText("Type");

        jLabel17.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel17.setText("Nom");

        lblLibellePrenom.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblLibellePrenom.setText("Prénom");

        lblIdClient.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lblTypeClient.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lblNomClient.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lblPrenomClient.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblLibellePrenom)
                    .addComponent(jLabel16)
                    .addComponent(jLabel13)
                    .addComponent(jLabel17))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblIdClient, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTypeClient, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNomClient, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblPrenomClient, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(76, 76, 76))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(lblIdClient))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(lblTypeClient))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(lblNomClient))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLibellePrenom)
                    .addComponent(lblPrenomClient))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel19.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel19.setText("Adresse");

        jLabel26.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel26.setText("Téléphone");

        jLabel27.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel27.setText("email");

        lblEmailClient.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lblTelephoneClient.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lblAdresseClient.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lblLibelleSIRET.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblLibelleSIRET.setText("SIRET");

        lblSIRET.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTelephoneClient, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAdresseClient, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addComponent(lblLibelleSIRET)
                            .addGap(18, 18, 18)
                            .addComponent(lblSIRET, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addComponent(jLabel27)
                            .addGap(18, 18, 18)
                            .addComponent(lblEmailClient, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(69, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(lblAdresseClient))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(lblTelephoneClient))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(lblEmailClient))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLibelleSIRET)
                    .addComponent(lblSIRET))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(0, 170, 245));

        jLabel21.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Informations du client");

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

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jLabel20.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel20.setText("Liste des factures payées :");

        lblNombrePayees.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblNombrePayees.setText("0 facture");

        jLabel25.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel25.setText("Solde des factures payées :");

        lblSoldePayees.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblSoldePayees.setText("0 €");

        btnAjouterLigneFacture1.setBackground(new java.awt.Color(0, 170, 245));
        btnAjouterLigneFacture1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        btnAjouterLigneFacture1.setForeground(new java.awt.Color(255, 255, 255));
        btnAjouterLigneFacture1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icons8_plus_20px_1.png"))); // NOI18N
        btnAjouterLigneFacture1.setText("Voir détails");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblNombrePayees, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblSoldePayees, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(btnAjouterLigneFacture1)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel25)
                        .addComponent(lblSoldePayees)
                        .addComponent(btnAjouterLigneFacture1))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel20)
                        .addComponent(lblNombrePayees)))
                .addContainerGap())
        );

        jScrollPane1.setBackground(new java.awt.Color(247, 247, 247));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        tblFacturePayees.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tblFacturePayees.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Numéro", "Date facture", "Delais de règlement", "Total TTC", "Net A Payer", "Etat", "Solde des paiements"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblFacturePayees.setGridColor(new java.awt.Color(230, 230, 230));
        tblFacturePayees.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblFacturePayees.setRowHeight(35);
        tblFacturePayees.setSelectionBackground(java.awt.SystemColor.controlHighlight);
        tblFacturePayees.setShowVerticalLines(false);
        tblFacturePayees.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblFacturePayeesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblFacturePayees);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jLabel22.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel22.setText("Liste des factures impayées :");

        btnAjouterLigneFacture.setBackground(new java.awt.Color(0, 170, 245));
        btnAjouterLigneFacture.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        btnAjouterLigneFacture.setForeground(new java.awt.Color(255, 255, 255));
        btnAjouterLigneFacture.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icons8_plus_20px_1.png"))); // NOI18N
        btnAjouterLigneFacture.setText("Voir détails");

        jLabel30.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel30.setText("Solde des factures impayées :");

        lblSoldeImpayees.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblSoldeImpayees.setText("0 €");

        lblNombreImpayees.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblNombreImpayees.setText("0 facture");

        btnPayerFacture.setBackground(new java.awt.Color(0, 170, 245));
        btnPayerFacture.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        btnPayerFacture.setForeground(new java.awt.Color(255, 255, 255));
        btnPayerFacture.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icons8_plus_20px_1.png"))); // NOI18N
        btnPayerFacture.setText("Acquitter");
        btnPayerFacture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPayerFactureActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22)
                .addGap(18, 18, 18)
                .addComponent(lblNombreImpayees, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblSoldeImpayees, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(btnPayerFacture)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAjouterLigneFacture)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblNombreImpayees)
                        .addComponent(jLabel30)
                        .addComponent(lblSoldeImpayees)
                        .addComponent(jLabel22))
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAjouterLigneFacture)
                        .addComponent(btnPayerFacture)))
                .addContainerGap())
        );

        jScrollPane2.setBackground(new java.awt.Color(247, 247, 247));
        jScrollPane2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        tblFactureImpayees.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tblFactureImpayees.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Numéro", "Date facture", "Delais de règlement", "Total TTC", "Net A Payer", "", "Solde des impayées"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblFactureImpayees.setGridColor(new java.awt.Color(230, 230, 230));
        tblFactureImpayees.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblFactureImpayees.setRowHeight(35);
        tblFactureImpayees.setSelectionBackground(java.awt.SystemColor.controlHighlight);
        tblFactureImpayees.setShowVerticalLines(false);
        tblFactureImpayees.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblFactureImpayeesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblFactureImpayees);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        jLabel23.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel23.setText("Nombre de commandes :");

        lblTotalFacture.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblTotalFacture.setText("0");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTotalFacture, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(lblTotalFacture))
                .addContainerGap())
        );

        btnFermer.setBackground(new java.awt.Color(0, 170, 245));
        btnFermer.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        btnFermer.setForeground(new java.awt.Color(255, 255, 255));
        btnFermer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icons8_delete_sign_25px.png"))); // NOI18N
        btnFermer.setText("Fermer");
        btnFermer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFermerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
            .addComponent(jScrollPane2)
            .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnFermer, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 25, Short.MAX_VALUE)
                .addComponent(btnFermer, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1231, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblFacturePayeesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblFacturePayeesMouseClicked

    }//GEN-LAST:event_tblFacturePayeesMouseClicked

    private void tblFactureImpayeesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblFactureImpayeesMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblFactureImpayeesMouseClicked

    private void btnFermerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFermerActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnFermerActionPerformed

    private void btnPayerFactureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPayerFactureActionPerformed
        if (tblFactureImpayees.getSelectedRowCount() > 0) {
            DefaultTableModel tableFactureModele = (DefaultTableModel) tblFactureImpayees.getModel();
            //Récupérer la facture dans la première colonne de la ligne selectionnée
            Facture facture = (Facture) tableFactureModele.getValueAt(tblFactureImpayees.getSelectedRow(), 0);

            if (facture.getEtat().equals("impayee")) {
                PaiementForm paiementForm = new PaiementForm(this, true, facture);
                paiementForm.setInfosClientFrame(this);
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
    }//GEN-LAST:event_btnPayerFactureActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InfosClientFrame().setVisible(true);
            }
        });
    }

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage("icons8_bullish_filled_75px.png"));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAjouterLigneFacture;
    private javax.swing.JButton btnAjouterLigneFacture1;
    private javax.swing.JButton btnFermer;
    private javax.swing.JButton btnPayerFacture;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAdresseClient;
    private javax.swing.JLabel lblEmailClient;
    private javax.swing.JLabel lblIdClient;
    private javax.swing.JLabel lblLibellePrenom;
    private javax.swing.JLabel lblLibelleSIRET;
    private javax.swing.JLabel lblNomClient;
    private javax.swing.JLabel lblNombreImpayees;
    private javax.swing.JLabel lblNombrePayees;
    private javax.swing.JLabel lblPrenomClient;
    private javax.swing.JLabel lblSIRET;
    private javax.swing.JLabel lblSoldeImpayees;
    private javax.swing.JLabel lblSoldePayees;
    private javax.swing.JLabel lblTelephoneClient;
    private javax.swing.JLabel lblTotalFacture;
    private javax.swing.JLabel lblTypeClient;
    public static javax.swing.JTable tblFactureImpayees;
    public static javax.swing.JTable tblFacturePayees;
    // End of variables declaration//GEN-END:variables
}
