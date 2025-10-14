/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jvtest;

/**
 *
 * @author ADMIN
 */
public class point { //Lớp vị trí của quân cờ
    int i, j;
    String name=null; //Tên nước đi (sẽ áp dụng cho Castle 
        public point(int i,int j) {
            this.i=i;
            this.j=j;
        }
        public point(int i,int j,String s) {
            this.i=i;
            this.j=j;
            this.name=s;
        }
}