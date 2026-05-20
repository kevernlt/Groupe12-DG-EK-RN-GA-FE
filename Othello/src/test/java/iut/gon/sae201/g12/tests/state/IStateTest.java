package iut.gon.sae201.g12.tests.state;

import iut.gon.sae201.g12.tokens.Pawn;
import iut.gon.sae201.g12.tokens.Ring;
import iut.gon.sae201.g12.tokens.Token;
import iut.gon.sae201.g12.utils.*;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class IStateTest {
    private Map<Coordinate, Token> buildBoardWithLine(Team team, Coordinate start, Direction direction) {
        Map<Coordinate, Token> board = new HashMap<>();
        Coordinate current = start;
        for (int i = 0; i < 5; i++) {
            board.put(current, new Pawn(team));
            current = current.toDir(Mode.POINTY, direction);
        }
        return board;
    }

    @Test
    public void testGetPawnsLinesEmpty() {
        Map<Coordinate, Token> board = new HashMap<>();
        board.put(new CoordinateDoubled(0, 0), null);

        List<Set<Coordinate>> lines = IState.getPawnsLines(board);

        assertTrue(lines.isEmpty(), "Un plateau sans pion ne doit contenir aucune ligne.");
    }

    @Test
    public void testGetPawnsLinesWithRingsOnly() {
        Map<Coordinate, Token> board = new HashMap<>();
        board.put(new CoordinateDoubled(0, 0), new Ring(Team.BLACK));
        board.put(new CoordinateDoubled(2, 0), new Ring(Team.BLACK));

        List<Set<Coordinate>> lines = IState.getPawnsLines(board);

        assertTrue(lines.isEmpty(), "Un plateau avec uniquement des anneaux ne doit contenir aucune ligne.");
    }

    @Test
    public void testGetPawnsLinesDetectsLine() {
        Coordinate start = new CoordinateDoubled(6, 6);
        Map<Coordinate, Token> board = buildBoardWithLine(Team.BLACK, start, Direction.E);

        List<Set<Coordinate>> lines = IState.getPawnsLines(board);

        assertFalse(lines.isEmpty(), "Une ligne de 5 pions doit être détectée.");
        assertEquals(5, lines.get(0).size(), "La ligne détectée doit contenir exactement 5 coordonnées.");
    }

    @Test
    public void testGetPawnsLinesDoesNotDetectFourPawns() {
        Map<Coordinate, Token> board = new HashMap<>();
        Coordinate current = new CoordinateDoubled(0, 0);
        for (int i = 0; i < 4; i++) {
            board.put(current, new Pawn(Team.BLACK));
            current = current.toDir(Mode.POINTY, Direction.E);
        }

        List<Set<Coordinate>> lines = IState.getPawnsLines(board);

        assertTrue(lines.isEmpty(), "4 pions alignés ne doivent pas former une ligne.");
    }

    @Test
    public void testGetPawnsLinesDetectsSixPawnsAsTwoLines() {
        Map<Coordinate, Token> board = new HashMap<>();
        Coordinate current = new CoordinateDoubled(6, 6);
        for (int i = 0; i < 6; i++) {
            board.put(current, new Pawn(Team.BLACK));
            current = current.toDir(Mode.POINTY, Direction.E);
        }

        List<Set<Coordinate>> lines = IState.getPawnsLines(board);

        assertEquals(2, lines.size(), "6 pions alignés doivent former deux lignes de 5.");
    }

    @Test
    public void testPawnLineReturnsTrueForFivePawns() {
        Coordinate start = new CoordinateDoubled(6, 6);
        Map<Coordinate, Token> board = buildBoardWithLine(Team.BLACK, start, Direction.E);
        Set<Coordinate> line = new HashSet<>();
        line.add(start);

        boolean result = IState.pawnLine(start, Direction.E, line, board);

        assertTrue(result, "pawnLine doit retourner true pour une ligne de 5 pions.");
    }

    @Test
    public void testPawnLineReturnsFalseForLessThanFive() {
        Map<Coordinate, Token> board = new HashMap<>();
        Coordinate start = new CoordinateDoubled(0, 0);
        board.put(start, new Pawn(Team.BLACK));
        Set<Coordinate> line = new HashSet<>();
        line.add(start);

        boolean result = IState.pawnLine(start, Direction.E, line, board);

        assertFalse(result, "pawnLine doit retourner false si moins de 5 pions sont alignés.");
    }

    @Test
    public void testGetPawnsLinesBothTeams() {
        Coordinate startBlack = new CoordinateDoubled(6, 0);
        Coordinate startWhite = new CoordinateDoubled(7, 5);
        Map<Coordinate, Token> board = new HashMap<>();
        board.putAll(buildBoardWithLine(Team.BLACK, startBlack, Direction.E));
        board.putAll(buildBoardWithLine(Team.WHITE, startWhite, Direction.E));

        List<Set<Coordinate>> lines = IState.getPawnsLines(board);

        assertEquals(2, lines.size(), "Les lignes des deux équipes doivent être détectées.");
    }
}