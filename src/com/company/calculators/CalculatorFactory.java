package com.company.calculators;

/**
 * The type Calculator factory.
 */
public class CalculatorFactory {

    /**
     * Get calculator points calculator.
     *
     * @param calculatorType the calculator type
     * @return the points calculator
     */
    public static PointsCalculator getCalculator(CalculatorType calculatorType){
        return switch (calculatorType){
            case STEP_BY_STEP -> new StepByStepCalculator();
            case CDA -> new CdaCalculator();
            case BREZENHEM -> new BrezenhemCalculator();
            case BREZENHEM_CIRCLE -> new BrezenhemCircleCalculator();
        };
    }
}
