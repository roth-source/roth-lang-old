package roth.lang.java;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Collection;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import roth.lang.List;
import roth.lang.Map;
import roth.lang.Set;

public class JavaClassLoader extends URLClassLoader
{
	protected Map<File, Set<Class<?>>> jarFileClassesMap = new Map<>();
	
	public JavaClassLoader(Collection<File> files)
	{
		super(fileUrls(files), ClassLoader.getSystemClassLoader());
		for(File file : files)
		{
			loadJar(file);
		}
	}
	
	protected static URL[] fileUrls(Collection<File> files)
	{
		List<URL> urls = new List<URL>();
		for(File file : files)
		{
			try
			{
				urls.add(file.toURI().toURL());
			}
			catch(MalformedURLException e)
			{
				e.printStackTrace();
			}
		}
		return urls.toArray(new URL[0]);
	}
	
	protected Set<Class<?>> loadJar(File file)
	{
		Set<Class<?>> classes = new Set<>();
		try
		{
			try(JarFile jarFile = new JarFile(file);)
			{
				Enumeration<JarEntry> entries = jarFile.entries();
				while(entries.hasMoreElements())
				{
					JarEntry jarEntry = entries.nextElement();
					String name = jarEntry.getName();
					if(name.endsWith(".class"))
					{
						name = name.replaceFirst("\\.class$", "").replaceAll("/", ".");
						try
						{
							classes.add(loadClass(name, true));
						}
						catch(Throwable e)
						{
							
						}
					}
				}
			}
		}
		catch(Throwable e)
		{
			
		}
		jarFileClassesMap.put(file, classes);
		return classes;
	}
	
	public Map<File, Set<Class<?>>> getJarFileClassesMap()
	{
		return jarFileClassesMap;
	}
	
}
