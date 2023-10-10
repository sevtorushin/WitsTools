package generators;

import descriptions.TimeBasedDescription;
import generators.functions.FunctionImpl;

public class ValueGenerator {
    private String item;
    private double value;
    private FunctionImpl<Double, Double> function;

    public ValueGenerator(TimeBasedDescription description, double initValue, FunctionImpl<Double, Double> function) {
        this.item = description.getItem();
        this.value = initValue;
        this.function = function;
    }

    public Double nextDependentOnPrevValue(){
        value = function.apply(value);
        return value;
    }

    public Double nextIndependentValue(){
        return function.apply(value);
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
