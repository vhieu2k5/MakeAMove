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

<<<<<<< HEAD
=======
    public Pawn(Color color) {
        this.color = color;
        this.symbol = "♙";
        this.name = "Pawn";
    }
    
>>>>>>> f3ae1d26ca71d8f51bce23a135d334dfb9b6e40d
    public Pawn(int i, int j, Color color) {
            this.x = i;
            this.y = j;
            this.color = color;
            this.symbol = "♙";
            this.name = "Pawn";
        }

    @Override
    public List<point> ValidMoves() {
        List<point> res = new ArrayList<>();
        int reverse=1;
        if (this.color==Color.WHITE) {
            if (this.x ==1) {
                point p = new point(this.x+1*reverse,this.y);
<<<<<<< HEAD
                if (Board.chessBoard[p.i][p.j].getName()==null) {
                    res.add(p);
                }
                point p2 = new point(this.x+2*reverse,this.y);
                if (Board.chessBoard[p2.i][p2.j].getName()==null) {
                    res.add(p2);
                } 
                //Check Move ăn
                switch (this.y) {
                    case 0:
                       point p3 = new point(this.x+1*reverse,this.y+1);
                       if (Board.chessBoard[p3.i][p3.j].getName()!=null && Board.chessBoard[p3.i][p3.j].getColor()!=this.color) {
=======
                if (Board2.chessBoard[p.i][p.j].getName()==null) {
                    res.add(p);
                    point p2 = new point(this.x+2*reverse,this.y);
                    if (Board2.chessBoard[p2.i][p2.j].getName()==null) {
                    res.add(p2);
                } 
                }
                
                //Check Move ăn
                switch(this.y) {
                    case 0:
                       point p3 = new point(this.x+1*reverse,this.y+1);
                       if (Board2.chessBoard[p3.i][p3.j].getName()!=null && Board2.chessBoard[p3.i][p3.j].getColor()!=this.color) {
>>>>>>> f3ae1d26ca71d8f51bce23a135d334dfb9b6e40d
                        res.add(p3);
                        }
                    break;
                    case 7:
                       p3 = new point(this.x+1*reverse,this.y-1);
<<<<<<< HEAD
                       if (Board.chessBoard[p3.i][p3.j].getName()!=null && Board.chessBoard[p3.i][p3.j].getColor()!=this.color) {
=======
                       if (Board2.chessBoard[p3.i][p3.j].getName()!=null && Board2.chessBoard[p3.i][p3.j].getColor()!=this.color) {
>>>>>>> f3ae1d26ca71d8f51bce23a135d334dfb9b6e40d
                        res.add(p3);
                        }
                       break;
                    default:
                        p3 = new point(this.x+1*reverse,this.y-1);
<<<<<<< HEAD
                       if (Board.chessBoard[p3.i][p3.j].getName()!=null && Board.chessBoard[p3.i][p3.j].getColor()!=this.color) {
                        res.add(p3);
                       }
                       point p4 = new point(this.x+1*reverse,this.y+1);
                       if (Board.chessBoard[p4.i][p4.j].getName()!=null && Board.chessBoard[p4.i][p4.j].getColor()!=this.color) {
=======
                       if (Board2.chessBoard[p3.i][p3.j].getName()!=null && Board2.chessBoard[p3.i][p3.j].getColor()!=this.color) {
                        res.add(p3);
                       }
                       point p4 = new point(this.x+1*reverse,this.y+1);
                       if (Board2.chessBoard[p4.i][p4.j].getName()!=null && Board2.chessBoard[p4.i][p4.j].getColor()!=this.color) {
>>>>>>> f3ae1d26ca71d8f51bce23a135d334dfb9b6e40d
                        res.add(p4);
                        }
                        break;
                }
            }
            else {
                point p3 = new point(this.x+1*reverse,this.y);
<<<<<<< HEAD
                 if (Board.chessBoard[p3.i][p3.j].getName()==null) {
=======
                 if (Board2.chessBoard[p3.i][p3.j].getName()==null) {
>>>>>>> f3ae1d26ca71d8f51bce23a135d334dfb9b6e40d
                    res.add(p3);
                }
                switch (this.y) {
                    case 0:
                       p3 = new point(this.x+1*reverse,this.y+1);
<<<<<<< HEAD
                       if (Board.chessBoard[p3.i][p3.j].getName()!=null && Board.chessBoard[p3.i][p3.j].getColor()!=this.color) {
=======
                       if (Board2.chessBoard[p3.i][p3.j].getName()!=null && Board2.chessBoard[p3.i][p3.j].getColor()!=this.color) {
>>>>>>> f3ae1d26ca71d8f51bce23a135d334dfb9b6e40d
                        res.add(p3);
                        }
                    break;
                    case 7:
                       p3 = new point(this.x+1*reverse,this.y-1);
<<<<<<< HEAD
                       if (Board.chessBoard[p3.i][p3.j].getName()!=null && Board.chessBoard[p3.i][p3.j].getColor()!=this.color) {
=======
                       if (Board2.chessBoard[p3.i][p3.j].getName()!=null && Board2.chessBoard[p3.i][p3.j].getColor()!=this.color) {
>>>>>>> f3ae1d26ca71d8f51bce23a135d334dfb9b6e40d
                        res.add(p3);
                        }
                       break;
                    default:
                        p3 = new point(this.x+1*reverse,this.y-1);
<<<<<<< HEAD
                       if (Board.chessBoard[p3.i][p3.j].getName()!=null && Board.chessBoard[p3.i][p3.j].getColor()!=this.color) {
                        res.add(p3);
                       }
                       point p4 = new point(this.x+1*reverse,this.y+1);
                       if (Board.chessBoard[p4.i][p4.j].getName()!=null && Board.chessBoard[p4.i][p4.j].getColor()!=this.color) {
=======
                       if (Board2.chessBoard[p3.i][p3.j].getName()!=null && Board2.chessBoard[p3.i][p3.j].getColor()!=this.color) {
                        res.add(p3);
                       }
                       point p4 = new point(this.x+1*reverse,this.y+1);
                       if (Board2.chessBoard[p4.i][p4.j].getName()!=null && Board2.chessBoard[p4.i][p4.j].getColor()!=this.color) {
>>>>>>> f3ae1d26ca71d8f51bce23a135d334dfb9b6e40d
                        res.add(p4);
                        }
                        break;
                }
            }
        }
        else {
            reverse=-1;
            if (this.x ==6) {
                point p = new point(this.x+1*reverse,this.y);
<<<<<<< HEAD
                if (Board.chessBoard[p.i][p.j].getName()==null) {
                    res.add(p);
                }
                point p2 = new point(this.x+2*reverse,this.y);
                if (Board.chessBoard[p2.i][p2.j].getName()==null) {
=======
                if (Board2.chessBoard[p.i][p.j].getName()==null) {
                    res.add(p);
                }
                point p2 = new point(this.x+2*reverse,this.y);
                if (Board2.chessBoard[p2.i][p2.j].getName()==null) {
>>>>>>> f3ae1d26ca71d8f51bce23a135d334dfb9b6e40d
                    res.add(p2);
                }
                switch (this.y) {
                    case 0:
                       point p3 = new point(this.x+1*reverse,this.y+1);
<<<<<<< HEAD
                       if (Board.chessBoard[p3.i][p3.j].getName()!=null && Board.chessBoard[p3.i][p3.j].getColor()!=this.color) {
=======
                       if (Board2.chessBoard[p3.i][p3.j].getName()!=null && Board2.chessBoard[p3.i][p3.j].getColor()!=this.color) {
>>>>>>> f3ae1d26ca71d8f51bce23a135d334dfb9b6e40d
                        res.add(p3);
                        }
                    break;
                    case 7:
                       p3 = new point(this.x+1*reverse,this.y-1);
<<<<<<< HEAD
                       if (Board.chessBoard[p3.i][p3.j].getName()!=null && Board.chessBoard[p3.i][p3.j].getColor()!=this.color) {
=======
                       if (Board2.chessBoard[p3.i][p3.j].getName()!=null && Board2.chessBoard[p3.i][p3.j].getColor()!=this.color) {
>>>>>>> f3ae1d26ca71d8f51bce23a135d334dfb9b6e40d
                        res.add(p3);
                        }
                       break;
                    default:
                        p3 = new point(this.x+1*reverse,this.y-1);
<<<<<<< HEAD
                       if (Board.chessBoard[p3.i][p3.j].getName()!=null && Board.chessBoard[p3.i][p3.j].getColor()!=this.color) {
                        res.add(p3);
                       }
                       point p4 = new point(this.x+1*reverse,this.y+1);
                       if (Board.chessBoard[p4.i][p4.j].getName()!=null && Board.chessBoard[p4.i][p4.j].getColor()!=this.color) {
=======
                       if (Board2.chessBoard[p3.i][p3.j].getName()!=null && Board2.chessBoard[p3.i][p3.j].getColor()!=this.color) {
                        res.add(p3);
                       }
                       point p4 = new point(this.x+1*reverse,this.y+1);
                       if (Board2.chessBoard[p4.i][p4.j].getName()!=null && Board2.chessBoard[p4.i][p4.j].getColor()!=this.color) {
>>>>>>> f3ae1d26ca71d8f51bce23a135d334dfb9b6e40d
                        res.add(p4);
                        }
                        break;
                }
            }
            else {
                point p3 = new point(this.x+1*reverse,this.y);
<<<<<<< HEAD
                 if (Board.chessBoard[p3.i][p3.j].getName()==null) {
=======
                 if (Board2.chessBoard[p3.i][p3.j].getName()==null) {
>>>>>>> f3ae1d26ca71d8f51bce23a135d334dfb9b6e40d
                    res.add(p3);
                }
                switch (this.y) {
                    case 0:
                       p3 = new point(this.x+1*reverse,this.y+1);
<<<<<<< HEAD
                       if (Board.chessBoard[p3.i][p3.j].getName()!=null && Board.chessBoard[p3.i][p3.j].getColor()!=this.color) {
=======
                       if (Board2.chessBoard[p3.i][p3.j].getName()!=null && Board2.chessBoard[p3.i][p3.j].getColor()!=this.color) {
>>>>>>> f3ae1d26ca71d8f51bce23a135d334dfb9b6e40d
                        res.add(p3);
                        }
                    break;
                    case 7:
                       p3 = new point(this.x+1*reverse,this.y-1);
<<<<<<< HEAD
                       if (Board.chessBoard[p3.i][p3.j].getName()!=null && Board.chessBoard[p3.i][p3.j].getColor()!=this.color) {
=======
                       if (Board2.chessBoard[p3.i][p3.j].getName()!=null && Board2.chessBoard[p3.i][p3.j].getColor()!=this.color) {
>>>>>>> f3ae1d26ca71d8f51bce23a135d334dfb9b6e40d
                        res.add(p3);
                        }
                       break;
                    default:
                        p3 = new point(this.x+1*reverse,this.y-1);
<<<<<<< HEAD
                       if (Board.chessBoard[p3.i][p3.j].getName()!=null && Board.chessBoard[p3.i][p3.j].getColor()!=this.color) {
                        res.add(p3);
                       }
                       point p4 = new point(this.x+1*reverse,this.y+1);
                       if (Board.chessBoard[p4.i][p4.j].getName()!=null && Board.chessBoard[p4.i][p4.j].getColor()!=this.color) {
=======
                       if (Board2.chessBoard[p3.i][p3.j].getName()!=null && Board2.chessBoard[p3.i][p3.j].getColor()!=this.color) {
                        res.add(p3);
                       }
                       point p4 = new point(this.x+1*reverse,this.y+1);
                       if (Board2.chessBoard[p4.i][p4.j].getName()!=null && Board2.chessBoard[p4.i][p4.j].getColor()!=this.color) {
>>>>>>> f3ae1d26ca71d8f51bce23a135d334dfb9b6e40d
                        res.add(p4);
                        }
                        break;
                }
            }
        }
        return res;
    }
    
}