import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
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

    private static JLabel info;
    public String userID;
    
    JPanel panel = new JPanel();
    JFrame frame = new JFrame();
    JTextPane tp = new JTextPane();

    // public static class FileNameComparator implements Comparator<File>{

    //     public int compare(File a, File b){
    
    //         String aName = a.getName();
    //         String bName = b.getName();
    //         int aSeason = 0;
    //         int bSeason = 0;
    //         int aYear = 0;
    //         int bYear = 0;
    
    //         if (aName.contains("Fall")){
    
    //             aSeason = 3;
    //             aYear = Integer.parseInt(aName.substring(aName.length()-8, aName.length()-4));
    //         }
    //         else if (aName.contains("Spring")){
    
    //             aSeason = 1;
    //             aYear = Integer.parseInt(aName.substring(aName.length()-8, aName.length()-4));
    //         }
    //         else if (aName.contains("Summer")){
    
    //             aSeason = 2;
    //             aYear = Integer.parseInt(aName.substring(aName.length()-8, aName.length()-4));
    //         }
    //         else {
    
    //             return -1;
    //         }
            
    //         if (bName.contains("Fall")){
    
    //             bSeason = 3;
    //             bYear = Integer.parseInt(bName.substring(bName.length()-8, bName.length()-4));
    //         }
    //         else if (bName.contains("Spring")){
    
    //             bSeason = 1;
    //             bYear = Integer.parseInt(bName.substring(bName.length()-8, bName.length()-4));
    //         }
    //         else if (bName.contains("Summer")){
    
    //             bSeason = 2;
    //             bYear = Integer.parseInt(bName.substring(bName.length()-8, bName.length()-4));
    //         }
    //         else{
    
    //             return 1;
    //         }
    
    //         if (aYear == bYear){
    
    //             return Integer.compare(aSeason, bSeason);
    //         }
    //         else {
    
    //             return Integer.compare(aYear, bYear);
    //         }
    
    
    //     }
        
    // }

    DisplayInfo(String user){

        userID = user;

        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setSize(950, 850);

        panel.setLayout(null);


        
        StringBuilder sb = new StringBuilder();
        String allInfo = "";
        try{
            String path = "/Users/marco/Documents/Documents/COMP_3700/Project/Project2_StudentInfoSys/src/" + userID + "AllInfo";
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
                    
                    if (((File) a).lastModified() < ((File) b).lastModified()){

                        return -1;
                    }
                    else if (((File) a).lastModified() > ((File) b).lastModified()){

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
    
}
