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
public class Bishop2 extends ChessPiece2 {

    public Bishop2(Color color) {
        this.color = color;
        this.symbol = "♗";
        this.name = "Bishop";
        this.is_Chess=true;
    }
    public Bishop2(int i, int j, Color color) {
            this.x = i;
            this.y = j;
            this.color = color;
            this.symbol = "♗";
            this.name = "Bishop";
            this.is_Chess=true;
        }
    @Override
    public List<point> ValidMoves() {
        List<point> res = new ArrayList<>();
        this.PotentialMoves = new ArrayList<>();
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        if (this.x > 0 && this.y > 0) {
            //Check chéo trên trái
            int i = this.x - 1;
            int j = this.y - 1;
            while (i >= 0 && j >= 0) {
                this.PotentialMoves.add(new point(i, j));
                if (Board2.chessBoard[i][j].getName() == null) {
                    res.add(new point(i, j));
                } else {
                    if (Board2.chessBoard[i][j].getColor() == this.color) {
                        break;
                    } else {
                        res.add(new point(i, j));
                        break;
                    }
                }
                i--;
                j--;
            }
        }

        if (this.x > 0 && this.y < 7) {
            //Check chéo trên phải
            int i = this.x - 1;
            int j = this.y + 1;
            while (i >= 0 && j < 8) {
                this.PotentialMoves.add(new point(i, j));
                if (Board2.chessBoard[i][j].getName() == null) {
                    res.add(new point(i, j));
                } else {
                    if (Board2.chessBoard[i][j].getColor() == this.color) {
                        break;
                    } else {
                        res.add(new point(i, j));
                        break;
                    }
                }
                i--;
                j++;
            }
        }

        if (this.x < 7 && this.y > 0) {
            //Check chéo dưới trái
            int i = this.x + 1;
            int j = this.y - 1;
            while (i < 8 && j >= 0) {
                this.PotentialMoves.add(new point(i, j));
                if (Board2.chessBoard[i][j] == null || Board2.chessBoard[i][j].getName() == null) {
                    res.add(new point(i, j));
                } else {
                    if (Board2.chessBoard[i][j].getColor() == this.color) {
                        break;
                    } else {
                        res.add(new point(i, j));
                        break;
                    }
                }
                i++;
                j--;
            }
        }

        if (this.x < 7 && this.y < 7) {
            //Check chéo dưới phải
            int i = this.x + 1;
            int j = this.y + 1;
            while (i < 8 && j < 8) {
                this.PotentialMoves.add(new point(i, j));
                if (Board2.chessBoard[i][j].getName() == null) {
                    res.add(new point(i, j));
                } else {
                    if (Board2.chessBoard[i][j].getColor() == this.color) {
                        break;
                    } else {
                        res.add(new point(i, j));
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
