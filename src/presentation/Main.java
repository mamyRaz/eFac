
package presentation;

import javax.swing.JFrame;

/**
 *
 * @author Mamy Sitraka
 */
public class Main {

    public static void main(String[] args) {
        LoginForm login = new LoginForm();
        login.setVisible(true);
        login.setLocationRelativeTo(null);//centrer le Frame
        login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//on ferme l'application en fermant cette fenetre
        login.pack();
    }
    
}
