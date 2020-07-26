package presentation.client;

import donnees.ClientModele;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import metier.Client;

/**
 *
 * @author Mamy Sitraka
 */
public class ClientMainFrame extends javax.swing.JFrame {

    private final ClientModele clientModele;//La classe contenant les requêtes sur les utilisateurs
    private ArrayList<Client> clients;
    private int totalClients;

    public ClientMainFrame() {
        initComponents();
        clientModele = new ClientModele();
        clients = new ArrayList<>();
        initialiserValeurComposants();
    }

    private void colorerPanelEnSurvol(JPanel panel) {
        panel.setBackground(new java.awt.Color(0, 145, 255));
    }

    private void colorerParDefaut(JPanel panel) {
        panel.setBackground(new java.awt.Color(0, 170, 245));
    }

    private void initialiserValeurComposants() {
        initialiserTableauClients();
    }

    private void initialiserTableauClients() {
        afficherTousClients();
    }

    public void afficherTousClients() {
        DefaultTableModel tableClientModele = (DefaultTableModel) tblClient.getModel(); //Récupérer le modèle de la table des factures
        if (tableClientModele != null) {
            tableClientModele.setRowCount(0); //Vider le modele de la table client
        }

        clients = clientModele.getTousClients();

        totalClients = clients.size(); //Nombre total des clients

        if (clients.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Le liste des clients est vide !", "Information",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            Object row[];
            for (Client client : clients) {
                row = new Object[8];

                client.setToStringType("tableau");

                row[0] = client; //Execute la méthode toString() de la classe Client
                row[1] = client.getNom();
                row[2] = client.getPrenom();
                row[3] = client.getClientType();
                row[4] = client.getSIRET();
                row[5] = client.getAdresse();
                row[6] = client.getTelephone();
                row[7] = client.getEmail();

                tableClientModele.addRow(row);
            }
        }

        mettreAJourStatistique(); //Affichage des statistiques
    }

    private void mettreAJourStatistique() {
        lblTotalClients.setText(String.valueOf(totalClients));
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panelBtnNouveauClient = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        panelBtnModifierClient = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        panelBtnSupprimerClient = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblClient = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        lblTotalClients = new javax.swing.JLabel();
        lblTotalHT2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Liste des clients");
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(0, 170, 245));

        panelBtnNouveauClient.setBackground(new java.awt.Color(0, 170, 245));
        panelBtnNouveauClient.setPreferredSize(new java.awt.Dimension(130, 85));
        panelBtnNouveauClient.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelBtnNouveauClientMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelBtnNouveauClientMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelBtnNouveauClientMousePressed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Nouveau client");

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icons8_plus_30px.png"))); // NOI18N

