package org.example.Graphical_User_Interface;

import javax.swing.*;
import java.awt.*;
public class View extends JFrame {
    private JButton b_addEmployee = new JButton("add Employee");
    private JButton b_addTask = new JButton("ADD Task");
    private JButton b_assignedTask = new JButton("Assigned Task");

    private JTextArea textArea = new JTextArea();

    private Model model;
    JFrame mainFrame = new JFrame("Task Manager");
    View(Model model)
    {
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 600);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }
}
