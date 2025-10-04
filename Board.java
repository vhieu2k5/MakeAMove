/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jvtest;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import static jvtest.GamePlay.squares;
import jvtest.point;

/**
 *
 * @author ADMIN
 */
public class Board { //Lớp bàn cờ
    public static List<point> chessBoard = new ArrayList<>(64);

    public Board() {
        chessBoard.clear();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                point p = new point(i, j);
                chessBoard.add(p);
            }
        }
    }
    public void InitChessPlay(GamePlay ui){
        for (int j = 0; j < 8; j++) {
            Pawn whitePawn = new Pawn(1, j, Color.WHITE); //Tạo một quân tốt ở vị trí ô (1,j) màu trắng
            whitePawn.setMove(1, j);
            
            
            //Cài đặt UI tốt trắng
            ui.squares[1][j].setText(whitePawn.symbol);
            ui.squares[1][j].setFont(new Font("Serif", Font.BOLD, 36));
            ui.squares[1][j].setForeground(whitePawn.color);

            // Black pawns on row 6
            Pawn blackPawn = new Pawn(6, j, Color.BLACK);
            blackPawn.setMove(6, j);
            ui.squares[6][j].setText(blackPawn.symbol);
            ui.squares[6][j].setFont(new Font("Serif", Font.BOLD, 36));
            ui.squares[6][j].setForeground(blackPawn.color);
        }
    }
}
