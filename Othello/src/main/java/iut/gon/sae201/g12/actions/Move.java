package iut.gon.sae201.g12.actions;
import iut.gon.sae201.g12.actions.Action;
import iut.gon.sae201.g12.utils.Coordinate;

/**
 * Représente un mouvement dans le jeu Othello.
 *
 * <p>Un mouvement contient une coordonnée source et une coordonnée destination.
 */
public class Move extends Action {
    /** Coordonnée de départ */
    private Coordinate from;
    /** Coordonnée de destination */
    private Coordinate to;

    /**
     * Crée un mouvement.
     *
     * @param from la coordonnée de départ
     * @param to la coordonnée de destination
     */
    public Move(Coordinate from, Coordinate to) {
        super();
        this.from = from;
        this.to = to;
    }

    /**
     * Retourne la coordonnée de départ.
     *
     * @return la coordonnée source
     */
    public Coordinate getFrom() {
        return this.from;
    }

    /**
     * Retourne la coordonnée de destination.
     *
     * @return la coordonnée cible
     */
    public Coordinate getTo() {
        return this.to;
    }

    /**
     * Modifie la coordonnée de départ.
     *
     * @param from la nouvelle coordonnée de départ
     */
    public void SetFrom(Coordinate from) {
        this.from = from;
    }

    /**
     * Modifie la coordonnée de destination.
     *
     * @param to la nouvelle coordonnée de destination
     */
    public void SetTo(Coordinate to) {this.to = to;}
}