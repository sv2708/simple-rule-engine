package ruleengine.app;

import ruleengine.Rule;
import ruleengine.actions.TerminateActionDispatcher;
import ruleengine.expression.Expression;
import ruleengine.expression.ExpressionParser;
import ruleengine.operation.And;
import ruleengine.operation.Equals;
import ruleengine.operation.Not;
import ruleengine.operation.Operations;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class App {

    public static void main(String[] args) {
        Operations operations = Operations.INSTANCE;

        // register new operations with the previously created container
        operations.registerOperation(new And());
        operations.registerOperation(new Equals());
        operations.registerOperation(new Not());

//        Expression ex1 = ExpressionParser.fromString("TEMPERATURE = 100 AND WEATHER = 'SUNNY'");
//        Expression ex2 = ExpressionParser.fromString("TEMPERATURE2 = -32 AND WEATHER2 = 'FREEZING'");
        Expression ex3 = ExpressionParser.fromString("TEMPERATURE = 75 AND WEATHER = 'PLEASANT' AND NOT AQI = 80");
//        Expression ex3 = ExpressionParser.fromString("NOT AQI = 80");



//        Rule rule1 = Rule.builder()
//                .actionDispatcher(new RunActionDispatcher())
//                .expressions(Arrays.asList(ex1, ex2))
//                .build();

        Rule rule2 = Rule.builder()
                .expressions(Arrays.asList(ex3))
                .actionDispatcher(new TerminateActionDispatcher()) // action to be performed if evaluated true
                .build();

        Map<String, Object> bindings = new HashMap<>();
        bindings.put("TEMPERATURE", 75);
        bindings.put("WEATHER", "'PLEASANT'");
        bindings.put("AQI", 79);
        bindings.put("TEMPERATURE2", -32);
        bindings.put("WEATHER2", "'FREEZING'");
//        Boolean result = rule1.evaluate(bindings);
//        System.out.println("Action Triggered: " + result);
        Boolean result = rule2.evaluate(bindings);
        System.out.println("Action Triggered: " + result);
    }
}