package iut.gon.sae201.g12.ai;

import iut.gon.sae201.g12.actions.Action;
import iut.gon.sae201.g12.utils.IState;

public interface AI {
    Action chooseMove(IState state);
}
