package images;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import model.ImgStatistic;
import org.apache.sanselan.ImageInfo;
import org.apache.sanselan.ImageReadException;
import org.apache.sanselan.Sanselan;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The type Dir processor.
 */
public class DirProcessor {
    private final File directory;

    /**
     * The Extensions.
     */
    static final String[] EXTENSIONS = new String[]{
            "gif", "png", "bmp", "jpg", "tif", "pcx"
    };

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

    /**
     * The Needed tags.
     */
    static final ArrayList<String>[] neededTags = new ArrayList[]{
            imageWidthTags,
            imageHeightTags,
            imageHorizontalResolutionTags,
            imageVerticalResolutionTags,
            imageColorDepthTags,
            imageCompressionTags
    };

    /**
     * The Image filter.
     */
    static final FilenameFilter IMAGE_FILTER = (dir, name) -> {
        for (final String ext : EXTENSIONS) {
            if (name.endsWith("." + ext)) {
                return true;
            }
        }
        return false;
    };

    /**
     * Instantiates a new Dir processor.
     *
     * @param directory the directory
     * @throws Exception the exception
     */
    public DirProcessor(File directory) throws Exception {
        if (!directory.isDirectory())
            throw new Exception("incorrect input directory");
        this.directory = directory;
    }

    /**
     * Gets images data.
     *
     * @return the images data
     * @throws Exception the exception
     */
    public ArrayList<ImgStatistic> getImagesData() throws Exception {
        ArrayList<ImgStatistic> data = new ArrayList<>();
        for (final File f : directory.listFiles(IMAGE_FILTER)) {
            Metadata metadata = ImageMetadataReader.readMetadata(f);
            var tempData = getDataFromMetadata(metadata);
            tempData[tempData.length - 1] = f.getName();
            if (!f.getAbsolutePath().endsWith("pcx")) {
                var resolution = getResolution(f);
                if(resolution[0]>0 && resolution[1]>0) {
                    tempData[2] = String.valueOf(resolution[0]);
                    tempData[3] = String.valueOf(resolution[1]);
                }
            }
            data.add(new ImgStatistic(tempData));
        }
        return data;
    }

    private int[] getResolution(File f) throws IOException, ImageReadException {
        final ImageInfo imageInfo = Sanselan.getImageInfo(f);
        final int physicalWidthDpi = imageInfo.getPhysicalWidthDpi();
        final int physicalHeightDpi = imageInfo.getPhysicalHeightDpi();
        return new int[]{physicalWidthDpi, physicalHeightDpi};
    }

    private String[] getDataFromMetadata(Metadata metadata) throws Exception {
        String[] temp = new String[neededTags.length + 1];
        for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {
                for (int i = 0; i < neededTags.length; i++) {
                    if (neededTags[i].contains(tag.getTagName())) {
                        temp[i] = tag.getDescription();
                        break;
                    }
                }
            }
            if (directory.hasErrors()) {
                StringBuilder errorBuilder = new StringBuilder();
                for (String error : directory.getErrors())
                    errorBuilder.append(error);
                throw new Exception(String.format("ERROR: %s\n", errorBuilder.toString()));
            }
        }

        return temp;
    }

}
