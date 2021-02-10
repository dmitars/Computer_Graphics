package colors;

/**
 * The type Xyz color.
 */
public class XYZColor extends ColorObject{

    @Override
    protected String getExactValue(double value, int index) {
        if(value<0) {
            value = 0;
        }else if(value>110){
            value = 110;
        }
        return String.valueOf(value);
    }
}
