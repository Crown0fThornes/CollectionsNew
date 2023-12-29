package main;
import java.lang.reflect.*;
import java.util.Arrays;

import groups.*;

public class Main {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		int res = 0;
		
		for (int i = 0; i < 100; i++) {
		 res += speedTest();
		}
		System.out.println(res);
		
	}
	
	public static LinkedSeries<Integer> linkedRange(int startInc, int endExc, int step) {
		LinkedSeries<Integer> res = new LinkedSeries<>();
		for (int i = startInc; i < endExc; i += step) res.add(i);
		return res;
	}
	
	public static ArraySeries<Integer> arrayRange(int startInc, int endExc, int step) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		ArraySeries<Integer> res = new ArraySeries<>();
		for (int i = startInc; i < endExc; i += step) res.add(i);
		return res;
	}
	
	public static void print(AbstractSeries<Integer> l) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {

        // Get the Class object for the MyClass
        Class<?> myClass = l.getClass();

        // Get the declared method by name
        Method print = myClass.getDeclaredMethod("fullArrayToString");

        // Enable access to the private method
        print.setAccessible(true);

        // Invoke the private method on an instance of the class
        System.out.println(print.invoke(l));
	}
	
	public static int speedTest() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		
		int max = 100;
		
		int size = getRandomNumber(0, max / 2);
		
//		System.out.println(size);
		
		LinkedSeries<Integer> toAdd = LinkedSeries.generate(linkedRange(0, getRandomNumber(0, size), 1), n -> true, n -> getRandomNumber(0, size / 4));
		int[] toAddInt = new int[toAdd.size()];
		for (int i = 0; i < toAddInt.length; i++) toAddInt[i] = toAdd.get(i);
		Arrays.toString(toAddInt);
		
		LinkedSeries<Integer> toRemove = LinkedSeries.generate(linkedRange(0, getRandomNumber(0, size), 1), n -> true, n -> getRandomNumber(0, size / 4));
		int[] toRemoveInt = new int[toRemove.size()];
		for (int i = 0; i < toRemoveInt.length; i++) toRemoveInt[i] = toRemove.get(i);
		
		long arrayDur = 0;
		long linkedDur = 0;
		
		{
			long startTime = System.nanoTime();
		
			ArraySeries<Integer> linkedSubject = new ArraySeries<>();
			for (int i = 0; i < size; i++) linkedSubject.add(i);
			for (int cur : toAddInt) linkedSubject.add(cur, 0);
			for (int cur : toRemoveInt) linkedSubject.remove(getRandomNumber(0, linkedSubject.size()));
			for (int i = 0; i < linkedSubject.size(); i++) linkedSubject.get(i);
			for (int cur : linkedSubject);
			
			long endTime = System.nanoTime();
			arrayDur = startTime - endTime;
		}
		
		{
			long startTime = System.nanoTime();
			
			LinkedSeries<Integer> linkedSubject = new LinkedSeries<>();
			for (int i = 0; i < size; i++) linkedSubject.add(i);
			for (int cur : toAddInt) linkedSubject.add(cur, 0);
			for (int cur : toRemoveInt) linkedSubject.remove(getRandomNumber(0, linkedSubject.size()));
			for (int i = 0; i < linkedSubject.size(); i++) linkedSubject.get(i);
			for (int cur : linkedSubject);
			
			long endTime = System.nanoTime();
			linkedDur = startTime - endTime;
		}
		
		return arrayDur < linkedDur ? 1 : -1;
		
		
	}

	public static int getRandomNumber(int min, int max) {
	    return (int) ((Math.random() * (max - min)) + min);
	}
	
}
