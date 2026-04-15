package battle;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import actions.*;
import combatants.*;
import statusEffects.*;
import items.Items;
import level.Level;
import strategy.TurnOrderStrategy;
import ui.DisplayManager;
import ui.InputHandler;

public class BattleEngine {
	 private final Player player;
	    private final Level level;
	    private final TurnOrderStrategy turnOrderStrategy;

	    private List<Combatants> activeEnemies;
	    private boolean backupSpawned;
	    private int roundNumber;
	    private boolean gameOver;
	    private boolean playerWon;

	    public BattleEngine(Player player, Level level, TurnOrderStrategy turnOrderStrategy) {
	        this.player = player;
	        this.level = level;
	        this.turnOrderStrategy = turnOrderStrategy;
	        this.activeEnemies = new ArrayList<>();
	        this.backupSpawned = false;
	        this.roundNumber = 0;
	        this.gameOver = false;
	        this.playerWon = false;
	    }

	    // ── Public API ──────────────────────────────────────────────────────────

	    public void startBattle() {
	        activeEnemies.addAll(level.createInitialEnemies());
	        runBattle();
	    }

	    public boolean isPlayerWon()    { return playerWon; }
	    public int    getRoundNumber()  { return roundNumber; }
	    public int    getAliveEnemyCount() {
	        return (int) activeEnemies.stream().filter(Combatants::isAlive).count();
	    }

	    // ── Core battle loop ────────────────────────────────────────────────────

	    private void runBattle() {
	        while (!gameOver) {
	            roundNumber++;
	            DisplayManager.printRoundHeader(roundNumber);

	            // Determine turn order for this round (alive combatants only at round start)
	            List<Combatants> allAlive    = buildAllAliveCombatants();
	            List<Combatants> turnOrder   = turnOrderStrategy.determineTurnOrder(allAlive);
	            DisplayManager.printTurnOrder(turnOrder);

	            // Execute every combatant's turn in order
	            for (Combatants current : turnOrder) {

	                // Skip already-eliminated combatants (could die mid-round)
	                if (!current.isAlive()) {
	                    DisplayManager.printEliminatedSkip(current);
	                    continue;
	                }

	                // Check stun before executing turn
	                if (isStunned(current)) {
	                    DisplayManager.printStunnedSkip(current);
	                    tickStun(current);
	                    // Check if player died from stun-skipped enemy (shouldn't happen but safe)
	                    if (checkGameOver()) return;
	                    continue;
	                }

	                // Execute the turn
	                if (current instanceof Player) {
	                    executePlayerTurn((Player) current);
	                } else {
	                    executeEnemyTurn((Enemy) current);
	                }

	                // Check win/loss after every action
	                if (checkGameOver()) return;
	            }

	            // ── End-of-round bookkeeping ──────────────────────────────────
	            endOfRound();

	            // ── Check backup spawn (after initial wave fully cleared) ─────
	            checkBackupSpawn();

	            // ── Round-end status display ──────────────────────────────────
	            DisplayManager.printRoundEndSummary(player, activeEnemies, roundNumber);

	            // Final check (backup may have just been added, so only lose-check matters)
	            if (!player.isAlive()) {
	                gameOver = true;
	                playerWon = false;
	                DisplayManager.printDefeatScreen(getAliveEnemyCount(), roundNumber);
	            }
	        }
	    }

	    // ── Player turn ─────────────────────────────────────────────────────────

	    private void executePlayerTurn(Player player) {
	        List<Combatants> aliveEnemies  = getAliveEnemies();
	        List<Items>      availItems    = player.getAvailableItems();
	        boolean         skillReady    = player.isSpecialSkillReady();

	        DisplayManager.printPlayerActionMenu(player, skillReady, availItems);
	        int choice = InputHandler.readInt(1, 4);

	        switch (choice) {
	            case 1: doBasicAttack(player, aliveEnemies);               break;
	            case 2: doDefend(player);                                  break;
	            case 3: doItem(player, availItems, aliveEnemies);          break;
	            case 4: doSpecialSkill(player, aliveEnemies, skillReady);  break;
	        }

	        // Decrement cooldown at the END of the player's own turn
	        player.decrementCooldown();
	    }

	    private void doBasicAttack(Player player, List<Combatants> aliveEnemies) {
	        Combatants target = selectTarget(aliveEnemies);
	        String log = new BasicAttack().execute(player, singletonList(target));
	        DisplayManager.printActionLog(log);
	    }

	    private void doDefend(Player player) {
	        String log = new Defend().execute(player, null);
	        DisplayManager.printActionLog(log);
	    }

	    private void doItem(Player player, List<Items> availItems, List<Combatants> aliveEnemies) {
	        if (availItems.isEmpty()) {
	            DisplayManager.printActionLog("No items available! Defaulting to Basic Attack.");
	            doBasicAttack(player, aliveEnemies);
	            return;
	        }
	        DisplayManager.printItemSelectionMenu(availItems);
	        int choice = InputHandler.readInt(1, availItems.size());
	        Items chosen = availItems.get(choice - 1);

	        // Power Stone fires the special skill — resolve targets the same way
	        List<Combatants> targets = (chosen instanceof items.PowerStone)
	            ? resolveSpecialTargets(player, aliveEnemies)
	            : aliveEnemies;

	        String log = new Item(chosen).execute(player, targets);
	        DisplayManager.printActionLog(log);
	    }

	    private void doSpecialSkill(Player player, List<Combatants> aliveEnemies, boolean skillReady) {
	        if (!skillReady) {
	            DisplayManager.printActionLog(player.getSpecialSkillName()
	                + " on cooldown (" + player.getSpecialSkillCooldown() + "). Defaulting to Basic Attack.");
	            doBasicAttack(player, aliveEnemies);
	            return;
	        }
	        List<Combatants> targets = resolveSpecialTargets(player, aliveEnemies);
	        String log = new SpecialSkills(aliveEnemies).execute(player, targets);
	        DisplayManager.printActionLog(log);
	    }

