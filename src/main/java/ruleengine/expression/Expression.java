package ruleengine.expression;

import java.util.Map;

public interface Expression {
    /**
     * Interprets the expression i.e: Operands that will be performed with AND, OR, NOT, EQUAL
     * @param bindings
     * @return returns true if action needs to be triggered or else false
     */
    boolean interpret(final Map<String, ?> bindings);
}
