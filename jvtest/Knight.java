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
public class Knight extends ChessPiece {
    public Knight(int i, int j, Color color) {
            this.x = i;
            this.y = j;
            this.color = color;
            this.symbol = "♘";
            this.name = "Knight";
            this.is_Chess=true;
        }
    @Override
    public List<point> ValidMoves() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        List<point> res = new ArrayList<>();
        
        int i;
        int j;
        
        if (this.x>1 && this.y<7) {
            //Check chéo trên phải 1
        i=this.x-2;
        j=this.y+1;
        if (Board.chessBoard[i][j].getColor()==this.color) {
            
            }
            else {
                res.add(new point(i,j));
            }
        }
        
        if (this.x>0 && this.y<6) {
            //Check chéo trên phải 2
        i=this.x-1;
        j=this.y+2;
        if (Board.chessBoard[i][j].getColor()==this.color) {
            
            }
            else {
                res.add(new point(i,j));
            }
        }
        
        if (this.x<7 && this.y<6) {
            //Check chéo dưới phải 1
        i=this.x+1;
        j=this.y+2;
        if (Board.chessBoard[i][j].getColor()==this.color) {
            
            }
            else {
                res.add(new point(i,j));
            }
        }
        
        if (this.x<6 && this.y<7) {
            //Check chéo dưới phải 2
        i=this.x+2;
        j=this.y+1;
        if (Board.chessBoard[i][j].getColor()==this.color) {
            
            }
            else {
                res.add(new point(i,j));
            }
        }
        
        if (this.x<6 && this.y>0) {
            //Check chéo dưới trái 1
        i=this.x+2;
        j=this.y-1;
        if (Board.chessBoard[i][j].getColor()==this.color) {
            
            }
            else {
                res.add(new point(i,j));
            }
        }
        
        if (this.x<7 && this.y>1) {
            //Check chéo dưới trái 2
        i=this.x+1;
        j=this.y-2;
        if (Board.chessBoard[i][j].getColor()==this.color) {
            
            }
            else {
                res.add(new point(i,j));
            }
        }
        
        if (this.x>0 && this.y>1) {
            //Check chéo trên trái 1
        i=this.x-1;
        j=this.y-2;

        if (Board.chessBoard[i][j].getColor()==this.color) {

            
            }
            else {
                res.add(new point(i,j));
            }
        }
        
        if (this.x>1 && this.y>0) {
            //Check chéo trên trái 2
        i=this.x-2;
        j=this.y-1;
        if (Board.chessBoard[i][j].getColor()==this.color) {
            
            }
            else {
                res.add(new point(i,j));
            }
        }
        
    
    return res;
    }
}