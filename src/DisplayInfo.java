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

    //private static JLabel info;
    public String userID;
    
    JPanel panel = new JPanel();
    JFrame frame = new JFrame();
    JTextPane tp = new JTextPane();

    public void showInfo(String userID){

        frame.add(panel);
        frame.setSize(950, 850);

        panel.setLayout(null);


        
        StringBuilder sb = new StringBuilder();
        String allInfo = "";
        try{
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

            Arrays.sort(listOfFiles, new Comparator()
            {
                @Override
                public int compare(Object a, Object b){
                    
                    // if (((File) a).lastModified() < ((File) b).lastModified()){

                    //     return -1;
                    // }
                    // else if (((File) a).lastModified() > ((File) b).lastModified()){

                    //     return 1;
                    // }
                    // else{

                    //     return 0;
                    // }

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

    DisplayInfo(String user){

        userID = user;

        showInfo(userID);
    }
    
}
