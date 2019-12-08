/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import controller.AbstractChatController;

/**
 *
 * @author student1
 */
public class WrittingThread extends Thread {

    private final Socket socket;
    private boolean messageSent;
    private final AbstractChatController controller;

    public WrittingThread(Socket socket, AbstractChatController controller) {
        super();
        this.socket = socket;
        this.controller = controller;

    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(Long.MAX_VALUE);
            } catch (InterruptedException ex) {
            }
            try {
                PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
                printWriter.println(controller.getMessageToSend());
                controller.clearSentMessage();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public boolean isMessageSent() {
        return messageSent;
    }

    public void setMessageSent(boolean isMessageSent) {
        this.messageSent = isMessageSent;
    }

}
