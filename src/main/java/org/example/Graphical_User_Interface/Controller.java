package org.example.Graphical_User_Interface;

import org.example.Business_Logic.TaskManagement;
import org.example.Data_Access.SerializationOperations;
import org.example.Data_Model.ComplexTask;
import org.example.Data_Model.Employee;
import org.example.Data_Model.SimpleTask;
import org.example.Data_Model.Task;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private TaskManagement taskManagement;
    private View view;

    public Controller(TaskManagement taskManagement, View view) {
        this.taskManagement = taskManagement;
        this.view = view;

        populateTable();

        view.bAssignedTask().addActionListener(e-> assignTaskToEmployee());
        view.getAddEmployeeButton().addActionListener(e->addEmployeeDialog());
        view.getAddSimpleTaskButton().addActionListener(e-> addSimpleTaskDialog());
        view.getViewStatisticsButton().addActionListener(e->viewStatistics());
        view.getViewEmployeeButton().addActionListener(e-> new EmployeeView(taskManagement).setVisible(true));
        view.getSaveDataButton().addActionListener(e-> SerializationOperations.saveEverything(taskManagement.getMapTaskEmployee(),taskManagement.getTaskList()));
        view.getAddComplexTaskButton().addActionListener(e->showAddComplexTaskDialog());
        view.getTaskTable().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                modifyStatusByClick();
            }
        });

    }
    private void populateTable() {
        DefaultTableModel employeeModel = (DefaultTableModel) view.getEmployeeTable().getModel();
        employeeModel.setRowCount(0);
        for(Employee e : taskManagement.getMapTaskEmployee().keySet())
        {
            employeeModel.addRow(new Object[]{e.getIdEmployee(), e.getName()});
        }

        DefaultTableModel taskModel = (DefaultTableModel) view.getTaskTable().getModel();
        taskModel.setRowCount(0);
        for(Task task: taskManagement.getTaskList())
        {
            taskModel.addRow(new Object[]{
                            task.getIdTask(),
                            task.getStatusTask(),
                            task.getTypeTask(),
                            task.isAssignedString(),
                            (task instanceof ComplexTask) ? ((ComplexTask) task).subtaskString() : "-"
                    });
        }
    }
    private void assignTaskToEmployee() {
        int employeeRow = view.getEmployeeTable().getSelectedRow();
        int taskRow = view.getTaskTable().getSelectedRow();

        if(employeeRow == -1 || taskRow == -1)
        {
            JOptionPane.showMessageDialog(null, "Please select an employee and a task");
            return;
        }
        int id = Integer.parseInt(view.getEmployeeTable().getValueAt(employeeRow, 0).toString());
        Employee employee= taskManagement.getEmployeeById(id);
        if(employee == null)
        {
            view.showMessage("NO EMPLOYEE FOUND");
        }
        Task task = taskManagement.getTaskList().get(taskRow);

        taskManagement.assignWorkToEmployee(id, task);
        SerializationOperations.saveMap(taskManagement.getMapTaskEmployee());
        view.showMessage("TASK ASSIGNED TO EMPLOYEE " +employee.getName());
    }
    private void modifyStatusByClick()
    {
        int taskRow = view.getTaskTable().getSelectedRow();

        Task task = taskManagement.getTaskList().get(taskRow);
        int idTask = task.getIdTask();

        Employee employee = taskManagement.findEmployeeByTask(idTask);

        if(employee == null)
        {
            view.showMessage("The task is not assigned");
            return;
        }
        Task employeeTask = taskManagement.searchTask(employee.getIdEmployee(), idTask);
        if(employeeTask == null)
        {
            view.showMessage("Task found in map, but not in this employee list");
            return;
        }
        taskManagement.swappStatus(employeeTask);
        task.setStatusTask(employeeTask.getStatusTask());

        SerializationOperations.saveTaskInMemory(taskManagement.getTaskList());
        SerializationOperations.saveMap(taskManagement.getMapTaskEmployee());

        updateTaskTable();
   }
    private void addEmployeeDialog(){
        String[] input = view.showAddEmployeeDialog();
        if(input == null)
            return;

        try{
            int id = Integer.parseInt(input[0]);
            String name = input[1].trim();
            if(name.isEmpty())
            {
                view.showMessage("Please enter a name");
                return;
            }

            taskManagement.addEmployee(id, name);
            view.showMessage("EMPLOYEE ADDED");

            DefaultTableModel employeeModel = (DefaultTableModel) view.getEmployeeTable().getModel();
            employeeModel.addRow(new Object[]{id, name});

            SerializationOperations.saveMap(taskManagement.getMapTaskEmployee());

        }catch(NumberFormatException e)
        {
            view.showMessage("Invalid input");
        }
    }
    public void addSimpleTaskDialog(){
        String[] input = view.showAddSimpleTaskDialog();
        if(input == null)
            return;
        try{
            int id = Integer.parseInt(input[0]);
            int startHour = Integer.parseInt(input[1]);
            int endHour = Integer.parseInt(input[2]);
            Task task;
            task = new SimpleTask(id, startHour, endHour);

            taskManagement.addTask(task);
            SerializationOperations.saveTaskInMemory(taskManagement.getTaskList());

            view.showMessage("Task ADDED");

            updateTaskTable();

        }catch(NumberFormatException e)
        {
            view.showMessage("Invalid input");
        }
    }
    public void viewStatistics() {

    }
    public void updateTaskTable() {
        DefaultTableModel taskModel = (DefaultTableModel) view.getTaskTable().getModel();
        taskModel.setRowCount(0);
        for(Task task : taskManagement.getTaskList()) {
            taskModel.addRow(new Object[]{
                    task.getIdTask(),
                    task.getStatusTask(),
                    (task instanceof SimpleTask) ? "Simple" : "Complex",
                    task.isAssignedString(),
                    (task instanceof ComplexTask) ? ((ComplexTask) task).subtaskString() : "-"
            });
        }
        SerializationOperations.saveTaskInMemory(taskManagement.getTaskList());
    }
    private void showAddComplexTaskDialog()
    {
        AddComplexTaskDialog dialog = new AddComplexTaskDialog(view,taskManagement,this);
        dialog.setVisible(true);
    }
}
