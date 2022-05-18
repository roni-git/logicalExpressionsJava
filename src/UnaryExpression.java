import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class UnaryExpression extends BaseExpression {
    //fields
    private Expression ex;
    /**.
     * A constructor
     * @param expression - an expression
     */
    public UnaryExpression(Expression expression) {
        this.ex = expression;
    }
    /**.
     * this function return this expression
     * @return Expression - this expression
     */
    public Expression getEx() {
        return this.ex;
    }
    /**
     * the function returns a list of the variables in the expression.
     * @return List<String> - a list of the variables in the expression.
     */
    public List<String> getVariables() {
        List<String> listForAll = new ArrayList<>();
        //put all the variable in a list- ex1's variables and ex2's variables
        listForAll.addAll(this.ex.getVariables());
        //staying from every variable just one
        Set<String> oneFromEachVariable = new HashSet<>(listForAll);
        listForAll.clear();
        listForAll.addAll(oneFromEachVariable);

        return listForAll;
    }
}
