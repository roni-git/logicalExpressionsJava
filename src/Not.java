/*
323867077
*/
import java.util.List;
import java.util.Map;

/**
 * @author roni gilboa
 * this class extends UnaryExpression
 * this is an expression from one value
 */
public class Not extends UnaryExpression {
    /**.
     * A constructor
     * @param expression - an Expression value
     */
    Not(Expression expression) {
        super(expression);
    }
    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        try {
            return !(super.getEx().evaluate(assignment));
        } catch (Exception e) {
            throw e;
        }

    }
    @Override
    public Boolean evaluate() throws Exception {
        try {
            return !(super.getEx().evaluate());
        } catch (Exception e) {
            throw e;
        }
    }
    @Override
    public String toString() {
        return "~(" + super.getEx().toString() + ")";
    }
    @Override
    public List<String> getVariables() {
        return super.getVariables();
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Not(super.getEx().assign(var, expression));
    }

    @Override
    public Expression nandify() {
        Expression nandi = super.getEx().nandify();
        return new Nand(nandi, nandi);
    }

    @Override
    public Expression norify() {
        Expression nori = super.getEx().norify();
        return new Nor(nori, nori);
    }

    @Override
    public Expression simplify() {
        Expression simplify1 = super.getEx().simplify();
        if (simplify1.toString().equals("T")) {
            return new Val(false);
        }
        if (simplify1.toString().equals("F")) {
            return new Val(true);
        }
        return new Not(simplify1);
    }
}
