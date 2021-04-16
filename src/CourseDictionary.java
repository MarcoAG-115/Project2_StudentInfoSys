import java.util.HashMap;

//CourseDictionary stores courseIds. They range from 10000-20000.
public class CourseDictionary extends Dictionary{

	public CourseDictionary(HashMap<String, Integer> i, HashMap<Integer, String> v) {
		ids = i;
		values = v;
	}
	public CourseDictionary() {
		ids = new HashMap<String, Integer>();
		values = new HashMap<Integer, String>();
	}
	public void addEntry(String course, int id) {
		course = course.toUpperCase();
		ids.put(course, id);
		values.put(id, course);
	}
}