package iut.gon.sae201.g12;

import iut.gon.sae201.g12.interfaces.IState;
import iut.gon.sae201.g12.utils.*;

import java.util.*;

public record State(Map<Coordinate, Token> board, Team turn, List<Set<Coordinate>> lines) implements IState {
    public boolean isInField(Coordinate c) {
        if(board.containsKey(c))return true;
        return false;
    }

    public Team winner() {
        if(rings().get(Team.WHITE).size()<=2)return Team.WHITE;
        if(rings().get(Team.BLACK).size()<=2)return Team.BLACK;
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

    public void keepGoing(Set<Coordinate> coordsSet, Coordinate from, Direction direction,boolean pion) {
        Coordinate to = from.toDir(Mode.POINTY, direction);
        if (!isInField(to)) return;
        if (board.get(to) instanceof Ring) return;
        if (!(board.get(to) instanceof Pawn)){
            coordsSet.add(to);
            if(pion)return;
        } else {
            pion=true;
        }
        keepGoing(coordsSet, to, direction,pion);
    }


    @Override
    public Map<Coordinate, Token> board() {
        return board;
    }

    @Override
    public Map<Team, List<Coordinate>> rings() {
        HashMap<Team,List<Coordinate>> ring = new HashMap<>();
        ArrayList<Coordinate> whiteRing = new ArrayList<>();
        ArrayList<Coordinate> blackRing = new ArrayList<>();
        for(Coordinate c: board.keySet()){
            if(board.get(c) instanceof Ring && board.get(c).getTeam()==Team.WHITE)whiteRing.add(c);
            if(board.get(c) instanceof Ring && board.get(c).getTeam()==Team.BLACK)blackRing.add(c);
        }
        ring.put(Team.WHITE,whiteRing);
        ring.put(Team.BLACK,blackRing);
        return ring;
    }

    @Override
    public List<Set<Coordinate>> lines() {
        ArrayList<Set<Coordinate>> line = new ArrayList<>();
        for(int r=-5;r<=5;r++){
            for(int q=-5;q<=5;q++){
                for(int s=-5;s<=5;s++){
                    HashSet<Coordinate> tryLine = new HashSet<>();
                    Coordinate c = new CoordinateCube(q,r,s);
                    if(pawnLine(c,Direction.E,tryLine)){
                        line.add(tryLine);
                    }
                    if(pawnLine(c,Direction.SE,tryLine)){
                        line.add(tryLine);
                    }
                    if(pawnLine(c,Direction.NE,tryLine)){
                        line.add(tryLine);
                    }
                }
            }
        }
        return line;
    }

    public boolean pawnLine(Coordinate c, Direction direction, Set<Coordinate> line){
        if(!isInField(c))return false;
        Coordinate to = c.toDir(Mode.POINTY,direction);
        if(board.get(to)instanceof Pawn){
            line.add(to);
            if(line.size()==5){
                return true;
            } else {
                return pawnLine(to,direction,line);
            }
        }
        return false;
    }

    @Override
    public Team turn() {
        return turn.other();
    }

    @Override
    public IState removeToken(Coordinate c) {
        Token t = board.get(c);
        if(t!=null){
            board.remove(c);
            board.put(c,null);
        }
        return this;
    }

    @Override
    public IState toggleToken(Coordinate c, Class<Token> tokenClass, Team team) {
        return null;
    }
}