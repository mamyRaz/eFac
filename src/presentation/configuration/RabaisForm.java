package presentation.configuration;

import donnees.FactureModele;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import metier.Rabais;

/**
 *
 * @author Mamy Sitraka
 */
public class RabaisForm extends javax.swing.JDialog {

    private FactureModele factureModele;
    private ArrayList<Rabais> rabaisList;

    public RabaisForm(java.awt.Frame parent, boolean modal) throws SQLException {
        super(parent, modal);
        initComponents();
        factureModele = new FactureModele();
        initialiserTableauRabais();
    }

    private boolean initialiserTableauRabais() throws SQLException {
        DefaultTableModel tableRabaisModele = (DefaultTableModel) tblRabais.getModel(); //Récupérer le modèle de la table rabais
        if (tableRabaisModele != null) {
            tableRabaisModele.setRowCount(0); //Vider le modele de la table rabais
        }

        if (factureModele.getTousRabais().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Le tableau des rabais est vide !", "Information",
                    JOptionPane.INFORMATION_MESSAGE);
            return false;
        } else {
            Object row[];
            for (Rabais rabais : factureModele.getTousRabais()) {
                row = new Object[2];
                row[0] = rabais.getTaux();
                row[1] = rabais.getCommentaires();

                tableRabaisModele.addRow(row);
            }
        }
        return true;
    }

    private boolean miseAJourRabais() {
        DefaultTableModel tableRabaisModele = (DefaultTableModel) tblRabais.getModel();
        int nbrLignes = tableRabaisModele.getRowCount();
        rabaisList = new ArrayList<>();

        for (int i = 0; i < nbrLignes; i++) {
            // Si l'une des colonnes dans une ligne est vide
            if (((tableRabaisModele.getValueAt(i, 0) == null) && (tableRabaisModele.getValueAt(i, 1) != null)) || ((tableRabaisModele.getValueAt(i, 0) != null) && (tableRabaisModele.getValueAt(i, 1) == null))) {
                JOptionPane.showMessageDialog(null, "Veuillez bien remplir le tableau !", "Erreur",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            } else {
                if ((tableRabaisModele.getValueAt(i, 0) != null) && (tableRabaisModele.getValueAt(i, 1) != null)) {
                    double taux = (double) tableRabaisModele.getValueAt(i, 0);
                    String commentaires = (String) tableRabaisModele.getValueAt(i, 1);

                    Rabais rabais = new Rabais(taux, commentaires);

                    rabaisList.add(rabais);
                }
            }
        }
        return factureModele.miseAJourRabais(rabaisList);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblRabais = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btnAjouterLigneRabais = new javax.swing.JButton();
        btnSupprimerLigneRabais = new javax.swing.JButton();
        btnAnnuler = new javax.swing.JButton();
        btnEnregistrer = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Tableau des rabais");
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        tblRabais.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        tblRabais.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null}
            },
            new String [] {
                "Taux ( en %)", "Commentaires"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Double.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblRabais.setFocusable(false);
        tblRabais.setGridColor(new java.awt.Color(195, 195, 195));
        tblRabais.setRowHeight(25);
        tblRabais.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tblRabais);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        btnAjouterLigneRabais.setBackground(new java.awt.Color(0, 170, 245));
        btnAjouterLigneRabais.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        btnAjouterLigneRabais.setForeground(new java.awt.Color(255, 255, 255));
        btnAjouterLigneRabais.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icons8_plus_20px_1.png"))); // NOI18N
        btnAjouterLigneRabais.setText("Nouvelle ligne");
        btnAjouterLigneRabais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAjouterLigneRabaisActionPerformed(evt);
            }
        });

        btnSupprimerLigneRabais.setBackground(new java.awt.Color(0, 170, 245));
        btnSupprimerLigneRabais.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        btnSupprimerLigneRabais.setForeground(new java.awt.Color(255, 255, 255));
        btnSupprimerLigneRabais.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icons8_delete_sign_20px.png"))); // NOI18N
        btnSupprimerLigneRabais.setText("Supprimer ligne");
        btnSupprimerLigneRabais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupprimerLigneRabaisActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAjouterLigneRabais, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSupprimerLigneRabais, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAjouterLigneRabais)
                    .addComponent(btnSupprimerLigneRabais))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnAnnuler.setBackground(new java.awt.Color(0, 170, 245));
        btnAnnuler.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        btnAnnuler.setForeground(new java.awt.Color(255, 255, 255));
        btnAnnuler.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icons8_delete_sign_25px.png"))); // NOI18N
        btnAnnuler.setText("Annuler");
        btnAnnuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnnulerActionPerformed(evt);
            }
        });

        btnEnregistrer.setBackground(new java.awt.Color(0, 170, 245));
        btnEnregistrer.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        btnEnregistrer.setForeground(new java.awt.Color(255, 255, 255));
        btnEnregistrer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icons8_micro_sd_25px.png"))); // NOI18N
        btnEnregistrer.setText("Enregistrer");
        btnEnregistrer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnregistrerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnEnregistrer, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAnnuler, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAnnuler, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEnregistrer, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
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
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAjouterLigneRabaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAjouterLigneRabaisActionPerformed
        DefaultTableModel tableModele = (DefaultTableModel) tblRabais.getModel();
        Object[] ligne = new Object[2];
        tableModele.addRow(ligne);
    }//GEN-LAST:event_btnAjouterLigneRabaisActionPerformed

    private void btnAnnulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnnulerActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnAnnulerActionPerformed

    private void btnEnregistrerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnregistrerActionPerformed

        if (miseAJourRabais()) {

            JOptionPane.showMessageDialog(null, "Configuation rabais avec succès !", "Information",
                    JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        }
    }//GEN-LAST:event_btnEnregistrerActionPerformed

    private void btnSupprimerLigneRabaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupprimerLigneRabaisActionPerformed
        DefaultTableModel tableModele = (DefaultTableModel) tblRabais.getModel();
        int ligneSelectionnee = tblRabais.getSelectedRow();
        if (ligneSelectionnee != -1) // Si une ligne est bien selectionnée
        {
            tableModele.removeRow(ligneSelectionnee);
        }
    }//GEN-LAST:event_btnSupprimerLigneRabaisActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    
                    RabaisForm dialog = new RabaisForm(new javax.swing.JFrame(), true);
                    dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent e) {
                            System.exit(0);
                        }
                    });
                    dialog.setVisible(true);
                    
                } catch (SQLException ex) {
                    Logger.getLogger(RabaisForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAjouterLigneRabais;
    private javax.swing.JButton btnAnnuler;
    private javax.swing.JButton btnEnregistrer;
    private javax.swing.JButton btnSupprimerLigneRabais;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblRabais;
    // End of variables declaration//GEN-END:variables
}