        javax.swing.GroupLayout panelBtnNouveauClientLayout = new javax.swing.GroupLayout(panelBtnNouveauClient);
        panelBtnNouveauClient.setLayout(panelBtnNouveauClientLayout);
        panelBtnNouveauClientLayout.setHorizontalGroup(
            panelBtnNouveauClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnNouveauClientLayout.createSequentialGroup()
                .addGroup(panelBtnNouveauClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBtnNouveauClientLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel15))
                    .addGroup(panelBtnNouveauClientLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel14)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        panelBtnNouveauClientLayout.setVerticalGroup(
            panelBtnNouveauClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnNouveauClientLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addGap(18, 18, 18))
        );

        panelBtnModifierClient.setBackground(new java.awt.Color(0, 170, 245));
        panelBtnModifierClient.setPreferredSize(new java.awt.Dimension(130, 85));
        panelBtnModifierClient.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelBtnModifierClientMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelBtnModifierClientMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelBtnModifierClientMousePressed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Modifier client");

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icons8_edit_30px.png"))); // NOI18N

        javax.swing.GroupLayout panelBtnModifierClientLayout = new javax.swing.GroupLayout(panelBtnModifierClient);
        panelBtnModifierClient.setLayout(panelBtnModifierClientLayout);
        panelBtnModifierClientLayout.setHorizontalGroup(
            panelBtnModifierClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnModifierClientLayout.createSequentialGroup()
                .addGroup(panelBtnModifierClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBtnModifierClientLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel23))
                    .addGroup(panelBtnModifierClientLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel22)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        panelBtnModifierClientLayout.setVerticalGroup(
            panelBtnModifierClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnModifierClientLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel22)
                .addGap(18, 18, 18))
        );

        panelBtnSupprimerClient.setBackground(new java.awt.Color(0, 170, 245));
        panelBtnSupprimerClient.setPreferredSize(new java.awt.Dimension(130, 85));
        panelBtnSupprimerClient.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelBtnSupprimerClientMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelBtnSupprimerClientMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelBtnSupprimerClientMousePressed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Supprimer client");

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icons8_delete_sign_30px_1.png"))); // NOI18N

        javax.swing.GroupLayout panelBtnSupprimerClientLayout = new javax.swing.GroupLayout(panelBtnSupprimerClient);
        panelBtnSupprimerClient.setLayout(panelBtnSupprimerClientLayout);
        panelBtnSupprimerClientLayout.setHorizontalGroup(
            panelBtnSupprimerClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnSupprimerClientLayout.createSequentialGroup()
                .addGroup(panelBtnSupprimerClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBtnSupprimerClientLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel24))
                    .addGroup(panelBtnSupprimerClientLayout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jLabel25)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        panelBtnSupprimerClientLayout.setVerticalGroup(
            panelBtnSupprimerClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnSupprimerClientLayout.createSequentialGroup()
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
                .addComponent(panelBtnNouveauClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(panelBtnModifierClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(panelBtnSupprimerClient, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBtnNouveauClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(panelBtnModifierClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(panelBtnSupprimerClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jScrollPane1.setBackground(new java.awt.Color(247, 247, 247));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        tblClient.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tblClient.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Numéro", "Nom", "Prenom", "Type de client", "Numéro SIRET", "Adresse", "Téléphone", "Email"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblClient.setGridColor(new java.awt.Color(230, 230, 230));
        tblClient.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblClient.setRowHeight(35);
        tblClient.setSelectionBackground(java.awt.SystemColor.controlHighlight);
        tblClient.setShowVerticalLines(false);
        tblClient.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblClient);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        lblTotalClients.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblTotalClients.setText("0");

        lblTotalHT2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblTotalHT2.setText("Nombre total des clients :");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTotalHT2)
                .addGap(18, 18, 18)
                .addComponent(lblTotalClients, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(843, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotalClients)
                    .addComponent(lblTotalHT2))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 566, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void panelBtnNouveauClientMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnNouveauClientMouseEntered
        colorerPanelEnSurvol(panelBtnNouveauClient);
    }//GEN-LAST:event_panelBtnNouveauClientMouseEntered

    private void panelBtnNouveauClientMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnNouveauClientMouseExited
        colorerParDefaut(panelBtnNouveauClient);
    }//GEN-LAST:event_panelBtnNouveauClientMouseExited

    private void panelBtnNouveauClientMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnNouveauClientMousePressed
        try {
            ClientForm clientForm = new ClientForm(this, true, this, "nouveau");
            clientForm.setLocationRelativeTo(null);
            clientForm.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(ClientMainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_panelBtnNouveauClientMousePressed

    private void panelBtnModifierClientMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnModifierClientMouseEntered
        colorerPanelEnSurvol(panelBtnModifierClient);
    }//GEN-LAST:event_panelBtnModifierClientMouseEntered

    private void panelBtnModifierClientMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnModifierClientMouseExited
        colorerParDefaut(panelBtnModifierClient);
    }//GEN-LAST:event_panelBtnModifierClientMouseExited

    private void panelBtnSupprimerClientMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnSupprimerClientMouseEntered
        colorerPanelEnSurvol(panelBtnSupprimerClient);
    }//GEN-LAST:event_panelBtnSupprimerClientMouseEntered

    private void panelBtnSupprimerClientMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnSupprimerClientMouseExited
        colorerParDefaut(panelBtnSupprimerClient);
    }//GEN-LAST:event_panelBtnSupprimerClientMouseExited

    private void panelBtnSupprimerClientMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnSupprimerClientMousePressed
        if (tblClient.getSelectedRowCount() > 0) {
            DefaultTableModel tableClientModele = (DefaultTableModel) tblClient.getModel();
            //Récupérer le client dans la première colonne de la ligne selectionnée
            Client client = (Client) tableClientModele.getValueAt(tblClient.getSelectedRow(), 0);

            int reponse = JOptionPane.showConfirmDialog(this, "Voulez-vous vraiment supprimer ce client ?", "Demande de confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (reponse == JOptionPane.YES_OPTION) {
                if (clientModele.supprimerClient(client.getId())) {
                    JOptionPane.showMessageDialog(null, "Client bien supprimé", "Information",
                            JOptionPane.INFORMATION_MESSAGE);
                    afficherTousClients();
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Veuillez selectionner le client à supprimer", "Erreur",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_panelBtnSupprimerClientMousePressed

    private void tblClientMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientMouseClicked

    }//GEN-LAST:event_tblClientMouseClicked

    private void panelBtnModifierClientMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnModifierClientMousePressed
        try {
            if (tblClient.getSelectedRowCount() > 0) {
                DefaultTableModel tableClientModele = (DefaultTableModel) tblClient.getModel();
                //Récupérer le client dans la première colonne de la ligne selectionnée
                Client client = (Client) tableClientModele.getValueAt(tblClient.getSelectedRow(), 0);

                ClientForm clientForm = new ClientForm(this, true, this, "modification", client);
                clientForm.setLocationRelativeTo(null);
                clientForm.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Veuillez selectionner le client à modifier", "Erreur",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientMainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_panelBtnModifierClientMousePressed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientMainFrame().setVisible(true);
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTotalClients;
    private javax.swing.JLabel lblTotalHT2;
    private javax.swing.JPanel panelBtnModifierClient;
    private javax.swing.JPanel panelBtnNouveauClient;
    private javax.swing.JPanel panelBtnSupprimerClient;
    public static javax.swing.JTable tblClient;
    // End of variables declaration//GEN-END:variables
}
