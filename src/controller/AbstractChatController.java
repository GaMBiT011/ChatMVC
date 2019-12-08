/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.actionlisteners.SendActionListener;
import ui.view.MainFormChat;

/**
 *
 * @author Nikola
 */
public abstract class AbstractChatController {

    protected MainFormChat mainForm;

    protected AbstractChatController() {

    }

    protected AbstractChatController(MainFormChat mainForm) {
        this.mainForm = mainForm;
    }

    public String getMessageToSend() {
        return mainForm.getTxtMessage().getText();
    }

    public void clearSentMessage() {
        mainForm.getTxtMessage().setText("");
    }

    public void setStatus(String status) {
        mainForm.getLblStatus().setText(status);
    }

    protected void show() {
        mainForm.setVisible(true);
    }

    protected void archiveMyMessage(String messageToSend) {
        mainForm.getTxtHistory().append("You: " + messageToSend + "\n");
    }

    public void setListeners() {

        mainForm.getTxtMessage().addActionListener(new SendActionListener(this));
        mainForm.getBtnSend().addActionListener(new SendActionListener(this));

    }

    public abstract void showReceivedMessage(String receivedMessage);

    public abstract void sendMessage();
}
