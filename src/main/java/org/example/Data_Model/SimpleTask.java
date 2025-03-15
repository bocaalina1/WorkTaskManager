package org.example.Data_Model;

public class SimpleTask extends Task {
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
            return  24 - (endHour - startHour);
        }
        return endHour - startHour;
    }

    @Override
    public String toString() {
        return "SimpleTask{" +
                "startHour=" + startHour +
                ", endHour=" + endHour +
                '}';
    }

    @Override
    public String getTypeTask() {
        return "Simple";
    }
}
