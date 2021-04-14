import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ChangeGrade implements ActionListener {

    private static JLabel semester;
    private static JLabel askForID;
    private static JTextField studentID;
    private static JLabel askForCourse;
    private static JTextField course;
    private static JLabel askForGrade;
    private static JTextField grade;
    private static JButton changeGradeButton;

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
        // TODO Auto-generated method stub
        
    }
    
}
