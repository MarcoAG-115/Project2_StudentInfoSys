import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//DictionaryFactory creates 3 dictionary files and passes them to classes that use them.
public class DictionaryFactory {
	private StudentDictionary sd;
	private CourseDictionary cd;
	private InformationDictionary id;
	
	//The 3 filenames correspond to 3 files with a semicolon-delimited list of dictionary entries.
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
			System.out.println("Error: file " + filename + " not found\n");
			return d;
		}
		int i = in.nextInt();
		String text = in.nextLine();
		String[] pairs = text.split(";");
		for(int j = 0;j < i;j++) {
			String[] temp = pairs[j].split(",");
			d.addEntry(temp[0], Integer.parseInt(temp[1]));
		}
		in.close();
		return d;
	}
	public Dictionary getStudentDictionary() {
		return sd;
	}
}
