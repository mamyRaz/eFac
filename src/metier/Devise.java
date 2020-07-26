
package metier;

/**
 *
 * @author Mamy Sitraka
 */
public class Devise {
    private int id;
    private String description;
    private String symbole;

    public Devise() {
    }

    public Devise(int id, String description, String symbole) {
        this.id = id;
        this.description = description;
        this.symbole = symbole;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSymbole() {
        return symbole;
    }

    public void setSymbole(String symbole) {
        this.symbole = symbole;
    }

    @Override
    public String toString() {
        return symbole;
    }
    
    
}
