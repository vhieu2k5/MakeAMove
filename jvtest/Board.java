/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jvtest;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;

/**
 *
 * @author ADMIN
 */
public class Board { //Lớp bàn cờ

    public static ChessPiece[][] chessBoard = new ChessPiece[8][8];
    public static King blackKing4 = new King(7, 3, Color.BLACK);
    public static King whiteKing4 = new King(0, 3, Color.WHITE);

    public Board() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                chessBoard[i][j] = new NullChess(i, j);
            }
        }
    }

    public static Move translateChessCode(String code) {
        try {
            int fromY = code.charAt(0) - 'a';
            int fromX = 8 - Character.getNumericValue(code.charAt(1));
            int ToY = code.charAt(2) - 'a';
            int ToX = 8 - Character.getNumericValue(code.charAt(3));
            System.out.println("from: " + code + " to: " + fromX + fromY + ToX + ToY);
            return new Move(fromX, fromY, ToX, ToY);
        } catch (Exception e) {
            // e.printStackTrace();
            if (code == null) {
                System.err.println("Code is null");
            }
            return null;
        }

    }

    public static String generateFEN(Color turnColor) {
    StringBuilder fen = new StringBuilder();

    // ----------------------------------------------------
    // PHẦN 1: XÁC ĐỊNH QUYỀN NHẬP THÀNH (ĐÃ SỬA LỖI LOGIC)
    // Quyền nhập thành bị mất khi Vua hoặc Xe xuất phát đã di chuyển.
    
    // Giả định: whiteKing4 và blackKing4 có thuộc tính moveCount
    boolean whiteKingMoved = (Board.whiteKing4.moveCount != 0);
    boolean blackKingMoved = (Board.blackKing4.moveCount != 0);

    boolean whiteRookQSideMoved = true; // [7][0] - Cánh Hậu (Q)
    boolean whiteRookKSideMoved = true; // [7][7] - Cánh Vua (K)
    boolean blackRookQSideMoved = true; // [0][0] - Cánh Hậu (q)
    boolean blackRookKSideMoved = true; // [0][7] - Cánh Vua (k)

    // Kiểm tra Xe Trắng cánh Hậu (A1 - [7][0])
    ChessPiece whiteQSideRook = Board.chessBoard[7][7];
    // Chú ý: Tên quân cờ chuẩn là "Rook", không phải "Rock"
    if (whiteQSideRook != null && whiteQSideRook.getName() != null && whiteQSideRook.getName().equals("Rock")) { 
        // Giả định: Rook có thuộc tính 'hasMoved' hoặc 'moveCount'
        // KHÔNG sử dụng CanCastle() vì nó là một kiểm tra phức tạp
        whiteRookQSideMoved = ((Rock) whiteQSideRook).moveCount != 1; 
    }

    // Kiểm tra Xe Trắng cánh Vua (H1 - [7][7])
    ChessPiece whiteKSideRook = Board.chessBoard[7][0];
    if (whiteKSideRook != null && whiteKSideRook.getName() != null && whiteKSideRook.getName().equals("Rock")) {
        whiteRookKSideMoved = ((Rock) whiteKSideRook).moveCount != 1;
    }

    // Kiểm tra Xe Đen cánh Hậu (A8 - [0][0])
    ChessPiece blackQSideRook = Board.chessBoard[0][7];
    if (blackQSideRook != null && blackQSideRook.getName() != null && blackQSideRook.getName().equals("Rock")) {
        blackRookQSideMoved = ((Rock) blackQSideRook).moveCount != 1;
    }
    
    // Kiểm tra Xe Đen cánh Vua (H8 - [0][7])
    ChessPiece blackKSideRook = Board.chessBoard[0][0];
    // Đã sửa lỗi bất nhất, dùng logic tương tự
    if (blackKSideRook != null && blackKSideRook.getName() != null && blackKSideRook.getName().equals("Rock")) {
        blackRookKSideMoved = ((Rock) blackKSideRook).moveCount != 1;
    }
    // ----------------------------------------------------


    // PHẦN 2: Bàn cờ
    for (int row = 0; row < 8; row++) {
        int emptyCount = 0;
        for (int col = 0; col < 8; col++) {
            ChessPiece piece = Board.chessBoard[row][col];

            // LƯU Ý: Đây là logic kiểm tra ô trống của bạn.
            // Nếu piece là null, nó sẽ gây ra NullPointerException ở piece.getColor(). 
            // Cần kiểm tra if (piece == null) trước tiên nếu ô trống là null.
            if (piece.getColor() == null) {
                emptyCount++;
            } else {
                if (emptyCount > 0) {
                    fen.append(emptyCount);
                    emptyCount = 0;
                }
                fen.append(convertPieceToFEN(piece));
            }
        }

        if (emptyCount > 0) {
            fen.append(emptyCount);
        }
        if (row < 7) {
            fen.append("/");
        }
    }

    // PHẦN 3: Lượt đi
    String turn = (turnColor == Color.white) ? "w" : "b";
    fen.append(" ").append(turn);

    // PHẦN 4: Quyền nhập thành (ĐÃ SỬA CÚ PHÁP CHUẨN FEN)
    StringBuilder castlingRights = new StringBuilder();

    // Vua Trắng chưa di chuyển VÀ Xe cánh Vua chưa di chuyển
    if (!whiteKingMoved && !whiteRookKSideMoved) {
        castlingRights.append("K");
    }
    // Vua Trắng chưa di chuyển VÀ Xe cánh Hậu chưa di chuyển
    if (!whiteKingMoved && !whiteRookQSideMoved) {
        castlingRights.append("Q");
    }
    // Vua Đen chưa di chuyển VÀ Xe cánh Vua chưa di chuyển
    if (!blackKingMoved && !blackRookKSideMoved) {
        castlingRights.append("k");
    }
    // Vua Đen chưa di chuyển VÀ Xe cánh Hậu chưa di chuyển
    if (!blackKingMoved && !blackRookQSideMoved) {
        castlingRights.append("q");
    }

    if (castlingRights.length() == 0) {
        fen.append(" -");
    } else {
        fen.append(" ").append(castlingRights.toString());
    }

    // PHẦN 5, 6, 7: En Passant, Halfmove, Fullmove
    // LƯU Ý: "- 0 1" bị hardcode. Bạn cần sử dụng các biến thực:
    // Ví dụ: fen.append(" ").append(enPassantSquare).append(" ").append(halfMoveClock).append(" ").append(fullMoveNumber);
    fen.append(" - 0 1");

    System.out.println("FEN: " + fen.toString());
    return fen.toString();
}

    private static char convertPieceToFEN(ChessPiece piece) {
        String name = piece.getName();
        boolean isWhite = piece.getColor().equals(Color.WHITE);

        char c;

        switch (name) {
            case "King":
                c = 'k';
                break;
            case "Queen":
                c = 'q';
                break;
            case "Rock":
                c = 'r';
                break;   // rook
            case "Bishop":
                c = 'b';
                break;
            case "Knight":
                c = 'n';
                break;
            case "Pawn":
                c = 'p';
                break;
            default:
                c = '?';
                break;
        }

        return isWhite ? Character.toUpperCase(c) : c;
    }

    public void InitChessPlay() {
        //King
        // blackKing4 = new King(0,3,Color.BLACK);
        blackKing4.setMove(null, 0, 3);
        GamePlay.squares[0][3].setText(blackKing4.symbol);
        GamePlay.squares[0][3].setFont(new Font("Serif", Font.BOLD, 36));
        GamePlay.squares[0][3].setForeground(blackKing4.color);

        // whiteKing4 = new King(7,3,Color.WHITE);
        whiteKing4.setMove(null, 7, 3);
        GamePlay.squares[7][3].setText(whiteKing4.symbol);
        GamePlay.squares[7][3].setFont(new Font("Serif", Font.BOLD, 36));
        GamePlay.squares[7][3].setForeground(whiteKing4.color);

        //Queen
        Queen blackQueen3 = new Queen(0, 4, Color.black);
        blackQueen3.setMove(null, 0, 4);
        GamePlay.squares[0][4].setText(blackQueen3.symbol);
        GamePlay.squares[0][4].setFont(new Font("Serif", Font.BOLD, 36));
        GamePlay.squares[0][4].setForeground(blackQueen3.color);

        Queen whiteQueen3 = new Queen(7, 4, Color.white);
        whiteQueen3.setMove(null, 7, 4);
        GamePlay.squares[7][4].setText(whiteQueen3.symbol);
        GamePlay.squares[7][4].setFont(new Font("Serif", Font.BOLD, 36));
        GamePlay.squares[7][4].setForeground(whiteQueen3.color);

        //Bishop
        Bishop whiteBishop = new Bishop(0, 5, Color.black);
        whiteBishop.setMove(null, 0, 5);
        GamePlay.squares[0][5].setText(whiteBishop.symbol);
        GamePlay.squares[0][5].setFont(new Font("Serif", Font.BOLD, 36));
        GamePlay.squares[0][5].setForeground(whiteBishop.color);

        Bishop whiteBishop2 = new Bishop(0, 2, Color.black);
        whiteBishop2.setMove(null, 0, 2);
        GamePlay.squares[0][2].setText(whiteBishop2.symbol);
        GamePlay.squares[0][2].setFont(new Font("Serif", Font.BOLD, 36));
        GamePlay.squares[0][2].setForeground(whiteBishop2.color);

        Bishop blackBishop = new Bishop(7, 2, Color.white);
        blackBishop.setMove(null, 7, 2);
        GamePlay.squares[7][2].setText(blackBishop.symbol);
        GamePlay.squares[7][2].setFont(new Font("Serif", Font.BOLD, 36));
        GamePlay.squares[7][2].setForeground(blackBishop.color);

        Bishop blackBishop2 = new Bishop(7, 5, Color.white);
        blackBishop2.setMove(null, 7, 5);
        GamePlay.squares[7][5].setText(blackBishop2.symbol);
        GamePlay.squares[7][5].setFont(new Font("Serif", Font.BOLD, 36));
        GamePlay.squares[7][5].setForeground(blackBishop2.color);

        //Knight
        Knight whiteKnight = new Knight(0, 1, Color.black);
        whiteKnight.setMove(null, 0, 1);
        GamePlay.squares[0][1].setText(whiteKnight.symbol);
        GamePlay.squares[0][1].setFont(new Font("Serif", Font.BOLD, 36));
        GamePlay.squares[0][1].setForeground(whiteKnight.color);

        Knight whiteKnight2 = new Knight(0, 6, Color.BLACK);
        whiteKnight2.setMove(null, 0, 6);
        GamePlay.squares[0][6].setText(whiteKnight2.symbol);
        GamePlay.squares[0][6].setFont(new Font("Serif", Font.BOLD, 36));
        GamePlay.squares[0][6].setForeground(whiteKnight2.color);

        Knight blackKnight = new Knight(7, 1, Color.white);
        blackKnight.setMove(null, 7, 1);
        GamePlay.squares[7][1].setText(blackKnight.symbol);
        GamePlay.squares[7][1].setFont(new Font("Serif", Font.BOLD, 36));
        GamePlay.squares[7][1].setForeground(blackKnight.color);

        Knight blackKnight2 = new Knight(7, 6, Color.white);
        blackKnight2.setMove(null, 7, 6);
        GamePlay.squares[7][6].setText(blackKnight.symbol);
        GamePlay.squares[7][6].setFont(new Font("Serif", Font.BOLD, 36));
        GamePlay.squares[7][6].setForeground(blackKnight.color);

        //Rock
        Rock whiteRock = new Rock(0, 0, Color.black);
        whiteRock.setMove(null, 0, 0);
        GamePlay.squares[0][0].setText(whiteRock.symbol);
        GamePlay.squares[0][0].setFont(new Font("Serif", Font.BOLD, 36));
        GamePlay.squares[0][0].setForeground(whiteRock.color);

        Rock whiteRock2 = new Rock(0, 7, Color.black);
        whiteRock2.setMove(null, 0, 7);
        GamePlay.squares[0][7].setText(whiteRock2.symbol);
        GamePlay.squares[0][7].setFont(new Font("Serif", Font.BOLD, 36));
        GamePlay.squares[0][7].setForeground(whiteRock2.color);

        Rock blackRock = new Rock(7, 0, Color.white);
        blackRock.setMove(null, 7, 0);
        GamePlay.squares[7][0].setText(blackRock.symbol);
        GamePlay.squares[7][0].setFont(new Font("Serif", Font.BOLD, 36));
        GamePlay.squares[7][0].setForeground(blackRock.color);

        Rock blackRock2 = new Rock(7, 7, Color.white);
        blackRock2.setMove(null, 7, 7);
        GamePlay.squares[7][7].setText(blackRock2.symbol);
        GamePlay.squares[7][7].setFont(new Font("Serif", Font.BOLD, 36));
        GamePlay.squares[7][7].setForeground(blackRock2.color);

        //Pawn
        for (int j = 0; j < 8; j++) {
            Pawn whitePawn = new Pawn(1, j, Color.black); //Tạo một quân tốt ở vị trí ô (1,j) màu trắng
            whitePawn.setMove(null, 1, j);

            //Cài đặt UI tốt trắng
            GamePlay.squares[1][j].setText(whitePawn.symbol);
            GamePlay.squares[1][j].setFont(new Font("Serif", Font.BOLD, 36));
            GamePlay.squares[1][j].setForeground(whitePawn.color);

            // Black pawns on row 6
            Pawn blackPawn = new Pawn(6, j, Color.white);
            blackPawn.setMove(null, 6, j);
            GamePlay.squares[6][j].setText(blackPawn.symbol);
            GamePlay.squares[6][j].setFont(new Font("Serif", Font.BOLD, 36));
            GamePlay.squares[6][j].setForeground(blackPawn.color);
        }
    }

    public static List<point> getAllValidMoves(String color) {
        List<point> validMoves = new ArrayList<>();
        List<point> potentialMoves = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (chessBoard[i][j] != null && chessBoard[i][j].is_Chess) {
                    if (chessBoard[i][j].color.toString().equalsIgnoreCase(color)) {
                        validMoves.addAll(chessBoard[i][j].ValidMoves());
                        potentialMoves.addAll(chessBoard[i][j].PotentialMoves);
                    }
                }
            }
        }
        return potentialMoves;
    }

    public static void setMoveBorder(int fromX, int fromY, int toX, int toY) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                GamePlay.squares[i][j].setBorderPainted(false);
            }
        }
        GamePlay.squares[toX][toY].setBorderPainted(true);
        GamePlay.squares[toX][toY].setBorder(BorderFactory.createLineBorder(Color.yellow, 3));

        GamePlay.squares[fromX][fromY].setBorderPainted(true);
        GamePlay.squares[fromX][fromY].setBorder(BorderFactory.createLineBorder(Color.yellow, 3));
    }
    public static void showPriorityMoves(){
        for (Move m: GamePlay.priorityMoves){
        GamePlay.squares[m.fromX][m.fromY].setBorderPainted(true);
        GamePlay.squares[m.fromX][m.fromY].setBorder(BorderFactory.createLineBorder(Color.red, 4));

       // GamePlay.squares[m.toX][m.toY].setBorderPainted(true);
       // GamePlay.squares[m.toX][m.fromY].setBorder(BorderFactory.createLineBorder(Color.red, 4));
        }
    }

}
