package roth.lang.java;

import roth.lang.Characters;

public abstract class JavaCode implements Characters, JavaReserved
{
	
	protected JavaCode()
	{
		
	}
	
	public String toRoth(JavaWorkspace workspace)
	{
		StringBuilder builder = new StringBuilder();
		toRoth(workspace, builder);
		return builder.toString();
	}
	
	protected void toRoth(JavaWorkspace workspace, StringBuilder builder)
	{
		
	}
	
	public String toJava(JavaWorkspace workspace)
	{
		StringBuilder builder = new StringBuilder();
		toJava(workspace, builder);
		return builder.toString();
	}
	
	protected void toJava(JavaWorkspace workspace, StringBuilder builder)
	{
		
	}
	
}
