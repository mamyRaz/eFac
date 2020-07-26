package presentation.utilisateur;

import donnees.UtilisateurModele;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.xml.bind.DatatypeConverter;
import metier.Profil;
import metier.Utilisateur;

/**
 *
 * @author Mamy Sitraka
 */
public class UtilisateurForm extends javax.swing.JDialog {

    private String typeOperation;
    private Utilisateur utilisateur;
    private UtilisateurMainFrame utilisateurMainFrame;
    private UtilisateurModele utilisateurModele;

    public UtilisateurForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public UtilisateurForm(java.awt.Frame parent, boolean modal, UtilisateurMainFrame utilisateurMainFrame, String typeOperation) throws SQLException {
        this(parent, modal);
        this.utilisateurMainFrame = utilisateurMainFrame;
        this.typeOperation = typeOperation;
        utilisateurModele = new UtilisateurModele();
        if (typeOperation.equals("nouveau")) {
            utilisateur = new Utilisateur(); //On crée un nouvel utilisateur
        }
        initialiserValeurComposants();
    }

    public UtilisateurForm(java.awt.Frame parent, boolean modal, UtilisateurMainFrame utilisateurMainFrame, String typeOperation, Utilisateur utilisateur) throws SQLException {
        this(parent, modal, utilisateurMainFrame, typeOperation);
        this.utilisateur = utilisateur; // l'objet utilisateur passé en paramètre fait référence à l'objet utilisateur selectionné dans le tableau
        initialiserFormulaire();
    }

    private void initialiserValeurComposants() throws SQLException {
        initialiserTitre();
        initialiserComboboxProfile();
    }

    private void initialiserFormulaire() {
        txtNomUtilisateur.setText(utilisateur.getNom());
        txtPrenomUtilisateur.setText(utilisateur.getPrenom());
        txtLoginUtilisateur.setText(utilisateur.getLogin());
    }

    private void initialiserTitre() {
        if (typeOperation.equals("nouveau")) {
            setTitle("Nouvel utilisateur");
        } else {
            setTitle("Modification de l'utilisateur");
        }
    }

