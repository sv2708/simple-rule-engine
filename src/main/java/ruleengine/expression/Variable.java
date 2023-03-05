package ruleengine.expression;

import java.util.Map;

public class Variable implements Expression {

    private final String name;

    public Variable(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean interpret(Map<String, ?> bindings) {
        return true;
    }

}
