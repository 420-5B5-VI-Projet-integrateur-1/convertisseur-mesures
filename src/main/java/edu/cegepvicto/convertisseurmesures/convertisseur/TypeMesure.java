package edu.cegepvicto.convertisseurmesures.convertisseur;

import java.util.List;

/**
 * Un type de mesure qui peut être convertie comme une distance ou une masse. Contient l'information
 * pour la conversion vers le type de base du SI vers le système impérial. Par exemple, le type
 * distance sait comment convertir un metre en pieds. Contient également la liste des unités de mesure
 * de ce type de mesure.
 */
public class TypeMesure {

    /**
     * Le nom du type de mesure
     */
    private final String nom;

    /**
     * Le facteur de conversion entre l'unité de base du SI pour la convertir en imp.
     */
    private final double SIversImp;

    /**
     * Les unités de mesure dans ce type de mesure
     */
    private final List<UniteMesure> unites;

    /**
     * Construit un nouveau type de mesure.
     *
     * @param nom le nom du type de mesure
     * @param SIversImp le facteur de conversion entre l'unité de base du SI vers l'impérial
     * @param unites les unités de mesure de ce type
     */
    public TypeMesure(String nom, double SIversImp, List<UniteMesure> unites) {
        this.nom = nom;
        this.SIversImp = SIversImp;
        this.unites = unites;
    }

    /**
     * Accesseur du nom
     * @return le nom du type de mesure
     */
    public String getNom() {
        return nom;
    }

    /**
     * Accesseur du facteur de conversion entre le SI et l'impérial
     * @return le nom du type de mesure
     */
    public double getSIversImp() {
        return SIversImp;
    }

    /**
     * Accesseur de toutes les unités de mesure de ce type de mesure
     * @return la liste des unités de mesure
     */
    public List<UniteMesure> getUnites() {
        return unites;
    }

    /**
     * Affiche le type de mesure comme un texte
     * @return le nom
     */
    public String toString(){
        return nom;
    }
}
