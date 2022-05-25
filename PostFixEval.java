import java.lang.Math;

/**
 * This is the class for storing and evaluating the postfix expression input by the user.
 * It stores the expression has a method to evaluate the expression as well as
 * to perform the calculation of every mathematical operator in the expression.
 */
public class PostFixEval {
    private StringBuffer postFixExpression;
    private StackInheritance<Float> stack = new StackInheritance<>();

    /**
     * Constructor for PostFixEval class.
     * Sets the postFixExpression instance variable.
     * @param postFixExpression Postfix expression in text form.
     */
    public PostFixEval(StringBuffer postFixExpression) {
        this.postFixExpression = postFixExpression;
    }

    /**
     * Performs calculation of postfix through algorithm of pushing and popping numbers
     * from stack and applying mathematical operators.
     * @return
     */
    public float evaluatePostFixExpression() {

        while (getPostFixExpression().charAt(0) != ')')
        {
            do {
                if (String.valueOf(getPostFixExpression().charAt(0)).matches("-?[0-9]")) {
                    String numString = "";

                    do {
                        numString = numString.concat(String.valueOf(getPostFixExpression().charAt(0)));
                        getPostFixExpression().deleteCharAt(0);
                    } while (String.valueOf(getPostFixExpression().charAt(0)).matches("-?[0-9]"));

                    stack.push(Float.valueOf(numString));
                }
                else if (String.valueOf(getPostFixExpression().charAt(0)).matches("[-+*/%^]")) {
                    float x = stack.pop();
                    float y = stack.pop();

                    String operator = String.valueOf(getPostFixExpression().charAt(0));
                    getPostFixExpression().deleteCharAt(0);

                    float result = calculate(x, y, operator);

                    stack.push(result);
                }
            } while (getPostFixExpression().charAt(0) != ' ');

            getPostFixExpression().deleteCharAt(0); // delete space (' ') between each number or mathematical operator in the expression
        }

        return stack.pop();
    }

    /**
     * Calculates the mathematical operation between the two numbers.
     * @param x First element popped from stack
     * @param y Second element popped from stack
     * @param operator String resembling mathematical operator
     * @return Result of math calculation
     */
    private float calculate(float x, float y, String operator) {
        float result = 0;

        if (operator.matches("-")) {
            result = y - x;
        }
        else if (operator.matches("\\+")) {
            result = y + x;
        }
        else if (operator.matches("\\*")) {
            result = y * x;
        }
        else if (operator.matches("/")) {
            result = y / x;
        }
        else if (operator.matches("%")) {
            result = y % x;
        }
        else if (operator.matches("\\^")) {
            result = (int) Math.pow(y, x);
        }

        return result;
    }

    /**
     * Function to get the text of the postfix expression
     * @return Postfix expression
     */
    private StringBuffer getPostFixExpression() {
        return this.postFixExpression;
    }
}