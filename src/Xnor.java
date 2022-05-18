/*
323867077
*/
import java.util.Map;

/**
 * @author roni gilboa
 * this class extends BinaryExpression
 * this is an expression from two values
 */
public class Xnor extends BinaryExpression {
    /**.
     * A constructor
     * @param expression1 - the first expression
     * @param expression2 - the second expression
     */
    public Xnor(Expression expression1, Expression expression2) {
        super(expression1, expression2);
    }
    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        try {
            return ((!(super.getEx1().evaluate(assignment)) && !(super.getEx2().evaluate(assignment)))
                    || (super.getEx1().evaluate(assignment) && super.getEx2().evaluate(assignment)));
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Boolean evaluate() throws Exception {
        try {
            return ((!(super.getEx1().evaluate()) && !(super.getEx2().evaluate()))
                    || (super.getEx1().evaluate() && super.getEx2().evaluate()));
        } catch (Exception e) {
           throw e;
        }
    }
    @Override
    public String toString() {
        return super.toStringForAll("#");
    }
    @Override
    public Expression assign(String var, Expression expression) {
        return new Xnor(super.getEx1().assign(var, expression), super.getEx2().assign(var, expression));
    }

    @Override
    public Expression nandify() {
        Expression nandi1 = super.getEx1().nandify();
        Expression nandi2 = super.getEx2().nandify();
        return new Nand(new Nand(new Nand(nandi1, nandi1), new Nand(nandi2, nandi2)), new Nand(nandi1, nandi2));
    }

    @Override
    public Expression norify() {
        Expression nori1 = super.getEx1().norify();
        Expression nori2 = super.getEx2().norify();
        return new Nor(new Nor(nori1, new Nor(nori1, nori2)), new Nor(nori2, new Nor(nori1, nori2)));
    }

    @Override
    public Expression simplify() {
        Expression simplify1 = super.getEx1().simplify();
        Expression simplify2 = super.getEx2().simplify();
        // x # x = 1
        if (simplify1.toString().equals(simplify2.toString())) {
            return new Val(true);
        }
        if (simplify1.toString().equals("T") && simplify2.toString().equals("F")) {
            return new Val(false);
        }
        if (simplify1.toString().equals("F") && simplify2.toString().equals("T")) {
            return new Val(false);
        }
        return new Xnor(simplify1, simplify2);
    }
}
