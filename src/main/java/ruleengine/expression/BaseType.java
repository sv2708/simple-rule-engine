package ruleengine.expression;

import java.util.Map;

public class BaseType<T> implements Expression {

    public final T value;
    public final Class<T> type;

    public BaseType(T value, Class<T> type) {
        this.value = value;
        this.type = type;
    }

    public T getValue() {
        return value;
    }

    public Class<T> getType() {
        return type;
    }

    @Override
    public boolean interpret(Map<String, ?> bindings) {
        return false;
    }

    /**
     * Its a factory method that generates the BaseType of Java object with the type of value passed as string
     * @param value Eg: 0.25 string -> 0.25 float
     * @return BaseType object with value and type set
     */
    public static BaseType<?> getBaseType(String value){

        if(value == null) throw new IllegalArgumentException("Value String cannot be null for generating a BaseType");

        if(value.equals("true") || value.equals("false"))
            return new BaseType<>(Boolean.getBoolean(value), Boolean.class);
        else if(value.startsWith("'")){
            return new BaseType<>(value, String.class);
        } else if (value.contains(".")) {
            return new BaseType<>(Float.parseFloat(value), Float.class);
        }else{
            return new BaseType<>(Integer.parseInt(value), Integer.class);
        }
    }

}
