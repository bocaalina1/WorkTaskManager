package org.example.Graphical_User_Interface;

import org.example.Business_Logic.TaskManagement;
import org.example.Data_Model.Task;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AddComplexTaskDialog extends JDialog {
    private JTextField idFiled;
    private JPanel taskPanel;
    private JButton cancelButton;
    private JButton okButton;
    private List<Task> unassignedTask;
    private TaskManagement taskManagement;
    private Controller controller;

    public AddComplexTaskDialog(JFrame parent, TaskManagement taskManagement, Controller controller) {
        super(parent, "Add Complex Task", true);
        this.taskManagement = taskManagement;
        this.controller = controller;
        unassignedTask = new ArrayList<>();
        initComponent();
        layoutComponent();
        addListeners();
        populateUnassignedTask();
        pack();
        setLocationRelativeTo(parent);
    }
    private void initComponent() {
        idFiled = new JTextField(10);
        taskPanel = new JPanel();
        cancelButton = new JButton("Cancel");
        okButton = new JButton("OK");
    }
    private void layoutComponent() {
        setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.add(new JLabel("Complex Task ID:"));
        panel.add(idFiled);

        taskPanel.setLayout(new BoxLayout(taskPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(taskPanel);
        scrollPane.setPreferredSize(new Dimension(400, 300));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(cancelButton);
        buttonPanel.add(okButton);

        add(panel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    private void addListeners() {
        cancelButton.addActionListener(e -> {
            dispose();
        });
        okButton.addActionListener(e -> {
            addComplexTask();
            dispose();
        });
    }
    public void populateUnassignedTask() {
        List<Task> availableTasks = getAvailableTasks();
        for(Task task : availableTasks) {
            JCheckBox checkBoxButton = new JCheckBox("Task id "+task.getIdTask());
            checkBoxButton.setActionCommand(String.valueOf(task.getIdTask()));

            taskPanel.add(checkBoxButton);
        }
    }
    private List<Task> getAvailableTasks() {
        List<Task> availableTasks = new ArrayList<>();
        for (Task task : taskManagement.getTaskList()) {
            if (!task.isAssigned()) {
                availableTasks.add(task);
            }
        }
        return availableTasks;
    }
    private void addComplexTask() {
        try{
            int complexTaskId = Integer.parseInt(idFiled.getText());
            List<Task> availableTasks = getAvailableTasks();
            taskManagement.addComplexTask(complexTaskId, availableTasks);
            controller.updateTaskTable();
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Please enter a valid task ID", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private List<Task> getUnassignedTask() {
        List<Task> unassignedTasks = new ArrayList<>();
        for (Component component : taskPanel.getComponents()) {
            if (component instanceof JRadioButton) {
                JCheckBox checkBoxButton = (JCheckBox) component;
                if (checkBoxButton.isSelected()) {
                    int taskId = Integer.parseInt(checkBoxButton.getActionCommand());
                    Task task = taskManagement.getTaskById(taskId);
                    if (task!=null) {
                        unassignedTasks.add(task);
                    }
                }
            }

        }
        return unassignedTasks;
    }
}
