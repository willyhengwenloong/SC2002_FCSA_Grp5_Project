package statusEffects;
import combatants.Combatants;
public class ArcaneBlast implements StatusEffects{
	private static final int ATTACK_BONUS = 10;
	
	@Override
    public void apply(Combatants target) {
        target.setAttack(target.getAttack() + ATTACK_BONUS);
        System.out.println(target.getName() + " gains +10 attack from Arcane Blast.");
	}

}
