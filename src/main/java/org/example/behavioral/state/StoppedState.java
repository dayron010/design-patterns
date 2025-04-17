package org.example.behavioral.state;

// Concrete State class (Stopped State)
public class StoppedState implements State {
    @Override
    public void play(MediaPlayer mediaPlayer) {
        System.out.println("Playing the player.");
        mediaPlayer.setState(new PlayingState());
    }

    @Override
    public void pause(MediaPlayer mediaPlayer) {
        System.out.println("Cannot pause, player is stopped!");
    }

    @Override
    public void stop(MediaPlayer mediaPlayer) {
        System.out.println("Already stopped!");
    }
}
