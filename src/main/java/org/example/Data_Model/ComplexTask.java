package org.example.Data_Model;

import java.util.ArrayList;
import java.util.List;

public class ComplexTask  extends Task {
    List<Task> tasks;
    public ComplexTask() {
        tasks = new ArrayList<Task>();
    }

    @Override
    public int estimateDuration() {
        return 0;
    }
}
