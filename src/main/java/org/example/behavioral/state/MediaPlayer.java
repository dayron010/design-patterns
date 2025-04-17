package org.example.behavioral.state;

// Concrete class
public class MediaPlayer {

    private State state;

    public MediaPlayer() {
        this.state = new StoppedState(); // Dastlabki holat
    }

    public void setState(State state) {
        this.state = state;
    }

    public void play() {
        state.play(this);
    }

    public void pause() {
        state.pause(this);
    }

    public void stop() {
        state.stop(this);
    }

}
