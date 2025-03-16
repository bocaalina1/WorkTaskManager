package org.example.Graphical_User_Interface;

import org.example.Business_Logic.TaskManagement;
import org.example.Data_Model.Employee;
import org.example.Data_Model.Task;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class InfoView extends JFrame {
    public InfoView(String title, String content) {

        setTitle(title);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea(content);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        JScrollPane scrollPanel = new JScrollPane(textArea);
        scrollPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e-> dispose());

        add(scrollPanel, BorderLayout.CENTER);
        add(closeButton, BorderLayout.SOUTH);

    }

}
