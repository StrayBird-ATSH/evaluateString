import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Stack;

class EvalPostFix {
    static double eval(@NotNull String exp) {
        String doubleOperator = "*/%+-&^|<<>>>";
        String singleOperator =
                "~ sin cos tan asin acos atan toRadians toDegrees" +
                        "exp log sqrt cbrt ceil floor rint";
        String[] delimitedExp = exp.split(" ");
        Stack<String> stack = new Stack<>();
        for (String element : delimitedExp) {
            if (ReversePolishNotation.isOperator(element)) {
                if (doubleOperator.contains(element))
                    stack.push(Double.toString(evaluateSingle(element,
                            Double.parseDouble(stack.pop()),
                            Double.parseDouble(stack.pop()))));
                else if (singleOperator.contains(element))
                    stack.push(Double.toString(evaluateSingle(element,
                            Double.parseDouble(stack.pop()))));
            } else stack.push(element);
        }
        return Double.parseDouble(stack.pop());
    }

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

    @Contract(pure = true)
    private static double evaluateSingle(String operator, double var2) {
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
}
