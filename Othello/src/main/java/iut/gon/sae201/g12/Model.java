package iut.gon.sae201.g12;

import iut.gon.sae201.g12.actions.Move;
import iut.gon.sae201.g12.actions.RemoveLine;
import iut.gon.sae201.g12.tokens.Pawn;
import iut.gon.sae201.g12.tokens.Token;
import iut.gon.sae201.g12.utils.Coordinate;
import iut.gon.sae201.g12.utils.IState;
import iut.gon.sae201.g12.utils.Team;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Model {
    private IState currentState;

    public Model(IState state){
        this.currentState = state;
    }
    public IState getCurrentState(){ return this.currentState; }
    public void setCurrentState(IState currentState) {
        this.currentState = currentState;
    }

    public Set<Coordinate> movesFrom(Coordinate from, Coordinate to) {
        return this.currentState.availableMoves(from);
    }

    public void moveRing(Coordinate from,Coordinate to) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Move move = new Move(from,to);
        this.currentState = this.currentState.move(move);
    }

    public List<Set<Coordinate>> getPawnsLines(){
        return this.currentState.lines();    }

    public void removeLine(Set<Coordinate> line,Coordinate ring){
        this.currentState = this.currentState.removeLine(new RemoveLine(line, ring));
    }

    public Map<Coordinate,Token> getBoard(){
        return this.currentState.board();
    }

    public Token getTokenAt(Coordinate c){
        return this.getBoard().get(c);
    }

    public boolean isInField(Coordinate c){
        return this.getBoard().containsKey(c);
    }

    public List<Coordinate> getRings(Team team){
        return this.currentState.rings().get(team);
    }

    public List<Coordinate> getPawns(Team team) {
        List<Coordinate> result = new ArrayList<>();
        for (Map.Entry<Coordinate, Token> entry : getBoard().entrySet()) {
            if (entry.getValue() instanceof Pawn && entry.getValue().getTeam().equals(team)) {
                result.add(entry.getKey());
            }
        }
        return result;
    }

    public Team getTurn(){
        return this.currentState.turn();
    }

    public void toggleToken(Coordinate position,Team team,Class<? extends Token> token) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        setCurrentState(currentState.toggleToken(position,token,team));
    }

    public void removeToken(Coordinate c){
        setCurrentState(currentState.removeToken(c));
    }
}
