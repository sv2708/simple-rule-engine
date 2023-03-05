package ruleengine.operation;

import ruleengine.expression.Expression;

import java.util.Stack;

public abstract class Operation implements Expression {

    protected final String symbol;

    protected Expression leftOperand = null;
    protected Expression rightOperand = null;

    public Operation(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public abstract Operation copy();

    public abstract int parse(final String[] tokens, final int pos, final Stack<Expression> stack);

    /**
     * Iterates through the token array to find the next expression.
     * @param tokens
     * @param pos position from which the iteration needs to start
     * @param stack next expression will be push into the stack
     * @return index of the last parsed token in the array
     */
    protected Integer findNextExpression(String[] tokens, int pos, Stack<Expression> stack){
        Operations operations = Operations.INSTANCE;
        for(int i = pos; i < tokens.length; i++){
            Operation nextOperation = operations.getOperation(tokens[i]);
            if(nextOperation != null){
                nextOperation = nextOperation.copy(); // next operation will be parsed and added to the stack.
                i = nextOperation.parse(tokens, i, stack);
                // It will then be popped and added as right operand from the previous parse call.
                return i;
            }
        }
        return null;
    }
}
