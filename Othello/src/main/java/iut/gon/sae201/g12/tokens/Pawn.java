package iut.gon.sae201.g12.tokens;

import iut.gon.sae201.g12.utils.Team;

/**
 * Représente un pion du jeu Othello/Reversi.
 *
 * <p>Un pion peut être basculé pour passer de l'équipe noire à l'équipe blanche
 * et inversement.
 */
public class Pawn extends Token {
    /**
     * Crée un pion appartenant à l'équipe spécifiée.
     *
     * @param team l'équipe propriétaire du pion
     */
    public Pawn(Team team) {
        super(team);
    }

    /**
     * Bascule l'équipe propriétaire du pion.
     *
     * <p>Si le pion appartient à BLACK, il devient WHITE, et réciproquement.
     */
    public void changeTeam() {
        this.team = (this.team == Team.BLACK ? Team.WHITE : Team.BLACK);
    }

    /**
     * Retourne la représentation caractère du pion.
     *
     * @return "x" si le pion appartient à BLACK, "." si à WHITE
     */
    @Override
    public String charRepr() {
        return this.team == Team.BLACK ? "\u001B[31mX\u001B[37m" : "\u001B[34mx\u001B[37m";
    }
}