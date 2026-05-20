package iut.gon.sae201.g12.ai;

import iut.gon.sae201.g12.state.State;
import iut.gon.sae201.g12.actions.Action;
import iut.gon.sae201.g12.tokens.Pawn;
import iut.gon.sae201.g12.tokens.Token;
import iut.gon.sae201.g12.utils.Coordinate;
import iut.gon.sae201.g12.utils.Direction;
import iut.gon.sae201.g12.utils.IState;
import iut.gon.sae201.g12.utils.Mode;
import iut.gon.sae201.g12.utils.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.*;

/**
 * Représente un nœud de l'arbre de jeu pour l'algorithme MinMax.
 *
 * <p>Chaque nœud contient un état du jeu, une action qui y a mené et une référence
 * à son nœud parent. Les nœuds possèdent une fonction d'évaluation pour évaluer
 * la qualité d'une position.
 */
@SuppressWarnings("unused")
@AllArgsConstructor
public class Node {
    @Getter
    private IState gameState;
    private Node parentNode;
    @Getter
    private Action boardAction;

    private static final Set<Direction> OMITTED_DIRECTIONS_MODE = Set.of(Direction.N, Direction.S);


    /**
     * Crée un nœud racine sans parent.
     *
     * @param gameState l'état du jeu
     */
    public Node(IState gameState) {
        this.gameState = gameState;
    }

    /**
     * Retourne le nœud parent.
     *
     * @return le nœud parent, ou null si c'est la racine
     */
    public Node getParentNode() {
        return parentNode;
    }

    /**
     * Évalue ce nœud selon le tour courant.
     *
     * @return le score d'évaluation
     */
    public int evaluate() {
        return evaluate(gameState.turn());
    }

    /**
     * Évalue ce nœud pour l'équipe de maximisation donnée.
     *
     * @param maximizingTeam l'équipe à maximiser
     * @return le score d'évaluation
     */
    public int evaluate(Team maximizingTeam) {
        if (!(getGameState() instanceof State st)) {
            return 0;
        }

        Team opponent = maximizingTeam.other();
        int finalScore = 0;

        if (parentNode != null && boardAction != null) {
            finalScore += 0;
        }

        Team winner = st.winner();
        if (winner == maximizingTeam) return 100_000;
        if (winner == opponent) return -100_000;

        int myRings = st.rings().getOrDefault(maximizingTeam, List.of()).size();
        int opponentRings = st.rings().getOrDefault(opponent, List.of()).size();
        finalScore += (myRings - opponentRings) * 1000;

        finalScore += (countMobility(st, maximizingTeam) - countMobility(st, opponent)) * 5;
        finalScore += (scorePawnLines(st, maximizingTeam) - scorePawnLines(st, opponent));

        return finalScore;
    }

    private int countMobility(State st, Team team) {
        Map<Coordinate, Token> boardCopy = new HashMap<>(st.board());
        State temp = new State(boardCopy, team, new ArrayList<>(st.lines()));
        int mobility = 0;
        for (Map.Entry<Coordinate, Token> entry : temp.board().entrySet()) {
            if (entry.getValue() instanceof iut.gon.sae201.g12.tokens.Ring ring && ring.getTeam() == team) {
                mobility += temp.availableMoves(entry.getKey()).size();
            }
        }
        return mobility;
    }

    private int scorePawnLines(State st, Team team) {
        int score = 0;
        for (Coordinate c : st.board().keySet()) {
            Token token = st.board().get(c);
            if (!(token instanceof Pawn pawn) || pawn.getTeam() != team) {
                continue;
            }

            for (Direction direction : Arrays.stream(Direction.values()).filter(d -> !OMITTED_DIRECTIONS_MODE.contains(d)).toList()) {
                Coordinate previous = c.toDir(Mode.POINTY, direction.opposite());
                Token previousToken = st.board().get(previous);
                if (previousToken instanceof Pawn previousPawn && previousPawn.getTeam() == team) {
                    continue;
                }

                int length = determineLineLength(st, c, direction, team);
                if (length >= 4) {
                    score += length * 50;
                }
            }
        }
        return score;
    }

    private int determineLineLength(State st, Coordinate start, Direction direction, Team team) {
        int length = 1;
        Coordinate current = start;

        while (true) {
            Coordinate next = current.toDir(Mode.POINTY, direction);
            Token nextToken = st.board().get(next);
            if (!(nextToken instanceof Pawn nextPawn) || nextPawn.getTeam() != team) {
                return length;
            }
            length++;
            current = next;
        }
    }
}
