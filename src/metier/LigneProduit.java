package metier;

/**
 *
 * @author Mamy Sitraka
 */

/*
    Cette classe est utilisée pour une ligne de type produit ou service ou autre
*/
public class LigneProduit extends LigneFacture {

    private double rabais = 0.0; //taux
    private double montantRabais = 0.0;
    private double remise = 0.0; //taux
    private double montantRemise = 0.0;
    private double ristourne = 0.0; //taux
    private double montantRistourne = 0.0;
    private double reductionsCommerciales = 0.0;
    private double netCommercial = 0.0;
    private ProduitAbstrait produit; //Tout ce qui concerne le produit ou service

    public LigneProduit() {
        super();
    }

    //Pour les produits et services
    public LigneProduit(int numero, String ligneType, int quantite, double montantUnitaire, double tva, ProduitAbstrait produit, double rabais, double remise, double ristourne) {
        super(numero, ligneType, produit.getReference() + " " + produit.getDesignation(), quantite, montantUnitaire, tva);
        this.produit = produit;
        this.rabais = rabais;
        this.remise = remise;
        this.ristourne = ristourne;
    }

    //Pour le type autre qui n'instancie pas la classe Produit ou service
    public LigneProduit(int numero, String ligneType, String designation, int quantite, double montantUnitaire, double tva, double rabais, double remise, double ristourne) {
        super(numero, ligneType, designation, quantite, montantUnitaire, tva);
        this.rabais = rabais;
        this.remise = remise;
        this.ristourne = ristourne;
    }

    @Override
    public double calculerLigne() {
        /*
            Concernant les escomptes, c'est au paiement qu'on évalue les escomptes.
            Par conséquent, l'évalation n'as pas lieu ici mais dans la facture
            on l'affecte juste à la ligne de produit
         */

        montantRabais = (quantite * montantUnitaire) * (rabais / 100);

        netCommercial = (quantite * montantUnitaire) - montantRabais;

        montantRemise = netCommercial * (remise / 100);

        netCommercial = netCommercial - montantRemise;

        montantRistourne = netCommercial * (ristourne / 100);

        netCommercial = netCommercial - montantRistourne;

        /*
            Le net financier n'est pas encore calculé car l'escompte se fait au paiement
            Donc, le montant hors taxes est égal au net commercial et est égal qu total reduction
         */
        reductionsCommerciales = montantRabais + montantRemise + montantRistourne;

        montantHT = netCommercial;
        montantTVA = montantHT * (tva / 100);

        montantTTC = montantHT + montantTVA;

        return montantTTC;
    }

    public double getRabais() {
        return rabais;
    }

    public void setRabais(double rabais) {
        this.rabais = rabais;
    }

    public double getMontantRabais() {
        return montantRabais;
    }

    public void setMontantRabais(double montantRabais) {
        this.montantRabais = montantRabais;
    }

    public double getRemise() {
        return remise;
    }

    public void setRemise(double remise) {
        this.remise = remise;
    }

    public double getMontantRemise() {
        return montantRemise;
    }

    public void setMontantRemise(double montantRemise) {
        this.montantRemise = montantRemise;
    }

    public double getRistourne() {
        return ristourne;
    }

    public void setRistourne(double ristourne) {
        this.ristourne = ristourne;
    }

    public double getMontantRistourne() {
        return montantRistourne;
    }

    public void setMontantRistourne(double montantRistourne) {
        this.montantRistourne = montantRistourne;
    }
    
    public double getReductionsCommerciales() {
        return reductionsCommerciales;
    }

    public void setReductionsCommerciales(double reductionsCommerciales) {
        this.reductionsCommerciales = reductionsCommerciales;
    }

    public double getNetCommercial() {
        return netCommercial;
    }

    public void setNetCommercial(double netCommercial) {
        this.netCommercial = netCommercial;
    }

    public ProduitAbstrait getProduit() {
        return produit;
    }

    public void setProduit(ProduitAbstrait produit) {
        this.produit = produit;
    }
    /*
    public String toStringEscompte() {
        String string = "";
        string = escomptes.stream().map((escompte) -> escompte.getEcheance() + "j -> " + escompte.getTaux() + "%\n").reduce(string, String::concat);
        return string;
    }*/
}
