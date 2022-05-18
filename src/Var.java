/*
323867077
*/
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author roni gilboa
 * this class is implements Expression
 * include a name of variable that has a boolean value
 */
public class Var implements Expression {
    //field
    private String variable;
    /**.
     * A constructor
     * @param variable - the name of the boolian value
     */
    Var(String variable) {
        this.variable = variable;
    }
    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        try {
        return assignment.get(this.variable);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Boolean evaluate() throws Exception {
        return evaluate(null);
    }
    @Override
    public String toString() {
        return this.variable;
    }
    @Override
    public List<String> getVariables() {
        List<String> variablesList = new ArrayList<>();
        variablesList.add(this.variable);
        return variablesList;
    }

    @Override
    public Expression assign(String var, Expression expression) {
        if (var.equals(this.variable)) {
            return expression;
        } else {
            return this;
        }
    }

    @Override
    public Expression nandify() {
        return this;
    }

    @Override
    public Expression norify() {
        return this;
    }

    @Override
    public Expression simplify() {
        return this;
    }
}
