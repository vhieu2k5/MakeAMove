/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jvtest;

import java.awt.Color;
import java.awt.Font;
import javax.swing.UIManager;

/**
 *
 * @author ADMIN
 */
public class Board { //Lớp bàn cờ
    public static ChessPiece[][] chessBoard = new ChessPiece[8][8];

    public Board() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                point p = new point(i, j);
                chessBoard[i][j] = new NullChess(i,j);
            }
        }
    }

public void InitChessPlay(GamePlay ui) {

        //King
        King whiteKing4 = new King(0,4,Color.WHITE);
        whiteKing4.setMove(null, 0, 4);
        ui.squares[0][4].setText(whiteKing4.symbol);
        ui.squares[0][4].setFont(new Font("Serif", Font.BOLD, 46));
        ui.squares[0][4].setForeground(whiteKing4.color);
        
        
        King blackKing4 = new King(7,4,Color.BLACK);
        blackKing4.setMove(null, 7, 4);
        ui.squares[7][4].setText(blackKing4.symbol);
        ui.squares[7][4].setFont(new Font("Serif", Font.BOLD, 46));
        ui.squares[7][4].setForeground(blackKing4.color);
        

        
        //Queen
        Queen whiteQueen3 = new Queen(0,3,Color.WHITE);
        whiteQueen3.setMove(null, 0, 3);
        ui.squares[0][3].setText(whiteQueen3.symbol);
        ui.squares[0][3].setFont(new Font("Serif", Font.BOLD, 36));
        ui.squares[0][3].setForeground(whiteQueen3.color);
        
        Queen blackQueen3 = new Queen(7,3,Color.BLACK);
        blackQueen3.setMove(null, 7, 3);
        ui.squares[7][3].setText(blackQueen3.symbol);
        ui.squares[7][3].setFont(new Font("Serif", Font.BOLD, 36));
        ui.squares[7][3].setForeground(blackQueen3.color);

        
        //Bishop
        Bishop whiteBishop = new Bishop(0,5,Color.WHITE);
        whiteBishop.setMove(null, 0, 5);
        ui.squares[0][5].setText(whiteBishop.symbol);
        ui.squares[0][5].setFont(new Font("Serif", Font.BOLD, 36));
        ui.squares[0][5].setForeground(whiteBishop.color);
        
        Bishop whiteBishop2 = new Bishop(0,2,Color.WHITE);
        whiteBishop2.setMove(null, 0, 2);
        ui.squares[0][2].setText(whiteBishop2.symbol);
        ui.squares[0][2].setFont(new Font("Serif", Font.BOLD, 36));
        ui.squares[0][2].setForeground(whiteBishop2.color);
        
        Bishop blackBishop = new Bishop(7,2,Color.BLACK);
        blackBishop.setMove(null, 7, 2);
        ui.squares[7][2].setText(blackBishop.symbol);
        ui.squares[7][2].setFont(new Font("Serif", Font.BOLD, 36));
        ui.squares[7][2].setForeground(blackBishop.color);
        
        Bishop blackBishop2 = new Bishop(7,5,Color.BLACK);
        blackBishop2.setMove(null, 7, 5);
        ui.squares[7][5].setText(blackBishop2.symbol);
        ui.squares[7][5].setFont(new Font("Serif", Font.BOLD, 36));
        ui.squares[7][5].setForeground(blackBishop2.color);
        
        //Knight
        Knight whiteKnight = new Knight(0,1,Color.WHITE);
        whiteKnight.setMove(null, 0, 1);
        ui.squares[0][1].setText(whiteKnight.symbol);
        ui.squares[0][1].setFont(new Font("Serif", Font.BOLD, 36));
        ui.squares[0][1].setForeground(whiteKnight.color);
        
        Knight whiteKnight2 = new Knight(0,6,Color.WHITE);
        whiteKnight2.setMove(null, 0, 6);
        ui.squares[0][6].setText(whiteKnight2.symbol);
        ui.squares[0][6].setFont(new Font("Serif", Font.BOLD, 36));
        ui.squares[0][6].setForeground(whiteKnight2.color);
        
        Knight blackKnight = new Knight(7,1,Color.BLACK);
        blackKnight.setMove(null, 7, 1);
        ui.squares[7][1].setText(blackKnight.symbol);
        ui.squares[7][1].setFont(new Font("Serif", Font.BOLD, 36));
        ui.squares[7][1].setForeground(blackKnight.color);
        
        Knight blackKnight2 = new Knight(7,6,Color.BLACK);
        blackKnight2.setMove(null, 7, 6);
        ui.squares[7][6].setText(blackKnight.symbol);
        ui.squares[7][6].setFont(new Font("Serif", Font.BOLD, 36));
        ui.squares[7][6].setForeground(blackKnight.color);
        
        
        
        //Rock
        Rock whiteRock = new Rock(0,0,Color.WHITE);
        whiteRock.setMove(null, 0, 0);
        ui.squares[0][0].setText(whiteRock.symbol);
        ui.squares[0][0].setFont(new Font("Serif", Font.BOLD, 36));
        ui.squares[0][0].setForeground(whiteRock.color);
        
        Rock whiteRock2 = new Rock(0,7,Color.WHITE);
        whiteRock2.setMove(null, 0, 7);
        ui.squares[0][7].setText(whiteRock2.symbol);
        ui.squares[0][7].setFont(new Font("Serif", Font.BOLD, 36));
        ui.squares[0][7].setForeground(whiteRock2.color);
        
        Rock blackRock = new Rock(7,0,Color.BLACK);
        blackRock.setMove(null, 7, 0);
        ui.squares[7][0].setText(blackRock.symbol);
        ui.squares[7][0].setFont(new Font("Serif", Font.BOLD, 36));
        ui.squares[7][0].setForeground(blackRock.color);
        
        Rock blackRock2 = new Rock(7,7,Color.BLACK);
        blackRock2.setMove(null, 7, 7);
        ui.squares[7][7].setText(blackRock2.symbol);
        ui.squares[7][7].setFont(new Font("Serif", Font.BOLD, 36));
        ui.squares[7][7].setForeground(blackRock2.color);
        
        
        //Pawn
        for (int j = 0; j < 8; j++) {
            Pawn whitePawn = new Pawn(1,j,Color.WHITE); //Tạo một quân tốt ở vị trí ô (1,j) màu trắng
            whitePawn.setMove(null, 1, j);
            
            
            //Cài đặt UI tốt trắng
            ui.squares[1][j].setText(whitePawn.symbol);
            ui.squares[1][j].setFont(new Font("Serif", Font.BOLD, 36));
            ui.squares[1][j].setForeground(whitePawn.color);

            // Black pawns on row 6
            Pawn blackPawn = new Pawn(6, j,Color.BLACK);
            blackPawn.setMove(null, 6, j);
            ui.squares[6][j].setText(blackPawn.symbol);
            ui.squares[6][j].setFont(new Font("Serif", Font.BOLD, 36));
            ui.squares[6][j].setForeground(blackPawn.color);
        }
    }
}
