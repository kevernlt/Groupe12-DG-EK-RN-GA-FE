package iut.gon.sae201.g12.factory;

import iut.gon.sae201.g12.State;
import iut.gon.sae201.g12.tokens.Pawn;
import iut.gon.sae201.g12.tokens.Ring;
import iut.gon.sae201.g12.tokens.Token;
import iut.gon.sae201.g12.utils.*;

import java.util.*;
import java.util.stream.Collectors;

public class FactoryDoubled implements IFactory {

    private final int[][] boardPoses = {{6, 12}, // on line 0, x cells go from 6 to 12
            {3, 15}, // here from 3 to 15
            {2, 16}, // ...
            {1, 17}, {0, 18}, {1, 17}, {0, 18}, {1, 17}, {2, 16}, {3, 15}, {6, 12}};

    private Map<Coordinate, Token> initializeEmptyBoard() {
        Set<CoordinateDoubled> temp = new HashSet<>();

        // Board is 11 lines (0-10)
        for (int i = 0; i <= 10; i++) {
            for (int j = boardPoses[i][0]; j <= boardPoses[i][1]; j+=2) {
                temp.add(new CoordinateDoubled(i, j));
            }
        }

        return temp.stream()
                .map(c ->
                        new CoordinateDoubled(c.getX(), c.getY())
                )
                .collect(
                        Collectors.toMap(c -> c, _var -> null)
                );
    }

    @Override
    public IState testState() {
        Map<Coordinate, Token> board = initializeEmptyBoard();

        board.replace(new CoordinateDoubled(5,9 ), new Pawn(Team.BLACK));
        board.replace(new CoordinateDoubled(6,9 ), new Ring(Team.BLACK));
        board.replace(new CoordinateDoubled(7,9 ), new Ring(Team.BLACK));
        board.replace(new CoordinateDoubled(8,9 ), new Ring(Team.WHITE));
        board.replace(new CoordinateDoubled(9,9 ), new Ring(Team.WHITE));
        return null;
    }

    @Override
    public IState stateForBlackLineTest() {
        return null;
    }

    @Override
    public IState stateForWhiteLineTest() {
        return null;
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
        return null;
    }
}