/*
323867077
*/

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author roni gilboa
 * the class BinaryExpression is an abstract class that extends BaseExpression
 * this class deals with the behavior of logical expressions that operate on two parameters
 */
public abstract class BinaryExpression extends BaseExpression {
    //fields
    private Expression ex1;
    private Expression ex2;
    /**.
     * A constructor
     * @param expression1 - the first expression
     * @param expression2 - the second expression
     */
    public BinaryExpression(Expression expression1, Expression expression2) {
        this.ex1 = expression1;
        this.ex2 = expression2;
    }

    /**
     * the function returns a list of the variables in the expression.
     * @return List<String> - a list of the variables in the expression.
     */
    public List<String> getVariables() {
        List<String> listForAll = new ArrayList<>();
        //put all the variable in a list- ex1's variables and ex2's variables
        listForAll.addAll(this.ex1.getVariables());
        listForAll.addAll(this.ex2.getVariables());
        //staying from every variable just one
        Set<String> oneFromEachVariable = new HashSet<>(listForAll);
        listForAll.clear();
        listForAll.addAll(oneFromEachVariable);

        return listForAll;
    }
    /**
     * this function returns a nice string representation of the expression.
     * @param sign - a string valuethat is actually a logic sign
     * @return string - the string of the expression
     */
    public String toStringForAll(String sign) {
       return ("(" + this.getEx1().toString() + " " + sign + " " + this.getEx2().toString() + ")");
    }
    /**.
     * the function getEx1 return this ex1
     *
     * @return ex1 - this ex1 expression
     */
    public Expression getEx1() {
        return this.ex1;
    }
    /**.
     * the function getEx2 return this ex2
     *
     * @return ex2 - this ex2 expression
     */
    public Expression getEx2() {
        return this.ex2;
    }
}
