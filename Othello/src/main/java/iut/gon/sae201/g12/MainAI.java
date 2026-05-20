package iut.gon.sae201.g12;

import iut.gon.sae201.g12.ai.MinMaxAI;
import iut.gon.sae201.g12.actions.Action;
import iut.gon.sae201.g12.factory.FactoryCube;
import iut.gon.sae201.g12.utils.IFactory;
import iut.gon.sae201.g12.utils.IState;
import iut.gon.sae201.g12.utils.Mode;

/**
 * Classe de test pour l'IA MinMax dans le jeu Othello.
 *
 * <p>Teste le comportement de l'IA de MinMax en mode POINTY avec le modèle CUBE.
 */
public class MainAI {
    /**
     * Fonction principale test pour l'IA.
     *
     * @param args les arguments de ligne de commande (non utilisés)
     */
    public static void main(String[] args) {
        iut.gon.sae201.g12.ui.GameManager manager = new iut.gon.sae201.g12.ui.GameManager();
        manager.setBoardMode(Mode.POINTY);
        manager.setDimensionMode(iut.gon.sae201.g12.ui.GameManager.DimensionMode.CUBE);
        manager.setPlayMode(iut.gon.sae201.g12.ui.GameManager.PlayMode.AI_VERSUS);
        manager.buildBoardFactory();

        IFactory factory = new FactoryCube();
        IState state = factory.testState();
        manager.setGameState(state);

        System.out.println(manager.renderBoard());

        MinMaxAI ai = new MinMaxAI(5);
        Action chosen = ai.chooseMove(state);
        System.out.println("Coup choisi: " + chosen);

        // ⚠️⚠️ LE MAIN N'EST PAS ICI
    }
}
