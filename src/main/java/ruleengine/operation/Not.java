package ruleengine.operation;

import ruleengine.expression.Expression;

import java.util.Map;
import java.util.Stack;

public class Not extends Operation{
    public Not(){
        super("NOT");
    }
    @Override
    public boolean interpret(Map<String, ?> bindings) {
        return !rightOperand.interpret(bindings);
    }

    @Override
    public Operation copy() {
        return new Not();
    }

    @Override
    public int parse(String[] tokens, int pos, Stack<Expression> stack) {
//        Expression right = stack.pop();
        int nextPos = findNextExpression(tokens, pos+1, stack);
        this.rightOperand = stack.pop();
        stack.push(this);
        return nextPos;
    }
}
