package g2evolution.localdatabasetesting.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by brajabasi on 07-04-2016.
 */
public class AttachmentType {

    private Pattern patternImage;
    private Pattern patternVideo;
    private Pattern patternAudio;
    private Matcher matcher;

    private static final String IMAGE_PATTERN =
            "([^\\s]+(\\.(?i)(jpg|png|gif|bmp))$)";
    private static final String VIDEO_PATTERN =
            "([^\\s]+(\\.(?i)(mp4|mpeg4|avi))$)";
    private static final String AUDIO_PATTERN =
            "([^\\s]+(\\.(?i)(mp3|ogg))$)";

    public AttachmentType(){
        patternImage = Pattern.compile(IMAGE_PATTERN);
        patternVideo = Pattern.compile(VIDEO_PATTERN);
        patternAudio = Pattern.compile(AUDIO_PATTERN);
    }

    /**
     * Validate image with regular expression
     * @param filePath image for validation
     * @return true valid image, false invalid image
     */
    public int getFileType(final String filePath){

        matcher = patternImage.matcher(filePath);
        if(matcher.matches()){
            return 1;
        }
        matcher = patternVideo.matcher(filePath);
        if(matcher.matches()){
            return 2;
        }
        matcher = patternAudio.matcher(filePath);
        if(matcher.matches()){
            return 3;
        }
        return -1;

    }
}
