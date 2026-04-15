package statusEffects;

import combatants.Combatants;

/**
 * SmokeBombEffect: enemy attacks deal 0 damage for the current turn and the next turn.
 * Applied to the Player when they use a Smoke Bomb item.
 */
public class SmokeBombEffect implements StatusEffect {

    private int turnsRemaining;

    public SmokeBombEffect(int turns) {
        this.turnsRemaining = turns;
    }

    @Override
    public String onTurnStart(Combatants target) {
        return null;
    }

    @Override
    public void tick() {
        if (turnsRemaining > 0) turnsRemaining--;
    }

    @Override
    public boolean isExpired() { return turnsRemaining <= 0; }

    @Override
    public int getDuration() { return turnsRemaining; }

    @Override
    public String getName() { return "Smoke Bomb"; }
}
