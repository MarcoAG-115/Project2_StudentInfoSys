import java.util.HashMap;

public class CourseDictionary extends Dictionary{

	public CourseDictionary(HashMap<String, Integer> i, HashMap<Integer, String> v) {
		ids = i;
		values = v;
	}
}