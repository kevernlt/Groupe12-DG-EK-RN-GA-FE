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
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public void buildBoardFactory() {
        if (this.dimensionMode.equals(DimensionMode.CUBE)) {
            this.factory = new FactoryCube();
        }
        if (this.dimensionMode.equals(DimensionMode.DOUBLED)) {
            this.factory = new FactoryDoubled();
        }
    }

    public void showPlate() {

        this.boardMode = Mode.FLAT;

        Map<Point, Token> board2 = new HashMap<>();

        for (Map.Entry<Coordinate, Token> entry : this.gameState.board().entrySet()) {

            board2.put(entry.getKey().to2DCoordinate(), entry.getValue());
        }

        if (this.boardMode == Mode.POINTY) {
            for (int i = 0; i <= 10; i++) {
                for (int j = 0; j <= 18; j++) {
                    if (board2.containsKey(new Point(i, j))) {
                        if (board2.get(new Point(i, j)) == null) {
                            System.out.print("-");
                        }
                        if (board2.get(new Point(i, j)) instanceof Pawn p) {
                            System.out.print(p.charRepr());
                        }
                        if (board2.get(new Point(i, j)) instanceof Ring r) {
                            System.out.print(r.charRepr());
                        }
                    } else {
                        System.out.print(" ");
                    }
                }
                System.out.println("");
            }
        } else if (this.boardMode == Mode.FLAT) {

            int[] suiteY = {3, 2, 1, 0, 2, 1, 0, 2, 1, 3, 2, 4, 3, 5, 7, 6, 8, 10, 12};
            int[] suitefinY = {6, 8, 10, 12, 11, 13, 15, 14, 16, 15, 17, 16, 18, 17, 16, 18, 17, 16, 15};

            int[] suiteX = {1, 2, 3, 4, 4, 5, 6, 6, 7, 7, 8, 8, 9, 9, 9, 10, 10, 10, 10};
            int[] suitefinX = {0, 0, 0, 0, 1, 1, 1, 2, 2, 3, 3, 4, 4, 5, 6, 6, 7, 8, 9};

            int[] nbcases = {2, 3, 4, 5, 4, 5, 6, 5, 6, 5, 6, 5, 6, 5, 4, 5, 4, 3, 2};
            int[] espaces = {4, 3, 2, 1, 2, 1, 0, 1, 0, 1, 0, 1, 0, 1, 2, 1, 2, 3, 4};

            int y;
            int x;

            for (int i = 0; i <= 18; i++) {

                for (int t = 0; t < espaces[i] +1; t++) {
                    System.out.print("    ");
                }

                switch(i){
                    case 3 :
                        System.out.print("    ");
                        break;
                    case 4 :
                        System.out.print("\\   ");
                        break;
                    default:
                        System.out.print(" ___");

                }




                for (int j = 0; j <= nbcases[i]-1; j++) {

                    x = suiteX[i] - j;
                    y = suiteY[i] + j * 3;

                    System.out.print("/ ");
                    if (board2.containsKey(new Point(x, y))) {
                        if (board2.get(new Point(x, y)) == null) {
                            System.out.print("-");
                        }
                        if (board2.get(new Point(x, y)) instanceof Pawn p) {
                            System.out.print(p.charRepr());
                        }
                        if (board2.get(new Point(x, y)) instanceof Ring r) {
                            System.out.print(r.charRepr());
                        }
                    } else {
                        System.out.print("  ");
                    }
                    System.out.print(" \\___");
                }

                System.out.println("");
            }
        }
    }

    private void printFlatCell(Map<Point, Token> board2, int i, int j) {
        Point p = new Point(i, j);
        if (board2.containsKey(p)) {
            Token token = board2.get(p);
            if (token == null) System.out.print("-");
            else if (token instanceof Pawn pw) System.out.print(pw.charRepr());
            else if (token instanceof Ring r) System.out.print(r.charRepr());
        } else {
            System.out.print(" ");
        }
    }

    public enum PlayMode {
        PLAYER_VERSUS, AI_VERSUS;
    }

    public enum DimensionMode {
        CUBE, DOUBLED;
    }

}
