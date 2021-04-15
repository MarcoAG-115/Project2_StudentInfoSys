import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginGUI implements ActionListener {

    private static JLabel userLabel;
    private static JTextField userText;
    private static JLabel passwordLabel;
    private static JPasswordField passwordText;
    private static JButton button;
    private static JLabel success;

    JPanel panel = new JPanel();
    JFrame frame = new JFrame();
    //public static void main(String[] args) {
    LoginGUI(){

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
       //System.out.println(user + ", " + password);

       if(user.equals("to745") && password.equals("4") && (e.getSource() == button)){
            success.setText("Login successful. Accessing Student View.");
            frame.dispose();
            StudentView myStudentView = new StudentView(user);
       }
       else if(user.equals("1") && password.equals("2") && (e.getSource() == button)){
            success.setText("Login successful. Accessing Administrator View.");
            frame.dispose();
            AdminView myAdminView = new AdminView();
       }
       else{
            success.setText("Login Failed. Incorrect Username and Password.");
       }

       //if(e.getSource() == button){
       //   
       //   frame.dispose();
       //   StudentView myView = new StudentView();
       //   
       //}
        
    }




}
