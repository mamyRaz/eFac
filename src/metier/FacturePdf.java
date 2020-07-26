package metier;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import javax.swing.JFileChooser;

/**
 *
 * @author Mamy Sitraka
 */
public class FacturePdf {

    private final SimpleDateFormat affichageDateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public void creer(Facture facture) throws FileNotFoundException, DocumentException {

        String chemin = "";
        JFileChooser fileJooser = new JFileChooser();
        fileJooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int res = fileJooser.showSaveDialog(null);

        if (res == JFileChooser.APPROVE_OPTION) {
            chemin = fileJooser.getSelectedFile().getPath();

        }
        Document document = new Document(PageSize.A4);

        PdfWriter.getInstance(document, new FileOutputStream(chemin + "Facture_F" + facture.getNumero() + ".pdf"));
        document.open();

        //Font 
        Font fontTitre = new Font(Font.FontFamily.HELVETICA, 13, Font.BOLD);
        Font fontDate = new Font(Font.FontFamily.HELVETICA, 5, Font.BOLD);
        Font fontTitreTable = new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD);
        Font fontTable = new Font(Font.FontFamily.HELVETICA, 8);

        //Titre de la facture
        Paragraph paragrapheTitre = new Paragraph("Facture " + facture.getType());
        paragrapheTitre.setAlignment(Element.ALIGN_CENTER);
        paragrapheTitre.setFont(fontTitre);
        paragrapheTitre.setSpacingAfter(10);
        document.add(paragrapheTitre);

        //Les informations concernant l'entreprise
        Paragraph paragrapheNomEntreprise = new Paragraph(facture.getEntreprise().getNom());
        paragrapheNomEntreprise.setFont(fontTable);
        document.add(paragrapheNomEntreprise);

        Paragraph paragrapheAdresseEntreprise = new Paragraph(facture.getEntreprise().getAdresse());
        paragrapheAdresseEntreprise.setFont(fontTable);
        document.add(paragrapheAdresseEntreprise);

        Paragraph paragrapheRaisonSocialEntreprise = new Paragraph("NI: "+facture.getEntreprise().getNif());
        paragrapheRaisonSocialEntreprise.setFont(fontTable);
        document.add(paragrapheRaisonSocialEntreprise);

        Paragraph paragrapheCapitalSocialEntreprise = new Paragraph("SIRET: "+facture.getEntreprise().getStat());
        paragrapheCapitalSocialEntreprise.setFont(fontTable);
        document.add(paragrapheCapitalSocialEntreprise);

        Paragraph paragrapheTelephoneEntreprise = new Paragraph(facture.getEntreprise().getTelephone());
        paragrapheTelephoneEntreprise.setFont(fontTable);
        document.add(paragrapheTelephoneEntreprise);

        Paragraph paragrapheEmailEntreprise = new Paragraph(facture.getEntreprise().getEmail());
        paragrapheEmailEntreprise.setFont(fontTable);
        document.add(paragrapheEmailEntreprise);

        Paragraph paragrapheTitreClient = new Paragraph();
        Font fontBold = new Font(Font.FontFamily.HELVETICA, Font.BOLD, Font.UNDERLINE);
        paragrapheTitreClient.add("CLIENT :");
        paragrapheTitreClient.setFont(fontBold);
        paragrapheTitreClient.setAlignment(Element.ALIGN_RIGHT);
        document.add(paragrapheTitreClient);

        //Les informations concernant le client
        Paragraph paragraphClient = new Paragraph(facture.getClient().getNom() + facture.getClient().getPrenom());
        paragraphClient.setFont(fontTable);
        paragraphClient.setAlignment(Element.ALIGN_RIGHT);
        document.add(paragraphClient);
        
        Paragraph paragrapheAdresseClient = new Paragraph(facture.getClient().getAdresse());
        paragrapheAdresseClient.setFont(fontTable);
        paragrapheAdresseClient.setAlignment(Element.ALIGN_RIGHT);
        document.add(paragrapheAdresseClient);

        Paragraph paragrapheTelephoneClient = new Paragraph(facture.getClient().getTelephone());
        paragrapheTelephoneClient.setFont(fontTable);
        paragrapheTelephoneClient.setAlignment(Element.ALIGN_RIGHT);
        document.add(paragrapheTelephoneClient);

        Paragraph paragrapheEmailClient = new Paragraph(facture.getClient().getEmail());
        paragrapheEmailClient.setFont(fontTable);
        paragrapheEmailClient.setAlignment(Element.ALIGN_RIGHT);
        document.add(paragrapheEmailClient);

