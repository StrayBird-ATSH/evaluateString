public class Main {
    public static void main(String[] args) {
        String expression = ReversePolishNotation.evaluate(
                "-1 + sin(3.1414926)");
        try {
//            System.out.println(EvalPostFixInt.eval(expression));
            System.out.println(EvalPostFix.eval(expression));
        } catch (Exception e) {
            System.out.println("The input expression has semantic errors");
        }
        System.out.println(-1 + Math.sin(3.1414926));
    }
}
