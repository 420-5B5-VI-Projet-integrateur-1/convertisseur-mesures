package edu.cegepvicto.convertisseurmesures;

import edu.cegepvicto.convertisseurmesures.convertisseur.Systeme;
import edu.cegepvicto.convertisseurmesures.convertisseur.TypeMesure;
import edu.cegepvicto.convertisseurmesures.convertisseur.UniteMesure;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class ConvertisseurController {

    private List<TypeMesure> typesMesures;

    @FXML
    private  TextField valeurAConvertir;

    @FXML
    private ComboBox<TypeMesure> typeConversion;

    @FXML
    private ComboBox<UniteMesure> uniteMesure;

    @FXML
    private ComboBox<UniteMesure> uniteConvertie;

    @FXML
    private CheckBox enregistrerFichier;

    @FXML
    private void initialize()
    {
        typesMesures = creerTypesMesures();

        creerLiaisonInterface();

        // Peuplement du contrôle de type mesure
        typeConversion.setItems(FXCollections.observableList(typesMesures));
        typeConversion.getSelectionModel().select(0);
    }

    @FXML
    private void onConvertirClic(ActionEvent actionEvent) {

    }

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

        typesMesures = new ArrayList<>();
        typesMesures.add(new TypeMesure("Distance", 3.28084, mesuresDistance));
        typesMesures.add(new TypeMesure("Masse", 2.204625, mesuresMasse));

        return typesMesures;
    }

    private void creerLiaisonInterface(){
        typeConversion.valueProperty().addListener((_, _, nouveauTypeMesure) -> {
            uniteMesure.setItems(FXCollections.observableList(nouveauTypeMesure.getUnites()));
            uniteMesure.getSelectionModel().select(0);
            uniteConvertie.setItems(FXCollections.observableList(nouveauTypeMesure.getUnites()));
            uniteConvertie.getSelectionModel().select(0);
        });
    }
}
