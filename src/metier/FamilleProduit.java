
package metier;

/**
 *
 * @author Mamy Sitraka
 */
public class FamilleProduit {
    private int id;
    private String designation;

    public FamilleProduit(int id, String designation) {
        this.id = id;
        this.designation = designation;
    }

    public FamilleProduit() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    @Override
    public String toString() {
        return designation;
    }
}
