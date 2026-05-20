package iut.gon.sae201.g12;

import iut.gon.sae201.g12.ai.MinMaxAI;
import iut.gon.sae201.g12.factory.FactoryDoubled;
import iut.gon.sae201.g12.utils.IFactory;

public class MainAI {
    public static void main(String[] args) {
        IFactory factory = new FactoryDoubled();
        MinMaxAI mainAI = new MinMaxAI(2);
        mainAI.chooseMove(factory.doubleLineStateTest());
        System.out.println(mainAI);
    }
}
