package donnees;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import metier.Client;
import metier.ClientType;

/**
 *
 * @author Mamy Sitraka
 */
public class ClientModele {

    private final Database db;
    private final Connection connexion;

    public ClientModele() {
        db = new Database();
        connexion = db.getConnetion();
    }

    public Client getClient(int id_client) throws SQLException {
        Client client = null;
        PreparedStatement pst;
        ResultSet res;
        int client_type_id;
        String nom, prenom, adresse, telephone, email, designation_type, siret;
        //requete preparee pour récupérer tous les clients
        pst = connexion.prepareStatement("select * from client as c join client_type as ct on c.client_type_id=ct.id_client_type where c.id_client=?");
        pst.setInt(1, id_client);

        res = pst.executeQuery();
        if (res != null) { //Tester si la requête retourne des résultats non vides
            while (res.next()) { //Parcourir tous les résultats
                nom = res.getString("nom");
                prenom = res.getString("prenom");
                adresse = res.getString("adresse");
                telephone = res.getString("telephone");
                email = res.getString("email");
                client_type_id = res.getInt("client_type_id");
                designation_type = res.getString("designation_type");
                siret = res.getString("siret");

                ClientType clientType = new ClientType(client_type_id, designation_type);
                client = new Client(id_client, clientType, nom, prenom, adresse, telephone, email, siret); //Création d'un objet client
                return client;
            }

        }
        return client;
    }

    public ArrayList<Client> getTousClients() {
        ArrayList<Client> clients = null;
        try {

            Client client;
            PreparedStatement pst;
            ResultSet res;
            int id_client, client_type_id;
            String nom, prenom, adresse, telephone, email, designation_type, siret;
            //requete preparee pour récupérer tous les devises
            pst = connexion.prepareStatement("select * from client as c join client_type as ct on c.client_type_id=ct.id_client_type");
            res = pst.executeQuery();
            if (res != null) { //Tester si la requête retourne des résultats non vides
                clients = new ArrayList<>();

                while (res.next()) { //Parcourir tous les résultats
                    id_client = res.getInt("id_client");
                    nom = res.getString("nom");
                    prenom = res.getString("prenom");
                    adresse = res.getString("adresse");
                    telephone = res.getString("telephone");
                    email = res.getString("email");
                    client_type_id = res.getInt("client_type_id");
                    designation_type = res.getString("designation_type");
                    siret = res.getString("siret");

                    ClientType clientType = new ClientType(client_type_id, designation_type);
                    client = new Client(id_client, clientType, nom, prenom, adresse, telephone, email, siret); //Création d'un objet entreprise
                    clients.add(client);
                }
                return clients;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClientModele.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erreur de récupération de tous les clients !", "Erreur",
                    JOptionPane.ERROR_MESSAGE);
        }
        return clients;
    }

    public boolean supprimerClient(int id_client) {
        try {
            PreparedStatement pst;

            pst = connexion.prepareStatement("delete from client where id_client=?");
            pst.setInt(1, id_client);

            if (pst.executeUpdate() <= 0) {
                JOptionPane.showMessageDialog(null, "Erreur d'execution de suppression du client dans la base de données !", "Erreur",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(FactureModele.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erreur de suppression du client !", "Erreur",
                    JOptionPane.ERROR_MESSAGE);
        }
        return true;
    }

    public boolean enregistrerClient(Client client) {
        try {
            PreparedStatement pst;

            pst = connexion.prepareStatement("insert into client (client_type_id,nom,prenom,adresse,telephone,email,siret) values (?,?,?,?,?,?,?)");

            if (client.getClientType() != null) {
                pst.setInt(1, client.getClientType().getId());
                pst.setString(2, client.getNom());
                pst.setString(3, client.getPrenom());
                pst.setString(4, client.getAdresse());
                pst.setString(5, client.getTelephone());
                pst.setString(6, client.getEmail());
                pst.setString(7, client.getSIRET());
            } else {
                JOptionPane.showMessageDialog(null, "Type de client inconnu pour l'enregistrement !", "Erreur",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }
            //Insertion dans la table client
            if (pst.executeUpdate() <= 0) {
                JOptionPane.showMessageDialog(null, "Erreur d'execution de l'enregistrement du client dans la base de données !", "Erreur",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(FactureModele.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erreur de l'enregistrement du client !", "Erreur",
                    JOptionPane.ERROR_MESSAGE);
        }
        return true;
    }

    public ArrayList<ClientType> getTousClientType() throws SQLException {
        ArrayList<ClientType> clientTypes = null;
        PreparedStatement pst;
        ResultSet res;
        int id_client_type;
        String designation_type;
        //requete preparée pour récupérer tous types de client
        pst = connexion.prepareStatement("select * from client_type");

        res = pst.executeQuery();
        if (res != null) { //Tester si la requête retourne des résultats non vides
            clientTypes = new ArrayList<>();

            while (res.next()) { //Parcourir tous les résultats
                id_client_type = res.getInt("id_client_type");
                designation_type = res.getString("designation_type");

                ClientType clientType = new ClientType(id_client_type, designation_type);
                clientTypes.add(clientType);
            }
            return clientTypes;
        }
        return clientTypes;
    }

    public boolean modifierClient(Client client) {
        try {
            PreparedStatement pst;

            pst = connexion.prepareStatement("update client set client_type_id=?, nom=?, prenom=?, adresse=?, telephone=?, email=?, siret=? where id_client=?");

            if (client.getClientType() != null) {
                pst.setInt(1, client.getClientType().getId());
                pst.setString(2, client.getNom());
                pst.setString(3, client.getPrenom());
                pst.setString(4, client.getAdresse());
                pst.setString(5, client.getTelephone());
                pst.setString(6, client.getEmail());
                pst.setString(7, client.getSIRET());
                pst.setInt(8, client.getId());
            } else {
                JOptionPane.showMessageDialog(null, "Type de client inconnu pour la modification !", "Erreur",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }
            //Insertion dans la table client
            if (pst.executeUpdate() <= 0) {
                JOptionPane.showMessageDialog(null, "Erreur d'execution de la modification du client dans la base de données !", "Erreur",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(FactureModele.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erreur de la modification du client !", "Erreur",
                    JOptionPane.ERROR_MESSAGE);
        }
        return true;
    }
}
