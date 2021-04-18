//Project 2 Group 11 Student Information System
//COMP 3700
//04-18-2021
//Description: Displays all info for a student in a window.
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public class DisplayInfo {

    //Initializes variable where parameter will be stored (student ID).
    public String userID;
    
    //Initializes and creates UI window for displaying all student info.
    JPanel panel = new JPanel();
    JFrame frame = new JFrame();
    JTextPane tp = new JTextPane();

    //Method / operation dedicated to displaying all student info
    //organized by semesters.
    public void showInfo(String userID){

        //Sets up dimensions for UI window.
        frame.add(panel);
        frame.setSize(950, 850);

        panel.setLayout(null);


        //Files are read and organized within the directory before reading contents.
        StringBuilder sb = new StringBuilder();
        String allInfo = "";
        try{
            //The path for the needed files and directories are found and assigned to path variables.
            Path p1 = Paths.get("AdminLogin.txt");
            Path p2 = Paths.get((p1.toAbsolutePath()).toString());
            //String newPath = p2.getParent() + "/src/" + userID + "/";
            String path = p2.getParent() + "/src/" + userID + "AllInfo";
            //String path = "/Users/marco/Documents/Documents/COMP_3700/Project/Project2_StudentInfoSys/src/" + userID + "AllInfo";
            File folder = new File(path);
            File[] listOfFiles = folder.listFiles();

            FileFilter fileFilter = new FileFilter()
            {
                @Override
                public boolean accept(File file){
                    return !file.isDirectory();
                }
            };
            listOfFiles = folder.listFiles(fileFilter);

            //Comparator is used to sort files according to their name (semester).
            Arrays.sort(listOfFiles, new Comparator()
            {
                @Override
                public int compare(Object a, Object b){
                    
                 

                    if (((File) a).length() < ((File) b).length()){

                        return -1;
                    }
                    else if (((File) a).length() > ((File) b).length()){

                        return 1;
                    }
                    else{

                        return 0;
                    }
                }
            });

            //The directory containing files that represent each semester is read
            //along with the files' contents.
            for (File file : listOfFiles){

                if (file.isFile()){

                    String fileName = file.getName();
                    BufferedReader br = new BufferedReader(new FileReader(path + "/" + fileName));
                    String line;
                    //String copy = "";
                    while ((line = br.readLine()) != null){
                    
                        if (line != null){
                            //copy = line;
                            sb.append(line + "\n");
                        }
                    }
                    allInfo = sb.toString();
                    br.close();
                }
            }
            tp.setText(allInfo);
        }
        catch(Exception ex){}

        //Text pane UI element dimensions and details are set up
        //and applied.
        Font font = new Font("", Font.BOLD, 20);
        tp.setFont(font);

        tp.setForeground(Color.WHITE);
        tp.setBackground(Color.BLACK);

        JScrollPane jp = new JScrollPane(tp);

        panel.setLayout(new BorderLayout());
        panel.add(jp, BorderLayout.CENTER);

        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }

    //Takes student ID as a parameter from previous classes.
    //Calls showInfo.
    DisplayInfo(String user){

        userID = user;

        showInfo(userID);
    }
    
}
