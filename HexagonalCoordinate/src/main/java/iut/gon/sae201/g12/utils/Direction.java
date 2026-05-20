package iut.gon.sae201.g12.utils;

/**
 * Enumération des 8 directions utilisées pour naviguer sur une grille hexagonale.
 */
public enum Direction {
    NO,
    N,
    NE,
    E,
    SE,
    S,
    SO,
    O;

    /**
     * Retourne la direction opposée à celle-ci.
     *
     * @return la direction opposée
     */
    public Direction opposite() {
        return switch (this) {
            case NO -> SE;
            case N -> S;
            case NE -> SO;
            case E -> O;
            case SE -> NO;
            case S -> N;
            case SO -> NE;
            case O -> E;
        };
    }
}
