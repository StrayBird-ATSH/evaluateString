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


        operatorPriority.put("(", 0);
        operatorPriority.put("+", 1);
        operatorPriority.put("-", 1);
        operatorPriority.put("*", 2);
        operatorPriority.put("/", 2);

        input = stringPreprocessor(input);
        String[] delimitedInput = input.split(" ");

        for (String element : delimitedInput) {
            if (element.equals(""))
                continue;
            if (element.equals("(")) {             //如果是'('直接压栈
                stackForOperators.push("(");
            } else if (isOperator(element)) {  //如果是运算符
                if (stackForOperators.isEmpty())               //如果运算符栈是空，就直接压栈
                    stackForOperators.push(element);
                else if (operatorPriority.get(element) > operatorPriority.get(stackForOperators.peek()))
                    //运算符栈不为空，且当当前运算符的优先级比站内第一个运算符的优先级高的时候，压栈
                    stackForOperators.push(element);
                else {                               //栈不为空，且运算符的优先级小于等于栈顶元素
                    for (int j = 0; j <= stackForOperators.size(); j++) {
                        String poppedElement = stackForOperators.pop();    //弹出栈内第一个元素
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
            } else if (element.equals(")")) {       //如果是')'就把站内'('上的元素都弹出栈
                for (int j = 0; j < stackForOperators.size(); j++) {
                    String poppedElement1 = stackForOperators.pop();
                    if (poppedElement1.equals("("))
                        break;
                    else {
                        result.append(poppedElement1);
                        result.append(" ");
                    }
                }
            } else {
                //如果是数字就直接添加
                result.append(element);
                result.append(" ");
            }
        }
        //把栈内剩余的运算符都弹出站
        for (int i = 0; i <= stackForOperators.size(); i++) {
            result.append(stackForOperators.pop());
            result.append(" ");
        }

        System.out.println(result);
    }

    private static boolean isOperator(String element) {
        return operatorPriority.containsKey(element);
    }

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
}