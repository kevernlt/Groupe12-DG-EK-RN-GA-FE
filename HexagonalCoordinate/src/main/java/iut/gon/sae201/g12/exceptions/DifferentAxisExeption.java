package iut.gon.sae201.g12.exceptions;

/**
 * Exception levée lorsqu'on tente d'obtenir un chemin entre deux coordonnées
 * qui ne partagent aucun axe commun dans la représentation choisie.
 */
public class DifferentAxisExeption extends RuntimeException {
    /**
     * Crée l'exception avec un message explicite.
     *
     * @param message texte décrivant l'erreur
     */
    public DifferentAxisExeption(String message) {
        super(message);
    }
}
