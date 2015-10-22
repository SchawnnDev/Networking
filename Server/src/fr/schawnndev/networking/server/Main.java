/*
 * ******************************************************
 *  * Copyright (C) 2015 SchawnnDev <contact@schawnndev.fr>
 *  *
 *  * This file (fr.schawnndev.networking.server.Main) is part of Server.
 *  *
 *  * Created by SchawnnDev on 22/10/15 23:47.
 *  *
 *  * Server can not be copied and/or distributed without the express
 *  * permission of SchawnnDev.
 *  ******************************************************
 */

package fr.schawnndev.networking.server;

import fr.schawnndev.networking.server.network.NetworkServer;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main extends JFrame {

    private static Main instance;
    private static SimpleDateFormat simpleDateFormat;
    private JTextArea consoleArea;
    private JList users;
    private NetworkServer networkServer;

    public Main() {

        // View

        initView();

        // Date

        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");

        log("Initializing view..");

        // Network

        log("Connecting server to port 7777");

        networkServer = new NetworkServer(7777);

        // Graphics

        setTitle("SchawnnDev Server");
        setSize(700, 800);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);

        log("Starting app");

    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                instance = new Main();
                instance.networkServer.start();
                instance.setVisible(true);
            }
        });

    }

    public static Main getInstance() {
        return instance;
    }

    void initView() {
        // Central panel
        JPanel jPanel = new JPanel();
        getContentPane().add(jPanel);
        jPanel.setLayout(new BorderLayout());

        consoleArea = new JTextArea();
        consoleArea.setEditable(false);

        // Console view
        JScrollPane consoleScrollPane = new JScrollPane(consoleArea);
        consoleScrollPane.setBorder(BorderFactory.createTitledBorder("Console"));
        jPanel.add(consoleScrollPane, BorderLayout.CENTER);

        // Users
        users = new JList();
        JScrollPane userSCrollPane = new JScrollPane(users);

        userSCrollPane.setBorder(BorderFactory.createTitledBorder("Utilisateurs connectes:"));

        userSCrollPane.setPreferredSize(new Dimension(200, 0));
        jPanel.add(userSCrollPane, BorderLayout.EAST);

    }

    public void log(String logMessage) {
        consoleArea.append('[' + simpleDateFormat.format(new Date()) + "] " + logMessage + "\n");
    }

}
