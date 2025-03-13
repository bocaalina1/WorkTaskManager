package org.example.Business_Logic;


import org.example.Data_Model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class TaskManagement {
    private Map<Employee, List<Task>> mapTaskEmployee;

    public TaskManagement() {
        mapTaskEmployee = new HashMap<Employee, List<Task>>();
    }

    public void addEmployee(int idEmployee, String name)
    {
        mapTaskEmployee.putIfAbsent(new Employee(idEmployee, name), new ArrayList<Task>());
    }

    public Task searchTask(int idEmployee, int idTask)
    {
        Employee employee = getEmployeeById(idEmployee);
        if(employee == null) return null;

        List<Task> tasks = mapTaskEmployee.get(employee);
        if(tasks == null) return null;

        for (Task task : tasks) {
            if (task.getIdTask() == idTask) {
                return task;
            }
        }
        return null;

    }
    public void swappStatus(Task task) {
        if (task.getStatusTask().equals("Completed")) {
            task.setStatusTask("Uncompleted");
        }
        else  task.setStatusTask("Completed");
    }
    public void modifyTaskStatus(int idEmployee, int taskID) {

        Task task = searchTask(idEmployee,taskID);
        if (task != null) {
            swappStatus(task);
        }
        else System.out.println("Task " + taskID + " not found for employee: " + idEmployee);

    }
    public int calculateEmployeeWorkDuration(int idEmployee) {
        Employee employee = getEmployeeById(idEmployee);
        if (employee == null) return -1;



        List<Task> tasks = mapTaskEmployee.get(employee);
        if (tasks == null) return -1;

        int duration = 0;
        for (Task task : tasks) {
            if (task.getStatusTask().equals("Completed")) {
                duration+= task.estimateDuration();
            }

        }
        return duration;
    }
    public void assignWorkToEmployee(int idEmployee,Task task) {
        Employee employee = getEmployeeById(idEmployee);
        if (employee == null)
        {
            System.out.println("Employee not found for employee: " + idEmployee);
            return;
        }
        mapTaskEmployee.get(employee).add(task);
    }
    public Employee getEmployeeById(int idEmployee) {
        for (Employee employee : mapTaskEmployee.keySet()) {
            if (employee.getIdEmployee() == idEmployee) {
                return employee;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "TaskManagement{" +
                "mapTaskEmployee=" + mapTaskEmployee +
                '}';
    }
}
