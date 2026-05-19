package iut.gon.sae201.g12;

import iut.gon.sae201.g12.utils.Coordinate;

import java.util.*;

public class State {

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
}