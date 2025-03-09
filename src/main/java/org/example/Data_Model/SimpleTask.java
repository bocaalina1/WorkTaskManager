package org.example.Data_Model;

public class SimpleTask extends Task {
    private int startHour;
    private int endHour;

    public SimpleTask(int startHour, int endHour) {
        this.startHour = startHour;
        this.endHour = endHour;
    }

    @Override
    public int estimateDuration() {
        return endHour - startHour;
    }
}
