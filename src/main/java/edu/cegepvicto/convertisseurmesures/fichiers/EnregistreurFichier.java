package edu.cegepvicto.convertisseurmesures.fichiers;

import edu.cegepvicto.convertisseurmesures.convertisseur.TypeMesure;
import edu.cegepvicto.convertisseurmesures.convertisseur.UniteMesure;

import java.io.IOException;
import java.nio.file.*;

/**
 * Enregistre une entrée de conversion dans un fichier. Le fichier est précisé à la création.
 */
public class EnregistreurFichier {

    /**
     * Chemin d'accès pour le fichier
     */
    private Path cheminFichier;

    /**
     * Crée un nouvel enregistreur de fichier de conversion à l'emplacement spécifié.
     *
     * @param nomFichier le nom du fichier géré par cet enregistreur
     * @param formatFichier le format du fichier, doit inclure le .
     */
    public EnregistreurFichier(String nomFichier, String formatFichier)
    {
        cheminFichier = Paths.get(nomFichier + formatFichier);
    }

    /**
     * Enregistre une conversion réalisée dans le système.
     *
     * @param typeMesure le type de mesure qui intervient la conversion
     * @param uniteMesureOriginale l'unité de mesure originale
     * @param uniteConvertie l'unité vers laquelle la conversion a eu lieu
     * @param valeurOriginale la valeur originale
     * @param valeurConvertie la valeur après la conversion
     * @throws IOException si une exception dans la manipulation des fichiers survient
     */
    public void enregistrer(TypeMesure typeMesure, UniteMesure uniteMesureOriginale,
                            UniteMesure uniteConvertie, double valeurOriginale, double valeurConvertie) throws IOException
    {
        String chaineInfo = "Conversion de " + typeMesure.getNom() + " [" + uniteMesureOriginale.getNom() +
                "] -> [" + uniteConvertie.getNom() + "] : " + valeurOriginale + " -> " + valeurConvertie + "\n";

        Files.writeString(cheminFichier, chaineInfo, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }
}
