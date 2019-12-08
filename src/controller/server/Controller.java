/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.server;

import controller.AbstractChatController;
import placement.WindowPlacement;
import server.Server;
import ui.view.MainFormChat;

/**
 *
 * @author Madic
 */
public class Controller extends AbstractChatController {

    private static Controller instance;
    private final Server server;

    private Controller() {
        mainForm = new MainFormChat("Server");
        server = new Server();
    }

    private Controller(Server server, MainFormChat mainForm) {
        super(mainForm);
        this.server = server;
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public static Controller getInstance(Server server, MainFormChat mainForm) {
        if (instance == null) {
            instance = new Controller(server, mainForm);
        }
        return instance;
    }

    public void startServer() {
        server.startServer();
    }

    @Override
    public void show() {
        super.show();
        WindowPlacement.setLocationToLeft(mainForm);
    }

    @Override
    public void sendMessage() {
        if (!server.getWrittingThread().isInterrupted()) {
            server.getWrittingThread().interrupt();
        }
        archiveMyMessage(getMessageToSend());
    }

    @Override
    protected void archiveMyMessage(String messageToSend) {
        mainForm.getTxtHistory().append("You: " + messageToSend + "\n");
    }

    @Override
    public void showReceivedMessage(String receivedMessage) {
        mainForm.getTxtLastReply().setText(receivedMessage);
        mainForm.getTxtHistory().append("Client: " + receivedMessage + "\n");
    }

}