	    /**
	     * Warrior → single target prompt; Wizard → all alive enemies (AoE).
	     */
	    private List<Combatants> resolveSpecialTargets(Player player, List<Combatants> aliveEnemies) {
	        if (player instanceof Warrior) {
	            return singletonList(selectTarget(aliveEnemies));
	        }
	        return new ArrayList<>(aliveEnemies); // Wizard AoE
	    }

	    private Combatants selectTarget(List<Combatants> aliveEnemies) {
	        if (aliveEnemies.size() == 1) return aliveEnemies.get(0);
	        DisplayManager.printTargetSelectionMenu(aliveEnemies);
	        int idx = InputHandler.readInt(1, aliveEnemies.size());
	        return aliveEnemies.get(idx - 1);
	    }

	    // ── Enemy turn ──────────────────────────────────────────────────────────

	    private void executeEnemyTurn(Enemy enemy) {
	        List<Combatants> playerTarget = singletonList(player);
	        Actions action = enemy.chooseAction(playerTarget, getAliveEnemies());

	        // Smoke Bomb: player is protected → deal 0 damage
	        if (hasSmokeBomb(player)) {
	            DisplayManager.printSmokeBombBlock(enemy, player);
	            // Do NOT tick smoke bomb here; it ticks once per round at end-of-round
	        } else {
	            String log = action.execute(enemy, playerTarget);
	            DisplayManager.printActionLog(log);
	        }
	    }

	    // ── End-of-round bookkeeping ────────────────────────────────────────────

	    private void endOfRound() {
	        // Tick defense buffs (last 2 rounds: current + next)
	        player.tickDefenseBuff();
	        for (Combatants e : activeEnemies) e.tickDefenseBuff();

	        // Tick Smoke Bomb once per round
	        tickSmokeBombEndOfRound(player);
	    }

	    // ── Backup spawn ────────────────────────────────────────────────────────

	    private void checkBackupSpawn() {
	        if (!backupSpawned && level.hasBackupSpawn()
	                && activeEnemies.stream().noneMatch(Combatants::isAlive)) {
	            backupSpawned = true;
	            List<Combatants> backup = level.createBackupEnemies();
	            activeEnemies.addAll(backup);
	            DisplayManager.printBackupSpawn(backup);
	        }
	    }

	    // ── Game-over check ─────────────────────────────────────────────────────

	    /**
	     * Returns true (and sets gameOver) if the game has ended.
	     * Called after every individual action and at end of round.
	     */
	    private boolean checkGameOver() {
	        if (!player.isAlive()) {
	            gameOver  = true;
	            playerWon = false;
	            DisplayManager.printDefeatScreen(getAliveEnemyCount(), roundNumber);
	            return true;
	        }
	        boolean noEnemies       = activeEnemies.stream().noneMatch(Combatants::isAlive);
	        boolean noMoreBackup    = backupSpawned || !level.hasBackupSpawn();
	        if (noEnemies && noMoreBackup) {
	            gameOver  = true;
	            playerWon = true;
	            DisplayManager.printVictoryScreen(
	                player.getCurrentHp(), player.getMaxHp(), roundNumber, player);
	            return true;
	        }
	        return false;
	    }

	    // ── Status-effect helpers ───────────────────────────────────────────────

	    private boolean isStunned(Combatants c) {
	        for (StatusEffect e : c.getStatusEffects()) {
	            if (e instanceof StunEffect && ((StunEffect) e).isStunned()) return true;
	        }
	        return false;
	    }

	    private void tickStun(Combatants c) {
	        List<StatusEffect> toRemove = new ArrayList<>();
	        for (StatusEffect e : c.getStatusEffects()) {
	            if (e instanceof StunEffect) {
	                e.tick();
	                if (e.isExpired()) {
	                    toRemove.add(e);
	                    DisplayManager.printActionLog("Stun expires on " + c.getName() + ".");
	                }
	            }
	        }
	        c.getStatusEffects().removeAll(toRemove);
	    }

	    private boolean hasSmokeBomb(Combatants c) {
	        for (StatusEffect e : c.getStatusEffects()) {
	            if (e instanceof SmokeBombEffect && !e.isExpired()) return true;
	        }
	        return false;
	    }

	    /**
	     * Ticks Smoke Bomb once at end of round (not per-enemy-hit).
	     * Duration 2: covers the round it was used (remainder) + the next full round.
	     */
	    private void tickSmokeBombEndOfRound(Combatants c) {
	        List<StatusEffect> toRemove = new ArrayList<>();
	        for (StatusEffect e : c.getStatusEffects()) {
	            if (e instanceof SmokeBombEffect) {
	                e.tick();
	                if (e.isExpired()) {
	                    toRemove.add(e);
	                    DisplayManager.printSmokeBombExpiry(c);
	                }
	            }
	        }
	        c.getStatusEffects().removeAll(toRemove);
	    }

	    // ── Utility ─────────────────────────────────────────────────────────────

	    private List<Combatants> getAliveEnemies() {
	        return activeEnemies.stream()
	                            .filter(Combatants::isAlive)
	                            .collect(Collectors.toList());
	    }

	    private List<Combatants> buildAllAliveCombatants() {
	        List<Combatants> all = new ArrayList<>();
	        if (player.isAlive()) all.add(player);
	        all.addAll(getAliveEnemies());
	        return all;
	    }

	    private List<Combatants> singletonList(Combatants c) {
	        List<Combatants> list = new ArrayList<>();
	        list.add(c);
	        return list;
	    }
		
	}

