package iut.gon.sae201.g12.ai;

import iut.gon.sae201.g12.State;
import iut.gon.sae201.g12.actions.Action;
import iut.gon.sae201.g12.actions.Move;
import iut.gon.sae201.g12.tokens.Pawn;
import iut.gon.sae201.g12.tokens.Ring;
import iut.gon.sae201.g12.tokens.Token;
import iut.gon.sae201.g12.utils.Coordinate;
import iut.gon.sae201.g12.utils.IState;
import iut.gon.sae201.g12.utils.Team;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MinMaxAI implements AI {
    @Getter
    private final int maxDepth;
    private int opponentDebilness;

    public MinMaxAI(int maxDepth) {
        this.maxDepth = maxDepth;
    }

    @Override
    public Action chooseMove(IState state) throws RuntimeException {
        Team maximizingTeam = state.turn();

        List<Move> rootMoves = getAllPossibleMoves(state);
        if (rootMoves.isEmpty()) {
            throw new RuntimeException("No possible moves");
        }

        // Evaluate each root move by running minimax on the resulting state (depth-1)
        Move bestMove = null;
        int bestScore = Integer.MIN_VALUE;
        int secondBest = Integer.MIN_VALUE;

        for (Move m : rootMoves) {
            IState childState = simulateMove(state, m);
            SearchResult res = minimax(new Node(childState), maxDepth - 1, Integer.MIN_VALUE, Integer.MAX_VALUE, maximizingTeam, m);
            int score = res.score();
            if (score > bestScore) {
                secondBest = bestScore;
                bestScore = score;
                bestMove = m;
            } else if (score > secondBest) {
                secondBest = score;
            }
        }

        // opponentDebilness: how much better the best reply is compared to the next best reply
        if (secondBest == Integer.MIN_VALUE) {
            // only one move available: debilness is bestScore (relative to 0)
            opponentDebilness = bestScore;
        } else {
            opponentDebilness = bestScore - secondBest;
        }

        return bestMove;
    }

    private SearchResult minimax(Node node, int depth, int alpha, int beta, Team maximizingTeam, Move rootAction) {
        IState state = node.getGameState();
        List<Move> moves = getAllPossibleMoves(state);

        if (depth == 0 || moves.isEmpty()) {
            return new SearchResult(node.evaluate(maximizingTeam), rootAction);
        }

        boolean maximizing = state.turn() == maximizingTeam;
        SearchResult best = new SearchResult(maximizing ? Integer.MIN_VALUE : Integer.MAX_VALUE, rootAction);

        for (Move move : moves) {
            IState nextState = simulateMove(state, move);
            Move nextRootAction = rootAction == null ? move : rootAction;
            SearchResult child = minimax(new Node(nextState, node, move), depth - 1, alpha, beta, maximizingTeam.other(), nextRootAction);

            if (maximizing) {
                if (child.score() > best.score()) {
                    best = new SearchResult(child.score(), nextRootAction);
                }
                alpha = Math.max(alpha, best.score());
            } else {
                if (child.score() < best.score()) {
                    best = new SearchResult(child.score(), nextRootAction);
                }
                beta = Math.min(beta, best.score());
            }

            if (alpha >= beta) {
                break;
            }
        }

        return best;
    }

    private List<Move> getAllPossibleMoves(IState state) {
        List<Move> allMoves = collectMovesForTeam(state, state.turn());
        if (allMoves.isEmpty()) {
            allMoves = collectMovesForTeam(state, state.turn().other());
        }
        return allMoves;
    }

    private List<Move> collectMovesForTeam(IState state, Team team) {
        List<Move> allMoves = new ArrayList<>();

        for (Map.Entry<Coordinate, Token> entry : state.board().entrySet()) {
            Token token = entry.getValue();
            if (!(token instanceof Ring) || token.getTeam() != team) {
                continue;
            }

            Coordinate from = entry.getKey();
            for (Coordinate to : legalDestinations(state, from, team)) {
                allMoves.add(new Move(from, to));
            }
        }

        return allMoves;
    }

    private List<Coordinate> legalDestinations(IState state, Coordinate from, Team team) {
        List<Coordinate> destinations = new ArrayList<>();
        for (Coordinate to : state.availableMoves(from)) {
            Token origin = state.board().get(from);
            if (origin instanceof Ring ring && ring.getTeam() == team) {
                destinations.add(to);
            }
        }
        return destinations;
    }

    private IState simulateMove(IState state, Move move) {
        Map<Coordinate, Token> newBoard = new HashMap<>(state.board());
        Token moving = newBoard.get(move.getFrom());
        if (!(moving instanceof Ring ring)) {
            return state;
        }

        Team team = ring.getTeam();
        newBoard.put(move.getFrom(), new Pawn(team));
        newBoard.put(move.getTo(), new Ring(team));
        return new State(newBoard, team.other(), new ArrayList<>(state.lines()));
    }

    private record SearchResult(int score, Move action) {}

    @Override
    public String toString() {
        return "MinMaxAI{" +
                "maxDepth=" + maxDepth +
                ", opponentDebilness=" + opponentDebilness +
                '}';
    }
}
