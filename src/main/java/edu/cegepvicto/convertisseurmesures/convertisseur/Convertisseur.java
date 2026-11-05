package edu.cegepvicto.convertisseurmesures.convertisseur;

/**
 * Responsable d'effectuer la conversion entre deux unités de mesure.
 */
public class Convertisseur {

    /**
     * Converti une valeur vers une autre pour un même type de mesure.
     *
     * @param typeMesure le type de mesure dans lequel la conversion est faite.
     * @param valeur la valeur à convertir.
     * @param uniteValeur les unités de la valeur à convertir.
     * @param uniteConvertir les unités vers lesquelles faire la conversion.
     * @return la valeur après la conversion.
     * @throws IllegalArgumentException si l'une des unités n'existe pas dans le type de mesure.
     */
    public double convertir(TypeMesure typeMesure, double valeur,
                                   UniteMesure uniteValeur, UniteMesure uniteConvertir) throws IllegalArgumentException
    {
        // Vérification d'intégrité des données
        validerUniteMesure(typeMesure, uniteValeur);
        validerUniteMesure(typeMesure, uniteConvertir);

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

    /**
     * Valide qu'une unité appartient bien au type de mesure.
     *
     * @param typeMesure le type dans lequel vérifier l'existance de l'unité.
     * @param uniteMesure l'unité de mesure.
     * @throws IllegalArgumentException si une unité n'existe pas dans le type.
     */
    private void validerUniteMesure(TypeMesure typeMesure, UniteMesure uniteMesure) throws IllegalArgumentException {
        if (!typeMesure.getUnites().contains(uniteMesure)) {
            throw new IllegalArgumentException(String.format("L'unité %s n'est pas le type de mesure %s.",
                    uniteMesure.getNom(), typeMesure.getNom()));
        }
    }
}
