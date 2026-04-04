package ui;

public class ConsoleUI {
	
	private static final String LINE  = "=============================";
	private static final String DLINE = "_____________________________";
	
	public static void printLoadingScreen()
	{
		System.out.println(LINE);
		System.out.println(" TURN-BASED COMBAT ARENA");
		System.out.println(LINE);
		System.out.println();
	}
	
	//Game Setup CLI
	public static void printPlayerChoices() {
		System.out.println("Choose Your Character");
		System.out.println(DLINE);
		System.out.println("1. Warrior");
		System.out.println("HP: 260 ATK: 40 DEF: 20 SPD: 30");
		System.out.println("Special Skill: Shield Bash - Deals Damage + stuns target");
		System.out.println();
		System.out.println("2. Wizard");
		System.out.println("HP: 200 ATK: 50 DEF: 10 SPD: 20");
		System.out.println("Special Skill: Arcane Blast - AOE Damage, Kills Boost ATK");
		System.out.println(DLINE);
		System.out.println("Enter choice (1-2): ");
	}
	
}
