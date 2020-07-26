
package metier;

/**
 *
 * @author Mamy Sitraka
 */
public class Rabais {
    
    private int id;
    private double taux;
    private String commentaires;

    public Rabais() {
    }

    public Rabais(double taux, String commentaires) {
        this.taux = taux;
        this.commentaires = commentaires;
    }

    public Rabais(int id, double taux, String commentaires) {
        this.id = id;
        this.taux = taux;
        this.commentaires = commentaires;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTaux() {
        return taux;
    }

    public void setTaux(double taux) {
        this.taux = taux;
    }

    public String getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(String commentaires) {
        this.commentaires = commentaires;
    }

    @Override
    public String toString() {
        return String.valueOf(taux);
    }
}
