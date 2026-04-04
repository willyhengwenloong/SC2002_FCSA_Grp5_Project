package combatants;

public class Warrior extends Player {
	private static final int Base_HP = 260;
	private static final int Base_Attack = 40;
	private static final int Base_Defense = 20;
	private static final int Base_Speed = 30;
	
	public Warrior() {
		super("Warrior", Base_HP, Base_Attack, Base_Defense, Base_Speed);
	}
}
