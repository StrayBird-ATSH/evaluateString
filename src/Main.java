public class Main {
    public static void main(String[] args) {
        String expression = ReversePolishNotation.evaluate(
                "1 + 5 * 6 + 3 * (2 + 3 * 2 + 2 - 1 + 3 * 3) + 10 / 5 - 6 * 1");
        System.out.println(EvalPostFix.eval(expression));
        System.out.println(1 + 5 * 6 + 3 * (2 + 3 * 2 + 2 - 1 + 3 * 3) + 10 / 5 - 6 * 1);
    }
}
