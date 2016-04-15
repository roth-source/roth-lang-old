package roth.lang.java;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

import roth.lang.Set;

public class JavaClass extends JavaType
{
	protected boolean _abstract;
	protected boolean _final;
	protected Set<JavaTypeVariable> typeVariables = new Set<>();
	protected JavaTypeDeclaration extendsTypeDeclaration;
	protected Set<JavaTypeDeclaration> implementsTypeDeclarations = new Set<>();
	protected Set<JavaField> constants = new Set<>();
	protected Set<JavaField> staticFields = new Set<>();
	protected Set<JavaMethod> staticMethods = new Set<>();
	protected Set<String> staticTypes = new Set<>();
	protected Set<JavaField> memberFields = new Set<>();
	protected Set<JavaConstructor> constructors = new Set<>();
	protected Set<JavaMethod> memberMethods = new Set<>();
	protected Set<String> memberTypes = new Set<>();
	
	public JavaClass()
	{
		
	}
	
	public JavaClass(Class<?> klass)
	{
		super(klass);
		_abstract = Modifier.isAbstract(klass.getModifiers());
		_final = Modifier.isFinal(klass.getModifiers());
		for(TypeVariable<?> typeVariable : klass.getTypeParameters())
		{
			typeVariables.add(new JavaTypeVariable(typeVariable));
		}
		extendsTypeDeclaration = JavaTypeDeclaration.get(klass.getGenericSuperclass());
		for(Type type : klass.getGenericInterfaces())
		{
			implementsTypeDeclarations.add(JavaTypeDeclaration.get(type));
		}
		for(Field declaredField : klass.getDeclaredFields())
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
		for(Constructor<?> declaredConstructor : klass.getDeclaredConstructors())
		{
			constructors.add(new JavaConstructor(declaredConstructor));
		}
		for(Method declaredMethod : klass.getDeclaredMethods())
		{
			JavaMethod method = new JavaMethod(declaredMethod);
			if(method.isStatic())
			{
				staticMethods.add(method);
			}
			else
			{
				memberMethods.add(method);
			}
		}
		for(Class<?> declaredClass : klass.getDeclaredClasses())
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
		builder.append(PACKAGE);
		builder.append(SPACE);
		builder.append(_package);
		builder.append(SEMI_COLON);
		builder.append(NEW_LINE);
		builder.append(NEW_LINE);
		access.toJava(builder);
		if(_abstract)
		{
			builder.append(ABSTRACT);
			builder.append(SPACE);
		}
		if(_final)
		{
			builder.append(FINAL);
			builder.append(SPACE);
		}
		builder.append(CLASS);
		builder.append(SPACE);
		builder.append(name);
		if(!typeVariables.isEmpty())
		{
			builder.append(LEFT_ANGLE_BRACKET);
			String seperator = BLANK;
			for(JavaTypeVariable typeVariable : typeVariables)
			{
				builder.append(seperator);
				typeVariable.toJava(workspace, builder);
				seperator = COMMA_SEPERATOR;
			}
			builder.append(RIGHT_ANGLE_BRACKET);
		}
		builder.append(SPACE);
		if(extendsTypeDeclaration != null)
		{
			builder.append(EXTENDS);
			builder.append(SPACE);
			extendsTypeDeclaration.toJava(workspace, builder);
			builder.append(SPACE);
		}
		if(!implementsTypeDeclarations.isEmpty())
		{
			builder.append(IMPLEMENTS);
			builder.append(SPACE);
			String seperator = BLANK;
			for(JavaTypeDeclaration implementsTypeDeclaration : implementsTypeDeclarations)
			{
				builder.append(seperator);
				implementsTypeDeclaration.toJava(workspace, builder);
				seperator = COMMA_SEPERATOR;
			}
		}
		builder.append(NEW_LINE);
		builder.append(LEFT_BRACE);
		if(!constants.isEmpty())
		{
			builder.append(NEW_LINE);
			builder.append(TAB);
			builder.append("// CONSTANTS");
			for(JavaField constant : constants)
			{
				builder.append(NEW_LINE);
				builder.append(TAB);
				constant.toJava(workspace, builder);
			}
			builder.append(NEW_LINE);
			builder.append(TAB);
		}
		if(!staticFields.isEmpty())
		{
			builder.append(NEW_LINE);
			builder.append(TAB);
			builder.append("// STATIC FIELDS");
			for(JavaField staticField : staticFields)
			{
				builder.append(NEW_LINE);
				builder.append(TAB);
				staticField.toJava(workspace, builder);
			}
			builder.append(NEW_LINE);
			builder.append(TAB);	
		}
		if(!staticMethods.isEmpty())
		{
			builder.append(NEW_LINE);
			builder.append(TAB);
			builder.append("// STATIC METHODS");
			for(JavaMethod staticMethod : staticMethods)
			{
				builder.append(NEW_LINE);
				builder.append(TAB);
				staticMethod.toJava(workspace, builder);
				builder.append(NEW_LINE);
				builder.append(TAB);
			}
		}
		if(!memberFields.isEmpty())
		{
			builder.append(NEW_LINE);
			builder.append(TAB);
			builder.append("// MEMBER FIELDS");
			for(JavaField memberField : memberFields)
			{
				builder.append(NEW_LINE);
				builder.append(TAB);
				memberField.toJava(workspace, builder);
			}
			builder.append(NEW_LINE);
			builder.append(TAB);	
		}
		if(!constructors.isEmpty())
		{
			builder.append(NEW_LINE);
			builder.append(TAB);
			builder.append("// CONSTRUCTORS");
			for(JavaConstructor constructor : constructors)
			{
				builder.append(NEW_LINE);
				builder.append(TAB);
				constructor.toJava(workspace, builder, name);
				builder.append(NEW_LINE);
				builder.append(TAB);
			}
		}
		if(!memberMethods.isEmpty())
		{
			builder.append(NEW_LINE);
			builder.append(TAB);
			builder.append("// MEMBER METHODS");
			for(JavaMethod memberMethod : memberMethods)
			{
				builder.append(NEW_LINE);
				builder.append(TAB);
				memberMethod.toJava(workspace, builder);
				builder.append(NEW_LINE);
				builder.append(TAB);
			}
		}
		builder.append(NEW_LINE);
		builder.append(RIGHT_BRACE);
	}
	
}
