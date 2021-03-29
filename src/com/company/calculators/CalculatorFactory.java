package com.company.calculators;

public class CalculatorFactory {

    public static PointsCalculator getCalculator(CalculatorType calculatorType){
        return switch (calculatorType){
            case STEP_BY_STEP -> new StepByStepCalculator();
            case CDA -> new CdaCalculator();
            case BREZENHEM -> new BrezenhemCalculator();
            case BREZENHEM_CIRCLE -> new BrezenhemCircleCalculator();
        };
    }
}
