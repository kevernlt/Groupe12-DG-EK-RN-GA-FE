package iut.gon.sae201.g12.factory;

import iut.gon.sae201.g12.State;
import iut.gon.sae201.g12.tokens.Pawn;
import iut.gon.sae201.g12.tokens.Ring;
import iut.gon.sae201.g12.tokens.Token;
import iut.gon.sae201.g12.utils.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Factory pour créer des états de test en utilisant le modèle hexagonal Cube.
 *
 * <p>Fournit des états préconfigurés pour les tests de jeu Othello/Reversi
 * basés sur la représentation de coordonnées cube.
 */
public class FactoryCube implements IFactory {

    private final int[][] boardPoses = {{6, 12}, // on line 0, x cells go from 6 to 12
            {3, 15}, // here from 3 to 15
            {2, 16}, // ...
            {1, 17}, {0, 18}, {1, 17}, {0, 18}, {1, 17}, {2, 16}, {3, 15}, {6, 12}};

    /**
     * It was quite log to convert all values manually
     * r = x - 5
     * q = (y - x - 4) / 2
     * s = - q - r
     */
    private CoordinateCube fromDoubled(int x, int y) {
        int r = x - 5;
        int q = (y - x - 4) / 2;
        int s = -q - r;
        return new CoordinateCube(q, r, s);
    }

    private Map<Coordinate, Token> initializeEmptyBoard() {
        Map<Coordinate, Token> temp = new HashMap<>();

        // Board is 11 lines (0-10)
        for (int i = 0; i <= 10; i++) {
            for (int j = boardPoses[i][0]; j <= boardPoses[i][1]; j += 2) {
                temp.put(new CoordinateDoubled(i, j), null);
            }
        }

        return temp;
    }


    @Override
    public IState testState() {
        Map<Coordinate, Token> board = initializeEmptyBoard();

        board.replace(fromDoubled(5, 11), new Ring(Team.BLACK));
        board.replace(fromDoubled(5, 13), new Ring(Team.BLACK));
        board.replace(fromDoubled(5, 15), new Ring(Team.WHITE));
        board.replace(fromDoubled(5, 17), new Ring(Team.WHITE));
        board.replace(fromDoubled(6, 12), new Ring(Team.WHITE));

        board.replace(fromDoubled(8, 8), new Ring(Team.BLACK));
        board.replace(fromDoubled(9, 9), new Ring(Team.BLACK));

        board.replace(fromDoubled(4, 2), new Ring(Team.BLACK));

        board.replace(fromDoubled(3, 13), new Ring(Team.WHITE));
        board.replace(fromDoubled(3, 15), new Ring(Team.WHITE));

        board.replace(fromDoubled(1, 11), new Pawn(Team.BLACK));
        board.replace(fromDoubled(3, 7), new Pawn(Team.BLACK));
        board.replace(fromDoubled(3, 11), new Pawn(Team.BLACK));
        board.replace(fromDoubled(9, 3), new Pawn(Team.BLACK));
        board.replace(fromDoubled(4, 6), new Pawn(Team.BLACK));

        board.replace(fromDoubled(1, 15), new Pawn(Team.WHITE));
        board.replace(fromDoubled(3, 9), new Pawn(Team.WHITE));
        board.replace(fromDoubled(10, 10), new Pawn(Team.WHITE));
        board.replace(fromDoubled(8, 6), new Pawn(Team.WHITE));

        return new State(
                board,
                Team.BLACK,
                new ArrayList<>()
        );
    }

