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
        /*faire avec State*/ return null;
    }

    public void moveRing(Coordinate from,Coordinate to){
        /*IState : move(Coordinate from, Coordinate to)*/
    }

    public List<Set<Coordinate>> getPawnsLines(){
        /*IState : lines()*/
        return null;
    }

    public void removeLine(Set<Coordinate> line,Coordinate ring){
        /*IState : removeLine(Set<Coordinate> line, Coordinate ring)*/
    }

    public Map<Coordinate,Token> getBoard(){
        /*IState (currentState) : getBoard()*/
        return null;
    }

    public Token getToken(Coordinate c){
        /* Map : get(Object key)*/
        return null;
    }

    public boolean isInField(Coordinate c){
        /*Map : constrainKey(Object key)*/
        return false;
    }

    public List<Coordinate> getRings(Team team){
        /*Map : entrySet(), getValue().getTeam()-->Token*/
        return null;
    }

    public List<Coordinate> getPawns(Team team){
        /*IState : lines()*/
        return null;
    }

    public Team getTurn(){
        return this.currentState.getCurrentTeam();
    }
}
