package iut.gon.sae201.g12.ai;

import iut.gon.sae201.g12.actions.Action;
import iut.gon.sae201.g12.actions.Move;
import iut.gon.sae201.g12.tokens.Token;
import iut.gon.sae201.g12.utils.Coordinate;
import iut.gon.sae201.g12.utils.IState;
import lombok.Getter;

import java.util.ArrayList;
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
    public Action chooseMove(IState state) {
        Node currentState = new Node(state);
        Action bestActionToRun = null;

        List<Node> childs = getAllPossibleActions(currentState);
        return null;
    }

    private List<Node> getAllPossibleActions(Node root) {
        List<Node> childs = new ArrayList<>();
        IState currentState = root.getGameState();

        for (Map.Entry<Coordinate, Token> tokensOfCurrentPlayer : currentState.board()
                .entrySet().stream().filter(
                        entry -> entry.getValue() != null && entry.getValue().getTeam() == currentState.turn()).toList()
        ) {
            for (Coordinate move : currentState.availableMoves(tokensOfCurrentPlayer.getKey())) {
                Move moveAction = new Move(tokensOfCurrentPlayer.getKey(), move);
                childs.add(new Node(currentState.move(moveAction), root, moveAction));
            }
        }

        return childs;
    }

    @Override
    public String toString() {
        return "MinMaxAI{" +
                "maxDepth=" + maxDepth +
                ", opponentDebilness=" + opponentDebilness +
                '}';
    }
}
