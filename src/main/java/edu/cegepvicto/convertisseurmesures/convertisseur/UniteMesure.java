package edu.cegepvicto.convertisseurmesures.convertisseur;

/**
 * Chaque unité de mesure des systèmes. Une unité peut se convertir vers l'unité de base de son
 * système de mesure. Par exemple le mètre ou le pied pour les distances, le kilogramme ou la livre
 * pour la masse...
 */
public class UniteMesure {

    /**
     * Le nom de l'unité
     */
    private final String nom;

    /**
     * Le système de mesure auquel appartient l'unité
     */
    private final Systeme systeme;

    /**
     * Facteur de conversion vers l'unité de base
     */
    private final double facteurVersBase;

    /**
     * Construit une nouvelle unité de mesure
     * @param nom le nom de l'unité de mesure
     * @param systeme le système dans lequel cette unité est définie
     * @param facteurVersBase le facteur de conversion vers l'unité de base
     */
    public UniteMesure(String nom, Systeme systeme, double facteurVersBase) {
        this.nom = nom;
        this.systeme = systeme;
        this.facteurVersBase = facteurVersBase;
    }


    /**
     * Accesseur du nom
     * @return le nom du type de mesure
     */
    public String getNom() {
        return nom;
    }

    /**
     * Accesseur du système de la mesure
     * @return le système de la mesure
     */
    public Systeme getSysteme() {
        return systeme;
    }

    /**
     * Accesseur du facteur de conversion vers l'unité de base
     * @return le facteur de conversion vers l'unité de base
     */
    public double getFacteurVersBase() {
        return facteurVersBase;
    }

    /**
     * Affiche l'unité de mesure comme un texte
     * @return le nom
     */
    public String toString(){
        return nom;
    }
}
