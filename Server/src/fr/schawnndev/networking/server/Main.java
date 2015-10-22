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

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    private static Main instance;
    private JTextPane consolePane;
    private JList users;

    public Main() {
        initView();

        setTitle("SchawnnDev Server");
        setSize(700, 800);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                instance = new Main();
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

        consolePane = new JTextPane();
        consolePane.setEditable(false);

        // Console view
        JScrollPane consoleScrollPane = new JScrollPane(consolePane);
        consoleScrollPane.setBorder(BorderFactory.createTitledBorder("Console"));
        jPanel.add(consoleScrollPane, BorderLayout.CENTER);

        // Users
        users = new JList();
        JScrollPane userSCrollPane = new JScrollPane(users);
        userSCrollPane.setPreferredSize(new Dimension(200, 0));
        jPanel.add(userSCrollPane, BorderLayout.EAST);


    }

}
