/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bretthanson.fantasysports.heronsformula;

/**
 *
 * @author bhanson
 */
public class HeronsFormula {

    public static void main(String[] args) {
        double a;

        a = triangleArea(3, 3, 3);
        System.out.println("A triangle with sides 3,3,3 has an area of " + a);

        a = triangleArea(3, 4, 5);
        System.out.println("A triangle with sides 3,4,5 has an area of " + a);

        a = triangleArea(7, 8, 9);
        System.out.println("A triangle with sides 7,8,9 has an area of " + a);
    }
    
    public static double triangleArea(int a, int b, int c)
    {
        double s, A;
        
        s = (a+b+c) / 2;
        A = Math.sqrt(s*(s-a)*(s-b)*(s-c));
        
        return A;
                
    }
}
