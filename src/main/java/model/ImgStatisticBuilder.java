package model;

public class ImgStatisticBuilder {
    private ImgStatistic imgStatistic;

    public ImgStatisticBuilder() {
        imgStatistic = new ImgStatistic();
    }

    public ImgStatisticBuilder buildCompression(String compression){
        imgStatistic.setCompression(parseCompression(compression));
        return this;
    }

    public ImgStatisticBuilder buildColorDepth(String colorDepth){
        imgStatistic.setColorDepth(parseColorDepth(colorDepth));
        return this;
    }

    public ImgStatisticBuilder buildWidth(String width){
        imgStatistic.setWidth(parseSize(width));
        return this;
    }

    public ImgStatisticBuilder buildHorResolution(String horResolution){
        imgStatistic.setHorResolution(parseResolution(horResolution));
        return this;
    }

    public ImgStatisticBuilder buildVertResolution(String vertResolution){
        imgStatistic.setVertResolution(parseResolution(vertResolution));
        return this;
    }

    public ImgStatisticBuilder buildHeight(String height){
        imgStatistic.setHeight(parseSize(height));
        return this;
    }

    public ImgStatisticBuilder buildName(String name){
        imgStatistic.setName(name);
        return this;
    }

    public void buildValue(String value,ImgDataType dataType){
        switch (dataType){
            case WIDTH -> buildWidth(value);
            case HEIGHT -> buildHeight(value);
            case WIDTH_DPI -> buildHorResolution(value);
            case HEIGHT_DPI -> buildVertResolution(value);
            case COLOR_DEPTH -> buildColorDepth(value);
            case COMPRESSION -> buildCompression(value);
        }
    }

    public void buildResolution(Resolution resolution){
        if(resolution.isPositive()) {
            buildVertResolution(String.valueOf(resolution.getVertDpi()));
            buildHorResolution(String.valueOf(resolution.getHorDpi()));
        }
    }

    public ImgStatistic build(){
        return imgStatistic;
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
}
