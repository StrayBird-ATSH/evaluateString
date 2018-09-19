public class Main {
    public static void main(String[] args) {
        String expression = ReversePolishNotation.evaluate(
                "1 + cos(sin (2 + 3/(2+5.0)))");
        try {
            System.out.println(EvalPostFix.eval(expression));
//            System.out.println(EvalPostFixInt.eval(expression));
        } catch (Exception e) {
            System.out.println("The input expression has semantic errors");
        }
        System.out.println(1 + Math.cos(Math.sin(2 + 3 / (2 + 5.0))));
    }
}
