package cz.seli.structEditor.components;

public abstract class BasicComponent implements Component
{
	protected final String name;
	protected final Object graphic; //TODO JavaFX graphic

	protected String value;

	public BasicComponent(String name, Object graphic)
	{
		this.name = name;
		this.graphic = graphic;
	}

	@Override
	public String getName()
	{
		return name;
	}

	@Override
	public Object getGraphic()
	{
		return graphic;
	}

	@Override
	public void setValue(String value)
	{
		this.value = value;
	}

	@Override
	public String getValue()
	{
		return value;
	}
}
