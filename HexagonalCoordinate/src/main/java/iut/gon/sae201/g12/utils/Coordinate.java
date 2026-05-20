package iut.gon.sae201.g12.utils;

import java.util.List;
import java.util.Objects;

/**
 * Représente une coordonnée abstraite dans un espace hexagonal.
 *
 * <p>Cette classe définit l'API commune aux différentes représentations de coordonnées
 * hexagonales du projet. Elle permet de convertir une coordonnée en 2D, de se déplacer
 * selon une direction, de récupérer les voisins directs et de calculer les coordonnées
 * situées entre deux positions sur un même axe.
 */
public abstract class Coordinate {

    /**
     * Convertit cette coordonnée vers une représentation 2D utilisable pour l'affichage
     * ou pour comparer deux coordonnées issues de modèles différents.
     *
     * @return le point 2D correspondant à cette coordonnée
     */
    public abstract Point to2DCoordinate();

    /**
     * Déplace cette coordonnée d'une case dans la direction demandée.
     *
     * @param mode le mode d'orientation des hexagones
     * @param direction la direction de déplacement
     * @return une nouvelle coordonnée après déplacement
     */
    public Coordinate toDir(Mode mode, Direction direction) {
        return switch (direction) {
            case NO -> this.NO(mode);
            case N -> this.N(mode);
            case NE -> this.NE(mode);
            case E -> this.E(mode);
            case SE -> this.SE(mode);
            case S -> this.S(mode);
            case SO -> this.SO(mode);
            case O -> this.O(mode);
        };
    }

    /**
     * Renvoie les voisins directs de cette coordonnée.
     *
     * <p>Le mode {@link Mode#FLAT} ou {@link Mode#POINTY} détermine quelles directions
     * sont considérées comme adjacentes.
     *
     * @param mode le mode d'orientation
     * @return une liste immuable contenant les 6 voisins directs
     */
    public List<Coordinate> getNeighbors(Mode mode) {

        List<Coordinate> ListCo;

        if (mode.equals(Mode.FLAT)) {
            ListCo = List.of(

                    this.N(mode), this.S(mode), this.NO(mode), this.NE(mode), this.SE(mode), this.SO(mode)

            );
        }else {

            ListCo = List.of(

                    this.E(mode), this.O(mode), this.NO(mode), this.NE(mode), this.SE(mode), this.SO(mode)

            );

        }

        return ListCo;


    }

    /**
     * Calcule les coordonnées strictement situées entre cette coordonnée et la coordonnée cible.
     *
     * <p>Le comportement exact dépend de l'implémentation concrète et du modèle de coordonnées.
     * Si les deux coordonnées sont identiques, la méthode renvoie une liste vide.
     *
     * @param mode le mode d'orientation
     * @param to la coordonnée destination
     * @return les coordonnées intermédiaires entre les deux points
     */
    public abstract List<Coordinate> between(Mode mode, Coordinate to);

    /**
     * Déplace cette coordonnée vers le nord-ouest.
     *
     * @param mode le mode d'orientation
     * @return la coordonnée obtenue après déplacement
     */
    public abstract Coordinate NO(Mode mode);

    /**
     * Déplace cette coordonnée vers le nord-est.
     *
     * @param mode le mode d'orientation
     * @return la coordonnée obtenue après déplacement
     */
    public abstract Coordinate NE(Mode mode);

    /**
     * Déplace cette coordonnée vers l'est.
     *
     * @param mode le mode d'orientation
     * @return la coordonnée obtenue après déplacement
     */
    public abstract Coordinate E(Mode mode);

    /**
     * Déplace cette coordonnée vers l'ouest.
     *
     * @param mode le mode d'orientation
     * @return la coordonnée obtenue après déplacement
     */
    public abstract Coordinate O(Mode mode);

    /**
     * Déplace cette coordonnée vers le nord.
     *
     * @param mode le mode d'orientation
     * @return la coordonnée obtenue après déplacement
     */
    public abstract Coordinate N(Mode mode);

    /**
     * Déplace cette coordonnée vers le sud.
     *
     * @param mode le mode d'orientation
     * @return la coordonnée obtenue après déplacement
     */
    public abstract Coordinate S(Mode mode);

    /**
     * Déplace cette coordonnée vers le sud-ouest.
     *
     * @param mode le mode d'orientation
     * @return la coordonnée obtenue après déplacement
     */
    public abstract Coordinate SO(Mode mode);

    /**
     * Déplace cette coordonnée vers le sud-est.
     *
     * @param mode le mode d'orientation
     * @return la coordonnée obtenue après déplacement
     */
    public abstract Coordinate SE(Mode mode);

    /**
     * Compare deux coordonnées à partir de leur représentation 2D.
     *
     * @param obj l'objet à comparer
     * @return {@code true} si les deux coordonnées ont la même position 2D
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof Coordinate co && this.to2DCoordinate().equals(co.to2DCoordinate());
    }

    /**
     * Retourne le code de hachage basé sur la représentation 2D.
     *
     * @return le code de hachage de cette coordonnée
     */
    @Override
    public int hashCode() {
        return Objects.hash(to2DCoordinate());
    }
}
