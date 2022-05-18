/*323867077*/
import java.util.Map;
import java.util.TreeMap;
/**
 * @author roni gilboa
 * A tast class to our code
 */
public class ExpressionsTest {
    /**.
     * A main for testing our code
     * @param args - a line of args
     * @throws Exception - if the envaluate is throw exception
     */
    public static void main(String[] args) throws Exception {
        Expression expression = new Or(
                                    new And(
                                        new Nand(
                                            new Val(true),
                                            new Var("x")),
                                        new Var("y")),
                                    new Var("z"));
        System.out.println(expression.toString());
        Map<String, Boolean> assignment = new TreeMap<String, Boolean>();
        assignment.put("x", true);
        assignment.put("y", true);
        assignment.put("z", false);
        try {
            System.out.println(new Val(expression.evaluate(assignment)).toString());
        } catch (Exception e) {
            throw e;
        }
        System.out.println(expression.nandify());
        System.out.println(expression.norify());
        System.out.println(expression.simplify());
        return;
    }
}