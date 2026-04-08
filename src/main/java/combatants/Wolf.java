package combatants;

public class Wolf extends Enemy{
	private static int instanceCount = 0;
	
	//Wolf Hp 40, Att 45, Def 5, Spd 35
	public Wolf() {
		super("Wolf " + (char)('A' + instanceCount++), 40, 45, 5, 35);
	}
	
	public static void resetCounter() {
		instanceCount = 0;
	}
}
