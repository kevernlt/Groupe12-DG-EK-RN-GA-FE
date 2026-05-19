package iut.gon.sae201.g12.ai;

import iut.gon.sae201.g12.actions.Action;
import iut.gon.sae201.g12.utils.IState;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Node {
    private IState board;
    private Node parentNode;
    private Action boardAction;


    public Node(IState board) {
        this.board = board;
    }
}
