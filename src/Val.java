/*
323867077
*/
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**.
 * @author roni gilboa
 * tha class val is a class of a boolean value the implement Expression
 */
public class Val implements Expression {
    //fields
    private boolean value;
    /**.
     * A constructor
     * @param value - a boolean value (true\false)
     */
    Val(boolean value) {
        this.value = value;
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return this.value;
    }

    @Override
    public Boolean evaluate() throws Exception {
        return this.value;
    }

    @Override
    public List<String> getVariables() {
        return new ArrayList<>();
    }
    @Override
    public String toString() {
        if (this.value) {
            return "T";
        } else {
            return "F";
        }
    }
    @Override
    public Expression assign(String var, Expression expression) {
        //our value here is Val (true or false) and not a Var
        //so we will not change anything because the thing that changes in assign is a Var
        return this;
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
