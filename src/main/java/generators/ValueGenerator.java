package generators;

import generators.functions.FunctionImpl;

public class ValueGenerator {
    private String item;
    private double value;
    private FunctionImpl<Double, Double> function;

    public ValueGenerator(String item, double initValue, FunctionImpl<Double, Double> function) {
        this.item = item;
        this.value = initValue;
        this.function = function;
    }

    public Double buildNextValue(){
        value = function.apply(value);
        return value;
    }

    public String getItem() {
        return item;
    }

    public double getValue() {
        return value;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setFunction(FunctionImpl<Double, Double> function) {
        this.function = function;
    }

    public FunctionImpl<Double, Double> getFunction() {
        return function;
    }
}
