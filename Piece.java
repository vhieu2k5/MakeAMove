
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import  java.util.List;

public class Piece {

    public List<point> chessBoard = new ArrayList<>(64);

    public Piece() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                chessBoard.add(new point(i, j));
                chessBoard.get(i*8+j).occupied=false;
            }
        }
    }
    public void InitChessPlay(){
        GamePlay ui = new GamePlay();
        for (int j = 0; j < 8; j++) {
            // White pawns on row 1
            Pawn whitePawn = new Pawn(1, j, Color.WHITE);
            whitePawn.setMove(1, j);
            ui.squares[1][j].setText(whitePawn.symbol);
            ui.squares[1][j].setFont(new Font("Serif", Font.BOLD, 36));
            ui.squares[1][j].setForeground(whitePawn.color);

            // Black pawns on row 6
            Pawn blackPawn = new Pawn(6, j, Color.BLACK);
            blackPawn.setMove(6, j);
            ui.squares[1][j].setText(blackPawn.symbol);
            ui.squares[1][j].setFont(new Font("Serif", Font.BOLD, 36));
            ui.squares[1][j].setForeground(blackPawn.color);
        }
    }

    public class point {

        int i, j;
        boolean occupied=false;
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
        public void setMove (int i, int j){
            this.x = i;
            this.y = j;
            int index = 8*i +j;
            chessBoard.get(index).occupied = true;
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
                int index= (this.currentMove().i-1) *8 +this.currentMove().j+1;
                if (chessBoard.get(index).occupied) res.add(new point(this.currentMove().i-1,this.currentMove().j+1));
                index= (this.currentMove().i+1) *8 +this.currentMove().j+1;
                if (chessBoard.get(index).occupied) res.add(new point(this.currentMove().i+1,this.currentMove().j+1));
            } 
            else 
            {
                res.add(new point(this.currentMove().i,this.currentMove().j-1));
                int index= (this.currentMove().i-1) *8 +this.currentMove().j-1;
                if (chessBoard.get(index).occupied) res.add(new point(this.currentMove().i-1,this.currentMove().j-1));
                index= (this.currentMove().i+1) *8 +this.currentMove().j-1;
                if (chessBoard.get(index).occupied) res.add(new point(this.currentMove().i+1,this.currentMove().j-1));
            }            
            return res;
        }
    }

}
