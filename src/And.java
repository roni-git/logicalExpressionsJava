/*323867077*/
import java.util.Map;
/**
 * @author roni gilboa
 * this class extends BinaryExpression
 * this is an expression from two values
 */
public class And extends BinaryExpression {
    /**.
     * A constructor
     * @param expression1 - the first expression
     * @param expression2 - the second expression
     */
    And(Expression expression1, Expression expression2) {
        super(expression1, expression2);
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return (this.getEx1().evaluate(assignment) && this.getEx2().evaluate(assignment));
    }

    @Override
    public Boolean evaluate() throws Exception {
        return (this.getEx1().evaluate() && this.getEx2().evaluate());
    }

    @Override
    public String toString() {
        return super.toStringForAll("&");
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new And(this.getEx1().assign(var, expression), this.getEx2().assign(var, expression));
    }

    @Override
    public Expression nandify() {
        Expression nandi1 = super.getEx1().nandify();
        Expression nandi2 = super.getEx2().nandify();
        return new Nand(new Nand(nandi1, nandi2), new Nand(nandi1, nandi2));
    }

    @Override
    public Expression norify() {
        Expression nori1 = super.getEx1().norify();
        Expression nori2 = super.getEx2().norify();
        return new Nor(new Nor(nori1, nori1), new Nor(nori2, nori2));
    }

    @Override
    public Expression simplify() {
        Expression simplify1 = super.getEx1().simplify();
        Expression simplify2 = super.getEx2().simplify();
        // x & x = x
        if (simplify1.toString().equals(simplify2.toString())) {
            return simplify1;
        }
        // x & 1 = x
        if (simplify1.toString().equals("T")) {
            return simplify2;
        }
        if (simplify2.toString().equals("T")) {
            return simplify1;
        }
        // x & 0 = 0
        if (simplify1.toString().equals("F") || simplify2.toString().equals("F")) {
            return new Val(false);
        }
        return new And(simplify1, simplify2);
    }
}
