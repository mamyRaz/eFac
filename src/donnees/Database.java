
package donnees;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;



/**
 *
 * @author Mamy Sitraka
 */
public class Database {
    
    private Connection connection = null;
 
    public Database() {
        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/facturation?autoReconnect=true&useSSL=false","root","");
        
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SQLException ex) {
            //a pop-up box
            JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de données !","Erreur",
                                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public Connection getConnetion() {
        return connection;
    }
}
