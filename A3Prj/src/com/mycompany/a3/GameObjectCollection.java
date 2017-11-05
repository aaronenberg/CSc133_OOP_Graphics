package com.mycompany.a3;

import java.util.ArrayList;
import java.util.Random;

public class
GameObjectCollection implements ICollection
{
    private ArrayList<GameObject> gameObjectCollection;

    GameObjectCollection()
    {
        gameObjectCollection = new ArrayList<GameObject>();
    }
    public void
    add(GameObject gameObject)
    {
        gameObjectCollection.add(gameObject);
    }
    
    public IIterator
    getIterator()
    {
        return new GameObjectCollectionIterator();
    }

    public Alien
    getRandomRemainingAlien()
    {
        Alien randomAlien;
        boolean randomAlienChosen = false;
        int randomAlienIdx = 0;
        Random random = new Random();
        
        while (randomAlienChosen == false) {
            randomAlienIdx = random.nextInt(gameObjectCollection.size());
            if (gameObjectCollection.get(randomAlienIdx) instanceof Alien)
                randomAlienChosen = true;
        }
        randomAlien = (Alien) gameObjectCollection.get(randomAlienIdx);
        
        return randomAlien;
    }
    
    public Astronaut
    getRandomRemainingAstronaut()
    {
        Astronaut randomAstronaut;
        boolean randomAstronautChosen = false;
        int randomAstronautIdx = 0;
        Random random = new Random();
        
        while (randomAstronautChosen == false) {
            randomAstronautIdx = random.nextInt(gameObjectCollection.size());
            if (gameObjectCollection.get(randomAstronautIdx) instanceof Astronaut)
                randomAstronautChosen = true;
        }
        randomAstronaut = (Astronaut) gameObjectCollection.get(randomAstronautIdx);
        
        return randomAstronaut;
    }
    	
    private class
    GameObjectCollectionIterator implements IIterator
    {
        private int idx;

        public
        GameObjectCollectionIterator()
        {
            idx = -1;
        }
        public boolean
        hasNext()
        {
            if (gameObjectCollection.size() <= 0)
                return false;
            if (idx == gameObjectCollection.size() - 1 )
                return false;
            return true;
        }
    		
        public GameObject
        getNext()
        {
            idx++;
            return gameObjectCollection.get(idx);
        }

        /*
         * when an object is removed from the array list
         * all elements to the right of that object shift left.
         * We must decrement the counter so that the very next
         * element will not get skipped by getNext() since it 
         * is placed at the current index.
         */
        public void
        remove()
        {
            gameObjectCollection.remove(idx--);
        }
    }

}
