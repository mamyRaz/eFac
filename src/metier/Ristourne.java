
package metier;

/**
 *
 * @author Mamy Sitraka
 */
public class Ristourne {

    private int id;
    private int nombreCommandes;
    private double tauxRistourne;
    
    public Ristourne() {
    }

    public Ristourne(int id, int nombreCommandes, double tauxRistourne) {
        this.id = id;
        this.nombreCommandes = nombreCommandes;
        this.tauxRistourne = tauxRistourne;
    }

    public Ristourne(int nombreCommandes, double tauxRistourne) {
        this.nombreCommandes = nombreCommandes;
        this.tauxRistourne = tauxRistourne;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNombreCommandes() {
        return nombreCommandes;
    }

    public void setNombreCommandes(int nombreCommandes) {
        this.nombreCommandes = nombreCommandes;
    }

    public double getTauxRistourne() {
        return tauxRistourne;
    }

    public void setTauxRistourne(double tauxRistourne) {
        this.tauxRistourne = tauxRistourne;
    }
    
}
