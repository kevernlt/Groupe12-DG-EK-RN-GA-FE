package iut.gon.sae201.g12.utils;

/**
 * Représentation immuable d'un point 2D.
 *
 * <p>Cette structure est utilisée pour exposer les coordonnées sous une forme simple,
 * notamment lors de la conversion depuis les modèles hexagonaux.
 *
 * @param x l'abscisse du point
 * @param y l'ordonnée du point
 */
public record Point(int x, int y) {
    /**
     * Retourne une représentation textuelle lisible du point.
     *
     * @return une chaîne du type {@code Point[X=..., Y=...]}
     */
    @Override
    public String toString() {
        return "Point[X=" + x + ", Y=" + y + "]";
    }
}
