
package metier;

/**
 *
 * @author Mamy Sitraka
 */
public class Entreprise {
    private int id;
    private String nom;
    private int nif;
    private int stat;
    private double capital_social;
    private String adresse;
    private String telephone;
    private String email;

    public Entreprise() {
    }

    public Entreprise(int id, String nom, int nif, int stat, double capital_social, String adresse, String telephone, String email) {
        this.id = id;
        this.nom = nom;
        this.nif = nif;
        this.stat = stat;
        this.capital_social = capital_social;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNif() {
        return nif;
    }

    public void setNif(int nif) {
        this.nif = nif;
    }

    public int getStat() {
        return stat;
    }

    public void setStat(int stat) {
        this.stat = stat;
    }

    public double getCapital_social() {
        return capital_social;
    }

    public void setCapital_social(double capital_social) {
        this.capital_social = capital_social;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return nom;
    }
    
}
