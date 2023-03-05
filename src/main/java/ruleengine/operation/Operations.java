package ruleengine.operation;

import ruleengine.operation.Operation;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Singleton class that contains map of operations supported by the Rule Engine
 */
public enum Operations {

    /* Application of singleton pattern for Operations Class*/
    INSTANCE;
    private final Map<String, Operation> availableOperations = new HashMap<>();

    public void registerOperation(String symbol, Operation operation){
        if(!availableOperations.containsKey(symbol)){
            availableOperations.put(symbol, operation);
        }
    }

    public void registerOperation(Operation operation){
        if(!availableOperations.containsKey(operation.getSymbol())){
            availableOperations.put(operation.getSymbol(), operation);
        }
    }

    public Operation getOperation(String symbol){
        return availableOperations.get(symbol);
    }

    public Set<String> getAllSymbols(){
        return availableOperations.keySet();
    }

}
