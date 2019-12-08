/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import controller.server.Controller;
import threads.ReadingThread;
import threads.WrittingThread;
import ui.view.MainFormChat;

/**
 *
 * @author student1
 */
public class Server {

    private ServerSocket serverSocket;
    private Socket socket;
    private WrittingThread writtingThread;
    private ReadingThread readingThread;

    public Server() {
    }

    public static void main(String[] args) {
        Server server = new Server();
        MainFormChat mainForm = new MainFormChat("Server");
        Controller.getInstance(server, mainForm).startServer();
    }

    public void startServer() {
        try {
            Controller.getInstance().show();
            Controller.getInstance().setStatus("Waiting for Clients");
            serverSocket = new ServerSocket(9000);
            socket = serverSocket.accept();
            Controller.getInstance().setStatus("Connected!");
            readingThread = new ReadingThread(socket, Controller.getInstance());
            readingThread.start();
            writtingThread = new WrittingThread(socket, Controller.getInstance());
            writtingThread.start();
            Controller.getInstance().setListeners();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ReadingThread getReadingThread() {
        return readingThread;
    }

    public WrittingThread getWrittingThread() {
        return writtingThread;
    }

}
