import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginController implements ActionListener {

    private static JLabel userLabel;
    private static JTextField userText;
    private static JLabel passwordLabel;
    private static JPasswordField passwordText;
    private static JButton button;
    private static JLabel success;

    JPanel panel = new JPanel();
    JFrame frame = new JFrame();
    //public static void main(String[] args) {
        LoginController(){

        //JPanel panel = new JPanel();
        //JFrame frame = new JFrame();
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);

        userLabel = new JLabel("User");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);
        panel.add(userText);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);
        
        passwordText = new JPasswordField();
        passwordText.setBounds(100, 50, 165, 25);
        panel.add(passwordText);

        button = new JButton("Login");
        button.setBounds(10, 80, 80, 25);
        //button.addActionListener(new LoginGUI());
        button.addActionListener(this);
        panel.add(button);
        
        success = new JLabel("");
        success.setBounds(10, 110, 350, 25);
        panel.add(success);

        frame.setVisible(true);

        //JPanel panel = new JPanel();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       
       String user = userText.getText();
       String password = passwordText.getText();
       String copy1 = "";;
       String copy2 = "";

       try
       {
          BufferedReader sl = new BufferedReader(new FileReader("StudentLogins.txt"));
          String line1;
          while ((line1 = sl.readLine()) != null){
      
              if ((line1.contains(user)) && (line1.contains(password))){
                  copy1 = line1;
              }
          }
          sl.close();

          BufferedReader al = new BufferedReader(new FileReader("AdminLogins.txt"));
          String line2;
          while ((line2 = al.readLine()) != null){
      
              if ((line2.contains(user)) && (line2.contains(password))){
                  copy2 = line2;
              }
          }
          al.close();

       }
       catch(Exception ex){}

       String aUser = "";
       String aPass = "";
       String sUser = "";
       String sPass = "";

       if (copy1.equals("")){

          aUser = copy2.substring(0, 5);
          aPass = copy2.substring(6, copy2.length());
       }
       else{

          sUser = copy1.substring(0, 5);
          sPass = copy1.substring(6, copy1.length());
       }

       if(user.equals(sUser) && password.equals(sPass) && (e.getSource() == button)){
            success.setText("Login successful. Accessing Student View.");
            frame.dispose();
            StudentViewController myStudentView = new StudentViewController(user);
       }
       else if(user.equals(aUser) && password.equals(aPass) && (e.getSource() == button)){
            success.setText("Login successful. Accessing Administrator View.");
            frame.dispose();
            AdminViewController myAdminView = new AdminViewController();
       }
       else{
            success.setText("Login Failed. Incorrect Username and Password.");
       }
        
    }




}
