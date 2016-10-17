package com.kyle.message_resp;

/**
 * Created by Java on 2016/10/9.
 */
public class VoiceMessage extends BaseMessage{
    private Voice Voice;

    public Voice getVoice() {
        return Voice;
    }

    public void setVoice(Voice voice) {
        Voice = voice;
    }
}
