package iut.gon.sae201.g12;

import iut.gon.sae201.g12.interfaces.IFactory;
import iut.gon.sae201.g12.interfaces.IState;

public class FactoryCube implements IFactory {

    @Override
    public IState testState() {
        return null;
    }

    @Override
    public IState stateForBlackLineTest() {
        return null;
    }

    @Override
    public IState stateForWhiteLineTest() {
        return null;
    }

    @Override
    public IState emptyState() {
        return null;
    }

    @Override
    public IState doubleLineStateTest() {
        return null;
    }
}