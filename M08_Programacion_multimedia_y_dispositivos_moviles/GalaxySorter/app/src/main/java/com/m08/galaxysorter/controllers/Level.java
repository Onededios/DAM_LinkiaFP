package com.m08.galaxyevader.controllers;


import com.m08.galaxyevader.objects.Background;
import com.m08.galaxyevader.objects.Enemy;
import com.m08.galaxyevader.objects.Player;

import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

// The Level class manages the game's levels, enemies, and player interactions
public class Level {
    private final long levelDuration_JOO = 20000;
    private final List<Enemy> enemies_JOO = new CopyOnWriteArrayList<>();
    private Player player_JOO;
    private float enemyMinXSpeed_JOO;
    private float enemyMaxXSpeed_JOO;
    private int actualLevel_JOO;
    private final long startTime_JOO;
    private long enemyPeriod_JOO;
    private final Timer gameTimer_JOO = new Timer();
    private final Background background_JOO;

    // Constructor initializes the background and sets the starting level
    public Level(int startingLevel_JOO) {
        this.background_JOO = new Background();
        actualLevel_JOO = startingLevel_JOO;
        startTime_JOO = System.currentTimeMillis();
        updateLevel();
    }

    public void setPlayer(Player player_JOO) {
        this.player_JOO = player_JOO;
    }

    public int getActualLevel() {
        return actualLevel_JOO;
    }

    public void setActualLevel(int level_JOO) {
        this.actualLevel_JOO = level_JOO;
    }

    public long getEnemyPeriod() {
        return enemyPeriod_JOO;
    }

    public float getEnemyMinXSpeed() {
        return enemyMinXSpeed_JOO;
    }

    public float getEnemyMaxXSpeed() {
        return enemyMaxXSpeed_JOO;
    }

    public Background getBackground() {
        return this.background_JOO;
    }

    // Format the elapsed time since the level started
    public String getFormattedActualTime() {
        long milliseconds_JOO = System.currentTimeMillis() - startTime_JOO;

        long minutes_JOO = (milliseconds_JOO / 60000) % 60;
        long seconds_JOO = (milliseconds_JOO / 1000) % 60;
        long millis_JOO = milliseconds_JOO % 1000;

        return String.format(Locale.UK, "%02d:%02d:%03d", minutes_JOO, seconds_JOO, millis_JOO);
    }

    private float calculateEnemyPeriod() {
        // Calculate the enemy period based on the level duration and current level
        float calculatedPeriod = levelDuration_JOO / (actualLevel_JOO * 4F);
        // Ensure the calculated period is at least 1 millisecond
        return Math.max(calculatedPeriod, 1);
    }

    private float calculateEnemyMinXSpeed() {
        // Calculate the minimum X speed of enemies based on the current level
        return actualLevel_JOO * 3.5F;
    }

    private float calculateEnemyMaxXSpeed() {
        // Calculate the maximum X speed of enemies based on the current level
        return actualLevel_JOO * 3.75F;
    }

    // Update the level parameters based on the current state
    public void updateLevel() {
        this.enemyPeriod_JOO = (long) calculateEnemyPeriod();
        this.enemyMinXSpeed_JOO = calculateEnemyMinXSpeed();
        this.enemyMaxXSpeed_JOO = calculateEnemyMaxXSpeed();
    }

    // Start the game timer and add enemies to the game
    public void startGameTimer() {
        // Initalize all the background stars
        background_JOO.initializeStars();
        // Initialize the methods to make em start working
        addEnemiesToGame();
        background_JOO.setMinSpeed(enemyMinXSpeed_JOO);
        // Schedule a task to run at regular intervals, updating the level and adding enemies
        gameTimer_JOO.schedule(new TimerTask() {
            @Override
            public void run() {
                setActualLevel(getActualLevel() + 1);
                updateLevel();
                addEnemiesToGame();
                background_JOO.setMinSpeed(enemyMinXSpeed_JOO);
            }
        }, levelDuration_JOO, levelDuration_JOO);
    }

    public synchronized void addEnemyToList() {
        enemies_JOO.add(new Enemy((float) (Math.random() * (getEnemyMaxXSpeed() - getEnemyMinXSpeed()) + getEnemyMinXSpeed()), actualLevel_JOO));
    }

    // Add enemies to the game at regular intervals
    public void addEnemiesToGame() {
        // Schedule a task to add enemies at regular intervals
        gameTimer_JOO.schedule(new TimerTask() {
            @Override
            public void run() {
                addEnemyToList();
            }
        }, getEnemyPeriod(), getEnemyPeriod());
    }

    public List<Enemy> getEnemies() {
        return enemies_JOO;
    }

    // Update the positions and interactions of enemies
    public void updateEnemies() {
        // Iterate through the list of enemies and update their positions
        for (Enemy enemy_JOO : enemies_JOO) {
            enemy_JOO.getShip().setPosX();

            // Check if an enemy is outside the screen in the X direction
            if (enemy_JOO.isOutsideScreenInX()) {
                // Update the player's score and remove the enemy from the list
                player_JOO.updatePlayerScore(enemy_JOO.getEnemyPoints());
                enemies_JOO.remove(enemy_JOO);
            }

            // Check for collisions between the player's ship and enemies
            if (enemy_JOO.getShip().shipCollisions(player_JOO.getShip().getShipRect())) {
                // Set the player as collided and stop the game timer
                player_JOO.setCrashed();
                gameTimer_JOO.cancel();
                gameTimer_JOO.purge();
                break; // Break out of the loop since the game is over
            }

        }
    }
}
