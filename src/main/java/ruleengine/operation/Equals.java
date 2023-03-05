package ruleengine.operation;

import ruleengine.expression.BaseType;
import ruleengine.expression.Expression;
import ruleengine.expression.Variable;

import java.util.Map;
import java.util.Stack;

public class Equals extends Operation{

    public Equals(){
        super("=");
    }


    /**
     * Actually performs the operation
     * For equals the left side is the variable and right side is the value to be compared.
     * @param bindings Map of values for tokens used in expression. TEMPERATURE = 75
     * @return result of the operation
     */
    @Override
    public boolean interpret(Map<String, ?> bindings) {
        Variable variable = (Variable) this.leftOperand;
        Object obj = bindings.get(variable.getName());  // checks for availability of variable in bindings map
        if(obj == null) return false;
        BaseType<?> baseType = (BaseType<?>) this.rightOperand;
        if(baseType.getType().equals(obj.getClass())){ // check if right side matches in both the type and the value
            if(baseType.getValue().equals(obj)){
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public Operation copy() {
        return new Equals();
    }

    /**
     *  Parses the current expression. Finds the left and right operands,
     *  sets it to the current operation(=) and pushes it to the stack.
     *  Eg: parses TEMPERATURE = 75.
     *  leftOperand is Variable TEMPERATURE
     *  rightOperand is actual value to be compared
     * @param tokens
     * @param pos pos points to the `EQUALS` operator in token array of the expression
     * @param stack
     * @return returns the position of the last parsed token in the array.
     */
    @Override
    public int parse(String[] tokens, int pos, Stack<Expression> stack) {

        if(pos-1 >= 0 && tokens.length >= pos+1){ // verifies if both left and right operands exists
            String variable = tokens[pos-1];
            this.leftOperand = new Variable(variable);
            // BaseType is the wrapper Object around the actual value and its type.
            this.rightOperand = BaseType.getBaseType(tokens[pos+1]);
            stack.push(this);
            return pos+1;
        }
        throw new IllegalArgumentException("Cannot assign value to the variable");
    }
}
