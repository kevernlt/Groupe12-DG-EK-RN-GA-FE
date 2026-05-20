package iut.gon.sae201.g12.utils;

/**
 * Enumération des directions disponibles sur une grille hexagonale.
 *
 * <p>Les directions suivent les conventions usuelles : nord-ouest, nord,
 * nord-est, est, sud-est, sud, sud-ouest et ouest.
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
     * Retourne la direction opposée à celle courante.
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
