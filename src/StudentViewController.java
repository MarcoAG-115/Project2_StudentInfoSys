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

public class StudentViewController implements ActionListener{

   private static JButton infoButton;
   private static JButton tButton;
   public String userID;
    
   JFrame frame = new JFrame("Main Frame");
   JFrame subframe = new JFrame("Sub Frame");
   JLabel label = new JLabel("Welcome to your Student View.");
   JMenu studentInfo = new JMenu("StudentInfo");
   JMenu transcript = new JMenu("Transcript");
   JMenuBar menubar = new JMenuBar();
   JMenuItem f = new JMenuItem("Open");
   JPanel panel = new JPanel();
   
   

   StudentViewController(String user){

      userID = user;
   
      label.setBounds(100, 0, 200, 50);
      label.setFont(new Font(null, Font.PLAIN, 12));
   
      frame.add(label);
   
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.add(panel);
      frame.setSize(420, 420);
      

      panel.setLayout(null);

      infoButton = new JButton("Gather Info");
      infoButton.setBounds(100, 50, 200, 25);
      infoButton.addActionListener(this);
      panel.add(infoButton);

      tButton = new JButton("Get All Info");
      tButton.setBounds(100, 110, 200, 25);
      tButton.addActionListener(this);
      panel.add(tButton);

      
      studentInfo.add(f);
      
      

      frame.setVisible(true);
      
      
   
   }



   @Override
   public void actionPerformed(ActionEvent e) {
      
      if (e.getSource() == infoButton){

         

         AllStudentInfo allStudentInfo = new AllStudentInfo(userID);
         
         //JOptionPane.showMessageDialog(null, userID, "stuff " + "more ", JOptionPane.INFORMATION_MESSAGE);

     }

     if (e.getSource() == tButton){

         DisplayInfo displayInfo = new DisplayInfo(userID);
     }
      
   }
   
}
