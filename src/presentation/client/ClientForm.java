package presentation.client;

import donnees.ClientModele;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import metier.Client;
import metier.ClientType;

/**
 *
 * @author Mamy Sitraka
 */
public class ClientForm extends javax.swing.JDialog {
    
    private Client client;
    private ClientMainFrame clientMainFrame;
    private ClientModele clientModele;
    private String typeOperation;
    
    public ClientForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    public ClientForm(java.awt.Frame parent, boolean modal, ClientMainFrame clientMainFrame, String typeOperation) throws SQLException {
        this(parent, modal);
        this.typeOperation = typeOperation;
        this.clientMainFrame = clientMainFrame;
        clientModele = new ClientModele();
        if (typeOperation.equals("nouveau")) {
            client = new Client();
        }
        initialiserValeurComposants();
    }
    
    public ClientForm(java.awt.Frame parent, boolean modal, ClientMainFrame clientMainFrame, String typeOperation, Client client) throws SQLException {
        this(parent, modal, clientMainFrame, typeOperation);
        this.client = client;
        initialiserFormulaire();
    }
    
    private void initialiserValeurComposants() throws SQLException {
        initialiserTitre();
        initialiserComboboxClientType();
    }
    
    private void initialiserFormulaire() {
        txtNomClient.setText(client.getNom());
        txtPrenomClient.setText(client.getPrenom());
        txtAdresseClient.setText(client.getAdresse());
        txtTelephoneClient.setText(client.getTelephone());
        txtEmailClient.setText(client.getEmail());
    }
    
    private void initialiserTitre() {
        if (typeOperation.equals("nouveau")) {
            setTitle("Nouveau client");
        } else {
            setTitle("Modification du client");
        }
    }
    
