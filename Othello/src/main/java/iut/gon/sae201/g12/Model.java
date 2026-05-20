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

/**
 * Modèle du jeu Othello/Reversi.
 *
 * <p>Encapsule l'état du jeu et fournit des méthodes fonctionnelles pour
 * interroger et modifier cet état.
 */
public class Model {
    /** L'état actuel du jeu */
    private IState currentState;

    /**
     * Crée un modèle avec un état initial.
     *
     * @param state l'état de départ
     */
    public Model(IState state){
        this.currentState = state;
    }

    /**
     * Retourne l'état actuel du jeu.
     *
     * @return l'état courant
     */
    public IState getCurrentState(){ return this.currentState; }

    /**
     * Définit un nouvel état courant.
     *
     * @param currentState le nouvel état
     */
    public void setCurrentState(IState currentState) {
        this.currentState = currentState;
    }

    /**
     * Retourne les mouvements disponibles à partir d'une position.
     *
     * @param from la coordonnée de départ
     * @param to (non utilisé dans la logique)
     * @return ensemble des coordonnées destination possibles
     */
    public Set<Coordinate> movesFrom(Coordinate from, Coordinate to) {
        return this.currentState.availableMoves(from);
    }

    /**
     * Effectue un mouvement de ring/anneau.
     *
     * @param from la coordonnée de départ
     * @param to la coordonnée destination
     * @throws InvocationTargetException si l'invocation réfléchie échoue
     * @throws InstantiationException si l'instantiation échoue
     * @throws IllegalAccessException si l'accès réfléchi échoue
     */
    public void moveRing(Coordinate from,Coordinate to) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Move move = new Move(from,to);
        this.currentState = this.currentState.move(move);
    }

    /**
     * Retourne les lignes de pions détectées.
     *
     * @return la liste des lignes de pions
     */
    public List<Set<Coordinate>> getPawnsLines(){
        return this.currentState.lines();    }

    /**
     * Supprime une ligne de pions.
     *
     * @param line la ligne à supprimer
     * @param ring le ring associé à la suppression
     */
    public void removeLine(Set<Coordinate> line,Coordinate ring){
        this.currentState = this.currentState.removeLine(new RemoveLine(line, ring));
    }

    /**
     * Retourne le plateau de jeu.
     *
     * @return une map associant coordonnées à pièces
     */
    public Map<Coordinate,Token> getBoard(){
        return this.currentState.board();
    }

    /**
     * Retourne la pièce à une coordonnée donnée.
     *
     * @param c la coordonnée
     * @return le token à cette position, ou null
     */
    public Token getTokenAt(Coordinate c){
        return this.getBoard().get(c);
    }

    /**
     * Vérifie si une coordonnée contient une pièce.
     *
     * @param c la coordonnée
     * @return {@code true} si une pièce existe à cette position
     */
    public boolean isInField(Coordinate c){
        return this.getBoard().containsKey(c);
    }

    /**
     * Retourne les anneaux d'une équipe.
     *
     * @param team l'équipe
     * @return liste des coordonnées des anneaux
     */
    public List<Coordinate> getRings(Team team){
        return this.currentState.rings().get(team);
    }

    /**
     * Retourne les pions d'une équipe.
     *
     * @param team l'équipe
     * @return liste des coordonnées des pions
     */
    public List<Coordinate> getPawns(Team team) {
        List<Coordinate> result = new ArrayList<>();
        for (Map.Entry<Coordinate, Token> entry : getBoard().entrySet()) {
            if (entry.getValue() instanceof Pawn && entry.getValue().getTeam().equals(team)) {
                result.add(entry.getKey());
            }
        }
        return result;
    }

    /**
     * Retourne l'équipe dont c'est le tour.
     *
     * @return l'équipe actuelle
     */
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
