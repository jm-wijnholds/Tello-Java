package com.example.tello;

public class TelloTest {

    public static void main(final String[] args) {
        final Tello tello = new Tello();

        tello.sendCommand("command");
        tello.sendCommand("takeoff");
        tello.sendCommand("ccw 90");
        tello.sendCommand("land");

        tello.finish();
    }

}
