package iut.gon.sae201.g12.utils;

public interface IFactory {
    IState testState();
    IState stateForBlackLineTest();
    IState stateForWhiteLineTest();
    IState emptyState();
    IState doubleLineStateTest();
}