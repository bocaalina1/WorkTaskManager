package org.example.Graphical_User_Interface;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
public class View extends JFrame {
    private JButton bAddEmployee;
    private JButton bAddTask;
    private JButton bAssignedTask;
    //private JTextArea textArea = new JTextArea();
    private JButton bViewEmployee;
    private JButton bViewStatistics;
    private JTable employeeTable;
    private JTable taskTable;

    public View()
    {
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setTitle("TASK MANAGER");
      setSize(800, 500);
      setLocationRelativeTo(null);
      setLayout(new BorderLayout());
      setBackground(Color.CYAN);

      getContentPane().setBackground(Color.CYAN);

      employeeTable = new JTable( new DefaultTableModel(new Object[]{"Name Employee"},0 ));
      employeeTable.setBackground(Color.pink);
      JScrollPane employeeScrollPane = new JScrollPane(employeeTable);
        ///employeeScrollPane.setOpaque(true);

      taskTable = new JTable( new DefaultTableModel(new Object[]{"ID Task", "Status","Type"},0 ));
      //taskTable.setGridColor(Color.pink);
      JScrollPane taskScrollPane = new JScrollPane(taskTable);

      bAssignedTask = new JButton("Assigned Task");
      bAddTask = new JButton("Add Task");
      bAddEmployee = new JButton("Add Employee");
      bViewEmployee = new JButton("View Employee");
      bViewStatistics = new JButton("View Statistics");

      JPanel tablePanel = new JPanel(new GridLayout(1,2));
      tablePanel.setBackground(Color.CYAN);
      tablePanel.add(employeeScrollPane);
      tablePanel.add(taskScrollPane);

      JPanel buttonPanel = new JPanel(new GridLayout(2,2));
      buttonPanel.add(bAddEmployee);
      buttonPanel.add(bAddTask);
      buttonPanel.add(bAssignedTask);
      buttonPanel.add(bViewEmployee);
      buttonPanel.add(bViewStatistics);


      add(tablePanel, BorderLayout.CENTER);
      add(buttonPanel, BorderLayout.SOUTH);

    }
    public JTable getEmployeeTable() {
        return employeeTable;
    }
    public JTable getTaskTable() {
        return taskTable;
    }
    public JButton bAssignedTask() {
        return bAssignedTask;
    }
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
    public JButton getAddEmployeeButton() {
        return bAddEmployee;
    }
    public JButton getAddTaskButton() {
        return bAddTask;
    }
    public JButton getViewEmployeeButton() {
        return bViewEmployee;
    }
    public JButton getViewStatisticsButton() {
        return bViewStatistics;
    }

    public String[] showAddEmployeeDialog()
    {
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        Object[] fields = {"Employee id: ", idField,"Name: ", nameField};

        int option = JOptionPane.showConfirmDialog(this, fields, "Add Employee", JOptionPane.OK_CANCEL_OPTION);

        if(option == JOptionPane.OK_OPTION)
        {
            return new String[]{idField.getText(),nameField.getText()};
        }
        return null;
    }
    public String[] showAddTaskDialog()
    {
        JTextField idField = new JTextField();
        JTextField startHour= new JTextField();
        JTextField endHour = new JTextField();
        //JTextField statusField = new JTextField();

        String[] taskTypes ={"Simple Task","Complex Task"};
        JComboBox<String> taskTypeComboBox = new JComboBox<>(taskTypes);

        Object[] fields = {"Task id: ", idField,"Task type", taskTypeComboBox, "Start Hour", startHour, "End Hour", endHour };

        int option = JOptionPane.showConfirmDialog(this, fields, "Add new task", JOptionPane.OK_CANCEL_OPTION);

        if(option == JOptionPane.OK_OPTION)
        {
            String id = idField.getText();
            String type = taskTypeComboBox.getSelectedItem().toString();
            if(type.equals("Simple Task"))
            {
                return new String[]{id,type,startHour.getText(),endHour.getText()};
            }
            return new String[]{id, type,"0","0"};
        }
        return null;
    }
}
