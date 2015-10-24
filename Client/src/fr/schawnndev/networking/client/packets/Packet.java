/*
 * ******************************************************
 *  * Copyright (C) 2015 SchawnnDev <contact@schawnndev.fr>
 *  *
 *  * This file (fr.schawnndev.networking.client.packets.Packet) is part of Client.
 *  *
 *  * Created by SchawnnDev on 23/10/15 16:12.
 *  *
 *  * Client can not be copied and/or distributed without the express
 *  * permission of SchawnnDev.
 *  ******************************************************
 */

package fr.schawnndev.networking.client.packets;

import java.util.ArrayList;
import java.util.List;

public abstract class Packet {

    private List<String> datas = new ArrayList<String>();
    private PacketType packetType;

    public Packet(String[] data) {

        for (String split : data)
            datas.add(split);

    }

    public Packet(PacketType packetType) {
        this.packetType = packetType;
    }

    public void addData(String data) {
        addData(data, 0);
    }

    public void addData(String data, int index) {
        datas.add(index, data);
    }


    public String getData(int index) {
        return datas.get(index);
    }

    protected abstract void indexOutgoingData();

    public String getOutgoingData() {
        indexOutgoingData();
        return serializeOutgoingData();
    }

    private String serializeOutgoingData() {
        StringBuffer stringBuffer = new StringBuffer(packetType.name()).append(';');

        for (int i = datas.size() - 1; i >= 0; i--) {
            String data = datas.get(i);
            stringBuffer.append(data).append(';');
        }

        return stringBuffer.toString();
    }


}
