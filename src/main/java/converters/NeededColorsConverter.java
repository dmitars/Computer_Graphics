package converters;

import colors.*;
import javafx.scene.paint.Color;

/**
 * The type Needed colors converter.
 */
public class NeededColorsConverter {
    private ColorType colorType;
    private ColorObject colorObject;

    /**
     * Instantiates a new Needed colors converter.
     */
    public NeededColorsConverter() {
    }

    /**
     * Instantiates a new Needed colors converter.
     *
     * @param colorObject the color object
     * @throws Exception the exception
     */
    public NeededColorsConverter(ColorObject colorObject) throws Exception {
        setColorObject(colorObject);
    }

    /**
     * Sets color object.
     *
     * @param colorObject the color object
     * @throws Exception the exception
     */
    public void setColorObject(ColorObject colorObject) throws Exception {
        if(colorObject instanceof CMYKColor)
            colorType = ColorType.CMYK;
        else if(colorObject instanceof XYZColor){
            colorType = ColorType.XYZ;
        }else if(colorObject instanceof LabColor){
            colorType = ColorType.LAB;
        }else{
            throw new Exception("unexpected color type");
        }
        this.colorObject = colorObject;
    }

    /**
     * Sets color.
     *
     * @param color the color
     * @throws Exception the exception
     */
    public void setColor(Color color) throws Exception {
        RGBToCMYKConverter converter = new RGBToCMYKConverter();
        ColorObject object = new RGBColor();
        object.setDoubleCoordinates(new double[]{color.getRed()*255,color.getGreen()*255,color.getBlue()*255});
        var cmykColor = converter.convert(object);
        setColorObject(cmykColor);
    }


    /**
     * Gets cmyk color.
     *
     * @return the cmyk color
     * @throws Exception the exception
     */
    public CMYKColor getCmykColor() throws Exception {
        return (CMYKColor) CommonConverter.convertColor(colorObject,colorType,ColorType.CMYK);
    }

    /**
     * Gets xyz color.
     *
     * @return the xyz color
     * @throws Exception the exception
     */
    public XYZColor getXyzColor() throws Exception{
        return (XYZColor) CommonConverter.convertColor(colorObject,colorType,ColorType.XYZ);
    }

    /**
     * Gets lab color.
     *
     * @return the lab color
     * @throws Exception the exception
     */
    public LabColor getLabColor() throws Exception{
        return (LabColor) CommonConverter.convertColor(colorObject,colorType,ColorType.LAB);
    }

    /**
     * Gets rgb color.
     *
     * @return the rgb color
     * @throws Exception the exception
     */
    public RGBColor getRgbColor() throws Exception{
        return (RGBColor) CommonConverter.convertColor(colorObject,colorType,ColorType.RGB);
    }

    /**
     * Gets with type.
     *
     * @param colorType the color type
     * @return the with type
     * @throws Exception the exception
     */
    public ColorObject getWithType(ColorType colorType) throws Exception {
        return switch (colorType){
            case LAB -> getLabColor();
            case XYZ -> getXyzColor();
            case CMYK -> getCmykColor();
            default -> throw new Exception("Unexpected color type");
        };
    }
}
