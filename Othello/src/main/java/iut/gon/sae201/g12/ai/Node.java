package iut.gon.sae201.g12.ai;

import iut.gon.sae201.g12.actions.Action;
import iut.gon.sae201.g12.utils.IState;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Node {
    @Getter
    private IState gameState;
    private Node parentNode;
    private Action boardAction;


    public Node(IState gameState) {
        this.gameState = gameState;
    }
}
