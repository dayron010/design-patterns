package org.example.behavioral.state;

// Concrete State class (Paused State)
public class PausedState implements State {

    @Override
    public void play(MediaPlayer mediaPlayer) {
        System.out.println("Resuming the player.");
        mediaPlayer.setState(new PlayingState());
    }

    @Override
    public void pause(MediaPlayer mediaPlayer) {
        System.out.println("Already paused!");
    }

    @Override
    public void stop(MediaPlayer mediaPlayer) {
        System.out.println("Stopping the player.");
        mediaPlayer.setState(new StoppedState());
    }
}
