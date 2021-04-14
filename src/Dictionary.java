import java.util.HashMap;


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
}
