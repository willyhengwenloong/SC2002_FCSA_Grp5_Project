# SC2002_FCSA_Grp5_Project
> School Java Project - a turn based combat arena game that runs entirely in the command line (no GUI)

---

## 👥 Collaborators
* **Willy** - [GitHub Profile](https://github.com/willyhengwenloong)
* **Balaji** - [GitHub Profile](https://github.com/balaji4567)
* **ZhiYou** - [GitHub Profile](https://github.com/3049564941-hub)
* **Uvadharanya** - [GitHub Profile](https://github.com/uva-lgtm)

---

## 🎮 Overview
A Command-line turn-based combat arena developed for the **SC2002 Group Assignment (AY25/26 Semester 2)**.
This project emphasizes **Object-Oriented Design Principles (OODP)** and **SOLID** architecture to create an extensible and robust combat system.

Players take on the role of a **Warrior** or **Wizard** to battle waves of enemies across three difficulty levels: Easy, Medium, and Hard. 




## 🛠️ Prerequisites
To run this project, you need:
* **Java JDK 17** (or your preferred version)
* **Eclipse IDE** (configured for Maven)
* **Git** installed on your machine

---

## ⚙️ First-Time Setup & Initialization
Since this project uses **Maven** (`pom.xml`), do **not** simply "Open" the folder. Follow these steps to ensure the build path is configured correctly:

To ensure the project links correctly with Eclipse and GitHub, follow these exact steps:

1. **Clone the Repository:**
   Open your terminal or Git Bash and run:
   ```bash
   git clone https://github.com/willyhengwenloong/SC2002_FCSA_Grp5_Project.git (https://github.com/your-username/your-repo-name.git)



---

## 🚀 How to Run
1. In the Eclipse **Package Explorer**, navigate to `src/main/java`.
2. Find the class containing the `main` method (e.g. `App.java`).
3. Right-click the file and select **Run As > Java Application**.
4. Use the Eclipse **Console** window to interact with the game.

## Import into Eclipse
1. Open Eclipse and go to **File > Import ...**
2. Select **Maven > Existing Maven Projects** and click **Next**.
3. **Browse** to the folder you just cloned.
4. Ensure the pom.xml checkbox is ticked and clicked finish
5. Wait for the progress bar in the bottom-right corner of Eclipse to finish "Building" the project

## Project Structure
* src/main/java - All functional Java Code
* pom.xml - DO NOT DELETE. This handles all libraries and Java Versions
* .gitignore - Keeps temporary Eclipse junk out of our GitHub

```text

src/main/java	
    ├── battle/                       			# [CONTROL LAYER]
    │   ├── GameManager.java          			# Coordinates the overall game lifecycle and state transitions
    │   ├── BattleEngine.java         			# Manages turn-based combat sequences and round logic
    │   └── GameSetup.java            			# Handles character initialization, item selection, and difficulty
    │
    ├── combatants/                   			# [ENTITY LAYER]
    │   ├── Combatants.java           			# Abstract base class for all fighting units
    │   ├── Player.java               			# Base class for user-controllable entities
    │   ├── Enemy.java                			# Base class for AI-controlled opponents
    │   ├── Warrior.java              			# Specialised Player subclass 
    │   ├── Wizard.java               			# Specialised Player subclass 
    │   ├── Goblin.java               			# Specialised Enemy subclass
    │   └── Wolf.java                 			# Specialised Enemy subclass
    │
    ├── actions/                      
    │   ├── Actions.java              			# Interface defining the execution contract for combat moves
    │   ├── BasicAttack.java          			# Offensive move implementation
    │   ├── Defend.java							# Defensive move implementation
    │   ├── Item.java                 			# Using consumable items in combat
    │   └── SpecialSkills.java        			# Complex abilities unique to specific combatant types
    │
    ├── items/                        
    │   ├── Items.java                			# Base interface for all consumable items and their effects
    │   ├── Potion.java
    │   ├── PowerStone.java           
    │   └── SmokeBomb.java           
    │
    ├── statusEffects/                
    │   ├── StatusEffect.java         			# Interface for recurring effects (DoT, Buffs, Debuffs)
    │   ├── ArcaneBlastEffect.java    
    │   ├── SmokeBombEffect.java      
    │   └── StunEffect.java           
    │
    ├── strategy/							
    │   ├── BasicActionStrategy.java  			# Default behavior for simple actions
    │   ├── EnemyBehaviourStrategy.java			# Interface to decide for enemy move
    │   ├── TurnOrderStrategy.java    			# Interface to decide combatant sequencing per round
    │   └── speedBasedTurnOrderStrategy.java 	# Implementation of turn order based on speed stats
    │    	        
    ├── level/                        
    │   └── Level.java							# Manages environment settings and difficulty scaling
    │
    ├── ui/                           			# [BOUNDARY LAYER]
    │   ├── DisplayManager.java					# Visual formatting and console output rendering
    │   └── InputHandler.java         			# Sanitized user input processing and validation
    │
    └── sc2002/assignment1            
        └── main.java                 			# Entry point of the application

```


    
    
                            

## 📊 Documentation
- [Class Diagram JPG](docs/images/classdiagram.jpg)
- [Sequence Diagram PNG](docs/images/UML_SEQUENCE_DIAGRAM.png)

---

📜 License
This project is licensed under the MIT License - see the LICENSE file for details.

