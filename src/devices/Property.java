package devices;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Property {
	private StringProperty name;
	private StringProperty value;
	/**
	 * user specified device parameter
	 * @param name property name
	 * @param value property value
	 */
	public Property(String name, String value) {
		this.name = new SimpleStringProperty(name);
		this.value = new SimpleStringProperty(value);
	}
	public String getName() {
		return name.get();
	}
	public StringProperty nameProperty() {
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
