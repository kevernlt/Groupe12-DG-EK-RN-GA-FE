package iut.gon.sae201.g12.factory;

import iut.gon.sae201.g12.State;
import iut.gon.sae201.g12.tokens.Pawn;
import iut.gon.sae201.g12.tokens.Ring;
import iut.gon.sae201.g12.tokens.Token;
import iut.gon.sae201.g12.utils.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Factory pour créer des états de test en utilisant le modèle hexagonal Doubled.
 *
 * <p>Fournit des états préconfigurés pour les tests de jeu Othello/Reversi
 * basés sur la représentation de coordonnées doubled.
 */
public class FactoryDoubled implements IFactory {

    private final int[][] boardPoses = {{6, 12}, // on line 0, x cells go from 6 to 12
            {3, 15}, // here from 3 to 15
            {2, 16}, // ...
            {1, 17}, {0, 18}, {1, 17}, {0, 18}, {1, 17}, {2, 16}, {3, 15}, {6, 12}};

    private Map<Coordinate, Token> initializeEmptyBoard() {
        Set<CoordinateDoubled> temp = new HashSet<>();

        // Board is 11 lines (0-10)
        for (int i = 0; i <= 10; i++) {
            for (int j = boardPoses[i][0]; j <= boardPoses[i][1]; j += 2) {
                temp.add(new CoordinateDoubled(i, j));
            }
        }

        Map<Coordinate, Token> map = new HashMap<>();
        for (CoordinateDoubled c : temp) {
            map.put(new CoordinateDoubled(c.getX(), c.getY()), null);
        }
        return map;
    }

    @Override
    public IState testState() {
        Map<Coordinate, Token> board = initializeEmptyBoard();

        board.replace(new CoordinateDoubled(5, 11), new Ring(Team.BLACK));
        board.replace(new CoordinateDoubled(5, 13), new Ring(Team.BLACK));
        board.replace(new CoordinateDoubled(5, 15), new Ring(Team.WHITE));
        board.replace(new CoordinateDoubled(5, 17), new Ring(Team.WHITE));
        board.replace(new CoordinateDoubled(6, 12), new Ring(Team.WHITE));

        board.replace(new CoordinateDoubled(8, 8), new Ring(Team.BLACK));
        board.replace(new CoordinateDoubled(9, 9), new Ring(Team.BLACK));

        board.replace(new CoordinateDoubled(4, 2), new Ring(Team.BLACK));

        board.replace(new CoordinateDoubled(3, 13), new Ring(Team.WHITE));
        board.replace(new CoordinateDoubled(3, 15), new Ring(Team.WHITE));

        board.replace(new CoordinateDoubled(1, 11), new Pawn(Team.BLACK));
        board.replace(new CoordinateDoubled(3, 7), new Pawn(Team.BLACK));
        board.replace(new CoordinateDoubled(3, 11), new Pawn(Team.BLACK));
        board.replace(new CoordinateDoubled(9, 3), new Pawn(Team.BLACK));
        board.replace(new CoordinateDoubled(4, 6), new Pawn(Team.BLACK));

        board.replace(new CoordinateDoubled(1, 15), new Pawn(Team.WHITE));
        board.replace(new CoordinateDoubled(3, 9), new Pawn(Team.WHITE));
        board.replace(new CoordinateDoubled(10, 10), new Pawn(Team.WHITE));
        board.replace(new CoordinateDoubled(8, 6), new Pawn(Team.WHITE));

        return new State(
                board,
                Team.BLACK,
                new ArrayList<>()
        );

    }

