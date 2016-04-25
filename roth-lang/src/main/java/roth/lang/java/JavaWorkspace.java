package roth.lang.java;

import java.io.File;
import java.io.IOException;
import java.util.Map.Entry;

import roth.lang.Map;
import roth.lang.Set;

public class JavaWorkspace
{
	protected File jdkDir;
	protected Set<File> classpathJarFiles = new Set<>();
	protected Map<String, JavaType> typeMap = new Map<>();
	
	public JavaWorkspace()
	{
		
	}
	
	public File getJdkDir()
	{
		return jdkDir;
	}
	
	public File getJreDir()
	{
		return new File(jdkDir, "jre");
	}
	
	public Set<File> getJreJarFiles()
	{
		return getJarFiles(getJreDir());
	}
	
	public Set<File> getClasspathJarFiles()
	{
		return classpathJarFiles;
	}
	
	public Set<File> getAllJarFiles()
	{
		return new Set<File>().collection(getJreJarFiles()).collection(getClasspathJarFiles());
	}
	
	public Map<String, JavaType> getTypeMap()
	{
		return typeMap;
	}
	
	public JavaType getType(String name)
	{
		return typeMap.get(name);
	}
	
	public JavaWorkspace setJdkDir(File jdkDir)
	{
		this.jdkDir = jdkDir;
		return this;
	}
	
	public JavaWorkspace setClasspathJarFiles(Set<File> classpathJarFiles)
	{
		this.classpathJarFiles = classpathJarFiles;
		return this;
	}
	
	public JavaWorkspace addClasspathJarFiles(File...classpathJarFiles)
	{
		this.classpathJarFiles.array(classpathJarFiles);
		return this;
	}
	
	public JavaWorkspace putType(String fullName, JavaType type)
	{
		typeMap.put(fullName, type);
		return this;
	}
	
	public JavaWorkspace load()
	{
		try(JavaClassLoader classLoader = new JavaClassLoader(this.getAllJarFiles());)
		{
			for(Entry<File, Set<Class<?>>> jarFileClassesEntry : classLoader.getJarFileClassesMap().entrySet())
			{
				File jarFile = jarFileClassesEntry.getKey();
				for(Class<?> _class : jarFileClassesEntry.getValue())
				{
					if(JavaType.isValid(_class))
					{
						String fullName = JavaType.getFullName(_class);
						JavaType type = this.getType(fullName);
						if(type == null)
						{
							type = JavaType.get(_class);
							if(type != null)
							{
								this.putType(fullName, type.setJarFile(jarFile));
								//System.out.println(fullName);
							}
						}
						else if(type.getHashCode() != _class.hashCode())
						{
							type = JavaType.get(_class);
							if(type != null)
							{
								this.putType(fullName, type.setJarFile(jarFile));
								//System.out.println(fullName);
							}
						}
						else
						{
							//System.out.println(fullName);
						}
					}
				}
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return this;
	}
	
	public static Set<File> getJarFiles(File dir)
	{
		Set<File> jarFiles = new Set<File>();
		for(File file : dir.listFiles())
		{
			if(file.isDirectory())
			{
				jarFiles.addAll(getJarFiles(file));
			}
			else if(file.isFile() && file.getName().endsWith(".jar"))
			{
				jarFiles.add(file);
			}
		}
		return jarFiles;
	}
	
	public static void main(String[] args) throws Exception
	{
		File jdkDir = new File("/Library/Java/JavaVirtualMachines/jdk1.8.0_77.jdk/Contents/Home");
		JavaWorkspace workspace = new JavaWorkspace().setJdkDir(jdkDir);
		workspace.load();
	}
	
}
