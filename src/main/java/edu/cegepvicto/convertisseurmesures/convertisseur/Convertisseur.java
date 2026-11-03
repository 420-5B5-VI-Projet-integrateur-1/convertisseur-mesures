package edu.cegepvicto.convertisseurmesures.convertisseur;

public class Convertisseur {

    public static double convertir(TypeMesure typeMesure, double valeur,
                                   UniteMesure uniteValeur, UniteMesure uniteConvertir) throws IllegalArgumentException
    {
        // Vérification d'intégrité des données
        if (!typeMesure.getUnites().contains(uniteValeur)) {
            throw new IllegalArgumentException(String.format("L'unité %s n'est pas le type de mesure %s.",
                    uniteValeur.getNom(), typeMesure.getNom()));
        }

        if (!typeMesure.getUnites().contains(uniteConvertir)) {
            throw new IllegalArgumentException(String.format("L'unité %s n'est pas le type de mesure %s.",
                    uniteValeur.getNom(), typeMesure.getNom()));
        }

        // Converti vers le type de base
        double valeurConvertie = valeur * uniteValeur.getFacteurVersBase();

        // Fait le changement de système si nécessaire
        if(uniteValeur.getSysteme() != uniteConvertir.getSysteme()) {
            if(uniteValeur.getSysteme() == Systeme.SYSTEME_INTERNATIONAL) {
                valeurConvertie *= typeMesure.getSIversImp();
            }
            else {
                valeurConvertie /= typeMesure.getSIversImp();
            }
        }

        // Converti du type de base vers le type à convertir
        return valeurConvertie /= uniteConvertir.getFacteurVersBase();
    }
}
