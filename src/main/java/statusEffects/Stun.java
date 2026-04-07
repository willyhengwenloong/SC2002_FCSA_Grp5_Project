package statusEffects;
import combatants.Combatants;
public class Stun implements StatusEffects{
	private static final int STUN_DURATION=2;
	
	@Override
	public void apply(Combatants target) {
		target.setStunned(true);
		target.setStunTurnsRemaining(STUN_DURATION);
		System.out.println(target.getName() + " is stunned and cannot act for this turn and next turn.");
	}

}
