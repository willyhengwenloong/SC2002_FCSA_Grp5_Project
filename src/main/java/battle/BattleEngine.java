package battle;

import java.util.ArrayList;
import java.util.List;

import actions.BasicAttack;
import actions.Defend;
import combatants.Combatants;
import combatants.Enemy;
import combatants.Goblin;
import combatants.Player;
import combatants.Wolf;
import strategy.TurnOrderStrategy;
import strategy.speedBasedTurnOrderStrategy;
import ui.DisplayManager;
import ui.InputHandler;

public class BattleEngine {
	public void start(Player player) {
		List<Combatants> enemies = new ArrayList<>();
		enemies.add(new Goblin());
		enemies.add(new Wolf());
		
		System.out.println("\nBattle start against a Goblin and a Wolf");
		
		int round = 1;
		TurnOrderStrategy turnStrategy = new speedBasedTurnOrderStrategy();
		
		while (player.isAlive() && hasAliveEnemies(enemies)) {
			DisplayManager.printRoundHeader(round);
			
			List<Combatants> allCombatants = new ArrayList<>();
			allCombatants.add(player);
			for (Combatants e: enemies) {
				if(e.isAlive()) {
					allCombatants.add(e);
				}
			}
			
			List<Combatants> turnOrder = turnStrategy.determineTurnOrder(allCombatants);
			DisplayManager.printTurnOrder(turnOrder);
			
			for (Combatants current : turnOrder) {
				if (!current.isAlive() || (!player.isAlive() || !hasAliveEnemies(enemies))) {
					continue;
				}
				
				if (current.isStunned()) {
					DisplayManager.printStunnedSkip(current);
					current.setStunTurnsRemaining(current.getStunTurnsRemaining() - 1);
					
					if (current.getStunTurnsRemaining() <= 0) {
						current.setStunned(false);
					}
					continue;
				}
				
				if (current instanceof Player) {
					processPlayerTurn((Player) current, enemies);
				} else {
					processEnemyTurn((Enemy) current, player);
				}
			}
			
			DisplayManager.printRoundEndSummary(player, enemies);
			
			for (Combatants c : allCombatants) {
				if (c.getDefenseBuffTurnsRemaining() > 0) {
					c.setDefenseBuffTurnsRemaining(c.getDefenseBuffTurnsRemaining() - 1);
					if(c.getDefenseBuffTurnsRemaining() == 0) {
						c.setDefenseBuff(0);
					}
				}
			}
			round++;
		}
		
		if (player.isAlive()) {
			DisplayManager.printVictoryScreen(player.getCurrentHp(), player.getMaxHp(), round, player);
		} else {
			int remaining = 0;
			for (Combatants e : enemies)
				if (e.isAlive())
					remaining++;
			DisplayManager.printDefeatScreen(remaining, round);
		}
	}
	
	private void processPlayerTurn(Player player, List<Combatants> enemies) {
		DisplayManager.printPlayerActionMenu(player, true, new ArrayList<>());
		int choice = InputHandler.readInt(1, 4);
		
		List<Combatants> aliveEnemies = new ArrayList<>();
		for (Combatants e : enemies) {
			if (e.isAlive()) {
				aliveEnemies.add(e);
			}
		}
		
		if (choice == 1) {
			DisplayManager.printTargetSelectionMenu(aliveEnemies);
			int targetChoice = InputHandler.readInt(1, aliveEnemies.size());
			Combatants target = aliveEnemies.get(targetChoice - 1);
			BasicAttack attack = new BasicAttack();
			attack.execute(player, target);
        } else if (choice == 2) {
            Defend defend = new Defend();
            defend.execute(player);
        } else if (choice == 3) {
            System.out.println(
                    "Items are not fully implemented in the current mini-version framework, using basic attack on first enemy!");
            if (!aliveEnemies.isEmpty()) {
                BasicAttack attack = new BasicAttack();
                attack.execute(player, aliveEnemies.get(0));
            }
        } else if (choice == 4) {
            DisplayManager.printTargetSelectionMenu(aliveEnemies);
            int targetChoice = InputHandler.readInt(1, aliveEnemies.size());
            Combatants target = aliveEnemies.get(targetChoice - 1);
            List<Combatants> targetList = new ArrayList<>();
            targetList.add(target);
            player.executeSpecialSkill(targetList, enemies);
        }
    }

    private void processEnemyTurn(Enemy enemy, Player player) {
        BasicAttack specializedAttack = new BasicAttack();
        specializedAttack.execute(enemy, player);
    }

    private boolean hasAliveEnemies(List<Combatants> enemies) {
        for (Combatants e : enemies) {
            if (e.isAlive())
                return true;
        }
        return false;
    }
		
	}

