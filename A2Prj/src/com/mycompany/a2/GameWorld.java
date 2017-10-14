package com.mycompany.a2;


import java.util.Observable;
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
GameWorld extends Observable
{
    private static final int INIT_ALIENS          = 3,
                             INIT_ASTRONAUTS      = 4,
                             ALIEN_BREACH_PENALTY = 10;
    private int astronautsRescued   = 0,
                astronautsRemaining = INIT_ASTRONAUTS,
                aliensSnuckIn       = 0,
                aliensRemaining     = INIT_ALIENS,
                totalScore          = 0;
    private boolean   soundOn       = false;
    private Spaceship spaceship;
    private GameObjectCollection gameObjectCollection;

    public void
    init()
    {
        spaceship = Spaceship.getSpaceship();
	gameObjectCollection = new GameObjectCollection();
        gameObjectCollection.add(spaceship);

        for (int i = 0; i < INIT_ASTRONAUTS; i++)
            gameObjectCollection.add(new Astronaut());

	for (int i = 0; i < INIT_ALIENS; i++)
	    gameObjectCollection.add(new Alien());

	System.out.println("Space fights game world initialized.\n");

	setChanged();
    }

    public boolean
    expandSpaceshipDoor()
    {
        boolean doorExpandable = spaceship.doorCanExpand();
        if (doorExpandable) {
            spaceship.expandDoor();
            setChanged();
            notifyObservers();
        }
        return doorExpandable;
    }

    public boolean
    contractSpaceshipDoor()
    {
        boolean doorContractable = spaceship.doorCanContract();
        if (doorContractable) {
            spaceship.contractDoor();
            setChanged();
            notifyObservers();
        }
        return doorContractable;
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
        setChanged();
        notifyObservers();
    }

    public void
    moveSpaceshipRight()
    {
        spaceship.moveRight();
        setChanged();
        notifyObservers();
    }

    public void
    moveSpaceshipUp()
    {
        spaceship.moveUp();
        setChanged();
        notifyObservers();
    }

    public void
    moveSpaceshipDown()
    {
        spaceship.moveDown();
        setChanged();
        notifyObservers();
    }


    public boolean
    rescue()
    {
        IIterator gameObjects = gameObjectCollection.getIterator();
        boolean aboardShip = false;

        while (gameObjects.hasNext()) {
            GameObject gameObject = gameObjects.getNext();

            if (gameObject instanceof Opponent
                && opponentAtDoor((Opponent) gameObject) != null)
            {
                aboardShip = true;
                updateScore((Opponent) gameObject);
                gameObjects.remove();
            }
        }
        if (aboardShip) {
            System.out.println("The door automatically shuts.\n");
            setChanged();
            notifyObservers();
        }
        return aboardShip;
    }

    public void
    spaceshipToAlien()
    {
        Alien alien = gameObjectCollection.getRandomRemainingAlien();
        spaceship.jumpToLocation(alien.getX(), alien.getY());
        setChanged();
        notifyObservers();
    }

    public void
    spaceshipToAstronaut()
    {
        Astronaut astronaut = gameObjectCollection.getRandomRemainingAstronaut();
        spaceship.jumpToLocation(astronaut.getX(), astronaut.getY());
        setChanged();
        notifyObservers();
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
        IIterator gameObjects = gameObjectCollection.getIterator();

        while (gameObjects.hasNext()) {
            GameObject gameObject = gameObjects.getNext();
            System.out.println(gameObject.toString());
        }
    }

    public void
    tick()
    {
        IIterator gameObjects = gameObjectCollection.getIterator();
        boolean opponentsMoved = false;

        // check there is at least one opponent to move
        while (gameObjects.hasNext() && (astronautsRemaining + aliensRemaining) > 0) {
            GameObject gameObject = gameObjects.getNext();

            if (gameObject instanceof IMoving) {
                IMoving movingGameObject = (IMoving) gameObject;
                movingGameObject.move();
                opponentsMoved = true;
            }
        }
        if (opponentsMoved) {
            setChanged();
            notifyObservers();
        }
    }

    public int
    getAliensRemaining()
    {
        return aliensRemaining;
    }

    public int
    getAstronautsRemaining()
    {
        return astronautsRemaining;
    }

    public void
    aliensCollide()
    {
        if (getAliensRemaining() >= 2) {
            gameObjectCollection.add(nearbyAlien());
            aliensRemaining++;
            setChanged();
            notifyObservers();
        }
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
        Alien randomRemainingAlien = gameObjectCollection.getRandomRemainingAlien();

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

    public boolean
    fight()
    {
        if (aliensRemaining > 0 && astronautsRemaining > 0) {
            Astronaut randomAstronaut;
            randomAstronaut = gameObjectCollection.getRandomRemainingAstronaut();
            randomAstronaut.collidesWithAlien();
            setChanged();
            notifyObservers();
            return true;
        }
        return false;
    }

    public int
    getAstronautsRescued()
    {
        return astronautsRescued;
    }
    public int
    getAliensSnuckIn()
    {
        return aliensSnuckIn;
    }

    public int
    getScore()
    {
        return totalScore;
    }

    public boolean
    getSound()
    {
        return soundOn;
    }

    public void
    toggleSound()
    {
        soundOn = !soundOn;
        setChanged();
        notifyObservers();
    }

    public GameObjectCollection
    getGameObjectCollection()
    {
        return gameObjectCollection;
    }

    public void
    initLocationsOnMap()
    {
        IIterator gameObjects = gameObjectCollection.getIterator();
        while (gameObjects.hasNext()) {
            GameObject gameObject = gameObjects.getNext();
            gameObject.setLocation(randomX(), randomY());
        }
        setChanged();
        notifyObservers();
    }

    private float
    randomX()
    {
        Random rX = new Random();
        float x = rX.nextFloat() * Game.getMapWidth();
        float roundedX = Math.round(x*10.0f)/10.0f;
        return roundedX;
    }

    private float
    randomY()
    {
        Random rY = new Random();
        float y = rY.nextFloat() * Game.getMapHeight();
        float roundedY = Math.round(y*10.0f)/10.0f;
        return roundedY;
    }

    public void
    updateScore(Opponent opponent)
    {
        if (opponent instanceof Alien) {
            totalScore -= ALIEN_BREACH_PENALTY;
            aliensRemaining--;
            aliensSnuckIn++;
            System.out.println("An alien sneaks in!");
        }
        else if (opponent instanceof Astronaut)
        {
            int reward = 0;
            int health = ((Astronaut) opponent).getHealth();
            reward = health + 5;
            totalScore += reward;
            astronautsRemaining--;
            astronautsRescued++;
            System.out.println("You rescue an astronaut.");
        }
    }
}
