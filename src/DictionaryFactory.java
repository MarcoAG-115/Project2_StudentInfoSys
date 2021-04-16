import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//DictionaryFactory creates 3 dictionary files and passes them to classes that use them.
public class DictionaryFactory {
	private StudentDictionary sd;
	private CourseDictionary cd;
	private InformationDictionary id;
	
	//The 3 filenames correspond to 3 files with a semicolon-delimited list of dictionary entries. 
	//Each entry is a pair [value],[id]
	public DictionaryFactory(String studentFile, String courseFile, String informationFile) {
		sd = new StudentDictionary();
		cd = new CourseDictionary();
		id = new InformationDictionary();
		sd = (StudentDictionary) fillDictionary(sd, studentFile);
		cd = (CourseDictionary) fillDictionary(cd, courseFile);
		id = (InformationDictionary) fillDictionary(id, informationFile);
	}

	public Dictionary fillDictionary(Dictionary d, String filename) {
		File f = new File(filename);
		Scanner in;
		try {
			in = new Scanner(f);
		} catch (FileNotFoundException e) {
			System.out.println("Error: File " + filename + " not found\n");
			return d;
		}
		String text = in.nextLine();
		String[] pairs = text.split(";");
		for(int i = 0;i < pairs.length;i++) {
			String[] temp = pairs[i].split(",");
			d.addEntry(temp[0], Integer.parseInt(temp[1]));
		}
		in.close();
		return d;
	}
	public Dictionary getStudentDictionary() {
		return sd;
	}
}
