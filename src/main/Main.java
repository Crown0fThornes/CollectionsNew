package main;
import groups.ArraySeries;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		ArraySeries<Integer> intSeries = new ArraySeries<>();
		Scanner input = new Scanner(System.in);
		
		for(int i = 0; i < 10; i++) {
			intSeries.add(i);
		}
		
		int next = input.nextInt();
		
		while(next != -1) {
			for(int i = 0; i < intSeries.size(); i++) {
				System.out.print(intSeries.get(i) + ", ");
			}
			
			System.out.println("");
			
			intSeries.add(next, 0);
			
			for(int i = 0; i < intSeries.size(); i++) {
				System.out.print(intSeries.get(i) + ", ");
			}
			
			next = input.nextInt();
		}
		
	}

}
