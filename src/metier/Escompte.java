
package metier;

import java.time.LocalDate;

/**
 *
 * @author Mamy Sitraka
 */
public class Escompte {
    private int id;
    private int echeance;
    private LocalDate echeanceDate;
    private double taux;

    public Escompte() {
    }

    public Escompte(int echeance, LocalDate echeance_date, double taux) {
        this();
        this.echeance = echeance;
        this.echeanceDate = echeance_date;
        this.taux = taux;
    }
    
    public Escompte(int id, int echeance, LocalDate echeance_date, double taux) {
        this(echeance, echeance_date, taux);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEcheance() {
        return echeance;
    }

    public void setEcheance(int echeance) {
        this.echeance = echeance;
    }

    public double getTaux() {
        return taux;
    }

    public void setTaux(double taux) {
        this.taux = taux;
    }

    public LocalDate getEcheanceDate() {
        return echeanceDate;
    }

    public void setEcheanceDate(LocalDate echeanceDate) {
        this.echeanceDate = echeanceDate;
    }
}
