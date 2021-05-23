package music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.playback.MutableAudioFrame;
import net.dv8tion.jda.api.audio.AudioSendHandler;
import org.jetbrains.annotations.Nullable;

import java.nio.ByteBuffer;

public class AudioPlayerSendHandler implements AudioSendHandler {

    // audio player-ul.
    private final AudioPlayer audioPlayer;
    // cat trimite lava player-ul
    private final ByteBuffer byteBuffer;
    // aici se trimite audio-ul
    private final MutableAudioFrame mutableAudioFrame;

    public AudioPlayerSendHandler(AudioPlayer audioPlayer) {
        this.audioPlayer = audioPlayer;
        this.byteBuffer = ByteBuffer.allocate(1024); // la 20 secunde, el da 1024 bytes.
        this.mutableAudioFrame = new MutableAudioFrame();
        this.mutableAudioFrame.setBuffer(byteBuffer);
    }

    @Override
    public boolean canProvide() {
        // transmite la audio frame-ul care trimite la byte buffer.
        // se apeleaza metoda de mai jos. amazing
        return this.audioPlayer.provide(this.mutableAudioFrame);
    }

    @Nullable
    @Override
    public ByteBuffer provide20MsAudio() {
        return this.byteBuffer.flip(); // reseteaza bufferul.
    }

    @Override
    public boolean isOpus() {
        // lava player returneaza un opus deci e mereu true aici
        return true;
    }
}
