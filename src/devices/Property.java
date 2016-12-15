package devices;

public class Property {
	private String name;
	private String value;
	/**
	 * user specified device parameter
	 * @param name
	 * @param value
	 */
	public Property(String name, String value) {
		this.setName(name);
		this.setValue(value);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

}
