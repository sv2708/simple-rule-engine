package ruleengine;

import ruleengine.actions.ActionDispatcher;
import ruleengine.expression.Expression;

import java.util.List;
import java.util.Map;

/**
 * Rule = Condition + Action
 * It evaluates the condition and executes the action if condition evaluates to true
 */
public class Rule {

    private List<Expression> expressions;
    private ActionDispatcher actionDispatcher;

    public Rule(Builder builder){
        this.expressions = builder.expressions;
        this.actionDispatcher = builder.actionDispatcher;
    }
    public static Builder builder(){
        return new Builder();
    }

    /**
     * Actual evaluation of the expression happens here
     * @param bindings values of the token used in expression Eg:- TEMPERATURE = 75. Here 75 is the value
     * @return returns true if expression evaluated to be true else false
     */
    public boolean evaluate(Map<String, ?> bindings){
        boolean result = true;
        for(Expression expression : expressions){
            result = result && expression.interpret(bindings);
        }
        if(result){
            actionDispatcher.fire();
        }
        return result;
    }

    public static class Builder{
        private List<Expression> expressions;
        private ActionDispatcher actionDispatcher;

        public Builder expressions(List<Expression> expressions){
            this.expressions = expressions;
            return this;
        }

        public Builder actionDispatcher(ActionDispatcher actionDispatcher){
            this.actionDispatcher = actionDispatcher;
            return this;
        }

        public Rule build(){
            return new Rule(this);
        }

    }


}
