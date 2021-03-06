package main;

import expressions.Parser;
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
        
        Expression ex = Parser.parse(s);
        
        System.err.println(ex.toString());
        
        System.err.println("RESULT | X = 0 : " + ex.getValue());
        
        System.err.println("done");
    }
}
