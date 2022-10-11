package cz.seli.structEditor.app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FunctionContext
{
	private final Map<String, Variable> variables;
	//List of indexes skipped (for example due condition branching if else blocks)
	private final List<Integer> skipIndexes;

	public FunctionContext()
	{
		this.variables = new HashMap<>();
		this.skipIndexes = new ArrayList<>();
	}

	public Object getVariable(String varName)
	{
		return variables.get(varName);
	}

	@SuppressWarnings("unchecked")
	public <T> T getVariableChecked(String varName, Class<T> varClass) throws IOException
	{
		Object var = variables.get(varName);

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

	public boolean isIndexSkipped(int index)
	{
		return this.skipIndexes.contains(index);
	}

	public List<Integer> getSkipIndexes()
	{
		return skipIndexes;
	}

	public void addSkippedIndex(int index)
	{
		this.skipIndexes.add(index);
	}

	public void addSkippedIndexes(List<Integer> indexes)
	{
		this.skipIndexes.addAll(indexes);
	}
}
