package at.htlsaalfelden.adventskalender;


import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class AudioController {
    public static MediaPlayer mp;
    AtomicInteger currentSong = new AtomicInteger(0);
    AtomicBoolean isPlaying = new AtomicBoolean(false);

    double currentVolume = 0.5;
    //URL[] songs = new URL[]{getClass().getResource("/jingle_bells.mp3"), getClass().getResource("/merry_christmas.mp3")};
    List<URL> songs = new ArrayList<>();

    public AudioController(){
        songs.add(getClass().getResource("/at/htlsaalfelden/adventskalender/its-beginning-to-look-a-lot-like-christmas.mp3"));

        mp = new MediaPlayer(new Media(songs.get(currentSong.get()).toString()));
        setVolume(50);
    }
    public void play(){
        new Thread(() -> {

            while (true) {
                if (!isPlaying.get()) {
                    isPlaying.set(true);
                    //stopping the last MediaPlayer, otherwise songs won't switch
                    if (mp != null) {
                        mp.stop();
                        mp.dispose();
                    }
                    mp = new MediaPlayer(new Media(songs.get(currentSong.get()).toString()));
                    mp.setVolume(currentVolume);
                    mp.setOnEndOfMedia(() -> {
                        System.out.println("Song: " + songs.get(currentSong.get()).getFile() + " beendet, nächster Song: " + songs.get((currentSong.get()+1) % songs.size()).getFile());
                        currentSong.set((currentSong.get() + 1) % songs.size());
                        isPlaying.set(false);
                    });
                    mp.play();
                }
            }
        }).start();
    }

    public void pause(){
        mp.pause();
    }

    public void resume(){
        if (mp.getStatus().equals(MediaPlayer.Status.PLAYING))
        {
            System.out.println("Audio is already playing");
            return;
        }
        mp.play();
    }

    public void reset(){
        mp.stop();
        mp.dispose();
        mp = new MediaPlayer(new Media(songs.get(currentSong.get()).toString()));
        currentSong.set(0);
        isPlaying.set(false);
        play();
    }
    public void addSong(URL url){
        songs.add(0, url);
        reset();
    }

    public void setVolume(double volume){
        mp.setVolume(volume/100);
        currentVolume = volume/100;
    }
    public double getVolume(){
        return mp.getVolume()*100;
    }
    public void setMute(boolean isMute){
        mp.setMute(isMute);
    }
    public boolean isMute(){
        return mp.isMute();
    }
}

