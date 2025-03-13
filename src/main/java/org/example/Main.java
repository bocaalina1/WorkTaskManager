package org.example;

import org.example.Business_Logic.TaskManagement;
import org.example.Data_Model.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        TaskManagement taskManagement = new TaskManagement();

        taskManagement.addEmployee(101,"Alina");
        taskManagement.addEmployee(102,"Maria");

        Task task1 = new SimpleTask(1,20,2);
        Task task2 = new SimpleTask(2,8,14);

        taskManagement.assignWorkToEmployee(101,task1);
        taskManagement.assignWorkToEmployee(102,task2);
        taskManagement.assignWorkToEmployee(101,task2);
        taskManagement.assignWorkToEmployee(101,task1);

        taskManagement.modifyTaskStatus(101,1);
        System.out.println(taskManagement.toString());
    }
}