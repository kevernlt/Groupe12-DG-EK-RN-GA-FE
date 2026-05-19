package iut.gon.sae201.g12;

import iut.gon.sae201.g12.utils.Coordinate;

import java.util.HashSet;

public class RemoveLine extends Action {
    private HashSet<Coordinate> lineCoordinates;
    private Coordinate ringToSacrifice;

    public RemoveLine(HashSet<Coordinate> lineCoordinates, Coordinate ringToSacrifice) {
        super();
        this.lineCoordinates = lineCoordinates;
        this.ringToSacrifice = ringToSacrifice;
    }

    public HashSet<Coordinate> getLine() {
        return this.lineCoordinates;
    }

    public void setline(HashSet<Coordinate> line) {
        return;
    }

    public Coordinate getRing() {
        return this.ringToSacrifice;
    }

    public void setRing(Coordinate ring) {
        return;
    }
}