package metier;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Mamy Sitraka
 */
public class Facture {

    private int id;
    private int numero;
    private String type; //Type de facture: doit ou avoir
    private LocalDate dateFacture;
    private LocalDate dateDelais;
    private double reductionsCommerciales = 0.0;
    private double reductionsFinancieres = 0.0;
    private double netCommerciale = 0.0;
    private double netFinancier = 0.0;
    private double totalHT = 0.0;
    private double totalTVA = 0.0;
    private double totalTTC = 0.0;
    private double acompte = 0.0;
    private double netAPayer = 0.0;
    private String etat = "impayee";
    private ArrayList<LigneFacture> lignes;
    private Entreprise entreprise;
    private Client client;
    private Devise devise;
    private ArrayList<Escompte> escomptes;
    private String remarques;
    private double soldeImpayees = 0.0; //Reste à payer
    private double soldePayees = 0.0; // déjà payée
    private ArrayList<Paiement> paiements;

    //Le dernier numéro de ligne de facture
    private int dernierNumeroLigne = 0;

    public Facture() {
        //Creer une liste de lignes vide à la création de la facture
        lignes = new ArrayList<>();
        escomptes = new ArrayList<>();
    }

    public Facture(int numero, String type) {
        this();
        this.numero = numero;
        this.type = type;
    }

    public Facture(int id, int numero, String type, LocalDate dateFacture, LocalDate dateDelais, String remarques, double soldeImpayees, double soldePayees, double reductionsCommerciales, double netCommerciale, double reductionsFinancieres, double netFinancier, double totalHT, double totalTVA, double totalTTC, double acompte, double netAPayer, String etat) {
        this.id = id;
        this.numero = numero;
        this.type = type;
        this.dateFacture = dateFacture;
        this.dateDelais = dateDelais;
        this.remarques = remarques;
        this.soldeImpayees = soldeImpayees;
        this.soldePayees = soldePayees;
        this.acompte = acompte;
        this.netAPayer = netAPayer;
        this.etat = etat;
        this.netCommerciale = netCommerciale;
        this.reductionsCommerciales = reductionsCommerciales;
        this.reductionsFinancieres = reductionsFinancieres;
        this.netFinancier = netFinancier;
        this.totalHT = totalHT;
        this.totalTVA = totalTVA;
        this.totalTTC = totalTTC;
    }

    public void ajouterLigne(LigneFacture l) {
        lignes.add(l);
    }

    public void supprimerLigne(LigneFacture l) {
        lignes.remove(l);
    }

    public void ajouterEscompte(Escompte e) {
        escomptes.add(e);
    }

    public void ajouterPaiement(Paiement paiement) {
        paiements.add(paiement);
    }

    public void supprimerEscompte(Escompte e) {
        escomptes.remove(e);
    }

    public void incrementerNumeroLigne() {
        dernierNumeroLigne = dernierNumeroLigne + 1;
    }

    public void decrementerNumeroLigne() {
        dernierNumeroLigne = dernierNumeroLigne - 1;
    }

    //Parcourir toutes les lignes de la facture sans se rendre compte de l'escompte commercial
    public double calculerFactureSansEscompte() {
        //Reinitialiser toutes les champs avant calcul de toutes les lignes
        reductionsCommerciales = 0;
        totalHT = 0;
        totalTVA = 0;
        totalTTC = 0;
        netCommerciale = 0;
        lignes.stream().map((ligne) -> {
            if (ligne instanceof LigneProduit) {
                LigneProduit ligneProduit = (LigneProduit) ligne;
                reductionsCommerciales += ligneProduit.getReductionsCommerciales();
                netCommerciale += ligneProduit.getNetCommercial();

            }
            return ligne;
        }).map((ligne) -> {
            totalHT += ligne.getMontantHT();
            return ligne;
        }).map((ligne) -> {
            totalTVA += ligne.getMontantTVA();
            return ligne;
        }).forEachOrdered((ligne) -> {
            totalTTC += ligne.getMontantTTC();
        });

        netAPayer = totalTTC - acompte;
        return netAPayer;
    }

    //Parcourir toutes les lignes de la facture avec l'escompte commercial
    public double calculerFactureAvecEscompte(Escompte escompte) {
        //Reinitialiser toutes les champs avant calcul de toutes les lignes
        reductionsCommerciales = 0;
        netCommerciale = 0;
        reductionsFinancieres = 0;
        totalHT = 0;
        totalTVA = 0;
        totalTTC = 0;

        double reductionsFinancieresLigne = 0, netFinancierLigne = 0, MontantTVALigne = 0, montantHTLigne = 0, totalHTProduit = 0, totalHTExpedition = 0, totalTVAProduit = 0, totalTVAExpedition = 0;
        for (LigneFacture ligne : lignes) {
            if (ligne instanceof LigneProduit) {
                //Pour les produits et services
                LigneProduit ligneProduit = (LigneProduit) ligne;
                reductionsFinancieresLigne = (ligneProduit.getNetCommercial() * ((escompte.getTaux()) / 100));
                netFinancierLigne = (ligneProduit.getNetCommercial()) - reductionsFinancieresLigne;
                montantHTLigne = netFinancierLigne;
                MontantTVALigne = netFinancierLigne * (ligneProduit.getTva() / 100);

                reductionsFinancieres += reductionsFinancieresLigne;
                netFinancier += netFinancierLigne;

                totalHTProduit += montantHTLigne;
                totalTVAProduit += MontantTVALigne;

            } else if (ligne instanceof LigneExpedition) {
                LigneExpedition ligneExpedition = (LigneExpedition) ligne;

                totalHTExpedition += (ligneExpedition.getMontantHT());
                totalTVAExpedition += (ligneExpedition.getMontantTVA());
            }
        }

        totalHT = totalHTProduit + totalHTExpedition;
        totalTVA = totalTVAProduit + totalTVAExpedition;
        totalTTC = totalHT + totalTVA;
        netAPayer = totalTTC - acompte;

        return netAPayer;
    }

