package colors;

import java.util.stream.Stream;

/**
 * The type Rgb color.
 */
public class RGBColor extends ColorObject{

    @Override
    protected String getExactValue(double value,int index) {
        if(value<0) {
            value = 0;
        }else if(value>255){
            value = 255;
        }
        return String.valueOf((int)value);
    }

    /**
     * Get int coordinates int [ ].
     *
     * @return the int [ ]
     */
    public int[] getIntCoordinates(){
        return Stream.of(getCoordinates())
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
