package roth.lang.java;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import roth.lang.Set;

public class JavaEnum extends JavaType
{
	protected Set<String> values = new Set<>();
	protected Set<JavaField> constants = new Set<>();
	protected Set<JavaField> staticFields = new Set<>();
	protected Set<JavaMethod> staticMethods = new Set<>();
	protected Set<String> staticTypes = new Set<>();
	protected Set<JavaField> memberFields = new Set<>();
	protected Set<JavaConstructor> constructors = new Set<>();
	protected Set<JavaMethod> abstractMethods = new Set<>();
	protected Set<JavaMethod> memberMethods = new Set<>();
	protected Set<String> memberTypes = new Set<>();
	
	
	public JavaEnum()
	{
		
	}
	
	public JavaEnum(Class<?> _class)
	{
		super(_class);
		for(Enum<?> enumConstant : (Enum<?>[]) _class.getEnumConstants())
		{
			values.add(enumConstant.name());
		}
		for(Field declaredField : _class.getDeclaredFields())
		{
			JavaField field = new JavaField(declaredField);
			if(field.isStatic())
			{
				if(field.isFinal())
				{
					constants.add(field);
				}
				else
				{
					staticFields.add(field);
				}
			}
			else
			{
				memberFields.add(field);
			}
		}
		for(Constructor<?> declaredConstructor : _class.getDeclaredConstructors())
		{
			constructors.add(new JavaConstructor(declaredConstructor));
		}
		for(Method declaredMethod : _class.getDeclaredMethods())
		{
			JavaMethod method = new JavaMethod(declaredMethod);
			if(method.isStatic())
			{
				staticMethods.add(method);
			}
			else if(method.isAbstract())
			{
				abstractMethods.add(method);
			}
			else
			{
				memberMethods.add(method);
			}
		}
		for(Class<?> declaredClass : _class.getDeclaredClasses())
		{
			String fullName = JavaType.getFullName(declaredClass);
			if(Modifier.isStatic(declaredClass.getModifiers()))
			{
				staticTypes.add(fullName);
			}
			else
			{
				memberTypes.add(fullName);
			}
		}
	}
	
	@Override
	protected void toRoth(JavaWorkspace workspace, StringBuilder builder)
	{
		
	}
	
	@Override
	protected void toJava(JavaWorkspace workspace, StringBuilder builder)
	{
		
	}
	
}
