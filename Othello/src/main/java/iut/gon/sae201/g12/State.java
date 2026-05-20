package iut.gon.sae201.g12;

import iut.gon.sae201.g12.actions.Move;
import iut.gon.sae201.g12.actions.RemoveLine;
import iut.gon.sae201.g12.tokens.Pawn;
import iut.gon.sae201.g12.tokens.Ring;
import iut.gon.sae201.g12.tokens.Token;
import iut.gon.sae201.g12.utils.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public record State(Map<Coordinate, Token> board, Team turn, List<Set<Coordinate>> lines) implements IState {
    private static final int[][] BOARD_POSES = {
            {6, 12}, {3, 15}, {2, 16}, {1, 17}, {0, 18},
            {1, 17}, {0, 18}, {1, 17}, {2, 16}, {3, 15}, {6, 12}
    };
    public static boolean isInField(Coordinate c) {
        if (c == null) return false;

        if (c instanceof CoordinateCube cube && (cube.getQ() + cube.getR() + cube.getS() != 0)) {
            return false;
        }

        Point p = c.to2DCoordinate();
        int x = p.x();
        int y = p.y();
        if (x < 0 || x >= BOARD_POSES.length) return false;

        int minY = BOARD_POSES[x][0];
        int maxY = BOARD_POSES[x][1];
        if (y < minY || y > maxY) return false;
        return (y - minY) % 2 == 0;
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
        return lines.equals(other.lines);
    }

    @Override
    public int hashCode() {
        return Objects.hash(board, turn, lines);
    }

    @Override
    public IState move(Move move) throws RuntimeException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if (!availableMoves(move.getFrom()).contains(move.getTo())) throw new IllegalArgumentException();
        if (rings().get(turn).contains(move.getFrom())) throw new IllegalArgumentException();
        if (isInField(move.getTo())) throw new IndexOutOfBoundsException();
        if (!(lines == null)) throw new RuntimeException();
        toggleToken(move.getTo(), Ring.class,turn);
        removeToken(move.getFrom());
        toggleToken(move.getFrom(), Pawn.class,turn);
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
        return lines;
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
    public IState toggleToken(Coordinate c, Class<? extends Token> tokenClass, Team team) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<?> constructor = tokenClass.getConstructors()[0];
        Token token = (Token) constructor.newInstance(team);
        if(token.getTeam() == board.get(c).getTeam() && token.getClass() == board.get(c).getClass()){
            removeToken(c);
            return this;
        }
        board.remove(c);
        board.put(c,token);
        return this;
    }
}