package com.kyle.message_req;

/**
 * Created by Java on 2016/10/13.
 */
public class VoiceMessage extends BaseMessage {
    private String Format;
    private String MediaId;
    private String Recognition;

    public String getFormat() {
        return Format;
    }

    public void setFormat(String format) {
        Format = format;
    }

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getRecognition() {
        return Recognition;
    }

    public void setRecognition(String recognition) {
        Recognition = recognition;
    }
}
