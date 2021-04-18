//Project 2 Group 11 Student Information System
//COMP 3700
//04-18-2021
//Description: Interface where a student can choose to compile info and / or to display info.
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

   //Initializes variables for UI elements.
   private static JButton infoButton;
   private static JButton tButton;

   //Initializes variable that will store parameter (student ID).
   public String userID;

   //Initializes and creates a UI window.
   JFrame frame = new JFrame("Main Frame");
   JFrame subframe = new JFrame("Sub Frame");
   JLabel label = new JLabel("Welcome to your Student View.");
   JMenu studentInfo = new JMenu("StudentInfo");
   JMenu transcript = new JMenu("Transcript");
   JMenuBar menubar = new JMenuBar();
   JMenuItem f = new JMenuItem("Open");
   JPanel panel = new JPanel();
   
   
   //Represents the initial student view and allows a user to run the 
   //display info and gather info operations.
   StudentViewController(String user){

      //Assigns parameter to userID so it can be used outside of StudentViewController.
      userID = user;
      
      //Sets up UI window dimensions.
      label.setBounds(100, 0, 200, 50);
      label.setFont(new Font(null, Font.PLAIN, 12));
   
      frame.add(label);
   
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.add(panel);
      frame.setSize(420, 420);
      

      panel.setLayout(null);

      //Creates UI elements that prompt user and accept user inputs.
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


   //This method checks for the user's button press. It calls the class that
   //corresponds with the prompt and button that was selected.
   @Override
   public void actionPerformed(ActionEvent e) {
      
      //Calls the class that contains the gather info operation.
      if (e.getSource() == infoButton){

         

         AllStudentInfo allStudentInfo = new AllStudentInfo(userID);
         
         //JOptionPane.showMessageDialog(null, userID, "stuff " + "more ", JOptionPane.INFORMATION_MESSAGE);

     }

     //Calls the class that contains the display info operation.
     if (e.getSource() == tButton){

         DisplayInfo displayInfo = new DisplayInfo(userID);
     }
      
   }
   
}
