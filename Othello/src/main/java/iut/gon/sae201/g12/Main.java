package iut.gon.sae201.g12;

import iut.gon.sae201.g12.ui.GameManager;
import iut.gon.sae201.g12.ui.InputManager;
import iut.gon.sae201.g12.ui.UIUtils;
import iut.gon.sae201.g12.utils.Mode;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        GameManager gameManager = new GameManager();
        System.out.println("---- Othello ----");
        System.out.println("---> Sélectionnez l'adverse");
        System.out.println("1 - Jouer contre un autre joueur");
        System.out.println("2 - Jouer contre une IA");
        gameManager.setPlayMode(InputManager.awaitNumberInput() == 1 ? GameManager.PlayMode.PLAYER_VERSUS : GameManager.PlayMode.AI_VERSUS);

        UIUtils.clearTerminal();
        System.out.println("---> Sélectionnez le mode de plateau");
        System.out.println("1 - Plat");
        System.out.println("2 - Pointu");
        gameManager.setBoardMode(InputManager.awaitNumberInput() == 1 ? Mode.FLAT : Mode.POINTY);

        gameManager.setDimensionMode(GameManager.DimensionMode.DOUBLED);
        gameManager.buildBoardFactory();
        UIUtils.clearTerminal();
        System.out.println("---> Plateau IFactory utilisé");
        System.out.println("1 - testState()");
        System.out.println("2 - stateForBlackLineTest()");
        System.out.println("3 - stateForWhiteLineTest()");
        System.out.println("4 - emptyState()");
        System.out.println("5 - doubleLineStateTest()");
        System.out.println("6 - Placement manuel");

        switch (InputManager.awaitNumberInput()) {
            case 1 -> gameManager.setGameState(gameManager.getFactory().testState());
            case 2 -> gameManager.setGameState(gameManager.getFactory().stateForBlackLineTest());
            case 3 -> gameManager.setGameState(gameManager.getFactory().stateForWhiteLineTest());
            case 4 -> gameManager.setGameState(gameManager.getFactory().emptyState());
            case 5 -> gameManager.setGameState(gameManager.getFactory().doubleLineStateTest());
            case 6 -> gameManager.setGameState(gameManager.ringsPlacement());
        }

        System.out.println("---- Configuration terminée ----");

        gameManager.showPlate();
        gameManager.start();
    }
}
