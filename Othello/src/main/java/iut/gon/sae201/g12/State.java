package iut.gon.sae201.g12;

import iut.gon.sae201.g12.interfaces.IState;
import iut.gon.sae201.g12.utils.Coordinate;

import java.util.*;

public class State implements IState {

    private Map<Coordinate, Token> board;
    private Team turn;
    private List<Set<Coordinate>> lines;

    public State(Map<Coordinate, Token> board, Team turn, List<Set<Coordinate>> lines) {
        this.board = board;
        this.turn = turn;
        this.lines = lines;
    }

    public boolean isInField(Coordinate c) {
        return false;
    }

    public Team winner() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public IState move(Move move) {
        return null;
    }

    @Override
    public IState removeLine(RemoveLine removeLine) {
        return null;
    }

    @Override
    public Set<Coordinate> availabkeMoves(Coordinate from) {
        return null;
    }

    @Override
    public Map<Coordinate, Token> board() {
        return null;
    }

    @Override
    public Map<Team, List<Coordinate>> rings() {
        return null;
    }

    @Override
    public List<Set<Coordinate>> lines() {
        return null;
    }

    @Override
    public Team turn() {
        return null;
    }

    @Override
    public List<Set<Coordinate>> getPawnsLines(Map<Coordinate, Token> board) {
        return null;
    }
}