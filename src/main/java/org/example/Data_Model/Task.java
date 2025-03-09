package org.example.Data_Model;

public abstract class Task {
    private int idTask;
    private String statusTask;

    public abstract int estimateDuration();
}
