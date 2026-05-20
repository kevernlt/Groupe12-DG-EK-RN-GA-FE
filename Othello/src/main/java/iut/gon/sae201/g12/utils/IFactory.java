package iut.gon.sae201.g12.utils;

/**
 * Interface pour la création d'états de jeu Othello à des fins de test.
 *
 * <p>Les implémentations fournissent des états préconfigurés pour tester
 * différents scénarios du jeu et permettent la conversion de coordonnées.
 */
public interface IFactory {
    /**
     * Convertit des coordonnées "doubled" (2D) en coordonnées du système utilisé.
     *
     * @param x la coordonnée x en système doubled
     * @param y la coordonnée y en système doubled
     * @return la coordonnée convertie
     */
    Coordinate fromDoubled(int x, int y);

    /**
     * Crée un état de test standard.
     *
     * @return un état de jeu configuré pour un test
     */
    IState testState();

    /**
     * Crée un état où une ligne noire peut être formée.
     *
     * @return un état de jeu permettant l'ajout d'une ligne noire
     */
    IState stateForBlackLineTest();

    /**
     * Crée un état où une ligne blanche peut être formée.
     *
     * @return un état de jeu permettant l'ajout d'une ligne blanche
     */
    IState stateForWhiteLineTest();

    /**
     * Crée un plateau vide.
     *
     * @return un état de jeu avec aucune pièce
     */
    IState emptyState();

    /**
     * Crée un état contenant deux lignes potentielles de pions.
     *
     * @return un état de jeu avec configuration de double ligne
     */
    IState doubleLineStateTest();
}