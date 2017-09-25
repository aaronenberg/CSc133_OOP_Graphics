package com.mycompany.a1;

import java.util.ArrayList;
import java.util.Random;
import com.codename1.charts.models.Point;

public class
GameWorld
{
    private static final int INIT_ALIENS = 3;
    private static final int INIT_ASTRONAUTS = 4;
    private static final int INIT_SPACESHIP = 1;
    private static final int ALIEN_BREACH_PENALTY = 10;
    private static final int HEALTHY_ASTRONAUT_REWARD = 10;
    private int astronautsRescued = 0;
    private int astronautsRemaining = INIT_ASTRONAUTS;
    private int aliensSnuckIn = 0;
    private int aliensRemaining = INIT_ALIENS;
    private int totalScore = 0;
    private ArrayList<GameObject> gameObjectArrayList;

    public void
    init()
    {
	gameObjectArrayList = new ArrayList<GameObject>();
	for (int i = 0; i < INIT_SPACESHIP; i++)
        {
            gameObjectArrayList.add(new Spaceship());
        }
        for (int i = 0; i < INIT_ASTRONAUTS; i++)
        {
            gameObjectArrayList.add(new Astronaut());
        }
	for (int i = 0; i < INIT_ALIENS; i++)
	{
	    gameObjectArrayList.add(new Alien());
	}
    }
    
    public Alien
    alien(int i)
    {
        Alien alien = null;   
        if (aliensRemaining > 0 && (gameObjectArrayList.get(i) instanceof Alien)) {
            alien = (Alien) gameObjectArrayList.get(i);
            return alien;
        }
        return alien;
    }
    
    public Spaceship
    getSpaceship()
    {
        Spaceship spaceship = null;
        for (int i = 0; i < gameObjectArrayList.size(); i++)
        {
            if (gameObjectArrayList.get(i) instanceof Spaceship)
                spaceship = (Spaceship) gameObjectArrayList.get(i);
        }
        return spaceship;
    }
	
    public void
    expandSpaceshipDoor()
    {
        Spaceship spaceship = getSpaceship();
        spaceship.expandDoor();
    }
    
    public boolean
    contractSpaceshipDoor()
    {
        Spaceship spaceship = getSpaceship();
        boolean doorCanContract = spaceship.doorCanContract();
        spaceship.contractDoor();
        return doorCanContract;
    }
    
    public void
    openSpaceshipDoor()
    {
        Spaceship spaceship = getSpaceship();
        spaceship.openDoor();
    }
    public void
    closeSpaceshipDoor()
    {
        Spaceship spaceship = getSpaceship();
        spaceship.closeDoor();
    }
    
    public void
    moveSpaceshipLeft()
    {
        Spaceship spaceship = getSpaceship();
        spaceship.moveLeft();
    }
    public void
    moveSpaceshipRight()
    {
        Spaceship spaceship = getSpaceship();
        spaceship.moveRight();
    }
    public void
    moveSpaceshipUp()
    {
        Spaceship spaceship = getSpaceship();
        spaceship.moveUp();
    }
    public void
    moveSpaceshipDown()
    {
        Spaceship spaceship = getSpaceship();
        spaceship.moveDown();
    }
    public void
    rescue()
    {
        boolean canBeRescued = false;
        for (int i = 0; i < gameObjectArrayList.size(); i++)
        {
            GameObject gameObj = gameObjectArrayList.get(i);
            if (gameObj instanceof Opponent) {
                canBeRescued = opponentAtDoor((Opponent) gameObj);
                if (canBeRescued && (gameObj instanceof Alien)) {
                    updateScore((Opponent) gameObj);
                    gameObjectArrayList.remove(i);
                    this.aliensRemaining--;
                    this.aliensSnuckIn++;
                }
                else if (canBeRescued && (gameObj instanceof Astronaut)) {
                    updateScore((Opponent) gameObj);
                    gameObjectArrayList.remove(i);
                    this.astronautsRemaining--;
                    this.astronautsRescued++;
                }
            }
        }
    }
    
    public void
    transferSpaceshipToAlien()
    {
        Alien alien = getRandomRemainingAlien();
        Spaceship spaceship = getSpaceship();
        spaceship.jumpToLocation(alien.getLocation().getX(), alien.getLocation().getY());
    }
    public void
    transferSpaceshipToAstronaut()
    {
        Astronaut astronaut = getRandomRemainingAstronaut();
        Spaceship spaceship = getSpaceship();
        spaceship.jumpToLocation(astronaut.getLocation().getX(), astronaut.getLocation().getY());
    }
    
    /*
     * Assuming the spaceship door is a square,
     * the Opponent is at the door if it's center is located within 
     * plus or minus half of the spaceship's size from the center of spaceship.
     */
    private boolean
    opponentAtDoor(Opponent opponent)
    {
        boolean isAtDoor = false;
        Spaceship spaceship = getSpaceship();
        Point locSpaceship = spaceship.getLocation();
        Point locOpponent = opponent.getLocation();
        int halfSpaceshipSize = spaceship.getSize()/2;
        
        if
        (
            (
                    (locOpponent.getX() > (locSpaceship.getX() - halfSpaceshipSize)) &&
                    (locOpponent.getX() < (locSpaceship.getX() + halfSpaceshipSize))
            ) &&
            (
                    (locOpponent.getY() > (locSpaceship.getY() - halfSpaceshipSize)) &&
                    (locOpponent.getY() < (locSpaceship.getY() + halfSpaceshipSize))
            )
        )
            isAtDoor = true;

        return isAtDoor;
    }
    

    public void
    map()
    {
        for (int i = 0; i < gameObjectArrayList.size(); i++)
        {
            GameObject go = gameObjectArrayList.get(i);
            if (go instanceof GameObject)
                System.out.println(go.toString());
        }
    }
    
    public void
    tick()
    {
        for (int i = 0; i < gameObjectArrayList.size(); i++)
        {
            if (gameObjectArrayList.get(i) instanceof IMoving) {
                IMoving movingGameObject = (IMoving) gameObjectArrayList.get(i);
                movingGameObject.move();
            }   
        }
    }
    
    public int
    getAliensRemaining()
    {   
        this.aliensRemaining = 0;
        for (int i = 0; i < gameObjectArrayList.size(); i++)
        {
            if (gameObjectArrayList.get(i) instanceof Alien)
                this.aliensRemaining++;
        }
        return this.aliensRemaining;
    }
    
    public int
    getAstronautsRemaining()
    {   
        this.astronautsRemaining = 0;
        for (int i = 0; i < gameObjectArrayList.size(); i++)
        {
            if (gameObjectArrayList.get(i) instanceof Astronaut)
                this.astronautsRemaining++;
        }
        return this.astronautsRemaining;
    }

    public boolean
    aliensCollide()
    {
        boolean collisionOccured = false;
        if (getAliensRemaining() >= 2) {
            gameObjectArrayList.add(nearbyAlien());
            this.aliensRemaining++;
            collisionOccured = true;
        }
        return collisionOccured;
    }
    
    /*
     * If the randomly selected alien we've chosen to spawn near 
     * is too close to any edge of the GameWorld,
     * then select a spawn point such that the new alien 
     * does not spawn outside of the GameWorld limits and
     * still within close proximity to our randomly selected alien
     */
    private Alien
    nearbyAlien()
    {
        float nearbyX;
        float nearbyY;
        Alien randomRemainingAlien = getRandomRemainingAlien();
        
        if (randomRemainingAlien.xAtMaxWidth())
            nearbyX = randomRemainingAlien.getX() - 2;
        else
            nearbyX = randomRemainingAlien.getX() + 2;
        if (randomRemainingAlien.yAtMaxHeight())
            nearbyY = randomRemainingAlien.getY() - 2;
        else
            nearbyY = randomRemainingAlien.getY() + 2;
        Alien spawnedAlien = new Alien(nearbyX, nearbyY);
        
        return spawnedAlien;
    }
    
    private Alien
    getRandomRemainingAlien()
    {
        Alien randomAlien;
        boolean randomAlienChosen = false;
        int randomAlienIdx = 0;
        Random random = new Random();
        
        while (randomAlienChosen == false)
        {
            randomAlienIdx = random.nextInt(gameObjectArrayList.size());
            if (gameObjectArrayList.get(randomAlienIdx) instanceof Alien)
                randomAlienChosen = true;
        }
        randomAlien = (Alien) gameObjectArrayList.get(randomAlienIdx);
        
        return randomAlien;
    }
    
    private Astronaut
    getRandomRemainingAstronaut()
    {
        Astronaut randomAstronaut;
        boolean randomAstronautChosen = false;
        int randomAstronautIdx = 0;
        Random random = new Random();
        
        while (randomAstronautChosen == false)
        {
            randomAstronautIdx = random.nextInt(gameObjectArrayList.size());
            if (gameObjectArrayList.get(randomAstronautIdx) instanceof Astronaut)
                randomAstronautChosen = true;
        }
        randomAstronaut = (Astronaut) gameObjectArrayList.get(randomAstronautIdx);
        
        return randomAstronaut;
    }
    
    public boolean
    fight()
    {
        boolean randomAstronautChosen = false;

        if (getAliensRemaining() >= 2)
        {
            Astronaut randomAstronaut;
            int randomAstronautIdx = 0;
            Random random = new Random();

            while (randomAstronautChosen == false)
            {
                randomAstronautIdx = random.nextInt(gameObjectArrayList.size());
                if (gameObjectArrayList.get(randomAstronautIdx) instanceof Astronaut)
                    randomAstronautChosen = true;
            }
            randomAstronaut = (Astronaut) gameObjectArrayList.get(randomAstronautIdx);
            randomAstronaut.collidesWithAlien();
        }
        return randomAstronautChosen;
    }
    
    public int
    getScore()
    {
        return this.totalScore;
    }
    
    public void
    updateScore(Opponent opponent)
    {
        if (opponent instanceof Alien)
            this.totalScore -= ALIEN_BREACH_PENALTY;
        else if (opponent instanceof Astronaut)
        {
            int reward = 0;
            int health = ((Astronaut) opponent).getHealth();
            reward = health + 5;
            this.totalScore += reward;
        }
    }
    
    public String
    toString()
    {
        String gameState = "       "
                         + "Current score: " + totalScore + " |"
                         + "\n  Astronauts rescued: " + astronautsRescued
                         + " | Aliens on spaceship: " + aliensSnuckIn
                         + "\nAstronauts remaining: " + astronautsRemaining
                         + " |    Aliens remaining: " + aliensRemaining;
        return gameState;
    }
}
