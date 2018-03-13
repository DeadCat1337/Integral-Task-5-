package main;

import expressions.*;
import integral.*;
import java.util.Scanner;

/**
 *
 * @author Leha
 */
public class Main {
    
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in).useDelimiter("\n");
        String s = scn.next();
        System.out.println(s);
        Parser.parse(s);
        
        System.err.println("done");
    }
}
