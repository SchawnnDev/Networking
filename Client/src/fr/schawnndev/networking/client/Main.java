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

import fr.schawnndev.networking.client.network.NetworkClient;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

    private static Main instance;
    private JTextArea chatPane;
    private JTextField userChatField;
    private JList users;
    private JButton sendButton;
    private NetworkClient networkClient;

    public Main() {

        // View

        initView();

        // Network

        networkClient = new NetworkClient("127.0.0.1", 7777); // Localhost

        // Graphics

        setTitle("SchawnnDev Client");
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
                instance.networkClient.connect();
                instance.setVisible(true);
            }
        });

    }

    public static Main getInstance() {
        return instance;
    }

    void initView() {
        // Set default look

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

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
        chatPane = new JTextArea();
        chatPane.setEditable(false);
        ((DefaultCaret) chatPane.getCaret()).setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        JScrollPane chatScrollPane = new JScrollPane(chatPane);
        chatScrollPane.setBorder(BorderFactory.createTitledBorder("Chat"));
        chatPanel.add(chatScrollPane, BorderLayout.CENTER);

        // Chat field

        final JPanel panelInput = new JPanel(new BorderLayout());
        jPanel.add(panelInput, BorderLayout.SOUTH);
        userChatField = new JTextField();
        panelInput.add(userChatField, BorderLayout.CENTER);

        sendButton = new JButton("Envoyer");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String text = userChatField.getText();

                if (text != null && text.length() > 0) {
                    System.out.println("button text: " + text);
                   // Messages.sendMessage(text); // Send
                    userChatField.setText(""); // Clear
                }

            }
        });

        panelInput.add(sendButton, BorderLayout.EAST);

    }

}