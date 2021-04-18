//Project 2 Group 11 Student Information System
//COMP 3700
//04-18-2021
//Description: Gathers all info for a student in a folder. Calculates cumulative GPA and
// 			   total credits completed. Updates info according to status, for example if a 			
//             course’s status is I for a year or more, then grade becomes an F.
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;

public class AllStudentInfo {

    //Initializes variable that will store the parameter passed to this class (student ID).
    public String userID;

    //Initializes and assigns current month and year values.
    Date date = new Date();
    LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    int year = localDate.getYear();
    int month = localDate.getMonthValue();
    String currentSeason = "";

    //Method / operation dedicated to gathering all of a student's info into one file.
    //It organizes info so it's ready to display.
    public void gatherInfo(String userID){

        //Finds current school season / semester and assigns it to currentSeason.
        if ((month <= 12) && (month >= 8)){

            currentSeason = "Fall";
    
        }
        else if ((month <= 8) && (month >= 5)){
    
            currentSeason = "Summer";
        }
        else {

            currentSeason = "Spring";
        }

        //Finds necessary file and directory paths needed to create and delete 
        //student files and directories.
        Path p1 = Paths.get("AdminLogin.txt");
        Path p2 = Paths.get((p1.toAbsolutePath()).toString());
        String path = p2.getParent() + "/src/" + userID + "/";
        
        //Finds student directory filled with individual course files / info.
        File folder = new File(p2.getParent() + "/src/" + userID);
        File[] listOfFiles = folder.listFiles();
        //String path = "/Users/marco/Documents/Documents/COMP_3700/Project/Project2_StudentInfoSys/src/" + userID + "/";
        //String newPath = "/Users/marco/Documents/Documents/COMP_3700/Project/Project2_StudentInfoSys/src/" + userID + "AllInfo";
        String newPath = p2.getParent() + "/src/" + userID + "AllInfo";

        //Deletes existing directory and contents that contain all info that is
        //no longer up-to-date.
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

        //A new directory is set up to replace the one that was just deleted.
        File newFolder = new File(newPath);
        newFolder.mkdir();

        //Array list containing all grades and credits for each course are created.
        ArrayList<String> gradesArray = new ArrayList<String>();
        ArrayList<String> creditsArray = new ArrayList<String>();

        //A counter and a variable for totalCredits are initialized.
        String totalCredits = "";
        int count = 0; 

        //This for loop goes through every file representing a student's individual courses
        //contained in the student directory named with only their ID. The files and their
        //contents are organized by according to the info found in the file names. This info
        //includes season, year, and course name. This info is also used to update a course's
        //status if applicable. All the organized info is stored in the new directory that 
        //contains files that each represent and contain info for each semester.
        for (File file : listOfFiles){

            if (file.isFile()){

                //Initializes and sets up variables needed to get substrings that
                //represent individual pieces of information.
                String fileName = file.getName();
                int end = fileName.length();
                int start = end - 12;
                int startCN = 5;
                int endCNforS = end - 14;
                int startSeason = end - 8;
                int endSeason = end - 4;

                if (fileName.contains("Fall")){
                    
                    //Semester year and course name are gathered from the file name.
                    String semesterYear = fileName.substring(startSeason, endSeason);
                    String courseName = fileName.substring(startCN, start);

                    //File contents are read and assigned to the copy variable.
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

                        //The file contents are used to assign the credit, grade, and status 
                        //values for the current file / course. This is done by knowing that
                        //the these values are stored the same way in each file.
                        String credit = Character.toString(copy.charAt(1));
                        String grade = Character.toString(copy.charAt(0));
                        String status = Character.toString(copy.charAt(2));
                      
                        //Course status and info is updated if any of the following conditions are met.
                        //If status is 'I' for more than a year, then grade becomes 'F'.
                        //If status is 'W' or 'N', then course info is not accounted for in totals.
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
                        
                       
        
                    }
                    catch(Exception ex){}
                }
            }
                
        }

        //Initializes and sets up variables needed to calculate total credits
        //completed and cumulatitive GPA using the array list.
        int totalCreditsCompleted = 0;
        double gpa = 0.0;
        int temp = 0;
        double gradePoints = 0.0;

        //Cumulatitive GPA is calculated by getting the result of total grade points
        //divided by the total credits completed found by multiplying course credits 
        //with the corresponding letter grade.
        //An 'A' is equal to 4 grade points.
        //A 'B' is equal to 3.3 grade points.
        //A 'C' is equal to 2.3 grade points.
        //A 'D' is equal to 1.3 grade points.
        //Anything else is equal to 0 grade points.

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

        //Cumulatitive GPA and total credits completed values are stored in their own file
        //in the newest student directory.
        try
        {
            File f = new File(newPath + "/" + userID + "AllInfo.txt");
            PrintWriter pw = new PrintWriter(new FileOutputStream(f, true));
            pw.append("\n" + "TOTAL GPA: " + gpa + "\n" + "TOTAL CREDITS COMPLETED: " + totalCreditsCompleted + " / " + totalCredits);
            pw.close();

        }
        catch(Exception ex){}
    }

    //Assigns student ID parameter so that it can be used by gatherInfo.
    //Calls gatherInfo.
    AllStudentInfo(String user){

        userID = user;

        gatherInfo(userID);

    }


}
    

