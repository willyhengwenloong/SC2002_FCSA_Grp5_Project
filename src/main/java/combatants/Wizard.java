package combatants;

public class Wizard extends Player{
	private static final int Base_Hp = 200;
	private static final int Base_Attack = 50;
	private static final int Base_Defense = 10;
	private static final int Base_Speed = 20;
	
	public Wizard() {
		super("Wizard", Base_Hp, Base_Attack, Base_Defense, Base_Speed);
	}
	
	public String getSpecialSkillName() {
		return "Arcane Blast";
	}
}
