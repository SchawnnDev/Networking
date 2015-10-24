/*
 * ******************************************************
 *  * Copyright (C) 2015 SchawnnDev <contact@schawnndev.fr>
 *  *
 *  * This file (fr.schawnndev.networking.client.packets.packets.ConnectPacket) is part of Client.
 *  *
 *  * Created by SchawnnDev on 23/10/15 16:12.
 *  *
 *  * Client can not be copied and/or distributed without the express
 *  * permission of SchawnnDev.
 *  ******************************************************
 */

package fr.schawnndev.networking.client.packets.packets;

import fr.schawnndev.networking.client.packets.Packet;
import fr.schawnndev.networking.client.packets.PacketType;

public class ConnectPacket extends Packet {

    public String i_pseudo;
    public String o_pseudo;

    public ConnectPacket(String[] data) {
        super(data);

        i_pseudo = getData(1);

    }

    public ConnectPacket(String pseudo) {
        super(PacketType.CONNECT);
        this.o_pseudo = pseudo;

    }

    @Override
    protected void indexOutgoingData() {
        addData(o_pseudo);
    }
}
