
package jvtest;


import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import  java.util.List;
import jvtest.GamePlay;

public class Piece {

    public ChessPiece[][] chessBoard = new ChessPiece[8][8];

    public void InitChessPlay(GamePlay ui){
        for (int j = 0; j < 8; j++) {
            // White pawns on row 1
            Pawn whitePawn = new Pawn(1, j, Color.WHITE);
            whitePawn.setMove(1, j,ui);
            // Black pawns on row 6
            Pawn blackPawn = new Pawn(6, j, Color.BLACK);
            blackPawn.setMove(6, j,ui);
          }
    }

    public class point {

        int i, j;
        public point(int i, int j){
            this.i = i;
            this.j=j;
        }
    }

    public abstract class ChessPiece {

        int x, y;
        Color color; //black or white
        String symbol;
        public abstract List<point> ValidMoves();
        public point currentMove(){
            return new point(x,y); 
        }
        public void setMove (int i, int j, GamePlay ui){
            ui.squares[i][j].setText(this.symbol);
            ui.squares[i][j].setFont(new Font("Serif", Font.BOLD, 36));
            ui.squares[i][j].setForeground(this.color);
            chessBoard[i][j]=this;
           if(this.x!=i || this.y !=j) 
           {
            chessBoard[this.x][this.y]=null;
            ui.squares[this.x][this.y].setText("");
           }
           
            this.x =i;
            this.y=j;

        }
    }

    public class Pawn extends ChessPiece {

        public Pawn(int i, int j, Color color) {
            this.x = i;
            this.y = j;
            this.color = color;
            this.symbol = "♙";
        }
        @Override
        public List<point> ValidMoves(){
            List<point> res = new ArrayList<>();
            if (this.color == Color.WHITE) 
            {
                res.add(new point(this.currentMove().i,this.currentMove().j+1));
                //int index= (this.currentMove().i-1) *8 +this.currentMove().j+1;
                if (chessBoard[this.currentMove().i-1][this.currentMove().j+1]!=null) res.add(new point(this.currentMove().i-1,this.currentMove().j+1));
               // index= (this.currentMove().i+1) *8 +this.currentMove().j+1;
                if (chessBoard[this.currentMove().i+1][this.currentMove().j+1]!=null) res.add(new point(this.currentMove().i+1,this.currentMove().j+1));
            } 
            else 
            {
                res.add(new point(this.currentMove().i,this.currentMove().j-1));
              //  int index= (this.currentMove().i-1) *8 +this.currentMove().j-1;
                if (chessBoard[this.currentMove().i-1][this.currentMove().j-1]!=null) res.add(new point(this.currentMove().i-1,this.currentMove().j-1));
             //   index= (this.currentMove().i+1) *8 +this.currentMove().j-1;
                if (chessBoard[this.currentMove().i+1][this.currentMove().j-1]!=null) res.add(new point(this.currentMove().i+1,this.currentMove().j-1));
            }            
            return res;
        }
    }

}