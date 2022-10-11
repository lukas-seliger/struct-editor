package cz.seli.structEditor.components;

import java.io.IOException;

import cz.seli.structEditor.app.FunctionContext;

public interface Component 
{
	public String getName();

	public String getCode();

	public Object getGraphic(); //TODO JavaFX

	public String getValue();

	public void setValue(String value);
	
	public Object execute(FunctionContext ctx) throws IOException;
}
