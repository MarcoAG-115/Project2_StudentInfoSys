//Project 2 Group 11 Student Information System
//COMP 3700
//04-18-2021
//Description: Interface where admin user can add a new student with student id, 
//             first name, and last name. Admin user can choose to navigate to 
//             add grade interface or change course interface.
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AdminViewController implements ActionListener{

    //Private variables for UI elements initialized.
    private static JLabel askForID;
    private static JTextField studentID;
    private static JLabel askFirstName;
    private static JTextField studentFN;
    private static JLabel askLastName;
    private static JTextField studentLN;
    private static JLabel askCreditsNeeded;
    private static JTextField totalCredits;
    private static JButton addButton;
    private static JButton addGradeButton;
    private static JButton changeGradeButton;

    //Creates and initializes UI window elements.
    JFrame frame = new JFrame();
    JLabel label = new JLabel("Welcome to the Administrator Menu.");
    JPanel panel = new JPanel();

    //Method / operation dedicated to adding a new student to the system.
    public void addStudent(){

        //Sets up addStudent UI window dimensions.
        label.setBounds(100, 0, 250, 50);

        frame.add(label);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setSize(420, 420);

        panel.setLayout(null);

        //Sets up UI elements that prompt user and receive student info.
        askForID = new JLabel("Enter student ID: ");
        askForID.setBounds(100, 40, 165, 25);
        panel.add(askForID);

        studentID = new JTextField(20);
        studentID.setBounds(100, 60, 165, 25);
        panel.add(studentID);

        askFirstName = new JLabel("Enter the first name of the student: ");
        askFirstName.setBounds(100, 100, 250, 25);
        panel.add(askFirstName);
       
        studentFN = new JTextField(20);
        studentFN.setBounds(100, 120, 165, 25);
        panel.add(studentFN);

        askLastName = new JLabel("Enter the last name of the student: ");
        askLastName.setBounds(100, 160, 250, 25);
        panel.add(askLastName);
        
        studentLN = new JTextField(20);
        studentLN.setBounds(100, 180, 165, 25);
        panel.add(studentLN);

        askCreditsNeeded = new JLabel("Enter total credits needed to graduate: ");
        askCreditsNeeded.setBounds(100, 210, 250, 25);
        panel.add(askCreditsNeeded);
        
        totalCredits = new JTextField(20);
        totalCredits.setBounds(100, 230, 165, 25);
        panel.add(totalCredits);

        addButton = new JButton("Add Student");
        addButton.setBounds(100, 280, 110, 25);
        addButton.addActionListener(this);
        panel.add(addButton);

        addGradeButton = new JButton("Add Grade");
        addGradeButton.setBounds(50, 310, 110, 25);
        addGradeButton.addActionListener(this);
        panel.add(addGradeButton);

        changeGradeButton = new JButton("Change Grade");
        changeGradeButton.setBounds(150, 310, 150, 25);
        changeGradeButton.addActionListener(this);
        panel.add(changeGradeButton);

        frame.setVisible(true);
    }

    //Calls addStudent method / operation.
    //This class represents the initial administrator / instructor view.
    //It allows the user to run the add student, add grade, and change
    //grade operations.
    AdminViewController(){

        addStudent();


    }

    //Method that assists addStudent by reading a user's button click as an input.
    //Completes the actual addStudent process.
    @Override
    public void actionPerformed(ActionEvent e) {

        //Initializes and sets up varaibles for the user given info.
        String id = studentID.getText();
        String fn = studentFN.getText();
        String ln = studentLN.getText();
        String cd = totalCredits.getText();

        //If a user clicks on the "Add Student" button, then any info entered will
        //be used to add a student in Database.txt.
        if (e.getSource() == addButton){

            Path p1 = Paths.get("AdminLogins.txt");
            String temp = (p1.toAbsolutePath()).toString();
            Path p2 = Paths.get(temp);
            String path = p2.getParent() + "/src/" + id;
            File file = new File(path);
            if (!file.exists()){
            try
            {
            file.mkdir();
            File f = new File("Database.txt");
            PrintWriter pw = new PrintWriter(new FileOutputStream(f, true));
            pw.append("\n " + id + ", " + fn + ", " + ln + ", " + cd);
            pw.close();

            }
            catch(Exception ex){}
            }
        }

        //If user clicks on "Add Grade" button, then NewCourse is called which
        //opens a new window.
        if (e.getSource() == addGradeButton){

            
            NewCourse addGrade = new NewCourse(cd);

        }

        //If user clicks on "Change Grade" button, then Modifier is called which
        //opens a new window.
        if (e.getSource() == changeGradeButton){

            
            Modifier changeGrade = new Modifier();

        }

        
    }
    
}
