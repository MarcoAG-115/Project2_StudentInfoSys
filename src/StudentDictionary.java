import java.util.HashMap;

//StudentDictionary stores studentIds, which range from 5000000-6000000.
public class StudentDictionary extends Dictionary{

	public StudentDictionary(HashMap<String, Integer> i, HashMap<Integer, String> v) {
		ids = i;
		values = v;
	}
	public StudentDictionary() {
		ids = new HashMap<String, Integer>();
		values = new HashMap<Integer, String>();
	}
	public void addEntry(String name, int id) {
		name = name.toUpperCase();
		ids.put(name, id);
		values.put(id, name);
	}
}