    @Override
    public IState stateForBlackLineTest() {
        Map<Coordinate, Token> board = initializeEmptyBoard();

        board.replace(fromDoubled(4, 2), new Ring(Team.BLACK));
        board.replace(fromDoubled(5, 11), new Ring(Team.BLACK));
        board.replace(fromDoubled(5, 13), new Ring(Team.BLACK));
        board.replace(fromDoubled(8, 8), new Ring(Team.BLACK));
        board.replace(fromDoubled(9, 9), new Ring(Team.BLACK));

        board.replace(fromDoubled(3, 13), new Ring(Team.WHITE));
        board.replace(fromDoubled(3, 15), new Ring(Team.WHITE));
        board.replace(fromDoubled(5, 15), new Ring(Team.WHITE));
        board.replace(fromDoubled(5, 17), new Ring(Team.WHITE));
        board.replace(fromDoubled(6, 12), new Ring(Team.WHITE));

        board.replace(fromDoubled(10, 12), new Pawn(Team.BLACK));
        board.replace(fromDoubled(9, 3), new Pawn(Team.BLACK));
        board.replace(fromDoubled(8, 16), new Pawn(Team.BLACK));
        board.replace(fromDoubled(8, 14), new Pawn(Team.BLACK));
        board.replace(fromDoubled(8, 12), new Pawn(Team.BLACK));
        board.replace(fromDoubled(8, 10), new Pawn(Team.BLACK));
        board.replace(fromDoubled(7, 1), new Pawn(Team.BLACK));
        board.replace(fromDoubled(7, 11), new Pawn(Team.BLACK));
        board.replace(fromDoubled(6, 0), new Pawn(Team.BLACK));
        board.replace(fromDoubled(6, 2), new Pawn(Team.BLACK));
        board.replace(fromDoubled(6, 4), new Pawn(Team.BLACK));
        board.replace(fromDoubled(6, 6), new Pawn(Team.BLACK));
        board.replace(fromDoubled(6, 16), new Pawn(Team.BLACK));
        board.replace(fromDoubled(4, 18), new Pawn(Team.BLACK));
        board.replace(fromDoubled(4, 16), new Pawn(Team.BLACK));
        board.replace(fromDoubled(4, 14), new Pawn(Team.BLACK));
        board.replace(fromDoubled(4, 6), new Pawn(Team.BLACK));
        board.replace(fromDoubled(4, 8), new Pawn(Team.BLACK));
        board.replace(fromDoubled(3, 7), new Pawn(Team.BLACK));
        board.replace(fromDoubled(3, 11), new Pawn(Team.BLACK));
        board.replace(fromDoubled(2, 6), new Pawn(Team.BLACK));
        board.replace(fromDoubled(2, 10), new Pawn(Team.BLACK));
        board.replace(fromDoubled(1, 5), new Pawn(Team.BLACK));
        board.replace(fromDoubled(1, 9), new Pawn(Team.BLACK));
        board.replace(fromDoubled(1, 11), new Pawn(Team.BLACK));
        board.replace(fromDoubled(0, 8), new Pawn(Team.BLACK));

        board.replace(fromDoubled(1, 15), new Pawn(Team.WHITE));
        board.replace(fromDoubled(3, 9), new Pawn(Team.WHITE));
        board.replace(fromDoubled(8, 6), new Pawn(Team.WHITE));
        board.replace(fromDoubled(10, 10), new Pawn(Team.WHITE));
        return new State(
                board,
                Team.BLACK,
                new ArrayList<>()
        );
    }