    private boolean initialiserComboboxClientType() throws SQLException {
        DefaultComboBoxModel cbxClientTypeModel = new DefaultComboBoxModel();
        
        if (!clientModele.getTousClientType().isEmpty()) { //Tester si la requête retourne des résultats non vides
            for (ClientType clientType : clientModele.getTousClientType()) {
                
                cbxClientTypeModel.addElement(clientType); //Ajouter l'objet dans le ComboboxModel
            }
            
            cbxTypeClient.setModel(cbxClientTypeModel);//Inserer le comboboxModel dans le composant

            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Type de client introuvable dans la base de données !", "Erreur",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
    }
    
    private boolean enregistrerClient() {
        if (!txtNomClient.getText().equals("") || !txtPrenomClient.getText().equals("") || !txtAdresseClient.getText().equals("")) {
            ClientType clientType = (ClientType) cbxTypeClient.getSelectedItem();
            if (clientType != null) {
                client.setClientType(clientType);
                client.setNom(txtNomClient.getText());
                client.setPrenom(txtPrenomClient.getText());
                client.setAdresse(txtAdresseClient.getText());
                client.setTelephone(txtTelephoneClient.getText());
                client.setEmail(txtEmailClient.getText());
                if (txtSIRET.isVisible()) {
                    client.setSIRET(txtSIRET.getText());
                } else {
                    client.setSIRET("");
                }
                
            } else {
                JOptionPane.showMessageDialog(null, "Veuillez selectionner le type de client", "Erreur",
                        JOptionPane.ERROR_MESSAGE);
                return false;
                
            }
        } else {
            JOptionPane.showMessageDialog(null, "Veuillez bien remplir au moins les champs nom, prenoms et adresse", "Erreur",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
    public void setClient(Client client) {
        this.client = client;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txtNomClient = new javax.swing.JTextField();
        txtPrenomClient = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtAdresseClient = new javax.swing.JTextField();
        txtTelephoneClient = new javax.swing.JTextField();
        txtEmailClient = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cbxTypeClient = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        txtSIRET = new javax.swing.JTextField();
        lblSIRET = new javax.swing.JLabel();
        btnAnnulerFacture = new javax.swing.JButton();
        btnEnregistrerClient = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(0, 170, 245));

        jLabel21.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Fiche client");

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

        txtNomClient.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        txtPrenomClient.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setText("Prénom(s)");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setText("Nom");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel7.setText("Adresse");

        txtAdresseClient.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        txtTelephoneClient.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        txtEmailClient.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel9.setText("email");

        jLabel8.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel8.setText("Téléphone");

        cbxTypeClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxTypeClientActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel10.setText("Type de client");

        txtSIRET.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lblSIRET.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblSIRET.setText("SIRET");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(lblSIRET))
                .addGap(56, 56, 56)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSIRET)
                    .addComponent(cbxTypeClient, 0, 439, Short.MAX_VALUE)
                    .addComponent(txtNomClient)
                    .addComponent(txtPrenomClient)
                    .addComponent(txtAdresseClient)
                    .addComponent(txtTelephoneClient)
                    .addComponent(txtEmailClient))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxTypeClient, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(45, 45, 45))
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(28, 28, 28)
                                .addComponent(jLabel7)
                                .addGap(45, 45, 45))
                            .addComponent(jLabel8))
                        .addGap(58, 58, 58))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(txtNomClient, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtPrenomClient, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtAdresseClient, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTelephoneClient, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtEmailClient, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSIRET)
                    .addComponent(txtSIRET, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );

        btnAnnulerFacture.setBackground(new java.awt.Color(0, 170, 245));
        btnAnnulerFacture.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        btnAnnulerFacture.setForeground(new java.awt.Color(255, 255, 255));
        btnAnnulerFacture.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icons8_delete_sign_25px.png"))); // NOI18N
        btnAnnulerFacture.setText("Annuler");
        btnAnnulerFacture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnnulerFactureActionPerformed(evt);
            }
        });

        btnEnregistrerClient.setBackground(new java.awt.Color(0, 170, 245));
        btnEnregistrerClient.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        btnEnregistrerClient.setForeground(new java.awt.Color(255, 255, 255));
        btnEnregistrerClient.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icons8_micro_sd_25px.png"))); // NOI18N
        btnEnregistrerClient.setText("Enregistrer");
        btnEnregistrerClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnregistrerClientActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(206, Short.MAX_VALUE)
                .addComponent(btnEnregistrerClient, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAnnulerFacture, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(btnAnnulerFacture, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEnregistrerClient, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAnnulerFactureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnnulerFactureActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnAnnulerFactureActionPerformed

    private void btnEnregistrerClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnregistrerClientActionPerformed
        if (typeOperation.equals("nouveau")) {
            if (enregistrerClient()) {
                if (clientModele.enregistrerClient(client)) {
                    clientMainFrame.afficherTousClients();
                    JOptionPane.showMessageDialog(null, "Client bien enregistré", "Information",
                            JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                }
            }
        } else {
            if (enregistrerClient()) {
                if (clientModele.modifierClient(client)) {
                    clientMainFrame.afficherTousClients();
                    JOptionPane.showMessageDialog(null, "Client à jour", "Information",
                            JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                }
            }
        }
    }//GEN-LAST:event_btnEnregistrerClientActionPerformed

    private void cbxTypeClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTypeClientActionPerformed
        ClientType clientType = (ClientType) cbxTypeClient.getSelectedItem();
        if (clientType.getDesignation_type().equals("entreprise")) {
            txtPrenomClient.setEnabled(false);
            txtPrenomClient.setText("");
            txtSIRET.setVisible(false);
            lblSIRET.setVisible(false);
        } else {
            txtPrenomClient.setEnabled(true);
        }
    }//GEN-LAST:event_cbxTypeClientActionPerformed
    
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ClientForm dialog = new ClientForm(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnAnnulerFacture;
    private javax.swing.JButton btnEnregistrerClient;
    private javax.swing.JComboBox<String> cbxTypeClient;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lblSIRET;
    private javax.swing.JTextField txtAdresseClient;
    private javax.swing.JTextField txtEmailClient;
    private javax.swing.JTextField txtNomClient;
    private javax.swing.JTextField txtPrenomClient;
    private javax.swing.JTextField txtSIRET;
    private javax.swing.JTextField txtTelephoneClient;
    // End of variables declaration//GEN-END:variables
}
