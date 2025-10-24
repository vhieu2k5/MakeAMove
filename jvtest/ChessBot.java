package jvtest;

import java.awt.Color;

public class ChessBot {

    private static final int MAX_DEPTH = 3; // Adjust for speed vs intelligence

    public Move findBestMove(Board board) {
        Move bestMove = null;
        int bestValue = Integer.MIN_VALUE;
for (int i=0;i<8;i++) {
    for (int j=0;j<8;j++) { 
        if (Board.chessBoard[i][j].getColor()!=null) {
            if (Board.chessBoard[i][j].getColor().toString().equalsIgnoreCase(Color.WHITE.toString())) {
                for (point move : Board.chessBoard[i][j].ValidMoves()) {
                    Board.chessBoard[i][j].setMove(new point(i,j), move.i, move.j);
                    int boardValue = minimax(board, MAX_DEPTH - 1, Integer.MIN_VALUE, Integer.MAX_VALUE, false);
                    Board.chessBoard[move.i][move.j].setMove(new point(move.i,move.j), i, j); // Undo move

                    if (boardValue > bestValue) {
                        bestValue = boardValue;
                        bestMove = new Move(i, j, move.i, move.j);
                    }
                }
            }
        }
    }
        }
        return bestMove;
    }

    private int minimax(Board board, int depth, int alpha, int beta, boolean isMaximizing) {
        if (depth == 0 || Board.isGameOver()) {
            return evaluateBoard();
        }

        if (isMaximizing) { // White bot's turn
            int maxEval = Integer.MIN_VALUE;
            for (int i=0;i<8;i++) {
                for (int j=0;j<8;j++) {
                    if (Board.chessBoard[i][j].getColor()!=null) {
                        if (Board.chessBoard[i][j].getColor().toString().equalsIgnoreCase(Color.WHITE.toString())) {
                            for (point move : Board.chessBoard[i][j].ValidMoves()) {
                                Board.chessBoard[i][j].setMove(new point(i,j), move.i, move.j);
                                int eval = minimax(board, depth - 1, alpha, beta, false);
                                Board.chessBoard[move.i][move.j].setMove(new point(move.i,move.j), i, j); // Undo move
                                maxEval = Math.max(maxEval, eval);
                                alpha = Math.max(alpha, eval);
                                if (beta <= alpha)
                                    break; // Beta cut-off
                            }
                        }
                    }
                }
            }
            return maxEval;

        } else { // Black player's turn
            int minEval = Integer.MAX_VALUE;
            for (int i=0;i<8;i++) {
                for (int j=0;j<8;j++) {
                    if (Board.chessBoard[i][j].getColor()!=null) {
                        if (Board.chessBoard[i][j].getColor().toString().equalsIgnoreCase(Color.BLACK.toString())) {
                            for (point move : Board.chessBoard[i][j].ValidMoves()) {
                                Board.chessBoard[i][j].setMove(new point(i,j), move.i, move.j);
                                int eval = minimax(board, depth - 1, alpha, beta, true);
                                Board.chessBoard[move.i][move.j].setMove(new point(move.i,move.j), i, j); // Undo move
                                minEval = Math.min(minEval, eval);
                                beta = Math.min(beta, eval);
                                if (beta <= alpha)
                                    break; // Alpha cut-off
                            }
                        }
                    }
                }
            }
            return minEval;
        }
    }

    private int evaluateBoard() {
        int score = 0;
        for(int i=0;i<8;i++) {
            for (int j=0;j<8;j++) {
                if (Board.chessBoard[i][j].getColor()!=null) {
                    if (Board.chessBoard[i][j].getColor().toString().equalsIgnoreCase(Color.WHITE.toString())) {
                        score += getPieceValue(Board.chessBoard[i][j]);
                    } else if (Board.chessBoard[i][j].getColor().toString().equalsIgnoreCase(Color.BLACK.toString())) {
                        score -= getPieceValue(Board.chessBoard[i][j]);
                    }
                }
            }
        }
        return score;
    }

    private int getPieceValue(ChessPiece piece) {
        return switch (piece.getName()) {
            case "Pawn" -> 10;
            case "Knight", "Bishop" -> 30;
            case "Rook" -> 50;
            case "Queen" -> 90;
            case "King" -> 900;
            default -> 0;
        };
    }
}