    private boolean initialiserComboboxProfile() {
        DefaultComboBoxModel cbxProfilModel = new DefaultComboBoxModel();

        if (!utilisateurModele.getTousProfils().isEmpty()) { //Tester si la requête retourne des résultats non vides
            for (Profil profil : utilisateurModele.getTousProfils()) {

                cbxProfilModel.addElement(profil); //Ajouter l'objet dans le ComboboxModel
            }

            cbxProfil.setModel(cbxProfilModel);//Inserer le comboboxModel dans le composant

            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Profil utilisateur introuvable dans la base de données !", "Erreur",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private boolean enregistrerUtilisateur() throws NoSuchAlgorithmException {
        if (!txtNomUtilisateur.getText().equals("") || !txtPrenomUtilisateur.getText().equals("") || !txtLoginUtilisateur.getText().equals("") || !txtMotDePasse.getPassword().equals("")) {
            Profil profil = (Profil) cbxProfil.getSelectedItem();
            if (profil != null) {
                String motDePasse = String.valueOf(txtMotDePasse.getPassword());
                String confimerMotDePasse = String.valueOf(txtConfirmerMotDePasse.getPassword());

                if (motDePasse.equals(confimerMotDePasse)) {
                    //Creer un hash en md5
                    MessageDigest md = MessageDigest.getInstance("MD5"); //on creer une instance d'objet MessageDigest
                    md.update(motDePasse.getBytes());
                    byte[] digest = md.digest();
                    String motDePasseCrypte = DatatypeConverter.printHexBinary(digest);

                    utilisateur.setProfil(profil);
                    utilisateur.setNom(txtNomUtilisateur.getText());
                    utilisateur.setPrenom(txtPrenomUtilisateur.getText());
                    utilisateur.setLogin(txtLoginUtilisateur.getText());
                    utilisateur.setPassword(motDePasseCrypte);
                } else {
                    JOptionPane.showMessageDialog(null, "Veuillez bien confirmer le mot de passe", "Erreur",
                            JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Veuillez selectionner le profil de l'utilisateur", "Erreur",
                        JOptionPane.ERROR_MESSAGE);
                return false;

            }
        } else {
            JOptionPane.showMessageDialog(null, "Veuillez bien remplir tous les champs", "Erreur",
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
        txtNomUtilisateur = new javax.swing.JTextField();
        txtPrenomUtilisateur = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtLoginUtilisateur = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        cbxProfil = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtMotDePasse = new javax.swing.JPasswordField();
        txtConfirmerMotDePasse = new javax.swing.JPasswordField();
        btnEnregistrerUtilisateur = new javax.swing.JButton();
        btnAnnulerUtilisateur = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(0, 170, 245));

        jLabel21.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Informations utilisateur");

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

        txtNomUtilisateur.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        txtPrenomUtilisateur.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setText("Prénom(s)");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setText("Nom");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel7.setText("Login");

        txtLoginUtilisateur.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel8.setText("Nouveau mot de passe");

        cbxProfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxProfilActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel10.setText("Profil");

        jLabel9.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel9.setText("Confirmer le mot de passe");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel10))
                        .addGap(56, 56, 56)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbxProfil, 0, 439, Short.MAX_VALUE)
                            .addComponent(txtNomUtilisateur)
                            .addComponent(txtPrenomUtilisateur)
                            .addComponent(txtLoginUtilisateur)
                            .addComponent(txtMotDePasse)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(35, 35, 35)
                        .addComponent(txtConfirmerMotDePasse, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxProfil, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(45, 45, 45))
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(28, 28, 28)
                                .addComponent(jLabel7))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(txtNomUtilisateur, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtPrenomUtilisateur, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtLoginUtilisateur, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(txtMotDePasse, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtConfirmerMotDePasse, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );

        btnEnregistrerUtilisateur.setBackground(new java.awt.Color(0, 170, 245));
        btnEnregistrerUtilisateur.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        btnEnregistrerUtilisateur.setForeground(new java.awt.Color(255, 255, 255));
        btnEnregistrerUtilisateur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icons8_micro_sd_25px.png"))); // NOI18N
        btnEnregistrerUtilisateur.setText("Enregistrer");
        btnEnregistrerUtilisateur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnregistrerUtilisateurActionPerformed(evt);
            }
        });

        btnAnnulerUtilisateur.setBackground(new java.awt.Color(0, 170, 245));
        btnAnnulerUtilisateur.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        btnAnnulerUtilisateur.setForeground(new java.awt.Color(255, 255, 255));
        btnAnnulerUtilisateur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icons8_delete_sign_25px.png"))); // NOI18N
        btnAnnulerUtilisateur.setText("Annuler");
        btnAnnulerUtilisateur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnnulerUtilisateurActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEnregistrerUtilisateur, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAnnulerUtilisateur, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAnnulerUtilisateur, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEnregistrerUtilisateur, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void cbxProfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxProfilActionPerformed

    }//GEN-LAST:event_cbxProfilActionPerformed

    private void btnEnregistrerUtilisateurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnregistrerUtilisateurActionPerformed
        if (typeOperation.equals("nouveau")) {
            try {

                if (enregistrerUtilisateur()) {
                    if (utilisateurModele.enregistrerUtilisateur(utilisateur)) {
                        utilisateurMainFrame.afficherTousUtilisateurs();
                        JOptionPane.showMessageDialog(null, "Utilisateur bien enregistré", "Information",
                                JOptionPane.INFORMATION_MESSAGE);
                        this.dispose();
                    }
                }

            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(UtilisateurForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {

                if (enregistrerUtilisateur()) {
                    if (utilisateurModele.modifierUtilisateur(utilisateur)) {
                        utilisateurMainFrame.afficherTousUtilisateurs();
                        JOptionPane.showMessageDialog(null, "Utilisateur à jour", "Information",
                                JOptionPane.INFORMATION_MESSAGE);
                        this.dispose();
                    }
                }

            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(UtilisateurForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnEnregistrerUtilisateurActionPerformed

    private void btnAnnulerUtilisateurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnnulerUtilisateurActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnAnnulerUtilisateurActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                UtilisateurForm dialog = new UtilisateurForm(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnAnnulerUtilisateur;
    private javax.swing.JButton btnEnregistrerUtilisateur;
    private javax.swing.JComboBox<String> cbxProfil;
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
    private javax.swing.JPasswordField txtConfirmerMotDePasse;
    private javax.swing.JTextField txtLoginUtilisateur;
    private javax.swing.JPasswordField txtMotDePasse;
    private javax.swing.JTextField txtNomUtilisateur;
    private javax.swing.JTextField txtPrenomUtilisateur;
    // End of variables declaration//GEN-END:variables
}
