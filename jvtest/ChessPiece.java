package jvtest;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;

public abstract class ChessPiece { //Lớp đặc tính chung của các quân cờ

    int x, y;
    boolean is_Chess = false;
    Color color; //black or white
    String symbol;
    String name;
    List<point> PotentialMoves = new ArrayList<>();

    public abstract List<point> ValidMoves();

    public void showValidMove() {
        List<point> p = this.ValidMoves();
        for (point po : p) {
            //  System.out.println(this.name +":" + po.i + " " + po.j);
            GamePlay.squares[po.i][po.j].setBorderPainted(true);
            GamePlay.squares[po.i][po.j].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
        }
        //Xem potential moves
        //   List<point> potential = this.PotentialMoves;
        //  if (potential!=null)
        //   for (point po: potential){
        //       GamePlay.squares[po.i][po.j].setBorderPainted(true);
        //     GamePlay.squares[po.i][po.j].setBorder(BorderFactory.createLineBorder(Color.red, 5));
        //   }

    }

    public void deleteValidMove() {
        List<point> p = this.ValidMoves();
        for (point po : p) {
            GamePlay.squares[po.i][po.j].setBorderPainted(false);
        }
        List<point> p2 = this.PotentialMoves;
        if (p2 != null) {
            for (point po: p2){
                GamePlay.squares[po.i][po.j].setBorderPainted(false);
            }
            this.PotentialMoves.removeAll(p2);
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

    public void setMove(point pre, int i, int j) { //Set là ô đã có quân cờ đặt
        if (pre != null && pre.i >= 0 && pre.j >= 0) { 
            Board.setMoveBorder(pre.i, pre.j, i, j);
            Board.chessBoard[pre.i][pre.j] = new NullChess(pre.i, pre.j);
        }
        Board.chessBoard[i][j] = this;
        //SetMoveCount cho Xe
        if (this!=null && this.name!=null && this.name.equals("Rock")){
           ((Rock) this).moveCount++;
        }
        // for (point p: Board.chessBoard[i][j].ValidMoves()){
        //     System.out.println("Potential move of "+this.name+": "+p.i+" "+p.j);
        // }
        this.x = i;
        this.y = j;
        
    }

    public Color getColor() {
        return color;
    }

    public void makeMove(int toX, int toY) {
    
        Board.chessBoard[toX][toY] = Board.chessBoard[this.x][this.y];
        Board.chessBoard[this.x][this.y] = new NullChess(this.x, this.y);
    }

}