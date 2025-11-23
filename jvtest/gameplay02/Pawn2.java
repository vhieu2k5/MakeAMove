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
public class Pawn2 extends ChessPiece2 { //Quân tốt

    public Pawn2(Color color) {
        this.color = color;
        this.symbol = "♙";
        this.name = "Pawn";
        this.is_Chess = true;
    }

    public Pawn2(int i, int j, Color color) {
        this.x = i;
        this.y = j;
        this.color = color;
        this.symbol = "♙";
        this.name = "Pawn";
        this.is_Chess = true;
    }

    @Override
    public List<point> ValidMoves() {
        List<point> res = new ArrayList<>();
        this.PotentialMoves = new ArrayList<>();

        int reverse = 1;
        if (this.color == Color.black) {
            if (this.x == 1) {
                point p = new point(this.x + 1 * reverse, this.y);
                if (Board2.chessBoard[p.i][p.j].getName() == null) {
                    res.add(p);
                    point p2 = new point(this.x + 2 * reverse, this.y);
                    if (Board2.chessBoard[p2.i][p2.j].getName() == null) {
                        res.add(p2);
                    }
                }

                //Check Move ăn
                switch (this.y) {
                    case 0:
                        point p3 = new point(this.x + 1 * reverse, this.y + 1);
                        this.PotentialMoves.add(p3);
                        if (Board2.chessBoard[p3.i][p3.j].getName() != null && Board2.chessBoard[p3.i][p3.j].getColor() != this.color) {
                            res.add(p3);
                        }
                        break;
                    case 7:
                        p3 = new point(this.x + 1 * reverse, this.y - 1);
                         this.PotentialMoves.add(p3);
                        if (Board2.chessBoard[p3.i][p3.j].getName() != null && Board2.chessBoard[p3.i][p3.j].getColor() != this.color) {
                            res.add(p3);
                        }
                        break;
                    default:
                        p3 = new point(this.x + 1 * reverse, this.y - 1);
                        this.PotentialMoves.add(p3);
                        if (Board2.chessBoard[p3.i][p3.j].getName() != null && Board2.chessBoard[p3.i][p3.j].getColor() != this.color) {
                            res.add(p3);
                        }
                        point p4 = new point(this.x + 1 * reverse, this.y + 1);
                        this.PotentialMoves.add(p4);
                        if (Board2.chessBoard[p4.i][p4.j].getName() != null && Board2.chessBoard[p4.i][p4.j].getColor() != this.color) {
                            res.add(p4);
                        }
                        break;
                }
            } else {
                point p3 = new point(this.x + 1 * reverse, this.y);
                if (Board2.chessBoard[p3.i][p3.j].getName() == null) {
                    res.add(p3);
                }
                switch (this.y) {
                    case 0:
                        p3 = new point(this.x + 1 * reverse, this.y + 1);
                        this.PotentialMoves.add(p3);
                        if (Board2.chessBoard[p3.i][p3.j].getName() != null && Board2.chessBoard[p3.i][p3.j].getColor() != this.color) {
                            res.add(p3);
                        }
                        break;
                    case 7:
                        p3 = new point(this.x + 1 * reverse, this.y - 1);
                        this.PotentialMoves.add(p3);
                        if (Board2.chessBoard[p3.i][p3.j].getName() != null && Board2.chessBoard[p3.i][p3.j].getColor() != this.color) {
                            res.add(p3);
                        }
                        break;
                    default:
                        p3 = new point(this.x + 1 * reverse, this.y - 1);
                        this.PotentialMoves.add(p3);
                        if (Board2.chessBoard[p3.i][p3.j].getName() != null && Board2.chessBoard[p3.i][p3.j].getColor() != this.color) {
                            res.add(p3);
                        }
                        point p4 = new point(this.x + 1 * reverse, this.y + 1);
                        this.PotentialMoves.add(p4);
                        if (Board2.chessBoard[p4.i][p4.j].getName() != null && Board2.chessBoard[p4.i][p4.j].getColor() != this.color) {
                            res.add(p4);
                        }
                        break;
                }
            }
        } else {
            reverse = -1;
            if (this.x == 6) {
                point p = new point(this.x + 1 * reverse, this.y);
                if (Board2.chessBoard[p.i][p.j].getName() == null) {
                    res.add(p);
                    point p2 = new point(this.x + 2 * reverse, this.y);
                    if (Board2.chessBoard[p2.i][p2.j].getName() == null) {
                        res.add(p2);
                    }
                }
                switch (this.y) {
                    case 0:
                        point p3 = new point(this.x + 1 * reverse, this.y + 1);
                        this.PotentialMoves.add(p3);
                        if (Board2.chessBoard[p3.i][p3.j].getName() != null && Board2.chessBoard[p3.i][p3.j].getColor() != this.color) {
                            res.add(p3);
                        }
                        break;
                    case 7:
                        p3 = new point(this.x + 1 * reverse, this.y - 1);
                        this.PotentialMoves.add(p3);
                        if (Board2.chessBoard[p3.i][p3.j].getName() != null && Board2.chessBoard[p3.i][p3.j].getColor() != this.color) {
                            res.add(p3);
                        }
                        break;
                    default:
                        p3 = new point(this.x + 1 * reverse, this.y - 1);
                        this.PotentialMoves.add(p3);
                        if (Board2.chessBoard[p3.i][p3.j].getName() != null && Board2.chessBoard[p3.i][p3.j].getColor() != this.color) {
                            res.add(p3);
                        }
                        point p4 = new point(this.x + 1 * reverse, this.y + 1);
                        this.PotentialMoves.add(p4);
                        if (Board2.chessBoard[p4.i][p4.j].getName() != null && Board2.chessBoard[p4.i][p4.j].getColor() != this.color) {
                            res.add(p4);
                        }
                        break;
                }
            } else {
                if (this.x + 1 * reverse >= 0) //Check điều kiện để lúc phong không bị lỗi over index
                {
                    point p3 = new point(this.x + 1 * reverse, this.y);
                    if (Board2.chessBoard[p3.i][p3.j].getName() == null) {
                        res.add(p3);
                    }
                    switch (this.y) {
                        case 0:
                            p3 = new point(this.x + 1 * reverse, this.y + 1);
                            if (Board2.chessBoard[p3.i][p3.j].getName() != null && Board2.chessBoard[p3.i][p3.j].getColor() != this.color) {
                                this.PotentialMoves.add(p3);
                                res.add(p3);
                            }
                            break;
                        case 7:
                            p3 = new point(this.x + 1 * reverse, this.y - 1);
                            if (Board2.chessBoard[p3.i][p3.j].getName() != null && Board2.chessBoard[p3.i][p3.j].getColor() != this.color) {
                                this.PotentialMoves.add(p3);
                                res.add(p3);
                            }
                            break;
                        default:
                            p3 = new point(this.x + 1 * reverse, this.y - 1);
                            if (Board2.chessBoard[p3.i][p3.j].getName() != null && Board2.chessBoard[p3.i][p3.j].getColor() != this.color) {
                                this.PotentialMoves.add(p3);
                                res.add(p3);
                            }
                            point p4 = new point(this.x + 1 * reverse, this.y + 1);
                            if (Board2.chessBoard[p4.i][p4.j].getName() != null && Board2.chessBoard[p4.i][p4.j].getColor() != this.color) {
                                this.PotentialMoves.add(p4);
                                res.add(p4);
                            }
                            break;
                    }
                }
            }
        }

        return res;
    }

}
