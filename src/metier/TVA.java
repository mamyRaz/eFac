
package metier;

/**
 *
 * @author Mamy Sitraka
 */
public class TVA {
    private int id;
    private double taux=20.0;
    private String description;

    public TVA() {
    }

    public TVA(int id, double taux, String description) {
        this.id = id;
        this.taux = taux;
        this.description = description;
    }

    public double getTaux() {
        return taux;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTaux(double taux) {
        this.taux = taux;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return String.valueOf(taux);
    }
}
