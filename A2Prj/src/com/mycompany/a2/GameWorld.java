package com.mycompany.a2;

import java.util.ArrayList;
import java.util.Random;


/*
 * GameWorld is our model which provides the user with
 * the methods to directly manipulate the GameObject
 * instances. It also keeps track of the game's state
 * including number of astronauts and aliens remaining,
 *  number of astronauts and aliens that got into the 
 *  spaceship and the total points earned.
 *  It is responsible for instantiating all GameObjects
 *  at the start of the game and creating or removing
 *  GameObjects during certain events in the game.
 *  The user can see the state of all GameObject instances
 *  by calling map().
 */

public class
GameWorld
{
    private static final int INIT_ALIENS = 3;
    private static final int INIT_ASTRONAUTS = 4;
    private static final int ALIEN_BREACH_PENALTY = 10;
    private int astronautsRescued = 0;
    private int astronautsRemaining = INIT_ASTRONAUTS;
    private int aliensSnuckIn = 0;
    private int aliensRemaining = INIT_ALIENS;
    private int totalScore = 0;
    private ArrayList<GameObject> gameObjectArrayList;
    private Spaceship spaceship;

    public void
    init()
    {
	gameObjectArrayList = new ArrayList<GameObject>();
        gameObjectArrayList.add(new Spaceship());
        spaceship = getSpaceship();

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
    getNextAlien(int i)
    {
        Alien alien = null;
        if (aliensRemaining > 0 && (gameObjectArrayList.get(i) instanceof Alien))
            alien = (Alien) gameObjectArrayList.get(i);
        return alien;         
    }
    public Astronaut
    getNextAstronaut(int i)
    {
        Astronaut astronaut = null;
        if (astronautsRemaining > 0 && (gameObjectArrayList.get(i) instanceof Astronaut))
            astronaut = (Astronaut) gameObjectArrayList.get(i);
        return astronaut;         
    }
    public Spaceship
    getSpaceship()
    {
        Spaceship s = null;
        int i = 0;
        while (s == null)
        {
            if (gameObjectArrayList.get(i) instanceof Spaceship)
                s = (Spaceship) gameObjectArrayList.get(i);
            i++;
        }
        return s;
    }
	
    public boolean
    expandSpaceshipDoor()
    {
        boolean doorCanExpand = spaceship.doorCanExpand();
        spaceship.expandDoor();
        return doorCanExpand;
    }
    
    public boolean
    contractSpaceshipDoor()
    {
        boolean doorCanContract = spaceship.doorCanContract();
        spaceship.contractDoor();
        return doorCanContract;
    }
    
    public void
    openSpaceshipDoor()
    {
        spaceship.openDoor();
    }
    public void
    closeSpaceshipDoor()
    {
        spaceship.closeDoor();
    }
    
    public void
    moveSpaceshipLeft()
    {
        spaceship.moveLeft();
    }
    public void
    moveSpaceshipRight()
    {
        spaceship.moveRight();
    }
    public void
    moveSpaceshipUp()
    {
        spaceship.moveUp();
    }
    public void
    moveSpaceshipDown()
    {
        spaceship.moveDown();
    }

    /*
     * when an object is removed from the list
     * all elements to the right of that object shift left,
     * so we must decrement the counter or else the very next
     * element would be skipped.
     */
    public void
    rescue()
    {
        for (int i = 0; i < gameObjectArrayList.size(); i++)
        {
            if (getNextAlien(i) != null) {
                Alien alien = getNextAlien(i);
                if (opponentAtDoor(alien) != null) {
                    updateScore((Opponent) alien);
                    gameObjectArrayList.remove(i--);
                }
            }
            
            if (getNextAstronaut(i) != null) {
                Astronaut astronaut = getNextAstronaut(i);
                if (opponentAtDoor(astronaut) != null) {
                    updateScore((Opponent) astronaut);
                    gameObjectArrayList.remove(i--);
                }
            }
        }
    }
    
    public void
    transferSpaceshipToAlien()
    {
        Alien alien = getRandomRemainingAlien();
        spaceship.jumpToLocation(alien.getX(), alien.getY());
    }

    public void
    transferSpaceshipToAstronaut()
    {
        Astronaut astronaut = getRandomRemainingAstronaut();
        spaceship.jumpToLocation(astronaut.getX(), astronaut.getY());
    }
    
    /*
     * Assuming the spaceship door is a square,
     * the Opponent is at the door if it's center is located within 
     * plus or minus half of the spaceship's size from the center of spaceship.
     */
    private Opponent
    opponentAtDoor(Opponent opponent)
    {
        int halfSpaceshipSize = spaceship.getSize()/2;
        
        if
        (
            ((opponent.getX() > (spaceship.getX() - halfSpaceshipSize)) &&
             (opponent.getX() < (spaceship.getX() + halfSpaceshipSize))
            ) &&
            ((opponent.getY() > (spaceship.getY() - halfSpaceshipSize)) &&
             (opponent.getY() < (spaceship.getY() + halfSpaceshipSize))
            )
        )
            return opponent;
        Opponent notFound = null;
        return notFound;
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

        if (getAliensRemaining() > 0)
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
        if (opponent instanceof Alien) {
            this.totalScore -= ALIEN_BREACH_PENALTY;
            this.aliensRemaining--;
            this.aliensSnuckIn++;
        }
        else if (opponent instanceof Astronaut)
        {
            int reward = 0;
            int health = ((Astronaut) opponent).getHealth();
            reward = health + 5;
            this.totalScore += reward;
            this.astronautsRemaining--;
            this.astronautsRescued++;
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
