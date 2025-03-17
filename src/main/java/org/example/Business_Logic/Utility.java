package org.example.Business_Logic;

import org.example.Business_Logic.TaskManagement;
import org.example.Data_Model.Employee;
import org.example.Data_Model.Task;

import java.util.*;

public class Utility {

    public static List<String> employeeWhoWorkMoreThan40H(TaskManagement taskManagement) {
       List<Employee> employeeWhoWorkMoreThan40H = getEmployeeWhoWorkMoreThan40H(taskManagement);
       Collections.sort(employeeWhoWorkMoreThan40H, Comparator.comparing(employee1 -> taskManagement.calculateEmployeeWorkDuration(employee1.getIdEmployee())) );

        return getNamesOfEmployeeWhoWorkMoreThan40H(employeeWhoWorkMoreThan40H);
    }
    public static List<Employee> getEmployeeWhoWorkMoreThan40H(TaskManagement taskManagement) {
        List<Employee> employeeWhoWorkMoreThan40H = new ArrayList<>();

        for(Employee employee: taskManagement.getMapTaskEmployee().keySet()) {
            int duration = taskManagement.calculateEmployeeWorkDuration(employee.getIdEmployee());

            if (duration > 40) {
                employeeWhoWorkMoreThan40H.add(employee);
            }
        }
        return employeeWhoWorkMoreThan40H;
    }
    public static List<String> getNamesOfEmployeeWhoWorkMoreThan40H(List<Employee> employeeWhoWorkMoreThan40H) {
        List<String> employeeWhoWorkMoreThan40HString = new ArrayList<>();
        for (Employee employee2: employeeWhoWorkMoreThan40H)
        {
            employeeWhoWorkMoreThan40HString.add("Name "+ employee2.getName() + " ID:" + employee2.getIdEmployee());
        }
        return employeeWhoWorkMoreThan40HString;
    }

    public static Map<String, Map<String,Integer>> nbOfCompletedAndUncompledTaskPerEmployee(TaskManagement taskManagement) {
        Map<String, Map<String,Integer>> nbOfCompletedAndUncompledTaskPerEmployee = new HashMap<>();

        for(Employee employee: taskManagement.getMapTaskEmployee().keySet()) {
            List<Task> tasks = taskManagement.getMapTaskEmployee().get(employee);

            int completedTasks = 0;
            int uncompletedTasks = 0;

            for(Task task: tasks) {
                if(task.getStatusTask().equals("Completed")) {
                    completedTasks++;
                }
                else uncompletedTasks++;
            }
            Map<String,Integer> nbOfTask = new HashMap<>();
            nbOfTask.put("Completed", completedTasks);
            nbOfTask.put("Uncompleted", uncompletedTasks);

            nbOfCompletedAndUncompledTaskPerEmployee.put(employee.getName(), nbOfTask);
        }
        return nbOfCompletedAndUncompledTaskPerEmployee;
    }
}
