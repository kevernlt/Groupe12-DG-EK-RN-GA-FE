package iut.gon.sae201.g12.ui;

import iut.gon.sae201.g12.Model;
import iut.gon.sae201.g12.State;
import iut.gon.sae201.g12.actions.Action;
import iut.gon.sae201.g12.actions.Move;
import iut.gon.sae201.g12.ai.MinMaxAI;
import iut.gon.sae201.g12.factory.FactoryCube;
import iut.gon.sae201.g12.factory.FactoryDoubled;
import iut.gon.sae201.g12.tokens.Pawn;
import iut.gon.sae201.g12.tokens.Ring;
import iut.gon.sae201.g12.tokens.Token;
import iut.gon.sae201.g12.utils.*;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class GameManager {
    @Getter
    IFactory factory;
    @Setter
    IState gameState;
    @Setter
    PlayMode playMode;
    @Setter
    Mode boardMode;
    @Setter
    DimensionMode dimensionMode;

    /**
     * Lance le jeu Othello avec tour par tour (joueur vs joueur ou joueur vs IA).
     *
     * @throws InvocationTargetException si l'invocation réfléchie échoue
     * @throws NoSuchMethodException     si le constructeur souhaité n'existe pas
     * @throws InstantiationException    si l'instantiation échoue
     * @throws IllegalAccessException    si l'accès réfléchi échoue
     */
    public void start() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        UIUtils.clearTerminal();
        System.out.println("=== Othello ===\n");
        showPlate();

        if (gameState instanceof State) {
            // Vérifier si les anneaux ont déjà été placés (si le plateau n'est pas vide)
            boolean needsRingPlacement = true;
            for (Token token : gameState.board().values()) {
                if (token instanceof Ring) {
                    needsRingPlacement = false;
                    break;
                }
            }

            // Si aucun anneau n'a été trouvé, faire le placement
            if (needsRingPlacement) {
                gameState = ringsPlacement();
            }

            Model model = new Model(gameState);

            // Initialiser l'IA si mode AI
            MinMaxAI ai = null;
            if (playMode == PlayMode.AI_VERSUS) {
                ai = new MinMaxAI(3);
                System.out.println("\nL'IA va jouer avec les pions NOIRS (BLACK)");
            }

            // Boucle de jeu principale
            while (((State) gameState).winner() == null) {
                UIUtils.clearTerminal();
                showPlate();

                Team currentTeam = model.getTurn();
                System.out.println("\n--- Tour de " + currentTeam.name() + " ---");

                System.out.println("Anneaux restants - BLACK: " + model.getRings(Team.BLACK).size() +
                                 ", WHITE: " + model.getRings(Team.WHITE).size());

                if (!model.getPawnsLines().isEmpty()) {
                    System.out.println("\nDes lignes de 5 pions ont été formées !");
                    handleLineRemoval(model);
                }

                // Déterminer qui joue : joueur humain ou IA
                boolean isAITurn = (playMode == PlayMode.AI_VERSUS && currentTeam == Team.BLACK);

                if (isAITurn) {
                    // Tour de l'IA
                    System.out.println("\nL'IA réfléchit...");
                    try {
                        Thread.sleep(1000); // Pause pour l'UX
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    try {
                        if (ai != null) {
                            Action aiMove = ai.chooseMove(gameState);
                            if (aiMove instanceof Move move) {
                                System.out.println("L'IA joue:");
                                Point fromPoint = move.getFrom().to2DCoordinate();
                                Point toPoint = move.getTo().to2DCoordinate();
                                System.out.println("  De (" + fromPoint.x() + ", " + fromPoint.y() + ") à (" +
                                                 toPoint.x() + ", " + toPoint.y() + ")");
                                gameState = gameState.move(move);
                                model = new Model(gameState);
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Erreur lors du coup de l'IA : " + e.getMessage());
                    }
                } else {
                    // Tour du joueur humain
                    playHumanTurn();
                    model = new Model(gameState);
                }
            }

            // Afficher le vainqueur
            showPlate();
            System.out.println("\n" + "=".repeat(40));
            Team winner = ((State) gameState).winner();
            if (winner == Team.BLACK) {
                System.out.println("L'équipe NOIRE a gagné !");
            } else if (winner == Team.WHITE) {
                System.out.println("L'équipe BLANCHE a gagné !");
            }
            System.out.println("=".repeat(40));
        }
    }

    /**
     * Gère le tour d'un joueur humain.
     */
    private void playHumanTurn() {
        Model model = new Model(gameState);
        Team currentTeam = model.getTurn();
        boolean validMoveFound = false;

        while (!validMoveFound) {
            System.out.println("\nSélectionnez un anneau à déplacer (x, y):");
            System.out.println("x exemple : 0");
            int fromX = InputManager.awaitNumberInput();
            System.out.println("y exemple : 6");
            int fromY = InputManager.awaitNumberInput();

            Coordinate fromCoord = getFactory().fromDoubled(fromX, fromY);
            Token tokenAtPosition = gameState.board().get(fromCoord);

            // Vérifier que c'est un anneau de l'équipe actuelle
            if (!(tokenAtPosition instanceof Ring)) {
                System.out.println("Aucun anneau à cette position !");
                continue;
            }
            if (tokenAtPosition.getTeam() != currentTeam) {
                System.out.println("Ce n'est pas votre anneau !");
                continue;
            }

            // Afficher les mouvements disponibles
            Set<Coordinate> availableMoves = gameState.availableMoves(fromCoord);
            if (availableMoves.isEmpty()) {
                System.out.println("Aucun mouvement disponible depuis cette position !");
                continue;
            }

            System.out.println("\nMouvements disponibles :");
            for (Coordinate move : availableMoves) {
                Point p = move.to2DCoordinate();
                System.out.println("  - (" + p.x() + ", " + p.y() + ")");
            }

            // Demander la destination
            System.out.println("\nChoisissez une destination (x, y):");
            System.out.println("x exemple : 0");
            int toX = InputManager.awaitNumberInput();
            System.out.println("y exemple : 6");
            int toY = InputManager.awaitNumberInput();

            Coordinate toCoord = getFactory().fromDoubled(toX, toY);

            if (!availableMoves.contains(toCoord)) {
                System.out.println("Mouvement invalide !");
                continue;
            }

            // Exécuter le mouvement
            try {
                gameState = gameState.move(new Move(fromCoord, toCoord));
                validMoveFound = true;
            } catch (Exception e) {
                System.out.println("Erreur lors du mouvement : ");
                System.out.println(e);
            }
        }
    }

    /**
     * Gère la suppression des lignes de pions.
     *
     * @param model le modèle du jeu
     */
    private void handleLineRemoval(Model model) {
        System.out.println("Sélectionnez les lignes à supprimer (coordonnées d'un pion de la ligne):");
        // Implémentation à faire selon les besoins
    }

    /**
     * Permet au joueur de placer les anneaux initiaux sur le plateau.
     *
     * <p>Demande au joueur d'entrer les positions de 5 anneaux blancs et 5 anneaux noirs
     * alternativement. Empêche de placer deux anneaux à la même position.
     *
     * @return l'état du jeu avec les anneaux placés
     * @throws InvocationTargetException si l'invocation réfléchie échoue
     * @throws NoSuchMethodException     si le constructeur souhaité n'existe pas
     * @throws InstantiationException    si l'instantiation échoue
     * @throws IllegalAccessException    si l'accès réfléchi échoue
     */
    public IState ringsPlacement() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        int blackRings = 5;
        int whiteRings = 5;
        int x, y;
        IState currentState = getFactory().emptyState();

        while (whiteRings > 0 || blackRings > 0) {
            if (whiteRings > 0) {
                UIUtils.clearTerminal();
                System.out.println("Entrez les coordonnées du " + (6 - whiteRings) + "e anneau blanc.");
                boolean validPosition = false;
                while (!validPosition) {
                    System.out.println("x exemple : 0");
                    x = InputManager.awaitNumberInput();
                    System.out.println("y exemple : 6");
                    y = InputManager.awaitNumberInput();

                    Coordinate coord = getFactory().fromDoubled(x, y);
                    Token tokenAtPosition = currentState.board().get(coord);
                    
                    if (tokenAtPosition != null) {
                        System.out.println("⚠Cette position est déjà occupée ! Choisissez une autre.");
                        continue;
                    }
                    
                    currentState = currentState.toggleToken(coord, Ring.class, Team.WHITE);
                    whiteRings -= 1;
                    validPosition = true;
                }

                gameState = currentState;
                showPlate();
            }

            if (blackRings > 0) {
                System.out.println("Entrez les coordonnées du " + (6 - blackRings) + "e anneau noir.");
                boolean validPosition = false;
                while (!validPosition) {
                    System.out.println("x exemple : 0");
                    x = InputManager.awaitNumberInput();
                    System.out.println("y exemple : 6");
                    y = InputManager.awaitNumberInput();

                    Coordinate coord = getFactory().fromDoubled(x, y);
                    Token tokenAtPosition = currentState.board().get(coord);
                    
                    if (tokenAtPosition != null) {
                        System.out.println("⚠️  Cette position est déjà occupée ! Choisissez une autre.");
                        continue;
                    }
                    
                    currentState = currentState.toggleToken(coord, Ring.class, Team.BLACK);
                    blackRings -= 1;
                    validPosition = true;
                }

                gameState = currentState;
                showPlate();
            }
        }
        gameState = currentState;
        return currentState;
    }

    public void buildBoardFactory() {
        if (this.dimensionMode.equals(DimensionMode.CUBE)) {
            this.factory = new FactoryCube();
        }
        if (this.dimensionMode.equals(DimensionMode.DOUBLED)) {
            this.factory = new FactoryDoubled();
        }
    }

    public void showPlate() {
        System.out.print(renderBoard());
    }

    public String renderBoard() {
        Map<Point, Token> board = new HashMap<>();
        for (Map.Entry<Coordinate, Token> entry : this.gameState.board().entrySet()) {
            board.put(entry.getKey().to2DCoordinate(), entry.getValue());
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i <= 10; i++) {
            for (int j = 0; j <= 18; j++) {
                Point point = new Point(i, j);
                if (board.containsKey(point)) {
                    Token token = board.get(point);
                    if (token == null) {
                        builder.append('-');
                    } else if (token instanceof Pawn p) {
                        builder.append(p.charRepr());
                    } else if (token instanceof Ring r) {
                        builder.append(r.charRepr());
                    } else {
                        builder.append('?');
                    }
                } else {
                    builder.append(' ');
                }

                if (j < 18) {
                    builder.append(' ');
                }
            }

            builder.append(System.lineSeparator());
        }

        return builder.toString();
    }

    public enum PlayMode {
        PLAYER_VERSUS, AI_VERSUS;
    }

    public enum DimensionMode {
        CUBE, DOUBLED;
    }
}
