/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jvtest;
import java.awt.Color;

/**
 *
 * @author ADMIN
 */
public class point { //Lớp vị trí của quân cờ
    int i, j;
        boolean occupied=false;
        String piece = null; //Quân cờ nào đang ở đây
        Color c = Color.ORANGE;
        public point(int i,int j) {
            this.i=i;
            this.j=j;
        }
}
