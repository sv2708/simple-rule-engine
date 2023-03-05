package ruleengine.expression;

import ruleengine.operation.Operation;
import ruleengine.operation.Operations;

import java.util.Stack;

public class ExpressionParser {

    private static final Operations operations = Operations.INSTANCE;

    /**
     *  Parses the expression string
     *  End result will be single expression consisting of left operand, right operand and the operation needed to be performed
     *  Eg 1: expressionString => TEMPERATURE = -32 AND WEATHER = 'FREEZING'  will be converted to
     *  Expression
     *      leftOperand = Expression(Equals, leftOperand=Variable(TEMPERATURE), rightOperand=BaseType(Integer, -32))
     *      rightOperand = Expression(Equals, leftOperand=Variable(WEATHER), rightOperand=BaseType(String, 'FREEZING'))
     *      operation = AND
     *
     *    Expressions will be evaluated from left to right
     *   Eg 2: expressionString => TEMPERATURE = -32 AND WEATHER = 'FREEZING AND AQI = 56'  will be converted to
     *   Expression
     *       leftOperand =
     *          Expression
     *              leftOperand = Expression(Equals, leftOperand=Variable(TEMPERATURE), rightOperand=BaseType(Integer, -32))
     *              rightOperand = Expression(Equals, leftOperand=Variable(WEATHER), rightOperand=BaseType(String, 'FREEZING'))
     *              operation = AND
     *      rightOperand =
     *          Expression
     *          leftOperand = Expression(Equals, leftOperand=Variable(AQI), rightOperand=BaseType(Integer, 80))
     *          Operation = EQUALS
     *      Operation = AND
     * @param expressionString TEMPERATURE = -32 AND WEATHER = 'FREEZING'
     * @return Expression String returned as Expression
     */
    public static Expression fromString(String expressionString){
        Stack<Expression> stack = new Stack<>();
        String[] tokens= expressionString.split("\\s");
        for(int i = 0; i < tokens.length-1; i++){
            Operation op = operations.getOperation(tokens[i]);
            /* Below line will be true only for =, AND, OR and other operators */
            if(op != null){
                op = op.copy(); // create a new instance
                /* Parse method implemented in the corresponding Operator gets called */
                i = op.parse(tokens, i, stack);
            }else{

            }
        }
        return stack.pop();
    }

}
