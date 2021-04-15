import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AdminView implements ActionListener{

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


    JFrame frame = new JFrame();
    JLabel label = new JLabel("Welcome to the Administrator Menu.");
    JPanel panel = new JPanel();

    AdminView(){

        label.setBounds(100, 0, 250, 50);
        //label.setFont(new Font(null, Font.PLAIN, 25));

        frame.add(label);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setSize(420, 420);
        //frame.setLayout(null);
        //frame.setVisible(true);

        panel.setLayout(null);

        //Adding student info to 'database' aka text file
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

    @Override
    public void actionPerformed(ActionEvent e) {
        String id = studentID.getText();
        String fn = studentFN.getText();
        String ln = studentLN.getText();
        String cd = totalCredits.getText();

        if (e.getSource() == addButton){

            String path = "/Users/marco/Documents/Documents/COMP_3700/Project/Project2_StudentInfoSys/src/" + id;
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

        if (e.getSource() == addGradeButton){

            //frame.dispose();
            AddGrade addGrade = new AddGrade(cd);

        }

        if (e.getSource() == changeGradeButton){

            //frame.dispose();
            ChangeGrade changeGrade = new ChangeGrade();

        }

        
    }
    
}
