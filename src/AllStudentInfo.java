import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;

public class AllStudentInfo {


    Date date = new Date();
    LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    int year = localDate.getYear();
    int month = localDate.getMonthValue();
    String currentSeason = "";

    AllStudentInfo(String userID){

        if ((month <= 12) && (month >= 8)){

            currentSeason = "Fall";
    
        }
        else if ((month <= 8) && (month >= 5)){
    
            currentSeason = "Summer";
        }
        else {

            currentSeason = "Spring";
        }

        File folder = new File("/Users/marco/Documents/Documents/COMP_3700/Project/Project2_StudentInfoSys/src/" + userID + "/");
        File[] listOfFiles = folder.listFiles();
        String path = "/Users/marco/Documents/Documents/COMP_3700/Project/Project2_StudentInfoSys/src/" + userID + "/";
        String newPath = "/Users/marco/Documents/Documents/COMP_3700/Project/Project2_StudentInfoSys/src/" + userID + "AllInfo";

        File oldFolder = new File(newPath);
        File[] oldFiles = oldFolder.listFiles();
        if (oldFiles != null){

            for (File f : oldFiles){

                if (f.isFile()){

                    f.delete();
                }
            }
        }
        oldFolder.delete();

        File newFolder = new File(newPath);
        newFolder.mkdir();
        ArrayList<String> gradesArray = new ArrayList<String>();
        ArrayList<String> creditsArray = new ArrayList<String>();
        String totalCredits = "";
        int count = 0; 

        for (File file : listOfFiles){

            if (file.isFile()){

                String fileName = file.getName();
                int end = fileName.length();
                int start = end - 12;
                int startCN = 5;
                int endCNforS = end - 14;
                int startSeason = end - 8;
                int endSeason = end - 4;
                //String season = fileName.substring(start, (end - 8));

                if (fileName.contains("Fall")){
                    
                    String semesterYear = fileName.substring(startSeason, endSeason);
                    String courseName = fileName.substring(startCN, start);

                    try
                    {
                        BufferedReader br = new BufferedReader(new FileReader(path + userID + courseName + "Fall" + semesterYear + ".txt"));
                        String line;
                        String copy = "";
                        while ((line = br.readLine()) != null){
                    
                            if (line != null){
                                copy = line;
                            }
                        }
                        br.close();
                        String credit = Character.toString(copy.charAt(1));
                        String grade = Character.toString(copy.charAt(0));
                        String status = Character.toString(copy.charAt(2));
                        // gradesArray.add(count, grade);
                        // creditsArray.add(count, credit);
                        // count++;

                        if (copy.charAt(2) == 'I'){

                            int tempYear = Integer.parseInt(semesterYear);
                            if (tempYear < year){

                                grade = "F";

                                gradesArray.add(count, grade);
                                creditsArray.add(count, credit);
                                count++;
                                if (!(copy.substring(3, copy.length()).equals(""))){

                                    totalCredits = copy.substring(3, copy.length());
                                }

                                File f = new File(newPath + "/" + userID + "AllFall" + semesterYear + "Info.txt");
                                PrintWriter pw = new PrintWriter(new FileOutputStream(f, true));
                                pw.append("\n\n\n " + "FALL " + semesterYear.toUpperCase());
                                pw.append("\n" +" ------- " + courseName + " ------- Grade: " + grade + " ------- Credits: " + credit + " ------- Status: " + status + " ------- ");
                                pw.close();
                            }
                            else{

                                gradesArray.add(count, grade);
                                creditsArray.add(count, credit);
                                count++;
                                if (!(copy.substring(3, copy.length()).equals(""))){

                                    totalCredits = copy.substring(3, copy.length());
                                }

                                File f = new File(newPath + "/" + userID + "AllFall" + semesterYear + "Info.txt");
                                PrintWriter pw = new PrintWriter(new FileOutputStream(f, true));
                                pw.append("\n\n\n " + "FALL " + semesterYear.toUpperCase());
                                pw.append("\n" +" ------- " + courseName + " ------- Grade: " + grade + " ------- Credits: " + credit + " ------- Status: " + status + " ------- ");
                                pw.close();
                            }
                        }
                        else if ((copy.charAt(2) == 'W') || (copy.charAt(2) == 'N')){

                            if (!(copy.substring(3, copy.length()).equals(""))){

                                totalCredits = copy.substring(3, copy.length());
                            }

                            File f = new File(newPath + "/" + userID + "AllFall" + semesterYear + "Info.txt");
                            PrintWriter pw = new PrintWriter(new FileOutputStream(f, true));
                            pw.append("\n\n\n " + "FALL " + semesterYear.toUpperCase());
                            pw.append("\n" +" ------- " + courseName + " ------- Grade: " + grade + " ------- Credits: " + credit + " ------- Status: " + status + " ------- ");
                            pw.close();
                        }
                        else{

                            gradesArray.add(count, grade);
                            creditsArray.add(count, credit);
                            count++;
                            if (!(copy.substring(3, copy.length()).equals(""))){

                                totalCredits = copy.substring(3, copy.length());
                            }

                            File f = new File(newPath + "/" + userID + "AllFall" + semesterYear + "Info.txt");
                            PrintWriter pw = new PrintWriter(new FileOutputStream(f, true));
                            pw.append("\n\n\n " + "FALL " + semesterYear.toUpperCase());
                            pw.append("\n" +" ------- " + courseName + " ------- Grade: " + grade + " ------- Credits: " + credit + " ------- Status: " + status + " ------- ");
                            pw.close();
                        }
                        
                        // File f = new File(newPath + "/" + userID + "AllFall" + semesterYear + "Info.txt");
                        // PrintWriter pw = new PrintWriter(new FileOutputStream(f, true));
                        // pw.append("\n\n\n " + "FALL " + semesterYear.toUpperCase());
                        // pw.append("\n" +" ------- " + courseName + " ------- Grade: " + grade + " ------- Credits: " + credit + " ------- Status: " + status + " ------- ");
                        // pw.close();
        
                    }
                    catch(Exception ex){}
                }
                else if (fileName.contains("Summer")){

                    String semesterYear = fileName.substring(startSeason, endSeason);
                    String courseName = fileName.substring(startCN, endCNforS);

                    try
                    {
                        BufferedReader br = new BufferedReader(new FileReader(path + userID + courseName + "Summer" + semesterYear + ".txt"));
                        String line;
                        String copy = "";
                        while ((line = br.readLine()) != null){
                    
                            if (line != null){
                                copy = line;
                            }
                        }
                        br.close();
                        String credit = Character.toString(copy.charAt(1));
                        String grade = Character.toString(copy.charAt(0));
                        String status = Character.toString(copy.charAt(2));
                        // gradesArray.add(count, grade);
                        // creditsArray.add(count, credit);
                        // count++;

                        if (copy.charAt(2) == 'I'){

                            int tempYear = Integer.parseInt(semesterYear);
                            if (tempYear < year){

                                grade = "F";

                                gradesArray.add(count, grade);
                                creditsArray.add(count, credit);
                                count++;
                                if (!(copy.substring(3, copy.length()).equals(""))){

                                    totalCredits = copy.substring(3, copy.length());
                                }

                                File f = new File(newPath + "/" + userID + "AllSummer" + semesterYear + "Info.txt");
                                PrintWriter pw = new PrintWriter(new FileOutputStream(f, true));
                                pw.append("\n\n\n " + "SUMMER " + semesterYear.toUpperCase());
                                pw.append("\n" +" ------- " + courseName + " ------- Grade: " + grade + " ------- Credits: " + credit + " ------- Status: " + status + " ------- ");
                                pw.close();
                            }
                            else{

                                gradesArray.add(count, grade);
                                creditsArray.add(count, credit);
                                count++;
                                if (!(copy.substring(3, copy.length()).equals(""))){

                                    totalCredits = copy.substring(3, copy.length());
                                }

                                File f = new File(newPath + "/" + userID + "AllSummer" + semesterYear + "Info.txt");
                                PrintWriter pw = new PrintWriter(new FileOutputStream(f, true));
                                pw.append("\n\n\n " + "SUMMER " + semesterYear.toUpperCase());
                                pw.append("\n" +" ------- " + courseName + " ------- Grade: " + grade + " ------- Credits: " + credit + " ------- Status: " + status + " ------- ");
                                pw.close();
                            }
                        }
                        else if ((copy.charAt(2) == 'W') || (copy.charAt(2) == 'N')){

                            if (!(copy.substring(3, copy.length()).equals(""))){

                                totalCredits = copy.substring(3, copy.length());
                            }

                            File f = new File(newPath + "/" + userID + "AllSummer" + semesterYear + "Info.txt");
                            PrintWriter pw = new PrintWriter(new FileOutputStream(f, true));
                            pw.append("\n\n\n " + "SUMMER " + semesterYear.toUpperCase());
                            pw.append("\n" +" ------- " + courseName + " ------- Grade: " + grade + " ------- Credits: " + credit + " ------- Status: " + status + " ------- ");
                            pw.close();
                        }
                        else{

                            gradesArray.add(count, grade);
                            creditsArray.add(count, credit);
                            count++;
                            if (!(copy.substring(3, copy.length()).equals(""))){

                                totalCredits = copy.substring(3, copy.length());
                            }

                            File f = new File(newPath + "/" + userID + "AllSummer" + semesterYear + "Info.txt");
                            PrintWriter pw = new PrintWriter(new FileOutputStream(f, true));
                            pw.append("\n\n\n " + "SUMMER " + semesterYear.toUpperCase());
                            pw.append("\n" +" ------- " + courseName + " ------- Grade: " + grade + " ------- Credits: " + credit + " ------- Status: " + status + " ------- ");
                            pw.close();
                        }
                        
                        // File f = new File(newPath + "/" + userID + "AllSummer" + semesterYear + "Info.txt");
                        // PrintWriter pw = new PrintWriter(new FileOutputStream(f, true));
                        // pw.append("\n\n\n " + "SUMMER " + semesterYear.toUpperCase());
                        // pw.append("\n" +" ------- " + courseName + " ------- Grade: " + grade + " ------- Credits: " + credit + " ------- Status: " + status + " ------- ");
                        // pw.close();
        
                    }
                    catch(Exception ex){}
                }
                else if (fileName.contains("Spring")){

                    String semesterYear = fileName.substring(startSeason, endSeason);
                    String courseName = fileName.substring(startCN, endCNforS);

                    try
                    {
                        BufferedReader br = new BufferedReader(new FileReader(path + userID + courseName + "Spring" + semesterYear + ".txt"));
                        String line;
                        String copy = "";
                        while ((line = br.readLine()) != null){
                    
                            if (line != null){
                                copy = line;
                            }
                        }
                        br.close();
                        String credit = Character.toString(copy.charAt(1));
                        String grade = Character.toString(copy.charAt(0));
                        String status = Character.toString(copy.charAt(2));
                        // gradesArray.add(count, grade);
                        // creditsArray.add(count, credit);
                        // count++;

                        if (copy.charAt(2) == 'I'){

                            int tempYear = Integer.parseInt(semesterYear);
                            if (tempYear < year){

                                grade = "F";

                                gradesArray.add(count, grade);
                                creditsArray.add(count, credit);
                                count++;
                                if (!(copy.substring(3, copy.length()).equals(""))){

                                    totalCredits = copy.substring(3, copy.length());
                                }

                                File f = new File(newPath + "/" + userID + "AllSpring" + semesterYear + "Info.txt");
                                PrintWriter pw = new PrintWriter(new FileOutputStream(f, true));
                                pw.append("\n\n\n " + "SPRING " + semesterYear.toUpperCase());
                                pw.append("\n" +" ------- " + courseName + " ------- Grade: " + grade + " ------- Credits: " + credit + " ------- Status: " + status + " ------- ");
                                pw.close();
                            }
                            else{

                                gradesArray.add(count, grade);
                                creditsArray.add(count, credit);
                                count++;
                                if (!(copy.substring(3, copy.length()).equals(""))){

                                    totalCredits = copy.substring(3, copy.length());
                                }

                                File f = new File(newPath + "/" + userID + "AllSpring" + semesterYear + "Info.txt");
                                PrintWriter pw = new PrintWriter(new FileOutputStream(f, true));
                                pw.append("\n\n\n " + "SPRING " + semesterYear.toUpperCase());
                                pw.append("\n" +" ------- " + courseName + " ------- Grade: " + grade + " ------- Credits: " + credit + " ------- Status: " + status + " ------- ");
                                pw.close();
                            }
                        }
                        else if ((copy.charAt(2) == 'W') || (copy.charAt(2) == 'N')){

                            if (!(copy.substring(3, copy.length()).equals(""))){

                                totalCredits = copy.substring(3, copy.length());
                            }

                            File f = new File(newPath + "/" + userID + "AllSpring" + semesterYear + "Info.txt");
                            PrintWriter pw = new PrintWriter(new FileOutputStream(f, true));
                            pw.append("\n\n\n " + "SPRING " + semesterYear.toUpperCase());
                            pw.append("\n" +" ------- " + courseName + " ------- Grade: " + grade + " ------- Credits: " + credit + " ------- Status: " + status + " ------- ");
                            pw.close();
                        }
                        else{

                            gradesArray.add(count, grade);
                            creditsArray.add(count, credit);
                            count++;
                            if (!(copy.substring(3, copy.length()).equals(""))){

                                totalCredits = copy.substring(3, copy.length());
                            }

                            File f = new File(newPath + "/" + userID + "AllSpring" + semesterYear + "Info.txt");
                            PrintWriter pw = new PrintWriter(new FileOutputStream(f, true));
                            pw.append("\n\n\n " + "SPRING " + semesterYear.toUpperCase());
                            pw.append("\n" +" ------- " + courseName + " ------- Grade: " + grade + " ------- Credits: " + credit + " ------- Status: " + status + " ------- ");
                            pw.close();
                        }
                        
                        // File f = new File(newPath + "/" + userID + "AllSpring" + semesterYear + "Info.txt");
                        // PrintWriter pw = new PrintWriter(new FileOutputStream(f, true));
                        // pw.append("\n\n\n " + "SPRING " + semesterYear.toUpperCase());
                        // pw.append("\n" +" ------- " + courseName + " ------- Grade: " + grade + " ------- Credits: " + credit + " ------- ");
                        // pw.close();
        
                    }
                    catch(Exception ex){}
                }
            }
                
        }

        int totalCreditsCompleted = 0;
        double gpa = 0.0;
        int temp = 0;
        double gradePoints = 0.0;
        for (int i = 0; i < gradesArray.size(); i++){

            temp = Integer.parseInt(creditsArray.get(i));
            totalCreditsCompleted = temp + totalCreditsCompleted;

            String tempGrade = "";
            tempGrade = gradesArray.get(i);
            if (tempGrade.equals("A")){

                gradePoints = gradePoints + (4 * temp); 
            }
            else if (tempGrade.equals("B")){

                gradePoints = gradePoints + (3.3 * temp);
            }
            else if (tempGrade.equals("C")){

                gradePoints = gradePoints + (2.3 * temp);
            }
            else if (tempGrade.equals("D")){

                gradePoints = gradePoints + (1.3 * temp);
            }
            else{

                gradePoints = gradePoints + (0 * temp); 
            }

        }
        gpa = (double) gradePoints / totalCreditsCompleted;
        gpa = Math.round(gpa * 100.0)/100.0;

        try
        {
            File f = new File(newPath + "/" + userID + "AllInfo.txt");
            PrintWriter pw = new PrintWriter(new FileOutputStream(f, true));
            pw.append("\n" + "TOTAL GPA: " + gpa + "\n" + "TOTAL CREDITS COMPLETED: " + totalCreditsCompleted + " / " + totalCredits);
            pw.close();

        }
        catch(Exception ex){}

    }


}
    

