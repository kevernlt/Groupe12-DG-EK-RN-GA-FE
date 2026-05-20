package iut.gon.sae201.g12;


import iut.gon.sae201.g12.actions.Action;
import iut.gon.sae201.g12.utils.Coordinate;

import java.util.HashSet;

/**
 * Représente une action de suppression de ligne de pions dans le jeu Othello.
 *
 * <p>Une action de suppression inclut la ligne de pions à retirer et l'anneau
 * (ring) associé à cette suppression.
 */
public class RemoveLine extends Action {
    /** L'ensemble des coordonnées formant la ligne de pions */
    private HashSet<Coordinate> line;
    /** La coordonnée de l'anneau associé */
    private Coordinate ring;

    /**
     * Crée une action de suppression de ligne.
     *
     * @param line l'ensemble des coordonnées de la ligne de pions
     * @param ring la coordonnée de l'anneau
     */
    public RemoveLine(HashSet<Coordinate> line, Coordinate ring) {
        super();
        this.line = line;
        this.ring = ring;
    }

    /**
     * Retourne la ligne de pions à supprimer.
     *
     * @return l'ensemble des coordonnées formant la ligne
     */
    public HashSet<Coordinate> getLine() {
        return this.line;
    }

    /**
     * Modifie la ligne de pions.
     *
     * @param line la nouvelle ligne
     */
    public void setline(HashSet<Coordinate> line) {
        this.line = line;
    }

    /**
     * Retourne la coordonnée de l'anneau à supprimer.
     *
     * @return la coordonnée de l'anneau
     */
    public Coordinate getRing() {
        return this.ring;
    }

    /**
     * Modifie la coordonnée de l'anneau.
     *
     * @param ring la nouvelle coordonnée de l'anneau
     */
    public void setRing(Coordinate ring) {this.ring = ring;}
}