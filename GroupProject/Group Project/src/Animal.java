/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
 

/**
 * Superclass for an animal, which creates the basic properties of an animal: death rate, 
 * birth rate, mortality rate, predation rate.
 * @author 
 */
public class Animal 
{
    
    public String type; //whether the animal is predator or prey (if neither isPredator prey method gives error)
    public double dRate; //diffusion rate
    public double bRate; //birth rate
    public double mRate; //mortality rate
    public double pRate; //predation rate
    public GridMap map;
    
    /**
     * Constructor for super class -- applicable to prey, for predator can have another constructor
     *  
     * @param type Whether the animal is predator or prey.
     * @param dRate The animal diffusion rate.
     * @param bRate The animal birth rate.
     */
    public Animal(String type, double dRate, double bRate)
    {
        this.type=type;
        this.dRate=dRate;
        this.bRate=bRate;
        mRate=0.0;
        pRate=0.0;
        map = null;
        
    }
    
    /**
     * A get method for the animal's diffusion rate.
     * 
     * @return The animal's diffusion rate.
     */   
    public double getDiffusionRate()
    {
        return dRate;
    }
    
    /**
     * A get method for the animal's birth rate.
     * 
     * @return The animal's birth rate.
     */
    public double getBirthRate()
    {
        return bRate;
    }
    
    /**
     * A get method for the animal's mortality rate. 
     * 
     * @return The animal's mortality rate. 
     */
    public double getMortalityRate()
    {
        return mRate;
    }
    
    /**
     * A get method for the animal's predation rate.
     * 
     * @return The animal's predation rate. 
     */
    public double getPredationRate()
    {
        return pRate;
    }
    
    /**
     * A get method that returns the GridMap object that represents the landscape/map.
     * 
     * @return The GridMap representing the landscape.
     */
    public GridMap getMap()
    {
        return map;
    }

    /**
     * This method tells whether the animal is a predator or prey.
     * 
     * @return True is the animal is a predator and false if it is prey. 
     */
    public boolean isPredator()
    {
       boolean result = false;
      
       if(type.compareTo("prey") != 0 && type.compareTo("predator") != 0)
       {
           //Possibly should be a different sort of error?
           throw new IllegalArgumentException("Animal must be 'predator' or 'prey'");
       }
       
       if(type.compareTo("predator") == 0)
       {
           result = true;
       }
       
       return result;
    }
    
    /**
     * This set method changes the animal's diffusion rate.
     * 
     * @param dRate The new diffusion rate. 
     */
    public void setDiffusionRate(double dRate)
    {
        this.dRate=dRate;
    }
    
    /**
     * This set method changes the animal's birth rate.
     * 
     * @param bRate The new birth rate. 
     */
    public void setBirthRate(double bRate)
    {
        this.bRate=bRate;
    }
    
    /**
     * This set method changes the animal's mortality rate.
     * 
     * @param mRate The new mortality rate. 
     */
    public void setMortalityRate(double mRate)
    {
        if(isPredator() == false)
        {
            mRate=0.0;
        }
        else
        {
            this.mRate=mRate;
        }
    }
    
    //This is kind of a personal choice, either the predation rate is applied to prey so goes with prey
    //OR the predatation rate is something which the predator does to the prey hence goes with predator
    //I'm defining it using the latter for now but it can change
    
    /**
     * This set method change's the animal's predation rate; 
     * (This is kind of a personal choice, either the predation rate is applied to prey so goes with prey
     * OR the predatation rate is something which the predator does to the prey hence goes with predator
     * I'm defining it using the latter for now but it can change)
     * 
     * @param pRate The animal's new predation rate.
     */
    public void setPredationRate(double pRate)
    {
        if(isPredator() == false)
        {
            pRate = 0.0;
        }   
        else
        {
            this.pRate = pRate;
        }
    }
    
    /**
     * This set method changes the type of animal to prey or predator.
     * 
     * @param type The animal's new type, either predator or prey.
     */
    public void setType(String type)
    {
        this.type = type;
    }
    
    /**
     * This method sets a new GridMap object to represent the landscape for the animal. 
     * 
     * @param map The new GridMap object. 
     */
    public void setMap(GridMap map)
    {
        this.map = map;
    }
}
