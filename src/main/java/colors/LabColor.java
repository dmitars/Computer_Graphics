package colors;

/**
 * The type Lab color.
 */
public class LabColor extends ColorObject{

    @Override
    protected String getExactValue(double value, int index) {
        if(index == 0) {
            if (value < 0) {
                value = 0;
            } else if (value > 100) {
                value = 100;
            }
        }
        return String.valueOf(value);
    }
}
