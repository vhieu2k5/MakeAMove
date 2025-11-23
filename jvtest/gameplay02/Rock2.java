/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package jvtest.gameplay02;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import jvtest.*;

/**
 *
 * @author ADMIN
 */
public class Rock2 extends ChessPiece2 {
    int moveCount=0;
    
    public Rock2(Color color) {
        this.color = color;
        this.symbol = "♜";
        this.name = "Rock";
        this.is_Chess=true; 
    }
    
        public Rock2(int i, int j, Color color) {
            this.x = i;
            this.y = j;
            this.color = color;
            this.symbol = "♜";
            this.name = "Rock";
            this.is_Chess=true;
        }

    @Override
    public List<point> ValidMoves() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        List<point> res = new ArrayList<>();
        this.PotentialMoves = new ArrayList<>();
        int j=this.y;

            //Check từ trên xuống
            for (int i=this.x-1;i>=0;i--) {
                this.PotentialMoves.add(new point(i, j));
                if (Board2.chessBoard[i][j].getName()==null) {
                    res.add(new point(i,j));
                }
                else {
                    if (Board2.chessBoard[i][j].getColor()==this.color) {
                        break;
                    }
                    else {
                        res.add(new point(i,j));
                        break;
                    }
                }
            }
            //System.out.println("Check tren xong");
            
            //Check từ vị trí hiện tại xuống dưới
            for (int i=this.x+1;i<8;i++) {
                this.PotentialMoves.add(new point(i, j));
                if (Board2.chessBoard[i][j].getName()==null) {
                    res.add(new point(i,j));
                }
                else {
                    if (Board2.chessBoard[i][j].getColor()==this.color) {
                        break;
                    }
                    else {
                        res.add(new point(i,j));
                        break;
                    }
                }
            }
            //System.out.println("Check duoi xong");
            
            //Check từ trái sang
            int i=this.x;
            for (j=this.y-1;j>=0;j--) {
                this.PotentialMoves.add(new point(i, j));
                if (Board2.chessBoard[i][j].getName()==null) {
                    res.add(new point(i,j));
                }
                else {
                    if (Board2.chessBoard[i][j].getColor()==this.color) {
                        break;
                    }
                    else {
                        res.add(new point(i,j));
                        break;
                    }
                }
            }
            
            //Check bên phải
            for (j=this.y+1;j<8;j++) {
                this.PotentialMoves.add(new point(i, j));
                if (Board2.chessBoard[i][j].getName()==null) {
                    res.add(new point(i,j));
                }
                else {
                    if (Board2.chessBoard[i][j].getColor()==this.color) {
                        break;
                    }
                    else {
                        res.add(new point(i,j));
                        break;
                    }
                }
            }
     

        return res;
    }
    public boolean CanCastle() {
        return moveCount==1;
    }

}
