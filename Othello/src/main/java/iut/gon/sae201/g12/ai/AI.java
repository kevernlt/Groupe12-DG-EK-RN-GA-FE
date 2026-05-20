package iut.gon.sae201.g12.ai;

import iut.gon.sae201.g12.actions.Action;
import iut.gon.sae201.g12.utils.IState;

/**
 * Interface pour la représentation des IA du jeu Othello.
 *
 * <p>Les implémentations fournissent des stratégies différentes pour choisir
 * le meilleur mouvement.
 */
public interface AI {
    /**
     * Choisit le meilleur coup pour le joueur actuel.
     *
     * @param state l'état actuel du jeu
     * @return l'action à effectuer
     */
    Action chooseMove(IState state);
}
