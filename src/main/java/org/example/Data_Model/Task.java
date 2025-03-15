package org.example.Data_Model;

import java.io.Serializable;

public abstract class Task implements Serializable {
    private static final long serialVersionUID = 1L;
    private int idTask;
    private String statusTask;
    private boolean isAssigned;

    public Task(int idTask, String statusTask) {
        this.idTask = idTask;
        this.statusTask = statusTask;
        this.isAssigned = false;
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

    public boolean isAssigned() {
        return isAssigned;
    }
    public String isAssignedString() {
        return isAssigned ? "Assigned" : "Unassigned";
    }

    public void setAssigned(boolean assigned) {
        isAssigned = assigned;
    }

    @Override
    public String toString() {
        return "Task: " +
                "idTask=" + idTask +
                ", statusTask='" + statusTask + '\'' +
                ' ';
    }
    public abstract String getTypeTask();
    public abstract int estimateDuration();
}
