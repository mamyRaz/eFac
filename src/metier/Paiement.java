
package metier;

import java.time.LocalDate;

/**
 *
 * @author Mamy Sitraka
 */
public class Paiement {
    private int id;
    private Escompte escompte; //Il seul escompte pour un paiement, un taux
    private LocalDate datePaiement;
    private String modePaiement;
    private double montantPaiement;
    private Facture facture;

    public Paiement() {
    }
    
    public Paiement(int id, LocalDate datePaiement, String modePaiement, double montantPaiement) {
        this.id = id;
        this.datePaiement = datePaiement;
        this.modePaiement = modePaiement;
        this.montantPaiement = montantPaiement;
    }

    public Paiement(int id, Escompte escompte, LocalDate datePaiement, String modePaiement, double montantPaiement) {
        this(id, datePaiement, modePaiement, montantPaiement);
        this.escompte = escompte;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Escompte getEscompte() {
        return escompte;
    }

    public void setEscompte(Escompte escompte) {
        this.escompte = escompte;
    }

    public LocalDate getDatePaiement() {
        return datePaiement;
    }

    public void setDatePaiement(LocalDate datePaiement) {
        this.datePaiement = datePaiement;
    }

    public String getModePaiement() {
        return modePaiement;
    }

    public void setModePaiement(String modePaiement) {
        this.modePaiement = modePaiement;
    }

    public double getMontantPaiement() {
        return montantPaiement;
    }

    public void setMontantPaiement(double montantPaiement) {
        this.montantPaiement = montantPaiement;
    }

    public Facture getFacture() {
        return facture;
    }

    public void setFacture(Facture facture) {
        this.facture = facture;
    }
        
}
