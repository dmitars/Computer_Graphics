package com.company.algorithms;

import java.util.ArrayList;

public class LiangBarskyAlgorithm implements Algorithm{


    public ArrayList<Integer> calc(ArrayList<Integer> line, ArrayList<Integer> rect){
        int x1 = line.get(0);
        int y1 = line.get(1);
        int x2 = line.get(2);
        int y2 = line.get(3);
        int xMin = rect.get(0);
        int yMin = rect.get(1);
        int xMax = rect.get(2);
        int yMax = rect.get(3);

        int dx = x2 - x1;
        int dy = y2 - y1;

        ArrayList<Integer> in = new ArrayList<>();

        int[] S = new int[]{-dy, -dx, dy, dx};
        int[] Q = new int[]{y1 - yMin, x1 - xMin, yMax - y1, xMax - x1 };
        double tvh = 0, tvih = 1;
        for(int i = 0; i < 4; ++i){
            if(S[i] > 0){
                tvih = Math.min((double)Q[i]/S[i], tvih);
            }
            if(S[i] < 0){
                tvh = Math.max((double)Q[i]/S[i], tvh);
            }
            if(S[i] == 0){
                if(Q[i] < 0)
                    return in;
            }
        }

        int x1new = (int)Math.round((x1 + tvh * (x2 - x1)));
        int y1new = (int)Math.round((y1 + tvh * (y2 - y1)));
        int x2new = (int)Math.round((x1 + tvih * (x2 - x1)));
        int y2new = (int)Math.round((y1 + tvih * (y2 - y1)));

        if(x1new < xMin || x1new > xMax ||  x2new < xMin || x2new > xMax ||
                y1new < yMin || y1new > yMax ||  y2new < yMin || y2new > yMax){
            return in;
        }

        in.add(x1new);
        in.add(y1new);
        in.add(x2new);
        in.add(y2new);

        return in;
    }
}
