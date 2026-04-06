package ui;

import java.util.Scanner;


public class InputHandler {

	private static final Scanner scanner = new Scanner(System.in);

	public static int readInt(int min, int max) {
		while (true) {
			try {
				String line = scanner.nextLine().trim();
				int value = Integer.parseInt(line);
				if (value >= min && value <= max) {
					return value;
				}
				System.out.print("Please enter a number between " + min + " and " + max + ": ");
			} catch (NumberFormatException e) {
				System.out.print("Invalid input. Enter a number between " + min + " and " + max + ": ");
			}
		}
	}

	/**
	 * Reads a non-empty line from the user.
	 */
	public static String readLine() {
		while (true) {
			String line = scanner.nextLine().trim();
			if (!line.isEmpty()) return line;
		}
	}

	public static boolean readYesNo(String prompt) {
		while (true) {
			System.out.print(prompt + " (y/n): ");
			String input = scanner.nextLine().trim().toLowerCase();
			if (input.equals("y") || input.equals("yes")) return true;
			if (input.equals("n") || input.equals("no")) return false;
			System.out.println("Please enter 'y' or 'n'.");
		}
	}
}
