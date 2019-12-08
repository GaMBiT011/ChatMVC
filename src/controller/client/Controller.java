/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.client;

import client.Client;
import controller.AbstractChatController;
import java.util.logging.Level;
import java.util.logging.Logger;
import placement.WindowPlacement;
import ui.view.MainFormChat;

/**
 *
 * @author Madic
 */
public class Controller extends AbstractChatController {

    private static Controller instance;
    private final Client client;

    private Controller() {
        client = new Client();
        mainForm = new MainFormChat("Client");
    }

    private Controller(Client client, MainFormChat mainForm) {
        super(mainForm);
        this.client = client;
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public static Controller getInstance(Client client, MainFormChat mainForm) {
        if (instance == null) {
            instance = new Controller(client, mainForm);
        }
        return instance;
    }

    public void start() throws Exception {
        client.connect();
    }

    public void connect() {
        try {
            show();
            client.connect();
            setStatus("Connected!");
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void sendMessage() {
        if (!client.getWrittingThread().isInterrupted()) {
            client.getWrittingThread().interrupt();
        }
        archiveMyMessage(getMessageToSend());
    }

    @Override
    protected void show() {
        super.show();
        WindowPlacement.setLocationToRight(mainForm);
    }

    @Override
    public void showReceivedMessage(String receivedMessage) {
        mainForm.getTxtLastReply().setText(receivedMessage);
        mainForm.getTxtHistory().append("Server: " + receivedMessage + "\n");
    }

}
