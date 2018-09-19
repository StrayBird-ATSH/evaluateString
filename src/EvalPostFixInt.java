import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Stack;
import java.util.regex.Pattern;

class EvalPostFixInt {
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
                    stack.push(Integer.toString(evaluateSingle(element,
                            Integer.parseInt(stack.pop()),
                            Integer.parseInt(stack.pop()))));
                else stack.push(Integer.toString(evaluateSingle(element,
                        Integer.parseInt(stack.pop()))));
            else if (isNum(element)) stack.push(element);
            else System.out.println("Invalid expression found at element: " + element);
        return Integer.parseInt(stack.pop());
    }

    /**
     * @param operator Currently processing operator
     * @param var2     the first popped number
     * @param var3     the second popped number
     * @return The result of this single expression
     */
    @Contract(pure = true)
    private static int evaluateSingle(@NotNull String operator, int var2, int var3) {
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
                return var3 & var2;
            case "^":
                return var3 ^ var2;
            case "|":
                return var3 | var2;
            case "<<":
                return var3 << var2;
            case ">>":
                return var3 >> var2;
            case ">>>":
                return var3 >>> var2;

        }
        return 0;
    }

    /**
     * @param operator Currently processing operator
     * @param var2     the first popped number
     * @return The result of this single expression
     */
    @Contract(pure = true)
    private static int evaluateSingle(@NotNull String operator, int var2) {
        switch (operator) {
            case "~":
                return ~var2;
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
        try {
            Integer.parseInt(str);
        } catch (Exception e) {
            System.out.println("There is an invalid element at " + str);
        }
        return true;
    }
}
