/*
 * ******************************************************
 *  * Copyright (C) 2015 SchawnnDev <contact@schawnndev.fr>
 *  *
 *  * This file (fr.schawnndev.networking.client.Main) is part of Client.
 *  *
 *  * Created by SchawnnDev on 22/10/15 23:47.
 *  *
 *  * Client can not be copied and/or distributed without the express
 *  * permission of SchawnnDev.
 *  ******************************************************
 */

package fr.schawnndev.networking.client;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    private static Main instance;
    private JTextPane chatPane;
    private JTextField userChatField;
    private JList users;
    private JButton sendButton;

    public Main() {
        initView();

        setTitle("SchawnnDev Server");
        setResizable(true);
        setSize(700, 800);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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

        // Users
        users = new JList();
        JScrollPane userSCrollPane = new JScrollPane(users);
        userSCrollPane.setPreferredSize(new Dimension(200, 0));
        jPanel.add(userSCrollPane, BorderLayout.EAST);

        // Chat
        JPanel chatPanel = new JPanel(new BorderLayout());
        jPanel.add(chatPanel, BorderLayout.CENTER);

        // Chat ui
        chatPane = new JTextPane();
        chatPane.setEditable(false);
        JScrollPane chatScrollPane = new JScrollPane(chatPane);
        chatScrollPane.setBorder(BorderFactory.createTitledBorder("Chat"));
        chatPanel.add(chatScrollPane, BorderLayout.CENTER);

        // Chat field

        JPanel panelInput = new JPanel(new BorderLayout());
        jPanel.add(panelInput, BorderLayout.SOUTH);
        userChatField = new JTextField();
        panelInput.add(userChatField, BorderLayout.CENTER);

        sendButton = new JButton("Envoyer");

        panelInput.add(sendButton, BorderLayout.EAST);

    }

}