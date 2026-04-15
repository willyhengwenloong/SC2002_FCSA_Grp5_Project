package combatants;

import strategy.BasicAttackStrategy;

public class Goblin extends Enemy{
	
	private static int instanceCount = 0;
	
	//Goblin enemy. HP 55, Att 35, Def 15, Spd 25
	//Goblin A, Goblin B etc
	public Goblin() {
		super("Goblin " + (char)('A' + instanceCount++),55, 35, 15, 25, new BasicAttackStrategy());
	}
	
	public static void resetCounter() {
		instanceCount = 0;
	}
}