    public Escompte getEscomptePaiement() {
        LocalDate datePaiement = LocalDate.now();
        /*
            Escompte pour stocker les dates et les taux exacts du paiement
         */
        Escompte escompteValide = null;
        LocalDate echeanceDate = null;
        for (Escompte escompte : escomptes) {

            /*
                Si la date de paiement correspond à la date échéance,
                on ne cherche plus à chercher le taux correspondant,
                car les autres escomptes sont soient des dates échéances déjà passées auxquelles on ne s'interèsse plus,
                soient des dates à venir mais la date correspondante à la date de paiement est encore plus proche que les dates à venir
             */
            if (datePaiement.isEqual(escompte.getEcheanceDate())) {
                echeanceDate = escompte.getEcheanceDate();
                escompteValide = escompte;
                break;
            }
            //Si la date de paiement est encore avant l'échéance
            if (datePaiement.isBefore(escompte.getEcheanceDate())) {
                if (echeanceDate == null) {
                    echeanceDate = escompte.getEcheanceDate();
                    escompteValide = escompte;
                } else { //si la date de l'escompte en cours est encore avant la date memorisée
                    if (escompte.getEcheanceDate().isBefore(echeanceDate)) {
                        echeanceDate = escompte.getEcheanceDate();
                        escompteValide = escompte;
                    }
                }
            }
        }
        return escompteValide;
    }

    public int getId() {
        return id;
    }

    public int getNumero() {
        return numero;
    }

    public LocalDate getDateFacture() {
        return dateFacture;
    }

    public LocalDate getDateDelais() {
        return dateDelais;
    }

    public double getTotalHT() {
        return totalHT;
    }

    public double getTotalTVA() {
        return totalTVA;
    }

    public double getTotalTTC() {
        return totalTTC;
    }

    public double getAcompte() {
        return acompte;
    }

    public double getNetAPayer() {
        return netAPayer;
    }

    public String getEtat() {
        return etat;
    }

    public ArrayList<LigneFacture> getLignes() {
        return lignes;
    }

    public String getType() {
        return type;
    }

    public Entreprise getEntreprise() {
        return entreprise;
    }

    public Client getClient() {
        return client;
    }

    public Devise getDevise() {
        return devise;
    }

    public double getNetCommerciale() {
        return netCommerciale;
    }

    public double getNetFinancier() {
        return netFinancier;
    }

    public void setDernierNumeroLigne(int dernierNumeroLigne) {
        this.dernierNumeroLigne = dernierNumeroLigne;
    }

    public int getDernierNumeroLigne() {
        return dernierNumeroLigne;
    }

    public ArrayList<Paiement> getPaiements() {
        return paiements;
    }

    public void setPaiements(ArrayList<Paiement> paiements) {
        this.paiements = paiements;
    }

    public double getSoldeImpayees() {
        return soldeImpayees;
    }

    public void setSoldeImpayees(double soldeImpayees) {
        this.soldeImpayees = soldeImpayees;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setDateFacture(LocalDate dateFacture) {
        this.dateFacture = dateFacture;
    }

    public void setDateDelais(LocalDate dateDelais) {
        this.dateDelais = dateDelais;
    }

    public void setAcompte(double acompte) {
        this.acompte = acompte;
    }

    public void setNetAPayer(double netAPayer) {
        this.netAPayer = netAPayer;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setLignes(ArrayList<LigneFacture> lignes) {
        this.lignes = lignes;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setEntreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setDevise(Devise devise) {
        this.devise = devise;
    }

    public String getRemarques() {
        return remarques;
    }

    public void setRemarques(String remarques) {
        this.remarques = remarques;
    }

    public ArrayList<Escompte> getEscomptes() {
        return escomptes;
    }

    public void setEscomptes(ArrayList<Escompte> escomptes) {
        this.escomptes = escomptes;
    }

    public double getReductionsCommerciales() {
        return reductionsCommerciales;
    }

    public void setReductionsCommerciales(double reductionsCommerciales) {
        this.reductionsCommerciales = reductionsCommerciales;
    }

    public double getReductionsFinancieres() {
        return reductionsFinancieres;
    }

    public void setReductionsFinancieres(double reductionsFinancieres) {
        this.reductionsFinancieres = reductionsFinancieres;
    }

    public double getSoldePayees() {
        return soldePayees;
    }

    public void setSoldePayees(double soldePayees) {
        this.soldePayees = soldePayees;
    }

    @Override
    public String toString() {
        return "F" + numero;
    }

}