    @Override
    public IState stateForWhiteLineTest() {
        Map<Coordinate, Token> board = initializeEmptyBoard();

        board.replace(fromDoubled(4, 2), new Ring(Team.BLACK));
        board.replace(fromDoubled(5, 11), new Ring(Team.BLACK));
        board.replace(fromDoubled(5, 13), new Ring(Team.BLACK));
        board.replace(fromDoubled(8, 8), new Ring(Team.BLACK));
        board.replace(fromDoubled(9, 9), new Ring(Team.BLACK));

        board.replace(fromDoubled(3, 13), new Ring(Team.WHITE));
        board.replace(fromDoubled(3, 15), new Ring(Team.WHITE));
        board.replace(fromDoubled(5, 15), new Ring(Team.WHITE));
        board.replace(fromDoubled(5, 17), new Ring(Team.WHITE));
        board.replace(fromDoubled(6, 12), new Ring(Team.WHITE));

        board.replace(fromDoubled(10, 12), new Pawn(Team.BLACK));
        board.replace(fromDoubled(9, 3), new Pawn(Team.BLACK));
        board.replace(fromDoubled(8, 16), new Pawn(Team.BLACK));
        board.replace(fromDoubled(8, 14), new Pawn(Team.BLACK));
        board.replace(fromDoubled(8, 12), new Pawn(Team.BLACK));
        board.replace(fromDoubled(8, 10), new Pawn(Team.BLACK));
        board.replace(fromDoubled(7, 1), new Pawn(Team.BLACK));
        board.replace(fromDoubled(7, 11), new Pawn(Team.BLACK));
        board.replace(fromDoubled(5, 9), new Pawn(Team.BLACK));
        board.replace(fromDoubled(6, 0), new Pawn(Team.BLACK));
        board.replace(fromDoubled(6, 2), new Pawn(Team.BLACK));
        board.replace(fromDoubled(6, 4), new Pawn(Team.BLACK));
        board.replace(fromDoubled(6, 6), new Pawn(Team.BLACK));
        board.replace(fromDoubled(6, 8), new Pawn(Team.BLACK));
        board.replace(fromDoubled(6, 16), new Pawn(Team.BLACK));
        board.replace(fromDoubled(4, 18), new Pawn(Team.BLACK));
        board.replace(fromDoubled(4, 16), new Pawn(Team.BLACK));
        board.replace(fromDoubled(4, 14), new Pawn(Team.BLACK));
        board.replace(fromDoubled(4, 6), new Pawn(Team.BLACK));
        board.replace(fromDoubled(4, 8), new Pawn(Team.BLACK));
        board.replace(fromDoubled(3, 7), new Pawn(Team.BLACK));
        board.replace(fromDoubled(3, 11), new Pawn(Team.BLACK));
        board.replace(fromDoubled(2, 6), new Pawn(Team.BLACK));
        board.replace(fromDoubled(2, 10), new Pawn(Team.BLACK));
        board.replace(fromDoubled(1, 5), new Pawn(Team.BLACK));
        board.replace(fromDoubled(1, 9), new Pawn(Team.BLACK));
        board.replace(fromDoubled(1, 11), new Pawn(Team.BLACK));
        board.replace(fromDoubled(0, 8), new Pawn(Team.BLACK));

        board.replace(fromDoubled(1, 15), new Pawn(Team.WHITE));
        board.replace(fromDoubled(3, 9), new Pawn(Team.WHITE));
        board.replace(fromDoubled(8, 6), new Pawn(Team.WHITE));
        board.replace(fromDoubled(10, 10), new Pawn(Team.WHITE));

        return new State(
                board,
                Team.BLACK,
                new ArrayList<>()
        );
    }

    @Override
    public IState emptyState() {
        return new State(
                initializeEmptyBoard(),
                Team.BLACK,
                new ArrayList<>()
        );
    }

    @Override
    public IState doubleLineStateTest() {
        Map<Coordinate, Token> board = initializeEmptyBoard();

        board.replace(fromDoubled(4, 2), new Ring(Team.BLACK));
        board.replace(fromDoubled(5, 11), new Ring(Team.BLACK));
        board.replace(fromDoubled(5, 13), new Ring(Team.BLACK));
        board.replace(fromDoubled(8, 8), new Ring(Team.BLACK));
        board.replace(fromDoubled(9, 9), new Ring(Team.BLACK));

        board.replace(fromDoubled(2, 16), new Ring(Team.WHITE));
        board.replace(fromDoubled(2, 12), new Ring(Team.WHITE));
        board.replace(fromDoubled(5, 15), new Ring(Team.WHITE));
        board.replace(fromDoubled(5, 17), new Ring(Team.WHITE));
        board.replace(fromDoubled(6, 12), new Ring(Team.WHITE));

        board.replace(fromDoubled(4, 12), new Pawn(Team.WHITE));
        board.replace(fromDoubled(4, 14), new Pawn(Team.WHITE));
        board.replace(fromDoubled(4, 8), new Pawn(Team.WHITE));
        board.replace(fromDoubled(4, 6), new Pawn(Team.WHITE));
        board.replace(fromDoubled(3, 9), new Pawn(Team.WHITE));

        board.replace(fromDoubled(3, 5), new Pawn(Team.BLACK));
        board.replace(fromDoubled(3, 7), new Pawn(Team.BLACK));
        board.replace(fromDoubled(3, 11), new Pawn(Team.BLACK));
        board.replace(fromDoubled(3, 13), new Pawn(Team.BLACK));
        board.replace(fromDoubled(4, 10), new Pawn(Team.BLACK));
        return new State(
                board,
                Team.BLACK,
                new ArrayList<>()
        );
    }
}