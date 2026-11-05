package edu.cegepvicto.convertisseurmesures;

import edu.cegepvicto.convertisseurmesures.convertisseur.Convertisseur;
import edu.cegepvicto.convertisseurmesures.convertisseur.Systeme;
import edu.cegepvicto.convertisseurmesures.convertisseur.TypeMesure;
import edu.cegepvicto.convertisseurmesures.convertisseur.UniteMesure;
import edu.cegepvicto.convertisseurmesures.fichiers.EnregistreurFichier;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Contrôleur de l'interface de conversion
 */
public class ConvertisseurController {

    /**
     * Référence vers le convertisseur
     */
    private Convertisseur convertisseur;

    /**
     * Enregistreur de fichier
     */
    private EnregistreurFichier enregistreur;

    /**
     * Convient le texte de la valeur à convertir
     */
    @FXML
    private  TextField valeurAConvertir;

    /**
     * Boîte de sélection du type de conversion à effectuer
     */
    @FXML
    private ComboBox<TypeMesure> typeConversion;

    /**
     * Unité de mesure de la donnée originale
     */
    @FXML
    private ComboBox<UniteMesure> uniteMesure;

    /**
     * Unité de mesure vers laquelle effectuer la conversion
     */
    @FXML
    private ComboBox<UniteMesure> uniteConvertie;

    /**
     * Indique si la conversion doit être sauvegardée ou non
     */
    @FXML
    private CheckBox enregistrerFichier;

    /**
     * Texte pour afficher le résultat de la conversion
     */
    @FXML
    private Text valeurConvertieTexte;

    /**
     * Texte pour afficher les messages d'erreur d'enregistrement
     */
    @FXML
    private Text erreurEnregistrement;

    /**
     * Initialise l'interface est les objets qui traitent les données internes comme le convertisseur
     * et l'enregistreur.
     */
    @FXML
    private void initialize()
    {
        convertisseur = new Convertisseur();
        enregistreur = new EnregistreurFichier("conversions", ".txt");
        creerLiaisonInterface();

        // Peuplement du contrôle de type mesure
        List<TypeMesure> typesMesures = creerTypesMesures();
        typeConversion.setItems(FXCollections.observableList(typesMesures));
        typeConversion.getSelectionModel().select(0);
    }

    /**
     * Action lorsque le bouton de conversion est cliqué.
     * @param actionEvent les paramètres de l'événement.
     */
    @FXML
    private void onConvertirClic(ActionEvent actionEvent) {
        // Réinitialise le message d'erreur
        erreurEnregistrement.setText("");

        TypeMesure typeMesure = typeConversion.getSelectionModel().getSelectedItem();
        UniteMesure uniteMesureOriginale = uniteMesure.getSelectionModel().getSelectedItem();
        UniteMesure uniteMesureConvertie = uniteConvertie.getSelectionModel().getSelectedItem();

        double valeur = 0.0;

        try {
            valeur = Double.parseDouble(valeurAConvertir.getText());
            double valeurConvertie = convertisseur.convertir(
                    typeMesure, valeur, uniteMesureOriginale, uniteMesureConvertie
            );
            valeurConvertieTexte.setText(String.format("%.4f", valeurConvertie));

            if(enregistrerFichier.isSelected()) {
                try {
                    enregistreur.enregistrer(typeMesure, uniteMesureOriginale, uniteMesureConvertie, valeur, valeurConvertie);
                } catch (IOException erreur) {
                    erreurEnregistrement.setText("Erreur dans l'enregistrement du fichier");
                }
            }

        } catch(NumberFormatException exception) {
            valeurConvertieTexte.setText("Une de format de la valeur à convertir empêche la conversion de se réaliser.");
        }
    }

    /**
     * Crée les différentes données de types de mesure et d'unités dans le système.
     * @return les types de mesures
     */
    private List<TypeMesure> creerTypesMesures(){
        List<UniteMesure> mesuresDistance = new ArrayList<>();
        mesuresDistance.add(
                new UniteMesure("Mètre", Systeme.SYSTEME_INTERNATIONAL, 1.0));
        mesuresDistance.add(
                new UniteMesure("Centimètre", Systeme.SYSTEME_INTERNATIONAL, 100));
        mesuresDistance.add(
                new UniteMesure("Pied", Systeme.IMPERIAL, 1.0));
        mesuresDistance.add(
                new UniteMesure("Mille natique", Systeme.IMPERIAL, 0.0001645787));

        List<UniteMesure> mesuresMasse = new ArrayList<>();
        mesuresMasse.add(
                new UniteMesure("Kilogramme", Systeme.SYSTEME_INTERNATIONAL, 1.0));
        mesuresMasse.add(
                new UniteMesure("Gramme", Systeme.SYSTEME_INTERNATIONAL, 1000));
        mesuresMasse.add (
                new UniteMesure("Livre", Systeme.IMPERIAL, 1.0));
        mesuresMasse.add(
                new UniteMesure("Tonne", Systeme.IMPERIAL, 0.000446429));

        List<TypeMesure> typesMesures = new ArrayList<>();
        typesMesures.add(new TypeMesure("Distance", 3.28084, mesuresDistance));
        typesMesures.add(new TypeMesure("Masse", 2.204625, mesuresMasse));

        return typesMesures;
    }

    /**
     * Lie les composants de l'interface pour obtenir les comportements dynamiques.
     */
    private void creerLiaisonInterface(){
        typeConversion.valueProperty().addListener((_, _, nouveauTypeMesure) -> {
            uniteMesure.setItems(FXCollections.observableList(nouveauTypeMesure.getUnites()));
            uniteMesure.getSelectionModel().select(0);
            uniteConvertie.setItems(FXCollections.observableList(nouveauTypeMesure.getUnites()));
            uniteConvertie.getSelectionModel().select(0);
        });
    }
}
