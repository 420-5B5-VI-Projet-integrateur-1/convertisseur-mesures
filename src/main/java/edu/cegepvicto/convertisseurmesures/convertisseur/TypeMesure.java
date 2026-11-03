package edu.cegepvicto.convertisseurmesures.convertisseur;

import java.util.List;

/**
 * Un type de mesure qui peut être convertie comme une distance ou une masse. Contient l'information
 * pour la conversion vers le type de base du SI vers le système impérial. Par exemple, le type
 * distance sait comment convertir un metre en pieds. Contient également la liste des unités de mesure
 * de ce type de mesure.
 */
public class TypeMesure {

    private final String nom;

    private  final double SIversImp;

    private final List<UniteMesure> unites;

    public TypeMesure(String nom, double SIversImp, List<UniteMesure> unites) {
        this.nom = nom;
        this.SIversImp = SIversImp;
        this.unites = unites;
    }


    public String getNom() {
        return nom;
    }

    public double getSIversImp() {
        return SIversImp;
    }

    public List<UniteMesure> getUnites() {
        return unites;
    }

    public String toString(){
        return nom;
    }
}
