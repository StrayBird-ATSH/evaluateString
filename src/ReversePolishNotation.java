import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;


public class ReversePolishNotation {
    private static Map<String, Integer> operatorPriority = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        sc.close();

        StringBuilder result = new StringBuilder();
        Stack<String> stackForOperators = new Stack<>();
        initiatePriority();
        input = stringPreprocessor(input);
        String[] delimitedInput = input.split(" ");

        for (String element : delimitedInput) {
            if (element.equals(""))
                continue;
            if (element.equals("("))             //Push directly if meets left bracket
                stackForOperators.push("(");
            else if (isOperator(element))
                if (stackForOperators.isEmpty())
                    stackForOperators.push(element);
                else if (operatorPriority.get(element) >
                        operatorPriority.get(stackForOperators.peek()))
                    //Higher priority
                    stackForOperators.push(element);
                else {                               //Lower priority
                    for (int j = 0; j <= stackForOperators.size(); j++) {
                        String poppedElement = stackForOperators.pop();
                        result.append(poppedElement);
                        result.append(" ");
                        if (stackForOperators.isEmpty()) {
                            stackForOperators.push(element);
                            break;
                        } else if (operatorPriority.get(element) >
                                operatorPriority.get(stackForOperators.peek())) {
                            stackForOperators.push(element);
                            break;
                        }
                    }
                }
            else if (element.equals(")"))
                for (int j = 0; j < stackForOperators.size(); j++) {
                    String poppedElement1 = stackForOperators.pop();
                    if (poppedElement1.equals("("))
                        break;
                    else {
                        result.append(poppedElement1);
                        result.append(" ");
                    }
                }
            else {
                result.append(element);
                result.append(" ");
            }
        }
        for (int i = 0; i <= stackForOperators.size(); i++) {
            result.append(stackForOperators.pop());
            result.append(" ");
        }
        System.out.println(result);
    }


    /**
     * @param element The current element from the String array
     * @return True if the String is an operator
     */
    private static boolean isOperator(String element) {
        return operatorPriority.containsKey(element);
    }


    /**
     * @param input The raw input from user input
     * @return A String that has delimited the elements with space
     */
    private static String stringPreprocessor(String input) {
        input = input.replaceAll("\\(", " ( ");
        input = input.replaceAll("\\)", " ) ");
        input = input.replaceAll("\\{", " ( ");
        input = input.replaceAll("\\[", " ( ");
        input = input.replaceAll("}", " ) ");
        input = input.replaceAll("]", " ) ");
        input = input.replaceAll("~", " ~ ");
        input = input.replaceAll("\\*", " * ");
        input = input.replaceAll("/", " / ");
        input = input.replaceAll("%", " % ");
        input = input.replaceAll("\\+", " + ");
        input = input.replaceAll("-", " - ");
        input = input.replaceAll("<<", " << ");
        input = input.replaceAll(">>", " >> ");
        input = input.replaceAll(">> >", " >>> ");
        input = input.replaceAll("&", " & ");
        input = input.replaceAll("\\^", " ^ ");
        input = input.replaceAll("\\|", " | ");
        input = input.replaceAll("sin", " sin ");
        input = input.replaceAll("cos", " cos ");
        input = input.replaceAll("tan", " tan ");
        input = input.replaceAll("asin", " asin ");
        input = input.replaceAll("acos", " acos ");
        input = input.replaceAll("atan", " atan ");
        input = input.replaceAll("a sin", " asin ");
        input = input.replaceAll("a cos", " acos ");
        input = input.replaceAll("a tan", " atan ");
        input = input.replaceAll("toRadians", " toRadians ");
        input = input.replaceAll("toDegree", " toDegree ");
        input = input.replaceAll("exp", " exp");
        input = input.replaceAll("log", " log ");
        input = input.replaceAll("sqrt", " sqrt ");
        input = input.replaceAll("cbrt", " cbrt ");
        input = input.replaceAll("ceil", " ceil ");
        input = input.replaceAll("floor", " floor ");
        input = input.replaceAll("rint", " rint ");
        return input;
    }


    /**
     * Initiates the priority map
     */
    private static void initiatePriority() {
        operatorPriority.put("(", 0);
        operatorPriority.put("|", 1);
        operatorPriority.put("^", 2);
        operatorPriority.put("&", 3);
        operatorPriority.put("<<", 4);
        operatorPriority.put(">>", 4);
        operatorPriority.put(">>>", 4);
        operatorPriority.put("+", 5);
        operatorPriority.put("-", 5);
        operatorPriority.put("*", 6);
        operatorPriority.put("/", 6);
        operatorPriority.put("%", 6);
        operatorPriority.put("mod", 6);
        operatorPriority.put("~", 7);
        operatorPriority.put("sin", 8);
        operatorPriority.put("cos", 8);
        operatorPriority.put("tan", 8);
        operatorPriority.put("asin", 8);
        operatorPriority.put("acos", 8);
        operatorPriority.put("atan", 8);
        operatorPriority.put("toRadians", 8);
        operatorPriority.put("toDegree", 8);
        operatorPriority.put("exp", 8);
        operatorPriority.put("log", 8);
        operatorPriority.put("sqrt", 8);
        operatorPriority.put("cbrt", 8);
        operatorPriority.put("ceil", 8);
        operatorPriority.put("floor", 8);
        operatorPriority.put("rint", 8);
    }
}