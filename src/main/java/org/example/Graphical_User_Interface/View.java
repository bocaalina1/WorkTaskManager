package org.example.Graphical_User_Interface;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
public class View extends JFrame {
    private JButton bAddEmployee;
    private JButton bAddSimpleTask;
    private JButton bAddComplexTask;
    private JButton bAssignedTask;
    //private JButton bSaveData;
    private JButton bViewEmployee;
    private JButton bViewStatistics;
    private JTable employeeTable;
    private JTable taskTable;

    public View()
    {
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setTitle("TASK MANAGER");
      setSize(1000, 600);
      setLocationRelativeTo(null);
      setLayout(new BorderLayout());
      setBackground(Color.CYAN);

      getContentPane().setBackground(Color.CYAN);

      employeeTable = new JTable( new DefaultTableModel(new Object[]{"ID ","Name Employee"},0 ));
      JScrollPane employeeScrollPane = new JScrollPane(employeeTable);

      taskTable = new JTable( new DefaultTableModel(new Object[]{"ID Task", "Status","Type","Assigned","Specifications"},0 ));
      setSizesTask(taskTable);
      JScrollPane taskScrollPane = new JScrollPane(taskTable);
      bAssignedTask = new JButton("Assigned Task");
      bAddSimpleTask = new JButton("Add Simple Task");
      bAddComplexTask = new JButton("Add Complex Task");
      bAddEmployee = new JButton("Add Employee");
      bViewEmployee = new JButton("View Employee");
      bViewStatistics = new JButton("View Statistics");
      //bSaveData = new JButton("Save Changes");

      JPanel tablePanel = new JPanel(new BorderLayout());
      employeeScrollPane.setPreferredSize(new Dimension(300, 0));
      tablePanel.setBackground(Color.CYAN);
      tablePanel.add(employeeScrollPane,BorderLayout.WEST);
      tablePanel.add(taskScrollPane, BorderLayout.CENTER);

      JPanel buttonPanel = new JPanel(new GridLayout(2,2));
      buttonPanel.add(bAddEmployee);
      buttonPanel.add(bAddSimpleTask);
      buttonPanel.add(bAddComplexTask);
      buttonPanel.add(bAssignedTask);
      buttonPanel.add(bViewEmployee);
      buttonPanel.add(bViewStatistics);

      add(tablePanel, BorderLayout.CENTER);
      add(buttonPanel, BorderLayout.SOUTH);

    }

    private void setSizesTask(JTable table)
    {
        table.getColumnModel().getColumn(0).setPreferredWidth(20);
        table.getColumnModel().getColumn(1).setPreferredWidth(40);
        table.getColumnModel().getColumn(2).setPreferredWidth(30);
        table.getColumnModel().getColumn(3).setPreferredWidth(20);
        table.getColumnModel().getColumn(4).setPreferredWidth(80);
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
    public JButton getAddSimpleTaskButton() {
        return bAddSimpleTask;
    }
    public JButton getAddComplexTaskButton() {
        return bAddComplexTask;
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
    public String[] showAddSimpleTaskDialog()
    {
        JTextField idField = new JTextField();
        JTextField startHour= new JTextField();
        JTextField endHour = new JTextField();
        //JTextField statusField = new JTextField();

        Object[] fields = {"Task id: ", idField, "Start Hour", startHour, "End Hour", endHour };

        int option = JOptionPane.showConfirmDialog(this, fields, "Add new task", JOptionPane.OK_CANCEL_OPTION);

        if(option == JOptionPane.OK_OPTION)
        {
            String id = idField.getText();

            return new String[]{id, startHour.getText(),endHour.getText()};

        }
        return null;
    }

}
