/*323867077*/
import java.util.List;
import java.util.Map;
/**
 * @author roni gilboa
 * this class extends BinaryExpression
 * this is an expression from two values
 */
public class Xor extends BinaryExpression {
    /**.
     * A constructor
     * @param expression1 - the first expression
     * @param expression2 - the second expression
     */
    Xor(Expression expression1, Expression expression2) {
        super(expression1, expression2);
    }
    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        try {
            return (super.getEx1().evaluate(assignment) && !(super.getEx2().evaluate(assignment)))
                    || ((!(super.getEx1().evaluate(assignment)) && super.getEx2().evaluate(assignment)));
        } catch (Exception e) {
            throw e;
        }
    }
    @Override
    public Boolean evaluate() throws Exception {
        return ((super.getEx1().evaluate() && !(super.getEx2().evaluate()))
                || ((!(super.getEx1().evaluate()) && super.getEx2().evaluate())));
    }
    @Override
    public List<String> getVariables() {
        return super.getVariables();
    }
    @Override
    public String toString() {
        return super.toStringForAll("^");
    }
    @Override
    public Expression assign(String var, Expression expression) {
        return new Xor(super.getEx1().assign(var, expression), super.getEx2().assign(var, expression));
    }

    @Override
    public Expression nandify() {
        Expression nandi1 = super.getEx1().nandify();
        Expression nandi2 = super.getEx2().nandify();
        return new Nand(new Nand(nandi1, new Nand(nandi1, nandi2)), new Nand(nandi2, new Nand(nandi1, nandi2)));
    }

    @Override
    public Expression norify() {
        Expression nori1 = super.getEx1().norify();
        Expression nori2 = super.getEx2().norify();
        return new Nor(new Nor(new Nor(nori1, nori1), new Nor(nori2, nori2)), new Nor(nori1, nori2));
    }

    @Override
    public Expression simplify() {
        Expression simplify1 = super.getEx1().simplify();
        Expression simplify2 = super.getEx2().simplify();
        // x ^ x = 0
        if (simplify1.toString().equals(simplify2.toString())) {
            return new Val(false);
        }
        // x ^ 1 = ~(x)
        if (simplify1.toString().equals("T")) {
            if (simplify2.toString().equals("F")) {
                return new Val(true);
            }
            return new Not(simplify2);
        }
        if (simplify2.toString().equals("T")) {
            if (simplify1.toString().equals("F")) {
                return new Val(true);
            }
            return new Not(simplify1);
        }
        // x ^ 0 = x
        if (simplify1.toString().equals("F")) {
            return simplify2;
        }
        if (simplify2.toString().equals("F")) {
            return simplify1;
        }
        return new Xor(simplify1, simplify2);
    }
}
