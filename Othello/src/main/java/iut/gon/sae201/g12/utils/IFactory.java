package iut.gon.sae201.g12.utils;

public interface IFactory {
    CoordinateCube fromDoubled(int x, int y);

    IState testState();
    IState stateForBlackLineTest();
    IState stateForWhiteLineTest();
    IState emptyState();
    IState doubleLineStateTest();
}