package ch.fhnw.wodss.tippspiel.util;

public class Tuple<X, Y> {

    public final X left;
    public final Y right;

    public Tuple(X x, Y y) {
        this.left = x;
        this.right = y;
    }


}