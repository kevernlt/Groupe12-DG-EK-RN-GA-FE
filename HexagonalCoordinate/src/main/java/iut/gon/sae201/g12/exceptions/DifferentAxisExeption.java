package iut.gon.sae201.g12.exceptions;

/**
 * Exception levée lorsqu'aucun axe commun n'existe entre deux coordonnées.
 *
 * <p>Cette exception est utilisée par les méthodes qui calculent les coordonnées
 * intermédiaires entre deux positions hexagonales.
 */
public class DifferentAxisExeption extends RuntimeException {
    /**
     * Crée une nouvelle exception avec un message explicite.
     *
     * @param message description de l'erreur
     */
    public DifferentAxisExeption(String message) {
        super(message);
    }
}
