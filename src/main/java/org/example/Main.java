package org.example;

import org.example.Business_Logic.TaskManagement;
import org.example.Data_Access.SerializationOperations;
import org.example.Data_Model.*;
import org.example.Graphical_User_Interface.Controller;
import org.example.Graphical_User_Interface.View;
import org.example.Data_Model.ComplexTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        TaskManagement taskManagement = new TaskManagement();

        Map<Employee, List<Task>> restoreMap = SerializationOperations.getMapMemory();
        ArrayList<Employee> restoreEmployees = SerializationOperations.getEmployeeFromMemory();
        ArrayList<Task> restoreTasks = SerializationOperations.getTaskFromMemory();

        if(restoreMap != null) {
            taskManagement.setMapTaskEmployee(restoreMap);
        }
        if(restoreEmployees != null) {
            taskManagement.setEmployeeList(restoreEmployees);
        }
        if(restoreTasks != null) {
            taskManagement.setTaskList(restoreTasks);
        }

        if(taskManagement.getEmployeeList().isEmpty()) {
            taskManagement.addEmployee(101,"Alina");
            taskManagement.addEmployee(102,"Maria");

            Task task1 = new SimpleTask(1,20,2);
            Task task2 = new SimpleTask(2,8,14);
            ComplexTask task3 = new ComplexTask(40);
            task3.addTask(task1);
            task3.addTask(task2);
            taskManagement.getTaskList().add(task1);
            taskManagement.getTaskList().add(task2);
            taskManagement.getTaskList().add(task3);

            taskManagement.assignWorkToEmployee(101,task1);
            taskManagement.assignWorkToEmployee(102,task2);
            taskManagement.assignWorkToEmployee(101,task2);
            taskManagement.assignWorkToEmployee(101,task1);

        }

        //taskManagement.modifyTaskStatus(101,1);
        System.out.println(taskManagement.toString());
        View taskView = new View();
        new Controller(taskManagement,taskView);

        taskView.setVisible(true);
    }
}