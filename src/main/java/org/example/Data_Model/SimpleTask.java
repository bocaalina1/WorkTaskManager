package org.example.Data_Model;

import java.io.Serializable;

public class SimpleTask extends Task implements Serializable{
    private static final long serialVersionUID = 1L;
    private int startHour;
    private int endHour;

    public SimpleTask(int idTask,int startHour, int endHour) {
        super(idTask, "Uncompleted");
        this.startHour = startHour;
        this.endHour = endHour;
    }

    @Override
    public int estimateDuration() {
        if (endHour < startHour) {
            return  24 + endHour - startHour;
        }
        return endHour - startHour;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
                " startHour=" + startHour +
                ", endHour=" + endHour +
                '\n';
    }

    @Override
    public String getTypeTask() {
        return "Simple";
    }
}
