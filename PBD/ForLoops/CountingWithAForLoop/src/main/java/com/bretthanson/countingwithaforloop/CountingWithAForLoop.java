/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bretthanson.countingwithaforloop;

import java.util.Scanner;

/**
 *
 * @author bhanson
 */
public class CountingWithAForLoop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Type in a message, and I will display it five times." );
        System.out.println("Message: ");
        String message = scanner.nextLine();
        
        for(int n=1; n<=5; n=n+1)
        {
            System.out.println(n + ". " + message);
        }
    }
}
