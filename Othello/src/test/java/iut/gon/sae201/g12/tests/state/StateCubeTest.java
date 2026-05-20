package iut.gon.sae201.g12.tests.state;

import iut.gon.sae201.g12.state.State;
import iut.gon.sae201.g12.actions.RemoveLine;
import iut.gon.sae201.g12.factory.FactoryCube;
import iut.gon.sae201.g12.tokens.Token;
import iut.gon.sae201.g12.utils.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class StateCubeTest {

    private FactoryCube factory;
    private IState emptyState;
    private IState testState;
    private IState blackLineState;
    private IState whiteLineState;
    private IState doubleLineState;

    // Conversion depuis coordonnées Doubled pour faciliter les tests
    private CoordinateCube fromDoubled(int x, int y) {
        int r = x - 5;
        int q = (y - x - 4) / 2;
        int s = -q - r;
        return new CoordinateCube(q, r, s);
    }

    @BeforeEach
    public void setUp() {
        factory = new FactoryCube();
        emptyState = factory.emptyState();
        testState = factory.testState();
        blackLineState = factory.stateForBlackLineTest();
        whiteLineState = factory.stateForWhiteLineTest();
        doubleLineState = factory.doubleLineStateTest();
    }

    // --- board() ---

    @Test
    public void testEmptyBoardIsNotNull() {
        assertNotNull(emptyState.board(), "Le plateau ne doit pas être null.");
    }

    @Test
    public void testEmptyBoardHasNoTokens() {
        for (Token t : emptyState.board().values()) {
            assertNull(t, "Un plateau vide ne doit contenir aucun token.");
        }
    }

    @Test
    public void testBoardContainsCenterCoordinate() {
        // Centre en [0,0,0] en cube
        assertTrue(emptyState.board().containsKey(new CoordinateCube(0, 0, 0)),
                "La case centrale [0,0,0] doit être dans le plateau.");
    }

    @Test
    public void testBoardDoesNotContainOutsideCoordinate() {
        assertFalse(emptyState.board().containsKey(new CoordinateCube(10, 10, -20)),
                "Une case hors plateau ne doit pas être présente.");
    }

    // --- turn() ---

    @Test
    public void testTurnIsNotNull() {
        assertNotNull(testState.turn(), "Le tour ne doit pas être null.");
    }

    @Test
    public void testEmptyStateTurn() {
        assertEquals(Team.WHITE, emptyState.turn(),
                "Le tour après BLACK doit être WHITE.");
    }

    // --- rings() ---

    @Test
    public void testEmptyStateHasNoRings() {
        Map<Team, List<Coordinate>> rings = emptyState.rings();
        assertTrue(rings.get(Team.BLACK).isEmpty(), "Un plateau vide ne doit pas avoir d'anneaux noirs.");
        assertTrue(rings.get(Team.WHITE).isEmpty(), "Un plateau vide ne doit pas avoir d'anneaux blancs.");
    }

    @Test
    public void testTestStateHasBlackRings() {
        assertFalse(testState.rings().get(Team.BLACK).isEmpty(),
                "testState doit avoir des anneaux noirs.");
    }

    @Test
    public void testTestStateHasWhiteRings() {
        assertFalse(testState.rings().get(Team.WHITE).isEmpty(),
                "testState doit avoir des anneaux blancs.");
    }

    @Test
    public void testRingsContainsExpectedCoordinate() {
        assertTrue(testState.rings().get(Team.BLACK).contains(fromDoubled(5, 11)),
                "L'anneau noir en [5,11] (doubled) doit être dans rings.");
    }

    // --- lines() ---

    @Test
    public void testEmptyStateHasNoLines() {
        assertTrue(emptyState.lines().isEmpty(), "Un plateau vide ne doit pas avoir de lignes.");
    }

    @Test
    public void testBlackLineStateHasLines() {
        assertTrue(blackLineState.lines().isEmpty(),
                "stateForBlackLineTest doit contenir au moins une ligne.");
    }

    @Test
    public void testWhiteLineStateHasLines() {
        assertFalse(whiteLineState.lines().isEmpty(),
                "stateForWhiteLineTest doit contenir au moins une ligne.");
    }

    @Test
    public void testEachLineHasFivePawns() {
        for (Set<Coordinate> line : blackLineState.lines()) {
            assertEquals(5, line.size(), "Chaque ligne doit contenir exactement 5 cases.");
        }
    }

    @Test
    public void testDoubleLineStateHasTwoLines() {
        assertEquals(2, doubleLineState.lines().size(),
                "doubleLineStateTest doit contenir exactement deux lignes.");
    }

    // --- removeToken() ---

    @Test
    public void testRemoveTokenSetsNull() {
        Coordinate c = fromDoubled(5, 11);
        IState newState = testState.removeToken(c);
        assertNull(newState.board().get(c), "Après removeToken, la case doit être null.");
    }

    @Test
    public void testRemoveTokenOnNullDoesNotThrow() {
        Coordinate c = new CoordinateCube(0, 0, 0);
        assertDoesNotThrow(() -> emptyState.removeToken(c),
                "removeToken sur une case vide ne doit pas lever d'exception.");
    }

    // --- removeLine() ---

    @Test
    public void testRemoveLineThrowsOnNullLine() {
        assertThrows(RuntimeException.class,
                () -> testState.removeLine(new RemoveLine(null, fromDoubled(5, 11))),
                "removeLine avec une ligne null doit lever une RuntimeException.");
    }

    @Test
    public void testRemoveLineThrowsOnWrongSize() {
        Set<Coordinate> line = new HashSet<>();
        line.add(fromDoubled(5, 9));
        assertThrows(RuntimeException.class,
                () -> testState.removeLine(new RemoveLine(line, fromDoubled(5, 11))),
                "removeLine avec moins de 5 cases doit lever une RuntimeException.");
    }

    // --- availableMoves() ---

    @Test
    public void testAvailableMovesNotNull() {
        assertNotNull(emptyState.availableMoves(new CoordinateCube(0, 0, 0)),
                "availableMoves ne doit pas retourner null.");
    }

    @Test
    public void testAvailableMovesFromCenterOnEmptyBoard() {
        assertFalse(emptyState.availableMoves(new CoordinateCube(0, 0, 0)).isEmpty(),
                "Depuis le centre d'un plateau vide, des mouvements doivent être disponibles.");
    }

    // --- equals() et hashCode() ---

    @Test
    public void testEqualsOnSameState() {
        assertEquals(emptyState, factory.emptyState(),
                "Deux états vides doivent être égaux.");
    }

    @Test
    public void testNotEqualsOnDifferentStates() {
        assertNotEquals(emptyState, testState,
                "Un état vide et un état avec tokens ne doivent pas être égaux.");
    }

    @Test
    public void testHashCodeConsistency() {
        assertEquals(emptyState.hashCode(), factory.emptyState().hashCode(),
                "Deux états égaux doivent avoir le même hashCode.");
    }

    // --- isInField() ---

    @Test
    public void testIsInFieldCenter() {
        assertTrue(State.isInField(new CoordinateCube(0, 0, 0)),
                "La case centrale doit être dans le terrain.");
    }

    @Test
    public void testIsInFieldOutside() {
        assertFalse(State.isInField(new CoordinateCube(10, 10, -20)),
                "Une case hors terrain ne doit pas être valide.");
    }

    @Test
    public void testIsInFieldInvalidConstraint() {
        assertFalse(State.isInField(new CoordinateCube(1, 1, 1)),
                "Une CoordinateCube avec q+r+s != 0 ne doit pas être dans le terrain.");
    }
}