package cz.seli.structEditor.context;

import java.util.ArrayList;
import java.util.List;

public class FunctionContext extends Context
{
	private final GlobalContext globalContext;
	//List of indexes skipped (for example due condition branching if else blocks)
	private final List<Integer> skipIndexes;

	public FunctionContext(GlobalContext globalContext)
	{
		super();
		this.skipIndexes = new ArrayList<>();
		this.globalContext = globalContext;
	}

	@Override
	public Object getVariable(String varName)
	{
		Object variable = super.getVariable(varName);
		
		if(variable == null)
			variable = globalContext.getVariable(varName);
		
			return variable;
	}

	@Override
	public void addVariable(Variable value)
	{
		if(value.getName().startsWith("$"))
			this.globalContext.addVariable(value);
		else
			super.addVariable(value);
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

	public GlobalContext getGlobalContext()
	{
		return globalContext;
	}
}
