package iut.gon.sae201.g12.ui;

import iut.gon.sae201.g12.factory.FactoryCube;
import iut.gon.sae201.g12.factory.FactoryDoubled;
import iut.gon.sae201.g12.tokens.Pawn;
import iut.gon.sae201.g12.tokens.Ring;
import iut.gon.sae201.g12.tokens.Token;
import iut.gon.sae201.g12.utils.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        if (this.dimensionMode.equals(DimensionMode.CUBE)) {
            this.factory = new FactoryCube();
        }
        if (this.dimensionMode.equals(DimensionMode.DOUBLED)) {
            this.factory = new FactoryDoubled();
        }
    }

    public void showPlate() {


        Map<Point, Token> board = this.gameState.board().entrySet().stream()
                .map(coordinateTokenEntry -> Map.entry(
                        coordinateTokenEntry.getKey().to2DCoordinate(),
                        coordinateTokenEntry.getValue()
                ))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ));
        for (int i = 0; i <= 10; i++) {
            for (int j = 0; j <= 18; j++) {
                if(board.containsKey(new Point(i,j))) {
                    if(board.get(new Point(i,j)) == null) {
                        System.out.println(" ");
                    }
                    if(board.get(new Point(i,j)) instanceof Pawn p ) {
                        System.out.println(p.charRepr());
                    }
                    if(board.get(new Point(i,j)) instanceof Ring r) {
                        System.out.println(r.charRepr());
                    }
                } else {
                    System.out.println(" ");
                }
            }
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
