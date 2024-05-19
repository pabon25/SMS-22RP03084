/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package studentmanagementsystem;

/*
 * This program implements a Student Management System using Java Swing GUI.
 * It allows users to input student details such as name, registration number,
 * and marks in three subjects (Mathematics, Java, and PHP). It also displays
 * the entered data in a table format
*/

//Import of useful class to be used 

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import static java.awt.Color.green;
import static java.awt.Color.red;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Pattern;

// Student class using encapsulation
class Student {
    private String name;
    private String regNumber;
    private double mathMarks;
    private double javaMarks;
    private double phpMarks;

 // Constructor to initialize a Student object
    public Student(String name, String regNumber, double mathMarks, double javaMarks, double phpMarks) {
        this.name = name;
        this.regNumber = regNumber;
        this.mathMarks = mathMarks;
        this.javaMarks = javaMarks;
        this.phpMarks = phpMarks;
    }

  // Getter methods to access the private fields
    public String getName() {
        return name;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public double getMathMarks() {
        return mathMarks;
    }

    public double getJavaMarks() {
        return javaMarks;
    }

    public double getPhpMarks() {
        return phpMarks;
    }

    // Method to calculate total marks
    public double getTotalMarks() {
        return mathMarks + javaMarks + phpMarks;
    }

  // Method to calculate average marks
    public double getAverageMarks() {
        return getTotalMarks() / 3.0;
    }
}

// Main class implementing the student management system
      public class StudentManagementSystem extends JFrame {
    private ArrayList<Student> students = new ArrayList<>();
    private JTextField nameField, regNumberField, mathMarksField, javaMarksField, phpMarksField;
    private JTable table;
    private DefaultTableModel tableModel;

    // Constructor to set up the GUI components
    public StudentManagementSystem() {
        // Set up the frame
        setTitle("Student Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create components
        JPanel inputPanel = new JPanel(new GridLayout(8, 2, 10, 10));
        inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Name input block
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBorder(new EmptyBorder(5, 5, 5, 5));
        inputPanel.add(nameLabel);
        nameField = new JTextField();
        nameField.setBorder(new LineBorder(Color.BLACK, 1));
        inputPanel.add(nameField);

        // Registration number input block
        JLabel regNumberLabel = new JLabel("Reg Number:");
        regNumberLabel.setBorder(new EmptyBorder(5, 5, 5, 5));
        inputPanel.add(regNumberLabel);
        regNumberField = new JTextField();
        regNumberField.setBorder(new LineBorder(Color.BLACK, 1));
        inputPanel.add(regNumberField);

        // Mathematics marks input block
        JLabel mathMarksLabel = new JLabel("Mathematics Marks:");
        mathMarksLabel.setBorder(new EmptyBorder(5, 5, 5, 5));
        inputPanel.add(mathMarksLabel);
        mathMarksField = new JTextField();
        mathMarksField.setBorder(new LineBorder(Color.BLACK, 1));
        inputPanel.add(mathMarksField);

        // Java marks input block
        JLabel javaMarksLabel = new JLabel("Java Marks:");
        javaMarksLabel.setBorder(new EmptyBorder(5, 5, 5, 5));
        inputPanel.add(javaMarksLabel);
        javaMarksField = new JTextField();
        javaMarksField.setBorder(new LineBorder(Color.BLACK, 1));
        inputPanel.add(javaMarksField);

        // PHP marks input block
        JLabel phpMarksLabel = new JLabel("PHP Marks:");
        phpMarksLabel.setBorder(new EmptyBorder(5, 5, 5, 5));
        inputPanel.add(phpMarksLabel);
        phpMarksField = new JTextField();
        phpMarksField.setBorder(new LineBorder(Color.BLACK, 1));
        inputPanel.add(phpMarksField);

        // Add Student button
        JButton addButton = new JButton("Add Student");
        addButton.addActionListener(new AddStudentListener());
        addButton.setBackground(green);
        inputPanel.add(addButton);

        // Exit button
        JButton exit = new JButton("EXIT");
        exit.addActionListener(new ShowExit());
        exit.setBackground(red);
        inputPanel.add(exit);

        // Create table to display students
        String[] columnNames = {"Name", "Reg Number", "Average"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Add components to the frame
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    // Inner class to handle Add Student button click
    private class AddStudentListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // Get input values
                String name = nameField.getText();
                String regNumber = regNumberField.getText();
                double mathMarks = Double.parseDouble(mathMarksField.getText());
                double javaMarks = Double.parseDouble(javaMarksField.getText());
                double phpMarks = Double.parseDouble(phpMarksField.getText());
                

                // Validate input values
               
                
                if(name.isEmpty()){
                     throw new IllegalArgumentException("Name must not be empty String");
                }
                if (!Pattern.matches("[a-zA-Z\\s]+", name)) {
                    throw new IllegalArgumentException("Name must containing only letters and spaces.");
                }
                if(!(name.length() >=2 && name.length()<30)){
                    throw new IllegalArgumentException("The name must not be one character");
                }
                if(!(name.length()<30)){
                   throw new IllegalArgumentException("The name must not greater than 30 characters"); 
                }
                if (regNumber.isEmpty() || regNumber.length() > 10) {
                    throw new IllegalArgumentException("Registration Number cannot be empty and must not exceed 10 characters.");
                }
                if(regNumber.length()<9){
                    throw new IllegalArgumentException("The reg number must started by 22 or any two number and followed by rp and five numbers");
                }
                if (mathMarks <= 0 || mathMarks > 100 || javaMarks <= 0 || javaMarks > 100 || phpMarks <= 0 || phpMarks > 100) {
                    throw new IllegalArgumentException("Marks must be between 0 and 100.");
                }

                // Check for duplicate registration number
                String regNumberLower = regNumber.toLowerCase();
                for (Student student : students) {
                    if (student.getRegNumber().toLowerCase().equals(regNumberLower)) {
                        throw new IllegalArgumentException("Registration Number already exists.");
                    }
                }

                // Create new student and add to list
                Student student = new Student(name, regNumber, mathMarks, javaMarks, phpMarks);
                students.add(student);

                // Add student to table
                tableModel.addRow(new Object[]{name, regNumber, student.getAverageMarks()});

                // Highlight the top student
                highlightTopStudent();

                // Clear input fields
                nameField.setText("");
                regNumberField.setText("");
                mathMarksField.setText("");
                javaMarksField.setText("");
                phpMarksField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(StudentManagementSystem.this, "Invalid input. Please enter numeric values for marks.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(StudentManagementSystem.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Method to highlight the top student in the table
    private void highlightTopStudent() {
        int rowCount = tableModel.getRowCount();
        if (rowCount == 0) {
            return;
        }

        int topStudentRow = 0;
        double highestAverage = (double) tableModel.getValueAt(0, 2);

        // Find the student with the highest average marks
        for (int i = 1; i < rowCount; i++) {
            double average = (double) tableModel.getValueAt(i, 2);
            if (average > highestAverage) {
                highestAverage = average;
                topStudentRow = i;
            }
        }
        

        // Highlight the top student row
        table.clearSelection();
        table.setRowSelectionInterval(topStudentRow, topStudentRow);
        table.setSelectionBackground(Color.cyan);
        table.scrollRectToVisible(table.getCellRect(topStudentRow, 0, true));
    }

    // Inner class to handle Exit button click
    private class ShowExit implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    // Main method to run the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StudentManagementSystem frame = new StudentManagementSystem();
            frame.setVisible(true);
        });
    }
}

