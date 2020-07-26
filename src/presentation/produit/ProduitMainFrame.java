package presentation.produit;

import donnees.ProduitModele;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import metier.Produit;
import metier.ProduitAbstrait;

/**
 *
 * @author Mamy Sitraka
 */
public class ProduitMainFrame extends javax.swing.JFrame {

    private ProduitModele produitModele;
    private ArrayList<ProduitAbstrait> produits;

    public ProduitMainFrame() throws SQLException {
        initComponents();
        produitModele = new ProduitModele();
        initialiserValeurComposants();
    }
    
    private void initialiserValeurComposants() throws SQLException {
        afficherTousProduits();
    }

    public void afficherTousProduits() throws SQLException {
        DefaultTableModel tableProduitModele = (DefaultTableModel) tblProduit.getModel(); //Récupérer le modèle de la table des factures
        if (tableProduitModele != null) {
            tableProduitModele.setRowCount(0); //Vider le modele de la table client
        }

        produits = produitModele.getTousLesProduits();

        if (produits.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Le liste des produits est vide !", "Information",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            Object row[];
            for (ProduitAbstrait produit : produits) {
                row = new Object[11];
                
                produit.setToStringType("tableau"); //Mode d'affichage en tableau c'est juste l'identifiant
                
                row[0] = produit; //Execute la méthode toString() de la classe Utilisateur
                row[1] = produit.getProduit_type();
                row[2] = produit.getFamille();
                row[3] = produit.getReference();
                row[4] = produit.getDesignation();
                row[5] = produit.getMontant_unitaire();
                row[6] = produit.getDevise();
                if (produit instanceof Produit) {
                    Produit article = (Produit) produit;
                    row[7] = article.getQuantite_stock();
                } else {
                    row[7] = "pas de stock";
                }

                row[8] = produit.getUnite();
                row[9] = produit.getTva();
                row[10] = produit.getRemise();

                tableProduitModele.addRow(row);
            }
        }
    }

    private void colorerPanelEnSurvol(JPanel panel) {
        panel.setBackground(new java.awt.Color(0, 145, 255));
    }

    private void colorerParDefaut(JPanel panel) {
        panel.setBackground(new java.awt.Color(0, 170, 245));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblProduit = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        panelBtnNouveauProduit = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        panelBtnModifierProduit = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        panelBtnSupprimerProduit = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Liste des produits");
        setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setBackground(new java.awt.Color(247, 247, 247));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        tblProduit.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tblProduit.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Numéro", "Type", "Famille", "Référence", "Designation", "Prix unitaire HT", "Devise", "Quantité en stock", "Unité", "TVA (%)", "remise (%)"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProduit.setGridColor(new java.awt.Color(230, 230, 230));
        tblProduit.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblProduit.setRowHeight(35);
        tblProduit.setSelectionBackground(java.awt.SystemColor.controlHighlight);
        tblProduit.setShowVerticalLines(false);
        tblProduit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProduitMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblProduit);

        jPanel1.setBackground(new java.awt.Color(0, 170, 245));

        panelBtnNouveauProduit.setBackground(new java.awt.Color(0, 170, 245));
        panelBtnNouveauProduit.setPreferredSize(new java.awt.Dimension(130, 85));
        panelBtnNouveauProduit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelBtnNouveauProduitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelBtnNouveauProduitMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelBtnNouveauProduitMousePressed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Nouveau produit");

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icons8_plus_30px.png"))); // NOI18N