    @Override
    public IState stateForBlackLineTest() {
        Map<Coordinate, Token> board = initializeEmptyBoard();

        board.replace(new CoordinateDoubled(4, 2), new Ring(Team.BLACK));
        board.replace(new CoordinateDoubled(5, 11), new Ring(Team.BLACK));
        board.replace(new CoordinateDoubled(5, 13), new Ring(Team.BLACK));
        board.replace(new CoordinateDoubled(8, 8), new Ring(Team.BLACK));
        board.replace(new CoordinateDoubled(9, 9), new Ring(Team.BLACK));

        board.replace(new CoordinateDoubled(3, 13), new Ring(Team.WHITE));
        board.replace(new CoordinateDoubled(3, 15), new Ring(Team.WHITE));
        board.replace(new CoordinateDoubled(5, 15), new Ring(Team.WHITE));
        board.replace(new CoordinateDoubled(5, 17), new Ring(Team.WHITE));
        board.replace(new CoordinateDoubled(6, 12), new Ring(Team.WHITE));

        board.replace(new CoordinateDoubled(10, 12), new Pawn(Team.BLACK));
        board.replace(new CoordinateDoubled(9, 3), new Pawn(Team.BLACK));
        board.replace(new CoordinateDoubled(8, 16), new Pawn(Team.BLACK));
        board.replace(new CoordinateDoubled(8, 14), new Pawn(Team.BLACK));
        board.replace(new CoordinateDoubled(8, 12), new Pawn(Team.BLACK));
        board.replace(new CoordinateDoubled(8, 10), new Pawn(Team.BLACK));
        board.replace(new CoordinateDoubled(7, 1), new Pawn(Team.BLACK));
        board.replace(new CoordinateDoubled(7, 11), new Pawn(Team.BLACK));
        board.replace(new CoordinateDoubled(6, 0), new Pawn(Team.BLACK));
        board.replace(new CoordinateDoubled(6, 2), new Pawn(Team.BLACK));
        board.replace(new CoordinateDoubled(6, 4), new Pawn(Team.BLACK));
        board.replace(new CoordinateDoubled(6, 6), new Pawn(Team.BLACK));
        board.replace(new CoordinateDoubled(6, 16), new Pawn(Team.BLACK));
        board.replace(new CoordinateDoubled(4, 18), new Pawn(Team.BLACK));
        board.replace(new CoordinateDoubled(4, 16), new Pawn(Team.BLACK));
        board.replace(new CoordinateDoubled(4, 14), new Pawn(Team.BLACK));
        board.replace(new CoordinateDoubled(4, 6), new Pawn(Team.BLACK));
        board.replace(new CoordinateDoubled(4, 8), new Pawn(Team.BLACK));
        board.replace(new CoordinateDoubled(3, 7), new Pawn(Team.BLACK));
        board.replace(new CoordinateDoubled(3, 11), new Pawn(Team.BLACK));
        board.replace(new CoordinateDoubled(2, 6), new Pawn(Team.BLACK));
        board.replace(new CoordinateDoubled(2, 10), new Pawn(Team.BLACK));
        board.replace(new CoordinateDoubled(1, 5), new Pawn(Team.BLACK));
        board.replace(new CoordinateDoubled(1, 9), new Pawn(Team.BLACK));
        board.replace(new CoordinateDoubled(1, 11), new Pawn(Team.BLACK));
        board.replace(new CoordinateDoubled(0, 8), new Pawn(Team.BLACK));

        board.replace(new CoordinateDoubled(1, 15), new Pawn(Team.WHITE));
        board.replace(new CoordinateDoubled(3, 9), new Pawn(Team.WHITE));
        board.replace(new CoordinateDoubled(8, 6), new Pawn(Team.WHITE));
        board.replace(new CoordinateDoubled(10, 10), new Pawn(Team.WHITE));
        return new State(
                board,
                Team.BLACK,
                new ArrayList<>()
        );

    }

