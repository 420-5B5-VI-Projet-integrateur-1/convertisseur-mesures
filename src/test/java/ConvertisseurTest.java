import edu.cegepvicto.convertisseurmesures.convertisseur.Convertisseur;
import edu.cegepvicto.convertisseurmesures.convertisseur.Systeme;
import edu.cegepvicto.convertisseurmesures.convertisseur.TypeMesure;
import edu.cegepvicto.convertisseurmesures.convertisseur.UniteMesure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConvertisseurTest {

    private Convertisseur convertisseur;

    private List<TypeMesure> typesMesures;

    private List<UniteMesure> unitesType1;

    private List<UniteMesure> unitesType2;

    @BeforeEach
    public void preparationTest() {
        convertisseur = new Convertisseur();
        typesMesures = new ArrayList<>();

        unitesType1 = new ArrayList<>();
        unitesType1.add(new UniteMesure("Type 1 - Unité 1", Systeme.SYSTEME_INTERNATIONAL, 1.0));
        unitesType1.add(new UniteMesure("Type 1 - Unité 2", Systeme.SYSTEME_INTERNATIONAL, 0.5));

        unitesType2 = new ArrayList<>();
        unitesType2.add(new UniteMesure("Type 2 - Unité 1", Systeme.SYSTEME_INTERNATIONAL, 1.0));
        unitesType2.add(new UniteMesure("Type 2 - Unité 2", Systeme.SYSTEME_INTERNATIONAL, 3));
        unitesType2.add(new UniteMesure("Type 2 - Unité 3", Systeme.IMPERIAL, 1.0));
        unitesType2.add(new UniteMesure("Type 2 - Unité 4", Systeme.IMPERIAL, .2));

        typesMesures.add(new TypeMesure("Type 1", 0.33, unitesType1));
        typesMesures.add(new TypeMesure("Type 2", 7.0, unitesType2));
    }

    @Test
    public void conversionIntraSysteme() {
        // Convertir de l'unité 2 vers 1 -> multiplier par 0.5
        assertEquals(1.0, convertisseur.convertir(typesMesures.get(0), 2.0,
                unitesType1.get(1), unitesType1.get(0)), 0.0001);
    }

    @Test
    public void conversionInterSysteme() {
        // Convertir de l'unité 1 vers 3 -> diviser par 7
        assertEquals(2, convertisseur.convertir(typesMesures.get(1), 14.0,
                unitesType2.get(2), unitesType2.get(0)), 0.0001);
    }

    @Test
    public void conversionComplexeInterSysteme(){
        // Convertir de l'unité 2 vers 4 -> x3, x7, /.2 (x5)
        assertEquals(1050, convertisseur.convertir(typesMesures.get(1), 10.0,
                unitesType2.get(1), unitesType2.get(3)), 0.0001);
    }

    @Test
    public void conversionComplexeInterSystemeInverse(){
        // Convertir de l'unité 4 vers 2 -> x.2, x 1/7, x 1/3
        assertEquals(4200, convertisseur.convertir(typesMesures.get(1), 40.0,
                unitesType2.get(1), unitesType2.get(3)), 0.0001);
    }

    @Test
    public void uniteIncoherente(){
        assertThrows(IllegalArgumentException.class, () -> {
            convertisseur.convertir(typesMesures.get(0), 1.0,
                    unitesType1.get(0), unitesType2.get(0));
        });
    }
}
