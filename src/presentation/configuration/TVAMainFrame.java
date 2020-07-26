package presentation.configuration;

import donnees.ProduitModele;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import metier.TVA;

/**
 *
 * @author Mamy Sitraka
 */
public class TVAMainFrame extends javax.swing.JDialog {

    private ProduitModele produitModele;

    public TVAMainFrame(java.awt.Frame parent, boolean modal) throws SQLException {
        super(parent, modal);
        initComponents();
        produitModele = new ProduitModele();
        initialiserTableauTVA();
    }

    private boolean initialiserTableauTVA() throws SQLException {
        DefaultTableModel tableTVAModele = (DefaultTableModel) tblTVA.getModel(); //Récupérer le modèle de la table rabais
        if (tableTVAModele != null) {
            tableTVAModele.setRowCount(0); //Vider le modele de la table TVA
        }

        if (produitModele.getTousTVA().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Le tableau des TVA est vide !", "Information",
                    JOptionPane.INFORMATION_MESSAGE);
            return false;
        } else {
            Object row[];
            for (TVA tva : produitModele.getTousTVA()) {
                row = new Object[2];
                row[0] = tva;
                row[1] = tva.getDescription();

                tableTVAModele.addRow(row);
            }
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTVA = new javax.swing.JTable();
        btnAjouterTVA = new javax.swing.JButton();
        btnModifierTVA = new javax.swing.JButton();
        btnAnnuler = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Liste des TVA");
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        tblTVA.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        tblTVA.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null}
            },
            new String [] {
                "Taux ( en %)", "Commentaires"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTVA.setFocusable(false);
        tblTVA.setGridColor(new java.awt.Color(195, 195, 195));
        tblTVA.setRowHeight(25);
        tblTVA.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tblTVA);

        btnAjouterTVA.setBackground(new java.awt.Color(0, 170, 245));
        btnAjouterTVA.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        btnAjouterTVA.setForeground(new java.awt.Color(255, 255, 255));
        btnAjouterTVA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icons8_plus_20px_1.png"))); // NOI18N
        btnAjouterTVA.setText("Nouveau TVA");
        btnAjouterTVA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAjouterTVAActionPerformed(evt);
            }
        });

        btnModifierTVA.setBackground(new java.awt.Color(0, 170, 245));
        btnModifierTVA.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        btnModifierTVA.setForeground(new java.awt.Color(255, 255, 255));
        btnModifierTVA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icons8_delete_sign_20px.png"))); // NOI18N
        btnModifierTVA.setText("Modifier TVA");
        btnModifierTVA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModifierTVAActionPerformed(evt);
            }
        });

        btnAnnuler.setBackground(new java.awt.Color(0, 170, 245));
        btnAnnuler.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        btnAnnuler.setForeground(new java.awt.Color(255, 255, 255));
        btnAnnuler.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icons8_delete_sign_25px.png"))); // NOI18N
        btnAnnuler.setText("Fermer");
        btnAnnuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnnulerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAnnuler, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAjouterTVA, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnModifierTVA, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 796, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAjouterTVA)
                    .addComponent(btnModifierTVA))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAnnuler, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAjouterTVAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAjouterTVAActionPerformed
        TVAForm tvaForm = new TVAForm(null, true, "nouveau");
        tvaForm.setLocationRelativeTo(null);
        tvaForm.setVisible(true);
    }//GEN-LAST:event_btnAjouterTVAActionPerformed

    private void btnModifierTVAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModifierTVAActionPerformed
        int ligneSelectionnee = tblTVA.getSelectedRow();
        DefaultTableModel tableModele = (DefaultTableModel) tblTVA.getModel();

        if (ligneSelectionnee != -1) // Si une ligne est bien selectionnée
        {
            TVA tva = (TVA) tableModele.getValueAt(ligneSelectionnee, 0);
            TVAForm tvaForm = new TVAForm(null, true, tva, "modification");
            tvaForm.setLocationRelativeTo(null);
            tvaForm.setVisible(true);
        }
    }//GEN-LAST:event_btnModifierTVAActionPerformed

    private void btnAnnulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnnulerActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnAnnulerActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {

                    TVAMainFrame dialog = new TVAMainFrame(new javax.swing.JFrame(), true);
                    dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent e) {
                            System.exit(0);
                        }
                    });
                    dialog.setVisible(true);

                } catch (SQLException ex) {
                    Logger.getLogger(TVAMainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAjouterTVA;
    private javax.swing.JButton btnAnnuler;
    private javax.swing.JButton btnModifierTVA;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblTVA;
    // End of variables declaration//GEN-END:variables
}
