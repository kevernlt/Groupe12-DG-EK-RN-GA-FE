package iut.gon.sae201.g12.utils;

/**
 * Représentation immuable d'un point 2D utilisé pour l'affichage ou la comparaison
 * entre différentes représentations de coordonnées hexagonales.
 *
 * @param x abscisse
 * @param y ordonnée
 */
public record Point(int x, int y) {
    @Override
    public String toString() {
        return "Point[X=" + x + ", Y=" + y + "]";
    }
}
