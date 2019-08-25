package com.example.tello;

import java.io.IOException;
import java.net.*;

public class Tello {

    /**
     * The IP-address from Tello
     */
    private static final String TELLO_IP = "192.168.10.1";

    /**
     * The Tello port to send commands to
     */
    private static final short TELLO_CMD_PORT = 8889;


    private InetAddress telloAddress;
    private DatagramSocket socket;


    public Tello() {
        try {
            telloAddress = InetAddress.getByName(TELLO_IP);
            socket = new DatagramSocket(new InetSocketAddress(TELLO_CMD_PORT));
        } catch (final UnknownHostException | SocketException e) {
            System.out.println("Cannot initiate Tello " + e.getMessage());
        }
    }

    /**
     * Sends commands to Tello
     *
     * @param cmd the command to send to Tello
     */
    public void sendCommand(final String cmd) {
        try {
            //Setting up the request to tello
            final DatagramPacket request = new DatagramPacket(cmd.getBytes(), cmd.getBytes().length, telloAddress, TELLO_CMD_PORT);
            socket.send(request);

            //Setting up the response from tello
            final DatagramPacket response = new DatagramPacket(cmd.getBytes(), cmd.getBytes().length);
            socket.receive(response);

            //Parsing response from tello
            final byte[] receiveBuffer = response.getData();
            final String quote = new String(receiveBuffer, 0, response.getLength());

            //Let's see the command from Tello
            System.out.println(quote);
        } catch (final IOException e) {
            System.out.println("Could not send command to Tello: " + e.getMessage());
        }
    }

    /**
     * Not realy needed for UDP, but we like to clean up stuff: closes the socket.
     */
    public void finish() {
        socket.close();
    }
}
