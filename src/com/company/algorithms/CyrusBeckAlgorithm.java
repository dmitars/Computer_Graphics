package com.company.algorithms;

import com.company.utils.Pair;

import java.util.ArrayList;
import java.util.Comparator;

public class CyrusBeckAlgorithm {

    public ArrayList<Integer> calculate(ArrayList<ArrayList<Integer>> polygon, ArrayList<Integer> line) {
        ArrayList<Double> inputs = new ArrayList<>();
        ArrayList<Double> outputs = new ArrayList<>();

        int size = polygon.size();
        for (int i = size - 1; i >= 0; i--) {
            var currentEdge = polygon.get(i);
            var currentEdgeVector = getReversedVectorFrom(currentEdge);
            var normalX = -currentEdgeVector.getSecond() / currentEdgeVector.getFirst();
            var normalVector = new Pair<>(normalX, 1);
            int nextIndex = i == size - 1 ? 0 : i + 1;
            var nextEdge = polygon.get(nextIndex);
            var nextEdgeVector = new Pair<>(nextEdge.get(0) - currentEdge.get(0), nextEdge.get(1) - currentEdge.get(1));
            if (getScalarMultiplication(normalVector, nextEdgeVector) < 0) {
                normalVector = new Pair<>(-normalVector.getFirst(), -normalVector.getSecond());
            }

            var lineVector = getStraightVectorFrom(line);
            var multiplication = getScalarMultiplication(lineVector, normalVector);
            if (multiplication == 0)
                continue;
            var wi = new Pair<>(line.get(0) - currentEdge.get(0), line.get(1) - currentEdge.get(1));
            var t = -getScalarMultiplication(wi, normalVector) / multiplication;
            if (t < 0 || t > 1)
                continue;
            if (multiplication > 0)
                outputs.add(t);
            else
                inputs.add(t);
        }

        outputs.sort(Comparator.reverseOrder());
        inputs.sort(Comparator.reverseOrder());
        ArrayList<Integer> result = new ArrayList<>();

        if(inputs.size()>1){
            outputs.add(inputs.get(inputs.size()-1));
        }else if (outputs.size()>1){
            inputs.add(outputs.get(outputs.size()-1));
        }

        if (inputs.isEmpty()) {
            if (outputs.isEmpty())
                return result;
            result.add(line.get(0));
            result.add(line.get(1));
            result.addAll(getPoint(line, outputs.get(0)));
            return result;
        }

        if (outputs.isEmpty()) {
            result.addAll(getPoint(line, inputs.get(0)));
            result.add(line.get(2));
            result.add(line.get(3));
            return result;
        }

        result.addAll(getPoint(line, inputs.get(0)));
        result.addAll(getPoint(line, outputs.get(0)));
        return result;
    }

    private ArrayList<Integer> getPoint(ArrayList<Integer> line, double t) {
        ArrayList<Integer> result = new ArrayList<>();
        result.add(line.get(0) + (int) Math.round(t * (line.get(2) - line.get(0))));
        result.add(line.get(1) + (int) Math.round(t * (line.get(3) - line.get(1))));
        return result;
    }

    private Pair<Integer, Integer> getStraightVectorFrom(ArrayList<Integer> line) {
        return new Pair<>(line.get(2) - line.get(0), line.get(3) - line.get(1));
    }

    private Pair<Integer, Integer> getReversedVectorFrom(ArrayList<Integer> line) {
        return new Pair<>(line.get(0) - line.get(2), line.get(1) - line.get(3));
    }

    private double getScalarMultiplication(Pair<? extends Number, ? extends Number> firstVector,
                                           Pair<? extends Number, ? extends Number> secondVector) {
        return firstVector.getFirst().doubleValue() * secondVector.getFirst().doubleValue()
                + firstVector.getSecond().doubleValue() * secondVector.getSecond().doubleValue();
    }
}