        if (facture.getClient().getClientType().getDesignation_type().equals("entreprise")) {
            Paragraph paragrapheSIRETClient = new Paragraph();
            paragrapheSIRETClient.setFont(fontTable);
            paragrapheSIRETClient.setAlignment(Element.ALIGN_RIGHT);
            paragrapheSIRETClient.add(String.valueOf(facture.getClient().getSIRET()));
            document.add(paragrapheSIRETClient);
        }
        

        Paragraph paragrapheDate = new Paragraph("Facture F" + facture.getNumero() + " du " + affichageDateFormat.format(Date.valueOf(facture.getDateFacture())) + " / Date d'échéance: " + affichageDateFormat.format(Date.valueOf(facture.getDateDelais())));
        paragrapheDate.setFont(fontDate);
        paragrapheDate.setAlignment(Element.ALIGN_CENTER);
        paragrapheDate.setSpacingBefore(15);
        document.add(paragrapheDate);

        PdfPTable table = new PdfPTable(8);
        table.setWidthPercentage(100);
        table.setSpacingBefore(12);
        table.setSpacingAfter(12);

        PdfPCell cellDesignation = new PdfPCell();
        Paragraph designation = new Paragraph("Designation");
        designation.setFont(fontTitreTable);
        cellDesignation.addElement(designation);

        PdfPCell cellQuantite = new PdfPCell();
        Paragraph quantite = new Paragraph("Quantité");
        quantite.setFont(fontTitreTable);
        cellQuantite.addElement(quantite);

        PdfPCell cellPU = new PdfPCell();
        Paragraph PU = new Paragraph("Prix Unitaire HT");
        PU.setFont(fontTitreTable);
        cellPU.addElement(PU);

        PdfPCell cellRabais = new PdfPCell();
        Paragraph rabais = new Paragraph("Rabais");
        rabais.setFont(fontTitreTable);
        cellRabais.addElement(rabais);

        PdfPCell cellRemise = new PdfPCell();
        Paragraph remise = new Paragraph("Remise");
        remise.setFont(fontTitreTable);
        cellRemise.addElement(remise);

        PdfPCell cellRistourne = new PdfPCell();
        Paragraph ristourne = new Paragraph("Ristourne");
        ristourne.setFont(fontTitreTable);
        cellRistourne.addElement(ristourne);

        PdfPCell cellTVA = new PdfPCell();
        Paragraph tva = new Paragraph("TVA");
        tva.setFont(fontTitreTable);
        cellTVA.addElement(tva);

        PdfPCell cellMontantHT = new PdfPCell();
        Paragraph montantHT = new Paragraph("Montant HT");
        montantHT.setFont(fontTitreTable);
        cellMontantHT.addElement(montantHT);

        table.addCell(cellDesignation);
        table.addCell(cellQuantite);
        table.addCell(cellPU);
        table.addCell(cellRabais);
        table.addCell(cellRemise);
        table.addCell(cellRistourne);
        table.addCell(cellTVA);
        table.addCell(cellMontantHT);

