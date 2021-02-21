package util;

import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import model.ImgDataType;
import model.ImgStatisticBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ImgStatisticBuilderUtils {

    /**
     * The Image width tags.
     */
    static final ArrayList<String> imageWidthTags = new ArrayList<>() {{
        add("Image Width");
        add("Width");
        add("X Max");
    }};


    /**
     * The Image height tags.
     */
    static final ArrayList<String> imageHeightTags = new ArrayList<>() {{
        add("Image Height");
        add("Height");
        add("Y Max");
    }};

    /**
     * The Image vertical resolution tags.
     */
    static final ArrayList<String> imageVerticalResolutionTags = new ArrayList<>() {{
        add("Y Resolution");
        add("Vertical DPI");
    }};

    /**
     * The Image horizontal resolution tags.
     */
    static final ArrayList<String> imageHorizontalResolutionTags = new ArrayList<>() {{
        add("X Resolution");
        add("Horizontal DPI");
    }};

    /**
     * The Image compression tags.
     */
    static final ArrayList<String> imageCompressionTags = new ArrayList<>() {{
        add("Compression");
        add("Compression Type");
    }};

    /**
     * The Image color depth tags.
     */
    static final ArrayList<String> imageColorDepthTags = new ArrayList<>() {{
        add("Bits Per Pixel");
        add("Bits per Pixel");
        add("Bits Per Sample");
        add("Data Precision");
    }};

    static final Map<ImgDataType,ArrayList<String>> neededTags = new HashMap<>(){
        {
            put(ImgDataType.WIDTH,imageWidthTags);
            put(ImgDataType.HEIGHT,imageHeightTags);
            put(ImgDataType.COLOR_DEPTH,imageColorDepthTags);
            put(ImgDataType.COMPRESSION,imageCompressionTags);
            put(ImgDataType.HEIGHT_DPI,imageVerticalResolutionTags);
            put(ImgDataType.WIDTH_DPI,imageHorizontalResolutionTags);
        }
    };

    public static void fillBuilderFromMetadata(ImgStatisticBuilder builder, Metadata metadata)
            throws Exception {
        for (Directory directory : metadata.getDirectories()) {
            fillBuilderFromDirectory(builder,directory);
            checkDirectory(directory);
        }
    }

    private static void fillBuilderFromDirectory(ImgStatisticBuilder builder, Directory directory){
        for (Tag tag : directory.getTags()) {
            for (var entrySetPair:neededTags.entrySet()) {
                if (entrySetPair.getValue().contains(tag.getTagName())) {
                    builder.buildValue(tag.getDescription(),entrySetPair.getKey());
                    break;
                }
            }
        }
    }

    private static void checkDirectory(Directory directory) throws Exception{
        if (directory.hasErrors()) {
            StringBuilder errorBuilder = new StringBuilder();
            for (String error : directory.getErrors())
                errorBuilder.append(error);
            throw new Exception(String.format("ERROR: %s\n", errorBuilder.toString()));
        }
    }
}
