
package metier;

/**
 *
 * @author Mamy Sitraka
 */
public class Service extends ProduitAbstrait{

    public Service() {
        super();
    }

    public Service(int id, FamilleProduit famille, ProduitType produit_type, String reference, String designation, double montant_unitaire, String unite, double remise, Devise devise, TVA tva) {
        super(id, famille, produit_type, reference, designation, montant_unitaire, unite, remise, devise, tva);
    }
}
