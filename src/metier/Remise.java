
package metier;

/**
 *
 * @author Mamy Sitraka
 */
public class Remise {
    private int id;
    private int nombreProduits;
    private double tauxRemise;

    public Remise() {
    }

    public Remise(int id, int nombreProduits, double tauxRemise) {
        this.id = id;
        this.nombreProduits = nombreProduits;
        this.tauxRemise = tauxRemise;
    }

    public Remise(int nombreProduits, double tauxRemise) {
        this.nombreProduits = nombreProduits;
        this.tauxRemise = tauxRemise;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNombreProduits() {
        return nombreProduits;
    }

    public void setNombreProduits(int nombreProduits) {
        this.nombreProduits = nombreProduits;
    }

    public double getTauxRemise() {
        return tauxRemise;
    }

    public void setTauxRemise(double tauxRemise) {
        this.tauxRemise = tauxRemise;
    }
}
