package iut.gon.sae201.g12.tokens;

import iut.gon.sae201.g12.utils.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Classe abstraite représentant une pièce du jeu Othello.
 *
 * <p>Chaque pièce appartient à une équipe (BLACK ou WHITE) et possède
 * une représentation textuelle pour l'affichage.
 */
@Getter
@Setter
@AllArgsConstructor
public abstract class Token {
    /** Équipe propriétaire de cette pièce */
    protected Team team;

    /**
     * Retourne la représentation textuelle/caractère de la pièce.
     *
     * @return une chaîne représentant la pièce (ex: "P" pour Pion, "R" pour Ring)
     */
    public abstract String charRepr();

    /**
     * Clone cette pièce.
     *
     * @return une copie de la pièce
     * @throws CloneNotSupportedException si le clonage n'est pas supporté
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}