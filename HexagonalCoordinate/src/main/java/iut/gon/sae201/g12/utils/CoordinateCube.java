package iut.gon.sae201.g12.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.security.InvalidParameterException;
import java.util.List;

@Getter
@AllArgsConstructor
public class CoordinateCube extends Coordinate {
    private int q;
    private int r;
    private int s;

    @Override
    public Point to2DCoordinate() {
        return null;
    }

    @Override
    public Coordinate toDir(Mode mode, Direction direction) {
        return null;
    }

    @Override
    public List<Coordinate> getNeighbors(Mode mode) {
        return List.of();
    }

    @Override
    public List<Coordinate> between(Mode mode, Coordinate to) {
        return List.of();
    }

    @Override
    public Coordinate NO(Mode mode) {

    if(mode.equals(Mode.FLAT)){

        if( this.s >= 3 || this.q <= -3 ) throw new InvalidParameterException("Tu es déjà au bord");

        return new CoordinateCube(this.q-1,this.r,this.s+1);

    }else{

        if( this.s >= 3 || this.r <= -3 ) throw new InvalidParameterException("Tu es déjà au bord");

        return new CoordinateCube(this.q,this.r-1,this.s+1);

    }

    }

    @Override
    public Coordinate NE(Mode mode) {
        return null;
    }

    @Override
    public Coordinate E(Mode mode) {

        if(mode.equals(Mode.FLAT)) throw new InvalidParameterException("Tu ne peux pas aller a l'est");

        if( this.q >= 3 || this.s <= -3 ) throw new InvalidParameterException("Tu es déjà au bord");

        return new CoordinateCube(this.q+1,this.r,this.s-1);
    }

    @Override
    public Coordinate O(Mode mode) {

        if(mode.equals(Mode.FLAT)) throw new InvalidParameterException("Tu ne peux pas aller a l'ouest");

        if( this.s >= 3 || this.q <= -3 ) throw new InvalidParameterException("Tu es déjà au bord");

        return new CoordinateCube(this.q-1,this.r,this.s+1);

    }

    @Override
    public Coordinate N(Mode mode) {
        if(mode.equals(Mode.POINTY)) throw new InvalidParameterException("Tu ne peux pas aller au nord");

        if( this.s >= 3 || this.r <= -3 ) throw new InvalidParameterException("Tu es déjà au bord");

        return new CoordinateCube(this.q,this.r-1,this.s+1);
    }

    @Override
    public Coordinate S(Mode mode) {
        if(mode.equals(Mode.POINTY)) throw new InvalidParameterException("Tu ne peux pas aller au sud");

        if( this.s <= -3 || this.r >= 3 ) throw new InvalidParameterException("Tu es déjà au bord");

        return new CoordinateCube(this.q,this.r+1,this.s-1);
    }


    @Override
    public Coordinate SO(Mode mode) {
        return null;
    }

    @Override
    public Coordinate SE(Mode mode) {
        if(mode.equals(Mode.FLAT)){

            if( this.q >= 3 || this.s <= -3 ) throw new InvalidParameterException("Tu es déjà au bord");

            return new CoordinateCube(this.q+1,this.r,this.s-1);

        }else{

            if( this.r >= 3 || this.s <= -3 ) throw new InvalidParameterException("Tu es déjà au bord");

            return new CoordinateCube(this.q,this.r+1,this.s-1);

        }
    }

}
