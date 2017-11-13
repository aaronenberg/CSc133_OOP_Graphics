package com.mycompany.a3;


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
    private static final int
        INIT_ALIENS = 10,
        INIT_ASTRONAUTS = 4,
        ALIEN_BREACH_PENALTY = 10;
    private static int
        astronautsRescued = 0,
        astronautsRemaining = INIT_ASTRONAUTS,
        aliensSnuckIn = 0,
        aliensRemaining = INIT_ALIENS,
        totalScore = 0;
    private static BGSound bgSound = new BGSound("BGSound.wav");
    private static Sound alienAstronautCollisionSound = new Sound("AlienAstronautCollision.wav");
    private static Sound alienSpawnedSound = new Sound("AlienSpawned.wav");
    private static Sound spaceshipDoorOpensSound = new Sound("SpaceshipDoorOpens.wav");
    
    private static boolean soundOn = false;
    private Spaceship spaceship;
    private static GameObjectCollection gameObjectCollection;

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
    resizeSpaceshipDoor(int keyCode)
    {
        if (spaceship.resizeDoor(keyCode)) {
            setChanged();
            notifyObservers();
            return true;
        }
        return false;
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
            playSound(gameObject, spaceship);

            if ((gameObject instanceof Opponent) && (opponentAtDoor((Opponent) gameObject) != null)) {
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

    public static void
    map()
    {
        IIterator gameObjects = gameObjectCollection.getIterator();

        while (gameObjects.hasNext()) {
            GameObject gameObject = gameObjects.getNext();
            System.out.println(gameObject.toString());
        }
    }

    public void
    tick(int elapsedMilliSecs)
    {
        IIterator gameObjects = gameObjectCollection.getIterator();
        boolean opponentsMoved = false;

        // check there is at least one opponent to move
        while (gameObjects.hasNext() && (astronautsRemaining + aliensRemaining) > 0) {
            GameObject gameObject = gameObjects.getNext();

            if (gameObject instanceof IMoving) {
                IMoving movingGameObject = (IMoving) gameObject;
                movingGameObject.move(elapsedMilliSecs);
                opponentsMoved = true;
            }
        }
        if (opponentsMoved) {
            setChanged();
            notifyObservers();
        }
    }

    public static void
    aliensCollide(Alien alien1, ICollider alien2)
    {
        if (aliensRemaining >= 2 && aliensRemaining < 30) {
            playSound(alien1, (GameObject) alien2);
            Alien spawnedAlien = nearbyAlien(alien1);
            gameObjectCollection.add(spawnedAlien);
            alien1.getCollisions().add(spawnedAlien);
            ((Opponent) alien2).getCollisions().add(spawnedAlien);
            aliensRemaining++;
        }
    }

    public static void
    playSound(GameObject opponent1, GameObject opponent2)
    {
        if (soundOn) {
            if (opponent2 instanceof Spaceship)
                spaceshipDoorOpensSound.play();
            else if (!(opponent1 instanceof Alien && opponent2 instanceof Alien))
                alienAstronautCollisionSound.play();
            else
                alienSpawnedSound.play();
        }
    }

    /*
     * If the randomly selected alien we've chosen to spawn near 
     * is too close to any edge of the GameWorld,
     * then select a spawn point such that the new alien 
     * does not spawn outside of the GameWorld limits and
     * still within close proximity to our randomly selected alien
     */
    private static Alien
    nearbyAlien(Alien alien1)
    {
        float nearbyX;
        float nearbyY;
        int size = alien1.getSize();

        if (alien1.xAtMaxWidth())
            nearbyX = alien1.getX() - size;
        else
            nearbyX = alien1.getX() + size;

        if (alien1.yAtMaxHeight())
            nearbyY = alien1.getY() - size;
        else
            nearbyY = alien1.getY() + size;

        Alien spawnedAlien = new Alien(nearbyX, nearbyY);

        return spawnedAlien;
    }

    public boolean
    healAstronaut()
    {
        boolean astronautHealed = false;
        IIterator gameObjects = gameObjectCollection.getIterator();

        // check there is at least one opponent to move
        while (gameObjects.hasNext()) {
            GameObject gameObject = gameObjects.getNext();

            if (gameObject instanceof ISelectable) {
                Astronaut astronaut = (Astronaut) gameObject;

                if (astronaut.isSelected()) {
                    astronaut.heal();
                    astronautHealed = true;
                    setChanged();
                    notifyObservers();
                }
            }
        }
        return astronautHealed;
    }

    /*
     * When an object is selected in pause mode,
     * and game play is resumed without manually
     * unselecting that object, we want to
     * force that object to become unselected
     * so that the next time we pause the game
     * all objects are unselected.
     */
    public static void
    clearSelected()
    {
        IIterator gameObjects = GameWorld.getGameObjectCollection().getIterator();
        while (gameObjects.hasNext()) {
            GameObject gameObject = gameObjects.getNext();

            if (gameObject instanceof ISelectable) {
                ISelectable astronaut = (ISelectable) gameObject;
                if (astronaut.isSelected())
                    astronaut.setSelected(false);
            }
        }
    }

    public boolean
    checkForAndHandleCollisions()
    {
        IIterator gameObjects1 = gameObjectCollection.getIterator();
        
        while (gameObjects1.hasNext()) {
            GameObject gameObject1 = gameObjects1.getNext();

            if (gameObject1 instanceof ICollider) {
                IIterator gameObjects2 = gameObjectCollection.getIterator();
                
                
                while (gameObjects2.hasNext()) {
                    GameObject gameObject2 = gameObjects2.getNext();
                    
                    if ( (gameObject2 instanceof ICollider) && (gameObject2 != gameObject1) ) {
                        ICollider collider1 = (ICollider) gameObject1;
                        ICollider collider2 = (ICollider) gameObject2;

                        if ( collider1.collidesWith(collider2) ) {
                            collider1.handleCollision(collider2);
                            setChanged();
                            notifyObservers();
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public static int
    getAliensRemaining()
    {
        return aliensRemaining;
    }

    public static int
    getAstronautsRemaining()
    {
        return astronautsRemaining;
    }

    public static int
    getAstronautsRescued()
    {
        return astronautsRescued;
    }
    public static int
    getAliensSnuckIn()
    {
        return aliensSnuckIn;
    }

    public static int
    getScore()
    {
        return totalScore;
    }

    public static boolean
    getSound()
    {
        return soundOn;
    }

    public void
    toggleSound()
    {
        soundOn = !soundOn;
        if (soundOn && !Game.gamePaused())
            BGSound.play();
        else
            BGSound.pause();
        setChanged();
        notifyObservers();
    }

    public static GameObjectCollection
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
