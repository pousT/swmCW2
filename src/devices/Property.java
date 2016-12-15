package devices;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Property {
	private String name;
	private StringProperty value;
	/**
	 * user specified device parameter
	 * @param name
	 * @param value
	 */
	public Property(String name, String value) {
		this.name = name;
		this.value = new SimpleStringProperty(value);
	}
	public String getName() {
		return name;
	}

	public String getValue() {
		return value.get();
	}
	public StringProperty valueProperty() {
		return value;
	}
	public void setValue(String value) {
		this.value.set(value);
	}

}
