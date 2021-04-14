package com.company.utils;

public class IntegerPairUtils {
    public static Pair<Integer,Integer> getPairWithMaxValuesOf(Pair<Integer,Integer> first, Pair<Integer,Integer>second){
        Pair<Integer,Integer>result = new Pair<>(0,0);
        result.setFirst(Math.max(first.getFirst(),second.getFirst()));
        result.setSecond(Math.max(second.getSecond(), first.getSecond()));
        return result;
    }
}
