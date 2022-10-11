package cz.seli.structEditor.context;

import java.util.HashMap;
import java.util.Map;

import cz.seli.structEditor.components.SchemaFunction;

public class GlobalContext extends Context
{
	private final Map<String, SchemaFunction<?>> functionMap;

	public GlobalContext(Map<String, SchemaFunction<?>> functionMap)
	{
		super(new HashMap<>());
		this.functionMap = functionMap;
	}

	public GlobalContext(Map<String, SchemaFunction<?>> functionMap, Map<String, Variable> variables)
	{
		super(variables);
		this.functionMap = functionMap;
	}

	public Map<String, SchemaFunction<?>> getFunctionMap()
	{
		return functionMap;
	}

	public SchemaFunction<?> getFunction(String functName)
	{
		return functionMap.get(functName);
	}

}
