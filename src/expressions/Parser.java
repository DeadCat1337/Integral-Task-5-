package expressions;

/**
 *
 * @author Leha
 */
public class Parser {
    
    static Variable x;
    
    public static Expression parse(String s){
        if(!check(s)){  //0. проверить на правильность скобок
            System.err.println("Bracket error");
            return null;
        }
        
        
        //1. удалить пробелы, проверить на символы, поставить *
        s = s.replaceAll(" ", "");
        int i = 0;
        while(i < s.length()){
            char c = s.charAt(i);
            if(Character.isDigit(c) || Character.isLetter(c)){
                i++;
                continue;
            }            
            if(c == '+' || c == '*' || c == '/' || c == '^'){
                i++;
                continue;
            }
            
            if(c == '-'){
                if(i != 0){
                    if(s.charAt(i-1) != '('){
                        s = s.substring(0, i) + "+" + s.substring(i);
                    }
                }
                i += 2;
                continue;
            }
            
            if(c == '('){
                if (i != 0) {
                    if (s.charAt(i - 1) == ')' || 
                            Character.isDigit(s.charAt(i - 1)) || 
                            Character.isLetter(s.charAt(i - 1))) {
                        s = s.substring(0, i) + "*" + s.substring(i);
                        //System.out.println("|" + s + "|");
                    }
                }
                i++;
                continue;
            }
            if (c == ')') {
                if (i != s.length() - 1) {
                    if (s.charAt(i + 1) == '(' || 
                            Character.isDigit(s.charAt(i + 1)) || 
                            Character.isLetter(s.charAt(i + 1))) {
                        s = s.substring(0, i + 1) + "*" + s.substring(i + 1);
                        //System.out.println("|" + s + "|");
                    }
                }
                i++;
                continue;
            }
            
            System.err.println("Incorrect character");
            return null;
        }
        System.err.println("|" + s + "|");
        x = new Variable(-10, 10);
        x.setX(0);
        return parseMain(s);
    }
    
    private static Expression parseMain(String s){
        if(s.isEmpty()){
            System.err.println("Empty string");
            return null;
        }
        if(s.charAt(0) == '+')
            return parseMain(s.substring(1));
        //System.out.println(s);
        if(s.length() == 1)
            return parseSingle(s);
        
        //2.найти нулевую скобочную вложенность. 
        //Если нет, то удалить лишние скобки
        int n = 0;
        boolean was0 = false;
        char c;
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            if (c == '(') {
                n++;
            }
            if (c == ')') {
                n--;
            }
            
            //3. проверить на младшие операции
            if (n == 0) {
                if(i != s.length() - 1)
                    was0 = true;
                if (c == '+') {
                    return parseOperation(s.substring(0, i),
                            s.substring(i + 1), Operation.PLUS);
                }
                /*if (c == '-') {
                    return parseOperation(s.substring(0, i),
                            s.substring(i + 1), Operation.MINUS);
                }*/
            }
        }
        if (!was0) {
            return parseMain(s.substring(1, s.length() - 1));
        }
        
        //4. проверить на средние операции
        n = 0;
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            if (c == '(') {
                n++;
            }
            if (c == ')') {
                n--;
            }

            if (n == 0) {
                if (c == '*') {
                    return parseOperation(s.substring(0, i),
                            s.substring(i + 1), Operation.MULT);
                }
                if (c == '/') {
                    return parseOperation(s.substring(0, i),
                            s.substring(i + 1), Operation.DIV);
                }
            }
        }
        
        //5. проверить на старшие операции
        n = 0;
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            if (c == '(') {
                n++;
            }
            if (c == ')') {
                n--;
            }

            if (n == 0) {
                if (c == '^') {
                    return parseOperation(s.substring(0, i),
                            s.substring(i + 1), Operation.POW);
                }
            }
        }
        
        //6. Если операций нет, интерпретируем как одиночный операнд
        return parseSingle(s);
    }
    
    private static Expression parseOperation(String arg1, String arg2, int code){
        return new Operation(parseMain(arg1), parseMain(arg2), code);
    }
    
    private static Expression parseSingle(String s){
        if(s.isEmpty())
            return null;
        
        if(s.charAt(0) == '-')
            return new Function1(parseMain(s.substring(1)), Function1.REV);
        
        if(s.equalsIgnoreCase("x"))
            return x;
        if(s.equalsIgnoreCase("e"))
            return new Number(Math.E);
        if(s.equalsIgnoreCase("pi"))
            return new Number(Math.PI);
        
        if(Character.isDigit(s.charAt(0))){
            return new Number(Double.parseDouble(s));
        }
        
        return new Param(0, s);
    }
    
    
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
