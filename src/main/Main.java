package main;
import java.lang.reflect.*;

import groups.*;

public class Main {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		LinkedSeries<Integer> list = new LinkedSeries<>();

		for (int i = 0; i < 10; i++) list.add(i);
		
		list.remove(5);
		
		try { for (int i = 0; i < list.size() + 2; i++) System.out.println(list.get(i)); }
		catch (Exception e) { e.printStackTrace(); }
		
		
		for (int cur : list) System.out.println(cur);
	}

}
