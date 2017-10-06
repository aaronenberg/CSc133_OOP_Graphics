package com.mycompany.a2;

import java.util.ArrayList;

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
    	
    private class
    GameObjectCollectionIterator implements IIterator
    {
        private int currentIdx;

        public
        GameObjectCollectionIterator()
        {
            this.currentIdx = -1;
        }
        public boolean
        hasNext()
        {
            if (gameObjectCollection.size() <= 0)
                return false;
            if (currentIdx == gameObjectCollection.size() - 1 )
                return false;
            return true;
        }
    		
        public GameObject
        getNext()
        {
            currentIdx++;
            return gameObjectCollection.get(currentIdx);
        }
    		
        public void
        remove()
        {
            gameObjectCollection.remove(currentIdx--);
        }
    }

}
