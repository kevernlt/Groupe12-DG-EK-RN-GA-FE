package iut.gon.sae201.g12.utils;

import java.util.List;
import java.util.Objects;

/**
 * Représente une coordonnée dans un système hexagonal abstrait.
 * <p>
 * Cette classe abstraite définit l'API commune pour différents modèles
 * de représentation de coordonnées hexagonales (par exemple {@link CoordinateCube}
 * et {@link CoordinateDoubled}). Elle fournit des opérations génériques comme
 * la conversion en coordonnées 2D, l'obtention des voisins et le calcul d'une
 * direction.
 */
public abstract class Coordinate {
    /**
     * Convertit cette coordonnée en une représentation 2D (pixel/grid simple) pour
     * affichage ou comparaison.
     *
     * @return un {@link Point} correspondant à cette coordonnée
     */
    public abstract Point to2DCoordinate();

    /**
     * Déplace la coordonnée d'une unité dans la {@code direction} donnée en
     * tenant compte du {@code mode} (FLAT ou POINTY).
     *
     * @param mode      le mode d'orientation des hexagones
     * @param direction la direction du déplacement
     * @return la nouvelle coordonnée déplacée
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
     * Renvoie la liste des voisins directs (6 cases) de cette coordonnée selon le {@code mode}.
     *
     * @param mode le mode d'orientation (FLAT ou POINTY)
     * @return une liste immuable des 6 coordonnées voisines
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
     * Calcule la liste des coordonnées strictement entre cette coordonnée et la
     * coordonnée {@code to} sur un même axe. Si les coordonnées sont identiques,
     * retourne une liste vide.
     *
     * @param mode le mode d'orientation (non utilisé par toutes les implémentations)
     * @param to   la coordonnée destination
     * @return la liste des coordonnées entre les deux (peut être vide)
     */
    public abstract List<Coordinate> between(Mode mode, Coordinate to);

    /* Méthodes pour chaque direction. Chaque implémentation doit fournir
       la logique de déplacement selon le mode. */
    public abstract Coordinate NO(Mode mode);

    public abstract Coordinate NE(Mode mode);

    public abstract Coordinate E(Mode mode);

    public abstract Coordinate O(Mode mode);

    public abstract Coordinate N(Mode mode);

    public abstract Coordinate S(Mode mode);

    public abstract Coordinate SO(Mode mode);

    public abstract Coordinate SE(Mode mode);

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Coordinate co && this.to2DCoordinate().equals(co.to2DCoordinate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(to2DCoordinate());
    }
}
