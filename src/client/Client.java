/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import controller.client.Controller;
import java.net.Socket;
import threads.ReadingThread;
import threads.WrittingThread;
import ui.view.MainFormChat;

/**
 *
 * @author student1
 */
public class Client extends Thread {

    private Socket socket;
    private WrittingThread writtingThread;
    private ReadingThread readingThread;

    public static void main(String[] args) {
        Client client = new Client();
        MainFormChat mainForm = new MainFormChat("Client");
        Controller.getInstance(client, mainForm).connect();
    }

    public void connect() throws Exception {
        socket = new Socket("localhost", 9000);
        readingThread = new ReadingThread(socket, Controller.getInstance());
        readingThread.start();
        writtingThread = new WrittingThread(socket, Controller.getInstance());
        writtingThread.start();
        Controller.getInstance().setListeners();
    }

    public ReadingThread getReadingThread() {
        return readingThread;
    }

    public WrittingThread getWrittingThread() {
        return writtingThread;
    }

}
