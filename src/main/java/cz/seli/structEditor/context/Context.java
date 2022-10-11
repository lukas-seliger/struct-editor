package cz.seli.structEditor.context;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class Context
{
	protected final Map<String, Variable> variables;

	public Context()
	{
		this.variables = new HashMap<>();
	}

	public Context(Map<String, Variable> variables)
	{
		this.variables = variables;
	}

	public Object getVariable(String varName)
	{
		Variable v = variables.get(varName);
		return v != null ? v.getValue() : null;
	}

	@SuppressWarnings("unchecked")
	public <T> T getVariableChecked(String varName, Class<T> varClass) throws IOException
	{
		Object var = getVariable(varName);

		if(var == null)
			return null;
		if(var.getClass().equals(varClass))
			return (T) var;
		else
			throw new IOException("Variable " + varName + " is not of type " + varClass.getName());
	}

	public void addVariable(Variable value)
	{
		this.variables.put(value.getName(), value);
	}
}
