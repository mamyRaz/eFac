
package metier;

/**
 *
 * @author Mamy Sitraka
 */
public class Produit extends ProduitAbstrait{
    private int quantite_stock;

    public Produit() {
        super();
    }

    public Produit(int quantite_stock, int id, FamilleProduit famille, ProduitType produit_type, String reference, String designation, double montant_unitaire, String unite, double remise, Devise devise, TVA tva) {
        super(id, famille, produit_type, reference, designation, montant_unitaire, unite, remise, devise, tva);
        this.quantite_stock = quantite_stock;
    }

    public int getQuantite_stock() {
        return quantite_stock;
    }

    public void setQuantite_stock(int quantite_stock) {
        this.quantite_stock = quantite_stock;
    }

    
    
}
