import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Stack;
import java.util.regex.Pattern;

class EvalPostFix {
    /**
     * @param exp The processed post-fix expression
     * @return Calculated result of the expression
     */
    static double eval(@NotNull String exp) {
        String doubleOperator = "*/%+-&^|<<>>>";
        String[] delimitedExp = exp.split(" ");
        Stack<String> stack = new Stack<>();
        for (String element : delimitedExp)
            if (ReversePolishNotation.isOperator(element))
                if (doubleOperator.contains(element))
                    stack.push(Double.toString(evaluateSingle(element,
                            Double.parseDouble(stack.pop()),
                            Double.parseDouble(stack.pop()))));
                else stack.push(Double.toString(evaluateSingle(element,
                        Double.parseDouble(stack.pop()))));
            else if (isNum(element)) stack.push(element);
            else System.out.println("Invalid expression found at element: " + element);
        return Double.parseDouble(stack.pop());
    }

    /**
     * @param operator Currently processing operator
     * @param var2     the first popped number
     * @param var3     the second popped number
     * @return The result of this single expression
     */
    @Contract(pure = true)
    private static double evaluateSingle(@NotNull String operator, double var2, double var3) {
        switch (operator) {
            case "*":
                return var3 * var2;
            case "/":
                return var3 / var2;
            case "%":
                return var3 % var2;
            case "+":
                return var3 + var2;
            case "-":
                return var3 - var2;
            case "&":
                return (int) var3 & (int) var2;
            case "^":
                return (int) var3 ^ (int) var2;
            case "|":
                return (int) var3 | (int) var2;
            case "<<":
                return (int) var3 << (int) var2;
            case ">>":
                return (int) var3 >> (int) var2;
            case ">>>":
                return (int) var3 >>> (int) var2;

        }
        return 0;
    }

    /**
     * @param operator Currently processing operator
     * @param var2     the first popped number
     * @return The result of this single expression
     */
    @Contract(pure = true)
    private static double evaluateSingle(@NotNull String operator, double var2) {
        switch (operator) {
            case "~":
                return ~(int) var2;
            case "sin":
                return Math.sin(var2);
            case "cos":
                return Math.cos(var2);
            case "tan":
                return Math.tan(var2);
            case "asin":
                return Math.asin(var2);
            case "acos":
                return Math.acos(var2);
            case "atan":
                return Math.atan(var2);
            case "toRadians":
                return Math.toRadians(var2);
            case "toDegrees":
                return Math.toDegrees(var2);
            case "exp":
                return Math.exp(var2);
            case "log":
                return Math.log(var2);
            case "sqrt":
                return Math.sqrt(var2);
            case "cbrt":
                return Math.cbrt(var2);
            case "ceil":
                return Math.ceil(var2);
            case "floor":
                return Math.floor(var2);
            case "rint":
                return Math.rint(var2);
        }
        return 0;
    }

    /**
     * Determines whether the element is a number
     *
     * @param str the input String
     * @return true if the String is a number, false otherwise
     */
    static private boolean isNum(String str) {
        String numRegex = "^\\d+(\\.\\d+)?$";
        return Pattern.matches(numRegex, str);
    }
}
