package ro.ase.acs.container;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

public class IoCContainer {

	private Map<Class<?>, Class<?>> map=new HashMap<>(); 
	
	public   void register(Class<?> contract, Class<?> implementation)
	{
		if(!map.containsKey(contract))
		{
			map.put(contract, implementation);
		}
	}
	
	public <T> T resolve(Class<?> contract) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException
	{
		if(map.containsKey(contract))
		{
			try {
				Class<?> classType=Class.forName(map.get(contract).getName());
				Constructor<?> constructor=classType.getConstructor();
				Object obj=constructor.newInstance();
				return (T)obj;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(e.getMessage());
			} 
		}
		return null;
	}
}
