package level;

import combatants.Combatants;
import combatants.Goblin;
import combatants.Wolf;
import java.util.ArrayList;
import java.util.List;

public class Level {

    public enum Difficulty { EASY, MEDIUM, HARD }

    private final int levelNumber;
    private final Difficulty difficulty;

    public Level(Difficulty difficulty) {
        this.difficulty = difficulty;
        switch (difficulty) {
            case EASY:   this.levelNumber = 1; break;
            case MEDIUM: this.levelNumber = 2; break;
            default:     this.levelNumber = 3; break;
        }
    }

    public int getLevelNumber() { return levelNumber; }
    public Difficulty getDifficulty() { return difficulty; }

    public String getDifficultyName() {
        switch (difficulty) {
            case EASY:   return "Easy";
            case MEDIUM: return "Medium";
            default:     return "Hard";
        }
    }

    public List<Combatants> createInitialEnemies() {
        List<Combatants> enemies = new ArrayList<>();
        switch (difficulty) {
            case EASY:
                // 3 Goblins
                enemies.add(new Goblin());
                enemies.add(new Goblin());
                enemies.add(new Goblin());
                break;
            case MEDIUM:
                // 1 Goblin + 1 Wolf
                enemies.add(new Goblin());
                enemies.add(new Wolf());
                break;
            case HARD:
                // 2 Goblins
                enemies.add(new Goblin());
                enemies.add(new Goblin());
                break;
        }
        return enemies;
    }


    public List<Combatants> createBackupEnemies() {
        List<Combatants> enemies = new ArrayList<>();
        switch (difficulty) {
            case EASY:
                // No backup spawn
                break;
            case MEDIUM:
                // 2 Wolves
                enemies.add(new Wolf());
                enemies.add(new Wolf());
                break;
            case HARD:
                // 1 Goblin + 2 Wolves
                enemies.add(new Goblin());
                enemies.add(new Wolf());
                enemies.add(new Wolf());
                break;
        }
        return enemies;
    }

    public boolean hasBackupSpawn() {
        return difficulty == Difficulty.MEDIUM || difficulty == Difficulty.HARD;
    }

    public String getInitialSpawnDescription() {
        switch (difficulty) {
            case EASY:   return "3 Goblins";
            case MEDIUM: return "1 Goblin + 1 Wolf";
            default:     return "2 Goblins";
        }
    }

    public String getBackupSpawnDescription() {
        switch (difficulty) {
            case MEDIUM: return "2 Wolves";
            case HARD:   return "1 Goblin + 2 Wolves";
            default:     return "None";
        }
    }
}
