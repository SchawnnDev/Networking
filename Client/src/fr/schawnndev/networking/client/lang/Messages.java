/*
 * ******************************************************
 *  * Copyright (C) 2015 SchawnnDev <contact@schawnndev.fr>
 *  *
 *  * This file (fr.schawnndev.networking.client.lang.Messages) is part of Client.
 *  *
 *  * Created by SchawnnDev on 23/10/15 01:23.
 *  *
 *  * Client can not be copied and/or distributed without the express
 *  * permission of SchawnnDev.
 *  ******************************************************
 */

package fr.schawnndev.networking.client.lang;

import java.util.ArrayList;

public class Messages {

    private static ArrayList<String> messages = new ArrayList<String>();

    public static ArrayList<String> getMessages() {
        return messages;
    }

    public static void sendMessage(String message) {
        if (message != null && !messages.contains(message)) // Pour éviter les doubles messages
            messages.add(message);

        System.out.println("Adding msg to list: " + message);
    }

}
