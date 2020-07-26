package presentation.utilisateur;

import donnees.UtilisateurModele;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import metier.Utilisateur;

/**
 *
 * @author Mamy Sitraka
 */
public class UtilisateurMainFrame extends javax.swing.JFrame {

    private final UtilisateurModele utilisateurModele;//La classe contenant les requêtes sur les factures
    private ArrayList<Utilisateur> utilisateurs;
    private int totalUtilisateurs;

    public UtilisateurMainFrame() {
        initComponents();
        utilisateurModele = new UtilisateurModele();
        utilisateurs = new ArrayList<>();
        initialiserValeurComposants();
    }

    private void initialiserValeurComposants() {
        afficherTousUtilisateurs();
    }

    public void afficherTousUtilisateurs() {
        DefaultTableModel tableUtilisateurModele = (DefaultTableModel) tblUtilisateur.getModel(); //Récupérer le modèle de la table des factures
        if (tableUtilisateurModele != null) {
            tableUtilisateurModele.setRowCount(0); //Vider le modele de la table client
        }

        utilisateurs = utilisateurModele.getTousUtilisateurs();

        totalUtilisateurs = utilisateurs.size(); //Nombre total des utilisateurs

        if (utilisateurs.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Le liste des utilisateurs est vide !", "Information",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            Object row[];
            for (Utilisateur utilisateur : utilisateurs) {
                row = new Object[5];
                row[0] = utilisateur; //Execute la méthode toString() de la classe Utilisateur
                row[1] = utilisateur.getNom();
                row[2] = utilisateur.getPrenom();
                row[3] = utilisateur.getProfil();
                row[4] = utilisateur.getLogin();

                tableUtilisateurModele.addRow(row);
            }
        }

        mettreAJourStatistique(); //Affichage des statistiques
    }

    private void mettreAJourStatistique() {
        lblTotalUtilisateurs.setText(String.valueOf(totalUtilisateurs));
    }

    public ArrayList<Utilisateur> getUtilisateurs() {
        return utilisateurs;
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        panelBtnNouvelUtilisateur = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        panelBtnModifierUtilisateur = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        panelBtnSupprimerUtilisateur = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUtilisateur = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        lblTotalUtilisateurs = new javax.swing.JLabel();
        lblLibeleTotalUtilisateurs = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Liste des utilisateurs");
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(0, 170, 245));

        panelBtnNouvelUtilisateur.setBackground(new java.awt.Color(0, 170, 245));
        panelBtnNouvelUtilisateur.setPreferredSize(new java.awt.Dimension(130, 85));
        panelBtnNouvelUtilisateur.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelBtnNouvelUtilisateurMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelBtnNouvelUtilisateurMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelBtnNouvelUtilisateurMousePressed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Nouvel uitlisateur");

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icons8_plus_30px.png"))); // NOI18N

