package statusEffects;

import combatants.Combatants;

/**
 * StatusEffect interface - applied to combatants, persists across turns.
 * OCP: new effects can be added without modifying existing code.
 */
public interface StatusEffect {
    /** Called at start of the affected combatant's turn. Returns log message or null. */
    String onTurnStart(Combatants target);

    /** Decrease duration counter. */
    void tick();

    /** True if the effect has expired and should be removed. */
    boolean isExpired();

    /** Duration remaining (in turns). */
    int getDuration();

    String getName();
}