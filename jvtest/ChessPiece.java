
package jvtest;

import java.awt.Color;
import  java.util.List;
import javax.swing.BorderFactory;

public abstract class ChessPiece { //Lớp đặc tính chung của các quân cờ
    
        int x, y;
        boolean is_Chess = false;
        Color color; //black or white
        String symbol;
        String name;
<<<<<<< HEAD
=======
        boolean firstMove = true;
>>>>>>> f3ae1d26ca71d8f51bce23a135d334dfb9b6e40d
        public abstract List<point> ValidMoves();

        public void showValidMove() {
            List<point> p = this.ValidMoves();
        for (point po:p) {
          //  System.out.println(this.name +":" + po.i + " " + po.j);
<<<<<<< HEAD
            GamePlay.squares[po.i][po.j].setBorderPainted(true);
            GamePlay.squares[po.i][po.j].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
=======
            GamePlay2.squares[po.i][po.j].setBorderPainted(true);
            GamePlay2.squares[po.i][po.j].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
>>>>>>> f3ae1d26ca71d8f51bce23a135d334dfb9b6e40d
        }
        }
        public void deleteValidMove() {
            List<point> p = this.ValidMoves();
        for (point po:p) {
<<<<<<< HEAD
            GamePlay.squares[po.i][po.j].setBorderPainted(false);
=======
            GamePlay2.squares[po.i][po.j].setBorderPainted(false);
>>>>>>> f3ae1d26ca71d8f51bce23a135d334dfb9b6e40d
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
<<<<<<< HEAD
               Board.chessBoard[pre.i][pre.j] = new NullChess(pre.i,pre.j);
            }
            Board.chessBoard[i][j]=this;
=======
               Board2.chessBoard[pre.i][pre.j] = new NullChess(pre.i,pre.j);
            }
            Board2.chessBoard[i][j]=this;
>>>>>>> f3ae1d26ca71d8f51bce23a135d334dfb9b6e40d
            // for (point p: Board.chessBoard[i][j].ValidMoves()){
            //     System.out.println("Potential move of "+this.name+": "+p.i+" "+p.j);
            // }
            this.x = i;
            this.y=j;
        }

    public Color getColor() {
        return color;
    }
public void makeMove(int toX, int toY){
<<<<<<< HEAD
    Board.chessBoard[toX][toY]= Board.chessBoard[this.x][this.y];
    Board.chessBoard[this.x][this.y] = new NullChess(this.x,this.y);
=======
    Board2.chessBoard[toX][toY]= Board2.chessBoard[this.x][this.y];
    Board2.chessBoard[this.x][this.y] = new NullChess(this.x,this.y);
>>>>>>> f3ae1d26ca71d8f51bce23a135d334dfb9b6e40d
}

    }
    
    

