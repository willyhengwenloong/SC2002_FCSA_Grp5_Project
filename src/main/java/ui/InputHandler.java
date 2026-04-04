package ui;

import java.util.Scanner;

public class InputHandler {
	private static final Scanner scanner = new Scanner(System.in);
	
	public static int readInt(int min, int max) {
		while(true) {
			try {
				String input = scanner.nextLine().trim();
				int value = Integer.parseInt(input);
				if(value >= min && value <= max) {
					return value;
				}
				System.out.print("Please enter a number between " + min + " and " + max + ": ");
				
			} catch(NumberFormatException e) {
				System.out.print("Invalid Input. Enter a number between " + min + " and " + max + ": ");
			}
		}
	}
}