        javax.swing.GroupLayout panelBtnNouveauProduitLayout = new javax.swing.GroupLayout(panelBtnNouveauProduit);
        panelBtnNouveauProduit.setLayout(panelBtnNouveauProduitLayout);
        panelBtnNouveauProduitLayout.setHorizontalGroup(
            panelBtnNouveauProduitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnNouveauProduitLayout.createSequentialGroup()
                .addGroup(panelBtnNouveauProduitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBtnNouveauProduitLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel15))
                    .addGroup(panelBtnNouveauProduitLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel14)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        panelBtnNouveauProduitLayout.setVerticalGroup(
            panelBtnNouveauProduitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnNouveauProduitLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addGap(18, 18, 18))
        );

        panelBtnModifierProduit.setBackground(new java.awt.Color(0, 170, 245));
        panelBtnModifierProduit.setPreferredSize(new java.awt.Dimension(130, 85));
        panelBtnModifierProduit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelBtnModifierProduitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelBtnModifierProduitMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelBtnModifierProduitMousePressed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Modifier produit");

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icons8_edit_30px.png"))); // NOI18N

        javax.swing.GroupLayout panelBtnModifierProduitLayout = new javax.swing.GroupLayout(panelBtnModifierProduit);
        panelBtnModifierProduit.setLayout(panelBtnModifierProduitLayout);
        panelBtnModifierProduitLayout.setHorizontalGroup(
            panelBtnModifierProduitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnModifierProduitLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(panelBtnModifierProduitLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel23)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBtnModifierProduitLayout.setVerticalGroup(
            panelBtnModifierProduitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnModifierProduitLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel22)
                .addGap(18, 18, 18))
        );

        panelBtnSupprimerProduit.setBackground(new java.awt.Color(0, 170, 245));
        panelBtnSupprimerProduit.setPreferredSize(new java.awt.Dimension(130, 85));
        panelBtnSupprimerProduit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelBtnSupprimerProduitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelBtnSupprimerProduitMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelBtnSupprimerProduitMousePressed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Supprimer produit");

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icons8_delete_sign_30px_1.png"))); // NOI18N

        javax.swing.GroupLayout panelBtnSupprimerProduitLayout = new javax.swing.GroupLayout(panelBtnSupprimerProduit);
        panelBtnSupprimerProduit.setLayout(panelBtnSupprimerProduitLayout);
        panelBtnSupprimerProduitLayout.setHorizontalGroup(
            panelBtnSupprimerProduitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnSupprimerProduitLayout.createSequentialGroup()
                .addGroup(panelBtnSupprimerProduitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBtnSupprimerProduitLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel24))
                    .addGroup(panelBtnSupprimerProduitLayout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jLabel25)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBtnSupprimerProduitLayout.setVerticalGroup(
            panelBtnSupprimerProduitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnSupprimerProduitLayout.createSequentialGroup()
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
                .addComponent(panelBtnNouveauProduit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(panelBtnModifierProduit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(panelBtnSupprimerProduit, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1022, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBtnNouveauProduit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(panelBtnModifierProduit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(panelBtnSupprimerProduit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 632, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblProduitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProduitMouseClicked

    }//GEN-LAST:event_tblProduitMouseClicked

    private void panelBtnNouveauProduitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnNouveauProduitMouseEntered
        colorerPanelEnSurvol(panelBtnNouveauProduit);
    }//GEN-LAST:event_panelBtnNouveauProduitMouseEntered

    private void panelBtnNouveauProduitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnNouveauProduitMouseExited
        colorerParDefaut(panelBtnNouveauProduit);
    }//GEN-LAST:event_panelBtnNouveauProduitMouseExited

    private void panelBtnNouveauProduitMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnNouveauProduitMousePressed
        try {
            
            ProduitForm produitForm = new ProduitForm(this, true, this, "nouveau");
            produitForm.setLocationRelativeTo(null);
            produitForm.setVisible(true);
             
        } catch (SQLException ex) {
            Logger.getLogger(ProduitMainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_panelBtnNouveauProduitMousePressed

    private void panelBtnModifierProduitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnModifierProduitMouseEntered
        colorerPanelEnSurvol(panelBtnModifierProduit);
    }//GEN-LAST:event_panelBtnModifierProduitMouseEntered

    private void panelBtnModifierProduitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnModifierProduitMouseExited
        colorerParDefaut(panelBtnModifierProduit);
    }//GEN-LAST:event_panelBtnModifierProduitMouseExited

    private void panelBtnModifierProduitMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnModifierProduitMousePressed
        if (tblProduit.getSelectedRowCount() > 0) {
            try {
                
                DefaultTableModel tableProduitModele = (DefaultTableModel) tblProduit.getModel();
                //Récupérer le produit dans la première colonne de la ligne selectionnée
                ProduitAbstrait produit = (ProduitAbstrait) tableProduitModele.getValueAt(tblProduit.getSelectedRow(), 0);
                
                ProduitForm produitForm = new ProduitForm(this, true, this, "modification", produit);
                produitForm.setLocationRelativeTo(null);
                produitForm.setVisible(true);
                
            } catch (SQLException ex) {
                Logger.getLogger(ProduitMainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Veuillez selectionner le produit à modifier", "Erreur",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_panelBtnModifierProduitMousePressed

    private void panelBtnSupprimerProduitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnSupprimerProduitMouseEntered
        colorerPanelEnSurvol(panelBtnSupprimerProduit);
    }//GEN-LAST:event_panelBtnSupprimerProduitMouseEntered

    private void panelBtnSupprimerProduitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnSupprimerProduitMouseExited
        colorerParDefaut(panelBtnSupprimerProduit);
    }//GEN-LAST:event_panelBtnSupprimerProduitMouseExited

    private void panelBtnSupprimerProduitMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnSupprimerProduitMousePressed
        if (tblProduit.getSelectedRowCount() > 0) {
            DefaultTableModel tableProduitModele = (DefaultTableModel) tblProduit.getModel();
            //Récupérer le produit dans la première colonne de la ligne selectionnée
            ProduitAbstrait produit = (ProduitAbstrait) tableProduitModele.getValueAt(tblProduit.getSelectedRow(), 0);

            int reponse = JOptionPane.showConfirmDialog(this, "Voulez-vous vraiment supprimer ce produit ?", "Demande de confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (reponse == JOptionPane.YES_OPTION) {
                if (produitModele.supprimerProduit(produit.getId())) {
                    try {
                        
                        JOptionPane.showMessageDialog(null, "Produit bien supprimé", "Information",
                                JOptionPane.INFORMATION_MESSAGE);
                        afficherTousProduits();
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(ProduitMainFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Veuillez selectionner le produit à supprimer", "Erreur",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_panelBtnSupprimerProduitMousePressed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    
                    new ProduitMainFrame().setVisible(true);
                    
                } catch (SQLException ex) {
                    Logger.getLogger(ProduitMainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelBtnModifierProduit;
    private javax.swing.JPanel panelBtnNouveauProduit;
    private javax.swing.JPanel panelBtnSupprimerProduit;
    public static javax.swing.JTable tblProduit;
    // End of variables declaration//GEN-END:variables
}
