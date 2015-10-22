/*
 * ******************************************************
 *  * Copyright (C) 2015 SchawnnDev <contact@schawnndev.fr>
 *  *
 *  * This file (fr.schawnndev.networking.server.network.NetworkServer) is part of Server.
 *  *
 *  * Created by SchawnnDev on 23/10/15 00:13.
 *  *
 *  * Server can not be copied and/or distributed without the express
 *  * permission of SchawnnDev.
 *  ******************************************************
 */

package fr.schawnndev.networking.server.network;

import fr.schawnndev.networking.server.Main;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class NetworkServer {

    private ServerSocket serverSocket;
    private int port;
    private boolean running;
    private Thread thread;

    public NetworkServer(int port) {
        this.port = port;
        this.running = false;
    }

    public void start() {

        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
            Main.getInstance().log("Failed to connecting server to port " + port + ". More infos: " + e.getMessage());
            return;
        }

        this.thread = new Thread(new Runnable() {

            @Override
            public void run() {

                while (running) {

                    try {
                        final Socket socket = serverSocket.accept();
                        Main.getInstance().log("Un utilisateur " + socket.getRemoteSocketAddress() + " s'est connecte!");

                        new Thread(new Runnable() { // Lectures des envois des clients

                            @Override
                            public void run() {

                                boolean err = false;

                                while (!err && socket.isConnected()) {

                                    try {
                                        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                                        String response = dataInputStream.readUTF();
                                        //TODO: parse
                                        Main.getInstance().log(response);
                                    } catch (EOFException e) {
                                        err = true;
                                        Main.getInstance().log("L'utilisateur " + socket.getRemoteSocketAddress() + " s'est deconnecte!");
                                    } catch (IOException e) {
                                        err = true;
                                        e.printStackTrace();
                                    }


                                }

                            }

                        }).start();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }

            }

        });

        // Starting thread

        this.thread.start();

        // Yeah, running!

        this.running = true;

    }

    public void stop() {
        this.running = false;
    }

}
