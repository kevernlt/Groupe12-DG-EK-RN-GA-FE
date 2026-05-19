package iut.gon.sae201.g12.utils;

import iut.gon.sae201.g12.actions.Move;
import iut.gon.sae201.g12.actions.RemoveLine;
import iut.gon.sae201.g12.tokens.Token;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IState {
    public IState move(Move move);

    public IState removeLine(RemoveLine removeLine);

    public Set<Coordinate> availabkeMoves(Coordinate from);

    public Map<Coordinate, Token> board();

    public Map<Team, List<Coordinate>> rings();

    public List<Set<Coordinate>> lines();

    public Team turn();

    public List<Set<Coordinate>> getPawnsLines(Map<Coordinate,Token> board);
}
