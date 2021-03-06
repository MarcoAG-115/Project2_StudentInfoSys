//Project 2 Group 11 Student Information System
//COMP 3700
//04-18-2021
//Description: Interface where admin user can add a 
//             new course with grade, credits, and 
//             status for a student.
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneId;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NewCourse implements ActionListener {

    //Initializes variables that will be used for UI elements.
    private static JLabel semester;
    private static JLabel askForID;
    private static JTextField studentID;
    private static JLabel askForCourse;
    private static JTextField course;
    private static JLabel askForGrade;
    private static JTextField grade;
    private static JLabel askForCredits;
    private static JTextField credits;
    private static JLabel askForStatus;
    private static JTextField status;
    private static JButton addButton;

    //Initializes variable that will store the parameter (total credits needed).
    public String totalCredits;

    //Initializes and assigns variables to the current month and year values.
    Date date = new Date();
    LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    int year = localDate.getYear();
    int month = localDate.getMonthValue();
    String currentSeason = "";
    
    //Initializes and creates a UI window.
    JFrame frame = new JFrame();
    JLabel label = new JLabel("Add Grade");
    JPanel panel = new JPanel();

    //Method / operation dedicated to allowing an administrator / instructor to 
    //add new course and corresponding info for a student, but only for the current
    //semester.
    public void addGrade(String totalCredits){

        //UI window dimensions are established.
        label.setBounds(200, 0, 250, 50);
        

        frame.add(label);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setSize(620, 620);
        

        panel.setLayout(null);

        //The current season / semester is found and assigned to currentSeason.
        if ((month <= 12) && (month >= 8)){

            semester = new JLabel("Fall " + year);
            semester.setBounds(200, 20, 250, 50);
            panel.add(semester);
            currentSeason = "Fall";
    
        }
        else if ((month <= 8) && (month >= 5)){
    
            semester = new JLabel("Summer " + year);
            semester.setBounds(200, 20, 250, 50);
            panel.add(semester);
            currentSeason = "Summer";
        }
        else {

            semester = new JLabel("Spring " + year);
            semester.setBounds(200, 20, 250, 50);
            panel.add(semester);
            currentSeason = "Spring";
        }

        //UI elements that prompt the user and accept inputs are created.
        askForID = new JLabel("Enter student ID: ");
        askForID.setBounds(200, 100, 165, 25);
        panel.add(askForID);

        studentID = new JTextField(20);
        studentID.setBounds(200, 120, 165, 25);
        panel.add(studentID);

        askForCourse = new JLabel("Enter the course name: ");
        askForCourse.setBounds(200, 160, 250, 25);
        panel.add(askForCourse);
       
        course = new JTextField(20);
        course.setBounds(200, 180, 165, 25);
        panel.add(course);

        askForGrade = new JLabel("Enter the course grade(A/B/C/D/F): ");
        askForGrade.setBounds(200, 220, 300, 25);
        panel.add(askForGrade);
       
        grade = new JTextField(20);
        grade.setBounds(200, 240, 165, 25);
        panel.add(grade);

        askForCredits = new JLabel("Enter the credits assigned to this course: ");
        askForCredits.setBounds(200, 280, 300, 25);
        panel.add(askForCredits);
       
        credits = new JTextField(20);
        credits.setBounds(200, 300, 165, 25);
        panel.add(credits);

        askForStatus = new JLabel("Optional - Enter course status(W/P/N/I): ");
        askForStatus.setBounds(200, 340, 300, 25);
        panel.add(askForStatus);
       
        status = new JTextField(20);
        status.setBounds(200, 360, 165, 25);
        panel.add(status);

        addButton = new JButton("Add");
        addButton.setBounds(200, 410, 110, 25);
        addButton.addActionListener(this);
        panel.add(addButton);

        frame.setVisible(true);
    }

    //Receives the total credits needed to graduate as parameter from previous
    //classes. Assigns it to totalCredits so it can be used by addGrade.
    //Calls addGrade.
    NewCourse(String cd){

        totalCredits = cd;

        addGrade(totalCredits);


    }

    //This method helps complete the addGrade operation by creating a new file
    //representing the new course and containing its' corresponding info.
    //This is done by checking for the user's button press.
    @Override
    public void actionPerformed(ActionEvent e) {
        
        //Initializes and assigns input values.
        String id = studentID.getText();
        String cn = course.getText();
        String gd = grade.getText();
        String cd = credits.getText();
        String s = status.getText();

        //Finds paths needed to create a student's files in the correct location.
        //These paths are assigned to path variables.
        Path p1 = Paths.get("AdminLogin.txt");
        Path p2 = Paths.get((p1.toAbsolutePath()).toString());
        String path = p2.getParent() + "/src/" + id + "/";
        //String path = "/Users/marco/Documents/Documents/COMP_3700/Project/Project2_StudentInfoSys/src/" + id + "/";

        //A file is created for the new course and is populated by the entered info.
        if (e.getSource() == addButton){

            try
            {
            File f = new File(path + id + cn + currentSeason + year + ".txt");
            //File f = new File(id + cn + currentSeason + year + ".txt");
            PrintWriter pw = new PrintWriter(new FileOutputStream(f, true));
            pw.append(gd + cd + s + totalCredits);
            pw.close();

            }
            catch(Exception ex){}

        }
        
    }
}
