package iut.gon.sae201.g12;

import iut.gon.sae201.g12.interfaces.IState;
import iut.gon.sae201.g12.tokens.Token;
import iut.gon.sae201.g12.utils.Coordinate;

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
        this.currentState.availableMoves(from);
    }

    public void moveRing(Coordinate from,Coordinate to){
        Move move = new Move(from,to);
        this.currentState.move(move);
    }

    public List<Set<Coordinate>> getPawnsLines(){
        /*IState : lines()*/
        return this.currentState.lines();
    }

    public void removeLine(Set<Coordinate> line,Coordinate ring){
        this.currentState = this.currentState.removeLine(line, ring);
    }

    public Map<Coordinate,Token> getBoard(){
        /*IState (currentState) : getBoard()*/
        return this.currentState.getBoard();
    }

    public Token getTokenAt(Coordinate c){
        /* Map : get(Object key)*/
        return this.getBoard().get(c);
    }

    public boolean isInField(Coordinate c){
        return this.getBoard().containsKey(c);
    }

    public List<Coordinate> getRings(Team team){
        /*Map : entrySet(), getValue().getTeam()-->Token ALED*/
        return null;
    }

    public List<Coordinate> getPawns(Team team){
        /*IState : lines()  ALED*/
        return null;
    }

    public Team getTurn(){
        return this.currentState.getCurrentTeam();
    }
}
