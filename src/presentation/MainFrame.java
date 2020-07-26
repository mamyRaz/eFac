
package presentation;

import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import metier.Utilisateur;
import presentation.facture.FactureMainFrame;
import presentation.client.ClientMainFrame;
import presentation.configuration.ConfigurationMainFrame;
import presentation.produit.ProduitMainFrame;
import presentation.utilisateur.UtilisateurMainFrame;

/**
 *
 * @author Mamy Sitraka
 */
public class MainFrame extends javax.swing.JFrame {

    private Utilisateur utilisateur;
    
    public MainFrame() {
        initComponents();
    }
    
    public MainFrame(Utilisateur utilisateur) {
        //Appelle le constructeur sans parametre
        this();
        this.utilisateur = utilisateur;
        initialiserMenu(utilisateur);
        setIcon();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        panelBtnQuitter = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        panelBtnMenuProduit = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        panelBtnMenuFacture = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        panelBtnMenuConfiguration = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        panelBtnMenuJournal = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        panelBtnMenuClient = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        panelBtnMenuUtilisateur = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 170, 245));

        jLabel13.setFont(new java.awt.Font("Calibri", 3, 48)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("e-fac");
        jLabel13.setToolTipText("");

        jLabel14.setFont(new java.awt.Font("Calibri", 2, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Bienvenue");
        jLabel14.setToolTipText("");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(166, 166, 166)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addContainerGap(855, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(47, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addGap(46, 46, 46))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1120, 190));

        panelBtnQuitter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelBtnQuitterMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelBtnQuitterMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelBtnQuitterMousePressed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icons8_shutdown_50px_1.png"))); // NOI18N

        javax.swing.GroupLayout panelBtnQuitterLayout = new javax.swing.GroupLayout(panelBtnQuitter);
        panelBtnQuitter.setLayout(panelBtnQuitterLayout);
        panelBtnQuitterLayout.setHorizontalGroup(
            panelBtnQuitterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnQuitterLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        panelBtnQuitterLayout.setVerticalGroup(
            panelBtnQuitterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBtnQuitterLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(38, 38, 38))
        );

        jPanel1.add(panelBtnQuitter, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 530, 100, 80));

        panelBtnMenuProduit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelBtnMenuProduitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelBtnMenuProduitMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelBtnMenuProduitMousePressed(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icons8_shopping_cart_loaded_50px.png"))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 170, 245));
        jLabel4.setText("Produit");
        jLabel4.setToolTipText("");

        javax.swing.GroupLayout panelBtnMenuProduitLayout = new javax.swing.GroupLayout(panelBtnMenuProduit);
        panelBtnMenuProduit.setLayout(panelBtnMenuProduitLayout);
        panelBtnMenuProduitLayout.setHorizontalGroup(
            panelBtnMenuProduitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnMenuProduitLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(panelBtnMenuProduitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        panelBtnMenuProduitLayout.setVerticalGroup(
            panelBtnMenuProduitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnMenuProduitLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel1.add(panelBtnMenuProduit, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 240, -1, -1));

        panelBtnMenuFacture.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelBtnMenuFactureMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelBtnMenuFactureMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelBtnMenuFactureMousePressed(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icons8_brief_50px.png"))); // NOI18N

        jLabel6.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 170, 245));
        jLabel6.setText("Facture");
        jLabel6.setToolTipText("");

        javax.swing.GroupLayout panelBtnMenuFactureLayout = new javax.swing.GroupLayout(panelBtnMenuFacture);
        panelBtnMenuFacture.setLayout(panelBtnMenuFactureLayout);
        panelBtnMenuFactureLayout.setHorizontalGroup(
            panelBtnMenuFactureLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnMenuFactureLayout.createSequentialGroup()
                .addGroup(panelBtnMenuFactureLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBtnMenuFactureLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel6))
                    .addGroup(panelBtnMenuFactureLayout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        panelBtnMenuFactureLayout.setVerticalGroup(
            panelBtnMenuFactureLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnMenuFactureLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel1.add(panelBtnMenuFacture, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 240, 140, 130));

        panelBtnMenuConfiguration.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelBtnMenuConfigurationMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelBtnMenuConfigurationMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelBtnMenuConfigurationMousePressed(evt);
            }
        });

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icons8_settings_50px.png"))); // NOI18N

        jLabel8.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 170, 245));
        jLabel8.setText("Configuration");
        jLabel8.setToolTipText("");

        javax.swing.GroupLayout panelBtnMenuConfigurationLayout = new javax.swing.GroupLayout(panelBtnMenuConfiguration);
        panelBtnMenuConfiguration.setLayout(panelBtnMenuConfigurationLayout);
        panelBtnMenuConfigurationLayout.setHorizontalGroup(
            panelBtnMenuConfigurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnMenuConfigurationLayout.createSequentialGroup()
                .addGroup(panelBtnMenuConfigurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBtnMenuConfigurationLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel8))
                    .addGroup(panelBtnMenuConfigurationLayout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel7)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBtnMenuConfigurationLayout.setVerticalGroup(
            panelBtnMenuConfigurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnMenuConfigurationLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel1.add(panelBtnMenuConfiguration, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 410, 140, 130));

        panelBtnMenuJournal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelBtnMenuJournalMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelBtnMenuJournalMouseExited(evt);
            }
        });

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icons8_us_news_50px.png"))); // NOI18N

        jLabel10.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 170, 245));
        jLabel10.setText("Journal");
        jLabel10.setToolTipText("");

        javax.swing.GroupLayout panelBtnMenuJournalLayout = new javax.swing.GroupLayout(panelBtnMenuJournal);
        panelBtnMenuJournal.setLayout(panelBtnMenuJournalLayout);
        panelBtnMenuJournalLayout.setHorizontalGroup(
            panelBtnMenuJournalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnMenuJournalLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBtnMenuJournalLayout.createSequentialGroup()
                .addContainerGap(39, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(34, 34, 34))
        );
        panelBtnMenuJournalLayout.setVerticalGroup(
            panelBtnMenuJournalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnMenuJournalLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel1.add(panelBtnMenuJournal, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 410, 140, 130));

        panelBtnMenuClient.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelBtnMenuClientMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelBtnMenuClientMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelBtnMenuClientMousePressed(evt);
            }
        });

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icons8_customer_insight_50px.png"))); // NOI18N

        jLabel12.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 170, 245));
        jLabel12.setText("Client");
        jLabel12.setToolTipText("");

        javax.swing.GroupLayout panelBtnMenuClientLayout = new javax.swing.GroupLayout(panelBtnMenuClient);
        panelBtnMenuClient.setLayout(panelBtnMenuClientLayout);
        panelBtnMenuClientLayout.setHorizontalGroup(
            panelBtnMenuClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnMenuClientLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(panelBtnMenuClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel11))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        panelBtnMenuClientLayout.setVerticalGroup(
            panelBtnMenuClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnMenuClientLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel1.add(panelBtnMenuClient, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 240, 140, 130));

        panelBtnMenuUtilisateur.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelBtnMenuUtilisateurMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelBtnMenuUtilisateurMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelBtnMenuUtilisateurMousePressed(evt);
            }
        });

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icons8_crowd_50px_1.png"))); // NOI18N

        jLabel16.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 170, 245));
        jLabel16.setText("Utilisateur");
        jLabel16.setToolTipText("");

        javax.swing.GroupLayout panelBtnMenuUtilisateurLayout = new javax.swing.GroupLayout(panelBtnMenuUtilisateur);
        panelBtnMenuUtilisateur.setLayout(panelBtnMenuUtilisateurLayout);
        panelBtnMenuUtilisateurLayout.setHorizontalGroup(
            panelBtnMenuUtilisateurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnMenuUtilisateurLayout.createSequentialGroup()
                .addGroup(panelBtnMenuUtilisateurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBtnMenuUtilisateurLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel16))
                    .addGroup(panelBtnMenuUtilisateurLayout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel15)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        panelBtnMenuUtilisateurLayout.setVerticalGroup(
            panelBtnMenuUtilisateurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnMenuUtilisateurLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel15)
                .addGap(18, 18, 18)
                .addComponent(jLabel16)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel1.add(panelBtnMenuUtilisateur, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 410, 140, 130));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 631, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void colorerPanelEnSurvol(JPanel panel){
        panel.setBackground(new java.awt.Color(220,220,220));
    }
    
    private void colorerParDefaut(JPanel panel){
        panel.setBackground(new java.awt.Color(240,240,240));
    }
    
    private void panelBtnMenuFactureMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnMenuFactureMouseEntered
        colorerPanelEnSurvol(panelBtnMenuFacture);
    }//GEN-LAST:event_panelBtnMenuFactureMouseEntered

    private void panelBtnMenuProduitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnMenuProduitMouseEntered
        colorerPanelEnSurvol(panelBtnMenuProduit);
    }//GEN-LAST:event_panelBtnMenuProduitMouseEntered

    private void panelBtnMenuConfigurationMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnMenuConfigurationMouseEntered
        colorerPanelEnSurvol(panelBtnMenuConfiguration);
    }//GEN-LAST:event_panelBtnMenuConfigurationMouseEntered

    private void panelBtnMenuJournalMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnMenuJournalMouseEntered
        colorerPanelEnSurvol(panelBtnMenuJournal);
    }//GEN-LAST:event_panelBtnMenuJournalMouseEntered

    private void panelBtnMenuClientMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnMenuClientMouseEntered
        colorerPanelEnSurvol(panelBtnMenuClient);
    }//GEN-LAST:event_panelBtnMenuClientMouseEntered

    private void panelBtnQuitterMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnQuitterMouseEntered
        colorerPanelEnSurvol(panelBtnQuitter);
    }//GEN-LAST:event_panelBtnQuitterMouseEntered

    private void panelBtnMenuFactureMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnMenuFactureMouseExited
        colorerParDefaut(panelBtnMenuFacture);
    }//GEN-LAST:event_panelBtnMenuFactureMouseExited

    private void panelBtnMenuProduitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnMenuProduitMouseExited
        colorerParDefaut(panelBtnMenuProduit);
    }//GEN-LAST:event_panelBtnMenuProduitMouseExited

    private void panelBtnMenuConfigurationMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnMenuConfigurationMouseExited
        colorerParDefaut(panelBtnMenuConfiguration);
    }//GEN-LAST:event_panelBtnMenuConfigurationMouseExited

    private void panelBtnMenuJournalMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnMenuJournalMouseExited
        colorerParDefaut(panelBtnMenuJournal);
    }//GEN-LAST:event_panelBtnMenuJournalMouseExited

    private void panelBtnMenuClientMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnMenuClientMouseExited
        colorerParDefaut(panelBtnMenuClient);
    }//GEN-LAST:event_panelBtnMenuClientMouseExited

    private void panelBtnQuitterMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnQuitterMouseExited
        colorerParDefaut(panelBtnQuitter);
    }//GEN-LAST:event_panelBtnQuitterMouseExited

    private void panelBtnMenuFactureMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnMenuFactureMousePressed
        try {
            
            FactureMainFrame factureMainFrame = new FactureMainFrame(utilisateur);
            factureMainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            factureMainFrame.setLocationRelativeTo(null);
            factureMainFrame.setVisible(true);
            
        } catch (SQLException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_panelBtnMenuFactureMousePressed

    private void panelBtnMenuUtilisateurMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnMenuUtilisateurMouseEntered
        colorerPanelEnSurvol(panelBtnMenuUtilisateur);
    }//GEN-LAST:event_panelBtnMenuUtilisateurMouseEntered

    private void panelBtnMenuUtilisateurMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnMenuUtilisateurMouseExited
        colorerParDefaut(panelBtnMenuUtilisateur);
    }//GEN-LAST:event_panelBtnMenuUtilisateurMouseExited

    private void panelBtnQuitterMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnQuitterMousePressed
        this.dispose();
        LoginForm loginForm = new LoginForm();
        loginForm.setVisible(true);
        loginForm.setLocationRelativeTo(null);//centrer le Frame
        loginForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//on ferme l'application en fermant cette fenetre
    }//GEN-LAST:event_panelBtnQuitterMousePressed

    private void panelBtnMenuClientMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnMenuClientMousePressed
        ClientMainFrame clientMainFrame = new ClientMainFrame();
        clientMainFrame.setLocationRelativeTo(null);
        clientMainFrame.setVisible(true);
    }//GEN-LAST:event_panelBtnMenuClientMousePressed

    private void panelBtnMenuUtilisateurMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnMenuUtilisateurMousePressed
        UtilisateurMainFrame utilisateurMainFrame = new UtilisateurMainFrame();
        utilisateurMainFrame.setLocationRelativeTo(null);
        utilisateurMainFrame.setVisible(true);
    }//GEN-LAST:event_panelBtnMenuUtilisateurMousePressed

    private void panelBtnMenuProduitMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnMenuProduitMousePressed
        try {
            
            ProduitMainFrame produitMainFrame = new ProduitMainFrame();
            produitMainFrame.setLocationRelativeTo(null);
            produitMainFrame.setVisible(true);
            
        } catch (SQLException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_panelBtnMenuProduitMousePressed

    private void panelBtnMenuConfigurationMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnMenuConfigurationMousePressed
        ConfigurationMainFrame configurationMainFrame = new ConfigurationMainFrame();
        configurationMainFrame.setLocationRelativeTo(null);
        configurationMainFrame.setVisible(true);
    }//GEN-LAST:event_panelBtnMenuConfigurationMousePressed

    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
    
    //Un utilisateur non administrateur ne peut pas acceder au module utilisateur
    private void initialiserMenu(Utilisateur u){
        switch(u.getProfil().getDesignation()){
            case "comptable": 
                panelBtnMenuUtilisateur.setVisible(false);
                panelBtnMenuConfiguration.setVisible(false);
                break;
            case "commerciale": 
                panelBtnMenuUtilisateur.setVisible(false);
                panelBtnMenuConfiguration.setVisible(false);
                panelBtnMenuProduit.setVisible(false);
                break;
            case "dirigeant": 
                panelBtnMenuUtilisateur.setVisible(false);
                panelBtnMenuFacture.setVisible(false);
                panelBtnMenuClient.setVisible(false);
                break;
        }           
    }

    private void setIcon(){
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel panelBtnMenuClient;
    private javax.swing.JPanel panelBtnMenuConfiguration;
    private javax.swing.JPanel panelBtnMenuFacture;
    private javax.swing.JPanel panelBtnMenuJournal;
    private javax.swing.JPanel panelBtnMenuProduit;
    private javax.swing.JPanel panelBtnMenuUtilisateur;
    private javax.swing.JPanel panelBtnQuitter;
    // End of variables declaration//GEN-END:variables
}
