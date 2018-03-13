package integral;

import expressions.*;

/**
 *
 * @author Leha
 */
public class Parser {
    
    public static Expression parse(String s){
        if(!check(s)){  //0. проверить на правильность скобок
            System.err.println("Bracket error");
            return null;
        }
        
        
        //1. удалить пробелы и проверить на символы
        s = s.trim();
        int i = 0;
        while(i < s.length()){
            if(s.charAt(i) == ' '){
                s = s.substring(0, i) + s.substring(i + 1);
                continue;
            }
            char c = s.charAt(i);
            if(Character.isDigit(c)){
                i++;
                continue;
            }
            if(Character.isLetter(c) || c == '(' || c == ')'){
                i++;
                continue;
            }
            if(c == '+' || c == '-' || c == '*' || c == '/' || c == '^'){
                i++;
                continue;
            }
            
            System.err.println("Incorrect character");
            return null;
        }
        System.out.println("|" + s + "|");
        
        return parse2(s);
    }
    
    private static Expression parse2(String s){
        if(s.isEmpty()){
            System.err.println("Empty string");
            return null;
        }
        for(int i = 0; i < s.length(); i++){
            if()
        }
    }
    
    private static Expression
    
    static boolean check(String s){
        int n = 0;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '(')
                n++;
            else if(s.charAt(i) == ')')
                n--;
            
            if(n < 0)
                return false;
        }
        if(n == 0)
            return true;
        return false;
    }
}
