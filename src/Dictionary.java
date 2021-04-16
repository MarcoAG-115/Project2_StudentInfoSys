import java.util.HashMap;

//The dictionary class stores the ids of parameters used in the system.
//Although the three child dictionary classes are currently the same, 
//they are separated because they would use different error-checking methods
//in the full implementation of our system.
public abstract class Dictionary {
	public HashMap<String, Integer> ids;
	public HashMap<Integer, String> values;
	public int getId(String value) {
		if(ids.containsKey(value)) {
			return ids.get(value);
		}
		return -1;
	}
	public String getValue(int id) {
		if(values.containsKey(id)) {
			return values.get(id);
		}
		return null;
	}
	public void addEntry(String value, int id) {
		value = value.toUpperCase();
		ids.put(value, id);
		values.put(id, value);
	}
}
