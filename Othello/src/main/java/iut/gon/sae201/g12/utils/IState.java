package iut.gon.sae201.g12.utils;

import iut.gon.sae201.g12.actions.Move;
import iut.gon.sae201.g12.actions.RemoveLine;
import iut.gon.sae201.g12.tokens.Pawn;
import iut.gon.sae201.g12.tokens.Token;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

import static iut.gon.sae201.g12.State.isInField;

public interface IState {

    public IState move(Move move) throws InvocationTargetException, InstantiationException, IllegalAccessException;

    public IState removeLine(RemoveLine removeLine);

    public Set<Coordinate> availableMoves(Coordinate from);

    public Map<Coordinate, Token> board();

    public Map<Team, List<Coordinate>> rings();

    public List<Set<Coordinate>> lines();

    public Team turn();

    public IState removeToken(Coordinate c);

    public IState toggleToken(Coordinate c, Class<? extends Token> tokenClass, Team team) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;

    public static List<Set<Coordinate>> getPawnsLines(Map<Coordinate, Token> board) {
        ArrayList<Set<Coordinate>> line = new ArrayList<>();
        HashSet<Coordinate> pawn = new HashSet<>();
        for (Coordinate c : board.keySet()) {
            if (board.get(c) instanceof Pawn) {
                pawn.add(c);
            }
        }
        for (Coordinate c : pawn) {

            HashSet<Coordinate> tryLine = new HashSet<>();
            tryLine.add(c);
            if (pawnLine(c, Direction.E, tryLine, board,teamPawn)) {
                line.add(tryLine);
            }

            tryLine = new HashSet<>();
            tryLine.add(c);
            if (pawnLine(c, Direction.SE, tryLine, board,teamPawn)) {
                line.add(tryLine);
            }

            tryLine = new HashSet<>();
            tryLine.add(c);
            if (pawnLine(c, Direction.NE, tryLine, board,teamPawn)) {
                line.add(tryLine);
            }
        }
        return line;
    }

    public static boolean pawnLine(Coordinate c, Direction direction, Set<Coordinate> line, Map<Coordinate, Token> board,Team team) {
        if (!isInField(c)) return false;
        Coordinate to = c.toDir(Mode.POINTY, direction);
        if (board.get(to) instanceof Pawn && team==board.get(to).getTeam()) {
            line.add(to);
            if (line.size() == 5) {
                return true;
            } else {
                return pawnLine(to, direction, line, board,team);
            }
        }
        return false;
    }
}
