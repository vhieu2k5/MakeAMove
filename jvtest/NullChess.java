/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jvtest;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class NullChess extends ChessPiece {

    @Override
    public List<point> ValidMoves() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return null;
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    
    }

    public NullChess(int i, int j) {
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
        @Override
    public ChessPiece copy() {
        NullChess p = new NullChess(this.x, this.y);
        p.is_Chess = this.is_Chess;
        p.symbol = this.symbol;
        p.PotentialMoves = new ArrayList<>(this.PotentialMoves);
        return p;
    }
}