        for (LigneFacture ligne : facture.getLignes()) {
            System.out.println(ligne.getDesignation());
            cellDesignation = new PdfPCell();
            designation = new Paragraph(ligne.getDesignation());
            designation.setFont(fontTable);
            cellDesignation.addElement(designation);
            table.addCell(cellDesignation);

            if (ligne instanceof LigneProduit) {
                LigneProduit ligneProduit = (LigneProduit) ligne;

                cellQuantite = new PdfPCell();
                quantite = new Paragraph(ligne.getQuantite() + " " + ligneProduit.getProduit().getUnite());
                quantite.setFont(fontTable);
                cellQuantite.addElement(quantite);
                table.addCell(cellQuantite);
            } else {

                cellQuantite = new PdfPCell();
                quantite = new Paragraph(String.valueOf(ligne.getQuantite()));
                quantite.setFont(fontTable);
                cellQuantite.addElement(quantite);
                table.addCell(cellQuantite);
            }

            cellPU = new PdfPCell();
            PU = new Paragraph(String.valueOf(ligne.getMontantUnitaire()));
            PU.setFont(fontTable);
            cellPU.addElement(PU);
            table.addCell(cellPU);

            if (ligne instanceof LigneProduit) {
                LigneProduit ligneProduit = (LigneProduit) ligne;

                cellRabais = new PdfPCell();
                rabais = new Paragraph(ligneProduit.getRabais() + " %");
                rabais.setFont(fontTable);
                cellRabais.addElement(rabais);
                table.addCell(cellRabais);

                cellRemise = new PdfPCell();
                remise = new Paragraph(ligneProduit.getRemise() + " %");
                remise.setFont(fontTable);
                cellRemise.addElement(remise);
                table.addCell(cellRemise);

                cellRistourne = new PdfPCell();
                ristourne = new Paragraph(ligneProduit.getRistourne() + " %");
                ristourne.setFont(fontTable);
                cellRistourne.addElement(ristourne);
                table.addCell(cellRistourne);
            } else {

                cellRabais = new PdfPCell();
                rabais = new Paragraph("0 %");
                rabais.setFont(fontTable);
                cellRabais.addElement(rabais);
                table.addCell(cellRabais);
                table.addCell(cellRabais);

                cellRemise = new PdfPCell();
                remise = new Paragraph("0 %");
                remise.setFont(fontTable);
                cellRemise.addElement(remise);
                table.addCell(cellRemise);
                table.addCell(cellRemise);

                cellRistourne = new PdfPCell();
                ristourne = new Paragraph("0 %");
                ristourne.setFont(fontTable);
                cellRistourne.addElement(ristourne);
                table.addCell(cellRistourne);
                table.addCell(cellRistourne);
            }

            cellTVA = new PdfPCell();
            tva = new Paragraph(ligne.getTva() + " %");
            tva.setFont(fontTable);
            cellTVA.addElement(tva);
            table.addCell(cellTVA);

            cellMontantHT = new PdfPCell();
            montantHT = new Paragraph(String.valueOf(ligne.getMontantHT()));
            montantHT.setFont(fontTable);
            cellMontantHT.addElement(montantHT);
            table.addCell(cellMontantHT);
        }
         document.add(table);

        Paragraph paragrapheTotalHT = new Paragraph("Montant total HT : " + (new BigDecimal(facture.getTotalHT()).setScale(2, BigDecimal.ROUND_UP)) + " €");
        paragrapheTotalHT.setFont(fontTable);
        paragrapheTotalHT.setAlignment(Element.ALIGN_RIGHT);
        document.add(paragrapheTotalHT);

        Paragraph paragrapheReductionsCommerciales = new Paragraph("Réductions commerciales : " + (new BigDecimal(facture.getReductionsCommerciales()).setScale(2, BigDecimal.ROUND_UP)) + " €");
        paragrapheReductionsCommerciales.setFont(fontTable);
        paragrapheReductionsCommerciales.setAlignment(Element.ALIGN_RIGHT);
        document.add(paragrapheReductionsCommerciales);

        Paragraph paragrapheReductionsFinancieres = new Paragraph("Réductions financières : " + (new BigDecimal(facture.getReductionsFinancieres()).setScale(2, BigDecimal.ROUND_UP)) + " €");
        paragrapheReductionsFinancieres.setFont(fontTable);
        paragrapheReductionsFinancieres.setAlignment(Element.ALIGN_RIGHT);
        document.add(paragrapheReductionsFinancieres);

        Paragraph paragrapheTotalTVA = new Paragraph("Total taxes : " + (new BigDecimal(facture.getTotalTVA()).setScale(2, BigDecimal.ROUND_UP)) + " €");
        paragrapheTotalTVA.setFont(fontTable);
        paragrapheTotalTVA.setAlignment(Element.ALIGN_RIGHT);
        document.add(paragrapheTotalTVA);

        Paragraph paragrapheTotalTTC = new Paragraph("Total TTC : " + (new BigDecimal(facture.getTotalTTC()).setScale(2, BigDecimal.ROUND_UP)) + " €");
        paragrapheTotalTTC.setFont(fontTable);
        paragrapheTotalTTC.setAlignment(Element.ALIGN_RIGHT);
        document.add(paragrapheTotalTTC);

        Paragraph paragrapheAcompte = new Paragraph("Acompte : " + (new BigDecimal(facture.getAcompte()).setScale(2, BigDecimal.ROUND_UP)) + " €");
        paragrapheAcompte.setFont(fontTable);
        paragrapheAcompte.setAlignment(Element.ALIGN_RIGHT);
        document.add(paragrapheAcompte);

        Paragraph paragrapheNetAPayer = new Paragraph("Net à payer : " + (new BigDecimal(facture.getNetAPayer()).setScale(2, BigDecimal.ROUND_UP)) + " €");
        paragrapheNetAPayer.setFont(fontTable);
        paragrapheNetAPayer.setAlignment(Element.ALIGN_RIGHT);
        document.add(paragrapheNetAPayer);
       
        document.close();
    }

}
