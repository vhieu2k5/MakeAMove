package jvtest;

import java.awt.Color;
public class MoveHistory {
    public int fromR, fromC;
    public int toR, toC;
    public ChessPiece movedPiece;
    public ChessPiece capturedPiece;
    public Color prevTurn;

    public MoveHistory(int fr, int fc, int tr, int tc,
                       ChessPiece moved, ChessPiece captured, Color turn) {
        fromR = fr; fromC = fc;
        toR = tr; toC = tc;
        movedPiece = moved;
        capturedPiece = captured;
        prevTurn = turn;
    }
}

