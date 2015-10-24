/*
 * ******************************************************
 *  * Copyright (C) 2015 SchawnnDev <contact@schawnndev.fr>
 *  *
 *  * This file (fr.schawnndev.networking.server.packets.PacketDictionary) is part of Server.
 *  *
 *  * Created by SchawnnDev on 23/10/15 16:18.
 *  *
 *  * Server can not be copied and/or distributed without the express
 *  * permission of SchawnnDev.
 *  ******************************************************
 */

package fr.schawnndev.networking.server.packets;

import fr.schawnndev.networking.server.packets.packets.ConnectPacket;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class PacketDictionary {

    private static final Map<PacketType, Class<? extends Packet>> packets = new HashMap<PacketType, Class<? extends Packet>>();

    static {
        packets.put(PacketType.CONNECT, ConnectPacket.class);
    }

    public static Packet translatePacketType(PacketType packetType, String[] data) {
        Class clazz = packets.get(packetType);

        if (clazz != null) {

            try {
                return (Packet) clazz.getConstructor(data.getClass()).newInstance(data);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }

        return null;
    }

}
