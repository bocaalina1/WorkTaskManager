package org.example.Data_Model;

public abstract class Task {
    private int idTask;
    private String statusTask;

    public int getIdTask() {
        return idTask;
    }

    public String getStatusTask() {
        return statusTask;
    }

    public abstract int estimateDuration();
}
