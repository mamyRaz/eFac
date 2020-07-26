package metier;

/**
 *
 * @author Mamy Sitraka
 */
public class Client {

    private int id;
    private ClientType clientType;
    private String nom;
    private String prenom;
    private String adresse;
    private String telephone;
    private String email;
    private String toStringType="";
    private String SIRET="";

    public Client() {
    }

    public Client(int id, ClientType clientType, String nom, String prenom, String adresse, String telephone, String email, String SIRET) {
        this.id = id;
        this.clientType = clientType;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
        this.SIRET = SIRET;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ClientType getClientType() {
        return clientType;
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
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

    public String getToStringType() {
        return toStringType;
    }

    public void setToStringType(String toStringType) {
        this.toStringType = toStringType;
    }

    public String getSIRET() {
        return SIRET;
    }

    public void setSIRET(String SIRET) {
        this.SIRET = SIRET;
    }

    @Override
    public String toString() {
        if (toStringType.equals("tableau")) {
            return String.valueOf(id);
        } else {
            return nom + " " + prenom + " - " + clientType.getDesignation_type();
        }
    }

}
