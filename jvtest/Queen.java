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
public class Queen extends ChessPiece {

    public Queen(int i, int j, Color color) {
            this.x = i;
            this.y = j;
            this.color = color;
            this.symbol = "♕";
            this.name = "Queen";
            this.is_Chess=true;
        }
    @Override
    public List<point> ValidMoves() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        List<point> res = new ArrayList<>();
        int j=this.y;
            //Check từ trên xuống
            for (int i=this.x-1;i>=0;i--) {
                if (Board.chessBoard[i][j].getName()==null) {
                    res.add(new point(i,j));
                }
                else {
                    if (Board.chessBoard[i][j].getColor()==this.color) {
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
                if (Board.chessBoard[i][j].getName()==null) {
                    res.add(new point(i,j));
                }
                else {
                    if (Board.chessBoard[i][j].getColor()==this.color) {
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
                if (Board.chessBoard[i][j].getName()==null) {
                    res.add(new point(i,j));
                }
                else {
                    if (Board.chessBoard[i][j].getColor()==this.color) {
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
                if (Board.chessBoard[i][j].getName()==null) {
                    res.add(new point(i,j));
                }
                else {
                    if (Board.chessBoard[i][j].getColor()==this.color) {
                        break;
                    }
                    else {
                        res.add(new point(i,j));
                        break;
                    }
                }
            }
            
if (this.x>0 && this.y>0) {
       //Check chéo trên trái
         i=this.x-1;
        j=this.y-1;
       while (i>=0 && j>=0) {
           if (Board.chessBoard[i][j].getName()==null) {
                    res.add(new point(i,j));
                }
                else {
                    if (Board.chessBoard[i][j].getColor()==this.color) {
                        break;
                    }
                    else {
                        res.add(new point(i,j));
                        break;
                    }
                }
           i--;
           j--;
       }
       }
        
       if (this.x>0 && this.y<7) {
           //Check chéo trên phải
        i=this.x-1;
        j=this.y+1;
       while (i>0 && j<8) {
           if (Board.chessBoard[i][j].getName()==null) {
                    res.add(new point(i,j));
                }
                else {
                    if (Board.chessBoard[i][j].getColor()==this.color) {
                        break;
                    }
                    else {
                        res.add(new point(i,j));
                        break;
                    }
                }
           i--;
           j++;
       }
       }
       
       if (this.x<7 && this.y>0) {
           //Check chéo dưới trái
        i=this.x+1;
        j=this.y-1;
       while (i<8 && j>0) {
           if (Board.chessBoard[i][j].getName()==null) {
                    res.add(new point(i,j));
                }
                else {
                    if (Board.chessBoard[i][j].getColor()==this.color) {
                        break;
                    }
                    else {
                        res.add(new point(i,j));
                        break;
                    }
                }
           i++;
           j--;
       }
       }
       
       if (this.x<7 && this.y<7) {
           //Check chéo dưới phải
        i=this.x+1;
        j=this.y+1;
       while (i<8 && j<8) {
           if (Board.chessBoard[i][j].getName()==null) {
                    res.add(new point(i,j));
                }
                else {
                    if (Board.chessBoard[i][j].getColor()==this.color) {
                        break;
                    }
                    else {
                        res.add(new point(i,j));
                        break;
                    }
                }
           i++;
           j++;
       }
       }
            
            return res;
    }
    
}
