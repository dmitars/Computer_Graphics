package images;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Metadata;
import model.ImgStatistic;
import model.ImgStatisticBuilder;
import model.Resolution;
import org.apache.sanselan.ImageInfo;
import org.apache.sanselan.ImageReadException;
import org.apache.sanselan.Sanselan;
import util.ImgStatisticBuilderUtils;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

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
        Objects.requireNonNull(directory, "directory cannot be null");
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
            var imgBuilder = new ImgStatisticBuilder();
            ImgStatisticBuilderUtils.fillBuilderFromMetadata(imgBuilder, metadata);
            imgBuilder.buildName(f.getName());
            var resolution = getResolutionFromNotPCX(f);
            imgBuilder.buildResolution(resolution);
            data.add(imgBuilder.build());
        }
        return data;
    }

    private Resolution getResolutionFromNotPCX(File f) throws IOException, ImageReadException {
        if (!f.getAbsolutePath().endsWith("pcx")) {
            final ImageInfo imageInfo = Sanselan.getImageInfo(f);
            final int physicalWidthDpi = imageInfo.getPhysicalWidthDpi();
            final int physicalHeightDpi = imageInfo.getPhysicalHeightDpi();
            return new Resolution(physicalWidthDpi, physicalHeightDpi);
        }
        return new Resolution();
    }

}
