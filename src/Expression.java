import java.util.List;
import java.util.Map;

public interface Expression {
    /**
     * the fuction evaluate the expression using the variable values provided
     * in the assignment, and return the result.
     * If the expression contains a variable which is not in the assignment,
     * an exception is thrown.
     * @param assignment
     * @return boolean value - true or false- the value of the expression
     * @throws Exception
     */
    Boolean evaluate(Map<String, Boolean> assignment) throws Exception;

    /**
     * the function is a convenience method.
     * Like the `evaluate(assignment)` method above,
     * but uses an empty assignment.
     * @return boolean value - true or false- the value of the expression
     * @throws Exception
     */
    Boolean evaluate() throws Exception;

    /**
     * the function returns a list of the variables in the expression.
     * @return List<String> - list of the variables
     */
    List<String> getVariables();
    /**
     * the function returns a nice string representation of the expression.
     * @return string - a nice string representation of the expression.
     */
    String toString();
    /**
     * Returns a new expression in which all occurrences of the variable
     * var are replaced with the provided expression (Does not modify the
     * current expression).
     * @param var - a variable to replace
     * @param expression - an expression to replace with the variable
     * @return  Expression - new expression that getting after the replace
     */
    Expression assign(String var, Expression expression);
    /**
     * the function returns the expression tree resulting
     * from converting all the operations
     * to the logical Nand operation.
     * @return Expression - the expression tree resulting
     *                      from converting all the operations
     */
    Expression nandify();

    /**
     * the function returns the expression tree resulting
     * from converting all the operations to the logical Nor operation.
     * @return Expression - the expression tree resulting
     *                      from converting all the operations to the logical Nor operation.
     */
    Expression norify();

    /**
     *  the function returned a simplified version of the current expression.
     * @return
     */
    Expression simplify();
}
