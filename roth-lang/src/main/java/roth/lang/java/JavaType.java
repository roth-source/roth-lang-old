package roth.lang.java;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;

import roth.lang.List;
import roth.lang.Set;

public abstract class JavaType extends JavaCode
{
	protected File jarFile;
	protected File sourceFile;
	protected int hashCode;
	protected String _package;
	protected List<String> enclosures = new List<>();
	protected Set<JavaAnnotationDeclaration> annotationDeclarations = new Set<>();
	protected JavaAccess access;
	protected boolean _static;
	protected boolean member;
	protected String name;
	
	protected JavaType()
	{
		
	}
	
	protected JavaType(Class<?> _class)
	{
		hashCode = _class.hashCode();
		_package = _class.getPackage().getName();
		Class<?> enclosingClass = _class;
		while((enclosingClass = enclosingClass.getEnclosingClass()) != null)
		{
			enclosures.addFirst(enclosingClass.getSimpleName());
		}
		for(Annotation declaredAnnotation : _class.getDeclaredAnnotations())
		{
			annotationDeclarations.add(new JavaAnnotationDeclaration(declaredAnnotation));
		}
		access = JavaAccess.fromModifiers(_class.getModifiers());
		_static = Modifier.isStatic(_class.getModifiers());
		member = _class.isMemberClass();
		name = _class.getSimpleName();
	}
	
	public File getJarFile()
	{
		return jarFile;
	}
	
	public File getSourceFile()
	{
		return sourceFile;
	}
	
	public int getHashCode()
	{
		return hashCode;
	}
	
	public String getPackage()
	{
		return _package;
	}
	
	public JavaAccess getAccess()
	{
		return access;
	}
	
	public boolean isStatic()
	{
		return _static;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getFullName()
	{
		StringBuilder builder = new StringBuilder();
		if(_package != null && !_package.isEmpty())
		{
			builder.append(_package);
			builder.append(".");
		}
		for(String enclosure : enclosures)
		{
			builder.append(enclosure);
			builder.append(".");
		}
		builder.append(name);
		return builder.toString();
	}
	
	public JavaType setJarFile(File jarFile)
	{
		this.jarFile = jarFile;
		return this;
	}
	
	public JavaType setSourceFile(File sourceFile)
	{
		this.sourceFile = sourceFile;
		return this;
	}
	
	public JavaType setHashCode(int hashCode)
	{
		this.hashCode = hashCode;
		return this;
	}
	
	public JavaType setPackage(String _package)
	{
		this._package = _package;
		return this;
	}
	
	public JavaType setAccess(JavaAccess access)
	{
		this.access = access;
		return this;
	}
	
	public JavaType setStatic(boolean _static)
	{
		this._static = _static;
		return this;
	}
	
	public JavaType setName(String name)
	{
		this.name = name;
		return this;
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_package == null) ? 0 : _package.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(this == obj)
		{
			return true;
		}
		if(obj == null)
		{
			return false;
		}
		if(getClass() != obj.getClass())
		{
			return false;
		}
		JavaType other = (JavaType) obj;
		if(_package == null)
		{
			if(other._package != null)
			{
				return false;
			}
		}
		else if(!_package.equals(other._package))
		{
			return false;
		}
		if(name == null)
		{
			if(other.name != null)
			{
				return false;
			}
		}
		else if(!name.equals(other.name))
		{
			return false;
		}
		return true;
	}
	
	public static JavaType get(Class<?> _class)
	{
		JavaType type = null;
		try
		{
			if(_class.isAnnotation())
			{
				type = new JavaAnnotation(_class);
			}
			else if(_class.isEnum())
			{
				type = new JavaEnum(_class);
			}
			else if(_class.isInterface())
			{
				type = new JavaInterface(_class);
			}
			else if(!_class.isAnonymousClass() && !_class.isLocalClass())
			{
				type = new JavaClass(_class);
			}
		}
		catch(Throwable e)
		{
			
		}
		return type;
	}
	
	public static String getFullName(Class<?> _class)
	{
		String fullName = null;
		try
		{
			fullName = _class.getCanonicalName();
		}
		catch(Throwable e)
		{
			
		}
		return fullName;
	}
	
	public static boolean isValid(Class<?> _class)
	{
		boolean valid = false;
		try
		{
			valid = _class.isAnnotation() || _class.isEnum() || _class.isInterface() || (!_class.isAnonymousClass() && !_class.isLocalClass());
		}
		catch(Throwable e)
		{
			
		}
		return valid;
	}
	
}
