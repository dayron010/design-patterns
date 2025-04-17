package org.example.behavioral.state;

// State interfeysi
public interface State {

    void play(MediaPlayer mediaPlayer);

    void pause(MediaPlayer mediaPlayer);

    void stop(MediaPlayer mediaPlayer);

}
