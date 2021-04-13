import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class StudentView {
    
    JFrame frame = new JFrame();
    JLabel label = new JLabel("Welcome to your Student View.");

    StudentView(){

        label.setBounds(100, 0, 200, 50);
        //label.setFont(new Font(null, Font.PLAIN, 25));

        frame.add(label);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 420);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
