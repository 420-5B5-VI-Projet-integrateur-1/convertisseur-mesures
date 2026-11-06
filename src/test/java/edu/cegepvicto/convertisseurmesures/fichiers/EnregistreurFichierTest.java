package edu.cegepvicto.convertisseurmesures.fichiers;

import edu.cegepvicto.convertisseurmesures.convertisseur.Systeme;
import edu.cegepvicto.convertisseurmesures.convertisseur.TypeMesure;
import edu.cegepvicto.convertisseurmesures.convertisseur.UniteMesure;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.imageio.IIOException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EnregistreurFichierTest {

    private EnregistreurFichier enregistreur;

    private TypeMesure typeMesure;

    private UniteMesure unite1;
    private UniteMesure unite2;
    private Path cheminFichier;

    @BeforeEach
    public void initialisation(){
        enregistreur = new EnregistreurFichier("test", ".txt");
        cheminFichier = Paths.get("test.txt");

        unite1 = new UniteMesure("Unite 1", Systeme.IMPERIAL, 1.0);
        unite2 = new UniteMesure("Unite 2", Systeme.IMPERIAL, 1.0);
        List<UniteMesure> unites = new ArrayList<>();
        unites.add(unite1);
        unites.add(unite2);
        typeMesure = new TypeMesure("Type", 1.0, unites);
    }

    @AfterEach
    void nettoyer() {
        try {
            Files.delete(cheminFichier);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void enregistrer() {
        String donnee = "Conversion de " + typeMesure.getNom() + " [" + unite1.getNom() +
                "] -> [" + unite2.getNom() + "] : " + 2.5 + " -> " + 1.0 + "\n";

        try {
            enregistreur.enregistrer(typeMesure, unite1, unite2, 2.5, 1.0);

            String contenuFichier = Files.readString(cheminFichier);

            assertEquals(donnee, contenuFichier);
        } catch(IOException erreur) {
            fail(erreur);
        }
    }

    @Test
    void ajouterInformation() {
        String donnee = "Conversion de " + typeMesure.getNom() + " [" + unite1.getNom() +
                "] -> [" + unite2.getNom() + "] : " + 2.5 + " -> " + 1.0 + "\n";
        String donnee2 = donnee + "Conversion de " + typeMesure.getNom() + " [" + unite2.getNom() +
                "] -> [" + unite1.getNom() + "] : " + 7.0 + " -> " + 50.0 + "\n";

        try {
            enregistreur.enregistrer(typeMesure, unite1, unite2, 2.5, 1.0);
            String contenuFichier = Files.readString(cheminFichier);
            assertEquals(donnee, contenuFichier);

            enregistreur.enregistrer(typeMesure, unite2, unite1, 7.0, 50);
            contenuFichier = Files.readString(cheminFichier);
            assertEquals(donnee2, contenuFichier);

        } catch(IOException erreur) {
            fail(erreur);
        }
    }

    @Test
    public void fichierInexistant()
    {

    }

    @Test
    public void erreurEcriture(){

    }
}