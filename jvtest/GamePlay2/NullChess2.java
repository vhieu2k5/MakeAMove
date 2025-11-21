/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jvtest.gameplay02;
import java.util.List;
import jvtest.*;

/**
 *
 * @author ADMIN
 */
public class NullChess2 extends ChessPiece2 {

    @Override
    public List<point> ValidMoves() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return null;
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    
    }

    public NullChess2(int i, int j) {
        this.x = i;
        this.y = j;
        //this.setColor(Color.ORANGE);
        this.is_Chess=false;
        this.name=null;
        this.symbol=null;
    }

    /**
     *
     * @param ui
     */
    @Override
    public void showValidMove() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    @Override
    public void deleteValidMove() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    } 
}
