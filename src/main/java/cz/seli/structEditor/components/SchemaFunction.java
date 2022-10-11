package cz.seli.structEditor.components;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import cz.seli.structEditor.components.Operation.OperationType;
import cz.seli.structEditor.context.FunctionContext;

public class SchemaFunction<R> extends BasicComponent
{
	private final List<BasicComponent> commands;
	private final Class<R> retvalType;

	private String[] parameters;

	public SchemaFunction(String name, FunctionContext ctx, Class<R> retvalType)
	{
		super(name, null);
		this.commands = new ArrayList<>();
		this.retvalType = retvalType;
	}

	@Override
	public void setValue(String value) throws IOException
	{
		this.value = value;
		int oParenthesisIndex = value.indexOf("(");
		int cParenthesisIndex = value.indexOf(")");

		if(cParenthesisIndex == -1 || oParenthesisIndex == -1)
			throw new IOException("Invalid function value, missing parenthesis");

		String paramString = this.value.substring(oParenthesisIndex + 1, cParenthesisIndex);
		this.parameters = Arrays.stream(paramString.split(",")).map(String::trim).toArray(String[]::new);
	}

	@Override
	@SuppressWarnings("unchecked")
	public R execute(FunctionContext ctx) throws IOException
	{
		for(int i = 0; i < commands.size(); i++)
		{
			if(ctx.getSkipIndexes().contains(i))
				continue;
			else
			{
				BasicComponent comp = commands.get(i);
				if(comp instanceof Operation && ((Operation) comp).getType().equals(OperationType.RETURN))
				{
					Object retval = comp.execute(ctx);

					if(retvalType == null || retvalType.equals(retval.getClass()))
						return (R) retval;
					else
						throw new IOException("Return value " + retval + " is of invalid type.");
				}
				else
					comp.execute(ctx);
			}
		}

		return null;
	}

	@Override
	public String getCode()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("function ").append(name).append("(");
		sb.append(Arrays.stream(parameters).collect(Collectors.joining(", ")));
		sb.append(")\n");
		sb.append("{\n");
		commands.stream().forEach(c -> sb.append(c.getCode()));
		sb.append("}\n");

		return sb.toString();
	}

	public String[] getParameters()
	{
		return parameters;
	}
}
