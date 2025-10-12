/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jvtest;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class King extends ChessPiece {
    int moveCount=0;

    public King(int i, int j, Color color) {
        this.x = i;
        this.y = j;
        this.color = color;
        this.symbol = "♔";
        this.name = "King";
        this.is_Chess = true;
        this.moveCount=0;
    }

    public void setMoveCount(int moveCount) {
        this.moveCount = moveCount;
    }

    @Override
    public List<point> ValidMoves() {
        List<point> res = new ArrayList<>();
        if (this.moveCount==0) {
            //Check bên phải
            for (int j=this.y+1;j<8;j++) {
                ChessPiece c = Board.chessBoard[this.x][j];
                if (c!=null && c.name!=null)
                {
                      if (!c.name.equals("Rock")) {
                        break;
                    
                }
                else {
                    Rock r = (Rock) c;
                    if (r.color==this.color && r.CanCastle()) {
                        res.add(new point(this.x,this.y+2,"Castle")); 
                    }
                }
                }
              
            }
            //Check bên trái
            for (int j=this.y-1;j>=0;j--) {
                ChessPiece c = Board.chessBoard[this.x][j];
                if (c!=null && c.name!=null)
                {
                     if (!c.name.equals("Rock")) {
                    if (c.name!=null) {
                        break;
                    }
                }
                else {
                    Rock r = (Rock) c;
                    if (r.color==this.color && r.CanCastle()) {
                        res.add(new point(this.x,this.y-2,"Castle"));
                    }
                } 
                }
              
            }
        }
        if (this.x > 0) {
            //Check ở trên
            int i = this.x - 1;
            int j = this.y;
            if (Board.chessBoard[i][j].getColor() == this.color) {

            } else {
                res.add(new point(i, j));
            }
        }

        if (this.x < 7) {
            //Check ở dưới
            int i = this.x + 1;
            int j = this.y;
            if (Board.chessBoard[i][j].getColor() == this.color) {

            } else {
                res.add(new point(i, j));
            }
        }

        if (this.x > 0 && this.y > 0) {
            //Check chéo trên trái
            int i = this.x - 1;
            int j = this.y - 1;
            if (Board.chessBoard[i][j].getColor() == this.color) {

            } else {
                res.add(new point(i, j));
            }
        }

        if (this.x > 0 && this.y < 7) {
            //Check chéo trên phải
            int i = this.x - 1;
            int j = this.y + 1;
            if (Board.chessBoard[i][j].getColor() == this.color) {

            } else {
                res.add(new point(i, j));
            }
        }

        if (this.y < 7) {
            //Check phải
            int i = this.x;
            int j = this.y + 1;
            if (Board.chessBoard[i][j].getColor() == this.color) {

            } else {
                res.add(new point(i, j));
            }
        }

        if (this.x < 7 && this.y < 7) {
            //Check dưới phải
            int i = this.x + 1;
            int j = this.y + 1;
            if (Board.chessBoard[i][j].getColor() == this.color) {

            } else {
                res.add(new point(i, j));
            }
        }

        if (this.x < 7 && this.y > 0) {
            //Check dưới trái
            int i = this.x + 1;
            int j = this.y - 1;
            if (Board.chessBoard[i][j].getColor() == this.color) {

            } else {
                res.add(new point(i, j));
            }
        }

        if (this.y > 0) {
            //Check trái
            int i = this.x;
            int j = this.y - 1;
            if (Board.chessBoard[i][j].getColor() == this.color) {

            } else {
                res.add(new point(i, j));
            }
        }
        res = res.stream().filter(pi -> CheckMateSingleMove(pi.i, pi.j)).collect(Collectors.toList());
        return res;
    }
    public boolean CheckMateSingleMove(int x, int y) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {                                                                                       
                ChessPiece cp = Board.chessBoard[i][j];
                if (cp.is_Chess && cp!=this && cp.color != GamePlay.turn) {
                    List<point> moves = cp.ValidMoves();
                    
                    // for (point pt: moves){
                    //     System.out.println(cp.name+ "   "+pt.i +" "+pt.j);
                    // }
                    if (moves != null) {
                        for(point p:moves){
                            System.out.println(p.i+"&"+x+"-" +p.j+"&"+y);
                            if (p.i==x && p.j==y) return false; 
                        }
                    }
                    //else 
                   // System.out.println(cp.name +" "+ x+" "+y+ " Khong co cho nao de doa!");
                }
            }
        }
        return true;
    }
    public boolean isCheckMate(){
        return !CheckMateSingleMove(this.x, this.y);
    }
}
