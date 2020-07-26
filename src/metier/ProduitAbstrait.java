package metier;

/**
 *
 * @author Mamy Sitraka
 */
public abstract class ProduitAbstrait { //Cette classe est abstrait, qui ne peut pas être instanciée

    private int id;
    private FamilleProduit famille;
    private ProduitType produit_type;
    private String reference;
    private String designation;
    private double montant_unitaire;
    private String unite;
    private double remise;
    private Devise devise;
    private TVA tva;
    private String toStringType="";
    
    public ProduitAbstrait() {
    }
    
    public ProduitAbstrait(int id, FamilleProduit famille, ProduitType produit_type, String reference, String designation, double montant_unitaire, String unite, double remise, Devise devise, TVA tva) {
        this.id = id;
        this.famille = famille;
        this.produit_type = produit_type;
        this.reference = reference;
        this.designation = designation;
        this.montant_unitaire = montant_unitaire;
        this.unite = unite;
        this.remise = remise;
        this.devise = devise;
        this.tva = tva;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public FamilleProduit getFamille() {
        return famille;
    }
    
    public void setFamille(FamilleProduit famille) {
        this.famille = famille;
    }
    
    public ProduitType getProduit_type() {
        return produit_type;
    }
    
    public void setProduit_type(ProduitType produit_type) {
        this.produit_type = produit_type;
    }
    
    public String getReference() {
        return reference;
    }
    
    public void setReference(String reference) {
        this.reference = reference;
    }
    
    public String getDesignation() {
        return designation;
    }
    
    public void setDesignation(String designation) {
        this.designation = designation;
    }
    
    public double getMontant_unitaire() {
        return montant_unitaire;
    }
    
    public void setMontant_unitaire(double montant_unitaire) {
        this.montant_unitaire = montant_unitaire;
    }
    
    public String getUnite() {
        return unite;
    }
    
    public void setUnite(String unite) {
        this.unite = unite;
    }
    
    public double getRemise() {
        return remise;
    }
    
    public void setRemise(double remise) {
        this.remise = remise;
    }
    
    public Devise getDevise() {
        return devise;
    }
    
    public void setDevise(Devise devise) {
        this.devise = devise;
    }
    
    public TVA getTva() {
        return tva;
    }
    
    public void setTva(TVA tva) {
        this.tva = tva;
    }

    public String getToStringType() {
        return toStringType;
    }

    public void setToStringType(String toStringType) {
        this.toStringType = toStringType;
    }
    
    @Override
    public String toString() {
        if (toStringType.equals("tableau")) {
            return String.valueOf(id);
        } else {
            return "-> " + designation + " - " + reference + " - " + famille;
        }
    }
    
}
