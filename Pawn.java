/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jvtest;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class Pawn extends ChessPiece { //Quân tốt

    @Override
    public List<point> ValidMoves(){
            List<point> res = new ArrayList<>(); //
            if (color == Color.WHITE) 
            {
                int index= (x+1)*8+y;
                if (Board.chessBoard.get(index).occupied==false){
                    res.add(new point(x+1,y));
                }
            } 
            else 
            {
                int index= (x-1)*8+y;
                if (Board.chessBoard.get(index).occupied==false){
                    res.add(new point(x-1,y));
                }
            }            
            return res;
        }
    public Pawn(int i, int j, Color color) {
            this.x = i;
            this.y = j;
            this.color = color;
            this.symbol = "♙";
            this.name = "Pawn";
        }
}
