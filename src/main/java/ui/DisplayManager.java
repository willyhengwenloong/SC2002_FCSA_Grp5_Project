package ui;

import combatants.Combatants;
import combatants.Player;
import items.Items;

import java.util.List;


public class DisplayManager {

	private static final String LINE  = "═══════════════════════════════════════════════════════════";
	private static final String DLINE = "───────────────────────────────────────────────────────────";

	// ── Loading / Setup screens ────────────────────────────────────────────

	public static void printLoadingScreen() {
		System.out.println(LINE);
		System.out.println("         ⚔  TURN-BASED COMBAT ARENA  ⚔");
		System.out.println(LINE);
		System.out.println();
	}

	public static void printPlayerChoices() {
		System.out.println("┌─ Choose Your Character ─────────────────────────────────┐");
		System.out.println("│  1. Warrior                                             │");
		System.out.println("│     HP: 260  ATK: 40  DEF: 20  SPD: 30                  │");
		System.out.println("│     Special: Shield Bash – Deals damage + stuns target  │");
		System.out.println("│                                                         │");
		System.out.println("│  2. Wizard                                              │");
		System.out.println("│     HP: 200  ATK: 50  DEF: 10  SPD: 20                  │");
		System.out.println("│     Special: Arcane Blast – AoE damage; kills boost ATK │");
		System.out.println("└─────────────────────────────────────────────────────────┘");
		System.out.print("Enter choice (1-2): ");
	}

	public static void printEnemyInfo() {
		System.out.println();
		System.out.println("┌─ Enemies ────────────────────────────────────────────────┐");
		System.out.println("│  Goblin  HP:55  ATK:35  DEF:15  SPD:25                   │");
		System.out.println("│  Wolf    HP:40  ATK:45  DEF:5   SPD:35                   │");
		System.out.println("└──────────────────────────────────────────────────────────┘");
	}

	public static void printDifficultyChoices() {
		System.out.println();
		System.out.println("┌─ Select Difficulty ─────────────────────────────────────┐");
		System.out.println("│  1. Easy   – Level 1: 3 Goblins                         │");
		System.out.println("│             No backup spawn                             │");
		System.out.println("│                                                         │");
		System.out.println("│  2. Medium – Level 2: 1 Goblin + 1 Wolf                 │");
		System.out.println("│             Backup spawn: 2 Wolves                      │");
		System.out.println("│                                                         │");
		System.out.println("│  3. Hard   – Level 3: 2 Goblins                         │");
		System.out.println("│             Backup spawn: 1 Goblin + 2 Wolves           │");
		System.out.println("└─────────────────────────────────────────────────────────┘");
		System.out.print("Enter choice (1-3): ");
	}

	public static void printItemChoices() {
		System.out.println();
		System.out.println("┌─ Choose Item " + "────────────────────────────────────────────┐");
		System.out.println("│  1. Potion      – Heal 100 HP (capped at max HP)         │");
		System.out.println("│  2. Power Stone – Free extra special skill use           │");
		System.out.println("│                   (does not affect cooldown)             │");
		System.out.println("│  3. Smoke Bomb  – Enemy attacks deal 0 dmg this+next turn│");
		System.out.println("└──────────────────────────────────────────────────────────┘");
	}

	public static void printItemSlotChoice(int slot) {
		System.out.print("Choose item " + slot + " (1-3, duplicates allowed): ");
	}

	// ── Round/Battle display ───────────────────────────────────────────────

	public static void printRoundHeader(int round) {
		System.out.println();
		System.out.println(LINE);
		System.out.println("  ROUND " + round);
		System.out.println(LINE);
	}

	public static void printTurnOrder(List<Combatants> order) {
		System.out.print("  Turn Order: ");
		for (int i = 0; i < order.size(); i++) {
			System.out.print(order.get(i).getName() + " (SPD " + order.get(i).getSpeed() + ")");
			if (i < order.size() - 1) System.out.print(" → ");
		}
		System.out.println();
		System.out.println(DLINE);
	}

	public static void printActionLog(String message) {
		System.out.println("  " + message);
	}

	public static void printRoundEndSummary(Player player, List<Combatants> enemies, int round) {
		System.out.println(DLINE);
		System.out.print("  End of Round " + round + ": " + player.getStatusSummary());
		System.out.println();
		for (Combatants e : enemies) {
			System.out.println("  " + e.getStatusSummary()
					+ (e.isAlive() ? "" : "  ✗ ELIMINATED"));
		}
		System.out.println();
	}

	public static void printBackupSpawn(List<Combatants> spawned) {
		System.out.println();
		System.out.println("  *** BACKUP SPAWN! New enemies have entered the arena! ***");
		for (Combatants c : spawned) {
			System.out.println("    ➤ " + c.getName() + " [HP: " + c.getCurrentHp() + "]");
		}
		System.out.println();
	}

