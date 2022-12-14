package cz.seli.structEditor.context;

public class Variable 
{
	private final String name;
	
	private Object value;

	public Variable(String name, Object value)
	{
		this.name = name;
		this.value = value;
	}

	public String getName()
	{
		return name;
	}

	public Object getValue()
	{
		return value;
	}

	public void setValue(Object value)
	{
		this.value = value;
	}
}
