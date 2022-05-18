/*323867077*/
import java.util.Map;
/**
 * @author roni gilboa
 * this class extends BinaryExpression
 * this is an expression from two values
 */
public class Nor extends BinaryExpression {
    /**.
     * A constructor
     * @param expression1 - the first expression
     * @param expression2 - the second expression
     */
    public Nor(Expression expression1, Expression expression2) {
        super(expression1, expression2);
    }
    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        try {
            return (!(super.getEx1().evaluate(assignment)) && !(super.getEx2().evaluate(assignment)));
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Boolean evaluate() throws Exception {
        try {
            return (!(super.getEx1().evaluate()) && !(super.getEx2().evaluate()));
        } catch (Exception e) {
            throw e;
        }
    }
    @Override
    public String toString() {
        return super.toStringForAll("V");
    }
    @Override
    public Expression assign(String var, Expression expression) {
        return new Nor(super.getEx1().assign(var, expression), super.getEx2().assign(var, expression));
    }

    @Override
    public Expression nandify() {
        Expression nandi1 = super.getEx1().nandify();
        Expression nandi2 = super.getEx2().nandify();
        Nand nandifyCalculate = new Nand(new Nand(nandi1, nandi1), new Nand(nandi2, nandi2));
        return new Nand(nandifyCalculate, nandifyCalculate);
    }

    @Override
    public Expression norify() {
        Expression nori1 = super.getEx1().norify();
        Expression nori2 = super.getEx2().norify();
        return new Nor(nori1, nori2);
    }

    @Override
    public Expression simplify() {
        Expression simplify1 = super.getEx1().simplify();
        Expression simplify2 = super.getEx2().simplify();
        // x ↓ x = ~(x)
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
        // x ↓ 1 = 0
        if (simplify1.toString().equals("T")) {
            return new Val(false);
        }
        if (simplify2.toString().equals("T")) {
            return new Val(false);
        }
        // x ↓ 0 = ~(x)
        if (simplify1.toString().equals("F")) {
            if (simplify2.toString().equals("T")) {
                return new Val(false);
            }
            return new Not(simplify2);
        }
        if (simplify2.toString().equals("F")) {
            if (simplify1.toString().equals("T")) {
                return new Val(false);
            }
            return new Not(simplify1);
        }
        return new Nor(simplify1, simplify2);
    }
}
