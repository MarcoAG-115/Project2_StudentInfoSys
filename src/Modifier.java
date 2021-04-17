import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Modifier implements ActionListener {

    private static JLabel askForID;
    private static JTextField studentID;
    private static JLabel askForCourse;
    private static JTextField course;
    private static JLabel askForGrade;
    private static JTextField grade;
    private static JLabel askForStatus;
    private static JTextField status;
    private static JButton changeGradeButton;

    Date date = new Date();
    LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    int year = localDate.getYear();
    int month = localDate.getMonthValue();
    String currentSeason = "";

    JFrame frame = new JFrame();
    JLabel label = new JLabel("Change Grade");
    JPanel panel = new JPanel();

    public void changeGrade(){

        label.setBounds(100, 0, 250, 50);
        

        frame.add(label);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setSize(620, 620);
        

        panel.setLayout(null);

        if ((month <= 12) && (month >= 8)){

            currentSeason = "Fall";
    
        }
        else if ((month <= 8) && (month >= 5)){
    
            currentSeason = "Summer";
        }
        else {

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

        askForStatus = new JLabel("Enter the course status: ");
        askForStatus.setBounds(200, 280, 250, 25);
        panel.add(askForStatus);
       
        status = new JTextField(20);
        status.setBounds(200, 300, 165, 25);
        panel.add(status);

        changeGradeButton = new JButton("Change Grade");
        changeGradeButton.setBounds(200, 350, 200, 25);
        changeGradeButton.addActionListener(this);
        panel.add(changeGradeButton);

        frame.setVisible(true);
    }

    Modifier(){

        changeGrade();


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        String id = studentID.getText();
        String cn = course.getText();
        String gd = grade.getText();
        String s = status.getText();

        Path p1 = Paths.get("AdminLogin.txt");
        Path p2 = Paths.get((p1.toAbsolutePath()).toString());
        String path = p2.getParent() + "/src/" + id + "/";
        //String path = "/Users/marco/Documents/Documents/COMP_3700/Project/Project2_StudentInfoSys/src/" + id + "/";

        if (e.getSource() == changeGradeButton){

            try
            {
                BufferedReader br = new BufferedReader(new FileReader(path + id + cn + currentSeason + year + ".txt"));
                StringBuffer inputBuffer = new StringBuffer();
                String line;
                String copy = "";

                while ((line = br.readLine()) != null){
                    
                    if (line != null){
                        copy = line;
                    }
                    inputBuffer.append(line);
                    inputBuffer.append('\n');
                }
                br.close();
                String credits = Character.toString(copy.charAt(1));
                String totalCredits = "";
                totalCredits = copy.substring(3, copy.length());
                String replaceWith = gd + credits + s + totalCredits;
                String inputStr = inputBuffer.toString();
                inputStr = inputStr.replace(copy, replaceWith);

                FileOutputStream fileOut = new FileOutputStream(path + id + cn + currentSeason + year + ".txt");
                fileOut.write(inputStr.getBytes());
                fileOut.close();
            }
            catch(Exception ex){}

        }
        
    }
    
}
