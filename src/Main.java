public class Main {
    public static void main(String[] args) {
        String expression = ReversePolishNotation.evaluate(
                "1+(2/{3|2}+1)");
        System.out.println(EvalPostFix.eval(expression));
        System.out.println(1 + (2 / (3 | 2) + 1));
    }
}
