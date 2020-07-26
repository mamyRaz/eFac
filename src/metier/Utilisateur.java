
package metier;

/**
 *
 * @author Mamy Sitraka
 */
public class Utilisateur {
    private int id;
    private String nom;
    private String prenom;
    private Profil profil;
    private String login;
    private String password;

    public Utilisateur() {
    }
    
    public Utilisateur(int id, String nom, String prenom, Profil profil, String login) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.profil = profil;
        this.login = login;
    }
    
    public Utilisateur(int id, String nom, String prenom, Profil profil, String login, String password) {
        this(id, nom, prenom, profil, login);
        this.password = password;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getLogin() {
        return login;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Profil getProfil() {
        return profil;
    }

    public void setProfil(Profil profil) {
        this.profil = profil;
    }
        
}
