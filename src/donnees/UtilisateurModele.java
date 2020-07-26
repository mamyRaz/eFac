package donnees;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import metier.Profil;
import metier.Utilisateur;

/**
 *
 * @author Mamy Sitraka
 */
public class UtilisateurModele {

    private final Database db;
    private final Connection connexion;

    public UtilisateurModele() {
        db = new Database();
        connexion = db.getConnetion();
    }

    public ArrayList<Utilisateur> getTousUtilisateurs() {
        ArrayList<Utilisateur> utilisateurs = null;
        try {

            Utilisateur utilisateur;
            Profil profil;
            PreparedStatement pst;
            ResultSet res;

            int id_utilisateur, id_profil;
            String nom, prenom, designation_profil, commentaire, login, password;
            //requete preparee pour récupérer tous les utilisateurs
            pst = connexion.prepareStatement("select * from utilisateur as u join profil as p on u.profil_id=p.id_profil");
            res = pst.executeQuery();
            if (res != null) { //Tester si la requête retourne des résultats non vides
                utilisateurs = new ArrayList<>();

                while (res.next()) { //Parcourir tous les résultats
                    id_utilisateur = res.getInt("id_utilisateur");
                    id_profil = res.getInt("id_profil");
                    nom = res.getString("nom");
                    prenom = res.getString("prenom");
                    designation_profil = res.getString("designation_profil");
                    commentaire = res.getString("commentaire");
                    login = res.getString("login");
                    password = res.getString("password");

                    profil = new Profil(id_profil, designation_profil, commentaire);
                    utilisateur = new Utilisateur(id_utilisateur, nom, prenom, profil, login, password); //Création d'un objet utilisateur
                    utilisateurs.add(utilisateur);
                }
                return utilisateurs;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClientModele.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erreur de récupération de tous les utilisateurs !", "Erreur",
                    JOptionPane.ERROR_MESSAGE);
        }
        return utilisateurs;
    }

    public boolean supprimerUtilisateur(int id_utilisateur) {
        try {
            PreparedStatement pst;

            pst = connexion.prepareStatement("delete from utilisateur where id_utilisateur=?");
            pst.setInt(1, id_utilisateur);

            if (pst.executeUpdate() <= 0) {
                JOptionPane.showMessageDialog(null, "Erreur d'execution de suppression de l'utilisateur dans la base de données !", "Erreur",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(FactureModele.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erreur de suppression de l'utilisateur !", "Erreur",
                    JOptionPane.ERROR_MESSAGE);
        }
        return true;
    }

    public boolean enregistrerUtilisateur(Utilisateur utilisateur) {
        try {
            PreparedStatement pst;

            pst = connexion.prepareStatement("insert into utilisateur (profil_id,nom,prenom,login,password) values (?,?,?,?,?)");

            if (utilisateur.getProfil() != null) {
                pst.setInt(1, utilisateur.getProfil().getId());
                pst.setString(2, utilisateur.getNom());
                pst.setString(3, utilisateur.getPrenom());
                pst.setString(4, utilisateur.getLogin());
                pst.setString(5, utilisateur.getPassword());
            } else {
                JOptionPane.showMessageDialog(null, "Profil utilisateur inconnu pour l'enregistrement !", "Erreur",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }
            //Insertion dans la table utilisateur
            if (pst.executeUpdate() <= 0) {
                JOptionPane.showMessageDialog(null, "Erreur d'execution de l'enregistrement de l'utilisateur dans la base de données !", "Erreur",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(FactureModele.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erreur de l'enregistrement de l'utilisateur !", "Erreur",
                    JOptionPane.ERROR_MESSAGE);
        }
        return true;
    }

    public boolean modifierUtilisateur(Utilisateur utilisateur) {
        try {
            PreparedStatement pst;

            pst = connexion.prepareStatement("update utilisateur set profil_id=?, nom=?, prenom=?, login=?, password=? where id_utilisateur=?");

            if (utilisateur.getProfil() != null) {
                pst.setInt(1, utilisateur.getProfil().getId());
                pst.setString(2, utilisateur.getNom());
                pst.setString(3, utilisateur.getPrenom());
                pst.setString(4, utilisateur.getLogin());
                pst.setString(5, utilisateur.getPassword());
                pst.setInt(6, utilisateur.getId());
            } else {
                JOptionPane.showMessageDialog(null, "Profil inconnu pour la modification de cet utilisateur !", "Erreur",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }
            //Insertion dans la table utilisateur
            if (pst.executeUpdate() <= 0) {
                JOptionPane.showMessageDialog(null, "Erreur d'execution de la modification de l'utilisateur dans la base de données !", "Erreur",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(FactureModele.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erreur de la modification de l'utilisateur !", "Erreur",
                    JOptionPane.ERROR_MESSAGE);
        }
        return true;
    }

    public ArrayList<Profil> getTousProfils() {
        ArrayList<Profil> profils = null;
        try {

            Profil profil;
            PreparedStatement pst;
            ResultSet res;

            int id_profil;
            String designation_profil, commentaire;
            //requete preparee pour récupérer tous les utilisateurs
            pst = connexion.prepareStatement("select * from profil");
            res = pst.executeQuery();
            if (res != null) { //Tester si la requête retourne des résultats non vides
                profils = new ArrayList<>();

                while (res.next()) { //Parcourir tous les résultats
                    id_profil = res.getInt("id_profil");
                    designation_profil = res.getString("designation_profil");
                    commentaire = res.getString("commentaire");

                    profil = new Profil(id_profil, designation_profil, commentaire);
                    profils.add(profil);
                }
                return profils;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClientModele.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erreur de récupération de tous les profils !", "Erreur",
                    JOptionPane.ERROR_MESSAGE);
        }
        return profils;
    }
}