	// ── Player action menu ─────────────────────────────────────────────────

	public static void printPlayerActionMenu(Player player, boolean specialReady, List<Items> availableItems) {
		System.out.println();
		System.out.println("  ┌─ " + player.getName() + "'s Turn ────────────────────────────────────────┐");
		System.out.println("  │  1. Basic Attack                                               ");
		System.out.println("  │  2. Defend (+10 DEF for current + next round)                  ");

		String itemLine = availableItems.isEmpty()
				? "  │  3. Item         [No items available]                    "
				: "  │  3. Item         " + formatItemsShort(availableItems) ;
		System.out.println(itemLine);

		String cooldownStr = specialReady
				? "[READY]"
				: "[CD: " + player.getSpecialSkillCooldown() + "]";
		System.out.printf("  │  4. %-20s %-26s %n",
				player.getSpecialSkillName(), cooldownStr);
		System.out.println("  └────────────────────────────────────────────────────────┘");
		System.out.print("  Choice: ");
	}

	private static String formatItemsShort(List<Items> items) {
		StringBuilder sb = new StringBuilder();
		for (Items i : items) sb.append(i.getName()).append(" ");
		// Pad to 24 chars
		String s = sb.toString().trim();
		while (s.length() < 24) s += " ";
		if (s.length() > 24) s = s.substring(0, 24);
		return s;
	}

	public static void printTargetSelectionMenu(List<Combatants> aliveEnemies) {
		System.out.println("  Select target:");
		for (int i = 0; i < aliveEnemies.size(); i++) {
			System.out.println("    " + (i + 1) + ". " + aliveEnemies.get(i).getStatusSummary());
		}
		System.out.print("  Choice: ");
	}

	public static void printItemSelectionMenu(List<Items> availableItems) {
		System.out.println("  Select item to use:");
		for (int i = 0; i < availableItems.size(); i++) {
			System.out.println("    " + (i + 1) + ". " + availableItems.get(i).getName()
					+ " – " + availableItems.get(i).getDescription());
		}
		System.out.print("  Choice: ");
	}

	// ── End screens ────────────────────────────────────────────────────────

	public static void printVictoryScreen(int remainingHp, int maxHp, int totalRounds, Player player) {
		System.out.println();
		System.out.println(LINE);
		System.out.println("  🏆  VICTORY!  🏆");
		System.out.println(LINE);
		System.out.println("  Congratulations, you have defeated all your enemies!");
		System.out.println();
		System.out.println("  Statistics:");
		System.out.println("    Remaining HP   : " + remainingHp + " / " + maxHp);
		System.out.println("    Total Rounds   : " + totalRounds);
		System.out.print  ("    Remaining Items: ");
		List<Items> leftover = player.getAvailableItems();
		if (leftover.isEmpty()) {
			System.out.println("None");
		} else {
			leftover.forEach(i -> System.out.print(i.getName() + " "));
			System.out.println();
		}
		System.out.println(LINE);
	}

	public static void printDefeatScreen(int enemiesRemaining, int totalRounds) {
		System.out.println();
		System.out.println(LINE);
		System.out.println("  💀  DEFEATED  💀");
		System.out.println(LINE);
		System.out.println("  Defeated. Don't give up, try again!");
		System.out.println();
		System.out.println("  Statistics:");
		System.out.println("    Enemies Remaining : " + enemiesRemaining);
		System.out.println("    Total Rounds      : " + totalRounds);
		System.out.println(LINE);
	}

	public static void printPostGameMenu() {
		System.out.println();
		System.out.println("  What would you like to do?");
		System.out.println("    1. Replay with the same settings");
		System.out.println("    2. Start a new game (return to home screen)");
		System.out.println("    3. Exit");
		System.out.print("  Choice: ");
	}

	public static void printStunnedSkip(Combatants combatant) {
		System.out.println("  " + combatant.getName() + " is STUNNED – turn skipped!");
	}

	public static void printSmokeBombBlock(Combatants attacker, Combatants target) {
		System.out.println("  " + attacker.getName() + " → Attack blocked by Smoke Bomb! 0 damage to "
				+ target.getName());
	}

	public static void printSmokeBombExpiry(Combatants holder) {
		System.out.println("  Smoke Bomb effect expires for " + holder.getName() + ".");
	}

	public static void printEliminatedSkip(Combatants combatant) {
		System.out.println("  " + combatant.getName() + " → ELIMINATED: Skipped");
	}

	public static void printSeparator() {
		System.out.println(DLINE);
	}
}
