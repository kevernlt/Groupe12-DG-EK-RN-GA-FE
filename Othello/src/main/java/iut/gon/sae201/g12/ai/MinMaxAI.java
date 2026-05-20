package iut.gon.sae201.g12.ai;

import iut.gon.sae201.g12.actions.Action;
import iut.gon.sae201.g12.utils.IState;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MinMaxAI implements AI {
    private int maxDepth;

    @Override
    public Action chooseMove(IState state) {
        Node currentState = new Node(state);
//        Action bestActionToRun
        return null;
    }
}
