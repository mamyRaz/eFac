package metier;

/**
 *
 * @author Mamy Sitraka
 */

/*
    Ce type de ligne concerne les autres frais comme la livraison, l'emballage...
*/
public class LigneExpedition extends LigneFacture {
    
    public LigneExpedition() {
        super();
    }
    
    public LigneExpedition(int numero, String ligneType, String designation, int quantite, double montantUnitaire, double tva) {
        super(numero, ligneType, designation, quantite, montantUnitaire, tva);
    }

    @Override
    public double calculerLigne() {

        montantHT = quantite * montantUnitaire;
        montantTVA = montantHT * (tva / 100);

        montantTTC = montantHT + montantTVA;

        return montantTTC;
    }

}