    @Override
    public IState stateForWhiteLineTest() {
        Map<Coordinate, Token> board = initializeEmptyBoard();

        board.replace(new CoordinateDoubled(4, 2), new Ring(Team.BLACK));
        board.replace(new CoordinateDoubled(5, 11), new Ring(Team.BLACK));
        board.replace(new CoordinateDoubled(5, 13), new Ring(Team.BLACK));
        board.replace(new CoordinateDoubled(8, 8), new Ring(Team.BLACK));
        board.replace(new CoordinateDoubled(9, 9), new Ring(Team.BLACK));

        board.replace(new CoordinateDoubled(3, 13), new Ring(Team.WHITE));
        board.replace(new CoordinateDoubled(3, 15), new Ring(Team.WHITE));
        board.replace(new CoordinateDoubled(5, 15), new Ring(Team.WHITE));
        board.replace(new CoordinateDoubled(5, 17), new Ring(Team.WHITE));
        board.replace(new CoordinateDoubled(6, 12), new Ring(Team.WHITE));

        board.replace(new CoordinateDoubled(10, 12), new Pawn(Team.BLACK));
        board.replace(new CoordinateDoubled(9, 3), new Pawn(Team.BLACK));
        board.replace(new CoordinateDoubled(8, 16), new Pawn(Team.BLACK));
        board.replace(new CoordinateDoubled(8, 14), new Pawn(Team.BLACK));
        board.replace(new CoordinateDoubled(8, 12), new Pawn(Team.BLACK));
        board.replace(new CoordinateDoubled(8, 10), new Pawn(Team.BLACK));
        board.replace(new CoordinateDoubled(7, 1), new Pawn(Team.BLACK));
        board.replace(new CoordinateDoubled(7, 11), new Pawn(Team.BLACK));
        board.replace(new CoordinateDoubled(5, 9), new Pawn(Team.BLACK));
        board.replace(new CoordinateDoubled(6, 0), new Pawn(Team.BLACK));
        board.replace(new CoordinateDoubled(6, 2), new Pawn(Team.BLACK));
        board.replace(new CoordinateDoubled(6, 4), new Pawn(Team.BLACK));
        board.replace(new CoordinateDoubled(6, 6), new Pawn(Team.BLACK));
        board.replace(new CoordinateDoubled(6, 8), new Pawn(Team.BLACK));
        board.replace(new CoordinateDoubled(6, 16), new Pawn(Team.BLACK));
        board.replace(new CoordinateDoubled(4, 18), new Pawn(Team.BLACK));
        board.replace(new CoordinateDoubled(4, 16), new Pawn(Team.BLACK));
        board.replace(new CoordinateDoubled(4, 14), new Pawn(Team.BLACK));
        board.replace(new CoordinateDoubled(4, 6), new Pawn(Team.BLACK));
        board.replace(new CoordinateDoubled(4, 8), new Pawn(Team.BLACK));
        board.replace(new CoordinateDoubled(3, 7), new Pawn(Team.BLACK));
        board.replace(new CoordinateDoubled(3, 11), new Pawn(Team.BLACK));
        board.replace(new CoordinateDoubled(2, 6), new Pawn(Team.BLACK));
        board.replace(new CoordinateDoubled(2, 10), new Pawn(Team.BLACK));
        board.replace(new CoordinateDoubled(1, 5), new Pawn(Team.BLACK));
        board.replace(new CoordinateDoubled(1, 9), new Pawn(Team.BLACK));
        board.replace(new CoordinateDoubled(1, 11), new Pawn(Team.BLACK));
        board.replace(new CoordinateDoubled(0, 8), new Pawn(Team.BLACK));
        // pfiou

        board.replace(new CoordinateDoubled(1, 15), new Pawn(Team.WHITE));
        board.replace(new CoordinateDoubled(3, 9), new Pawn(Team.WHITE));
        board.replace(new CoordinateDoubled(8, 6), new Pawn(Team.WHITE));
        board.replace(new CoordinateDoubled(10, 10), new Pawn(Team.WHITE));

        return new State(
                board,
                Team.BLACK,
                IState.getPawnsLines(board)
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

        board.replace(new CoordinateDoubled(4, 2), new Ring(Team.BLACK));
        board.replace(new CoordinateDoubled(5, 11), new Ring(Team.BLACK));
        board.replace(new CoordinateDoubled(5, 13), new Ring(Team.BLACK));
        board.replace(new CoordinateDoubled(8, 8), new Ring(Team.BLACK));
        board.replace(new CoordinateDoubled(9, 9), new Ring(Team.BLACK));

        board.replace(new CoordinateDoubled(2, 16), new Ring(Team.WHITE));
        board.replace(new CoordinateDoubled(2, 12), new Ring(Team.WHITE));
        board.replace(new CoordinateDoubled(5, 15), new Ring(Team.WHITE));
        board.replace(new CoordinateDoubled(5, 17), new Ring(Team.WHITE));
        board.replace(new CoordinateDoubled(6, 12), new Ring(Team.WHITE));

        board.replace(new CoordinateDoubled(4, 12), new Pawn(Team.WHITE));
        board.replace(new CoordinateDoubled(4, 14), new Pawn(Team.WHITE));
        board.replace(new CoordinateDoubled(4, 8), new Pawn(Team.WHITE));
        board.replace(new CoordinateDoubled(4, 6), new Pawn(Team.WHITE));
        board.replace(new CoordinateDoubled(4,10), new Pawn(Team.WHITE));

        board.replace(new CoordinateDoubled(3, 5), new Pawn(Team.BLACK));
        board.replace(new CoordinateDoubled(3, 7), new Pawn(Team.BLACK));
        board.replace(new CoordinateDoubled(3, 11), new Pawn(Team.BLACK));
        board.replace(new CoordinateDoubled(3, 13), new Pawn(Team.BLACK));
        board.replace(new CoordinateDoubled(3,9), new Pawn(Team.BLACK));
        return new State(
                board,
                Team.BLACK,
                IState.getPawnsLines(board)
        );

    }
}