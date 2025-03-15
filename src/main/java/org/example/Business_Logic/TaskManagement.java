package org.example.Business_Logic;


import org.example.Data_Model.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class TaskManagement implements Serializable {
    private static final long serialVersionUID = 1L;
    private Map<Employee, List<Task>> mapTaskEmployee;
    private ArrayList<Employee> employeeList;
    private ArrayList<Task> taskList;

    public TaskManagement() {

        mapTaskEmployee = new HashMap<Employee, List<Task>>();
        employeeList = new ArrayList<>();
        taskList = new ArrayList<>();
    }

    public int searchEmployee(int idEmployee, String name) {
        for (Employee e: employeeList)
        {
            if(e.getIdEmployee() == idEmployee)
                return -1;
        }
        return 0;
    }
    public void addEmployee(int idEmployee, String name)
    {
        int doesItExist = searchEmployee(idEmployee, name);
        if(doesItExist == -1)
        {
            System.out.println("The id already exist" + idEmployee);
        }
        else {
            employeeList.add(new Employee(idEmployee, name));
            mapTaskEmployee.putIfAbsent(new Employee(idEmployee, name), new ArrayList<Task>());
        }
    }

    public void addTask(Task task)
    {
        taskList.add(task);
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
    public Employee findEmployeeByTask(int idTask) {
        for(Map.Entry<Employee, List<Task>> entry: mapTaskEmployee.entrySet())
        {
            for(Task task: entry.getValue())
                if(task.getIdTask() == idTask)
                    return entry.getKey();
        }
        return null;
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
        Task aux  = searchTask(idEmployee,task.getIdTask());
        if(aux == null)
        {
            mapTaskEmployee.get(employee).add(task);
        }
        else System.out.println("Task " + task.getIdTask() + " already assigned to employee: " + idEmployee);


    }
    public Employee getEmployeeById(int idEmployee) {
        for (Employee employee : mapTaskEmployee.keySet()) {
            if (employee.getIdEmployee() == idEmployee) {
                return employee;
            }
        }
        return null;
    }

    public List<Task> getTaskListForEmployee(Employee employee) {
        return mapTaskEmployee.getOrDefault(employee, new ArrayList<>());
    }
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public ArrayList<Employee> getEmployeeList() {
        return employeeList;
    }

    public Map<Employee, List<Task>> getMapTaskEmployee() {
        return mapTaskEmployee;
    }

    public void setMapTaskEmployee(Map<Employee, List<Task>> mapTaskEmployee) {
        this.mapTaskEmployee = mapTaskEmployee;
    }
    public void setEmployeeList(ArrayList<Employee> employeeList) {
        this.employeeList = employeeList;
    }
    public void setTaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    @Override
    public String toString() {
        return "TaskManagement{" +
                "mapTaskEmployee=" + mapTaskEmployee +
                '}';
    }
}
