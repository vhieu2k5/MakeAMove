package jvtest;

import javax.swing.*;
import java.awt.event.ActionListener;

public class GameTimer {
    private int whiteTime;
    private int blackTime;
    private boolean whiteTurn = true;
    private Timer timer;
    private OnTimeChangeListener listener;

    public interface OnTimeChangeListener {
        void onWhiteTimeChange(int whiteSeconds);
        void onBlackTimeChange(int blackSeconds);
        void onTimeUp(String side);
    }

    public GameTimer(int minutes, OnTimeChangeListener listener) {
        this.whiteTime = minutes * 60;
        this.blackTime = minutes * 60;
        this.listener = listener;

        timer = new Timer(1000, e -> tick());
    }

    private void tick() {
        if (whiteTurn) {
            whiteTime--;
            listener.onWhiteTimeChange(whiteTime);
            if (whiteTime <= 0) {
                timer.stop();
                listener.onTimeUp("White");
            }
        } else {
            blackTime--;
            listener.onBlackTimeChange(blackTime);
            if (blackTime <= 0) {
                timer.stop();
                listener.onTimeUp("Black");
            }
        }
    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    public void switchTurn() {
        whiteTurn = !whiteTurn;
    }

    public void reset(int minutes) {
        stop();
        whiteTime = blackTime = minutes * 60;
        whiteTurn = true;
    }

    public String formatTime(int seconds) {
        int min = seconds / 60;
        int sec = seconds % 60;
        return String.format("%02d:%02d", min, sec);
    }

    public boolean isRunning() {
        return timer.isRunning();
    }
}
