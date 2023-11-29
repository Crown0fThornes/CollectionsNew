package main;
import groups.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		ArraySeries<Integer> intSeries = new ArraySeries<>();
//		Scanner input = new Scanner(System.in);
		
//		for(int i = 0; i < 10; i++) {
//			intSeries.add(i);
//		}
//		
//		int next = input.nextInt();
//		
//		while(next != -1) {
//			for(int i = 0; i < intSeries.size(); i++) {
//				System.out.print(intSeries.get(i) + ", ");
//			}
//			
//			System.out.println("");
//			
//			intSeries.add(next, 0);
//			
//			for(int i = 0; i < intSeries.size(); i++) {
//				System.out.print(intSeries.get(i) + ", ");
//			}
//			
//			next = input.nextInt();
//		}
		

        //Reflection magic
//		try {
//	        Class<?> myClass = intSeries.getClass();
//	        Method fullArrayToString = null;
//			fullArrayToString = myClass.getDeclaredMethod("fullArrayToString");
//	        fullArrayToString.setAccessible(true);
//	        
//	        for (int i = 0; i < 20; i++) {
//	        	intSeries.add(i);
//	        	if (i == 11) intSeries.shrink();
//	        	System.out.println(fullArrayToString.invoke(intSeries)); 
//	        }
//	        
//	        for (int i = 0; i < 20; i++) {
//	        	intSeries.remove(0);
//	        	if (i == 11) intSeries.shrink();
//	        	System.out.println(fullArrayToString.invoke(intSeries));
//	        }
//			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
//		ArrayDict<String, Integer> map = new ArrayDict<>();
//		
//		for (int i = 0; i < 100; i++) {
//			map.add("" + i, i);
//		}
//		
//		System.out.println(map.get("50"));
		

		ArraySeries<Integer> nums = new ArraySeries<>();
		for (int i = 0; i < 10; i++) nums.add(i);
		
		for (int num : nums) System.out.println(num);
		
		
		
	}

}
