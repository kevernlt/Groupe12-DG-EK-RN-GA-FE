package iut.gon.sae201.g12;

import iut.gon.sae201.g12.interfaces.IState;
import iut.gon.sae201.g12.utils.Coordinate;
import iut.gon.sae201.g12.utils.Direction;
import iut.gon.sae201.g12.utils.Mode;

import java.util.*;

public record State(Map<Coordinate, Token> board, Team turn, List<Set<Coordinate>> lines) implements IState {
    public boolean isInField(Coordinate c) {
        return false;
    }

    public Team winner() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        State other = (State) o;
        if (!board.equals(other.board)) return false;
        if (!turn.equals(other.turn)) return false;
        if (!lines.equals(other.lines)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(board, turn, lines);
    }

    @Override
    public IState move(Move move) throws IllegalArgumentException, IndexOutOfBoundsException, RuntimeException {
        if (!availableMoves(move.getFrom()).contains(move.getTo())) throw new IllegalArgumentException();
        if (rings().get(turn).contains(move.getFrom())) throw new IllegalArgumentException();
        if (isInField(move.getTo())) throw new IndexOutOfBoundsException();
        if (!getPawnsLines(board) == null) throw new RuntimeException();
        toggleToken(move.getTo(), turn,//ring);
        removeToken(move.getFrom());
        toggleToken(move.getFrom(), turn,//pawn);
        return this;
    }

    @Override
    @SuppressWarnings("suspicious")
    public IState removeLine(RemoveLine removeLine) throws RuntimeException {
        if (removeLine == null) throw new RuntimeException();
        if (removeLine.getLine() == null) throw new RuntimeException();
        if (removeLine.getLine().size() != 5) throw new RuntimeException();
        Team t = board.get((Coordinate) removeLine.getLine().toArray()[0]).getTeam();
        for (Coordinate c : removeLine.getLine()) {
            if (board.get(c).getTeam() != t) {
                throw new RuntimeException();
            }
        }
        for (Coordinate c : removeLine.getLine()) {
            if (!isInField(c)) throw new RuntimeException();
        }
        for (Coordinate c : removeLine.getLine()) {
            removeToken(c);
        }
        removeToken(removeLine.getRing());
        return this;
    }

    @Override
    public Set<Coordinate> availableMoves(Coordinate from) {
        Set<Coordinate> moves = new HashSet<>();
        keepGoing(moves,from,Direction.O,false);
        keepGoing(moves,from,Direction.E,false);
        keepGoing(moves,from,Direction.NE,false);
        keepGoing(moves,from,Direction.NO,false);
        keepGoing(moves,from,Direction.SO,false);
        keepGoing(moves,from,Direction.SE,false);
        return moves;
    }

    public void keepGoing(Set<Coordinate> coordsSet, Coordinate from, Direction direction,boolean pawn) {
        Coordinate to = from.toDir(Mode.POINTY, direction);
        if (!isInField(to)) return;
        if (board.get(to) instanceof Ring) return;
        if (!(board.get(to) instanceof Pawn)){
            coordsSet.add(to);
        } else {
            pawn=true;
        }
        if(pawn)return;
        keepGoing(coordsSet, from, direction,pawn);
    }


    @Override
    public Map<Coordinate, Token> board() {
        return board;
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
        return turn.other();
    }

    @Override
    public List<Set<Coordinate>> getPawnsLines(Map<Coordinate, Token> board) {
        return null;
    }
}