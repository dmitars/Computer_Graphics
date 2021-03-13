package img.threshold;

/**
 * The type Interval.
 */
public class Interval {
    /**
     * The X base.
     */
    int xBase;
    /**
     * The X max.
     */
    int xMax;
    /**
     * The Y base.
     */
    int yBase;
    /**
     * The Y max.
     */
    int yMax;

    /**
     * Gets x base.
     *
     * @return the x base
     */
    public int getXBase() {
        return xBase;
    }


    /**
     * Sets x base.
     *
     * @param xBase the x base
     */
    public void setXBase(int xBase) {
        this.xBase = xBase;
    }

    /**
     * Gets x max.
     *
     * @return the x max
     */
    public int getXMax() {
        return xMax;
    }

    /**
     * Sets x max.
     *
     * @param xMax the x max
     */
    public void setXMax(int xMax) {
        this.xMax = xMax;
    }

    /**
     * Gets y base.
     *
     * @return the y base
     */
    public int getYBase() {
        return yBase;
    }

    /**
     * Sets y base.
     *
     * @param yBase the y base
     */
    public void setYBase(int yBase) {
        this.yBase = yBase;
    }

    /**
     * Gets y max.
     *
     * @return the y max
     */
    public int getYMax() {
        return yMax;
    }

    /**
     * Sets y max.
     *
     * @param yMax the y max
     */
    public void setYMax(int yMax) {
        this.yMax = yMax;
    }

    /**
     * Gets width.
     *
     * @return the width
     */
    public int getWidth() {
        return xMax - xBase + 1;
    }

    /**
     * Get height int.
     *
     * @return the int
     */
    public int getHeight(){
        return yMax - yBase + 1;
    }
}