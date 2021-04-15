import java.util.HashMap;

//InformationDictionary stores informationIds, which range from 00-99.
//InformationIds are used by the database to differentiate between grades, 
//different types of demographic information, and degrees.
public class InformationDictionary extends Dictionary{

	public InformationDictionary(HashMap<String, Integer> i, HashMap<Integer, String> v) {
		ids = i;
		values = v;
	}
	public InformationDictionary() {
		ids = new HashMap<String, Integer>();
		values = new HashMap<Integer, String>();
	}
	@Override
	public void addEntry(String type, int id) {
		type = type.toUpperCase();
		ids.put(type, id);
		values.put(id, type);
	}
}