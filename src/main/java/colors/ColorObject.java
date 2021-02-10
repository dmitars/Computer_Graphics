package colors;

import exceptions.ColorOutOfBoundsException;

import java.util.stream.Stream;

/**
 * The type Color object.
 */
public abstract class ColorObject {
    private String[] coordinates;

    /**
     * Is counted precisely boolean.
     *
     * @return the boolean
     */
    public boolean isCountedPrecisely() {
        return countedPrecisely;
    }

    /**
     * The Counted precisely.
     */
    protected boolean countedPrecisely = false;

    /**
     * Get coordinates string [ ].
     *
     * @return the string [ ]
     */
    public String[] getCoordinates() {
        return coordinates;
    }

    /**
     * Sets coordinates.
     *
     * @param coordinates the coordinates
     */
    public void setCoordinates(String[] coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * Get double coordinates double [ ].
     *
     * @return the double [ ]
     */
    public double[] getDoubleCoordinates() {
        return Stream.of(coordinates)
                .mapToDouble(Double::parseDouble)
                .toArray();
    }

    /**
     * Sets double coordinates.
     *
     * @param doubleCoordinates the double coordinates
     */
    public void setDoubleCoordinates(double[] doubleCoordinates) {
        coordinates = new String[doubleCoordinates.length];
        for (int i = 0; i < doubleCoordinates.length; i++)
            setCoordinate(i, doubleCoordinates[i]);
    }

    /**
     * Set coordinate.
     *
     * @param index the index
     * @param value the value
     */
    public void setCoordinate(int index, double value){
        String exactValue = getExactValue(value, index);
        if (Double.parseDouble(exactValue) != value)
            countedPrecisely = true;
        coordinates[index] = exactValue.substring(0,Math.min(exactValue.length(),4));
    }

    /**
     * Gets exact value.
     *
     * @param value the value
     * @param index the index
     * @return the exact value
     */
    abstract protected String getExactValue(double value, int index);
}
