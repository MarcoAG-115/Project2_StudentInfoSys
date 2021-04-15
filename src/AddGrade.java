import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.ZoneId;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddGrade implements ActionListener {

    private static JLabel semester;
    private static JLabel askForID;
    private static JTextField studentID;
    private static JLabel askForCourse;
    private static JTextField course;
    private static JLabel askForGrade;
    private static JTextField grade;
    private static JLabel askForCredits;
    private static JTextField credits;
    private static JButton addButton;

    Date date = new Date();
    LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    int year = localDate.getYear();
    int month = localDate.getMonthValue();
    String currentSeason = "";
    
    JFrame frame = new JFrame();
    JLabel label = new JLabel("Add Grade");
    JPanel panel = new JPanel();

    AddGrade(){

        label.setBounds(200, 0, 250, 50);
        //label.setFont(new Font(null, Font.PLAIN, 25));

        frame.add(label);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setSize(620, 620);
        //frame.setLayout(null);
        //frame.setVisible(true);

        panel.setLayout(null);


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

        askForGrade = new JLabel("Enter the course grade: ");
        askForGrade.setBounds(200, 220, 250, 25);
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

        addButton = new JButton("Add");
        addButton.setBounds(200, 350, 110, 25);
        addButton.addActionListener(this);
        panel.add(addButton);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        String id = studentID.getText();
        String cn = course.getText();
        String gd = grade.getText();
        String cd = credits.getText();
        String path = "/Users/marco/Documents/Documents/COMP_3700/Project/Project2_StudentInfoSys/src/" + id + "/";

        if (e.getSource() == addButton){

            try
            {
            File f = new File(path + id + cn + currentSeason + year + ".txt");
            //File f = new File(id + cn + currentSeason + year + ".txt");
            PrintWriter pw = new PrintWriter(new FileOutputStream(f, true));
            pw.append(gd + cd);
            pw.close();

            }
            catch(Exception ex){}

        }
        
    }
}