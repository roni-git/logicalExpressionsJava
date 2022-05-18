/*323867077*/
import java.util.Map;

/**
 * @author roni gilboa
 * this class extends BinaryExpression
 * this is an expression from two values
 */
public class Nand extends BinaryExpression {
    /**.
     * A constructor
     * @param expression1 - the first expression
     * @param expression2 - the second expression
     */
    public Nand(Expression expression1, Expression expression2) {
        super(expression1, expression2);
    }
    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        try {
            return ((!(super.getEx1().evaluate(assignment)) && !(super.getEx2().evaluate(assignment)))
                    || !(super.getEx1().evaluate(assignment))
                    || !(super.getEx2().evaluate(assignment)));
        } catch (Exception e) {
            throw e;
        }
    }
    @Override
    public Boolean evaluate() throws Exception {
        try {
            return ((!(super.getEx1().evaluate()) && !(super.getEx2().evaluate()))
                    || !(super.getEx1().evaluate())
                    || !(super.getEx2().evaluate()));
        } catch (Exception e) {
            throw e;
        }
    }
    @Override
    public String toString() {
        return super.toStringForAll("A");
    }
    @Override
    public Expression assign(String var, Expression expression) {
        return new Nand(super.getEx1().assign(var, expression), super.getEx2().assign(var, expression));
    }

    @Override
    public Expression nandify() {
        Expression nandi1 = super.getEx1().nandify();
        Expression nandi2 = super.getEx2().nandify();
        return new Nand(nandi1, nandi2);
    }

    @Override
    public Expression norify() {
        Expression nori1 = super.getEx1().norify();
        Expression nori2 = super.getEx2().norify();
        Nor norifyCalculate = new Nor(new Nor(nori1, nori1), new Nor(nori2, nori2));
        return new Nor(norifyCalculate, norifyCalculate);
    }

    @Override
    public Expression simplify() {
        Expression simplify1 = super.getEx1().simplify();
        Expression simplify2 = super.getEx2().simplify();
        // x ↑ x = ~(x)
        if (simplify1.toString().equals(simplify2.toString())) {
            if (simplify2.toString().equals("F")) {
                return new Val(true);
            }
            if (simplify2.toString().equals("T")) {
                return new Val(false);
            } else {
                return new Not(simplify1);
            }
        }
        // x ↑ 1 = ~(x)
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
        // x ↑ 0 = 1
        if (simplify1.toString().equals("F")) {
            return new Val(true);
        }
        if (simplify2.toString().equals("F")) {
            return new Val(true);
        }
        return new Nand(simplify1, simplify2);
    }
}
