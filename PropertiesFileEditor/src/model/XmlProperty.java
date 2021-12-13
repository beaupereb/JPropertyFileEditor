package model;

public class XmlProperty<T> implements Comparable{

	protected String name;
	protected T value;
	protected T defaultValue;
	
	public XmlProperty(String name, T defaultValue, T value){
		this.name = name;
		this.defaultValue = defaultValue;
		this.value = value;
	}
	
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public T getValue() {
		return value;
	}


	public void setValue(T value) {
		this.value = value;
	}


	public T getDefaultValue() {
		return defaultValue;
	}


	public void setDefaultValue(T defaultValue) {
		this.defaultValue = defaultValue;
	}
	
	
	
}
