/*
 * ******************************************************
 *  * Copyright (C) 2015 SchawnnDev <contact@schawnndev.fr>
 *  *
 *  * This file (fr.schawnndev.networking.client.network.ClientServer) is part of Client.
 *  *
 *  * Created by SchawnnDev on 23/10/15 00:18.
 *  *
 *  * Client can not be copied and/or distributed without the express
 *  * permission of SchawnnDev.
 *  ******************************************************
 */

package fr.schawnndev.networking.client.network;

import fr.schawnndev.networking.client.packets.Packet;
import fr.schawnndev.networking.client.packets.PacketDictionary;
import fr.schawnndev.networking.client.packets.PacketType;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

public class NetworkClient {

    private Socket socket;
    private String ipAdress;
    private int port;
    private Thread thread;
    private String pseudo;
    private DataOutputStream dataOutputStream;

    public NetworkClient(String ipAdress, int port) {
        this.ipAdress = ipAdress;
        this.port = port;
        this.pseudo = "SchawnnDev";
    }

    private void writeMessage(String message) {

        try {
            dataOutputStream.writeUTF(pseudo + " : " + message);
        } catch (EOFException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void connect() {

        try {
            socket = new Socket(ipAdress, port);
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        this.thread = new Thread(new Runnable() {

            @Override
            public void run() {

                while (true) {

                    try {
                        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                        String rawData = dataInputStream.readUTF();
                        String[] data = rawData.trim().split(";");

                        PacketType packetType = PacketType.valueOf(data[0]);
                        Packet packet = PacketDictionary.translatePacketType(packetType, data);


                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        });

        // Starting thread

        this.thread.start();

    }

    public void disconnect() {

    }

}
