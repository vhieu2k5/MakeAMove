/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jvtest;

import java.awt.Color;
import  java.util.List;
import javax.swing.BorderFactory;

/**
 *
 * @author ADMIN
 */
public abstract class ChessPiece { //Lớp đặc tính chung của các quân cờ
    
        int x, y;
        boolean is_Chess = false;
        Color color; //black or white
        String symbol;
        String name;
        public abstract List<point> ValidMoves();
        public void showValidMove(GamePlay ui) {
            List<point> p = this.ValidMoves();
        for (point po:p) {
            //System.out.println(po.i + " " + po.j);
            ui.squares[po.i][po.j].setBorderPainted(true);
            ui.squares[po.i][po.j].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
        }
        }
        public void deleteValidMove(GamePlay ui) {
            List<point> p = this.ValidMoves();
        for (point po:p) {
            ui.squares[po.i][po.j].setBorderPainted(false);
        }
        }

    public void setIs_Chess(boolean is_Chess) {
        this.is_Chess = is_Chess;
    }

    public String getName() {
        return name;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setName(String name) {
        this.name = name;
    }

        public void setMove (point pre, int i, int j){ //Set là ô đã có quân cờ đặt
            if (pre!=null && pre.i>=0 && pre.j>=0) {
               Board.chessBoard[pre.i][pre.j] = new NullChess(pre.i,pre.j);
            }
            Board.chessBoard[i][j]=this;
            this.x = i;
            this.y=j;
        }

    public Color getColor() {
        return color;
    }


    }
    
    


