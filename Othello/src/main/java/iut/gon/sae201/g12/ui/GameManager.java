package iut.gon.sae201.g12.ui;

import iut.gon.sae201.g12.factory.FactoryCube;
import iut.gon.sae201.g12.factory.FactoryDoubled;
import iut.gon.sae201.g12.tokens.Pawn;
import iut.gon.sae201.g12.tokens.Ring;
import iut.gon.sae201.g12.tokens.Token;
import iut.gon.sae201.g12.utils.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * Gestionnaire du jeu Othello.
 *
 * <p>Encapsule la configuration du jeu (mode de plateau, mode de jeu, système
 * de coordonnées) et fournit les méthodes d'affichage du plateau.
 */
public class GameManager {
    /**
     * Énumération des modes de jeu.
     */
    public enum PlayMode {
        PLAYER_VERSUS,
        AI_VERSUS
    }

    /**
     * Énumération des systèmes de coordonnées disponibles.
     */
    public enum DimensionMode {
        CUBE,
        DOUBLED
    }

    /** La factory utilisée pour créer les états du jeu */
    @Getter
    IFactory factory;
    /** L'état actuel du jeu */
    @Setter
    IState gameState;
    /** Le mode de jeu (joueur vs joueur ou joueur vs IA) */
    @Setter
    PlayMode playMode;
    /** Le mode d'orientation du plateau hexagonal */
    @Setter
    Mode boardMode;
    /** Le système de coordonnées */
    @Setter
    DimensionMode dimensionMode;

    /**
     * Construit la factory appropriée selon le mode de dimension.
     */
    public void buildBoardFactory() {
        if (this.dimensionMode.equals(DimensionMode.CUBE)) {
            this.factory = new FactoryCube();
        }
        if (this.dimensionMode.equals(DimensionMode.DOUBLED)) {
            this.factory = new FactoryDoubled();
        }
    }

    /**
     * Affiche le plateau de jeu dans le terminal.
     */
    public void showPlate() {
        System.out.print(renderBoard());
    }

    /**
     * Génère une représentation textuelle du plateau de jeu.
     *
     * @return une chaîne représentant le plateau avec les pièces
     */
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
        PLAYER_VERSUS,
        AI_VERSUS;
    }

    public enum DimensionMode {
        CUBE,
        DOUBLED;
    }

}
