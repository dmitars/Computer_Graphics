package colors;

import java.util.stream.DoubleStream;

/**
 * The type Cmyk color.
 */
public class CMYKColor extends ColorObject{

    @Override
    protected String getExactValue(double value, int index) {
        if(value<0) {
            value = 0;
        }else if(value>1){
            value = 1;
        }
        return String.valueOf(value);
    }
}
