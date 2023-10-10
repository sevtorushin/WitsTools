package generators.functions;

import java.util.Random;

public class RandomFunction implements FunctionImpl<Double, Double> {

    private double maxVariation;

    public RandomFunction(double maxVariation) {
        this.maxVariation = maxVariation;
    }

    /**
     * Function y = (rand - 0.5) * 2 *delta + x, where x - parameter xValue
     *
     * @param xValue input value
     * @return y
     */
    @Override
    public Double apply(Double xValue) {
        return xValue + (Math.random() - 0.5) * (maxVariation * 2);
    }
}
