/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jvtest.gameplay02;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import jvtest.Move;
import jvtest.point;

/**
 *
 * @author ADMIN
 */
public class Board2 { //Lớp bàn cờ

    public static ChessPiece2[][] chessBoard = new ChessPiece2[8][8];
    public static King2 whiteKing4 = new King2(7, 4, Color.WHITE);
    public static King2 blackKing4 = new King2(0, 4, Color.BLACK);
    public static ArrayList<ChessPiece2> chessPieceBlack = new ArrayList<>();
    public static ArrayList<ChessPiece2> chessPieceWhite = new ArrayList<>();

    public Board2() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                chessBoard[i][j] = new NullChess2(i, j);
            }
        }
    }

    public void InitChessPlay() {
        chessPieceBlack = new ArrayList<>();
        chessPieceWhite = new ArrayList<>();
        //King
        blackKing4 = new King2(0, 3, Color.BLACK);
        blackKing4.setMove(null, 0, 3);
        GamePlay2.squares[0][3].setText(blackKing4.symbol);
        GamePlay2.squares[0][3].setFont(new Font("Serif", Font.BOLD, 36));
        GamePlay2.squares[0][3].setForeground(blackKing4.color);

        whiteKing4 = new King2(7, 3, Color.WHITE);
        whiteKing4.setMove(null, 7, 3);
        GamePlay2.squares[7][3].setText(whiteKing4.symbol);
        GamePlay2.squares[7][3].setFont(new Font("Serif", Font.BOLD, 36));
        GamePlay2.squares[7][3].setForeground(whiteKing4.color);

        //Queen
        Queen2 blackQueen3 = new Queen2(0, 4, Color.BLACK);
        blackQueen3.setMove(null, 0, 4);
        GamePlay2.squares[0][4].setText("?");
        GamePlay2.squares[0][4].setFont(new Font("Serif", Font.BOLD, 36));
        GamePlay2.squares[0][4].setForeground(blackQueen3.color);
        chessPieceBlack.add(new Queen2(Color.BLACK));

        Queen2 whiteQueen3 = new Queen2(7, 4, Color.WHITE);
        whiteQueen3.setMove(null, 7, 4);
        GamePlay2.squares[7][4].setText("?");
        GamePlay2.squares[7][4].setFont(new Font("Serif", Font.BOLD, 36));
        GamePlay2.squares[7][4].setForeground(whiteQueen3.color);
        chessPieceWhite.add(new Queen2(Color.white));

        //Bishop
        Bishop2 blackBishop = new Bishop2(0, 5, Color.BLACK);
        blackBishop.setMove(null, 0, 5);
        GamePlay2.squares[0][5].setText("?");
        GamePlay2.squares[0][5].setFont(new Font("Serif", Font.BOLD, 36));
        GamePlay2.squares[0][5].setForeground(blackBishop.color);
        chessPieceBlack.add(new Bishop2(Color.BLACK));

        Bishop2 blackBishop2 = new Bishop2(0, 2, Color.BLACK);
        blackBishop2.setMove(null, 0, 2);
        GamePlay2.squares[0][2].setText("?");
        GamePlay2.squares[0][2].setFont(new Font("Serif", Font.BOLD, 36));
        GamePlay2.squares[0][2].setForeground(blackBishop2.color);
        chessPieceBlack.add(new Bishop2(Color.BLACK));

        Bishop2 whiteBishop = new Bishop2(7, 2, Color.WHITE);
        whiteBishop.setMove(null, 7, 2);
        GamePlay2.squares[7][2].setText("?");
        GamePlay2.squares[7][2].setFont(new Font("Serif", Font.BOLD, 36));
        GamePlay2.squares[7][2].setForeground(whiteBishop.color);
        chessPieceWhite.add(new Bishop2(Color.white));

        Bishop2 whiteBishop2 = new Bishop2(7, 5, Color.WHITE);
        whiteBishop2.setMove(null, 7, 5);
        GamePlay2.squares[7][5].setText("?");
        GamePlay2.squares[7][5].setFont(new Font("Serif", Font.BOLD, 36));
        GamePlay2.squares[7][5].setForeground(whiteBishop2.color);
        chessPieceWhite.add(new Bishop2(Color.white));

        //Knight
        Knight2 blackKnight = new Knight2(0, 1, Color.BLACK);
        blackKnight.setMove(null, 0, 1);
        GamePlay2.squares[0][1].setText("?");
        GamePlay2.squares[0][1].setFont(new Font("Serif", Font.BOLD, 36));
        GamePlay2.squares[0][1].setForeground(blackKnight.color);
        chessPieceBlack.add(new Knight2(Color.black));

        Knight2 blackKnight2 = new Knight2(0, 6, Color.BLACK);
        blackKnight2.setMove(null, 0, 6);
        GamePlay2.squares[0][6].setText("?");
        GamePlay2.squares[0][6].setFont(new Font("Serif", Font.BOLD, 36));
        GamePlay2.squares[0][6].setForeground(blackKnight2.color);
        chessPieceBlack.add(new Knight2(Color.black));

        Knight2 whiteKnight = new Knight2(7, 1, Color.WHITE);
        whiteKnight.setMove(null, 7, 1);
        GamePlay2.squares[7][1].setText("?");
        GamePlay2.squares[7][1].setFont(new Font("Serif", Font.BOLD, 36));
        GamePlay2.squares[7][1].setForeground(whiteKnight.color);
        chessPieceWhite.add(new Knight2(Color.white));

        Knight2 whiteKnight2 = new Knight2(7, 6, Color.WHITE);
        whiteKnight2.setMove(null, 7, 6);
        GamePlay2.squares[7][6].setText("?");
        GamePlay2.squares[7][6].setFont(new Font("Serif", Font.BOLD, 36));
        GamePlay2.squares[7][6].setForeground(whiteKnight.color);
        chessPieceWhite.add(new Knight2(Color.white));

        //Rock
        Rock2 blackRock = new Rock2(0, 0, Color.BLACK);
        blackRock.setMove(null, 0, 0);
        GamePlay2.squares[0][0].setText("?");
        GamePlay2.squares[0][0].setFont(new Font("Serif", Font.BOLD, 36));
        GamePlay2.squares[0][0].setForeground(blackRock.color);
        chessPieceBlack.add(new Rock2(Color.black));

        Rock2 blackRock2 = new Rock2(0, 7, Color.BLACK);
        blackRock2.setMove(null, 0, 7);
        GamePlay2.squares[0][7].setText("?");
        GamePlay2.squares[0][7].setFont(new Font("Serif", Font.BOLD, 36));
        GamePlay2.squares[0][7].setForeground(blackRock2.color);
        chessPieceBlack.add(new Rock2(Color.black));

        Rock2 whiteRock = new Rock2(7, 0, Color.WHITE);
        whiteRock.setMove(null, 7, 0);
        GamePlay2.squares[7][0].setText("?");
        GamePlay2.squares[7][0].setFont(new Font("Serif", Font.BOLD, 36));
        GamePlay2.squares[7][0].setForeground(whiteRock.color);
        chessPieceWhite.add(new Rock2(Color.white));

        Rock2 whiteRock2 = new Rock2(7, 7, Color.WHITE);
        whiteRock2.setMove(null, 7, 7);
        GamePlay2.squares[7][7].setText("?");
        GamePlay2.squares[7][7].setFont(new Font("Serif", Font.BOLD, 36));
        GamePlay2.squares[7][7].setForeground(whiteRock2.color);
        chessPieceWhite.add(new Rock2(Color.white));

        //Pawn
        for (int j = 0; j < 8; j++) {
            Pawn2 blackPawn = new Pawn2(1, j, Color.BLACK); //Tạo một quân tốt ở vị trí ô (1,j) màu trắng
            blackPawn.setMove(null, 1, j);

            //Cài đặt UI tốt trắng
            GamePlay2.squares[1][j].setText("?");
            GamePlay2.squares[1][j].setFont(new Font("Serif", Font.BOLD, 36));
            GamePlay2.squares[1][j].setForeground(blackPawn.color);
            chessPieceBlack.add(new Pawn2(Color.black));

            // White pawns on row 6
            Pawn2 whitePawn = new Pawn2(6, j, Color.WHITE);
            whitePawn.setMove(null, 6, j);
            GamePlay2.squares[6][j].setText("?");
            GamePlay2.squares[6][j].setFont(new Font("Serif", Font.BOLD, 36));
            GamePlay2.squares[6][j].setForeground(whitePawn.color);
            chessPieceWhite.add(new Pawn2(Color.white));
        }
    }

    public static List<point> getAllValidMoves(String color) {
        List<point> validMoves = new ArrayList<>();
        List<point> potentialMoves = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (Board2.chessBoard[i][j] != null && Board2.chessBoard[i][j].is_Chess) {
                    if (Board2.chessBoard[i][j].color.toString().equalsIgnoreCase(color)) {
                        validMoves.addAll(Board2.chessBoard[i][j].ValidMoves());
                        potentialMoves.addAll(Board2.chessBoard[i][j].PotentialMoves);
                    }
                }
            }
        }
        return potentialMoves;
    }

    public static void showPriorityMoves() {
        for (Move m : GamePlay2.priorityMoves) {
            GamePlay2.squares[m.fromX][m.fromY].setBorderPainted(true);
            GamePlay2.squares[m.fromX][m.fromY].setBorder(BorderFactory.createLineBorder(Color.red, 4));

            // GamePlay.squares[m.toX][m.toY].setBorderPainted(true);
            // GamePlay.squares[m.toX][m.fromY].setBorder(BorderFactory.createLineBorder(Color.red, 4));
        }
    }

    public static void setMoveBorder(int fromX, int fromY, int toX, int toY) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                GamePlay2.squares[i][j].setBorderPainted(false);
            }
        }
        GamePlay2.squares[toX][toY].setBorderPainted(true);
        GamePlay2.squares[toX][toY].setBorder(BorderFactory.createLineBorder(Color.yellow, 3));

        GamePlay2.squares[fromX][fromY].setBorderPainted(true);
        GamePlay2.squares[fromX][fromY].setBorder(BorderFactory.createLineBorder(Color.yellow, 3));
    }

}
