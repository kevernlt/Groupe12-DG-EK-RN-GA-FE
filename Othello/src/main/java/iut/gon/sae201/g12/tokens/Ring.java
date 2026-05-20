package iut.gon.sae201.g12.tokens;

import iut.gon.sae201.g12.utils.Team;

/**
 * Représente un anneau (ring) du jeu Othello/Reversi.
 *
 * <p>Un anneau appartient à une équipe et est distingué visuellement d'un pion.
 */
public class Ring extends Token {
    /**
     * Crée un anneau appartenant à l'équipe spécifiée.
     *
     * @param team l'équipe propriétaire de l'anneau
     */
    public Ring(Team team) {
        super(team);
    }

    /**
     * Retourne la représentation caractère de l'anneau.
     *
     * @return "O" si l'anneau appartient à BLACK, "o" si à WHITE
     */
    @Override
    public String charRepr() {
        return this.team == Team.BLACK ? "\u001B[31mO\u001B[37m" : "\u001B[34mo\u001B[37m";
    }
}