package ruleengine.operation;

import ruleengine.expression.Expression;

import java.util.Map;
import java.util.Stack;

public class And extends Operation {
    public And() {
        super("AND");
    }

    /**
     * Actual expression gets interpreted here.
     *
     *
     * @param bindings
     * @return  LeftOperand is an expression and rightOperand is also an expression. They both will be evaluated and returned.
     *          Eg:  TEMPERATURE = 85 && WEATHER = 'WARM'
     */
    @Override
    public boolean interpret(Map<String, ?> bindings) {
        return leftOperand.interpret(bindings) && rightOperand.interpret(bindings);
    }

    @Override
    public Operation copy() {
        return new And();
    }

    /**
     *  Parses the expression and returns the last parsed token index in the expression
     * @param tokens token array to be processed
     * @param pos Normally this is the AND operator's position in the token array
     * @param stack stack in which the expressions will be popped and evaluated.
     * @return last parsed token's index in array
     */
    @Override
    public int parse(String[] tokens, int pos, Stack<Expression> stack) {
        Expression left = stack.pop(); // left side of the current expression
        int nextPos = findNextExpression(tokens, pos+1, stack); // finds the next expression and adds it to stack
        Expression right = stack.pop();
        this.leftOperand = left;
        this.rightOperand = right;
        stack.push(this);
        return nextPos;
    }
}
