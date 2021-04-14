import java.util.HashMap;

public class StudentDictionary extends Dictionary{

	public StudentDictionary(HashMap<String, Integer> i, HashMap<Integer, String> v) {
		ids = i;
		values = v;
	}
}
