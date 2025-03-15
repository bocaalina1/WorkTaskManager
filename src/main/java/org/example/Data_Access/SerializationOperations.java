package org.example.Data_Access;

import org.example.Business_Logic.TaskManagement;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.example.Data_Model.*;
import org.example.Business_Logic.*;

public class SerializationOperations {

    public static void saveEverything(Map<Employee, List<Task>> map,ArrayList<Task> tasks)
    {
        saveMap(map);
        saveTaskInMemory(tasks);
    }
    public static void saveMap(Map<Employee, List<Task>> map) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("Map.dat");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(map);
            objectOutputStream.flush();
            objectOutputStream.close();
        }catch (IOException e){
            System.out.println("Could not save map in memory");
        }
    }
    public static Map<Employee, List<Task>> getMapMemory()  {
        try{
            FileInputStream fileInputStream = new FileInputStream("Map.dat");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Map<Employee, List<Task>> map = (Map<Employee, List<Task>>) objectInputStream.readObject();
            objectInputStream.close();
            return map;
        }catch (IOException | ClassNotFoundException e){
            System.out.println("Could not restore Map in app");
            return null;
        }
    }

    public static void saveTaskInMemory(ArrayList<Task> tasks) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("Tasks.dat");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(tasks);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException e) {
            System.out.println("Could not save tasks in memory");
        }
    }
    public static ArrayList<Task> getTaskFromMemory() {
        try{
            FileInputStream fileInputStream = new FileInputStream("Tasks.dat");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            ArrayList<Task> array = (ArrayList<Task>) objectInputStream.readObject();
            objectInputStream.close();
            return array;
        }catch (IOException | ClassNotFoundException e){
            System.out.println("Could not restore tasks in app");
            return null;
        }
    }


}
