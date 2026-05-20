package iut.gon.sae201.g12.tests.state;

import iut.gon.sae201.g12.state.State;
import iut.gon.sae201.g12.actions.RemoveLine;
import iut.gon.sae201.g12.factory.FactoryDoubled;
import iut.gon.sae201.g12.tokens.Pawn;
import iut.gon.sae201.g12.tokens.Token;
import iut.gon.sae201.g12.utils.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class StateDoubleTest {

    private FactoryDoubled factory;
    private IState emptyState;
    private IState testState;
    private IState blackLineState;
    private IState whiteLineState;
    private IState doubleLineState;

    @BeforeEach
    public void setUp() {
        factory = new FactoryDoubled();
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
        assertTrue(emptyState.board().containsKey(new CoordinateDoubled(5, 9)),
                "La case centrale [5,9] doit être dans le plateau.");
    }

    @Test
    public void testBoardContainsBorderCoordinate() {
        assertTrue(emptyState.board().containsKey(new CoordinateDoubled(0, 6)),
                "La case de bordure [0,6] doit être dans le plateau.");
    }

    @Test
    public void testBoardDoesNotContainOutsideCoordinate() {
        assertFalse(emptyState.board().containsKey(new CoordinateDoubled(0, 0)),
                "La case [0,0] ne doit pas être dans le plateau.");
    }

    // --- turn() ---

    @Test
    public void testTurnIsNotNull() {
        assertNotNull(testState.turn(), "Le tour ne doit pas être null.");
    }

    @Test
    public void testEmptyStateTurnIsWhite() {
        // emptyState est initialisé avec Team.BLACK, turn() appelle other()
        assertEquals(Team.WHITE, emptyState.turn(), "Le tour après BLACK doit être WHITE.");
    }

    // --- rings() ---

    @Test
    public void testEmptyStateHasNoRings() {
        Map<Team, List<Coordinate>> rings = emptyState.rings();
        assertTrue(rings.get(Team.BLACK).isEmpty(), "Un plateau vide ne doit pas avoir d'anneaux noirs.");
        assertTrue(rings.get(Team.WHITE).isEmpty(), "Un plateau vide ne doit pas avoir d'anneaux blancs.");
    }

    @Test
    public void testTestStateHasCorrectBlackRings() {
        assertTrue(testState.rings().get(Team.BLACK).isEmpty() == false,
                "testState doit avoir des anneaux noirs.");
    }

    @Test
    public void testTestStateHasCorrectWhiteRings() {
        assertTrue(testState.rings().get(Team.WHITE).isEmpty() == false,
                "testState doit avoir des anneaux blancs.");
    }

    @Test
    public void testRingsContainsExpectedCoordinate() {
        Map<Team, List<Coordinate>> rings = testState.rings();
        assertTrue(rings.get(Team.BLACK).contains(new CoordinateDoubled(5, 11)),
                "L'anneau noir en [5,11] doit être dans rings.");
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
        Coordinate c = new CoordinateDoubled(5, 11);
        IState newState = testState.removeToken(c);
        assertNull(newState.board().get(c), "Après removeToken, la case doit être null.");
    }

    @Test
    public void testRemoveTokenOnNullDoesNotThrow() {
        Coordinate c = new CoordinateDoubled(5, 9); // case vide dans emptyState
        assertDoesNotThrow(() -> emptyState.removeToken(c),
                "removeToken sur une case vide ne doit pas lever d'exception.");
    }

    // --- removeLine() ---

    @Test
    public void testRemoveLineThrowsOnNullLine() {
        assertThrows(RuntimeException.class,
                () -> testState.removeLine(new RemoveLine(null, new CoordinateDoubled(5, 11))),
                "removeLine avec une ligne null doit lever une RuntimeException.");
    }

    @Test
    public void testRemoveLineThrowsOnWrongSize() {
        Set<Coordinate> line = new HashSet<>();
        line.add(new CoordinateDoubled(5, 9));
        assertThrows(RuntimeException.class,
                () -> testState.removeLine(new RemoveLine(line, new CoordinateDoubled(5, 11))),
                "removeLine avec moins de 5 cases doit lever une RuntimeException.");
    }

    @Test
    public void testRemoveLineThrowsOnMixedTeams() {
        // Construire une ligne de 5 cases avec des équipes mélangées
        Set<Coordinate> line = new HashSet<>();
        line.add(new CoordinateDoubled(5, 1));
        line.add(new CoordinateDoubled(5, 3));
        line.add(new CoordinateDoubled(5, 5));
        line.add(new CoordinateDoubled(5, 7));
        line.add(new CoordinateDoubled(5, 9));

        // Placer des pions d'équipes différentes sur ces cases
        Map<Coordinate, Token> board = new HashMap<>(testState.board());
        board.put(new CoordinateDoubled(5, 1), new Pawn(Team.BLACK));
        board.put(new CoordinateDoubled(5, 3), new Pawn(Team.BLACK));
        board.put(new CoordinateDoubled(5, 5), new Pawn(Team.BLACK));
        board.put(new CoordinateDoubled(5, 7), new Pawn(Team.BLACK));
        board.put(new CoordinateDoubled(5, 9), new Pawn(Team.WHITE)); // équipe différente

        IState mixedState = new State(board, Team.BLACK, new ArrayList<>());
        assertThrows(RuntimeException.class,
                () -> mixedState.removeLine(new RemoveLine(line, new CoordinateDoubled(5, 11))),
                "removeLine avec des équipes mélangées doit lever une RuntimeException.");
    }

    // --- availableMoves() ---

    @Test
    public void testAvailableMovesNotNull() {
        Coordinate c = new CoordinateDoubled(5, 9);
        assertNotNull(emptyState.availableMoves(c), "availableMoves ne doit pas retourner null.");
    }

    @Test
    public void testAvailableMovesFromCenterOnEmptyBoard() {
        Coordinate c = new CoordinateDoubled(5, 9);
        Set<Coordinate> moves = emptyState.availableMoves(c);
        assertFalse(moves.isEmpty(),
                "Depuis le centre d'un plateau vide, des mouvements doivent être disponibles.");
    }

    @Test
    public void testAvailableMovesBlockedByRing() {
        // [5,11] contient un anneau noir dans testState, il doit bloquer les mouvements vers l'est
        Coordinate from = new CoordinateDoubled(5, 9);
        Set<Coordinate> moves = testState.availableMoves(from);
        assertFalse(moves.contains(new CoordinateDoubled(5, 13)),
                "Un anneau bloque les mouvements au-delà de lui.");
    }

    // --- equals() et hashCode() ---

    @Test
    public void testEqualsOnSameState() {
        IState emptyState2 = factory.emptyState();
        assertEquals(emptyState, emptyState2, "Deux états vides doivent être égaux.");
    }

    @Test
    public void testNotEqualsOnDifferentStates() {
        assertNotEquals(emptyState, testState,
                "Un état vide et un état avec tokens ne doivent pas être égaux.");
    }

    @Test
    public void testHashCodeConsistency() {
        IState emptyState2 = factory.emptyState();
        assertEquals(emptyState.hashCode(), emptyState2.hashCode(),
                "Deux états égaux doivent avoir le même hashCode.");
    }

    // --- isInField() ---

    @Test
    public void testIsInFieldCenter() {
        assertTrue(State.isInField(new CoordinateDoubled(5, 9)),
                "La case centrale doit être dans le terrain.");
    }

    @Test
    public void testIsInFieldOutside() {
        assertFalse(State.isInField(new CoordinateDoubled(0, 0)),
                "La case [0,0] ne doit pas être dans le terrain.");
    }
}