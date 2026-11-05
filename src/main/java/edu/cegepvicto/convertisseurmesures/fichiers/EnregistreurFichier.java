package edu.cegepvicto.convertisseurmesures.fichiers;

import edu.cegepvicto.convertisseurmesures.convertisseur.TypeMesure;
import edu.cegepvicto.convertisseurmesures.convertisseur.UniteMesure;

import java.nio.file.*;

/**
 * Enregistre une entrée de conversion dans un fichier
 */
public class EnregistreurFichier {

    /**
     * Le nom du fichier dans lequel l'enregistrement se fait
     */
    private String nomFichier;

    /**
     * L'extension du fichier où se fait la sauvegarde
     */
    private String formatFichier;

    /**
     * Chemin d'accès pour le fichier
     */
    private Path cheminFichier;

    /**
     * Crée un nouvel enregistreur de fichier de conversion à l'emplacement spécifié
     * @param nomFichier
     * @param formatFichier
     */
    public EnregistreurFichier(String nomFichier, String formatFichier)
    {
        this.nomFichier = nomFichier;
        this.formatFichier = formatFichier;

        cheminFichier = Paths.get(nomFichier + formatFichier);
    }

    public void Enregistrer(TypeMesure typeMesure, UniteMesure uniteMesureOriginale,
                            UniteMesure uniteConvertie, double valeurOriginale, double valeurConvertie)
    {
        String chaineInfo = "Conversion de " + typeMesure.getNom() + "[" + uniteMesureOriginale.getNom() +
                "] -> [" + uniteConvertie.getNom() + "] : " + valeurOriginale + " -> " + valeurConvertie + "\n";

        Files.write(cheminFichier, chaineInfo, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }
}
