package jvtest;

import java.awt.Color;

public class ChessBot {

    private static final int MAX_DEPTH = 3; // Adjust for speed vs intelligence

    public Move findBestMove(ChessPiece[][] chessboard) {
        Move bestMove = null;
        int bestValue = Integer.MIN_VALUE;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (chessboard[i][j].getColor() != null) {
                    ChessPiece captured = chessboard[i][j];
                   if (Color.WHITE.equals(chessboard[i][j].getColor())) {
                        for (point move : chessboard[i][j].ValidMoves()) {
                            ChessPiece temp = chessboard[move.i][move.j];

                            chessboard[move.i][move.j] = captured;
                            chessboard[i][j] = temp; // Make move

                            int boardValue = minimax(chessboard, MAX_DEPTH - 1, Integer.MIN_VALUE, Integer.MAX_VALUE, false);

                            chessboard[move.i][move.j] = temp;
                            chessboard[i][j] = captured; // Undo move

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
private int minimax(ChessPiece[][] board, int depth, int alpha, int beta, boolean isMaximizing) {
    if (depth == 0 || Board.isGameOver()) {
        return evaluateBoard(board);
    }

    if (isMaximizing) { // White (bot)
        int maxEval = Integer.MIN_VALUE;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ChessPiece piece = board[i][j];
                if (piece == null || !Color.WHITE.equals(piece.getColor())) continue;

                for (point move : piece.ValidMoves()) {
                    ChessPiece captured = board[move.i][move.j];

                    // make move
                    board[move.i][move.j] = piece;
                    board[i][j] = null;

                    int eval = minimax(board, depth - 1, alpha, beta, false);

                    // undo
                    board[i][j] = piece;
                    board[move.i][move.j] = captured;

                    maxEval = Math.max(maxEval, eval);
                    alpha = Math.max(alpha, eval);
                    if (beta <= alpha) break;
                }
            }
        }
        return maxEval;

    } else { // Black (human)
        int minEval = Integer.MAX_VALUE;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ChessPiece piece = board[i][j];
                if (piece == null || !Color.BLACK.equals(piece.getColor())) continue;

                for (point move : piece.ValidMoves()) {
                    ChessPiece captured = board[move.i][move.j];

                    // make move
                    board[move.i][move.j] = piece;
                    board[i][j] = new NullChess(i, j);

                    int eval = minimax(board, depth - 1, alpha, beta, true);

                    // undo
                    board[i][j] = piece;
                    board[move.i][move.j] = captured;

                    minEval = Math.min(minEval, eval);
                    beta = Math.min(beta, eval);
                    if (beta <= alpha) break;
                }
            }
        }
        return minEval;
    }
}


    private int evaluateBoard(ChessPiece[][] chessboard) {
        int score = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (chessboard[i][j]!=null && chessboard[i][j].getColor() != null) {
                  if (Color.WHITE.equals(chessboard[i][j].getColor())) {
                        score += getPieceValue(chessboard[i][j]);
                    } else if (chessboard[i][j].getColor().toString().equalsIgnoreCase(Color.BLACK.toString())) {
                        score -= getPieceValue(chessboard[i][j]);
                    }
                }
            }
        }
        return score;
    }

    private int getPieceValue(ChessPiece piece) {
        return switch (piece.getName()) {
            case "Pawn" ->
                10;
            case "Knight", "Bishop" ->
                30;
            case "Rook" ->
                50;
            case "Queen" ->
                90;
            case "King" ->
                900;
            default ->
                0;
        };
    }
}