        javax.swing.GroupLayout panelBtnNouvelUtilisateurLayout = new javax.swing.GroupLayout(panelBtnNouvelUtilisateur);
        panelBtnNouvelUtilisateur.setLayout(panelBtnNouvelUtilisateurLayout);
        panelBtnNouvelUtilisateurLayout.setHorizontalGroup(
            panelBtnNouvelUtilisateurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnNouvelUtilisateurLayout.createSequentialGroup()
                .addGroup(panelBtnNouvelUtilisateurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBtnNouvelUtilisateurLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel15))
                    .addGroup(panelBtnNouvelUtilisateurLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel14)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBtnNouvelUtilisateurLayout.setVerticalGroup(
            panelBtnNouvelUtilisateurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnNouvelUtilisateurLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addGap(18, 18, 18))
        );

        panelBtnModifierUtilisateur.setBackground(new java.awt.Color(0, 170, 245));
        panelBtnModifierUtilisateur.setPreferredSize(new java.awt.Dimension(130, 85));
        panelBtnModifierUtilisateur.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelBtnModifierUtilisateurMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelBtnModifierUtilisateurMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelBtnModifierUtilisateurMousePressed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Modifier utilisateur");

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icons8_edit_30px.png"))); // NOI18N

        javax.swing.GroupLayout panelBtnModifierUtilisateurLayout = new javax.swing.GroupLayout(panelBtnModifierUtilisateur);
        panelBtnModifierUtilisateur.setLayout(panelBtnModifierUtilisateurLayout);
        panelBtnModifierUtilisateurLayout.setHorizontalGroup(
            panelBtnModifierUtilisateurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBtnModifierUtilisateurLayout.createSequentialGroup()
                .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(panelBtnModifierUtilisateurLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel23)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBtnModifierUtilisateurLayout.setVerticalGroup(
            panelBtnModifierUtilisateurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnModifierUtilisateurLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22)
                .addGap(18, 18, 18))
        );

        panelBtnSupprimerUtilisateur.setBackground(new java.awt.Color(0, 170, 245));
        panelBtnSupprimerUtilisateur.setPreferredSize(new java.awt.Dimension(130, 85));
        panelBtnSupprimerUtilisateur.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelBtnSupprimerUtilisateurMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelBtnSupprimerUtilisateurMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelBtnSupprimerUtilisateurMousePressed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Supprimer utilisateur");

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icons8_delete_sign_30px_1.png"))); // NOI18N

        javax.swing.GroupLayout panelBtnSupprimerUtilisateurLayout = new javax.swing.GroupLayout(panelBtnSupprimerUtilisateur);
        panelBtnSupprimerUtilisateur.setLayout(panelBtnSupprimerUtilisateurLayout);
        panelBtnSupprimerUtilisateurLayout.setHorizontalGroup(
            panelBtnSupprimerUtilisateurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnSupprimerUtilisateurLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel25)
                .addContainerGap(55, Short.MAX_VALUE))
            .addGroup(panelBtnSupprimerUtilisateurLayout.createSequentialGroup()
                .addComponent(jLabel24)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelBtnSupprimerUtilisateurLayout.setVerticalGroup(
            panelBtnSupprimerUtilisateurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnSupprimerUtilisateurLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel24)
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(panelBtnNouvelUtilisateur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(panelBtnModifierUtilisateur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(panelBtnSupprimerUtilisateur, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBtnNouvelUtilisateur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(panelBtnModifierUtilisateur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(panelBtnSupprimerUtilisateur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jScrollPane1.setBackground(new java.awt.Color(247, 247, 247));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        tblUtilisateur.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tblUtilisateur.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Numéro", "Nom", "Prenom", "Profil", "Login"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblUtilisateur.setGridColor(new java.awt.Color(230, 230, 230));
        tblUtilisateur.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblUtilisateur.setRowHeight(35);
        tblUtilisateur.setSelectionBackground(java.awt.SystemColor.controlHighlight);
        tblUtilisateur.setShowVerticalLines(false);
        tblUtilisateur.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUtilisateurMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblUtilisateur);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        lblTotalUtilisateurs.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblTotalUtilisateurs.setText("0");

        lblLibeleTotalUtilisateurs.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblLibeleTotalUtilisateurs.setText("Nombre total des utilisateurs :");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblLibeleTotalUtilisateurs)
                .addGap(18, 18, 18)
                .addComponent(lblTotalUtilisateurs, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotalUtilisateurs)
                    .addComponent(lblLibeleTotalUtilisateurs))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 566, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void panelBtnNouvelUtilisateurMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnNouvelUtilisateurMouseEntered
        colorerPanelEnSurvol(panelBtnNouvelUtilisateur);
    }//GEN-LAST:event_panelBtnNouvelUtilisateurMouseEntered

    private void panelBtnNouvelUtilisateurMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnNouvelUtilisateurMouseExited
        colorerParDefaut(panelBtnNouvelUtilisateur);
    }//GEN-LAST:event_panelBtnNouvelUtilisateurMouseExited

    private void panelBtnNouvelUtilisateurMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnNouvelUtilisateurMousePressed

        try {
            
            UtilisateurForm UtilisateurForm = new UtilisateurForm(this, true, this, "nouveau");
            UtilisateurForm.setLocationRelativeTo(null);
            UtilisateurForm.setVisible(true);
            
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurMainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_panelBtnNouvelUtilisateurMousePressed

    private void panelBtnModifierUtilisateurMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnModifierUtilisateurMouseEntered
        colorerPanelEnSurvol(panelBtnModifierUtilisateur);
    }//GEN-LAST:event_panelBtnModifierUtilisateurMouseEntered

    private void panelBtnModifierUtilisateurMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnModifierUtilisateurMouseExited
        colorerParDefaut(panelBtnModifierUtilisateur);
    }//GEN-LAST:event_panelBtnModifierUtilisateurMouseExited

    private void panelBtnModifierUtilisateurMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnModifierUtilisateurMousePressed
        if (tblUtilisateur.getSelectedRowCount() > 0) {
            try {
                
                DefaultTableModel tableUtilisateurModele = (DefaultTableModel) tblUtilisateur.getModel();
                //Récupérer le utilisateur dans la première colonne de la ligne selectionnée
                Utilisateur utilisateur = (Utilisateur) tableUtilisateurModele.getValueAt(tblUtilisateur.getSelectedRow(), 0);
                
                UtilisateurForm utilisateurForm = new UtilisateurForm(this, true, this, "modification", utilisateur);
                utilisateurForm.setLocationRelativeTo(null);
                utilisateurForm.setVisible(true);
                
            } catch (SQLException ex) {
                Logger.getLogger(UtilisateurMainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Veuillez selectionner l'utilisateur à modifier", "Erreur",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_panelBtnModifierUtilisateurMousePressed

    private void panelBtnSupprimerUtilisateurMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnSupprimerUtilisateurMouseEntered
        colorerPanelEnSurvol(panelBtnSupprimerUtilisateur);
    }//GEN-LAST:event_panelBtnSupprimerUtilisateurMouseEntered

    private void panelBtnSupprimerUtilisateurMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnSupprimerUtilisateurMouseExited
        colorerParDefaut(panelBtnSupprimerUtilisateur);
    }//GEN-LAST:event_panelBtnSupprimerUtilisateurMouseExited

    private void panelBtnSupprimerUtilisateurMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnSupprimerUtilisateurMousePressed
        if (tblUtilisateur.getSelectedRowCount() > 0) {
            DefaultTableModel tableFactureModele = (DefaultTableModel) tblUtilisateur.getModel();
            //Récupérer le client dans la première colonne de la ligne selectionnée
            Utilisateur utilisateur = (Utilisateur) tableFactureModele.getValueAt(tblUtilisateur.getSelectedRow(), 0);

            int reponse = JOptionPane.showConfirmDialog(this, "Voulez-vous vraiment supprimer cet utilisateur ?", "Demande de confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (reponse == JOptionPane.YES_OPTION) {
                if (utilisateurModele.supprimerUtilisateur(utilisateur.getId())) {
                    JOptionPane.showMessageDialog(null, "Utilisateur bien supprimé", "Information",
                            JOptionPane.INFORMATION_MESSAGE);
                    afficherTousUtilisateurs();
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Veuillez selectionner l'utilisateur à supprimer", "Erreur",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_panelBtnSupprimerUtilisateurMousePressed

    private void tblUtilisateurMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUtilisateurMouseClicked

    }//GEN-LAST:event_tblUtilisateurMouseClicked

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UtilisateurMainFrame().setVisible(true);
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblLibeleTotalUtilisateurs;
    private javax.swing.JLabel lblTotalUtilisateurs;
    private javax.swing.JPanel panelBtnModifierUtilisateur;
    private javax.swing.JPanel panelBtnNouvelUtilisateur;
    private javax.swing.JPanel panelBtnSupprimerUtilisateur;
    public static javax.swing.JTable tblUtilisateur;
    // End of variables declaration//GEN-END:variables
}
