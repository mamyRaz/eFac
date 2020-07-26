
package donnees;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import metier.Entreprise;

/**
 *
 * @author Mamy Sitraka
 */
public class EntrepriseModele {
    
    private final Database db;
    private final Connection connexion;

    public EntrepriseModele() {
        db = new Database();
        connexion = db.getConnetion();
    }
    
    public Entreprise getEntreprise(int id_entreprise) throws SQLException{
    Entreprise entreprise = null;
        PreparedStatement pst;
        ResultSet res;
        int id, nif, stat;
        double capital_social;
        String nom, adresse, telephone, email;
        //requete preparee pour récupérer tous les devises
        pst = connexion.prepareStatement("select * from entreprise");

        res = pst.executeQuery();
        if (res != null) { //Tester si la requête retourne des résultats non vides
            while (res.next()) { //Parcourir tous les résultats
                id = res.getInt("id_entreprise");
                nif = res.getInt("nif");
                stat = res.getInt("stat");
                capital_social = res.getDouble("capital_social");
                nom = res.getString("nom");
                adresse = res.getString("adresse");
                telephone = res.getString("telephone");
                email = res.getString("email");

                entreprise = new Entreprise(id, nom, nif, stat, capital_social, adresse, telephone, email); //Création d'un objet entreprise
                return entreprise;
            }

            
        } 
        return entreprise;
    }

}
