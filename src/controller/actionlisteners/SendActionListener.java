/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.actionlisteners;

import controller.AbstractChatController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Nikola
 */
public class SendActionListener implements ActionListener {

    private final AbstractChatController controller;

    public SendActionListener(AbstractChatController controller) {
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        controller.sendMessage();
    }

}
