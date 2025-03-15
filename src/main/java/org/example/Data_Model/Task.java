package org.example.Data_Model;

public abstract class Task {
    private int idTask;
    private String statusTask;

    public Task(int idTask, String statusTask) {
        this.idTask = idTask;
        this.statusTask = statusTask;
    }

    public int getIdTask() {
        return idTask;
    }

    public String getStatusTask() {
        return statusTask;
    }
    public void setIdTask(int idTask) {
        this.idTask = idTask;
    }
    public void setStatusTask(String statusTask) {
        this.statusTask = statusTask;
    }


    @Override
    public String toString() {
        return "Task{" +
                "idTask=" + idTask +
                ", statusTask='" + statusTask + '\'' +
                '}';
    }
    public abstract String getTypeTask();
    public abstract int estimateDuration();
}
