package org.example.Data_Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ComplexTask  extends Task implements Serializable {
    private static final long serialVersionUID = 1L;
    ArrayList<Task> tasks;

    public ComplexTask(int idTask) {
        super(idTask, "Uncompleted");
        tasks = new ArrayList<>();

    }
    @Override
    public int estimateDuration() {
        int duration = 0;
        for (Task task : tasks) {
            duration += task.estimateDuration();
        }
        return duration;
    }
    public void addTask(Task task) {
        Task aux = searchTasks(task.getIdTask());
        if (aux == null) {
            tasks.add(task);
        }
        else System.out.println("Task Already exist");
    }
    public void deleteTask(Task task) {
        Task aux = searchTasks(task.getIdTask());
        if (aux != null) {
            tasks.remove(aux);
        }
        else {
            System.out.println("Task Not Found");
        }

    }
    public Task searchTasks(int idTask) {
        for (Task task : tasks) {
            if (task.getIdTask() == idTask) {
                return task;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return  super.toString()+ "\n ComplexTask : \n" +
                 tasks;
    }

    @Override
    public String getTypeTask() {
        return "Complex";
    }
}
