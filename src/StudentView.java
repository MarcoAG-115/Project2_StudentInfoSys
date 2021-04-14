import javax.swing.*;
import java.awt.event.*;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentView{
    
   JFrame frame = new JFrame("Main Frame");
   JFrame subframe = new JFrame("Sub Frame");
   JLabel label = new JLabel("Welcome to your Student View.");
   JMenu studentInfo = new JMenu("StudentInfo");
   JMenu transcript = new JMenu("Transcript");
   JMenuBar menubar = new JMenuBar();
   JMenuItem f = new JMenuItem("Open");
   
   

   StudentView(){
   
      
   
      label.setBounds(100, 0, 200, 50);
      label.setFont(new Font(null, Font.PLAIN, 12));
   
      frame.add(label);
   
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(420, 420);
      frame.setLayout(null);
      frame.setVisible(true);
      
      subframe.setSize(300, 300);
      subframe.setLayout(null);
      
      menubar.add(studentInfo);
      menubar.add(transcript);
      studentInfo.add(f);
      
      frame.setJMenuBar(menubar);
      
      f.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                subframe.setVisible(true);
            }
      });
       
      //transcript.addActionListener(this);
   
   }
   
}
