package iut.gon.sae201.g12;

import iut.gon.sae201.g12.utils.CoordinateCube;
import iut.gon.sae201.g12.utils.Mode;

public class Main {
    public static void main(String[] args) {
        CoordinateCube test = new CoordinateCube(0,0,0 );
        test.N(Mode.FLAT);
        System.out.println("It works!");
    }
}
