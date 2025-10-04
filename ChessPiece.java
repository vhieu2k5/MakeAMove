/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jvtest;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import  java.util.List;

/**
 *
 * @author ADMIN
 */
public abstract class ChessPiece { //Lớp đặc tính chung của các quân cờ
    
        int x, y;
        Color color; //black or white
        String symbol;
        String name;
        public abstract List<point> ValidMoves();
        public point currentMove(){
            return new point(x,y); 
        }
        public void setMove (int i, int j){ //Set là ô đã có quân cờ đặt
            this.x = i;
            this.y = j;
            int index = 8*i +j;
            Board.chessBoard.get(index).occupied = true;
            Board.chessBoard.get(index).piece = name;
            Board.chessBoard.get(index).c = color;
        }
    }
    
    


