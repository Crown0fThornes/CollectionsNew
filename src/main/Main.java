package main;
import java.lang.reflect.*;

import groups.*;

public class Main {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		LinkedSeries<Integer> list = new LinkedSeries<>();

		for (int i = 0; i < 10; i++) list.add(i);

		print(list);
		
		list.filter(n -> n % 2 == 0);
		
		print(list);
		
	}
	
	public static void print(LinkedSeries<Integer> l) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {

        // Get the Class object for the MyClass
        Class<?> myClass = l.getClass();

        // Get the declared method by name
        Method print = myClass.getDeclaredMethod("fullArrayToString");

        // Enable access to the private method
        print.setAccessible(true);

        // Invoke the private method on an instance of the class
        System.out.println(print.invoke(l));
	}

}
