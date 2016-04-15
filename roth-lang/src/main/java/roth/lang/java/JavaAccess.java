package roth.lang.java;

import java.lang.reflect.Modifier;

import roth.lang.Characters;

public enum JavaAccess implements Characters
{
	PUBLIC,
	PROTECTED,
	PACKAGE,
	PRIVATE,
	;
	
	@Override
	public String toString()
	{
		return !PACKAGE.equals(this) ? name().toLowerCase() : "";
	}
	
	public void toJava(StringBuilder builder)
	{
		if(!PACKAGE.equals(this))
		{
			builder.append(name().toLowerCase());
			builder.append(SPACE);
		}
	}
	
	public static JavaAccess fromModifiers(int modifiers)
	{
		JavaAccess access = PACKAGE;
		if(Modifier.isPublic(modifiers))
		{
			access = PUBLIC;
		}
		else if(Modifier.isProtected(modifiers))
		{
			access = PROTECTED;
		}
		else if(Modifier.isPrivate(modifiers))
		{
			access = PRIVATE;
		}
		return access;
	}
	
}
