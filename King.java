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
public class King extends ChessPiece {

    public King(int i, int j, Color color) {
            this.x = i;
            this.y = j;
            this.color = color;
            this.symbol = "♔";
            this.name = "\u2654";
        }
    @Override
    public List<point> ValidMoves() {
        List<point> res = new ArrayList<point>();
        
        
        if (this.x>0) {
            //Check ở trên
            int i=this.x-1;
            int j=this.y;
            if (Board.chessBoard[i][j].getColor()==this.color) {
            
            }
            else {
                res.add(new point(i,j));
            }
        }
        
        if (this.x<7) {
            //Check ở dưới
            int i=this.x+1;
            int j=this.y;
            if (Board.chessBoard[i][j].getColor()==this.color) {
            
            }
            else {
                res.add(new point(i,j));
            }
        }
        
        if (this.x>0 && this.y>0) {
            //Check chéo trên trái
            int i=this.x-1;
            int j=this.y-1;
            if (Board.chessBoard[i][j].getColor()==this.color) {
            
            }
            else {
                res.add(new point(i,j));
            }
        }
        
        if (this.x>0 && this.y<7) {
            //Check chéo trên phải
            int i=this.x-1;
            int j=this.y+1;
            if (Board.chessBoard[i][j].getColor()==this.color) {
            
            }
            else {
                res.add(new point(i,j));
            }
        }
        
        if (this.y<7) {
            //Check phải
            int i=this.x;
            int j=this.y+1;
            if (Board.chessBoard[i][j].getColor()==this.color) {
            
            }
            else {
                res.add(new point(i,j));
            }
        }
        
        if (this.x<7 && this.y<7) {
            //Check dưới phải
            int i=this.x+1;
            int j=this.y+1;
            if (Board.chessBoard[i][j].getColor()==this.color) {
            
            }
            else {
                res.add(new point(i,j));
            }
        }
        
        if (this.x<7 && this.y>0) {
            //Check dưới trái
             int i=this.x+1;
            int j=this.y-1;
            if (Board.chessBoard[i][j].getColor()==this.color) {
            
            }
            else {
                res.add(new point(i,j));
            }
        }
        
        if (this.y>0) {
            //Check trái
            int i=this.x;
            int j=this.y-1;
            if (Board.chessBoard[i][j].getColor()==this.color) {
            
            }
            else {
                res.add(new point(i,j));
            }
        }
        
        return res;
    }
    
}
