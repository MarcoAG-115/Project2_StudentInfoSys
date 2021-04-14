import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ChangeGrade implements ActionListener {

    private static JLabel askForID;
    private static JTextField studentID;
    private static JLabel askForCourse;
    private static JTextField course;
    private static JLabel askForGrade;
    private static JTextField grade;
    private static JButton changeGradeButton;

    Date date = new Date();
    LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    int year = localDate.getYear();
    int month = localDate.getMonthValue();
    String currentSeason = "";

    JFrame frame = new JFrame();
    JLabel label = new JLabel("Change Grade");
    JPanel panel = new JPanel();

    ChangeGrade(){

        label.setBounds(100, 0, 250, 50);
        //label.setFont(new Font(null, Font.PLAIN, 25));

        frame.add(label);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setSize(620, 620);
        //frame.setLayout(null);
        //frame.setVisible(true);

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

        changeGradeButton = new JButton("Change Grade");
        changeGradeButton.setBounds(200, 350, 110, 25);
        changeGradeButton.addActionListener(this);
        panel.add(changeGradeButton);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        String id = studentID.getText();
        String cn = course.getText();
        String gd = grade.getText();
        String path = "/Users/marco/Documents/Documents/COMP_3700/Project/Project2_StudentInfoSys/src/" + id + "/";

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
                String replaceWith = gd + credits;
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
