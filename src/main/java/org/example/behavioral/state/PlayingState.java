package org.example.behavioral.state;

// Concrete state class (Playing State)
public class PlayingState implements State {

    @Override
    public void play(MediaPlayer mediaPlayer) {
        System.out.println("Already playing!");
    }

    @Override
    public void pause(MediaPlayer mediaPlayer) {
        System.out.println("Pausing the player.");
        mediaPlayer.setState(new PausedState());
    }

    @Override
    public void stop(MediaPlayer mediaPlayer) {
        System.out.println("Stopping the player.");
        mediaPlayer.setState(new StoppedState());
    }
}
