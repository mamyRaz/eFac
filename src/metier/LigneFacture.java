package metier;


/**
 *
 * @author Mamy Sitraka
 */
public abstract class LigneFacture { //on ne peut pas instancier cette classe. Seules les classes derivees(Produits, Service,...) sont instanciables
    //On met protected pour que les classes dérivées puisssent les utiliser
    protected int id;
    protected Facture facture;
    protected String ligneType;
    protected int numero;
    protected String designation;
    protected double montantUnitaire = 0.0;
    protected int quantite = 1;
    /*
        On déclare double car on récupère le tva depuis le fomulaire 
        mais pas depuis l'objet Produit ou Service.
        Pour l'objet LigneFacture, on a juste besoin du taux saisi sur la champ TVA
     */
    protected double tva;
    protected double montantTVA = 0.0;
    protected double montantHT = 0.0;
    protected double montantTTC = 0.0;
    
    /*
        Calculer  tous les attributs de la ligne (montantHT, montantTVA, montantTTC,...)
        Le calcul de la ligne est différent selon le type de produit (produit, service, reduction)
        Chaque type de produit doit implémenter cette méthode
     */
    public abstract double calculerLigne();

    public LigneFacture() {
    }

    public LigneFacture(int numero, String ligneType, String designation, int quantite, double montantUnitaire, double tva) {
        this.numero = numero;
        this.ligneType = ligneType;
        this.designation = designation;
        this.quantite = quantite;
        this.montantUnitaire = montantUnitaire;
        this.tva = tva;
    }

    public String getLigneType() {
        return ligneType;
    }

    public void setLigneType(String ligneType) {
        this.ligneType = ligneType;
    }
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getMontantUnitaire() {
        return montantUnitaire;
    }

    public void setMontantUnitaire(double montantUnitaire) {
        this.montantUnitaire = montantUnitaire;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getTva() {
        return tva;
    }

    public void setTva(double tva) {
        this.tva = tva;
    }

    public double getMontantTVA() {
        return  montantTVA;
    }

    public void setMontantTVA(double montantTVA) {
        this.montantTVA = montantTVA;
    }

    public double getMontantHT() {
        return montantHT;
    }

    public void setMontantHT(double montantHT) {
        this.montantHT = montantHT;
    }

    public double getMontantTTC() {
        return montantTTC;
    }

    public void setMontantTTC(double montantTTC) {
        this.montantTTC = montantTTC;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Facture getFacture() {
        return facture;
    }

    public void setFacture(Facture facture) {
        this.facture = facture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    @Override
    public String toString() {
        return numero + " - " + ligneType;
    }
}
