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


        Map<Point, Token> board2 = new HashMap<>();

        for (Map.Entry<Coordinate, Token> entry : this.gameState.board().entrySet()) {

            board2.put(entry.getKey().to2DCoordinate(), entry.getValue());
        }
        System.out.print("\u001B[37m");
        if (this.boardMode == Mode.POINTY) {

            System.out.println("             / \\ / \\ / \\ / \\ ");

            for (int i = 0; i <= 10; i++) {

                if (i == 3 || i == 5 || i == 7) {
                    System.out.print(" ");
                } else if (i == 2 || i == 8) {
                    System.out.print("  ");
                } else if (i == 1 || i == 9) {
                    System.out.print("   ");
                } else if (i == 0 || i == 10) {
                    System.out.print("      ");
                }


                for (int j = 0; j <= 18; j++) {

                    if (board2.containsKey(new Point(i, j))) {

                        System.out.print("| ");

                        if (board2.get(new Point(i, j)) == null) {
                            System.out.print("-");
                        }
                        if (board2.get(new Point(i, j)) instanceof Pawn p) {
                            System.out.print("" + p.charRepr());
                        }
                        if (board2.get(new Point(i, j)) instanceof Ring r) {
                            System.out.print("" + r.charRepr());
                        }


                    } else {
                        System.out.print(" ");
                    }


                }
                System.out.println("");

                if (i == 3 || i == 5 || i == 7) {
                    System.out.print(" ");
                } else if (i == 2 || i == 8) {
                    System.out.print("   ");
                } else if (i == 1 || i == 9) {
                    System.out.print("     ");
                } else if (i == 0 || i == 10) {
                    System.out.print("       ");
                }


                if (i == 0) {
                    System.out.print("/ \\ / \\ / \\ / \\ / \\ / \\ / \\");
                } else if (i == 1) {
                    System.out.print("/ \\ / \\ / \\ / \\ / \\ / \\ / \\ / \\");
                } else if (i == 2) {
                    System.out.print("/ \\ / \\ / \\ / \\ / \\ / \\ / \\ / \\ / \\");
                } else if (i == 3 || i == 5) {
                    System.out.print("/ \\ / \\ / \\ / \\ / \\ / \\ / \\ / \\ / \\ / \\");
                } else if (i == 4 || i == 6) {
                    System.out.print(" \\ / \\ / \\ / \\ / \\ / \\ / \\ / \\ / \\ / \\ /");
                } else if (i == 7) {
                    System.out.print("  \\ / \\ / \\ / \\ / \\ / \\ / \\ / \\ / \\ /");
                } else if (i == 8) {
                    System.out.print("  \\ / \\ / \\ / \\ / \\ / \\ / \\ / \\ /");
                } else if (i == 9) {
                    System.out.print("  \\ / \\ / \\ / \\ / \\ / \\ / \\ /");
                } else {
                    System.out.print("      \\ / \\ / \\ / \\ / ");
                }


                System.out.println("");
            }
        } else if (this.boardMode == Mode.FLAT) {

            int[] suiteY = {3, 2, 1, 0, 2, 1, 0, 2, 1, 3, 2, 4, 3, 5, 7, 6, 8, 10, 12};


            int[] suiteX = {1, 2, 3, 4, 4, 5, 6, 6, 7, 7, 8, 8, 9, 9, 9, 10, 10, 10, 10};


            int[] nbcases = {2, 3, 4, 5, 4, 5, 6, 5, 6, 5, 6, 5, 6, 5, 4, 5, 4, 3, 2};
            int[] espaces = {4, 3, 2, 1, 2, 1, 0, 1, 0, 1, 0, 1, 0, 1, 2, 1, 2, 3, 4};

            int y;
            int x;

            System.out.print("                         ___     ___\n");

            for (int i = 0; i <= 18; i++) {

                for (int t = 0; t < espaces[i] + 1; t++) {
                    System.out.print("    ");
                }

                if (i == 3 || i == 15) {

                    System.out.print("    ");

                } else if (i == 4) {

                    System.out.print("\\___");

                } else if (i > 5 && i < 14) {
                    if (i % 2 == 1) {
                        System.out.print("\\___");
                    } else {
                        System.out.print("    ");
                    }
                } else if (i > 13) {

                    System.out.print("\\___");
                } else {
                    System.out.print(" ___");
                }


                for (int j = 0; j <= nbcases[i] - 1; j++) {

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

                    if (j == nbcases[i] - 1) {

                        if (i == 3 || i == 15) {
                            System.out.print(" \\");
                        } else if (i == 4) {

                            System.out.print(" \\___/");

                        } else if (i > 5 && i < 14) {
                            if (i % 2 == 1) {
                                System.out.print(" \\___/");
                            } else {
                                System.out.print(" \\");
                            }
                        } else if (i > 13) {

                            System.out.print(" \\___/");
                        } else {
                            System.out.print(" \\___");
                        }

                    } else {
                        System.out.print(" \\___");
                    }
                }

                System.out.println("");
            }
            System.out.print("                        \\___/   \\___/\n");
        }
        System.out.print("\u001B[0m");

    }

    public void showPlateWithCoo() {
        Map<Point, Token> board2 = new HashMap<>();

        for (Map.Entry<Coordinate, Token> entry : this.gameState.board().entrySet()) {

            board2.put(entry.getKey().to2DCoordinate(), entry.getValue());
        }
        System.out.print("\u001B[35m");

        if (this.boardMode == Mode.POINTY) {

            System.out.println("             / \\ / \\ /  \\ /  \\ ");

            for (int i = 0; i <= 10; i++) {

                if      (i == 3 || i == 5 || i == 7) System.out.print("  ");
                else if (i == 2 || i == 8)            System.out.print("    ");
                else if (i == 1 || i == 9)            System.out.print("      ");
                else if (i == 0 || i == 10)           System.out.print("            ");

                for (int j = 0; j <= 18; j++) {
                    if (board2.containsKey(new Point(i, j))) {
                        System.out.printf("|%d,%d", i, j); // affiche "| i,j "
                    } else {
                        System.out.print("");
                    }
                }
                System.out.println("");

                if      (i == 3 || i == 5 || i == 7) System.out.print(" ");
                else if (i == 2 || i == 8)            System.out.print("   ");
                else if (i == 1 || i == 9)            System.out.print("     ");
                else if (i == 0 || i == 10)           System.out.print("       ");

                if      (i == 0)           System.out.print("/ \\ / \\ / \\ / \\ /  \\ /  \\ /  \\");
                else if (i == 1)           System.out.print("/ \\ / \\ / \\ / \\ /  \\ /  \\ /  \\ /  \\");
                else if (i == 2)           System.out.print("/ \\ / \\ / \\ / \\ / \\ /  \\ /  \\ /  \\ /  \\");
                else if (i == 3 || i == 5) System.out.print("/ \\ / \\ / \\ / \\ / \\ /  \\ /  \\ /  \\ /  \\ /  \\");
                else if (i == 4 || i == 6) System.out.print(" \\ / \\ / \\ / \\ / \\ / \\  / \\  / \\  / \\  / \\  /");
                else if (i == 7)           System.out.print("  \\ / \\ / \\ / \\ / \\ / \\  / \\  / \\  / \\  /");
                else if (i == 8)           System.out.print("  \\ / \\ / \\ / \\ / \\  / \\  / \\  / \\  /");
                else if (i == 9)           System.out.print("  \\ / \\ / \\ / \\ / \\  / \\  / \\  /");
                else                       System.out.print("      \\  / \\  / \\   / \\   / ");

                System.out.println("");
            }

        } else if (this.boardMode == Mode.FLAT) {

            int[] suiteY = {3, 2, 1, 0, 2, 1, 0, 2, 1, 3, 2, 4, 3, 5, 7, 6, 8, 10, 12};
            int[] suiteX = {1, 2, 3, 4, 4, 5, 6, 6, 7, 7, 8, 8, 9, 9, 9, 10, 10, 10, 10};
            int[] nbcases = {2, 3, 4, 5, 4, 5, 6, 5, 6, 5, 6, 5, 6, 5, 4, 5, 4, 3, 2};
            int[] espaces = {4, 3, 2, 1, 2, 1, 0, 1, 0, 1, 0, 1, 0, 1, 2, 1, 2, 3, 4};

            System.out.print("                                   ______       ______\n");

            for (int i = 0; i <= 18; i++) {

                for (int t = 0; t < espaces[i] + 1; t++) System.out.print("    ");

                if (i < 3) System.out.print("    ");

                if (i == 17 || i == 1) {
                    System.out.print("  ");
                } else if (i == 18 || i == 0) {
                    System.out.print("    ");
                }

                if (i == 3 || i == 15) System.out.print("       ");
                else if (i == 4) System.out.print("   \\_____");
                else if (i > 5 && i < 14) System.out.print(i % 2 == 1 ? "\\_____" : "    ");
                else if (i > 13) System.out.print("   \\_____");

                else System.out.print(" _____");

                for (int j = 0; j <= nbcases[i] - 1; j++) {
                    int x = suiteX[i] - j;
                    int y = suiteY[i] + j * 3;

                    if (board2.containsKey(new Point(x, y))) {
                        System.out.printf("/%2d,%-2d", x, y); // affiche "/ x,y "
                    } else {
                        System.out.print("/   ");
                    }

                    if (j == nbcases[i] - 1) {
                        if (i == 3 || i == 15) System.out.print(" \\");
                        else if (i == 4) System.out.print(" \\_____/");
                        else if (i > 5 && i < 14) System.out.print(i % 2 == 1 ? " \\_____/" : " \\");
                        else if (i > 13) System.out.print(" \\_____/");
                        else System.out.print(" \\_____");
                    } else {
                        System.out.print(" \\_____");
                    }
                }
                System.out.println("");
            }
            System.out.print("                                 \\______/     \\______/\n");
        }

        System.out.print("\u001B[0m");
    }

    public enum PlayMode {
        PLAYER_VERSUS, AI_VERSUS;
    }

    public enum DimensionMode {
        CUBE, DOUBLED;
    }

}
