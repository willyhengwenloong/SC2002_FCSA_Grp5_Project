package statusEffects;
import combatants.Combatants;
/**
 * StunEffect: prevents the target from acting for the specified number of turns.
 * Applied by Warrior's Shield Bash.
 */
public class StunEffect implements StatusEffect {

    private int turnsRemaining;
    private boolean expired;

    public StunEffect(int turns) {
        this.turnsRemaining = turns;
        this.expired = false;
    }

    @Override
    public String onTurnStart(Combatants target) {
        // The stun message is handled in BattleEngine when checking if combatant can act.
        return null;
    }

    /** Called by BattleEngine after the stunned combatant's turn is skipped. */
    @Override
    public void tick() {
        if (turnsRemaining > 0) {
            turnsRemaining--;
            if (turnsRemaining == 0) {
                expired = true;
            }
        }
    }

    @Override
    public boolean isExpired() { return expired; }

    @Override
    public int getDuration() { return turnsRemaining; }

    @Override
    public String getName() { return "Stun"; }

    public boolean isStunned() { return turnsRemaining > 0; }
}