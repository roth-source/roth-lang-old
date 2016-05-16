package roth.lang.java.type;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.TypeVariable;

import roth.lang.Set;
import roth.lang.java.JavaWorkspace;

public class JavaInterface extends JavaType
{
	protected boolean _abstract;
	protected Set<JavaTypeVariable> typeVariables = new Set<>();
	protected JavaTypeDeclaration extendsTypeDeclaration;
	protected Set<JavaField> constants = new Set<>();
	protected Set<JavaMethod> staticMethods = new Set<>();
	protected Set<String> staticTypes = new Set<>();
	protected Set<JavaMethod> abstractMethods = new Set<>();
	protected Set<JavaMethod> defaultMethods = new Set<>();
	protected Set<String> memberTypes = new Set<>();
	
	public JavaInterface()
	{
		
	}
	
	public JavaInterface(Class<?> _class)
	{
		super(_class);
		_abstract = Modifier.isAbstract(_class.getModifiers());
		for(TypeVariable<?> typeVariable : _class.getTypeParameters())
		{
			typeVariables.add(new JavaTypeVariable(typeVariable));
		}
		extendsTypeDeclaration = JavaTypeDeclaration.get(_class.getGenericSuperclass());
		for(Field declaredField : _class.getDeclaredFields())
		{
			JavaField field = new JavaField(declaredField);
			if(field.isStatic() && field.isFinal())
			{
				constants.add(field);
			}
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
				defaultMethods.add(method);
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
	public void toRoth(JavaWorkspace workspace, StringBuilder builder)
	{
		
	}
	
	@Override
	public void toJava(JavaWorkspace workspace, StringBuilder builder)
	{
		
	}
	
}
