package org.example.Graphical_User_Interface;

import org.example.Business_Logic.TaskManagement;
import org.example.Data_Model.Employee;
import org.example.Data_Model.Task;

import javax.lang.model.type.ArrayType;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class EmployeeView  extends JFrame {
    private JTextArea displayEmployee;
    private JButton closeButton;

    TaskManagement taskManagement;
    public EmployeeView(TaskManagement taskManagement) {
        this.taskManagement = taskManagement;
        setTitle("Employee Work Summary");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());


        displayEmployee = new JTextArea();
        displayEmployee.setEditable(false);
        displayEmployee.setLineWrap(true);
        JScrollPane displayEmployeeScrollPane = new JScrollPane(displayEmployee);

        closeButton = new JButton("Close");
        closeButton.addActionListener(e-> dispose());

        add(displayEmployeeScrollPane, BorderLayout.CENTER);
        add(closeButton, BorderLayout.SOUTH);
        populateTextArea();
    }
    private void populateTextArea() {
        StringBuilder text = new StringBuilder();

        for(Employee e: taskManagement.getEmployeeList())
        {
            int workDuration = taskManagement.calculateEmployeeWorkDuration(e.getIdEmployee());
            List<Task> task =taskManagement.getTaskListForEmployee(e);

            text.append("* Employee ID: " + e.getIdEmployee() + " | Name: ").append(e.getName()).append("\n")
                    .append(" Work Duration: " + workDuration + " hours \n").append("Tasks: \n");

            if(task.isEmpty())
                text.append("No work for the employee\n");
            else {
                for(Task t: task)
                    text.append(t.toString()).append("\n");
            }
            text.append("\n");

        }
        displayEmployee.setText(text.toString());
    }

}
