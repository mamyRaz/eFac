
package metier;

/**
 *
 * @author Mamy Sitraka
 */
public class ClientType {
    private int id;
    private String designation_type;

    public ClientType() {
    }

    public ClientType(int id, String designation_type) {
        this.id = id;
        this.designation_type = designation_type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesignation_type() {
        return designation_type;
    }

    public void setDesignation_type(String designation_type) {
        this.designation_type = designation_type;
    }

    @Override
    public String toString() {
        return designation_type;
    }
    
}
