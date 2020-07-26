
package metier;

/**
 *
 * @author Mamy Sitraka
 */
public class Profil {
    
    private int id;
    private String designation;
    private String commentaire;

    public Profil(int id, String designation, String commentaire) {
        this.id = id;
        this.designation = designation;
        this.commentaire = commentaire;
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

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    @Override
    public String toString() {
        return designation;
    }
}
