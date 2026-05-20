package iut.gon.sae201.g12.interfaces;

import iut.gon.sae201.g12.Move;
import iut.gon.sae201.g12.RemoveLine;
import iut.gon.sae201.g12.Team;
import iut.gon.sae201.g12.Token;
import iut.gon.sae201.g12.utils.Coordinate;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IState {
    public IState move(Move move) throws InvocationTargetException, InstantiationException, IllegalAccessException;

    public IState removeLine(RemoveLine removeLine);

    public Set<Coordinate> availableMoves(Coordinate from);

    public Map<Coordinate, Token> board();

    public Map<Team, List<Coordinate>> rings();

    public List<Set<Coordinate>> lines();

    public Team turn();

    public IState removeToken(Coordinate c);

    public IState toggleToken(Coordinate c, Class<? extends Token> tokenClass,Team team) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;

    public static List<Set<Coordinate>> getPawnsLines(Map<Coordinate, Token> board) {
        return null;
    }
}
