package reversepolishcalculator;

import java.util.EmptyStackException;
import java.util.Stack;

public class RPN {
    
    private enum Errors {
    NONE, ILL_OP, SYNTAX;
    }

    static Stack<Double> numStack = new Stack<>();

    /** evaluate() breaks the expression into tokens, pops them 
     * onto a stack and returns the evaluated expression.     * 
     * @param expression
     * @return result
     */
    public static String evaluate(String expression) {
        
        String result;
        Errors retErr = Errors.NONE;
        numStack.clear();
        expression = expression.replaceAll("\\s+", ",");
        String[] tokens = expression.split(",");

        for (String token : tokens) {
            try {
                double tok = Double.parseDouble(token);
                numStack.push(tok);
            } catch (NumberFormatException n) {
                if (token.equals("+") || token.equals("-")
                        || token.equals("*") || token.equals("/")) {
                    retErr = operatorFound(token);
                } else {
                    retErr = Errors.ILL_OP;
                    break;
                }
            }
        }
        if (numStack.size() == 1 && retErr == Errors.NONE) {
            result = numStack.peek().toString();
        } else  {
            result = "syntax error";
        }
        return result;
    }

    /** operatorFound() handles each operator as they are found by
     * popping the top two items on the stack and solving them based on
     * the operator. 
     * 
     * @param str
     * @return retErr (if there are none, returns NONE)
     */
    public static Errors operatorFound(String str) {
        Errors retErr = Errors.NONE;
        try {
            double val1 = numStack.pop();
            double val2 = numStack.pop();

            switch (str) {
                case "+":
                    numStack.push(val2 + val1);
                    break;
                case "-":
                    numStack.push(val2 - val1);
                    break;
                case "*":
                    numStack.push(val2 * val1);
                    break;
                case "/":
                    numStack.push(val2 / val1);
                    break;
            }
        } catch (EmptyStackException e) {
            retErr = Errors.SYNTAX;
        }
        return retErr;
    }
}
