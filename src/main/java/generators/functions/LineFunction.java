package generators.functions;

public class LineFunction implements FunctionImpl<Double, Double> {

    private double aDouble;
    private double bDouble;

    public LineFunction(double aDouble, double bDouble) {
        this.aDouble = aDouble;
        this.bDouble = bDouble;
    }

    /**
     * Function y = ax + b, where x - parameter xValue
     *
     * @param xValue
     * @return y
     */
    @Override
    public Double apply(Double xValue) {
        return aDouble * xValue + bDouble;
    }
}
