package iut.gon.sae201.g12.ui;

import iut.gon.sae201.g12.factory.FactoryCube;
import iut.gon.sae201.g12.factory.FactoryDoubled;
import iut.gon.sae201.g12.utils.IFactory;
import iut.gon.sae201.g12.utils.IState;
import iut.gon.sae201.g12.utils.Mode;
import lombok.Getter;
import lombok.Setter;

public class GameManager {
    @Getter
    IFactory factory;
    @Setter
    IState gameState;
    @Setter
    PlayMode playMode;
    @Setter
    Mode boardMode;
    @Setter
    DimensionMode dimensionMode;

    public void buildBoardFactory() {
        if(this.dimensionMode.equals(DimensionMode.CUBE)) {
            this.factory = new FactoryCube();
        }
        if(this.dimensionMode.equals(DimensionMode.DOUBLED)) {
            this.factory = new FactoryDoubled();
        }
    }

    public enum PlayMode {
        PLAYER_VERSUS,
        AI_VERSUS;
    }

    public enum DimensionMode {
        CUBE,
        DOUBLED;
    }
}
