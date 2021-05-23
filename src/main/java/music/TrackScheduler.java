package music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEvent;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class TrackScheduler extends AudioEventAdapter {

    private final AudioPlayer player;
    private final BlockingQueue<AudioTrack> queue;

    public TrackScheduler(AudioPlayer player) {
        this.player = player;
        this.queue = new LinkedBlockingDeque<>();
    }

    // adaug track-uri in blockingQueue-ul meu
    public void addTrack(AudioTrack track){
        if(!this.player.startTrack(track, true)){
            this.queue.offer(track);
        }
    }

    // pornesc urmatorul track
    public String nextTrack(){
        AudioTrack track = this.queue.poll();
        this.player.startTrack(track, false);
        return track.getInfo().uri;
    }

    public int getQueueLength(){
        return queue.size();
    }

    @Override
    public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason) {
        if(endReason.mayStartNext){
            nextTrack();
        }
    }
}
