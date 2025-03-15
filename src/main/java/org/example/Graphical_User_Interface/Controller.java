package org.example.Graphical_User_Interface;

import org.example.Business_Logic.TaskManagement;
import org.example.Data_Model.ComplexTask;
import org.example.Data_Model.Employee;
import org.example.Data_Model.SimpleTask;
import org.example.Data_Model.ComplexTask;
import org.example.Data_Model.Task;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Controller {
    private TaskManagement taskManagement;
    private View view;

    public Controller(TaskManagement taskManagement, View view) {
        this.taskManagement = taskManagement;
        this.view = view;

        populateTable();

        view.bAssignedTask().addActionListener(e-> assignTaskToEmployee());
        view.getAddEmployeeButton().addActionListener(e->addEmployeeDialog());
        view.getAddTaskButton().addActionListener(e-> addTaskDialog());
        view.getViewStatisticsButton().addActionListener(e->viewStatistics());

    }
    private void populateTable() {
        DefaultTableModel employeeModel = (DefaultTableModel) view.getEmployeeTable().getModel();
        for(Employee emp : taskManagement.getEmployeeList())
        {
            employeeModel.addRow(new Object[]{emp.getName()});
        }

        DefaultTableModel taskModel = (DefaultTableModel) view.getTaskTable().getModel();
        for(Task task: taskManagement.getTaskList())
        {
            taskModel.addRow(new Object[]{task.getIdTask(), task.getStatusTask(), task.getTypeTask()});
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
        Employee employee = taskManagement.getEmployeeList().get(employeeRow);
        Task task = taskManagement.getTaskList().get(taskRow);

        taskManagement.assignWorkToEmployee(employee.getIdEmployee(), task);
        view.showMessage("TASK ASSIGNED TO EMPLOYEE " +employee.getName());
    }
//    private void modifyStatusByClick()
//    {
//        int taskRow = view.getTaskTable().getSelectedRow();
//        Task task = taskManagement.getTaskList().get(taskRow);
//        taskManagement.modifyTaskStatus(task.getIdTask());
//    }
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
            employeeModel.addRow(new Object[]{name});
        }catch(NumberFormatException e)
        {
            view.showMessage("Invalid input");
        }
    }
    public void addTaskDialog(){
        String[] input = view.showAddTaskDialog();
        if(input == null)
            return;
        try{
            int id = Integer.parseInt(input[0]);
            String type = input[1].trim();

            Task task;
            if("Simple Task".equals(type))
            {
                int startHour = Integer.parseInt(input[2]);
                int endHour = Integer.parseInt(input[3]);
                task = new SimpleTask(id, startHour, endHour);
            }
            else{
                task = new ComplexTask(id);
            }


            taskManagement.addTask(task);
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
        for(Task task : taskManagement.getTaskList())
        {
            taskModel.addRow(new Object[]{task.getIdTask(),
                    task.getStatusTask(),
                    (task instanceof SimpleTask)? "Simple":"Complex"});
        }
    }
}
