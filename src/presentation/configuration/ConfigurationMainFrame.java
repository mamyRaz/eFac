package presentation.configuration;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author MAmy Sitraka
 */
public class ConfigurationMainFrame extends javax.swing.JFrame {

    public ConfigurationMainFrame() {
        initComponents();
    }

    private void colorerPanelEnSurvol(JLabel jlabel) {
        jlabel.setForeground(new java.awt.Color(0, 0, 170));
    }

    private void colorerParDefaut(JLabel jlabel) {
        jlabel.setForeground(new java.awt.Color(0, 170, 245));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        lblAcompte = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lblConfigRabais = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblConfigRemise = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblConfigRistourne = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        lblAcompte1 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        lblConfigTVA = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Configuration");
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(240, 240, 240), null));

        lblAcompte.setFont(new java.awt.Font("Arial", 1, 17)); // NOI18N
        lblAcompte.setText("Réductions commerciales");

        lblConfigRabais.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lblConfigRabais.setForeground(new java.awt.Color(0, 170, 245));
        lblConfigRabais.setText("> Rabais");
        lblConfigRabais.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblConfigRabaisMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblConfigRabaisMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblConfigRabaisMousePressed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setText("Ce paramètre permet d'établir les règles de decision par rapport à l'attribution de ");

        jLabel10.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel10.setText("réduction sur les produits non conformes ou défectueux.");

        lblConfigRemise.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lblConfigRemise.setForeground(new java.awt.Color(0, 170, 245));
        lblConfigRemise.setText("> Remise");
        lblConfigRemise.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblConfigRemiseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblConfigRemiseMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblConfigRemiseMousePressed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel12.setText("Ce paramètre permet d'établir les règles de decision par rapport à l'attribution de ");

        jLabel13.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel13.setText("réduction sur le nombre de produits achetés par un client.");

        lblConfigRistourne.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lblConfigRistourne.setForeground(new java.awt.Color(0, 170, 245));
        lblConfigRistourne.setText("> Ristourne");
        lblConfigRistourne.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblConfigRistourneMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblConfigRistourneMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblConfigRistourneMousePressed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel15.setText("Ce paramètre permet d'établir les règles de decision par rapport à l'attribution de ");

        jLabel16.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel16.setText("réduction sur le nombre de commandes passées par un client.");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAcompte, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addComponent(lblConfigRabais, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblConfigRemise, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblConfigRistourne, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAcompte)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblConfigRabais)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblConfigRemise)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblConfigRistourne)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(240, 240, 240), null));

        lblAcompte1.setFont(new java.awt.Font("Arial", 1, 17)); // NOI18N
        lblAcompte1.setText("TVA");

        lblConfigTVA.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lblConfigTVA.setForeground(new java.awt.Color(0, 170, 245));
        lblConfigTVA.setText("> Configuration TVA");
        lblConfigTVA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblConfigTVAMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblConfigTVAMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblConfigTVAMousePressed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel7.setText("Ce paramètre permet d'ajouter un nouveau TVA et de modifier.");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(lblAcompte1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(204, 204, 204))
                    .addComponent(jSeparator2)
                    .addComponent(lblConfigTVA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAcompte1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblConfigTVA)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(405, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblConfigRabaisMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblConfigRabaisMousePressed
        try {

            RabaisForm rabaisForm = new RabaisForm(this, true);
            rabaisForm.setLocationRelativeTo(this);
            rabaisForm.setVisible(true);

        } catch (SQLException ex) {
            Logger.getLogger(ConfigurationMainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lblConfigRabaisMousePressed

    private void lblConfigTVAMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblConfigTVAMouseEntered
        colorerPanelEnSurvol(lblConfigTVA);
    }//GEN-LAST:event_lblConfigTVAMouseEntered

    private void lblConfigTVAMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblConfigTVAMousePressed
        try {

            TVAMainFrame tvaMainFrame = new TVAMainFrame(this, true);
            tvaMainFrame.setLocationRelativeTo(this);
            tvaMainFrame.setVisible(true);

        } catch (SQLException ex) {
            Logger.getLogger(ConfigurationMainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lblConfigTVAMousePressed

    private void lblConfigRabaisMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblConfigRabaisMouseEntered
        colorerPanelEnSurvol(lblConfigRabais);
    }//GEN-LAST:event_lblConfigRabaisMouseEntered

    private void lblConfigRemiseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblConfigRemiseMouseEntered
        colorerPanelEnSurvol(lblConfigRemise);
    }//GEN-LAST:event_lblConfigRemiseMouseEntered

    private void lblConfigRistourneMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblConfigRistourneMouseEntered
        colorerPanelEnSurvol(lblConfigRistourne);
    }//GEN-LAST:event_lblConfigRistourneMouseEntered

    private void lblConfigRemiseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblConfigRemiseMouseExited
        colorerParDefaut(lblConfigRemise);
    }//GEN-LAST:event_lblConfigRemiseMouseExited

    private void lblConfigRabaisMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblConfigRabaisMouseExited
        colorerParDefaut(lblConfigRabais);
    }//GEN-LAST:event_lblConfigRabaisMouseExited

    private void lblConfigRistourneMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblConfigRistourneMouseExited
        colorerParDefaut(lblConfigRistourne);
    }//GEN-LAST:event_lblConfigRistourneMouseExited

    private void lblConfigTVAMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblConfigTVAMouseExited
        colorerParDefaut(lblConfigTVA);
    }//GEN-LAST:event_lblConfigTVAMouseExited

    private void lblConfigRistourneMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblConfigRistourneMousePressed
        try {

            RistourneForm ristourneForm = new RistourneForm(this, true);
            ristourneForm.setLocationRelativeTo(this);
            ristourneForm.setVisible(true);

        } catch (SQLException ex) {
            Logger.getLogger(ConfigurationMainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_lblConfigRistourneMousePressed

    private void lblConfigRemiseMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblConfigRemiseMousePressed
        try {

            RemiseForm remiseForm = new RemiseForm(this, true);
            remiseForm.setLocationRelativeTo(this);
            remiseForm.setVisible(true);

        } catch (SQLException ex) {
            Logger.getLogger(ConfigurationMainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lblConfigRemiseMousePressed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConfigurationMainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblAcompte;
    private javax.swing.JLabel lblAcompte1;
    private javax.swing.JLabel lblConfigRabais;
    private javax.swing.JLabel lblConfigRemise;
    private javax.swing.JLabel lblConfigRistourne;
    private javax.swing.JLabel lblConfigTVA;
    // End of variables declaration//GEN-END:variables
}
