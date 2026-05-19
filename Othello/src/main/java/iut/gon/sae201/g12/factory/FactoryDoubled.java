package iut.gon.sae201.g12.factory;

import iut.gon.sae201.g12.State;
import iut.gon.sae201.g12.utils.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class FactoryDoubled implements IFactory {

    private final int[][] boardPoses = {{6, 12}, // on line 0, x cells go from 6 to 12
            {3, 15}, // here from 3 to 15
            {2, 16}, // ...
            {1, 17}, {0, 18}, {1, 17}, {0, 18}, {1, 17}, {2, 16}, {3, 15}, {6, 12}};

    private Set<CoordinateDoubled> initializeEmptyBoard() {
        Set<CoordinateDoubled> temp = new HashSet<>();

        // Board is 11 lines (0-10)
        for (int i = 0; i <= 10; i++) {
            for (int j = boardPoses[i][0]; j <= boardPoses[i][1]; j++) {
                temp.add(new CoordinateDoubled(i, j));
            }
        }

        return temp;
    }

    @Override
    public IState testState() {
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
                initializeEmptyBoard().stream()
                        .map(c ->
                                new CoordinateDoubled(c.getX(), c.getY())
                        )
                        .collect(
                                Collectors.toMap(c -> c, _var -> null)
                        ),
                Team.BLACK,
                new ArrayList<>()
        );
    }

    @Override
    public IState doubleLineStateTest() {
        return null;
    }
}