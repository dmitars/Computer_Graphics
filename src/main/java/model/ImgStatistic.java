package model;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * The type Img statistic.
 */
public class ImgStatistic {
    /**
     * The Name.
     */
    String name;
    /**
     * The Width.
     */
    String width;
    /**
     * The Height.
     */
    String height;
    /**
     * The Hor resolution.
     */
    String horResolution;
    /**
     * The Vert resolution.
     */
    String vertResolution;
    /**
     * The Color depth.
     */
    String colorDepth;
    /**
     * The Compression.
     */
    String compression;

    /**
     * Instantiates a new Img statistic.
     *
     * @param values the values
     * @throws Exception the exception
     */
    public ImgStatistic(String[] values) throws Exception {
        if (values.length < 7)
            throw new Exception("incorrect values");
        name = values[6];
        width = parseSize(values[0]);
        height = parseSize(values[1]);
        horResolution = parseResolution(values[2]);
        vertResolution = parseResolution(values[3]);
        colorDepth = parseColorDepth(values[4]);
        compression = parseCompression(values[5]);
    }

    private ImgStatistic() {

    }

    /**
     * Get identifiers string [ ].
     *
     * @return the string [ ]
     */
    public static String[] getIdentifiers() {
        var fields = ImgStatistic.class.getDeclaredFields();
        return Arrays.stream(ImgStatistic.class.getDeclaredFields())
                .sequential()
                .map(Field::getName)
                .toArray(String[]::new);
    }

    /**
     * Get gui identifiers string [ ].
     *
     * @return the string [ ]
     */
    public static String[] getGUIIdentifiers(){
        return new String[]{
                "Name",
                "Width",
                "Height",
                "Horizontal\nResolution",
                "Vertical\nResolution",
                "Color\nDepth",
                "Compression"
        };
    }

    private String parseResolution(String value){
        if(value == null)
            return "";
        return value.split(" ")[0];
    }

    private String parseColorDepth(String value) {
        var values = value.split(" ");
        return values[0] + " bits/pixel";
    }

    private String parseCompression(String compression) {
        return compression == null ? " - " : compression;
    }

    private String parseSize(String value){
        var values = value.split(" ");
        if(values.length>1)
            return value;
        else
            return values[0]+" pixels";
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets width.
     *
     * @return the width
     */
    public String getWidth() {
        return width;
    }

    /**
     * Sets width.
     *
     * @param width the width
     */
    public void setWidth(String width) {
        this.width = width;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public String getHeight() {
        return height;
    }

    /**
     * Sets height.
     *
     * @param height the height
     */
    public void setHeight(String height) {
        this.height = height;
    }

    /**
     * Gets hor resolution.
     *
     * @return the hor resolution
     */
    public String getHorResolution() {
        return horResolution;
    }

    /**
     * Sets hor resolution.
     *
     * @param horResolution the hor resolution
     */
    public void setHorResolution(String horResolution) {
        this.horResolution = horResolution;
    }

    /**
     * Gets vert resolution.
     *
     * @return the vert resolution
     */
    public String getVertResolution() {
        return vertResolution;
    }

    /**
     * Sets vert resolution.
     *
     * @param vertResolution the vert resolution
     */
    public void setVertResolution(String vertResolution) {
        this.vertResolution = vertResolution;
    }

    /**
     * Gets color depth.
     *
     * @return the color depth
     */
    public String getColorDepth() {
        return colorDepth;
    }

    /**
     * Sets color depth.
     *
     * @param colorDepth the color depth
     */
    public void setColorDepth(String colorDepth) {
        this.colorDepth = colorDepth;
    }

    /**
     * Gets compression.
     *
     * @return the compression
     */
    public String getCompression() {
        return compression;
    }

    /**
     * Sets compression.
     *
     * @param compression the compression
     */
    public void setCompression(String compression) {
        this.compression = compression;
    }
}
