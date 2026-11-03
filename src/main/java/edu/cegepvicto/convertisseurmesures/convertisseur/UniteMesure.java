package edu.cegepvicto.convertisseurmesures.convertisseur;

/**
 * Chaque unité de mesure des systèmes. Une unité peut se convertir vers l'unité de base de son
 * système de mesure. Par exemple le mètre ou le pied pour les distances, le kilogramme ou la livre
 * pour la masse...
 */
public class UniteMesure {

    private final String nom;

    private final Systeme systeme;

    private final double facteurVersBase;

    public UniteMesure(String nom, Systeme systeme, double facteurVersBase) {
        this.nom = nom;
        this.systeme = systeme;
        this.facteurVersBase = facteurVersBase;
    }

    public String getNom() {
        return nom;
    }

    public Systeme getSysteme() {
        return systeme;
    }

    public double getFacteurVersBase() {
        return facteurVersBase;
    }

    public String toString(){
        return nom;
    }
}
